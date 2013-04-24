package com.origwood.liuxue.util;

import static com.origwood.liuxue.AppContext.DEBUG;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.origwood.liuxue.bean.Result;

/**
 * the tool class for change json to object
 * 
 * @author wanggang Email: 315331371@qq.com
 * 
 */
public class Json2Bean {
	/**
	 * 解析请求结果 格式类似
	 * 
	 * <pre>
	 * {"subResultType":0,"errorMap":{"field":"fromUserId","msg":"关注者不存在"}}
	 * </pre>
	 * 
	 * @param content
	 * @return
	 */
	public static Result getResult(String content) {
		if (DEBUG)
			Loger.i("result:" + content);
		try {
			JSONObject jsonObject = new JSONObject(content);
			Result result = new Result();
			if (jsonObject.optJSONObject("errorMap") != null) {
				result.setMsg(jsonObject.optJSONObject("errorMap").optString(
						"msg"));
			}
			result.setSubResultType(jsonObject.optInt("subResultType"));
			return result;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解析数据，把
	 * <p>
	 * 
	 * </p>
	 * 
	 * @param content
	 * @param beanClass
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBeanFromHasResult(String content, Class<T> beanClass) {
		if (DEBUG)
			Loger.i("result" + content);
		try {
			JSONObject jsonObject = new JSONObject(content)
					.getJSONObject("item");
			Iterator<String> keys = jsonObject.keys();
			T bean = null;
			try {
				bean = beanClass.newInstance();
			} catch (InstantiationException e1) {

				e1.printStackTrace();
			} catch (IllegalAccessException e1) {

				e1.printStackTrace();
			}
			if (DEBUG)
				Loger.i("beanClass=" + beanClass.getName());
			while (keys.hasNext()) {
				String key = keys.next();

				if (DEBUG)
					Loger.i("the field is '" + key + "'");
				Field field;
				try {
					field = beanClass.getDeclaredField(key);
					field.setAccessible(true);
					field.set(bean, jsonObject.get(key));
				} catch (NoSuchFieldException e) {
					Loger.e("NoSuchFieldException");
					e.printStackTrace();
				} catch (IllegalArgumentException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (JSONException e) {

					e.printStackTrace();
				}
			}
			return bean;
		} catch (JSONException e) {

			e.printStackTrace();
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(String content, Class<T> beanClass) {
		if (content == null) {
			return null;
		}
		if (DEBUG)
			Loger.i(content);
		T bean = null;
		try {
			JSONObject jsonObject = new JSONObject(content);
			Iterator<String> keys = jsonObject.keys();
			bean = beanClass.newInstance();
			if (DEBUG)
				Loger.i("beanClass=" + beanClass.getName());
			while (keys.hasNext()) {
				String key = keys.next();

				if (DEBUG)
					Loger.i("the field is '" + key + "'");
				Field field;
				try {
					field = beanClass.getDeclaredField(key);
					field.setAccessible(true);
					field.set(bean, jsonObject.get(key));
				} catch (NoSuchFieldException e) {
					Loger.e("NoSuchFieldException");
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
	}

	public static <T> List<T> getList(String content, Class<T> beanClass) {
		if (content == null) {
			return null;
		}
		List<T> list;
		if (DEBUG)
			Loger.i(content);
		JSONArray jsonArray = null;
		try {
			jsonArray = new JSONArray(content);
		} catch (JSONException e) {
			Loger.e("The wrong JSON data format");
			return null;
		}
		list = new ArrayList<T>();
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = (JSONObject) jsonArray.opt(i);
			if (jsonObject != null) {

				list.add(getBean(jsonObject.toString(), beanClass));
			}
		}
		return list;
	}

	public static <T> LinkedList<T> getLinkedList(String content,
			Class<T> beanClass) {
		if (content == null) {
			return null;
		}
		LinkedList<T> list;
		if (DEBUG)
			Loger.i(content);
		JSONArray jsonArray = null;
		try {
			jsonArray = new JSONArray(content);
		} catch (JSONException e) {
			Loger.e("The wrong JSON data format");
			return null;
		}
		list = new LinkedList<T>();
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = (JSONObject) jsonArray.opt(i);
			if (jsonObject != null) {

				list.add(getBean(jsonObject.toString(), beanClass));
			}
		}
		return list;
	}
}
