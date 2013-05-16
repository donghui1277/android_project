package com.dong.schedule;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		classesCursor.close();
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
	@Override
	public void createNote() {
		// TODO Auto-generated method stub
		db.execSQL("insert into notes (bg_color_id) values (0)");
//		Cursor  notesCursor = db.rawQuery("select id from notes order by id desc", null);
//		int id  = notesCursor.getInt(notesCursor.getColumnIndex("id"));
//		saveNote(note, id);
	}
	@Override
	public void saveNote(String modify, int id) {
		// TODO Auto-generated method stub
		Date date=new Date();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String time=df.format(date);
		db.execSQL("update notes set content = '"+modify+"',modified_date = '"+time+"' where id="+id+"" );
	}
	@Override
	public List<NoteBean> getAll() {
		// TODO Auto-generated method stub
		ArrayList<NoteBean> notes = new ArrayList<NoteBean>();
		Cursor  notesCursor = db.rawQuery("select * from notes order by id desc", null);
		
		while (notesCursor.moveToNext()) {
			NoteBean noteBean = new NoteBean();  
			noteBean.id = notesCursor.getInt(notesCursor.getColumnIndex("id"));  
			noteBean.bg_color_id = notesCursor.getInt(notesCursor.getColumnIndex("bg_color_id"));  
            noteBean.created_date = notesCursor.getString(notesCursor.getColumnIndex("created_date"));
            noteBean.modified_date = notesCursor.getString(notesCursor.getColumnIndex("modified_date"));   
            noteBean.content = notesCursor.getString(notesCursor.getColumnIndex("content"));
            notes.add(noteBean);
		}
		notesCursor.close();
		return notes;
	}
	@Override
	public List<Map<String, Object>> getList() {
		// TODO Auto-generated method stub
		ArrayList<Map<String, Object>> item = new ArrayList<Map<String, Object>>();
		Cursor  listCursor = db.rawQuery("select * from notes order by id desc", null);
		
		while (listCursor.moveToNext()) {
			Map<String, Object> map = new HashMap<String, Object>();
			String title = listCursor.getString(listCursor.getColumnIndex("content")).length()>8?
					listCursor.getString(listCursor.getColumnIndex("content")).substring(0, 6)+"...":
						listCursor.getString(listCursor.getColumnIndex("content"));
			map.put("TITLE", title);
			map.put("TIMELABLE", listCursor.getString(listCursor.getColumnIndex("modified_date")).substring(0, 10));
            item.add(map);
		}
		listCursor.close();
		return item;
	}
	@Override
	public void deleteNote(int id) {
		// TODO Auto-generated method stub
		db.execSQL("delete from notes where id = "+id+"");
	}

}
