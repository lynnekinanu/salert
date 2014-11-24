package com.lynn.salert;

import java.util.ArrayList;

import com.lynn.models.Customer_Model;
import com.lynn.models.Schedule_Model;
import com.lynn.models.Users_Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {
	private static final String DATABASE = "salert";
	private static final int VERSION = 2;
	private final String T_USERS = "users", T_CUSTOMER = "customer",
			T_SCHEDULES = "schedules";
	SQLiteDatabase db;

	public DatabaseHandler(Context context) {
		super(context, DATABASE, null, VERSION);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// create counties table
		db.execSQL("CREATE TABLE IF NOT EXISTS "
				+ T_USERS
				+ " (id INTEGER PRIMARY KEY AUTOINCREMENT ,email VARCHAR(50) ,password VARCHAR, firstname VARCHAR(30) ,lastname VARCHAR(30));");
		db.execSQL("CREATE TABLE IF NOT EXISTS "
				+ T_CUSTOMER
				+ " (id INTEGER PRIMARY KEY AUTOINCREMENT ,email VARCHAR(50) ,fullnames VARCHAR, mobile VARCHAR(30) ,facebook VARCHAR(100),twitter VARCHAR(100));");
		db.execSQL("CREATE TABLE IF NOT EXISTS "
				+ T_SCHEDULES
				+ " (id INTEGER PRIMARY KEY AUTOINCREMENT ,service VARCHAR(50) ,date VARCHAR, time VARCHAR ,customer_id INT,amount DOUBLE,remarks VARCHAR);");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + T_USERS);
		db.execSQL("DROP TABLE IF EXISTS " + T_CUSTOMER);
		db.execSQL("DROP TABLE IF EXISTS " + T_SCHEDULES);
	}

	public void open() {
		db = this.getWritableDatabase();
	}

	/**
	 * create new user ie salon owner
	 * 
	 * @param user
	 * @return
	 */
	public boolean createUser(Users_Model user) {
		boolean y = true;
		open();
		ContentValues values = new ContentValues();
		values.put("email", user.getUserEmail());
		values.put("password", user.getUserPassword());
		values.put("firstname", user.getUserFirstname());
		values.put("lastname", user.getUserLastname());
		if (db.insert(T_USERS, null, values) < 1) {
			y = false;
		}
		return y;
	}

	public Users_Model getUserDetails(String email, String password) {
		Users_Model user = new Users_Model();
		 
		open();
		Cursor c = db.rawQuery("SELECT * FROM " + T_USERS + "  WHERE email = '"
				+ email + "' AND password ='" + password + "'", null);
		 user.setUserFirstname(""); 
		for (c.moveToFirst(); c.isFirst(); c.moveToNext()) {
			user = new Users_Model(); 
			user.setUserFirstname(c.getString(c.getColumnIndex("firstname")));
			user.setUserLastname(c.getString(c.getColumnIndex("lastname")));
		}
		c.close(); 
		return user;
	}

	public boolean createCustomer(Customer_Model customer) {
		boolean y = true;
		open();
		ContentValues values = new ContentValues();
		values.put("fullnames", customer.getCustomerFullnames());
		values.put("email", customer.getCustomerEmail());
		values.put("twitter", customer.getCustomerTwitter());
		values.put("facebook", customer.getCustomerFacebook());
		values.put("mobile", customer.getCustomerMobile());
		if (db.insert(T_CUSTOMER, null, values) < 1) {
			y = false;
		}
		return y;
	}
public boolean createSchedule(Schedule_Model schedule){
	boolean y = true;
	open();
	ContentValues values = new ContentValues();
	values.put("date",schedule.getScheduleDate());
	values.put("time",schedule.getScheduleTime());
	values.put("remarks",schedule.getScheduleRemarks());
	values.put("service",schedule.getScheduleServiceName());
	values.put("amount",schedule.getScheduleAmount());
	values.put("customer_id",schedule.getScheduleCustomerID());
 
	if (db.insert(T_SCHEDULES, null, values) < 1) {
		y = false;
	}
	return y;
}
	public ArrayList<Customer_Model> getAllCustomers() {
		open();
		ArrayList<Customer_Model> customers = new ArrayList<Customer_Model>();
		Customer_Model customer;
		Cursor c = db.rawQuery("SELECT * FROM " + T_CUSTOMER, null);
		while (c.moveToNext()) {
			customer = new Customer_Model();
			customer.setCustomerID(c.getInt(c.getColumnIndex("id")));
			customer.setCustomerFullnames(c.getString(c
					.getColumnIndex("fullnames")));
			customer.setCustomerEmail(c.getString(c.getColumnIndex("email")));
			customer.setCustomerFacebook(c.getString(c
					.getColumnIndex("facebook")));
			customer.setCustomerMobile(c.getString(c.getColumnIndex("mobile")));
			customer.setCustomerTwitter(c.getString(c.getColumnIndex("twitter")));
			customers.add(customer);

		}
		c.close();
		return customers;
	}
	
	public ArrayList<Customer_Model> getAllCustomersSpinner() {
		open();
		ArrayList<Customer_Model> customers = new ArrayList<Customer_Model>();
		Customer_Model customer;
		Cursor c = db.rawQuery("SELECT * FROM " + T_CUSTOMER, null);
		customer=new Customer_Model();
		customer.setCustomerID(0);
		customer.setCustomerFullnames("Select Customer");
		customers.add(customer);
		while (c.moveToNext()) {
			customer = new Customer_Model();
			customer.setCustomerID(c.getInt(c.getColumnIndex("id")));
			customer.setCustomerFullnames(c.getString(c
					.getColumnIndex("fullnames")));
			customer.setCustomerEmail(c.getString(c.getColumnIndex("email")));
			customer.setCustomerFacebook(c.getString(c
					.getColumnIndex("facebook")));
			customer.setCustomerMobile(c.getString(c.getColumnIndex("mobile")));
			customer.setCustomerTwitter(c.getString(c.getColumnIndex("twitter")));
			customers.add(customer);

		}
		c.close();
		return customers;
	}
/**
 * get all schedules
 * @return
 */
	public ArrayList<Schedule_Model> getAllSchedules() {
		open();
		ArrayList<Schedule_Model> schedules = new ArrayList<Schedule_Model>();
		Schedule_Model schedule;
		Cursor c = db.rawQuery("SELECT " + T_SCHEDULES + ".*," + T_CUSTOMER
				+ ".fullnames FROM " + T_SCHEDULES + " left join " + T_CUSTOMER
				+ " on " + T_CUSTOMER + ".id =" + T_SCHEDULES + ".customer_id",
				null);

		schedule = new Schedule_Model();

		while (c.moveToNext()) {
			schedule = new Schedule_Model();
			schedule.setScheduleAmount(c.getDouble(c.getColumnIndex("amount")));
			schedule.setScheduleCustomerName(c.getString(c
					.getColumnIndex("fullnames")));
			schedule.setScheduleDate(c.getString(c.getColumnIndex("date")));
			schedule.setScheduleRemarks(c.getString(c.getColumnIndex("remarks")));
			schedule.setScheduleServiceName(c.getString(c
					.getColumnIndex("service")));
			schedule.setScheduleTime(c.getString(c.getColumnIndex("time")));
			schedule.setScheduleID(c.getInt(c.getColumnIndex("time")));
			schedules.add(schedule);

		}
		c.close();
		return schedules;
	}

}
