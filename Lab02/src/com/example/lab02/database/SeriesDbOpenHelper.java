package com.example.lab02.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SeriesDbOpenHelper extends SQLiteOpenHelper {

	public static final String COLUMN_ID = "_id";
	//public static final String COLUMN_NAME = "name";
	//public static final String COLUMN_DONE = "done";
	//public static final String COLUMN_DUE_DATE = "due_date";
	//public static final String TABLE_NAME = "ToDoItems";
	public static final String COLUMN_RATING = "rating";
	public static final String COLUMN_TITLE = "title";
	public static final String TABLE_NAME = "SeriesItems";

	private static final int DATABASE_VERSION = 1;

	//private static final String DATABASE_NAME_EXPRESSION = "ToDoDatabase_%s.db";
	private static final String DATABASE_NAME_EXPRESSION = "SeriesDatabase_%s.db";
	private static final String DATABASE_CREATE = String
			.format("create table %s (%s  integer primary key autoincrement, "
					+ "%s text not null, %s text not null);",
					TABLE_NAME, COLUMN_ID, COLUMN_TITLE, COLUMN_RATING);

	

	public SeriesDbOpenHelper(Context context, String lang) {
		super(context, String.format(DATABASE_NAME_EXPRESSION, lang), null,
				DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(String.format("DROP TABLE IF EXISTS %s", TABLE_NAME));
		onCreate(db);
	}

}
