package com.lynn.customadapters;

import java.util.ArrayList;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.salert.R;
import com.lynn.customadapters.CustomerCustomAdapter.ViewHolder;
import com.lynn.models.Customer_Model;
import com.lynn.models.Schedule_Model;

public class ScheduleCustomeListAdapter extends BaseAdapter {
	ArrayList<Schedule_Model> data;
	Context context;

	public ScheduleCustomeListAdapter(Context context,
			ArrayList<Schedule_Model> data_) {
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
			v = layoutInflater.inflate(R.layout.custom_schedule, null);
			holder.customer_names = (TextView) v.findViewById(R.id.tvScheduleCustomername);
			holder.dates = (TextView) v.findViewById(R.id.tvScheduleDate);
			holder.time= (TextView) v.findViewById(R.id.tvScheduleTime);
			holder.services_remarks = (TextView) v.findViewById(R.id.tvScheduleRemarks); 
			v.setTag(holder);

		} else {
			holder = (ViewHolder) v.getTag();
		}
		holder.customer_names.setText(data.get(position).getScheduleCustomerName());
		holder.time.setText(data.get(position).getScheduleTime());
		holder.dates.setText(data.get(position).getScheduleDate());
		String message=Html.fromHtml("<b><h1>"+data.get(position).getScheduleServiceName()+"<h1></b><p>"+data.get(position).getScheduleRemarks()+"</p>").toString();
		holder.services_remarks.setText(message);
		 
		return v;

	}

	// holder for the differenct views in the layour
	static class ViewHolder {
		
		TextView customer_names, time, dates, services_remarks;

	}
}
