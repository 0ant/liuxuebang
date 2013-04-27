package com.origwood.liuxue.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.Toast;

import com.google.inject.Singleton;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.origwood.liuxue.bean.Result;
import com.origwood.liuxue.bean.User;
import com.origwood.liuxue.util.ImageTools;
import com.origwood.liuxue.util.Json2Bean;

/**
 * ����ҵ�񷽷�
 * 
 * @author wanggang
 * @date�� 2013-4-14 email: 315331371@qq.com
 */
@Singleton
public class AppService {
	private Context context;
	private static final String DUG_TAG = "AppService";

	public AppService() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AppService(Context context) {
		super();
		this.context = context;
	}

	static AsyncHttpClient client = new AsyncHttpClient();

	public static void stop(Context context, boolean b) {
		client.cancelRequests(context, b);
	}

	/**�����޸�
	 * @param newPwd
	 * @param oldPwd
	 * @param a
	 * @param context
	 */
	public void modifyPwd(String newPwd, String oldPwd,
			final AppServiceOnFinished a, final Context context) {
		RequestParams params = new RequestParams();
		params.put("newPwd", newPwd);
		params.put("oldPwd", oldPwd);
		Log.d(DUG_TAG, URLs.MODIFYPWD);
		client.get(URLs.MODIFYPWD, params, new AsyncHttpResponseHandler() {

			@Override
			public void onFailure(Throwable arg0, String arg1) {
				Result result = new Result();
				result.setMsg("���Ӵ���");
				a.onFailed(result);
				Log.e(DUG_TAG, arg0 + arg1);
			}

			@Override
			public void onSuccess(String response) {
				Result result = Json2Bean.getResult(response);
				if (result.getSubResultType() == 0) {
					a.onFailed(result);
				} else {
					PersistentCookieStore myCookieStore = new PersistentCookieStore(
							context);
					client.setCookieStore(null);
					client.setCookieStore(myCookieStore);
					a.onSuccess(result);
				}
			}

		});
	}

	public static Object getUserById(String id, final AppServiceOnFinished a) {

		client.get("http://42.96.136.159/app/subUserLogin",
				new AsyncHttpResponseHandler() {
					@Override
					public void onSuccess(String response) {
						Result result = Json2Bean.getResult(response);
						if (result.getSubResultType() == 0) {
							a.onFailed(result);
						} else {
							String str = "wanggang";
							a.onSuccess(str);
						}
					}

				});

		return null;
	}

	/**
	 * �û���¼
	 * 
	 * @param username
	 *            �û���
	 * @param password
	 *            ����
	 * @param a
	 *            ��ɺ�ص��ӿ�
	 * @param context
	 *            �����Ķ���
	 */
	public void login(String username, String password,
			final AppServiceOnFinished a, final Context context) {
		RequestParams params = new RequestParams();
		params.put("loginName", username);
		params.put("password", password);

		client.get(URLs.LOGIN, params, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(String response) {

				Result result = Json2Bean.getResult(response);
				if (result.getSubResultType() == 0) {
					a.onFailed(result);

				} else {
					PersistentCookieStore myCookieStore = new PersistentCookieStore(
							context);
					client.setCookieStore(myCookieStore);
					a.onSuccess(result);
				}
			}

			@Override
			public void onFailure(Throwable arg0, String arg1) {

				Result result = new Result();
				result.setMsg("���ӳ���");
				a.onFailed(result);

				super.onFailure(arg0, arg1);
			}

		});
	}

	/**
	 * ����û���Ϣ
	 * 
	 * @param onFinished
	 */
	public void getUserInfo(final AppServiceOnFinished onFinished) {

		client.get(URLs.USERINFO, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(String response) {
				Result result = Json2Bean.getResult(response);
				if (result.getSubResultType() == 0) {
					onFinished.onFailed(result);
				} else {
					User user = Json2Bean.getBeanFromHasResult(response,
							User.class);
					onFinished.onSuccess(user);
				}
			}

		});
	}

	/**
	 * �û�ע��
	 * 
	 * @param username
	 * @param password
	 * @param onFinished
	 * @param context
	 */
	public void register(final String username, final String password,
			final AppServiceOnFinished onFinished, final Context context) {
		RequestParams params = new RequestParams();
		params.put("loginName", username);
		params.put("password", password);

		client.get(URLs.REGISTER, params, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(String response) {

				Result result = Json2Bean.getResult(response);
				if (result.getSubResultType() == 0) {
					onFinished.onFailed(result);

				} else {
					Toast.makeText(context, "ע��ɹ�", Toast.LENGTH_SHORT).show();
					login(username, password, onFinished, context);

				}
			}

			@Override
			public void onFailure(Throwable arg0, String arg1) {

				Result result = new Result();
				result.setMsg("������������");
				onFinished.onFailed(result);
				super.onFailure(arg0, arg1);
			}

		});
	}

	/**
	 * ����û����Ƿ�ע��
	 * 
	 * @param username
	 *            �û���
	 * @param onFinished
	 *            ���֮��Ļص��ӿ�
	 */
	public void checkIsRegister(String username,
			final AppServiceOnFinished onFinished) {

		RequestParams params = new RequestParams();
		params.put("loginName", username);
		client.get(URLs.CHECKISREGISTER, params,
				new AsyncHttpResponseHandler() {
					@Override
					public void onSuccess(String response) {
						Result result = Json2Bean.getResult(response);
						if (result.getSubResultType() == 0) {
							onFinished.onFailed(result);
						} else {
							onFinished.onSuccess(null);
						}
					}
				});
	}

	/**���¸�������
	 * @param usericon
	 * @param sex
	 * @param nickname
	 * @param stage
	 * @param phone
	 * @param onFinished
	 * @param context
	 */
	public void subInfoSetting(File usericon, String sex, String nickname,
			String stage, String phone, final AppServiceOnFinished onFinished,
			final Context context) {
		Log.i(DUG_TAG, sex + stage);
		RequestParams params = new RequestParams();
//		byte[] icon = ImageTools.getInstance().Drawable2Bytes(usericon);
//		params.put("headImgFile", new ByteArrayInputStream(icon), "icon.png");
		try {
			params.put("headImgFile", usericon);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		params.put("sex", sex);
		params.put("nickName", nickname);
		params.put("stage", stage);
		params.put("mobilePhone", phone);
		client.post(URLs.INFOSETTING, params, new AsyncHttpResponseHandler() {

			@Override
			public void onFailure(Throwable arg0, String arg1) {
				Log.e(DUG_TAG, arg0 + arg1);
				onFinished.onFailed(null);
			}

			@Override
			public void onSuccess(String response) {
				Log.i(DUG_TAG, response);
				Result result = Json2Bean.getResult(response);
				if (result.getSubResultType() == 0) {
					onFinished.onFailed(result);
				} else {
					onFinished.onSuccess(null);
				}
			}

		});
	}

	/**��ȡ������ѧ�׶�
	 * @param context
	 * @param onFinished
	 */
	public void getUserAllStage(final Context context,
			final AbsAppServiceOnFinished onFinished) {
		final Result result = new Result();
		client.get(URLs.GETUSERALLSTAGE, new AsyncHttpResponseHandler() {
			@Override
			public void onFailure(Throwable arg0, String arg1) {
				Log.e(DUG_TAG, "�����쳣");
				result.setMsg("�����쳣");
				onFinished.onFailed(result);
			}

			@Override
			public void onSuccess(String content) {
				Log.i(DUG_TAG, content);
				try {
					JSONObject jsonObject = new JSONObject(content);
					int subResultType = jsonObject.optInt("subResultType");
					if (subResultType == 1) {
						LinkedHashMap<String, String> values = new LinkedHashMap<String, String>();
						values.clear();
						JSONArray jsonArray = jsonObject.getJSONArray("items");
						for (int i = 0; i < jsonArray.length(); i++) {
							JSONObject obj = jsonArray.getJSONObject(i);
							String name = obj.getString("name");
							String value = obj.getString("value");
							values.put(name, value);
							Log.i(DUG_TAG, name + "===" + value);
						}
						onFinished.onSuccess(values);
						return;
					}
					result.setMsg("�������쳣");
					onFinished.onFailed(result);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**������
	 * @param name
	 * @param descript
	 * @param file
	 * @param OnFinished
	 */
	public void subCreateGroupApply(String name, String descript, File file,
			final AbsAppServiceOnFinished OnFinished) {
		RequestParams params=new RequestParams();
		params.put("name", name);
		params.put("note", descript);
		try {
			params.put("imgFile", file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Log.i(DUG_TAG, URLs.SUBCREATEGROUPAPPLY);
		client.post(URLs.SUBCREATEGROUPAPPLY, params, new AsyncHttpResponseHandler(){

			@Override
			public void onFailure(Throwable arg0, String content) {
				Log.e(DUG_TAG, arg0+content);
				Result result=new Result();result.setMsg("�����쳣");
				OnFinished.onFailed(result);
			}

			@Override
			public void onSuccess(int arg0, String content) {
				Log.i(DUG_TAG, arg0+content);
				if(arg0!=200){
					OnFinished.onFailed(new Result("������󣬱���ʧ��"));
				}
				try {
					JSONObject jsonObject=new JSONObject(content);
					int subResultType=jsonObject.getInt("subResultType");
					if(subResultType==1){
						OnFinished.onFailed(new Result("����ɹ�"));
						return;
					}
					JSONObject errorMap=jsonObject.getJSONObject("errorMap");
					String msg=errorMap.getString("msg");
					OnFinished.onFailed(new Result(msg));
				} catch (JSONException e) {
					Log.e(DUG_TAG, "���ص�json��ʽ����");
					e.printStackTrace();
				}
//				OnFinished.onSuccess(result);
			}
			
		});
	}

}
