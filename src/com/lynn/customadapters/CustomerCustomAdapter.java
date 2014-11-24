package com.lynn.customadapters;

import java.util.ArrayList;

import com.example.salert.R;
import com.lynn.models.Customer_Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomerCustomAdapter extends BaseAdapter {
	ArrayList<Customer_Model> data;
	Context context;

	public CustomerCustomAdapter(Context context,
			ArrayList<Customer_Model> data_) {
		this.context = context;
		data = data_;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(final int position, View view, ViewGroup container) {
		View v = view;
		ViewHolder holder;
		if (v == null) {
			holder = new ViewHolder();
			LayoutInflater layoutInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = layoutInflater.inflate(R.layout.custom_customer, null);
			holder.names = (TextView) v.findViewById(R.id.tvCutomerNames);
			holder.email = (TextView) v.findViewById(R.id.tvCutomerEmail);
			holder.mobile = (TextView) v.findViewById(R.id.tvCutomerMobile);
			holder.twitter = (TextView) v.findViewById(R.id.tvCutomerTwitter);
			holder.facebook = (TextView) v.findViewById(R.id.tvCutomerFacebook);
			v.setTag(holder);

		} else {
			holder = (ViewHolder) v.getTag();
		}
		holder.names.setText(data.get(position).getCustomerFullnames());
		holder.mobile.setText(data.get(position).getCustomerMobile());
		holder.email.setText(data.get(position).getCustomerEmail());
		holder.twitter.setText(data.get(position).getCustomerTwitter());
		holder.facebook.setText(data.get(position).getCustomerFacebook());
	  
		return v;

	}

	// holder for the differenct views in the layour
	static class ViewHolder {
		TextView names, twitter, facebook, mobile, email;

	}
}
