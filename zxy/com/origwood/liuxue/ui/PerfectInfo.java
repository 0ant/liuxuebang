package com.origwood.liuxue.ui;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.origwood.liuxue.R;
import com.origwood.liuxue.bean.Result;
import com.origwood.liuxue.service.AbsAppServiceOnFinished;

@ContentView(R.layout.activity_perfectinfo)
public class PerfectInfo extends Base implements View.OnClickListener{
	
	private final static String DUG_TAG="PerfectInfo";
	@InjectView(R.id.pi_icon)
	private ImageView mIconImageView;
	@InjectView(R.id.pi_sex)
	private TextView mSexTextView;
	@InjectView(R.id.pi_nicknanme)
	private TextView mNicknameTextView;
	@InjectView(R.id.pi_stage)
	private TextView mStageTextView;
	@InjectView(R.id.pi_mobilephone)
	private TextView mPhomeTextView;
	@InjectView(R.id.title)
	private TextView mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mTitle.setText("信息完善页面");
		mIconImageView.setOnClickListener(this);
	}

//	public void update(View view) {
////		Drawable useicon = mIconImageView.getDrawable();
//		String sex = mSexTextView.getText().toString().trim();
//		String nickname = mNicknameTextView.getText().toString().trim();
//		String stage = mStageTextView.getText().toString().trim();
//		String phone = mPhomeTextView.getText().toString().trim();
//		service.subInfoSetting(null, sex, nickname, stage, phone,
//				new AbsAppServiceOnFinished() {
//
//					@Override
//					public void onSuccess(Object object) {
//					}
//
//					@Override
//					public void onFailed(Result result) {
//					}
//				},this);
//	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.pi_icon:
			Log.i(DUG_TAG, "图片");
			break;

		default:
			break;
		}
	}
}
