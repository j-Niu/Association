package com.future.baselib.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import com.future.baselib.R;
import com.future.baselib.activity.BaseFragment;
import com.future.baselib.view.PagerSlidingTabStrip;

import java.util.ArrayList;


/**
 * Fragment滑动切换适配器
 * @author chenyu
 */
public class ViewPageFragmentAdapter extends FragmentStatePagerAdapter implements ViewPager.OnPageChangeListener {

	private final Context mContext;
	protected PagerSlidingTabStrip mPagerStrip;
	private final ViewPager mViewPager;
	private final ArrayList<ViewPageInfo> mTabs = new ArrayList<ViewPageInfo>();

	static class DummyTabFactory implements TabHost.TabContentFactory {
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

	public ViewPageFragmentAdapter(FragmentManager fm, PagerSlidingTabStrip pageStrip, ViewPager pager) {
		super(fm);
		mContext = pager.getContext();
		mPagerStrip = pageStrip;
		mViewPager = pager;
		mViewPager.setAdapter(this);
		mViewPager.addOnPageChangeListener(this);
		mPagerStrip.setViewPager(mViewPager);
	}

	public ViewPageFragmentAdapter(FragmentManager fm, ViewPager pager) {
		super(fm);
		mContext = pager.getContext();
		mViewPager = pager;
		mViewPager.setAdapter(this);
		mViewPager.addOnPageChangeListener(this);
	}
	

	public void addTab(String title, String tag, Class<?> clss, Bundle args) {
		ViewPageInfo info = new ViewPageInfo(title, tag, clss, args);
		mTabs.add(info);
	}

	public void addTab(String title, String tag, BaseFragment fragment, Bundle args){
		ViewPageInfo info = new ViewPageInfo(title, tag, fragment, args);
		mTabs.add(info);
	}

	@Override
	public void notifyDataSetChanged() {
		super.notifyDataSetChanged();
		for (ViewPageInfo viewPageInfo : mTabs) {
			TextView v = (TextView) LayoutInflater.from(mContext).inflate(R.layout.sliding_tab_item, null);
			v.setText(viewPageInfo.title);
			if (mPagerStrip!=null)mPagerStrip.addTab(v);
		}
	}

	@Override
	public int getCount() {
		return mTabs.size();
	}

	@Override
	public Fragment getItem(int position) {
		ViewPageInfo info = mTabs.get(position);
		if(info.fragment != null){
			return info.fragment;
		}
		return Fragment.instantiate(mContext, info.clss.getName(), info.args);
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return mTabs.get(position).title;
	}

	@Override
	public void onPageScrollStateChanged(int state) {
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
	}

	@Override
	public void onPageSelected(int position) {
		
		
	}
}