package com.origwood.liuxue.ui;

import android.os.Bundle;

import com.actionbarsherlock.app.SherlockFragment;

public class FragmentMyGroup extends SherlockFragment {
	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		setUserVisibleHint(true);
	}
}
