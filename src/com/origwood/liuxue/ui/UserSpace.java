package com.origwood.liuxue.ui;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.origwood.liuxue.R;
import com.origwood.liuxue.bean.Result;
import com.origwood.liuxue.bean.User;
import com.origwood.liuxue.service.AbsAppServiceOnFinished;

@ContentView(R.layout.activity_user)
public class UserSpace extends Base implements OnClickListener {
	@InjectView(R.id.nickname)
	TextView tvNickName;
	@InjectView(R.id.layout_fans)
	LinearLayout layoutFans;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		layoutFans.setOnClickListener(this);
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

	@Override
	public void onClick(View v) {
		Intent mIntent = null;
		switch (v.getId()) {
		case R.id.layout_fans:
			mIntent = new Intent(this, MyFans.class);
			mIntent.putExtra("title", "ÎÒµÄ·ÛË¿");
			startActivity(mIntent);
			break;

		default:
			break;
		}

	}
}