package com.origwood.liuxue.ui;

import java.io.File;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.inject.Inject;
import com.origwood.liuxue.R;
import com.origwood.liuxue.bean.Result;
import com.origwood.liuxue.service.AbsAppServiceOnFinished;

@ContentView(R.layout.activity_perfectinfo)
public class PerfectInfo extends Base {
	@InjectView(R.id.title)
	private TextView mTitle;
	@Inject
	private ImageView mIconImageView;
	@Inject
	private TextView mSexTextView;
	@Inject
	private TextView mNicknameTextView;
	@Inject
	private TextView mStageTextView;
	@Inject
	private TextView mPhomeTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mTitle.setText("信息完善页面");
	}

	public void update(View view) {
		Drawable useicon = mIconImageView.getDrawable();
		String sex = mSexTextView.getText().toString().trim();
		String nickname = mNicknameTextView.getText().toString().trim();
		String stage = mStageTextView.getText().toString().trim();
		String phone = mPhomeTextView.getText().toString().trim();
		service.subInfoSetting(useicon, sex, nickname, stage, phone,
				new AbsAppServiceOnFinished() {

					@Override
					public void onSuccess(Object object) {
					}

					@Override
					public void onFailed(Result result) {
					}
				},this);
	}
}
