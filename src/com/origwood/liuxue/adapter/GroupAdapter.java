package com.origwood.liuxue.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.origwood.liuxue.AppContext;
import com.origwood.liuxue.R;
import com.origwood.liuxue.bean.ResultGroupList;
import com.origwood.liuxue.common.UIHelper;
import com.origwood.liuxue.util.Loger;
import com.origwood.liuxue.util.NetImageTools;

public class GroupAdapter extends BaseAdapter {
	private Context mContext;
	private LayoutInflater mLayoutInflater;
	private ResultGroupList resultGroupList;

	public ResultGroupList getResultGroupList() {
		return resultGroupList;
	}

	public void setResultGroupList(ResultGroupList resultGroupList) {
		this.resultGroupList = resultGroupList;
		this.notifyDataSetChanged();
	}

	public GroupAdapter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GroupAdapter(Context mContext) {
		super();
		this.mContext = mContext;
		mLayoutInflater = LayoutInflater.from(mContext);
		resultGroupList = new ResultGroupList();
	}

	public Context getmContext() {
		return mContext;
	}

	@Override
	public int getCount() {

		return resultGroupList.getItems().size();
	}

	@Override
	public Object getItem(int position) {

		return resultGroupList.getItems().get(position);
	}

	@Override
	public long getItemId(int position) {

		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder v = null;
		if (convertView == null) {
			v = new ViewHolder();
			convertView = mLayoutInflater.inflate(R.layout.item_group, null);
			convertView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					UIHelper.toGroup(mContext,
							resultGroupList.getItems().get(position).getId());

				}
			});
			v.imgIcon = (ImageView) convertView.findViewById(R.id.icon);
			v.imgHot = (ImageView) convertView.findViewById(R.id.hot);
			v.imgJionOrOut = (ImageView) convertView
					.findViewById(R.id.jionorout);
			v.imgOfficer = (ImageView) convertView.findViewById(R.id.officer);
			v.tvMemnum = (TextView) convertView.findViewById(R.id.memnum);
			v.tvMessage = (TextView) convertView.findViewById(R.id.message);
			v.tvName = (TextView) convertView.findViewById(R.id.nikename);
			convertView.setTag(v);
		} else {
			v = (ViewHolder) convertView.getTag();
		}
		if (AppContext.DEBUG) {
			Loger.i("resultGroupList size:" + resultGroupList.getItems().size());
			Loger.i("resultGroupList:"
					+ resultGroupList.getItems().get(position));
		}

		v.tvName.setText(resultGroupList.getItems().get(position).getName());
		NetImageTools.getInstance().setImage(v.imgIcon,
				R.drawable.ic_group_pic,
				resultGroupList.getItems().get(position).getImg());
		v.imgHot.setVisibility(resultGroupList.getItems().get(position)
				.getIsHot() == 1 ? View.VISIBLE : View.GONE);
		v.imgOfficer.setVisibility(resultGroupList.getItems().get(position)
				.getIsOfficial() == 1 ? View.VISIBLE : View.GONE);
		v.tvMemnum.setText(resultGroupList.getItems().get(position)
				.getUserAmount()
				+ "");
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
