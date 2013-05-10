package com.dong.schedule;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements OnTouchListener, OnGestureListener{
	
	DAOImp daoImp;
	int  today = (Calendar.getInstance().get(Calendar.DAY_OF_WEEK)+6)%7;
	String week[] = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
	GestureDetector mGestureDetector;
	TextView textView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mGestureDetector = new GestureDetector((OnGestureListener) this);
		
		TableLayout viewSnsLayout = (TableLayout)findViewById(R.id.TableLayout);    
        viewSnsLayout.setOnTouchListener(this);    
        viewSnsLayout.setLongClickable(true);
        
		daoImp = new DAOImp(this);
		
		ImageButton btnleft = (ImageButton) findViewById(R.id.imageButton1);
		ImageButton btnright = (ImageButton) findViewById(R.id.imageButton2);
		textView = (TextView) findViewById(R.id.TextView1);
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
        //应用的最后一个Activity关闭时应释放DB  
        daoImp.close(); 
    }

	//生成菜单
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	//菜单响应事件
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		//关于
        switch (item.getItemId()) {
        case R.id.action_about:
        	new AlertDialog.Builder(this).setIcon(R.drawable.ic_launcher).setMessage("Nothing").setPositiveButton("确定",
        			new DialogInterface.OnClickListener() {
        		
						public void onClick(DialogInterface dialog, int which) {
							
							dialog.dismiss();
							
						}
					}).show();
            break;
            
        //编辑
        case R.id.action_edit:
        {
        	Intent intent = new Intent(this, Edit.class);
        	startActivity(intent);
        }
            break;
            
        //退出
        case R.id.action_exit:
        	new AlertDialog.Builder(this)
			.setCancelable(false)
			.setMessage("您确认要退出程序吗？")
			.setPositiveButton("确定",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,	int which) {
							MainActivity.this.finish();
						}
					})
			.setNegativeButton("取消",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,	int which) {
							dialog.dismiss();
						}
					}).show();
            break;
        }

        return false;
	}
	
	//更新视图
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


	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}


	private int verticalMinDistance = 20;  
	private int minVelocity         = 0; 
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		if (e1.getX() - e2.getX() > verticalMinDistance && Math.abs(velocityX) > minVelocity) {  
			  
			today = (today+1)%7;
			updateView(today);
			textView.setText(week[today]);
			
	    } else if (e2.getX() - e1.getX() > verticalMinDistance && Math.abs(velocityX) > minVelocity) {  

	    	today = (today+6)%7;
			updateView(today);
			textView.setText(week[today]);
	    } 
		return false;
	}


	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return mGestureDetector.onTouchEvent(event);
	}
}
