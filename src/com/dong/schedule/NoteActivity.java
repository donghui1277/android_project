package com.dong.schedule;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NoteActivity extends Activity{

	DAOImp daoImp;
	private TextView time;
	private EditText editText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.note);
		
		time = (TextView) findViewById(R.id.Time);
		editText = (EditText) findViewById(R.id.Note);
		
		daoImp = new DAOImp(this);
		final Bundle bundle = getIntent().getExtras();
		final List<NoteBean> notes = daoImp.getAll();
		if(bundle!=null){
			if (bundle.getInt("flag")>=0){
				time.setText(notes.get(bundle.getInt("flag")).modified_date);
				editText.setText(notes.get(bundle.getInt("flag")).content);
			}
		}
		
		editText.addTextChangedListener(new TextWatcher() {
		    
		    @Override
		    public void onTextChanged(CharSequence s, int start, int before, int count) {

		    }
		    
		    @Override
		    public void beforeTextChanged(CharSequence s, int start, int count,
		            int after) {
		    }
		    
		    @Override
		    public void afterTextChanged(Editable s) {
//		    	if (bundle.getInt("flag")==-1){
//					daoImp.createNote(editText.getText().toString());
//				}
		        daoImp.saveNote(editText.getText().toString(),notes.get(bundle.getInt("flag")).id);
		    }
		});
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode == KeyEvent.KEYCODE_BACK){
			 daoImp.close();
			 finish();
			 startActivity(new Intent (NoteActivity.this, MainActivity.class)); 
			 return true;
		 }
		return false;
	}
}
