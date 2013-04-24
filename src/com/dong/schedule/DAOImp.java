package com.dong.schedule;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DAOImp implements DAO{

	private DBHelper helper;  
    private SQLiteDatabase db;
    
    public DAOImp(Context context) {
    	helper = new DBHelper(context);
    	db  = helper.getReadableDatabase();
    }
	@Override
	public List<ClassBean> query(int day) {
		// TODO Auto-generated method stub
		ArrayList<ClassBean> classes = new ArrayList<ClassBean>();
		Cursor  classesCursor = db.rawQuery("select * from classes where day = ?", new String[]{day+""});
		
		while (classesCursor.moveToNext()) {
			ClassBean classBean = new ClassBean();  
            classBean.day = classesCursor.getInt(classesCursor.getColumnIndex("day"));  
            classBean.no = classesCursor.getInt(classesCursor.getColumnIndex("no"));  
            classBean.name = classesCursor.getString(classesCursor.getColumnIndex("name"));  
            classBean.address = classesCursor.getString(classesCursor.getColumnIndex("address"));  
            classes.add(classBean);
		}
		return classes;
	}

	@Override
	public void updateName(int no, int day, String st1) {
		// TODO Auto-generated method stub
		db.execSQL("update classes set name = '"+st1+"' where no="+no+" and day="+day+"" );
	}

	@Override
	public void updateAddress(int no, int day, String st2) {
		// TODO Auto-generated method stub
		db.execSQL("update classes set address = '"+st2+"' where no="+no+" and day="+day+"" );
	}

	public void close(){
		db.close();
	}
}
