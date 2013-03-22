package com.example.lab01;


import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.lab01.adapter.PopularAdapter;
import com.example.lab01.item.PopularItem;

public class DisplayPopular extends Activity {
	private ListView listView1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_popular);
     PopularItem items[]=new PopularItem[]
		{
    		 new PopularItem("9.4", "Game of Thrones"),
    		 new PopularItem("9.0", "Dexter"),
    		 new PopularItem("8.8", "The Walking Dead"),
    		 new PopularItem("8.7", "Modern Family"),
    		 new PopularItem("8.6", "The Big Bang Theory"),
    		 new PopularItem("8.3", "Californication"),
		};
		// Show the Up button in the action bar.
     PopularAdapter adapter=new PopularAdapter(this,R.layout.listview_item_row,items);
     listView1=(ListView)findViewById(R.id.listView1);
     listView1.setAdapter(adapter);
		setupActionBar();
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_popular, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
