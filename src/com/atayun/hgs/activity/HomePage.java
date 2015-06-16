package com.atayun.hgs.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.atayun.hgs.adapter.DrawerAdapter;
import com.atayun.hgs.adapter.FragmentAdapter;
import com.atayun.hgs.adapter.ImagePaperAdapter;
import com.atayun.hgs.adapter.MyPageChangeListener;
import com.atayun.hgs.dao.GetGoodsInfoDao;
import com.atayun.hgs.fragment.HomeLayoutLeft;
import com.atayun.hgs.fragment.HomeLayoutRight;
import com.atayun.hgs.modle.CarDetailItems;
import com.atayun.hgs.modle.MatchGoodsInfoItems;
import com.atayun.hgs.modle.GoodsInfoItems;
import com.atayun.hgs.modle.IDApplication;
import com.atayun.hgs.url.ConnData;
import com.atayun.hgs.util.JsonObjectPostRequest;

import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class HomePage extends FragmentActivity {
	

	private String url_getAllCargoInfo = ConnData.getAllCargoInfo;
	private String url_getcarInfo = ConnData.getcarInfo;
	private String url_getUserFlag = ConnData.getAllCargoInfo;
	private String url_machgoods="http://121.40.50.7:8080/wuLiuServer/cargoInfo.do";
	public static ArrayList<GoodsInfoItems> goodsInfoItems = new ArrayList<GoodsInfoItems>();
	public static ArrayList<CarDetailItems> carInfoItems = new ArrayList<CarDetailItems>();
	public static ArrayList<MatchGoodsInfoItems> BestMachgoodsInfoItems = new ArrayList<MatchGoodsInfoItems>();
	public static ArrayList<MatchGoodsInfoItems> OrtherMatchgoodsInfoItems = new ArrayList<MatchGoodsInfoItems>();
	private Map<String, String> mMap;// 用于封装 完成注册提交的参数
	private Map<String, String> mMap2;// 用于封装 完成注册提交的参数
	private Map<String, String> mMap3;// 用于封装 完成注册提交的参数
	private Map<String, String> mMap4;// 用于封装 完成注册提交的参数
	private String flagID;
	private String userId;
//    private Button search_goods;
    RequestQueue requestQueue ;
	// ViewPager是google SDk中自带的一个附加包的一个类，可以用来实现屏幕间的切换。
		// android-support-v4.jar
	
    private ViewPager mPager;//页卡内容
	private LayoutInflater inflater;
	private ViewPager mviewPager;
	private List<ImageView> dotViewList;// 用于小圆点图片
	private List<ImageView> imageViewlist;// 用于存放轮播效果图片
	private LinearLayout dotLayout;
	private int currentItem = 0;// 当前轮播页面
	private boolean isAutoPlay = true;// 是否自动轮播
	private ScheduledExecutorService scheduledExecutorService;// 定时任务
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (msg.what == 100) {
				mviewPager.setCurrentItem(currentItem);
			}
		}
	};
	// ====================以上是广告轮播的声明===============================
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private String[] mPlanetTitles = { "首页", "使用协议", "广告服务", "帮助" };// 存侧边栏的字
	private ImageButton bt_createDrawer;
	private ImageButton bt_personCenter;//个人中心按钮
	private int ScreenWidth;//用于设置圆点大小
	public void setCurrentItem(int currentItem){
		this.currentItem=currentItem;
	}
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.homepage);
//		search_goods=(Button)findViewById(R.id.search_goods);
//		search_goods.setOnClickListener(this);
		WindowManager wm=this.getWindowManager();
 	 requestQueue = Volley.newRequestQueue(this);
		ScreenWidth=wm.getDefaultDisplay().getWidth();
		flagID=((IDApplication)getApplication()).getFlagID();
		userId=((IDApplication)getApplication()).getUserId();
		initView();
		initImagePaper();// 添加轮播的图片和圆点
		InitViewPager();
		if (isAutoPlay) {
			startPlay();
		}
	}

	private void initView() {
		inflater = LayoutInflater.from(HomePage.this);
		mviewPager = (ViewPager) findViewById(R.id.myviewPager);
		dotLayout = (LinearLayout) findViewById(R.id.dotLayout);
		dotLayout.removeAllViews();

		dotViewList = new ArrayList<ImageView>();
		imageViewlist = new ArrayList<ImageView>();
		
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		bt_createDrawer = (ImageButton) findViewById(R.id.create_DrawerPage);
		bt_personCenter = (ImageButton) findViewById(R.id.person_center);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);
		
		List<Map<String, Object>> data = getData();
		mDrawerList.setAdapter(new DrawerAdapter(this, data));
		
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
		bt_createDrawer.setOnClickListener(new MyBtListener());
		bt_personCenter.setOnClickListener(new MyBtListener());
	}
	
	//系统back键的监听
	 @Override  
	    public boolean onKeyDown(int keyCode, KeyEvent event)  
	    {  
	        if (keyCode == KeyEvent.KEYCODE_BACK )  
	        {  
	            // 创建退出对话框  
	            AlertDialog isExit = new AlertDialog.Builder(this).create();  
	            // 设置对话框标题  
	            isExit.setTitle("系统提示");  
	            // 设置对话框消息  
	            isExit.setMessage("确定要退出吗");  
	            // 添加选择按钮并注册监听  
	            isExit.setButton("确定", listener);  
	            isExit.setButton2("取消", listener);  
	            // 显示对话框  
	            isExit.show();  
	  
	        }  
	          
	        return false;  
	          
	    }  
	    /**监听对话框里面的button点击事件*/  
	    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener()  
	    {  
	        public void onClick(DialogInterface dialog, int which)  
	        {  
	            switch (which)  
	            {  
	            case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序  
	                finish();  
	                break;  
	            case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框  
	                break;  
	            default:  
	                break;  
	            }  
	        }  
	    };    
	
	

	// 添加轮播的图片和圆点
	@SuppressLint("NewApi")
	public void initImagePaper() {
		
		// 动态添加了四个小圆点
		for (int i = 0; i < 4; i++) {
			ImageView dotView = new ImageView(HomePage.this);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					new LayoutParams(LayoutParams.WRAP_CONTENT,
							android.view.ViewGroup.LayoutParams.WRAP_CONTENT));

			params.leftMargin = 15;// 设置小圆点的外边距
			params.rightMargin = 15;
			params.height = ScreenWidth/50;// 设置小圆点的大小
			params.width = ScreenWidth/50;

			// 轮播到得时候改变颜色
			if (i == 0) {
				dotView.setBackgroundResource(R.drawable.point_pressed);
			} else {
				dotView.setBackgroundResource(R.drawable.point_unpressed);
			}

			dotLayout.addView(dotView, params);
			dotViewList.add(dotView);
		}
		// 动态添加了四张图片
		for (int i = 0; i < 4; i++) {
			//布局
			ImageView img = (ImageView) inflater.inflate(
					R.layout.imagepaper_item, null);
			//添加图片
			if (i == 0) {
				img.setBackgroundResource(R.drawable.roll_img1);
			} else if (i == 1) {
				img.setBackgroundResource(R.drawable.roll_img2);
			} else if (i == 2) {
				img.setBackgroundResource(R.drawable.roll_img3);
			} else if (i == 3) {
				img.setBackgroundResource(R.drawable.roll_img4);
			}

			imageViewlist.add(img);
		}

		ImagePaperAdapter adapter = new ImagePaperAdapter(
				(ArrayList) imageViewlist);

		mviewPager.setAdapter(adapter);
		mviewPager.setCurrentItem(0);
		mviewPager.setOnPageChangeListener(new MyPageChangeListener(this,currentItem,dotViewList));

	}
	/**
	 * 初始化ViewPager
	 */
	private void InitViewPager() {
		mPager = (ViewPager) findViewById(R.id.mainviewpager);
		ArrayList<Fragment> fragmentArray = new ArrayList<Fragment>();
		
		fragmentArray.add(new HomeLayoutLeft());
		fragmentArray.add(new HomeLayoutRight());
		mPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), fragmentArray));
		mPager.setCurrentItem(0);
	}

	/**
	 * 开始轮播图切换
	 */
	private void startPlay() {
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutorService.scheduleAtFixedRate(new SlideShowTask(), 1, 4,
				TimeUnit.SECONDS);
		// 根据他的参数说明，第一个参数是执行的任务，第二个参数是第一次执行的间隔，第三个参数是执行任务的周期；
	}

	/**
	 * 执行轮播图切换任务
	 */
	private class SlideShowTask implements Runnable {

		@Override
		public void run() {
			synchronized (mviewPager) {
				currentItem = (currentItem + 1) % imageViewlist.size();
				handler.sendEmptyMessage(100);
			}
		}
	}
	// ========================以上是轮播，以下是侧边栏=========================
	/**
	 * 侧边栏listview添加图片
	 */
	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < 4; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("DrawerPic", i);
			map.put("DrawerText", mPlanetTitles[i]);
			list.add(map);
		}
		return list;
	}

	/**
	 * 侧边栏的监听类
	 */
	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			switch (position) {
			case 0:
				break;

			case 1:
				Intent intent2 = new Intent(HomePage.this, TermsOfUse.class);// 使用协议
				startActivity(intent2);
				break;

			case 2:
				Intent intent3 = new Intent(HomePage.this, AdService.class);// 广告服务
				startActivity(intent3);
				break;

			case 3:
				Intent intent4 = new Intent(HomePage.this, AdService.class);// 帮助
				startActivity(intent4);
				break;

			default:
				break;
			}
			mDrawerLayout.closeDrawer(mDrawerList);
		}
	}
	

	@SuppressWarnings("unused")
	private class MyBtListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			// 打开侧边栏的按钮监听
			case R.id.create_DrawerPage:
				mDrawerLayout.openDrawer(Gravity.LEFT);
				break;
				
			case R.id.person_center:
				//跳转到个人中心
				if(flagID.equals("0")){
					Intent intent = new Intent(HomePage.this, CarPersonCenter.class);

					finish();
					startActivity(intent);
					break;
				}else{
					Intent intent = new Intent(HomePage.this, GoodsPersonCenter.class);

					finish();
					startActivity(intent);
					break;
				}
				

			default:
				break;
			}
		}
	}

}
