package com.dong.schedule;

import java.util.List;

public interface DAO {
	public List<ClassBean> query(int day);
	public void updateName(int no, int day, String st1);
	public void updateAddress(int no, int day, String st2);
}
