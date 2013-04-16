package com.origwood.liuxue.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.origwood.liuxue.R;

public class TopicAdapter extends BaseAdapter {
	private Context mContext;
	private LayoutInflater mLayoutInflater;

	public TopicAdapter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TopicAdapter(Context mContext) {
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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder v = null;
		if (convertView == null) {
			v = new ViewHolder();
			convertView = mLayoutInflater.inflate(R.layout.item_topic, null);
			v.imgIcon = (ImageView) convertView.findViewById(R.id.icon);
			v.imgPic = (ImageView) convertView.findViewById(R.id.pic);
			v.imgVoice = (ImageView) convertView.findViewById(R.id.voice);
			v.tvTitle = (TextView) convertView.findViewById(R.id.title);

			v.tvMessage = (TextView) convertView.findViewById(R.id.message);
			v.tvReply = (TextView) convertView.findViewById(R.id.replynum);

		} else {
			v = (ViewHolder) convertView.getTag();
		}
		return convertView;
	}

	class ViewHolder {
		ImageView imgIcon;// 发帖人logo
		ImageView imgVoice; // 是否有语音
		ImageView imgPic; // 是否有图片
		TextView tvTitle;// 帖子标题
		TextView tvMessage;// 最后回复内容
		TextView tvReply;// 回复数目
		TextView tvDate;// 最后回复的时间

	}
}
