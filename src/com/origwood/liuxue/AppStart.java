package com.origwood.liuxue;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.origwood.liuxue.ui.Base;
import com.origwood.liuxue.ui.Login;

/**
 * 应用入口
 * 
 * @author wanggang
 * @date： 2013-3-31 email: 315331371@qq.com
 */
public class AppStart extends Base implements OnClickListener {
	private Button btnQQ, btnSina, btnRenRen, btnLogin, btnRegister;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		btnQQ = (Button) findViewById(R.id.qq);
		btnSina = (Button) findViewById(R.id.sina);
		btnRenRen = (Button) findViewById(R.id.renren);
		btnLogin = (Button) findViewById(R.id.login);
		btnRegister = (Button) findViewById(R.id.register);
		btnQQ.setOnClickListener(this);
		btnSina.setOnClickListener(this);
		btnRenRen.setOnClickListener(this);
		btnLogin.setOnClickListener(this);
		btnRegister.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.qq:

			break;
		case R.id.sina:

			break;
		case R.id.renren:

			break;
		case R.id.login:
			startActivity(new Intent(this, Login.class));
			break;
		case R.id.register:

			break;
		default:
			break;
		}

	}
}
