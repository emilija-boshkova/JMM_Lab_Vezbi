package com.example.lab01;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class DisplayAll extends Activity {
	List<Map<String, String>>seriesList = new ArrayList<Map<String,String>>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_all);
		String[] items = { "The Big Bang Theory","Game of Thrones","2 Broke Girls",
	    "Game of Thrones","Californication","How I Met Your Mother",
	    "Suburgatory","Dexter","Modern Family","Once Upon A Time",
	    "Fringe","Go On","Homeland","Arrow","Supernatural" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items);
		ListView listView = (ListView) findViewById(R.id.listView);
		listView.setAdapter(adapter);
		// Show the Up button in the action bar.
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parentAdapter, View view, int position,
                    long id) {
TextView clickedView = (TextView) view;
String thanks = getResources().getString(R.string.thanks);
String voted = getResources().getString(R.string.voted);
new AlertDialog.Builder(view.getContext()).setTitle(thanks).setMessage(voted+" "+clickedView.getText()).setNeutralButton("Close", null).show();
//Toast.makeText(DisplayAll.this, "Item with id ["+id+"] - Position ["+position+"] - Serie ["+clickedView.getText()+"]", Toast.LENGTH_SHORT).show();

}
		});
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
		getMenuInflater().inflate(R.menu.display_all, menu);
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
