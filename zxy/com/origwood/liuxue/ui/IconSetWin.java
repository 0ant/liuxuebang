package com.origwood.liuxue.ui;

import com.origwood.liuxue.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;

public class IconSetWin extends PopupWindow {
	private Button btn_take_photo, btn_pick_photo, btn_cancel;
	private View mMenuView;

	public IconSetWin(Activity context, OnClickListener itemsOnClick) {
		super(context);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mMenuView = inflater.inflate(R.layout.pi_popupwindow, null);
		btn_take_photo = (Button) mMenuView.findViewById(R.id.pi_takepic);
		btn_pick_photo = (Button) mMenuView.findViewById(R.id.pi_selectpic);
		btn_cancel = (Button) mMenuView.findViewById(R.id.pi_cancle);
		// ȡ����ť
		btn_cancel.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// ���ٵ�����
				dismiss();
			}
		});
		// ���ð�ť����
		btn_pick_photo.setOnClickListener(itemsOnClick);
		btn_take_photo.setOnClickListener(itemsOnClick);
		// ����SelectPicPopupWindow��View
		this.setContentView(mMenuView);
		// ����SelectPicPopupWindow��������Ŀ�
		this.setWidth(LayoutParams.MATCH_PARENT);
		// ����SelectPicPopupWindow��������ĸ�
		this.setHeight(LayoutParams.WRAP_CONTENT);
		// ����SelectPicPopupWindow��������ɵ��
		this.setFocusable(true);
		// ����SelectPicPopupWindow�������嶯��Ч��
		// this.setAnimationStyle(R.style.AnimBottom);
		// ʵ����һ��ColorDrawable��ɫΪ��͸��
		ColorDrawable dw = new ColorDrawable(0xb0000000);
		// ����SelectPicPopupWindow��������ı���
		this.setBackgroundDrawable(dw);
		// mMenuView���OnTouchListener�����жϻ�ȡ����λ�������ѡ������������ٵ�����
		mMenuView.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {

				int height = mMenuView.findViewById(R.id.pi_popwin).getTop();
				int y = (int) event.getY();
				if (event.getAction() == MotionEvent.ACTION_UP) {
					if (y < height) {
						dismiss();
					}
				}
				return true;
			}
		});
	}
}
