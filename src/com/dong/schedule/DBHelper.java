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
		db.execSQL("INSERT INTO classes VALUES(1,1,'��ų����Ų���1-16�ܣ�','���߽�305')");
		db.execSQL("INSERT INTO classes VALUES(1,2,'��ѧӢ�3-16�ܣ�','��Ž�403')");
		db.execSQL("INSERT INTO classes VALUES(1,3,'ë�ţ�1-8�ܣ�','��һ��102')");
		db.execSQL("INSERT INTO classes VALUES(1,4,'����ר�5-16�ܣ�','�ٳ�')");
		db.execSQL("INSERT INTO classes VALUES(1,5,'����ϰ','����ϰ')");
		db.execSQL("INSERT INTO classes VALUES(2,1,'�����߼���·B��5-16�ܣ�','������306')");
		db.execSQL("INSERT INTO classes VALUES(2,2,'ģ�������·A��1-16�ܣ�','������306')");
		db.execSQL("INSERT INTO classes VALUES(2,3,'����źŷ�����9-16�ܣ�','���Ľ�209')");
		db.execSQL("INSERT INTO classes VALUES(2,4,'��ѧ����ʵ��A��5-16�ܣ�','��ռ�ý���')");
		db.execSQL("INSERT INTO classes VALUES(2,5,'����ϰ','����ϰ')");
		db.execSQL("INSERT INTO classes VALUES(3,1,'���ݿ⣨2-8�ܣ�','���Ľ�112')");
		db.execSQL("INSERT INTO classes VALUES(3,2,'��ų����Ų���1-16�ܣ�','���߽�305')");
		db.execSQL("INSERT INTO classes VALUES(3,3,'0','0')");
		db.execSQL("INSERT INTO classes VALUES(3,4,'��ʽ�����ߣ�10-16˫�ܣ�','�����101')");
		db.execSQL("INSERT INTO classes VALUES(3,5,'����ϰ','����ϰ')");
		db.execSQL("INSERT INTO classes VALUES(4,1,'ģ�������·A��1-12�ܣ�','������306')");
		db.execSQL("INSERT INTO classes VALUES(4,2,'�����߼���·B��5-16�ܣ�','������306')");
		db.execSQL("INSERT INTO classes VALUES(4,3,'��ѧӢ�6-16˫�ܣ�','���Ż���')");
		db.execSQL("INSERT INTO classes VALUES(4,4,'ë�ţ�1-4�ܣ�','��һ��102')");
		db.execSQL("INSERT INTO classes VALUES(4,5,'����ϰ','����ϰ')");
		db.execSQL("INSERT INTO classes VALUES(5,1,'����źŷ�����9-16�ܣ�','���Ľ�209')");
		db.execSQL("INSERT INTO classes VALUES(5,2,'���ݿ⣨2-8�ܣ�','���Ľ�112')");
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
