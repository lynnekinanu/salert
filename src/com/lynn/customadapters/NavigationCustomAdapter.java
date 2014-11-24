package com.lynn.customadapters;

import java.util.ArrayList;

import com.example.salert.R;
import com.lynn.models.NavigationDrawer;

 

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class NavigationCustomAdapter extends BaseAdapter {
	private ArrayList<NavigationDrawer> list;
	private Context context;

	public NavigationCustomAdapter(Context c,
			ArrayList<NavigationDrawer> navList) {

		this.list = navList;
		this.context = c;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return this.list.get(position);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup viewGroup) {
		// TODO Auto-generated method stub
	//	Log.e("Custom Adapter", "here");
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.navigation_custom_layout,
					null);

		}

		ImageView image = (ImageView) convertView
				.findViewById(R.id.imgListImage);
		TextView navTitle = (TextView) convertView
				.findViewById(R.id.txListMenuName);

		image.setScaleType(ImageView.ScaleType.CENTER_CROP);
		image.setImageResource(list.get(position).getIcon());
		// navTitle.setCompoundDrawablesWithIntrinsicBounds(list.get(position).getIcon(),
		// 0, 0, 0);
		navTitle.setText(list.get(position).getTitle());

		return convertView;

	}

}
