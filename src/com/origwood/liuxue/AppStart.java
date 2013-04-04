package com.origwood.liuxue;

import android.os.Bundle;
import android.widget.Button;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.origwood.liuxue.ui.Base;

/**
 * 应用入口
 * 
 * @author wanggang
 * @date： 2013-3-31 email: 315331371@qq.com
 */
public class AppStart extends Base {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 0, 0, R.string.menu_settings)
				.setActionView(new Button(this))
				.setShowAsAction(
						MenuItem.SHOW_AS_ACTION_IF_ROOM
								| MenuItem.SHOW_AS_ACTION_WITH_TEXT);

		return true;
	}
}
