package com.origwood.liuxue.ui;

import roboguice.inject.InjectView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.inject.Inject;
import com.origwood.liuxue.R;
import com.origwood.liuxue.bean.Result;
import com.origwood.liuxue.service.AbsAppServiceOnFinished;
import com.origwood.liuxue.service.AppService;

public class Login extends Base implements OnClickListener {

	@InjectView(R.id.login)
	Button btnLogin;
	@InjectView(R.id.register)
	Button btnRegister;
	@Inject
	AppService service;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_register);
		// btnLogin = (Button) findViewById(R.id.login);
		// btnRegister = (Button) findViewById(R.id.register);
		btnLogin.setOnClickListener(this);
		btnRegister.setOnClickListener(this);
		// 显示获取数据弹出框
		service.getUserById("test", new AbsAppServiceOnFinished() {

			@Override
			public void onSuccese(Object object) {
				// TODO Auto-generated method stub
				super.onSuccese(object);
			}

			@Override
			public void onFailed(Result result) {
				// TODO Auto-generated method stub
				super.onFailed(result);
			}

		});
		// 隐藏
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.login:
			startActivity(new Intent(this, Main.class));
			break;
		case R.id.register:

			break;
		default:
			break;
		}
	}

}
