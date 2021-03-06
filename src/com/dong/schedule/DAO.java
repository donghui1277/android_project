package com.dong.schedule;

import java.util.List;
import java.util.Map;

public interface DAO {
	public List<ClassBean> query(int day);
	public void updateName(int no, int day, String st1);
	public void updateAddress(int no, int day, String st2);
	public void createNote();
	public void deleteNote(int id);
	public void saveNote(String modify, int id);
	public List<NoteBean> getAll();
	public List<Map<String, Object>> getList();
}
