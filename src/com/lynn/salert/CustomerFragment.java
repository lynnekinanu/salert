package com.lynn.salert;

import java.util.ArrayList;

 
import com.example.salert.R;
import com.lynn.customadapters.CustomerCustomAdapter;
import com.lynn.models.Customer_Model;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class CustomerFragment extends Fragment {
	ArrayList<Customer_Model> customers;
	ListView lstCustomer;
	CustomerCustomAdapter adapter;
	DatabaseHandler handler;
	Dialog customerDialog;
	EditText etFullnames,etEmail,etFacebook,etMobile,etTwitter;
	Button btnCreateCustomer; 
	Customer_Model customer;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.all_customers, container, false);
		customers = new ArrayList<Customer_Model>();
		lstCustomer = (ListView) view.findViewById(R.id.lstAllCustomers);
		handler = new DatabaseHandler(getActivity());
		customers = handler.getAllCustomers();
		customer=new Customer_Model();
		handler.close();
		adapter = new CustomerCustomAdapter(getActivity(), customers);
		lstCustomer.setAdapter(adapter);
		setHasOptionsMenu(true);
		lstCustomer.setOnItemClickListener(new OnItemClickListener() {
		 
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

			}
		});

		return view;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
	
		inflater.inflate(R.menu.customers, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_add_customer:
createCustomer();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void createCustomer(){  
		customerDialog = new Dialog(getActivity());
		customerDialog.requestWindowFeature(Window.FEATURE_LEFT_ICON);
		customerDialog.setTitle("New Customer");
		customerDialog.setContentView(R.layout.new_customer);
		initilizeDialogViews(customerDialog);
		customerDialog.setFeatureDrawableResource(Window.FEATURE_LEFT_ICON,
				R.drawable.customer);
		btnCreateCustomer.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			customer.setCustomerEmail(etEmail.getText().toString());
			customer.setCustomerFacebook(etFacebook.getText().toString());
			customer.setCustomerFullnames(etFullnames.getText().toString());
			customer.setCustomerMobile(etMobile.getText().toString());
			customer.setCustomerTwitter(etTwitter.getText().toString());
			if(handler.createCustomer(customer)){
			Toast.makeText(getActivity(), "Successfully created customer", Toast.LENGTH_LONG).show();
			}
			handler.close();
			customers = handler.getAllCustomers();
	 
			handler.close();
			adapter = new CustomerCustomAdapter(getActivity(), customers);
			lstCustomer.setAdapter(adapter);
				
			customerDialog.dismiss();
			}
		});
		customerDialog.show();
	 
	}

	private void initilizeDialogViews(Dialog view) {
	etEmail=(EditText)view.findViewById(R.id.etCustomerEmail);
	etFacebook=(EditText)view.findViewById(R.id.etCustomerFacebook);
	etFullnames=(EditText)view.findViewById(R.id.etCustomerFullnames);
	etMobile=(EditText)view.findViewById(R.id.etCustomerMobile);
	etTwitter=(EditText)view.findViewById(R.id.etCustomerTwitter);
	btnCreateCustomer=(Button)view.findViewById(R.id.btnAddCustomer);
	}
}
