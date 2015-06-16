package com.atayun.hgs.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.sdk.android.oss.OSSService;
import com.alibaba.sdk.android.oss.OSSServiceProvider;
import com.alibaba.sdk.android.oss.callback.GetBytesCallback;
import com.alibaba.sdk.android.oss.model.AccessControlList;
import com.alibaba.sdk.android.oss.model.ClientConfiguration;
import com.alibaba.sdk.android.oss.model.OSSException;
import com.alibaba.sdk.android.oss.model.TokenGenerator;
import com.alibaba.sdk.android.oss.storage.OSSBucket;
import com.alibaba.sdk.android.oss.storage.OSSData;
import com.alibaba.sdk.android.oss.util.OSSToolKit;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.atayun.hgs.adapter.CircleImageView;
import com.atayun.hgs.modle.GetAndUploadDataDemo;
import com.atayun.hgs.modle.GetAndUploadFileDemo;
import com.atayun.hgs.modle.IDApplication;
import com.atayun.hgs.url.ConnData;
import com.atayun.hgs.util.JsonObjectPostRequest;
/**
 * 车主个人中心
 */
public class CarPersonCenter extends Activity implements OnClickListener{
	int COMP;
	private static String TAG = "GetAndUploadDataDemo";
	private OSSBucket bucket;
	private Map<String, String> mMap;
	private String url = ConnData.changeCar2Cargo;
	boolean fla_dilog=true;
	
	private TextView PersonName;
	private TextView Person_identification;//认证
	private TextView PhoneNumber;
	private ImageView roundImageView;
	private LinearLayout personDetail;//头像，姓名的父容器LinearLayout
	private Button bt_OrderManage;//订单管理
	private Button bt_MyClient;//我的用户
	private Button bt_InfoToast;//消息提醒
	private Button bt_back;//返回键
	private Button carowner_manage;//更换身份按钮
    private String userPicurl="";//头像保存到数据库的文件名
	private String name="王老吉";//输入姓名
//	private String identify="(已认证)";
	private String Number="15384758393";
	private String flagID="0";
	private String userId="12";
	private int screenwidth, screenheight;
	private Bitmap bit_tou;
	/*********************图片下载*****************/
	static final String accessKey = "TQkMXE7vtrAtQ1y6"; // 测试代码没有考虑AK/SK的安全性
	static final String screctKey = "pV4X450T8ArDhYCDf8wIs4lK51Shcx";

	public static OSSService ossService = OSSServiceProvider.getService();
	
	private Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg){
			if(msg.what==COMP){
				roundImageView.setImageBitmap(bit_tou);
			}
		}
	};
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_carperson_center);
		// 用于得到屏幕的宽高
				DisplayMetrics dm = new DisplayMetrics();
				getWindowManager().getDefaultDisplay().getMetrics(dm);
				screenwidth = dm.widthPixels;
				screenheight = dm.heightPixels;

//		flagID=((IDApplication)getApplication()).getFlagID();  
//		Log.d("carflagID", flagID);
//		userId=((IDApplication)getApplication()).getUserId();
		initView();
		PersonName.setText(name);//设置名字
//		Person_identification.setText(identify);//设置，就会显示已认证
		PhoneNumber.setText(Number);
		roundImageView.setImageResource(R.drawable.head_pic);//设置头像图片
		 uploadinit();
		 asyncGetData("portraitImages/MT2015061459070.jpg");
		
	
		
		 
//		 new  GetAndUploadDataDemo.show("portraitImages/MT2015061459070.jpg");
	}
	private void initView() {
		personDetail=(LinearLayout) findViewById(R.id.person_detail);
		PersonName=(TextView) findViewById(R.id.person_name);
		Person_identification=(TextView) findViewById(R.id.person_isentification);
		PhoneNumber=(TextView) findViewById(R.id.person_phonenumber);
		bt_OrderManage=(Button) findViewById(R.id.order_manage);
		bt_MyClient=(Button) findViewById(R.id.My_client);
		bt_InfoToast=(Button) findViewById(R.id.info_toast);
		bt_back=(Button) findViewById(R.id.back);
		carowner_manage=(Button) findViewById(R.id.carowner_manage);

		
		roundImageView=(ImageView) findViewById(R.id.roundImageView);
		roundImageView.setOnClickListener(this);
		carowner_manage.setOnClickListener(this);
		bt_back.setOnClickListener(this);
	}
	
/********************************图片下载初始化**********************************************/
	
	private void uploadinit(){
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);

		// 初始化设置
		ossService.setApplicationContext(this.getApplicationContext());
		ossService.setGlobalDefaultTokenGenerator(new TokenGenerator() { // 设置全局默认加签器
			@Override
			public String generateToken(String httpMethod, String md5, String type, String date,
					String ossHeaders, String resource) {

				String content = httpMethod + "\n" + md5 + "\n" + type + "\n" + date + "\n" + ossHeaders
						+ resource;

				return OSSToolKit.generateToken(accessKey, screctKey, content);
			}
			
		}
		
				
				);
		ossService.setGlobalDefaultHostId("oss-cn-hangzhou.aliyuncs.com");
		ossService.setCustomStandardTimeWithEpochSec(System.currentTimeMillis() / 1000);
		ossService.setGlobalDefaultACL(AccessControlList.PUBLIC_READ); // 默认为private

		ClientConfiguration conf = new ClientConfiguration();
		conf.setConnectTimeout(15 * 1000); // 设置全局网络连接超时时间，默认30s
		conf.setSocketTimeout(15 * 1000); // 设置全局socket超时时间，默认30s
		conf.setMaxConnections(50); // 设置全局最大并发网络链接数, 默认50
		ossService.setClientConfiguration(conf);
		
	}
	/********************************************************************************/
	//头像下载
	private void setimag(Bitmap bm){
//		roundImageView=(ImageView) findViewById(R.id.roundImageView);
		roundImageView.setImageBitmap(bm);
//		roundImageView.setOnClickListener(this);
	}
	
	
	// 异步获取数据
	public void asyncGetData(String object) {
		
		ossService = OSSServiceProvider.getService();
		bucket = ossService.getOssBucket("hgs"); // 替换为你的bucketName
		OSSData data = ossService.getOssData(bucket, object);
		// 同样也可以设置范围
		// data.setRange(10, Range.INFINITE);
		data.getInBackground(new GetBytesCallback() {
			
			@Override
			public void onSuccess(String objectKey, byte[] data) {
				Log.d("aaaa", "下载图片成功");
				 byte[] a=data;
	          final Bitmap bm=Bytes2Bimap(a);
	          
	          //setimag(bm);
	          Runnable ra = new Runnable() {
				public void run() {
					Log.i("startSetImage", "start");
					
					bit_tou=bm;
					Log.i("stopSetImage", "stop");
					Message msg=new Message();
					msg.what=COMP;
					handler.sendMessage(msg);
				}
	          };
	          Thread thread = new Thread(ra);
	          thread.start();
	    
	          
	          
	          File f = new File("/sdcard/atayun/", "dfdfdf3.jpg");//保存地址，保存的文件名
	          
	  		if (f.exists()) {
	  			f.delete();
	  		}
	  		try {
	  			FileOutputStream out = new FileOutputStream(f);
	  			bm.compress(Bitmap.CompressFormat.JPEG, 30, out);
	  			out.flush();
	  			out.close();
	  			
	  			Log.d("TAG", "已经保存");
	  		} catch (FileNotFoundException e) {
	  			// TODO Auto-generated catch block
	  			e.printStackTrace();
	  		} catch (IOException e) {
	  			// TODO Auto-generated catch block
	  			e.printStackTrace();
	  		}
	          
	          
//	          b.createBitmap(screenwidth, screenheight, null);
//	          roundImageView.setImageBitmap(b);
				Log.d(TAG, "[onSuccess] - " + objectKey + " length: " + data.length);
			}

			@Override
			public void onProgress(String objectKey, int byteCount, int totalSize) {
				Log.d(TAG, "[onProgress] - current download: " + objectKey + " bytes:" + byteCount + " in total:" + totalSize);
			}

			@Override
			public void onFailure(String objectKey, OSSException ossException) {
				Log.e(TAG, "[onFailure] - download " + objectKey + " failed!\n" + ossException.toString());
				
			}
		});
		
	
		
	}
	
	
	
	
	public void startDemo() {//调用图片上传方法
		//从对应文件夹下载，下载到对应文件夹
		new GetAndUploadDataDemo().show("portraitImages/MT2015061459070.jpg");//第一个参数代表启动show里面对应哪个方法

		
	}
	
	
	//将byte[]转换成Bitmap
	 public Bitmap Bytes2Bimap(byte[] b) {
         if (b.length != 0) {
             return BitmapFactory.decodeByteArray(b, 0, b.length);
         } else {
             return null;
         }
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
		
		

		
	private void turn_togoods(){
		String cargoFlag=((IDApplication)getApplication()).getCargoFlag();
		String companyFlag=((IDApplication)getApplication()).getCompanyFlag();
		mMap = new HashMap<String, String>();
		mMap.put("method", "changeCar2Cargo");
		mMap.put("userId", userId);
		mMap.put("cargoFlag", cargoFlag);
		mMap.put("companyFlag", companyFlag);
		
		// 请求服务
				RequestQueue requestQueue = Volley.newRequestQueue(this);
				JsonObjectPostRequest jsonObjectPostRequest = new JsonObjectPostRequest(
						url, new Response.Listener<JSONObject>() {

							@Override
							public void onResponse(JSONObject response) {
								try {

									String chageState = response
											.getString("chageState");

									switch (chageState) {
									case "200":
										Toast.makeText(CarPersonCenter.this, "身份变更成功！",
												Toast.LENGTH_SHORT).show();
			                        String cargoFlag=response.getString("cargoFlag");
			                        String carUserFlag=response.getString("carUserFlag");
			                        String companyFlag=response.getString("companyFlag");
			                        Log.d("cargoFlag", cargoFlag);
			                        Log.d("carUserFlag", carUserFlag);
			                        Log.d("companyFlag", companyFlag);
			                        
			                        ((IDApplication)getApplication()).setCargoFlag(cargoFlag);
			                        ((IDApplication)getApplication()).setCarUserFlag(carUserFlag);
			                        ((IDApplication)getApplication()).setCompanyFlag(companyFlag);
			            			((IDApplication)getApplication()).setFlagID("1");
//			            			Intent intent=new Intent(CarPersonCenter.this,GoodsPersonCenter.class);
//			            			finish();
//			            			startActivity(intent);
										
									
											break;

									case "10":
										Toast.makeText(CarPersonCenter.this, "身份变更失败！",
												Toast.LENGTH_SHORT).show();
										break;
									
									
									default:
										break;
									}

								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							}
						}, new Response.ErrorListener() {

							@Override
							public void onErrorResponse(VolleyError error) {
								Toast.makeText(CarPersonCenter.this, "获取失败，请检查网络",
										Toast.LENGTH_SHORT).show();

							}
						}, mMap);
				requestQueue.add(jsonObjectPostRequest);
		
	
	}
	
	
	
	 @Override  
	    public boolean onKeyDown(int keyCode, KeyEvent event)  
	    {  
	        if (keyCode == KeyEvent.KEYCODE_BACK )  
	        {  
	           //当back键按下，返回主界面
	        	
	        	Intent intent2=new Intent(CarPersonCenter.this,PageFoot.class);
				finish();
				startActivity(intent2);
	        }  
	          
	        return false;  
	          
	    }  
	
	 private void distoryDialog(DialogInterface dialog){  //关闭弹幕的方法
	        try {  
	            Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");  
	            field.setAccessible(true);  
	            field.set(dialog, false);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }   
	    }  
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.carowner_manage:
			
		
			CreateDialog();

			
			break;
		case R.id.back:
			Intent intent2=new Intent(CarPersonCenter.this,PageFoot.class);
			finish();
			startActivity(intent2);
			
			break;
		case R.id.yes_toturn:
			turn_togoods();
			break;
		case R.id.no_toturn:
//			fla_dilog=false;
//			CreateDialog(fla_dilog);
//			CreateDialog().cancel();
			distoryDialog(CreateDialog());
			//设置弹幕消失
			break;
		case R.id.roundImageView://点击了头像按钮
			//跳转到图片上传页面
			Intent intent = new Intent(CarPersonCenter.this, UpLoadPictureActivity.class);
			intent.putExtra("OBject", "portraitImages");// 将头像保存到的头像文件夹名传过去
			startActivityForResult(intent, 1);
			
			
			break;
	}
}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {

		case 1:
			
			userPicurl = data.getStringExtra("picname");//头像的保存到数据库的文件名
			String urlpic = data.getStringExtra("picurl");
			Log.d("用户头像", userPicurl);
			if(!(userPicurl.equals(""))){//如果头像上传成功，将其显示出来
				Bitmap bm=UpLoadPictureActivity.bm;
				roundImageView.setImageBitmap(bm);
			}
			

			break;
		}
	}
	
	
}
