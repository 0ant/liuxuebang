package com.origwood.liuxue;

import java.text.SimpleDateFormat;

import org.json.JSONObject;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.origwood.liuxue.common.UIHelper;
import com.origwood.liuxue.keep.AccessTokenKeeper;
import com.origwood.liuxue.ui.Base;
import com.renren.api.connect.android.Renren;
import com.renren.api.connect.android.exception.RenrenAuthError;
import com.renren.api.connect.android.view.RenrenAuthListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.weibo.sdk.android.Oauth2AccessToken;
import com.weibo.sdk.android.Weibo;
import com.weibo.sdk.android.WeiboAuthListener;
import com.weibo.sdk.android.WeiboDialogError;
import com.weibo.sdk.android.WeiboException;

/**
 * 应用入口
 * 
 * @author wanggang
 * @date： 2013-3-31 email: 315331371@qq.com
 */
public class AppStart extends Base implements OnClickListener {
	private static final String APP_ID = "100413604";
	private static final String SCOPE = "all";
	private View btnQQ, btnSina, btnRenRen, btnLogin, btnRegister;
	private Tencent mTencent;
	private Renren renren;
	private Weibo mWeibo;
	private static final String SINA_CONSUMER_KEY = "739483384";// 替换为开发者的appkey，例如"1646212860";
	private static final String SINA_REDIRECT_URL = "http://www.sina.com";
	private static final String RENREN_API_KEY = "01726894bdb1441996b6eaa3c2782b33";
	private static final String RENREN_SECRET_KEY = "d1398c3386044c72b4e18230452b7167";
	private static final String RENREN_APP_ID = "231259";
	public static Oauth2AccessToken accessToken;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		btnQQ = findViewById(R.id.qq);
		btnSina = findViewById(R.id.sina);
		btnRenRen = findViewById(R.id.renren);
		btnLogin = findViewById(R.id.login);
		btnRegister = findViewById(R.id.register);
		btnQQ.setOnClickListener(this);
		btnSina.setOnClickListener(this);
		btnRenRen.setOnClickListener(this);
		btnLogin.setOnClickListener(this);
		btnRegister.setOnClickListener(this);
		mTencent = Tencent.createInstance(APP_ID, this.getApplicationContext());
		renren = new Renren(RENREN_API_KEY, RENREN_SECRET_KEY, RENREN_APP_ID,
				this);
		mWeibo = Weibo.getInstance(SINA_CONSUMER_KEY, SINA_REDIRECT_URL);

	}

	private void onClickLogin() {
		if (!mTencent.isSessionValid()) {
			IUiListener listener = new BaseUiListener() {
				@Override
				protected void doComplete(JSONObject values) {

				}
			};
			mTencent.login(this, SCOPE, listener);
		} else {
			mTencent.logout(this);

		}
	}

	private class BaseUiListener implements IUiListener {

		@Override
		public void onComplete(JSONObject response) {
			// mBaseMessageText.setText("onComplete:");
			// mMessageText.setText(response.toString());
			Toast.makeText(getApplicationContext(), "授权成功", 1000);
			doComplete(response);
		}

		protected void doComplete(JSONObject values) {

		}

		@Override
		public void onError(UiError e) {
			// showResult("onError:", "code:" + e.errorCode + ", msg:"
			// + e.errorMessage + ", detail:" + e.errorDetail);
		}

		@Override
		public void onCancel() {
			// showResult("onCancel", "");
		}
	}

	class AuthDialogListener implements WeiboAuthListener {

		@Override
		public void onComplete(Bundle values) {
			String token = values.getString("access_token");
			String expires_in = values.getString("expires_in");
			accessToken = new Oauth2AccessToken(token, expires_in);
			if (accessToken.isSessionValid()) {
				String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
						.format(new java.util.Date(accessToken.getExpiresTime()));
				Toast.makeText(getApplicationContext(),
						"认证成功: \r\n access_token: " + token + "\r\n"
								+ "expires_in: " + expires_in + "\r\n有效期："
								+ date, 2000);

				try {
					Class sso = Class
							.forName("com.weibo.sdk.android.api.WeiboAPI");// 如果支持weiboapi的话，显示api功能演示入口按钮
				} catch (ClassNotFoundException e) {
					// e.printStackTrace();
					Log.i("SinaSDK",
							"com.weibo.sdk.android.api.WeiboAPI not found");

				}

				AccessTokenKeeper.keepAccessToken(getApplicationContext(),
						accessToken);
				Toast.makeText(getApplicationContext(), "认证成功",
						Toast.LENGTH_SHORT).show();
			}
		}

		@Override
		public void onError(WeiboDialogError e) {
			Toast.makeText(getApplicationContext(),
					"Auth error : " + e.getMessage(), Toast.LENGTH_LONG).show();
		}

		@Override
		public void onCancel() {
			Toast.makeText(getApplicationContext(), "Auth cancel",
					Toast.LENGTH_LONG).show();
		}

		@Override
		public void onWeiboException(WeiboException e) {
			Toast.makeText(getApplicationContext(),
					"Auth exception : " + e.getMessage(), Toast.LENGTH_LONG)
					.show();
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.qq:
			onClickLogin();
			break;
		case R.id.sina:
			mWeibo.authorize(this, new AuthDialogListener());
			break;
		case R.id.renren:
			final RenrenAuthListener listener = new RenrenAuthListener() {

				@Override
				public void onComplete(Bundle values) {
					Log.d("test", values.toString());
					// startApiList();
				}

				@Override
				public void onRenrenAuthError(RenrenAuthError renrenAuthError) {

				}

				@Override
				public void onCancelLogin() {
				}

				@Override
				public void onCancelAuth(Bundle values) {
				}

			};
			renren.authorize(this, null, listener, 1);
			break;
		case R.id.login:
			UIHelper.toLogin(AppStart.this);
			// Test.fadeLogin(service,this);
			// startActivity(new Intent(AppStart.this,PerfectInfo.class));
			break;

		default:
			break;
		}

	}
}
