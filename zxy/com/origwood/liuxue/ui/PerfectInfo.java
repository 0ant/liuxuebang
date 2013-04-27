package com.origwood.liuxue.ui;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Set;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.origwood.liuxue.R;
import com.origwood.liuxue.bean.Result;
import com.origwood.liuxue.service.AbsAppServiceOnFinished;

@ContentView(R.layout.activity_perfectinfo)
public class PerfectInfo extends GetPhote implements View.OnClickListener {

	private final static String DUG_TAG = "PerfectInfo";
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
	private LinkedHashMap<String, String> stageHashMap=null;
	private AlertDialog mProgress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
	}

	private void init() {
		mTitle.setText("信息完善页面");
		//测试用
		mSexTextView.setText("男");
		mNicknameTextView.setText("wewe");
//		mStageTextView.setText("即将出国");
		//注册事件
		mIconImageView.setOnClickListener(this);
		mSexTextView.setOnClickListener(this);
		mNicknameTextView.setOnClickListener(this);
		mStageTextView.setOnClickListener(this);
		mPhomeTextView.setOnClickListener(this);
	}
	public void dismissProgress(){
		if(mProgress!=null){
			mProgress.dismiss();
			mProgress=null;
		}
	}
	public void update(View view) {
		
		view.setFocusable(false);
		dismissProgress();
		mProgress=CustomProgressDialog.create(this, "资料更新中");
		mProgress.show();
		String sex ="false";
		if(mSexTextView.getText().toString().trim().equals("男"))
			sex = "true";
		String nickname = mNicknameTextView.getText().toString().trim();
		String stage = mStageTextView.getText().toString().trim();
		String phone = mPhomeTextView.getText().toString().trim();
		service.subInfoSetting(getImageFile(), sex, nickname, stageHashMap.get(stage), phone,
				new AbsAppServiceOnFinished() {

					@Override
					public void onSuccess(Object object) {
						dismissProgress();
						Toast.makeText(PerfectInfo.this, "更新成功",
								Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onFailed(Result result) {
						dismissProgress();
						Toast.makeText(PerfectInfo.this, "更新失败",
								Toast.LENGTH_SHORT).show();
					}
				}, this);
	}

	@Override
	public void onClick(View v) {
		
		
		
		final EditText value = new EditText(this);
		switch (v.getId()) {
		case R.id.pi_icon:
			getPhoto();
//			setUsericon();
			break;
		case R.id.pi_nicknanme:
			new AlertDialog.Builder(this)
					.setTitle("更改昵称")
					.setIcon(android.R.drawable.ic_dialog_info)
					.setView(value)
					.setPositiveButton(
							"确定",
							new android.content.DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									mNicknameTextView.setText(value.getText());
								}

							}).setNegativeButton("取消", null).show();
			break;
		case R.id.pi_mobilephone:
			value.setInputType(InputType.TYPE_CLASS_PHONE);
			new AlertDialog.Builder(this)
					.setTitle("更改手机号码")
					.setIcon(android.R.drawable.ic_dialog_info)
					.setView(value)
					.setPositiveButton(
							"确定",
							new android.content.DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									mPhomeTextView.setText(value.getText());
								}
							}).setNegativeButton("取消", null).show();
			break;
		case R.id.pi_sex:
			new AlertDialog.Builder(this)
					.setTitle("性别")
					.setIcon(android.R.drawable.ic_dialog_info)
					.setSingleChoiceItems(new String[] { "男", "女" }, 0,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									if (which == 0)
										mSexTextView.setText("男");
									else if (which == 1)
										mSexTextView.setText("女");
									dialog.dismiss();
								}
							}).setNegativeButton("取消", null).show();
			break;
		case R.id.pi_stage:
			// 留学阶段信息
			setStageInfo();
			break;
		default:
			break;
		}
	}

	private void setStageInfo() {
		service.getUserAllStage(this,new AbsAppServiceOnFinished(){

			@SuppressWarnings("unchecked")
			@Override
			public void onSuccess(Object object) {
				stageHashMap=(LinkedHashMap<String, String>) object;
				Set<String> stageSet = stageHashMap.keySet();
				final Object[] s = stageSet.toArray();
				final String[] stages = new String[s.length];
				for (int i = 0; i < s.length; i++) {
					stages[i] = s[i].toString();
					Log.i(DUG_TAG, stages[i]);
				}
				new AlertDialog.Builder(PerfectInfo.this)
						.setTitle("选择留学阶段")
						.setIcon(android.R.drawable.ic_dialog_info)
						.setSingleChoiceItems(stages, 0,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										mStageTextView.setText(stages[which]);
										dialog.dismiss();
									}
								}).setNegativeButton("取消", null).show();
			}

			@Override
			public void onFailed(Result result) {
				Toast.makeText(PerfectInfo.this, result.getMsg(), Toast.LENGTH_SHORT).show();
			}});
	}

	@Override
	protected void setConf() {
		folderName="user_pic";
		defaultPicName="ic_usericon.png";
		picName="user_icon.jpg";
		imageView=mIconImageView;
	}
}
