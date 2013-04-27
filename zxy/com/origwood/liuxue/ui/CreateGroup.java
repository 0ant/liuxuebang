package com.origwood.liuxue.ui;

import java.io.ByteArrayOutputStream;
import java.io.File;

import com.origwood.liuxue.R;
import com.origwood.liuxue.bean.Result;
import com.origwood.liuxue.service.AbsAppServiceOnFinished;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

@ContentView(R.layout.activity_creategroup)
public class CreateGroup extends GetPhote implements View.OnClickListener{
	@InjectView(R.id.group_name) private EditText mNameEditText;
	@InjectView(R.id.group_location) private EditText mLocationEditText;
	@InjectView(R.id.group_descript) private EditText mDescriptEditText;
	@InjectView(R.id.group_photo) private ImageView mPhotoImageView;
	
	@InjectView(R.id.cg_submit) private ImageButton mSubmitButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mSubmitButton.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		String name=mNameEditText.getText().toString().trim();
		String location=mLocationEditText.getText().toString().trim();
		String descript=mDescriptEditText.getText().toString().trim();
		//提供的接口里面没有 位置 
		service.subCreateGroupApply(name,descript,getImageFile(),new AbsAppServiceOnFinished(){

			@Override
			public void onSuccess(Object object) {
				showShortToast((String) object);
			}

			@Override
			public void onFailed(Result result) {
				showShortToast(result.getMsg());
			}
			public void showShortToast(String msg){
				Toast.makeText(CreateGroup.this, msg, Toast.LENGTH_SHORT).show();
			}
		});
	}
	public void setPhoto(View v){
		getPhoto();
	}
	@Override
	protected void setConf() {
		folderName="group_pic";
		defaultPicName="ic_usericon.png";
		picName="group_icon.jpg";
		imageView=mPhotoImageView;
	}
}
