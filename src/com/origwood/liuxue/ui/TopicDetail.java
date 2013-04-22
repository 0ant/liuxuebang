package com.origwood.liuxue.ui;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.widget.ListView;

import com.origwood.liuxue.R;
import com.origwood.liuxue.adapter.ReplyAdapter;

@ContentView(R.layout.activity_topic_detail)
public class TopicDetail extends Base {
	@InjectView(R.id.list)
	ListView mListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mListView.setAdapter(new ReplyAdapter(this));

	}
}