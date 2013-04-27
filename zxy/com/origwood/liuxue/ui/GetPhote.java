package com.origwood.liuxue.ui;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.origwood.liuxue.R;
import com.origwood.liuxue.util.BitmapUtil;
import com.origwood.liuxue.util.FileUtils;
import com.origwood.liuxue.util.ImageTools;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public abstract class GetPhote extends Base {
	private static final int PICK_PHOTO = 0;
	private static final int TAKE_PHOTO = 1;
	private String saveDir = Environment.getExternalStorageDirectory()
			.getPath() + "/LiuxueBang/pictures";
	private IconSetWin selectWindow;
	private Bitmap photo;
	// 子类要用到到参数

	private File file;
	private boolean photoFlag = false;// 是否选择或拍照
	protected ImageView imageView;

	protected String folderName, defaultPicName,picName;

	/**
	 * 设置folderName,defaultPicName，imageView,picName<br>
	 * defaultPicName对应assets文件夹下默认上传到文件<br>
	 * imageView 图片显示到组件<br>
	 * picName 拍摄照片保存到名字
	 */
	protected abstract void setConf();
	/**
	 * 返回图片文件
	 * @return
	 */
	protected File getImageFile(){
		if(file==null||!photoFlag){
			setDefaultImage();
		}
//		File 
		photoFlag=false;
		return file;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setConf();

		File dic = new File(saveDir + File.separator + folderName);
		if (!dic.exists()) {
			dic.mkdirs();// 创建目录
		}

		file = null;
	}

	/**
	 * 设置群图片
	 * 
	 * @param v
	 */
	public void getPhoto() {
		// Toast.makeText(this, "frame", Toast.LENGTH_SHORT).show();
		selectWindow = new IconSetWin(this, itemsOnClick);
		selectWindow.showAtLocation(imageView, Gravity.BOTTOM
				| Gravity.CENTER_HORIZONTAL, 0, 0);
	}

	/**
	 * 照片显示的imageiew
	 * 
	 * @return
	 */
	public void setDefaultImage() {
		try {
			InputStream is = getAssets().open(defaultPicName);
			File file = new File(saveDir + File.separator + folderName,
					defaultPicName);
			
			if (file.exists()) {
				this.file = file;
				return;
			}
			
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				Toast.makeText(GetPhote.this, "照片创建失败!", Toast.LENGTH_LONG)
						.show();
				return;
			}
			FileOutputStream out = new FileOutputStream(file);
			byte[] buffer = new byte[1024];
			int count = -1;
			while ((count = is.read(buffer, 0, 1024)) != -1) {
				out.write(buffer, 0, count);
			}
			this.file = file;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode != RESULT_OK)
			return;
		photoFlag = true;
		if (requestCode == PICK_PHOTO) {
			try {
				Uri selectedImage = data.getData();
				String[] filePathColumn = { MediaStore.Images.Media.DATA };

				Cursor cursor = getContentResolver().query(selectedImage,
						filePathColumn, null, null, null);
				cursor.moveToFirst();

				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
				String picturePath = cursor.getString(columnIndex);
				file = new File(picturePath);
				cursor.close();
				imageView.setImageBitmap(parseBitmap(BitmapFactory.decodeFile(picturePath)));
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (requestCode == TAKE_PHOTO) {
			if (file != null && file.exists()) {
				imageView.setImageBitmap(parseBitmap(BitmapFactory.decodeFile(file.getPath())));
			}
		}
	}
	public Bitmap parseBitmap(Bitmap bitmap){
		return BitmapUtil.getRoundedCornerBitmap(BitmapUtil.zoomBitmap(bitmap, 100, 100));
	}
	private OnClickListener itemsOnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			selectWindow.dismiss();
			switch (v.getId()) {
			case R.id.pi_selectpic:// 选择照片
				Intent getImage = new Intent(Intent.ACTION_GET_CONTENT);
				getImage.addCategory(Intent.CATEGORY_OPENABLE);
				getImage.setType("image/*");
				startActivityForResult(getImage, PICK_PHOTO);
				break;
			case R.id.pi_takepic:// 拍摄照片
				destoryImage();
				String state = Environment.getExternalStorageState();
				if (state.equals(Environment.MEDIA_MOUNTED)) {
					file = new File(saveDir+File.separator+folderName, picName);
					file.delete();
					if (!file.exists()) {
						try {
							file.createNewFile();
						} catch (IOException e) {
							e.printStackTrace();
							Toast.makeText(GetPhote.this, "照片创建失败!",
									Toast.LENGTH_LONG).show();
							return;
						}
					}
					Intent intent = new Intent(
							"android.media.action.IMAGE_CAPTURE");
					intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
					startActivityForResult(intent, 1);
				} else {
					Toast.makeText(GetPhote.this, "sdcard无效或没有插入!",
							Toast.LENGTH_SHORT).show();
				}
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
	protected void onDestroy() {
		destoryImage();
		super.onDestroy();
	}

	private void destoryImage() {
		if (photo != null) {
			photo.recycle();
			photo = null;
		}
	}
}
