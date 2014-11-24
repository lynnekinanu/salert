package com.lynn.salert;

import com.example.salert.R;
import com.lynn.customadapters.ViewPagerAdapter;

import android.app.ActionBar;
import android.app.ActionBar.TabListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

import android.support.v7.app.ActionBar.Tab;

public class SigninSignupActivity extends FragmentActivity {
	ActionBar mActionBar;
	ViewPager mPager;
	Tab tab;

	@Override
	protected void onCreate(Bundle savedInstance) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstance);
		setContentView(R.layout.pager);

		mActionBar = getActionBar();
		mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		// locate viewpager
		mPager = (ViewPager) findViewById(R.id.pager);

		// activate fragment manager
		FragmentManager fm = getSupportFragmentManager();
		// capture page swipes
		ViewPager.SimpleOnPageChangeListener ViewPageListerner = new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				super.onPageSelected(position);
				mActionBar.setSelectedNavigationItem(position);
			}
		};
		mPager.setOnPageChangeListener(ViewPageListerner);
		// setting the adapter
		ViewPagerAdapter viewPageAdapter = new ViewPagerAdapter(fm);
		viewPageAdapter.fragment1 = new SigninFragment();
		viewPageAdapter.fragment = new SignupFragment();

		// set the view pager adapter into viewPager
		mPager.setAdapter(viewPageAdapter);
		mPager.setAdapter(viewPageAdapter);
		ActionBar.TabListener tabListener = new TabListener() {
			
			@Override
			public void onTabUnselected(android.app.ActionBar.Tab arg0,
					android.app.FragmentTransaction arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onTabSelected(android.app.ActionBar.Tab tab,
					android.app.FragmentTransaction arg1) {
				// TODO Auto-generated method stub
				mPager.setCurrentItem(tab.getPosition());
			}
			
			@Override
			public void onTabReselected(android.app.ActionBar.Tab arg0,
					android.app.FragmentTransaction arg1) {
				// TODO Auto-generated method stub
				
			}
		};
		String[] tabs = { "Sign In", "Sign Up" };
		for (String tabTitle : tabs) {
			ActionBar.Tab tab = mActionBar.newTab().setText(tabTitle)
					.setTabListener(tabListener);
			mActionBar.addTab(tab);
		}

	}
}
