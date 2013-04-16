package com.origwood.liuxue.ui;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.widget.ListView;

import com.origwood.liuxue.R;
import com.origwood.liuxue.adapter.UserAdapter;

@ContentView(R.layout.activity_myfans)
public class MyFans extends Base {
	@InjectView(R.id.list)
	ListView mListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mListView.setAdapter(new UserAdapter(this));
	}
}