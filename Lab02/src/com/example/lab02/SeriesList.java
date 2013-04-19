package com.example.lab02;

import java.util.Date;
import java.util.List;

import com.example.lab02.DownloadService;
import com.example.lab02.R;
import com.example.lab02.SeriesDetails;
import com.example.lab02.SeriesList;
import com.example.lab02.SeriesList.OnDownloadRefreshReceiver;
import com.example.lab02.adapters.SeriesAdapter;
import com.example.lab02.database.SeriesDao;
import com.example.lab02.model.SerieItem;
import com.example.lab02.tasks.DownloadTask;
import com.example.lab02.tasks.DownloadWithProgressTask;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;

public class SeriesList extends Activity {
	public static final String DEFAULT_LANG = "mk";

	static final String ACTION_TODO_DETAILS = "com.example.lab02.ACTION_TODO_DETAILS";
	private EditText mItemTitle;
	private EditText mItemRating;
	private ListView mSeriesItemsList;
	private SeriesDao mDao;
	private SeriesAdapter mAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_series_list);
		loadViews();
		initList();
	}
	@Override
	protected void onResume() {
		super.onResume();
		mDao = new SeriesDao(this, DEFAULT_LANG);
		mDao.open();
		loadData();
	}

	@Override
	protected void onPause() {
		super.onPause();
		mDao.close();
	}

	private void loadData() {

		List<SerieItem> res = mDao.getAllItems();
		mAdapter.clear();
		mAdapter.addAll(res);
	}

	/**
	 * Inflates the views from the xml layout
	 */
	private void loadViews() {
		mItemTitle = (EditText) findViewById(R.id.txtTitle);
		mItemRating = (EditText) findViewById(R.id.txtRating);
		mSeriesItemsList = (ListView) findViewById(R.id.SeriesItems);
	}

	private void initList() {
		mAdapter = new SeriesAdapter(this);
		mSeriesItemsList.setAdapter(mAdapter);
		//mTodoItemsList.setOnItemClickListener(mAdapter);
		mSeriesItemsList
				.setOnItemLongClickListener(new OnItemLongClickListener() {

					@Override
					public boolean onItemLongClick(AdapterView<?> arg0,
							View parent, int position, long id) {
						Toast.makeText(SeriesList.this, "Item long click",
								Toast.LENGTH_LONG).show();
						return true;
					}

				});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(1, 1, 1, "Refresh");
		menu.add(1, 2, 2, "Refresh with service");
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == 1) {
			if (checkConnection()) {
				DownloadWithProgressTask task = new DownloadWithProgressTask(
						this, mAdapter);
				task.execute(getString(R.string.all_series_items));

				return true;
			}

		} else if (item.getItemId() == 2) {
			if (checkConnection()) {
				createDialog();
				IntentFilter filter = new IntentFilter(
						DownloadTask.ITEMS_DOWNLOADED_ACTION);
				registerReceiver(new OnDownloadRefreshReceiver(), filter);
				startService(new Intent(this, DownloadService.class));
				return true;
			}
		}

		return false;
	}

	private boolean checkConnection() {

		ConnectivityManager connectivityMannager = (ConnectivityManager) this
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = connectivityMannager.getActiveNetworkInfo();
		if (netInfo == null) {
			showSettingsAlert();
			return false;
		} else {
			return true;
		}

	}

	public void showSettingsAlert() {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

		// Setting Dialog Title
		alertDialog.setTitle("Internet settings");

		// Setting Dialog Message
		alertDialog
				.setMessage("No active connection. Do you want to go to settings menu?");

		// On pressing Settings button
		alertDialog.setPositiveButton("Settings",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface promptDialog, int which) {
						Intent intent = new Intent(
								Settings.ACTION_WIFI_SETTINGS);
						SeriesList.this.startActivity(intent);
					}
				});

		// on pressing cancel button
		alertDialog.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface promptDialog, int which) {
						promptDialog.dismiss();

					}
				});

		// Showing Alert Message
		alertDialog.show();
	}

	private ProgressDialog loadingDialog;

	private void createDialog() {
		loadingDialog = new ProgressDialog(this);
		loadingDialog.setTitle(this.getResources().getString(
				R.string.download_title));
		loadingDialog.setMessage(this.getResources().getString(
				R.string.download_description));
		loadingDialog.setIndeterminate(true);
		loadingDialog.setCancelable(false);
	}

	public void explicit(View view) {
		startActivity(new Intent(this, SeriesDetails.class));
	}

	public void implicit(View view) {
		Intent detailsIntent = new Intent(ACTION_TODO_DETAILS);
		detailsIntent.putExtra("time", (new Date()).getTime());
		startActivity(detailsIntent);
	}

	public void addSerieItem(View view) {

		SerieItem item = new SerieItem(mItemTitle.getText().toString(),mItemRating.getText().toString());

		mAdapter.add(item);
		mDao.insert(item);
	}

	class OnDownloadRefreshReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {

			loadData();

			if (loadingDialog != null && loadingDialog.isShowing()) {
				loadingDialog.dismiss();
			}

			SeriesList.this.unregisterReceiver(this);

		}
	}
}
