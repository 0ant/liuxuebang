package com.origwood.liuxue.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.origwood.liuxue.R;

public class Login extends Base implements OnClickListener {
	private Button btnLogin, btnRegister;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_register);
		btnLogin = (Button) findViewById(R.id.login);
		btnRegister = (Button) findViewById(R.id.register);
		btnLogin.setOnClickListener(this);
		btnRegister.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
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
