package com.origwood.liuxue.ui;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.os.Bundle;
import android.widget.ListView;

import com.origwood.liuxue.R;
import com.origwood.liuxue.adapter.GroupAdapter;

@ContentView(R.layout.activity_recommendation)
public class Recommendation extends Base {
	@InjectView(R.id.list)
	ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		listView.setAdapter(new GroupAdapter(this));
	}
}
