package com.dong.schedule;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnTouchListener, OnGestureListener{
	
	DAOImp daoImp;
	int  today = (Calendar.getInstance().get(Calendar.DAY_OF_WEEK)+6)%7;
	String week[] = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
	GestureDetector mGestureDetector;
	private TextView textView;
	private ListView listView;
	LinearLayout linearLayout;
	private String[] mStrings = {"1","2","3","4","5","6","7","8","9","10","11"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mGestureDetector = new GestureDetector((OnGestureListener) this);
		
		TableLayout viewSnsLayout = (TableLayout)findViewById(R.id.TableLayout);
		linearLayout = (LinearLayout) findViewById(R.id.LinearLayout1);
		
		//读取SharedPreference 设置背景
		SharedPreferences sp = getSharedPreferences("com.dong.schedule_preferences", MODE_PRIVATE);
		int bg = Integer.parseInt(sp.getString("bg", "2"));
		switch (bg) {
		case 1:
			linearLayout.setBackgroundResource(R.drawable.bg1);
			break;
			
		case 2:
			linearLayout.setBackgroundResource(R.drawable.bg2);
			break;
			
		case 3:
			linearLayout.setBackgroundResource(R.drawable.bg3);
			break;
		
		case 4:
			linearLayout.setBackgroundResource(R.drawable.bg4);
			break;
			
		case 5:
			linearLayout.setBackgroundResource(R.drawable.bg5);
			break;

		default:
			break;
		}
		
        viewSnsLayout.setOnTouchListener(this);    
        viewSnsLayout.setLongClickable(true);
        
		daoImp = new DAOImp(this);
		
		ImageButton btnleft = (ImageButton) findViewById(R.id.imageButton1);
		ImageButton btnright = (ImageButton) findViewById(R.id.imageButton2);
		textView = (TextView) findViewById(R.id.TextView1);
		listView = (ListView) findViewById(R.id.NoteList);
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
		
		
		//NoteList
		List<Map<String, Object>> items = daoImp.getList();
		SimpleAdapter adapter = new SimpleAdapter(this,
				(List<Map<String, Object>>) items, R.layout.list_item,
				new String[] {"TITLE", "TIMELABLE" }, new int[] {
						R.id.title, R.id.timeLable});
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				if(daoImp!=null){daoImp.close();}
				MainActivity.this.finish();
				editNote(arg2);
			}
		});
		
		//长按
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				List<NoteBean> notes = daoImp.getAll();
				delNote(notes.get(arg2).id);
				return true;
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
        case R.id.action_new:
        {
//        	Intent intent = new Intent(this, NoteActivity.class);
//        	intent.putExtra("flag", -1);
//			if(daoImp!=null){daoImp.close();}
//			MainActivity.this.finish();
//        	startActivityForResult(intent,1);
			
			daoImp.createNote();
			Intent intent = new Intent(this, NoteActivity.class);
			intent.putExtra("flag", 0);
			if(daoImp!=null){daoImp.close();}
			MainActivity.this.finish();
			startActivity(intent);
        }
            break;
            
        //编辑
        case R.id.action_edit:
        {
        	MainActivity.this.finish();
        	startActivity(new Intent (MainActivity.this, Edit.class));  
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
	
	
	public void editNote(int flag){
		Intent intent = new Intent(this, NoteActivity.class);
    	intent.putExtra("flag", flag);
    	startActivityForResult(intent,1);
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
	
	public void delNote(final int id){
		new AlertDialog.Builder(this).setIcon(R.drawable.ic_launcher).setMessage("确认删除").setPositiveButton("确定",
    			new DialogInterface.OnClickListener() {
    		
					public void onClick(DialogInterface dialog, int which) {
						daoImp.deleteNote(id);
						dialog.dismiss();
						Intent intent = new Intent(MainActivity.this, MainActivity.class);
						MainActivity.this.finish();
						startActivity(intent);
					}
				}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
						
					}
				}).show();
	}
}
