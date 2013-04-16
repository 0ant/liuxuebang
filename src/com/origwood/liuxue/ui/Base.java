package com.origwood.liuxue.ui;

import roboguice.activity.RoboActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.google.inject.Inject;
import com.origwood.liuxue.R;
import com.origwood.liuxue.service.AppService;

public class Base extends RoboActivity {
	@Inject
	protected AppService service;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(android.R.style.Theme_NoTitleBar);
		super.onCreate(savedInstanceState);

	}

	@Override
	protected void onStart() {

		super.onStart();
		if (findViewById(R.id.back) != null) {

			findViewById(R.id.back).setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					finish();
				}
			});
		}
		if (findViewById(R.id.title) != null) {
			if (getIntent().getStringExtra("title") != null) {
				TextView tvTitle = (TextView) findViewById(R.id.title);
				tvTitle.setText(getIntent().getStringExtra("title"));
			}
		}

	}
}
