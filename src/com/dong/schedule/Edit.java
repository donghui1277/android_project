package com.dong.schedule;

import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.PreferenceChangeListener;

import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.widget.Toast;


public class Edit extends PreferenceActivity implements OnPreferenceChangeListener{

	private EditTextPreference name_editPreference;
	private EditTextPreference address_editPreference;
	DAOImp daoImp;
	SharedPreferences sp;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
		
		name_editPreference = (EditTextPreference) findPreference("name");
		address_editPreference = (EditTextPreference) findPreference("address");
		
		name_editPreference.setOnPreferenceChangeListener(this);
		address_editPreference.setOnPreferenceChangeListener(this);
		
		daoImp = new DAOImp(this);
		sp = PreferenceManager.getDefaultSharedPreferences(this);
	}

	@Override
	public boolean onPreferenceChange(Preference preference, Object newValue) {
		// TODO Auto-generated method stub
		if (preference==name_editPreference) {
			daoImp.updateName(Integer.parseInt(sp.getString("lesson", "0")), Integer.parseInt(sp.getString("day", "1")), newValue.toString());
			Toast.makeText(Edit.this, "课名修改成功", Toast.LENGTH_SHORT).show();
			return true;
		}else if (preference==address_editPreference) {
			daoImp.updateAddress(Integer.parseInt(sp.getString("lesson", "0")), Integer.parseInt(sp.getString("day", "1")), newValue.toString());
			Toast.makeText(Edit.this, "课名修改成功", Toast.LENGTH_SHORT).show();
			return true;
		}
		return false;
	}


	public boolean onKeyDown(int keyCode, KeyEvent event){
		 if(keyCode == KeyEvent.KEYCODE_BACK){
			 daoImp.close();
			 finish();
			 return true;
		 }
		return false;
	}
	

}
