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
//		for (int i = 0; i < 7; i++) {
//			for (int j = 1; j < 6; j++) {
//				db.execSQL("insert into classes(day, no, name, address) values ("+i+","+j+","+i+","+j+")");
//			}
//		}
		
		//wang
		db.execSQL("INSERT INTO classes VALUES(0,1,'0','0')");
		db.execSQL("INSERT INTO classes VALUES(0,2,'0','0')");
		db.execSQL("INSERT INTO classes VALUES(0,3,'0','0')");
		db.execSQL("INSERT INTO classes VALUES(0,4,'0','0')");
		db.execSQL("INSERT INTO classes VALUES(0,5,'0','0')");
		db.execSQL("INSERT INTO classes VALUES(1,1,'电磁场与电磁波（1-16周）','翠七教305')");
		db.execSQL("INSERT INTO classes VALUES(1,2,'大学英语（3-16周）','翠九教403')");
		db.execSQL("INSERT INTO classes VALUES(1,3,'毛概（1-8周）','翠一教102')");
		db.execSQL("INSERT INTO classes VALUES(1,4,'体育专项（5-16周）','操场')");
		db.execSQL("INSERT INTO classes VALUES(1,5,'晚自习','晚自习')");
		db.execSQL("INSERT INTO classes VALUES(2,1,'数字逻辑电路B（5-16周）','翠三教306')");
		db.execSQL("INSERT INTO classes VALUES(2,2,'模拟电子线路A（1-16周）','翠三教306')");
		db.execSQL("INSERT INTO classes VALUES(2,3,'随机信号分析（9-16周）','翠四教209')");
		db.execSQL("INSERT INTO classes VALUES(2,4,'大学物理实验A（5-16周）','不占用教室')");
		db.execSQL("INSERT INTO classes VALUES(2,5,'晚自习','晚自习')");
		db.execSQL("INSERT INTO classes VALUES(3,1,'数据库（2-8周）','翠四教112')");
		db.execSQL("INSERT INTO classes VALUES(3,2,'电磁场与电磁波（1-16周）','翠七教305')");
		db.execSQL("INSERT INTO classes VALUES(3,3,'0','0')");
		db.execSQL("INSERT INTO classes VALUES(3,4,'形式与政策（10-16双周）','翠五教101')");
		db.execSQL("INSERT INTO classes VALUES(3,5,'晚自习','晚自习')");
		db.execSQL("INSERT INTO classes VALUES(4,1,'模拟电子线路A（1-12周）','翠三教306')");
		db.execSQL("INSERT INTO classes VALUES(4,2,'数字逻辑电路B（5-16周）','翠三教306')");
		db.execSQL("INSERT INTO classes VALUES(4,3,'大学英语（6-16双周）','二号机房')");
		db.execSQL("INSERT INTO classes VALUES(4,4,'毛概（1-4周）','翠一教102')");
		db.execSQL("INSERT INTO classes VALUES(4,5,'晚自习','晚自习')");
		db.execSQL("INSERT INTO classes VALUES(5,1,'随机信号分析（9-16周）','翠四教209')");
		db.execSQL("INSERT INTO classes VALUES(5,2,'数据库（2-8周）','翠四教112')");
		db.execSQL("INSERT INTO classes VALUES(5,3,'0','0')");
		db.execSQL("INSERT INTO classes VALUES(5,4,'0','0')");
		db.execSQL("INSERT INTO classes VALUES(5,5,'0','0')");
		db.execSQL("INSERT INTO classes VALUES(6,1,'0','0')");
		db.execSQL("INSERT INTO classes VALUES(6,2,'0','0')");
		db.execSQL("INSERT INTO classes VALUES(6,3,'0','0')");
		db.execSQL("INSERT INTO classes VALUES(6,4,'0','0')");
		db.execSQL("INSERT INTO classes VALUES(6,5,'0','0')");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS" + DATABASE_NAME);
		onCreate(db);
	}

}
