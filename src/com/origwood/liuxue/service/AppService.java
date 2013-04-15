package com.origwood.liuxue.service;

import android.content.Context;

import com.google.inject.Singleton;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.origwood.liuxue.bean.Result;
import com.origwood.liuxue.bean.User;
import com.origwood.liuxue.util.Json2Bean;

/**
 * 所有业务方法
 * 
 * @author wanggang
 * @date： 2013-4-14 email: 315331371@qq.com
 */
@Singleton
public class AppService {
	private Context context;

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
				result.setMsg("连接出错");
				a.onFailed(result);

				super.onFailure(arg0, arg1);
			}

		});
	}

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
}
