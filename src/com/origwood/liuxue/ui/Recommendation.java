package com.origwood.liuxue.ui;

import android.os.Bundle;
import android.widget.ListView;

import com.origwood.liuxue.R;
import com.origwood.liuxue.adapter.GroupAdapter;

public class Recommendation extends Base {
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recommendation);
		listView = (ListView) findViewById(R.id.list);
		listView.setAdapter(new GroupAdapter(this));
	}
}
