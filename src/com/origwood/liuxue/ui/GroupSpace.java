package com.origwood.liuxue.ui;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;

import com.origwood.liuxue.R;
import com.origwood.liuxue.adapter.TopicAdapter;
import com.origwood.liuxue.bean.Result;
import com.origwood.liuxue.bean.ResultGroup;
import com.origwood.liuxue.common.UIHelper;
import com.origwood.liuxue.service.AbsAppServiceOnFinished;

@ContentView(R.layout.activity_group)
public class GroupSpace extends Base implements OnClickListener,
		OnItemClickListener {
	@InjectView(R.id.list)
	ListView mListView;
	@InjectView(R.id.pub_topic)
	ImageButton ibPub;
	private ResultGroup mResultGroup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		mListView.setAdapter(new TopicAdapter(this));
		mListView.setOnItemClickListener(this);
		ibPub.setOnClickListener(this);
		service.getGroupById(getIntent().getStringExtra("groupId"), true,
				new AbsAppServiceOnFinished() {

					@Override
					public void onSuccess(Object object) {
						mResultGroup = (ResultGroup) object;

					}

					@Override
					public void onFailed(Result result) {
						showFaildedMessage(result, getApplicationContext());
					}

				});
	}

	private void getData() {

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.pub_topic:
			UIHelper.toPublishTopic(GroupSpace.this, null);
			break;

		default:
			break;
		}

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		UIHelper.toTopicDetail(GroupSpace.this, null);
	}
}