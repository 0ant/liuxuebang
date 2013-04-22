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
import com.origwood.liuxue.util.Loger;

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
		// setContentView(R.layout.activity_login_register);
		// btnLogin = (Button) findViewById(R.id.login);
		// btnRegister = (Button) findViewById(R.id.register);
		btnLogin.setOnClickListener(this);
		btnRegister.setOnClickListener(this);
		etUsername.setText("testtest");
		etPassword.setText("123456");
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.login:
			service.login(etUsername.getText().toString(), etPassword.getText()
					.toString(), new AbsAppServiceOnFinished() {

				@Override
				public void onSuccess(Object object) {

					Toast.makeText(getApplicationContext(), "µÇÂ¼³É¹¦",
							Toast.LENGTH_SHORT).show();
					startActivity(new Intent(Login.this, Main.class));

				}

				@Override
				public void onFailed(Result result) {
					Loger.i("onFailt:" + result.getMsg());
					Toast.makeText(getApplicationContext(), result.getMsg(),
							Toast.LENGTH_SHORT).show();
					super.onFailed(result);
				}

			}, this);

			break;
		case R.id.register:
			service.checkIsRegister(etUsername.getText().toString(),
					new AbsAppServiceOnFinished() {

						@Override
						public void onSuccess(Object object) {

							super.onSuccess(object);
						}

						@Override
						public void onFailed(Result result) {
							Toast.makeText(getApplicationContext(),
									result.getMsg(), Toast.LENGTH_SHORT).show();
							super.onFailed(result);
						}

					});
			break;
		default:
			break;
		}
	}

}
