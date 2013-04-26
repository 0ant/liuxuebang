package com.origwood.liuxue.util;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.origwood.liuxue.BuildConfig;

/**
 * 使用fastjson解析 http://www.oschina.net/p/fastjson
 * <p>
 * 这里仅仅是解析出返回结果中items包裹的数据
 * </p>
 * 
 * @author wanggang
 * @date： 2013-4-26 email: 315331371@qq.com
 */
public class Jsoner {
	public static <T> T parseObject(String text, Class<T> clazz) {

		return JSON.parseObject(JSON.parseObject(text).getString("items"),
				clazz);
	}

	public static <T> List<T> parseArray(String text, Class<T> clazz) {
		return JSON
				.parseArray(JSON.parseObject(text).getString("items"), clazz);
	}

	public static <T> T parseObjectAgency(String text, Class<T> clazz) {
		if (BuildConfig.DEBUG) {
			Loger.i("JSON:" + text);
		}
		return JSON.parseObject(text, clazz);
	}

	public static <T> List<T> parseArrayAgency(String text, Class<T> clazz) {
		return JSON.parseArray(text, clazz);
	}
}
