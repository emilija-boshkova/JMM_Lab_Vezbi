package com.example.lab02.tasks;

import java.util.List;

import com.example.lab02.R;
import com.example.lab02.SeriesList;
import com.example.lab02.adapters.SeriesAdapter;
import com.example.lab02.database.SeriesDao;
import com.example.lab02.model.SerieItem;
import com.example.lab02.utils.Downloader;
import com.example.lab02.utils.OnContentDownloaded;
import com.example.lab02.utils.OnToDoItemsDownloaded;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.widget.Toast;

public class DownloadTask extends AsyncTask<String, Void, List<SerieItem>> {

	public static final String ITEMS_DOWNLOADED_ACTION = "com.example.lab02.ITEMS_DOWNLOADED_ACTION";
	private Exception exception = null;
	protected Context context;

	public DownloadTask(Context context) {
		this.context = context;
	}

	@Override
	protected List<SerieItem> doInBackground(String... params) {
		if (params.length < 1) {
			exception = new IllegalArgumentException(
					"At least one argument for the download url expected. ");
			return null;
		} else {

			String url = params[0];
			OnContentDownloaded<List<SerieItem>> handler = new OnToDoItemsDownloaded();
			try {
				Downloader.getFromUrl(url, handler);
				publishProgress(null);
				return handler.getResult();
			} catch (Exception ex) {
				exception = ex;
				return null;
			}
		}
	}

	@Override
	protected void onPostExecute(List<SerieItem> result) {
		super.onPostExecute(result);
		if (exception != null) {
			Toast.makeText(context, "Error: " + exception.getMessage(),
					Toast.LENGTH_LONG).show();
		} else {

			SeriesDao dao = new SeriesDao(context, SeriesList.DEFAULT_LANG);
			dao.open();

			for (SerieItem item : result) {
				dao.insert(item);
			}
			dao.close();
			Intent intent=new Intent(ITEMS_DOWNLOADED_ACTION);
			context.sendBroadcast(intent);

		}
	}

}
