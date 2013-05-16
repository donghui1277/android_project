package com.dong.schedule;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{
	
	private static final String DATABASE_NAME = "schedule.db";  
    private static final int DATABASE_VERSION = 1;  

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE IF NOT EXISTS classes" +  
                "(day INTEGER,no INTEGER,name TEXT,address TEXT)");
		db.execSQL("CREATE TABLE IF NOT EXISTS notes (id INTEGER PRIMARY KEY AUTOINCREMENT,bg_color_id INTEGER NOT NULL DEFAULT 0, created_date TEXT NOT NULL DEFAULT (datetime('now','localtime')), modified_date TEXT NOT NULL DEFAULT (datetime('now','localtime')),content TEXT NOT NULL DEFAULT '')");
//		for (int i = 0; i < 7; i++) {
//			for (int j = 1; j < 6; j++) {
//				db.execSQL("insert into classes(day, no, name, address) values ("+i+","+j+","+i+","+j+")");
//			}
//		}
		

		db.execSQL("INSERT INTO notes(content) values ('³¤°´É¾³ý')");
		
		for (int i = 0; i<7; i++){
			for (int j = 1; j<6; j++) {
				db.execSQL("INSERT INTO classes VALUES("+i+","+j+",'0','0')");
			}
		}
		

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS" + DATABASE_NAME);
		onCreate(db);
	}

}
