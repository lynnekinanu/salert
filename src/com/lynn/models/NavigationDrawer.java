package com.lynn.models;

public class NavigationDrawer {
	int icon;
	String navigationTitle;
public NavigationDrawer(){}
public NavigationDrawer(String title,int icon){
	this.navigationTitle=title;
	this.icon=icon;
}

public int getIcon(){
	return this.icon;
	
}
public String getTitle(){
	return this.navigationTitle;
}

public void setIcon(int icon){
	this.icon=icon;
	
}
public void setTitle(String title){
	this.navigationTitle=title;
}
}
