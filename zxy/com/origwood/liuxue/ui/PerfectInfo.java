package com.origwood.liuxue.ui;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Set;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.app.AlertDialog;
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
public class PerfectInfo extends Base implements View.OnClickListener {

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
	private IconSetWin selectWindow;
	private LinkedHashMap<String, String> stageHashMap=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mTitle.setText("信息完善页面");
		mIconImageView.setOnClickListener(this);
		mSexTextView.setOnClickListener(this);
		mNicknameTextView.setOnClickListener(this);
		mStageTextView.setOnClickListener(this);
		mPhomeTextView.setOnClickListener(this);
	}

	public void update(View view) {
		Drawable useicon = mIconImageView.getDrawable();
		String sex ="F";
		if(mSexTextView.getText().toString().trim().equals("男"))
			sex = "M";
		String nickname = mNicknameTextView.getText().toString().trim();
		String stage = mStageTextView.getText().toString().trim();
		String phone = mPhomeTextView.getText().toString().trim();
		service.subInfoSetting(useicon, sex, nickname, stageHashMap.get(stage), phone,
				new AbsAppServiceOnFinished() {

					@Override
					public void onSuccess(Object object) {
						Toast.makeText(PerfectInfo.this, "更新成功",
								Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onFailed(Result result) {
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
			setUsericon();
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

	private void setUsericon() {
		selectWindow = new IconSetWin(this, itemsOnClick);
		selectWindow.showAtLocation(mIconImageView, Gravity.BOTTOM
				| Gravity.CENTER_HORIZONTAL, 0, 0);
	}

	private OnClickListener itemsOnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			selectWindow.dismiss();
			switch (v.getId()) {
			case R.id.pi_selectpic:// 选择照片
				Intent getImage = new Intent(Intent.ACTION_GET_CONTENT);
				getImage.addCategory(Intent.CATEGORY_OPENABLE);
				getImage.setType("image/jpeg");
				startActivityForResult(getImage, 0);
				break;
			case R.id.pi_takepic:// 拍摄照片
				Intent getImageByCamera = new Intent(
						"android.media.action.IMAGE_CAPTURE");
				startActivityForResult(getImageByCamera, 1);
				break;
			case R.id.pi_cancle:// 取消
				if (selectWindow.isShowing())
					selectWindow.dismiss();
				break;

			default:
				break;
			}
		}
	};

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		ContentResolver contentResolver = getContentResolver();
		Bitmap myBitmap = null;
		/**
		 * 因为两种方式都用到了startActivityForResult方法，这个方法执行完后都会执行onActivityResult方法，
		 * 所以为了区别到底选择了那个方式获取图片要进行判断
		 * ，这里的requestCode跟startActivityForResult里面第二个参数对应
		 */

		if (requestCode == 0) {

			try {
				Uri selectedImage = data.getData();
				String[] filePathColumn = { MediaStore.Images.Media.DATA };

				Cursor cursor = getContentResolver().query(selectedImage,
						filePathColumn, null, null, null);
				cursor.moveToFirst();

				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
				String picturePath = cursor.getString(columnIndex);
				cursor.close();
				mIconImageView.setImageBitmap(BitmapFactory
						.decodeFile(picturePath));
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (requestCode == 1) {
			try {
				Bundle extras = data.getExtras();
				myBitmap = (Bitmap) extras.get("data");
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				myBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
			} catch (Exception e) {
				e.printStackTrace();
			}
			mIconImageView.setImageBitmap(myBitmap);
		}
	}

	public static Bitmap getPicFromBytes(byte[] bytes,
			BitmapFactory.Options opts) {
		if (bytes != null)
			if (opts != null)
				return BitmapFactory.decodeByteArray(bytes, 0, bytes.length,
						opts);
			else
				return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
		return null;
	}
}
