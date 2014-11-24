package com.lynn.salert;

import java.util.ArrayList;

 
import com.example.salert.R;
import com.lynn.customadapters.NavigationCustomAdapter;
import com.lynn.models.NavigationDrawer;

import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
 
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends ActionBarActivity {
	DrawerLayout drawerLayout;
	public ListView drawerList;
	RelativeLayout relative;
	ActionBarDrawerToggle drawerToggle;
	// drawer title
	CharSequence mDrawerTitle;
	// used to store app title
	CharSequence mTitle;
	// slide menu items
	String[] navMenuTitles;
	TypedArray navMenuIcons;

	ArrayList<NavigationDrawer> navDrawerItems;
	NavigationCustomAdapter adapter;
	NavigationDrawer detail;
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.navigation_drawer);
	        relative=(RelativeLayout)findViewById(R.id.relative);
	        mTitle = mDrawerTitle = getTitle();
			navDrawerItems = new ArrayList<NavigationDrawer>();

			navMenuTitles = getResources().getStringArray(R.array.titleArray);
			navMenuIcons = getResources()
					.obtainTypedArray(R.array.nav_drawer_icons);

			drawerList = (ListView) findViewById(R.id.list_nav);
			drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
			for (int i = 0; i < navMenuIcons.length(); i++) {

				detail = new NavigationDrawer();
				detail.setIcon(navMenuIcons.getResourceId(i, -1));
				detail.setTitle(navMenuTitles[i]);
				navDrawerItems.add(detail);
			}
			
			// Recycle the typed array
					navMenuIcons.recycle();

					adapter = new NavigationCustomAdapter(getApplicationContext(),navDrawerItems);
					drawerList.setAdapter(adapter);

					// enabling action bar icon and behaving it as toggle button

					getSupportActionBar().setDisplayHomeAsUpEnabled(true);
					getSupportActionBar().setHomeButtonEnabled(true);
					drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
							R.drawable.ic_drawer, R.string.drawer_open,
							R.string.drawer_close) {
						@Override
						public void onDrawerClosed(View drawerView) {
							super.onDrawerClosed(drawerView);
							getSupportActionBar().setTitle(mTitle);
							// call onprepareoptionsmenu() to show action bar icons
							supportInvalidateOptionsMenu();
						}

						@Override
						public void onDrawerOpened(View drawerView) {
							super.onDrawerOpened(drawerView);
							getSupportActionBar().setTitle(mTitle);
							// call onprepareoptionsmenu() to hide action bar icons
							supportInvalidateOptionsMenu();
						}

					};

					drawerLayout.setDrawerListener(drawerToggle);
					if (savedInstanceState == null) {
						// on first time display view for first nav item
						displayView(0);
					}
				 
					drawerList.setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1,
								int position, long arg3) {
							// display the view for selected nav drawer item
							displayView(position);

						}
					});

				}
	 @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		 MenuInflater inflater=new MenuInflater(getApplicationContext());
		 inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			 
				if (drawerLayout.isDrawerOpen(relative)) {
					drawerLayout.closeDrawer(relative);
				} else {
					drawerLayout.openDrawer(relative);
				}
				return true;
			 
		}

		
		
		return super.onOptionsItemSelected(item);
	}
	    
	    
	    @Override
		public void setTitle(CharSequence title) {
			// TODO Auto-generated method stub
			super.setTitle(title);
			mTitle = title;
			getSupportActionBar().setTitle(mTitle);
		}

		// while using actionbardrawer toggle it has to be called during the
		// onPostCreate() and onConfigurationChanged()
		@Override
		protected void onPostCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onPostCreate(savedInstanceState);
			drawerToggle.syncState();
		}

		@Override
		public boolean onPrepareOptionsMenu(Menu menu) {
			 
			// if the nav is opened ,hide action items

			boolean drawerOpen = drawerLayout.isDrawerOpen(relative);
			 
			return super.onPrepareOptionsMenu(menu);
		}

		@Override
		public void onConfigurationChanged(Configuration newConfig) {
			// TODO Auto-generated method stub
			super.onConfigurationChanged(newConfig);
			drawerToggle.onConfigurationChanged(newConfig);
		}

		// iteratin on the various selected elements to there corresponding
		// fragements

		public void displayView(int position) {

			Fragment fragement = null;
			switch (position) {
			case 0: 
				//make order
				fragement = new ScheduleFragment();
				break;
			case 1: 
				//make sales
				fragement = new CustomerFragment();
				break;
			case 2: 
				//stock
				fragement = new CalendarFragment();
				break;
			case 3: 
				//transactions
				fragement = new ProfileFragment();
				break;
			 
			default:
				break;
			}

			if (fragement != null) {
				FragmentManager fragmentManager = getSupportFragmentManager();
				fragmentManager.beginTransaction()
						.replace(R.id.content_frame, fragement).commit();
				// update selected item and title
				drawerList.setItemChecked(position, true);
				drawerList.setSelection(position);
				setTitle(navMenuTitles[position]);
				// close the drawer
				drawerLayout.closeDrawer(relative);

			} else {
				Log.e("Main Activity", "Error in creating fragement");
			}
		}
	 
	}
