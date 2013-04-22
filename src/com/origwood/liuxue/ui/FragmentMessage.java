package com.origwood.liuxue.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;
import com.origwood.liuxue.R;
import com.origwood.liuxue.adapter.ReplyAdapter;

public class FragmentMessage extends SherlockFragment {

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		setUserVisibleHint(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.fragment_message, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		ListView listView = (ListView) view.findViewById(R.id.list);
		listView.setAdapter(new ReplyAdapter(getActivity()));
		super.onViewCreated(view, savedInstanceState);
	}

}
