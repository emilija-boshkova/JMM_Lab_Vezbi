package com.example.lab02.database;

import java.util.ArrayList;
//import java.util.Date;
import java.util.List;

import com.example.lab02.model.SerieItem;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SeriesDao {
	// Database fields
	private SQLiteDatabase database;
	private SeriesDbOpenHelper dbHelper;
	private String[] allColumns = { SeriesDbOpenHelper.COLUMN_ID,
			SeriesDbOpenHelper.COLUMN_TITLE, SeriesDbOpenHelper.COLUMN_RATING};

	public SeriesDao(Context context, String lang) {
		dbHelper = new SeriesDbOpenHelper(context, lang);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		database.close();
		dbHelper.close();
	}

	public boolean insert(SerieItem item) {

		if (item.getId() != null) {
			return update(item);
		}

		long insertId = database.insert(SeriesDbOpenHelper.TABLE_NAME, null,
				itemToContentValues(item));

		if (insertId > 0) {
			item.setId(insertId);
			return true;
		} else {
			return false;
		}

	}

	public boolean update(SerieItem item) {
		long numRowsAffected = database.update(SeriesDbOpenHelper.TABLE_NAME,
				itemToContentValues(item), SeriesDbOpenHelper.COLUMN_ID + " = "
						+ item.getId(), null);
		return numRowsAffected > 0;
	}

	public List<SerieItem> getAllItems() {
		List<SerieItem> items = new ArrayList<SerieItem>();

		Cursor cursor = database.query(SeriesDbOpenHelper.TABLE_NAME, allColumns,
				null, null, null, null, null);

		if (cursor.moveToFirst()) {
			do {
				items.add(cursorToItem(cursor));
			} while (cursor.moveToNext());
		}
		cursor.close();
		return items;
	}

	public SerieItem getById(long id) {

		Cursor cursor = database
				.query(SeriesDbOpenHelper.TABLE_NAME, allColumns,
						SeriesDbOpenHelper.COLUMN_ID + " = " + id, null, null,
						null, null);
		try {
			if (cursor.moveToFirst()) {
				return cursorToItem(cursor);
			} else {
				// no items found
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			cursor.close();
		}

	}

	protected SerieItem cursorToItem(Cursor cursor) {
		SerieItem item = new SerieItem();
		item.setId(cursor.getLong(cursor
				.getColumnIndex(SeriesDbOpenHelper.COLUMN_ID)));

		item.setTitle(cursor.getString(cursor
				.getColumnIndex(SeriesDbOpenHelper.COLUMN_TITLE)));

		//item.setDone(1 == cursor.getInt((cursor
			//	.getColumnIndex(SeriesDbOpenHelper.COLUMN_DONE))));

		item.setRating(cursor.getString(cursor
				.getColumnIndex(SeriesDbOpenHelper.COLUMN_RATING)));

		return item;
	}

	protected ContentValues itemToContentValues(SerieItem item) {
		ContentValues values = new ContentValues();
		if (item.getId() != null) {
			values.put(SeriesDbOpenHelper.COLUMN_ID, item.getId());
		}
		values.put(SeriesDbOpenHelper.COLUMN_TITLE, item.getTitle());
		//values.put(SeriesDbOpenHelper.COLUMN_DONE, item.isDone() ? 1 : 0);
		values.put(SeriesDbOpenHelper.COLUMN_RATING, item.getRating());
		return values;
	}
}
