package com.atayun.hgs.activity;
/*
 * 上传图片的Activity
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.sdk.android.oss.OSSService;
import com.alibaba.sdk.android.oss.OSSServiceProvider;
import com.alibaba.sdk.android.oss.model.AccessControlList;
import com.alibaba.sdk.android.oss.model.ClientConfiguration;
import com.alibaba.sdk.android.oss.model.TokenGenerator;
import com.alibaba.sdk.android.oss.util.OSSToolKit;

import com.atayun.hgs.modle.GetAndUploadFileDemo;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.util.EventLog.Event;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class UpLoadPictureActivity extends Activity {
	private final String IMAGE_TYPE = "image/*";
    private static boolean flag=false;
	private final int IMAGE_CODE = 0; // 这里的IMAGE_CODE是自己任意定义的
	 private static boolean agd=true;
	private Button addPic = null, showPicPath = null;
    private String picurl="";
    private String picname="";
    private String object="";
    private String folder="";
	private ImageView imgShow = null;
	private TextView imgPath = null;
	private TextView textView1 = null;
	public static Bitmap bm = null;
	private int screenwidth, screenheight;
	/*********************图片上传*****************/
		static final String accessKey = "TQkMXE7vtrAtQ1y6"; // 测试代码没有考虑AK/SK的安全性
		static final String screctKey = "pV4X450T8ArDhYCDf8wIs4lK51Shcx";

		public static OSSService ossService = OSSServiceProvider.getService();
		//*******************************************************
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_picture);
		// 用于得到屏幕的宽高
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		screenwidth = dm.widthPixels;
		screenheight = dm.heightPixels;
		Intent intent=getIntent();
		folder=intent.getStringExtra("OBject");
		Log.d("上传到的文件夹是：", folder);
		
		init();
		uploadinit();
	}
/********************************图片上传初始化**********************************************/
	
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
	
	//异步类
		private class Task extends AsyncTask<Void, Void, Void> {

			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				textView1.setText("正在上传中....");
				// Toast.makeText(GoodsManagerActivity.this, "task 开始运行",
				// Toast.LENGTH_SHORT).show();
			}

			@Override
			protected Void doInBackground(Void... params) {
				// 模拟耗时操作 比如网络连接等
				startDemo();
				// 判断如果task已经cancel就没有必须继续进行下面的操作
				if (!isCancelled()) {
					// System.out.println("task 如果被cancel,就不会显示");
				}
				return null;
			}

			@Override
			protected void onPostExecute(Void result) {
				super.onPostExecute(result);
				// Toast.makeText(GoodsManagerActivity.this, "task 完成",
				// Toast.LENGTH_SHORT).show();
				textView1.setText("上传图片 成功!");
				 //数据是使用Intent返回
                Intent intent = new Intent();
                //把返回数据存入Intent
                intent.putExtra("picname", picname);//图片名称
                intent.putExtra("picurl", picurl);//图片路径
                
                //设置返回数据
                UpLoadPictureActivity.this.setResult(1, intent);
                //关闭Activity
                UpLoadPictureActivity.this.finish();
				
				
				// 通知主线程更新UI

				if (!isFinishing()) {
					try {
						// createAlertDialog().show();
					} catch (Exception e) {
					}
				}
			}

			@Override
			protected void onCancelled() {
				super.onCancelled();
				System.out.println("task 取消");
			}

		}
		
		public void startDemo() {//调用图片上传方法
			object=folder+"/"+picname;//上传到对应文件夹
//			new GetAndUploadFileDemo().show(picurl,object);

			
		}
	
	private void init() {
		// TODO Auto-generated method stub

		addPic = (Button) findViewById(R.id.btnClose);
		showPicPath = (Button) findViewById(R.id.btnSend);
		imgPath = (TextView) findViewById(R.id.img_path);
		imgShow = (ImageView) findViewById(R.id.imgShow);
		textView1=(TextView) findViewById(R.id.textView1);
		addPic.setOnClickListener(listener);

		showPicPath.setOnClickListener(listener);

	}

	private void CreateDialog() {//创建弹幕
		
		// 将xml文件实例化为View对象
		Dialog dialog = new Dialog(this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.picturelinfo_dilog);
		dialog.setTitle("选择图片");
		Window dialWindow = dialog.getWindow();
		WindowManager.LayoutParams lp = dialWindow.getAttributes();
		dialWindow.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP);
		lp.y = (int) (screenheight * 1 / 4);
		lp.width = (int) (screenwidth * 3 / 4);
		lp.alpha = 0.6f;// 透明度
		dialWindow.setAttributes(lp);
		dialog.show();
		
		Button take_pic = (Button) dialog.findViewById(R.id.take_pic);
		Button take_pho = (Button) dialog.findViewById(R.id.take_pho);
		take_pic.setOnClickListener(listener);
		take_pho.setOnClickListener(listener);
//		take_pic.setOnTouchListener(new OnTouchListener(){
//
//			@Override
//			public boolean onTouch(View v, MotionEvent Event) {//监听按钮松开事件
//				// TODO Auto-generated method stub
//				if(Event.getAction()==MotionEvent.ACTION_DOWN){
//					agd=false;
//				}
//				return agd;
//			}
//			
//		});
//		take_pho.setOnTouchListener(new OnTouchListener(){
//
//			@Override
//			public boolean onTouch(View v, MotionEvent Event) {
//				// TODO Auto-generated method stub
//				if(Event.getAction()==MotionEvent.ACTION_DOWN){
//					agd=false;
//					Log.d("sdsd", "相册按钮被按下");
//				}
//				return agd;
//			}
//			
//		});
//		if(!agd){
//			dialog.setCancelable(flag);; //按钮松开，弹幕消失
//			agd=true;
//		}
		

	}

	
	private OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

			Button btn = (Button) v;

			switch (btn.getId()) {

			case R.id.btnClose:
				CreateDialog();
				break;

			case R.id.btnSend:
				if(picurl.equals("")){
					Toast.makeText(UpLoadPictureActivity.this, "请选择需要上传的图片", Toast.LENGTH_SHORT)
					.show();
					break;
				}else{
					
					Task task = new Task();//开始上传
					task.execute();
				break;
				
				}
			case R.id.take_pic:
				break;
			case R.id.take_pho:
				setImage();
				break;
			}

		}

		private void setImage() {
			// TODO Auto-generated method stub
			// 使用intent调用系统提供的相册功能，使用startActivityForResult是为了获取用户选择的图片

			Intent getAlbum = new Intent(Intent.ACTION_GET_CONTENT);

			getAlbum.setType(IMAGE_TYPE);

			startActivityForResult(getAlbum, IMAGE_CODE);

		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (resultCode != RESULT_OK) { // 此处的 RESULT_OK 是系统自定义得一个常量

			Log.e("TAG->onresult", "ActivityResult resultCode error");

			return;

		}

		

		// 外界的程序访问ContentProvider所提供数据 可以通过ContentResolver接口

		ContentResolver resolver = getContentResolver();

		// 此处的用于判断接收的Activity是不是你想要的那个

		if (requestCode == IMAGE_CODE) {

			try {

				Uri originalUri = data.getData(); // 获得图片的uri

				bm = MediaStore.Images.Media.getBitmap(resolver, originalUri);
				// 显得到bitmap图片
				imgShow.setImageBitmap(bm);
				// 生成文件名
				SimpleDateFormat t = new SimpleDateFormat("yyyyMMddssSSS");
				String filename = "MT" + (t.format(new Date())) + ".jpg";
				if(sdCardExist){//判断是否有sd卡
					createPath("/sdcard/atayun/");
				}else{
					Toast.makeText(UpLoadPictureActivity.this, "没有插入SD卡", Toast.LENGTH_SHORT)
					.show();
					return;
				}

				if(flag){//确保创建文件夹
					saveBitmap(filename, bm);
					 picurl="/sdcard/atayun/"+filename;//得到想要的图片的路径
					 picname=filename;
					Log.d("picurl", picurl);

				}else{//创建文件夹失败
					Toast.makeText(UpLoadPictureActivity.this, "获取图片失败", Toast.LENGTH_SHORT)
					.show();
					return;
					
				}
//得到的图片的路径是


			} catch (IOException e) {

				Log.e("TAG-->Error", e.toString());

			}

		}

	}


	 //判断sd卡是否存在
	boolean sdCardExist = Environment.getExternalStorageState() 
			.equals(android.os.Environment.MEDIA_MOUNTED);
	
	
	/**
	 * 
	 * 
	 创建目录文件
	 */
//如果没有sd卡new File("data/data/aaa").createNewFile();
	public static void createPath(String path) {

		File file = new

		File(path);
		 flag=true;
		if

		(!file.exists()) {//判断文件夹是否存在,如果不存在则创建文件夹
			file.mkdir();
			Log.d("ads", "文件夹创建成功");

		}

	}

	/** 保存方法 */
	@SuppressLint("SdCardPath")
	public void saveBitmap(String picName, Bitmap bm) {
		
		Log.d("TAG", "保存图片");
		File f = new File("/sdcard/atayun/", picName);//保存地址，保存的文件名
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

	}

//	protected void setResut(int resultCode, Intent intent){
//		
//	}
}