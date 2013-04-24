package com.origwood.liuxue.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;
import com.origwood.liuxue.R;
import com.origwood.liuxue.adapter.GroupAdapter;
import com.origwood.liuxue.common.UIHelper;

public class FragmentMyGroup extends SherlockFragment implements
		OnClickListener {
	private ImageButton ibSelect, ibUserSpace;

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		setUserVisibleHint(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_mygroup, container,
				false);
		ListView listView = (ListView) view.findViewById(R.id.list);
		listView.setAdapter(new GroupAdapter(getActivity()));
		ibSelect = (ImageButton) view.findViewById(R.id.recommendation);

		ibUserSpace = (ImageButton) view.findViewById(R.id.user);
		ibSelect.setOnClickListener(this);
		ibUserSpace.setOnClickListener(this);
		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		super.onViewCreated(view, savedInstanceState);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.recommendation:
			UIHelper.toRecommendation(getActivity());
			break;
		case R.id.user:
			UIHelper.toUserSpace(getActivity(), null);
			break;
		default:
			break;
		}

	}
}
