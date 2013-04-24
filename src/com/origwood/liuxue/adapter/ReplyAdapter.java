package com.origwood.liuxue.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.origwood.liuxue.R;

public class ReplyAdapter extends BaseAdapter {
	private Context mContext;
	private LayoutInflater mLayoutInflater;

	public ReplyAdapter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReplyAdapter(Context mContext) {
		super();
		this.mContext = mContext;
		mLayoutInflater = LayoutInflater.from(mContext);
	}

	@Override
	public int getCount() {

		return 10;
	}

	@Override
	public Object getItem(int position) {

		return null;
	}

	@Override
	public long getItemId(int position) {

		return 0;
	}

	public Context getmContext() {
		return mContext;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder v = null;
		if (convertView == null) {
			v = new ViewHolder();
			convertView = mLayoutInflater.inflate(R.layout.item_topic_reply,
					null);
			v.imgIcon = (ImageView) convertView.findViewById(R.id.usericon);
			v.ibPic = (ImageButton) convertView.findViewById(R.id.pic);
			v.ibVoice = (ImageButton) convertView.findViewById(R.id.voice);
			v.tvContent = (TextView) convertView.findViewById(R.id.title);
			v.tvVoiceTime = (TextView) convertView
					.findViewById(R.id.voice_time);
			v.tvDate = (TextView) convertView.findViewById(R.id.date);
			v.tvUserStatus = (TextView) convertView
					.findViewById(R.id.userstatus);
			v.imgUserType = (ImageView) convertView.findViewById(R.id.usertype);

		} else {
			v = (ViewHolder) convertView.getTag();
		}
		return convertView;
	}

	class ViewHolder {
		ImageView imgIcon;// ������logo
		ImageButton ibVoice; // ����
		ImageButton ibPic; // ͼƬ����
		TextView tvVoiceTime;// ����ʱ��
		TextView tvContent;// �ظ�����
		TextView tvUserStatus;// �û�״̬,exp:"Ӣ����ѧ��|׼����ѧ��"
		TextView tvDate;// �ظ���ʱ��
		ImageView imgUserType;

	}
}
