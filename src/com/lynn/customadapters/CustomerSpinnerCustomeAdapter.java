package com.lynn.customadapters;

import java.util.ArrayList;
import java.util.List;

import com.example.salert.R;
import com.lynn.models.Customer_Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

 

public class CustomerSpinnerCustomeAdapter extends ArrayAdapter<Customer_Model> {
	ArrayList<Customer_Model>data;
	Context context;

		public CustomerSpinnerCustomeAdapter(Context context, int resource,
				List<Customer_Model> objects) {
			super(context, resource, objects);
			this.context=context;
			this.data=(ArrayList<Customer_Model>)objects;
		 
		}


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size() ;
	}
	 @Override
		public Customer_Model getItem(int position) {
			// TODO Auto-generated method stub
			return data.get(position);
		}

	 @Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}
	 @Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			return getCustomView(position, convertView, parent);
		}
	 @Override
		public View getDropDownView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			return getCustomView(position, convertView, parent);
		}
	 public View getCustomView(int position, View convertView, ViewGroup parent) {
	 
	     LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	     View row=inflater.inflate(R.layout.spinner_custom,parent, false);
	     TextView label=(TextView)row.findViewById(R.id.tvCustomTitle);
	     label.setText(data.get(position).getCustomerFullnames()); 
	     return row;
	     }
	}