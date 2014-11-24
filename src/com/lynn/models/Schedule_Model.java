package com.lynn.models;

public class Schedule_Model {
	String service_name,date,time,remarks,customer_name;
	double amount;
	int id,customer_id;
	public int getScheduleID(){
		return id;
	}
	public void setScheduleID(int id){
		this.id=id;
	}
	
	public int getScheduleCustomerID(){
		return customer_id;
	}
	public void setScheduleCustomerID(int customer_id){
		this.customer_id=customer_id;
	}
	
	public double getScheduleAmount(){
		return amount;
	}
	public void setScheduleAmount(double amount){
		this.amount=amount;
	}
	
	public String getScheduleServiceName(){
		return service_name;
	}
	public void setScheduleServiceName(String service_name){
		this.service_name=service_name;
	}
	
	public String getScheduleDate(){
		return date;
	}
	public void setScheduleDate(String date){
		this.date=date;
	}
	
	public String getScheduleTime(){
		return time;
	}
	public void setScheduleTime(String time){
		this.time=time;
	}
	public String getScheduleRemarks(){
		return remarks;
	}
	public void setScheduleRemarks(String remarks){
		this.remarks=remarks;
	}
	public String getScheduleCustomerName(){
		return customer_name;
	}
	public void setScheduleCustomerName(String customer_name){
		this.customer_name=customer_name;
	}
}
