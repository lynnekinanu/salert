package com.lynn.salert;

import java.util.ArrayList;
import java.util.Calendar;

import com.example.salert.R;
import com.lynn.customadapters.CustomerCustomAdapter;
import com.lynn.customadapters.CustomerSpinnerCustomeAdapter;
import com.lynn.customadapters.ScheduleCustomeListAdapter;
import com.lynn.models.Customer_Model;
import com.lynn.models.Schedule_Model;

import android.app.Dialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ScheduleFragment extends Fragment implements View.OnClickListener {
	ArrayList<Schedule_Model> schedules;
	ListView lstSchedule;
	ScheduleCustomeListAdapter adapter;
	DatabaseHandler handler;
	Dialog scheduleDialog;
	EditText etScheduleAmount, etScheduleResults;
	Spinner spnSpinnerServices, spnSpinnerCustomer;
	Button btnCreateSchedule, btnCancel, btnDate, btnTime;
	Schedule_Model schedule;
	int customer_id = 0;
	String date = "", time = "", service = "", remarks = "";
	double amount = 0;

	Calendar calendar;
	int year, day, month;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.all_schedules, container, false);

		schedules = new ArrayList<Schedule_Model>();
		lstSchedule = (ListView) view.findViewById(R.id.lstAllSchedules);
		handler = new DatabaseHandler(getActivity());
		popolateSchedules();
		setHasOptionsMenu(true);

		calendar = Calendar.getInstance();
		year = calendar.get(Calendar.YEAR);
		day = calendar.get(Calendar.DATE);
		month = calendar.get(Calendar.MONTH);
		lstSchedule.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

			}
		});
		return view;

	}

	private void popolateSchedules() {
		schedules = handler.getAllSchedules();
		schedule = new Schedule_Model();
		handler.close();
		Log.e("schedules count", schedules.size() + "");
		adapter = new ScheduleCustomeListAdapter(getActivity(), schedules);
		lstSchedule.setAdapter(adapter);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);

		inflater.inflate(R.menu.schedules, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_add_schedule:
			createSchedule();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void createSchedule() {
		scheduleDialog = new Dialog(getActivity());
		scheduleDialog.requestWindowFeature(Window.FEATURE_LEFT_ICON);
		scheduleDialog.setTitle("New Schedule");
		scheduleDialog.setContentView(R.layout.new_schedule);
		initilizeDialogViews(scheduleDialog);
		ArrayList<Customer_Model> customers = new ArrayList<Customer_Model>();

		customers = handler.getAllCustomersSpinner();
		CustomerSpinnerCustomeAdapter spinAdapter = new CustomerSpinnerCustomeAdapter(
				getActivity(), R.layout.spinner_custom, customers);
		spnSpinnerCustomer.setAdapter(spinAdapter);
		spnSpinnerCustomer
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> adapterView,
							View arg1, int position, long arg3) {
						Customer_Model cust = new Customer_Model();
						cust = (Customer_Model) adapterView.getSelectedItem();
						customer_id = cust.getCustomerID();

					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}
				});
		spnSpinnerServices
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> adapterview,
							View arg1, int position, long arg3) {
						service = adapterview.getItemAtPosition(position)
								.toString();

					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}
				});
		btnDate.setOnClickListener(this);
		btnTime.setOnClickListener(this);
		btnCreateSchedule.setOnClickListener(this);
		btnCancel.setOnClickListener(this);
		scheduleDialog.show();

	}

	private void initilizeDialogViews(Dialog view) {
		spnSpinnerCustomer = (Spinner) view
				.findViewById(R.id.spnScheduleCustomers);
		spnSpinnerServices = (Spinner) view
				.findViewById(R.id.spnScheduleServices);
		etScheduleAmount=(EditText)view.findViewById(R.id.etScheduleAcmount);
		etScheduleResults=(EditText)view.findViewById(R.id.etScheduleRemarks);
		btnDate = (Button) view.findViewById(R.id.btnScheduleDate);
		btnTime = (Button) view.findViewById(R.id.btnScheduleTime);
		btnCancel = (Button) view.findViewById(R.id.btnCancelSchedule);
		btnCreateSchedule = (Button) view.findViewById(R.id.btnSubmitSchedule);

	}

	private void showDatePicker() {
		DatePickerFragment date = new DatePickerFragment();
		/**
		 * Set Up Current Date Into dialog
		 */
		Calendar calender = Calendar.getInstance();
		Bundle args = new Bundle();
		args.putInt("year", year);
		args.putInt("month", month);
		args.putInt("day", day);
		date.setArguments(args);
		/**
		 * Set Call back to capture selected date
		 */
		date.setCallBack(ondate);
		date.show(getActivity().getSupportFragmentManager(), "Date Picker");
	}

	public void showTimePicker() {
		TimePickerFragment newFragment = new TimePickerFragment();
		newFragment.setCallBack(ontime);
		newFragment.show(getActivity().getSupportFragmentManager(),
				"timePicker");

	}

	OnTimeSetListener ontime = new OnTimeSetListener() {
		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			Log.e("Time Date", hourOfDay + "");
			String am_pm=(hourOfDay<11)?"A.M":"P.M";	
			btnTime.setText(String.valueOf(hourOfDay) + ":"
					+ String.valueOf(minute));
			time = String.valueOf(hourOfDay) + ":" + String.valueOf((minute<10)?"0"+minute:minute)+ "  "+am_pm;
		}
	};
	OnDateSetListener ondate = new OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			monthOfYear += 1;
			btnDate.setText(String.valueOf(year) + "-"
					+ String.valueOf(monthOfYear) + "-"
					+ String.valueOf(dayOfMonth));
			String month = null;
			if (monthOfYear < 10) {
				month = "0" + monthOfYear;
			} else {
				month = monthOfYear + "";
			}
			date = String.valueOf(year) + "-" + month + "-"
					+ String.valueOf(dayOfMonth);

		}
	};

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnScheduleDate:
			showDatePicker();
			break;
		case R.id.btnScheduleTime:
			showTimePicker();
			break;
		case R.id.btnCancelSchedule:
			scheduleDialog.dismiss();
			break;
		case R.id.btnSubmitSchedule:
			if (validateScheduleViews()) {
				Schedule_Model schedule = new Schedule_Model();
				schedule.setScheduleAmount(Double.parseDouble(etScheduleAmount
						.getText().toString()));
				schedule.setScheduleCustomerID(customer_id);
				schedule.setScheduleDate(date);
				schedule.setScheduleRemarks(etScheduleResults.getText()
						.toString());
				schedule.setScheduleServiceName(service);
				schedule.setScheduleTime(time);
				
				if(handler.createSchedule(schedule))
				{
					Toast.makeText(getActivity(), "Created Schedule Successfully", Toast.LENGTH_LONG).show();
					popolateSchedules();
					scheduleDialog.dismiss();
				}else{
					Toast.makeText(getActivity(), "Error Creating Schedule", Toast.LENGTH_LONG).show();
					scheduleDialog.dismiss(); 
				}
			}
			break;

		default:
			break;
		}

	}

	private boolean validateScheduleViews() {
		boolean y = false;
		if (customer_id == 0) {
			Toast.makeText(getActivity(), "Select Customer", Toast.LENGTH_LONG)
					.show();
		} else if (service == "") {
			Toast.makeText(getActivity(), "Select Service", Toast.LENGTH_LONG)
					.show();
		} else if (date == "") {
			Toast.makeText(getActivity(), "Select Date", Toast.LENGTH_LONG)
					.show();
		} else if (time == "") {
			Toast.makeText(getActivity(), "Select Time", Toast.LENGTH_LONG)
					.show();
		} else if (etScheduleAmount.getText().toString().equals("")) {
			etScheduleAmount.setError("Amount is empty");
			etScheduleAmount.requestFocus();
		} else if (etScheduleResults.getText().toString().equals("")) {
			etScheduleResults.setError("Results is empty");
			etScheduleResults.requestFocus();
		} else {
			y = true;
		}

		return y;
	}

}
