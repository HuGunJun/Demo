package com.hgj.demo.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

public class FragmntAdapter extends FragmentPagerAdapter {
	private List<Fragment> list_frag;
	private FragmentManager fm;
	public FragmntAdapter(FragmentManager fm, List<Fragment> list_frag) {
		super(fm);
		this.list_frag = list_frag;
		this.fm = fm;
	}

	@Override
	public Fragment getItem(int arg0) {
		Fragment fragment = list_frag.get(arg0);
		return fragment;
	}

	@Override
	public int getCount() {
		return list_frag.size();
	}

	@Override
	public Fragment instantiateItem(ViewGroup container, int position) {
		Fragment fragment = (Fragment) super.instantiateItem(container,
				position);
		fm.beginTransaction().show(fragment).commit();
		return fragment;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		Fragment fragment = list_frag.get(position);
		fm.beginTransaction().hide(fragment).commit();
	}
}
