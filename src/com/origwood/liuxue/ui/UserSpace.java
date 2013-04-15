package com.origwood.liuxue.ui;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.origwood.liuxue.R;
import com.origwood.liuxue.bean.Result;
import com.origwood.liuxue.bean.User;
import com.origwood.liuxue.service.AbsAppServiceOnFinished;

@ContentView(R.layout.activity_user)
public class UserSpace extends Base {
	@InjectView(R.id.nickname)
	TextView tvNickName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		service.getUserInfo(new AbsAppServiceOnFinished() {

			@Override
			public void onSuccess(Object object) {
				User user = (User) object;
				tvNickName.setText(user.getNickName());
				super.onSuccess(object);
			}

			@Override
			public void onFailed(Result result) {
				Toast.makeText(getApplicationContext(), result.getMsg(),
						Toast.LENGTH_SHORT).show();
				super.onFailed(result);
			}

		});

	}
}
