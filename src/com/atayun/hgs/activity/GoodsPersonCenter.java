package com.atayun.hgs.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atayun.hgs.adapter.CircleImageView;
import com.atayun.hgs.modle.IDApplication;
/**
 *货主 个人中心
 */
public class GoodsPersonCenter extends Activity implements OnClickListener{
	private TextView PersonName;
	private TextView Person_identification;//认证
	private TextView PhoneNumber;
	private CircleImageView roundImageView;
	private LinearLayout personDetail;//头像，姓名的父容器LinearLayout
	private Button bt_OrderManage;//订单管理
	private Button goods_manage;//货物管理
	private Button bt_MyClient;//我的用户
	private Button bt_InfoToast;//消息提醒
	private Button bt_back;//返回键

	private Button goodsowner_manage;//货主按钮，实现身份的转换

	private String flagID;
	private String userId;
	private String name="";//输入姓名
//	private String identify="(已认证)";
	private String Number="";
	private int screenwidth, screenheight;
//	public static  ArrayList<GoodsInfoItems> goodsInfoItems = new ArrayList<GoodsInfoItems>();
//		private Map<String, String> mMap;// 用于封装 完成注册提交的参数
//		private String url="http://121.40.50.7:8080/wuLiuServer/cargoInfo.do";
		
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.goodsperson_center);
		// 用于得到屏幕的宽高
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		screenwidth = dm.widthPixels;
		screenheight = dm.heightPixels;

//		flagID=((IDApplication)getApplication()).getFlagID();  
//		Log.d("GoodsPersonCenter-goodsflagID", flagID);
//		userId=((IDApplication)getApplication()).getUserId();
//		
//		Log.d("GoodsPersonCenter-userId",userId);
		initView();
		PersonName.setText(name);//设置名字
//		Person_identification.setText(identify);//设置，就会显示已认证
		PhoneNumber.setText(Number);
		roundImageView.setImageResource(R.drawable.head_pic);//设置头像图片
	}
	private void initView() {
		personDetail=(LinearLayout) findViewById(R.id.person_detail);
		PersonName=(TextView) findViewById(R.id.person_name);
		Person_identification=(TextView) findViewById(R.id.person_isentification);
		PhoneNumber=(TextView) findViewById(R.id.person_phonenumber);
		bt_OrderManage=(Button) findViewById(R.id.order_manage);
		goods_manage=(Button) findViewById(R.id.goods_manage);
		bt_MyClient=(Button) findViewById(R.id.My_client);
		bt_InfoToast=(Button) findViewById(R.id.info_toast);
		bt_back=(Button) findViewById(R.id.back);
	
		goodsowner_manage=(Button) findViewById(R.id.goodsowner_manage);
		roundImageView=(CircleImageView) findViewById(R.id.roundImageView);
		
		goodsowner_manage.setOnClickListener(this);
		goods_manage.setOnClickListener(this);
		bt_back.setOnClickListener(this);
		bt_OrderManage.setOnClickListener(this);
	}
	//监听手机back键
	

	 @Override  
	    public boolean onKeyDown(int keyCode, KeyEvent event)  
	    {  
	        if (keyCode == KeyEvent.KEYCODE_BACK )  
	        {  
	           //当back键按下，返回主界面
	        	
	        	Intent intent2=new Intent(GoodsPersonCenter.this,PageFoot.class);
				finish();
				startActivity(intent2);
	        }  
	          
	        return false;  
	          
	    }  
	 
	
	 private Dialog CreateDialog() {//创建弹幕
			
			// 将xml文件实例化为View对象
		     Dialog dialog = new Dialog(this);
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialog.setContentView(R.layout.carcenter_dilog);
				dialog.setTitle(null);
				Window dialWindow = dialog.getWindow();
				WindowManager.LayoutParams lp = dialWindow.getAttributes();
				dialWindow.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP);
				lp.y = (int) (screenheight * 1 / 4);
				lp.width = (int) (screenwidth * 3 / 4);
				lp.alpha = 0.6f;// 透明度
				dialWindow.setAttributes(lp);
				dialog.show();
				
				Button no_toturn = (Button) dialog.findViewById(R.id.no_toturn);
				Button yes_toturn = (Button) dialog.findViewById(R.id.yes_toturn);
				no_toturn.setOnClickListener(this);
				yes_toturn.setOnClickListener(this);
				return dialog;

			}
	 
	 
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.goodsowner_manage:
			((IDApplication)getApplication()).setFlagID("0");
			Intent intent=new Intent(GoodsPersonCenter.this,CarPersonCenter.class);
			finish();
			startActivity(intent);
		
			break;
		case R.id.back:
			Intent intent2=new Intent(GoodsPersonCenter.this,PageFoot.class);
			finish();
			startActivity(intent2);
			
			break;
		case R.id.goods_manage:
//			Task  task = new Task();
//			  task.execute();

//			Intent intent3=new Intent(GoodsPersonCenter.this,GoodsManagerActivity.class);
//			finish();
//			startActivity(intent3);
			Intent intent3=new Intent(GoodsPersonCenter.this,PoiSearchDemo.class);
			finish();
			startActivity(intent3);
			
			break;
			
		case R.id.order_manage:
			Intent intent4=new  Intent(GoodsPersonCenter.this,OrderManager.class);
			finish();
			startActivity(intent4);
			
			break;
			
		}
		
	}
}
