package com.dong.schedule;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	DAOImp daoImp;
	int  today = (Calendar.getInstance().get(Calendar.DAY_OF_WEEK)+6)%7;
	String week[] = {"������","����һ","���ڶ�","������","������","������","������"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		daoImp = new DAOImp(this);
		
		ImageButton btnleft = (ImageButton) findViewById(R.id.imageButton1);
		ImageButton btnright = (ImageButton) findViewById(R.id.imageButton2);
		final TextView textView = (TextView) findViewById(R.id.TextView1);
		final TextView textView2 = (TextView) findViewById(R.id.Week);
		textView2.setText(Calendar.getInstance().get(Calendar.WEEK_OF_YEAR)-8+"");
		updateView(today);
		textView.setText(week[today]);
		
		//Imagebutton
		btnleft.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				today = (today+6)%7;
				updateView(today);
				textView.setText(week[today]);
			}
		});
		
		//ImageButton
		btnright.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				today = (today+1)%7;
				updateView(today);
				textView.setText(week[today]);
			}
		});
	}
	
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		updateView(today);
	}


	protected void onDestroy() {  
        super.onDestroy();  
        //Ӧ�õ����һ��Activity�ر�ʱӦ�ͷ�DB  
        daoImp.close(); 
    }

	//���ɲ˵�
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	//�˵���Ӧ�¼�
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		//����
        switch (item.getItemId()) {
        case R.id.action_about:
        	new AlertDialog.Builder(this).setIcon(R.drawable.ic_launcher).setMessage("Nothing").setPositiveButton("ȷ��",
        			new DialogInterface.OnClickListener() {
        		
						public void onClick(DialogInterface dialog, int which) {
							
							dialog.dismiss();
							
						}
					}).show();
            break;
            
        //�༭
        case R.id.action_edit:
        {
        	Intent intent = new Intent(this, Edit.class);
        	startActivity(intent);
        }
            break;
            
        //�˳�
        case R.id.action_exit:
        	new AlertDialog.Builder(this)
			.setCancelable(false)
			.setMessage("��ȷ��Ҫ�˳�������")
			.setPositiveButton("ȷ��",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,	int which) {
							MainActivity.this.finish();
						}
					})
			.setNegativeButton("ȡ��",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,	int which) {
							dialog.dismiss();
						}
					}).show();
            break;
        }

        return false;
	}
	
	//������ͼ
	public void updateView(int day){
		
		TextView tv[] = {
		(TextView) findViewById(R.id.TextView11),
		(TextView) findViewById(R.id.TextView12),
		(TextView) findViewById(R.id.TextView21),
		(TextView) findViewById(R.id.TextView22),
		(TextView) findViewById(R.id.TextView31),
		(TextView) findViewById(R.id.TextView32),
		(TextView) findViewById(R.id.TextView41),
		(TextView) findViewById(R.id.TextView42),
		(TextView) findViewById(R.id.TextView51),
		(TextView) findViewById(R.id.TextView52)
		};
		
		List<ClassBean> classes = daoImp.query(day);
		System.out.println(classes.get(1).address);
		for (int i = 0; i < classes.size(); i++) {
			tv[i*2].setText(classes.get(i).name);
			tv[i*2+1].setText(classes.get(i).address);
		}
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
