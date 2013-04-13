package com.origwood.liuxue.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.origwood.liuxue.R;

public class GroupAdapter extends BaseAdapter {
	private Context mContext;
	private LayoutInflater mLayoutInflater;

	public GroupAdapter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GroupAdapter(Context mContext) {
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
			convertView = mLayoutInflater.inflate(R.layout.item_group, null);
			v.imgIcon = (ImageView) convertView.findViewById(R.id.icon);
			v.imgHot = (ImageView) convertView.findViewById(R.id.hot);
			v.imgJionOrOut = (ImageView) convertView
					.findViewById(R.id.jionorout);
			v.imgOfficer = (ImageView) convertView.findViewById(R.id.officer);
			v.tvMemnum = (TextView) convertView.findViewById(R.id.memnum);
			v.tvMessage = (TextView) convertView.findViewById(R.id.message);
			v.tvName = (TextView) convertView.findViewById(R.id.name);

		} else {
			v = (ViewHolder) convertView.getTag();
		}
		return convertView;
	}

	class ViewHolder {
		ImageView imgIcon;// 帮logo
		TextView tvName;// 帮名字
		TextView tvMessage;// 最后发帖标题
		TextView tvMemnum;// 成员数
		ImageView imgOfficer;// 官方帮标记
		ImageView imgHot;// 热门帮标记
		ImageView imgJionOrOut;// 加入或者退出
	}

}
