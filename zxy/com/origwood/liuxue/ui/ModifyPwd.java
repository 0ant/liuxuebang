package com.origwood.liuxue.ui;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.inject.Inject;
import com.origwood.liuxue.R;
import com.origwood.liuxue.bean.Result;
import com.origwood.liuxue.service.AbsAppServiceOnFinished;
import com.origwood.liuxue.service.AppService;
@ContentView(R.layout.activity_modifypwd)
public class ModifyPwd extends Base {
	@InjectView(R.id.mp_oldpwd)
	private EditText mOldpwdEditText;
	@InjectView(R.id.mp_newpwd)
	private EditText mNewpwdEditText;
	@InjectView(R.id.mp_renewpwd)
	private EditText mReNewEditText;
	@InjectView(R.id.title)
	private TextView mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(android.R.style.Theme_NoTitleBar);
		super.onCreate(savedInstanceState);
		mTitle.setText("注册");
	}

	@Override
	protected void onStart() {

		super.onStart();
	}

	public void edit(View view) {
		mReNewEditText.setError(null);
		String oldPwd=mOldpwdEditText.getText().toString().trim();
		String newPwd=mNewpwdEditText.getText().toString().trim();
		String renewPwd=mReNewEditText.getText().toString().trim();
		if(!TextUtils.isEmpty(newPwd)&&newPwd.equals(renewPwd)){
			service.modifyPwd(newPwd, oldPwd, new AbsAppServiceOnFinished() {

				@Override
				public void onSuccess(Object object) {
					Toast.makeText(getApplicationContext(), "修改成功",
							Toast.LENGTH_SHORT).show();
				}

				@Override
				public void onFailed(Result result) {
					Toast.makeText(getApplicationContext(), result.getMsg(),
							Toast.LENGTH_SHORT).show();
				}
				
			}, this);
			return;
		}
		mReNewEditText.setError("两次输入到密码不一致");
	}

	public void reset(View view) {
		mReNewEditText.setText(null);
		mOldpwdEditText.setText(null);
		mNewpwdEditText.setText(null);
	}
}
