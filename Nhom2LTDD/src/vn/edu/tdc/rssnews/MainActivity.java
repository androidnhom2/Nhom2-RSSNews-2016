package vn.edu.tdc.rssnews;

import java.util.ArrayList;

import vn.edu.tdc.adapter.SlideMenuAdapter;
import vn.edu.tdc.datamodels.ItemSlideMenu;
import vn.edu.tdc.fragment.BusinessContent;
import vn.edu.tdc.fragment.EducationContent;
import vn.edu.tdc.fragment.EntertainmentContent;
import vn.edu.tdc.fragment.HealContent;
import vn.edu.tdc.fragment.HistoryContent;
import vn.edu.tdc.fragment.HomeContent;
import vn.edu.tdc.fragment.LawContent;
import vn.edu.tdc.fragment.SportContent;
import vn.edu.tdc.fragment.TravelContent;
import vn.edu.tdc.fragment.WorldContent;
import vn.edu.tdc.nhom2ltdd.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends Activity {

	String[] menutitles;
	TypedArray menuIcons;
	
	//private CharSequence mTitle;
	private DrawerLayout drawerLayout;
	private ListView lvDrawerMenu;
	private ActionBarDrawerToggle actionBarDrawerToggle;

	private ArrayList<ItemSlideMenu> itemSlide;
	private SlideMenuAdapter menuadapter;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);

		menutitles = getResources().getStringArray(R.array.titles);
		menuIcons = getResources().obtainTypedArray(R.array.icons);

		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		lvDrawerMenu = (ListView) findViewById(R.id.slider_list);

		itemSlide = new ArrayList<ItemSlideMenu>();

		for (int i = 0; i < menutitles.length; i++) {
			ItemSlideMenu items = new ItemSlideMenu(menuIcons.getResourceId(i,
					-1), menutitles[i]);
			itemSlide.add(items);
		}

		menuIcons.recycle();

		menuadapter = new SlideMenuAdapter(MainActivity.this, itemSlide);

		lvDrawerMenu.setAdapter(menuadapter);
		lvDrawerMenu.setOnItemClickListener(new SlideitemListener());

		// enabling action bar app icon and behaving it as toggle button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
				R.drawable.ic_drawer, // nav menu toggle icon
				R.drawable.ic_back, // nav drawer open - description for
									// accessibility
				R.drawable.ic_back // nav drawer close - description for
									// accessibility
		) {
			public void onDrawerClosed(View view) {
				// calling onPrepareOptionsMenu() to show action bar icons
				getActionBar().setTitle("Tổng hợp tin tức RSS");
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				// calling onPrepareOptionsMenu() to hide action bar icons
				getActionBar().setTitle("Menu");
				invalidateOptionsMenu();
			}
		};

		drawerLayout.setDrawerListener(actionBarDrawerToggle);

		if (savedInstanceState == null) {
			// on first time display view for first nav item
			updateDisplay(0);
		}
		
	}

	class SlideitemListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			updateDisplay(position);
		}

	}
	
	

	private void updateDisplay(int position) {
		Fragment fragment = null;
		switch (position) {
		case 0:
			fragment = new HomeContent();
			break;
		case 1:
			fragment = new SportContent();
			break;
		case 2:
			fragment = new EducationContent();
			break;
		case 3:
			fragment = new BusinessContent();
			break;
		case 4:
			fragment = new TravelContent();
			break;
		case 5:
			fragment = new WorldContent();
			break;
		case 6:
			fragment = new EntertainmentContent();
			break;
		case 7:
			fragment = new HealContent();
			break;
		case 8:
			fragment = new LawContent();
			break;
		case 9:
			fragment = new HistoryContent();
			break;
		default:
			break;
		}

		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.newsContent, fragment).commit();

			// update selected item and title, then close the drawer
			setTitle(menutitles[position]);
			drawerLayout.closeDrawer(lvDrawerMenu);
		} else {
			// error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}

	}

	/*@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}*/

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// toggle nav drawer on selecting action bar app icon/title
		if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return false;
	}

	/***
	 * Called when invalidateOptionsMenu() is triggered
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		drawerLayout.isDrawerOpen(lvDrawerMenu);
		return super.onPrepareOptionsMenu(menu);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		actionBarDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		actionBarDrawerToggle.onConfigurationChanged(newConfig);
	}

}