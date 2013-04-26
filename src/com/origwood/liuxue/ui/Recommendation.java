package com.origwood.liuxue.ui;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.widget.ListView;

import com.origwood.liuxue.R;
import com.origwood.liuxue.adapter.GroupAdapter;
import com.origwood.liuxue.bean.ResultGroupList;
import com.origwood.liuxue.service.AbsAppServiceOnFinished;

@ContentView(R.layout.activity_recommendation)
public class Recommendation extends Base {
	@InjectView(R.id.list)
	ListView listView;
	private int pageIndex;
	private GroupAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		adapter = new GroupAdapter(this);
		listView.setAdapter(adapter);
		getData();
	}

	private void getData() {
		service.setContext(this);
		service.getAllGroup(new AbsAppServiceOnFinished() {
			@Override
			public void onSuccess(Object object) {
				adapter.setResultGroupList((ResultGroupList) object);
			}
		}, pageIndex, true);
	}
}
