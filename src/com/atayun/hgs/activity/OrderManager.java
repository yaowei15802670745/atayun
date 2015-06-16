package com.atayun.hgs.activity;

import com.atayun.hgs.modle.IDApplication;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class OrderManager extends TabActivity implements OnCheckedChangeListener{
	private RadioGroup radiogroup;
	private TabHost tab;
	private String userId="12";
	private Button bt_back;
	private Button bt_add;
	private RadioButton om_wait_handle;
	private RadioButton om_unfinished;
	private RadioButton om_has_finished;
	private RadioButton om_cancel;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.order_manager);
//		userId=((IDApplication) getApplication()).getUserId();
		initView();
	}

	private void initView() {
		bt_add=(Button) findViewById(R.id.om_add);
		bt_back=(Button) findViewById(R.id.om_back);
		
		om_wait_handle=(RadioButton) findViewById(R.id.om_wait_handle);
		om_unfinished=(RadioButton) findViewById(R.id.om_unfinished);
		om_has_finished=(RadioButton) findViewById(R.id.om_has_finished);
		om_cancel=(RadioButton) findViewById(R.id.om_cancel);
	
		tab=(TabHost)findViewById(android.R.id.tabhost);
		tab.setFocusable(true);
		
		TabHost.TabSpec tabspec=tab.newTabSpec("1");
		Intent intent=new Intent(OrderManager.this,WaitHandle.class);
		tabspec.setIndicator("待处理").setContent(intent);
		tab.setup(this.getLocalActivityManager());
		tab.addTab(tabspec);
		
		TabHost.TabSpec tabspec2=tab.newTabSpec("2");
		Intent intent2=new Intent(OrderManager.this,WaitHandle.class);
		tabspec2.setIndicator("未完成").setContent(intent2);
		tab.addTab(tabspec2);
		
		TabHost.TabSpec tabspec3=tab.newTabSpec("3");
		Intent intent3=new Intent(OrderManager.this,WaitHandle.class);
		tabspec3.setIndicator("已完成").setContent(intent3);
		tab.addTab(tabspec3);
		
		TabHost.TabSpec tabspec4=tab.newTabSpec("4");
		Intent intent4=new Intent(OrderManager.this,WaitHandle.class);
		tabspec4.setIndicator("已取消").setContent(intent4);
		tab.addTab(tabspec4);
		
		tab.setCurrentTab(0);
		
		radiogroup=(RadioGroup)findViewById(R.id.caidan);
		radiogroup.setOnCheckedChangeListener(this);
	}
	public void onCheckedChanged(RadioGroup arg0, int arg1) {
		switch (arg1) {
		case R.id.om_wait_handle:
			tab.setCurrentTab(0);
			
			break;
		case R.id.om_unfinished:
			tab.setCurrentTab(1);
			
			break;	
		case R.id.om_has_finished:
			tab.setCurrentTab(3);
			
			break;
		case R.id.om_cancel:
			tab.setCurrentTab(4);
			
			break;	
		}
	}

}
