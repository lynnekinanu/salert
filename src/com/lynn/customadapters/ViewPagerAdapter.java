package com.lynn.customadapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter; 

public class ViewPagerAdapter extends FragmentPagerAdapter {
	final int PAGE_COUNT = 2;
public Fragment fragment1,fragment;
	public ViewPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int position) {

		switch (position) {
		case 0:
		 
			return fragment1;
		case 1:
		 
			return fragment;

		default:
			return null;
		}

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return PAGE_COUNT;
	}

}
