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
		ImageView imgIcon;// �û�logo
		TextView tvNickName;// �û�����
		TextView tvMessage;// ���������
		TextView tvJoin;// �������
		TextView tvAttion;// ��ע��Ŀ
		TextView tvFans;// ��˿��Ŀ
		TextView tvCollect;// �ղ�������Ŀ
		ImageView imgLevel;// �ȼ�ͼ��
		TextView tvLevel;// �û��ȼ�

	}
}
