package com.origwood.liuxue.ui;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.origwood.liuxue.R;
import com.origwood.liuxue.bean.Result;
import com.origwood.liuxue.bean.User;
import com.origwood.liuxue.common.UIHelper;
import com.origwood.liuxue.service.AbsAppServiceOnFinished;

@ContentView(R.layout.activity_user)
public class UserSpace extends Base implements OnClickListener {
	@InjectView(R.id.nickname)
	TextView tvNickName;
	@InjectView(R.id.layout_fans)
	LinearLayout layoutFans;
	@InjectView(R.id.layout_attent)
	LinearLayout layoutAttent;
	@InjectView(R.id.layout_collect)
	LinearLayout layoutCollect;
	@InjectView(R.id.layout_group)
	LinearLayout layoutGroup;

	TextView tvAttentAmount;
	TextView tvBeAttentAmount;
	TextView tvJoinGroupAmount;
	TextView tvNoteAmount;
	TextView tvRelation;
	TextView tvTopicAmount;

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
				showFaildedMessage(result, getApplicationContext());
				super.onFailed(result);
			}

		});

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.layout_fans:
			UIHelper.toFans(UserSpace.this, null);
			break;
		case R.id.layout_attent:
			break;
		case R.id.layout_collect:
			break;
		case R.id.layout_group:
			break;
		default:
			break;
		}

	}
}