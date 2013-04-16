package com.origwood.liuxue.ui;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.origwood.liuxue.R;
import com.origwood.liuxue.adapter.TopicAdapter;

@ContentView(R.layout.activity_group)
public class GroupSpace extends Base implements OnClickListener {
	@InjectView(R.id.list)
	ListView mListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		mListView.setAdapter(new TopicAdapter(this));
	}

	@Override
	public void onClick(View v) {
		Intent mIntent = null;
		switch (v.getId()) {
		case R.id.layout_fans:

			break;

		default:
			break;
		}

	}
}