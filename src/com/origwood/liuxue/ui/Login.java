package com.origwood.liuxue.ui;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.origwood.liuxue.R;
import com.origwood.liuxue.bean.Result;
import com.origwood.liuxue.service.AbsAppServiceOnFinished;

@ContentView(R.layout.activity_login_register)
public class Login extends Base implements OnClickListener {

	@InjectView(R.id.login)
	Button btnLogin;
	@InjectView(R.id.register)
	Button btnRegister;
	@InjectView(R.id.username)
	EditText etUsername;
	@InjectView(R.id.password)
	EditText etPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		btnLogin.setOnClickListener(this);
		btnRegister.setOnClickListener(this);
		etUsername.setText("wanggang123");
		etPassword.setText("wanggang");

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.login:
			service.login(etUsername.getText().toString(), etPassword.getText()
					.toString(), new AbsAppServiceOnFinished() {

				@Override
				public void onSuccess(Object object) {

					Toast.makeText(getApplicationContext(), "登录成功",
							Toast.LENGTH_SHORT).show();
					startActivity(new Intent(Login.this, Main.class));

				}

				@Override
				public void onFailed(Result result) {
					showFaildedMessage(result, getApplicationContext());

				}

			}, this);

			break;
		case R.id.register:
			service.register(etUsername.getText().toString(), etPassword
					.getText().toString(), new AbsAppServiceOnFinished() {

				@Override
				public void onSuccess(Object object) {

					Toast.makeText(getApplicationContext(), "登录成功",
							Toast.LENGTH_SHORT).show();
					startActivity(new Intent(Login.this, Main.class));

				}

				@Override
				public void onFailed(Result result) {
					showFaildedMessage(result, getApplicationContext());

				}

			}, this);
			break;
		default:
			break;
		}
	}

}
