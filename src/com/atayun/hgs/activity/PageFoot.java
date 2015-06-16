package com.atayun.hgs.activity;

import java.util.Map;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.RadioButton;

import com.atayun.hgs.modle.IDApplication;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.RadioGroup.OnCheckedChangeListener;
/*
 * 页脚（车源发布   首页   货源发布）
 * 
 */
public class PageFoot extends TabActivity implements OnCheckedChangeListener{


	private String flagID="";
	private String userId="";

	
	private RadioGroup radiogroup;
	private TabHost tab;
	private String carUserFlag="";
	private String cargoFlag="";
	private String companyFlag="";
	private String userBaseFlag="";//userBaseFlag 个人基本信息（车主货主共用
	private RadioButton personcenter,HomeBtn,publish;

	@SuppressWarnings("deprecation")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.page_foot);
		flagID=((IDApplication)getApplication()).getFlagID(); 
		carUserFlag=((IDApplication)getApplication()).getCarUserFlag();
		cargoFlag=((IDApplication)getApplication()).getCargoFlag();
		companyFlag=((IDApplication)getApplication()).getCompanyFlag();
		userBaseFlag=((IDApplication)getApplication()).getUserBaseFlag();
		Log.d("主页flagID", flagID);
		Log.d("主页companyFlag", companyFlag);
		Log.d("主页cargoFlag", cargoFlag);
		Log.d("主页carUserFlag", carUserFlag);
		Log.d("主页userBaseFlag", userBaseFlag);

		userId=((IDApplication)getApplication()).getUserId();
		

		personcenter=(RadioButton) findViewById(R.id.button1);
		HomeBtn=(RadioButton) findViewById(R.id.button2);
		publish=(RadioButton) findViewById(R.id.button3);
//		carSource.setText("个人中心");
		tab=(TabHost)findViewById(android.R.id.tabhost);
		tab.setFocusable(true);
	
		if(flagID.equals("0")){//0表示车主
			
			publish.setText("发布车源");
			TabHost.TabSpec tabspec=tab.newTabSpec("1");
			Intent intent=new Intent(PageFoot.this,MessageCenterActivity.class); 
			
			 tabspec.setIndicator("查看订单").setContent(intent);
			tab.addTab(tabspec);
			
			TabHost.TabSpec tabspec2=tab.newTabSpec("2");
			Intent intent2=new Intent(PageFoot.this,HomePage.class);
			tabspec2.setIndicator("首页").setContent(intent2);
			tab.setup(this.getLocalActivityManager());
			tab.addTab(tabspec2);
			
			TabHost.TabSpec tabspec3=tab.newTabSpec("3");
//			Intent intent3=new Intent(PageFoot.this,CarSourceUI.class);
			if(carUserFlag.equals("1")||carUserFlag.equals("2")){		
				Intent intent3=new Intent(PageFoot.this,PublishCarActivity_Is.class);
				tabspec3.setIndicator("发布车源").setContent(intent3);
				tab.addTab(tabspec3);
			}else{
				Intent intent3=new Intent(PageFoot.this,PublishCarActivity_No.class);
				tabspec3.setIndicator("发布车源").setContent(intent3);
				tab.addTab(tabspec3);
			}
			
	
		}else if(flagID.equals("1")){
			
			publish.setText("发布货源");
			TabHost.TabSpec tabspec=tab.newTabSpec("1");
			Intent intent=new Intent(PageFoot.this,MessageCenterActivity.class); 
			
			tabspec.setIndicator("查看订单").setContent(intent);
			tab.addTab(tabspec);
			
			TabHost.TabSpec tabspec2=tab.newTabSpec("2");
			Intent intent2=new Intent(PageFoot.this,HomePage.class);
			tabspec2.setIndicator("首页").setContent(intent2);
			tab.setup(this.getLocalActivityManager());
			tab.addTab(tabspec2);
			
			TabHost.TabSpec tabspec3=tab.newTabSpec("3");//现在只做了已完善，已认证和未完善，未认证，没有正在审核环节
			if(!(userBaseFlag.equals("0"))||companyFlag.equals("1")||companyFlag.equals("2")){
				//货主个人基本信息已经完善
				Intent intent3=new Intent(PageFoot.this,PublishGoodsActivity_Is.class);//跳转到货源发布  
				tabspec3.setIndicator("发布货源").setContent(intent3);
				tab.addTab(tabspec3);	
				
			}else{//货主个人基本信息未完善
				Intent intent3=new Intent(PageFoot.this,PublishGoodsActivity_No.class);//跳转到货源发布  
				tabspec3.setIndicator("发布货源").setContent(intent3);
				tab.addTab(tabspec3);	
				
			}
			
			
		}
		
		//默认显示中间的
		tab.setCurrentTab(1);
		
		radiogroup=(RadioGroup)findViewById(R.id.caidan);
		radiogroup.setOnCheckedChangeListener(this);
	}
	
	
	
	
	
	
	

	public void onCheckedChanged(RadioGroup arg0, int arg1) {
		switch (arg1) {
		case R.id.button1:
			tab.setCurrentTab(0);
			break;
		case R.id.button2:
			tab.setCurrentTab(1);
			break;
		case R.id.button3:
			tab.setCurrentTab(2);
			break;		
		}
	}
}
