package com.origwood.liuxue.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.origwood.liuxue.R;

public class UserAdapter extends BaseAdapter {
	private Context mContext;
	private LayoutInflater mLayoutInflater;

	public UserAdapter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserAdapter(Context mContext) {
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
			convertView = mLayoutInflater.inflate(R.layout.item_user, null);
			v.imgIcon = (ImageView) convertView.findViewById(R.id.icon);
			v.imgLevel = (ImageView) convertView.findViewById(R.id.imgLevel);
			v.tvLevel = (TextView) convertView.findViewById(R.id.level);

			v.tvMessage = (TextView) convertView.findViewById(R.id.message);
			v.tvNickName = (TextView) convertView.findViewById(R.id.nikename);
			v.tvAttion = (TextView) convertView.findViewById(R.id.attention);
			v.tvCollect = (TextView) convertView.findViewById(R.id.collect);
			v.tvJoin = (TextView) convertView.findViewById(R.id.join);
			v.tvFans = (TextView) convertView.findViewById(R.id.fans);

		} else {
			v = (ViewHolder) convertView.getTag();
		}
		return convertView;
	}

	class ViewHolder {
		ImageView imgIcon;// 用户logo
		TextView tvNickName;// 用户名字
		TextView tvMessage;// 最后发帖标题
		TextView tvJoin;// 加入帮数
		TextView tvAttion;// 关注数目
		TextView tvFans;// 粉丝数目
		TextView tvCollect;// 收藏帖子数目
		ImageView imgLevel;// 等级图标
		TextView tvLevel;// 用户等级

	}
}
