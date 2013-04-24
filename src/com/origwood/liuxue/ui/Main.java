package com.origwood.liuxue.ui;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.origwood.liuxue.R;

public class Main extends SherlockFragmentActivity {
	private TabHost mTabHost;
	private ViewPager mViewPager;
	private TabsAdapter mTabsAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// 设置主题，在每个activity创建之前需要完成这个操作
		setTheme(R.style.Theme_Sherlock_Light_NoActionBar);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mTabHost = (TabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup();
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mTabsAdapter = new TabsAdapter(this, mTabHost, mViewPager);
		for (int i = 0, length = TabsValue.mTextviewArray.length; i < length; i++) {
			TabSpec tabSpec = mTabHost.newTabSpec("合作社").setIndicator(
					getTabItemView(i));

			mTabsAdapter.addTab(tabSpec, TabsValue.mTabClassArray[i], null);
			mTabHost.getTabWidget().getChildAt(i)
					.setBackgroundResource(R.drawable.background_main_tabitem);
		}
		if (savedInstanceState != null) {
			mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab"));
		}
	}

	private View getTabItemView(int index) {

		View view = LayoutInflater.from(this).inflate(
				R.layout.item_main_tabitem, null);

		TextView textView = (TextView) view.findViewById(R.id.textview);
		ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
		textView.setText(TabsValue.mTextviewArray[index]);
		imageView.setImageResource(TabsValue.mImageViewArray[index]);
		return view;
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {

		outState.putString("tab", mTabHost.getCurrentTabTag());
		super.onSaveInstanceState(outState);
	}

	public static class TabsValue {
		public static Class mTabClassArray[] = { FragmentMyGroup.class,
				FragmentMessage.class, FragmentPublish.class,
				FragmentSelect.class, FragmentMore.class };
		public static final String mTextviewArray[] = { "我的帮", "动态", "发话题",
				"精华", "更多" };
		public static final int mImageViewArray[] = {
				R.drawable.background_main_tabitem_mygroup,
				R.drawable.background_main_tabitem_message,
				R.drawable.background_main_tabitem_publish,
				R.drawable.background_main_tabitem_select,
				R.drawable.background_main_tabitem_more };
	}

	/**
	 * Tabs适配器
	 * 
	 * @author wanggang
	 * @date： 2013-4-5 email: 315331371@qq.com
	 */
	public class TabsAdapter extends FragmentPagerAdapter implements
			TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener {
		private final Context mContext;
		private final TabHost mTabHost;
		private final ViewPager mViewPager;
		private final ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();

		final class TabInfo {
			private final String tag;
			private final Class<?> clss;
			private final Bundle args;

			TabInfo(String _tag, Class<?> _class, Bundle _args) {
				tag = _tag;
				clss = _class;
				args = _args;
			}
		}

		class DummyTabFactory implements TabHost.TabContentFactory {
			private final Context mContext;

			public DummyTabFactory(Context context) {
				mContext = context;
			}

			@Override
			public View createTabContent(String tag) {
				View v = new View(mContext);
				v.setMinimumWidth(0);
				v.setMinimumHeight(0);
				return v;
			}
		}

		public TabsAdapter(FragmentActivity activity, TabHost tabHost,
				ViewPager pager) {
			super(activity.getSupportFragmentManager());
			mContext = activity;
			mTabHost = tabHost;
			mViewPager = pager;
			mTabHost.setOnTabChangedListener(this);
			mViewPager.setAdapter(this);
			mViewPager.setOnPageChangeListener(this);
		}

		public void addTab(TabHost.TabSpec tabSpec, Class<?> clss, Bundle args) {
			tabSpec.setContent(new DummyTabFactory(mContext));
			String tag = tabSpec.getTag();

			TabInfo info = new TabInfo(tag, clss, args);
			mTabs.add(info);
			mTabHost.addTab(tabSpec);
			notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return mTabs.size();
		}

		@Override
		public Fragment getItem(int position) {
			TabInfo info = mTabs.get(position);

			return Fragment.instantiate(mContext, info.clss.getName(),
					info.args);
		}

		@Override
		public void onTabChanged(String tabId) {
			int position = mTabHost.getCurrentTab();
			mViewPager.setCurrentItem(position);
		}

		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {
		}

		@Override
		public void onPageSelected(int position) {
			TabWidget widget = mTabHost.getTabWidget();
			int oldFocusability = widget.getDescendantFocusability();
			widget.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
			mTabHost.setCurrentTab(position);
			widget.setDescendantFocusability(oldFocusability);
		}

		@Override
		public void onPageScrollStateChanged(int state) {
		}
	}

}
