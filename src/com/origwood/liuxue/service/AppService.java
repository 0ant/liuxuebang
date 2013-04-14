package com.origwood.liuxue.service;

import android.content.Context;

import com.google.inject.Singleton;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.origwood.liuxue.bean.Result;
import com.origwood.liuxue.util.Json2Bean;

/**
 * 所有业务方法
 * 
 * @author wanggang
 * @date： 2013-4-14 email: 315331371@qq.com
 */
@Singleton
public class AppService {
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
							a.onSuccese(str);
						}
					}

				});

		return null;
	}

}
