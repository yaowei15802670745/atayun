package com.atayun.hgs.activity;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.atayun.hgs.modle.IDApplication;
import com.atayun.hgs.url.ConnData;
import com.atayun.hgs.util.JsonObjectPostRequest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class CarownerInfo extends Activity implements OnClickListener {
	private String url = ConnData.changeCarOwnerInfo;
	private Map<String, String> mMap;
	private Button ow_complete; // 完成
	private Button ow_tiaoguo;// 跳过，进入主界面
	private EditText ow_cariLpnum; // 车牌号
	private EditText ow_cariType; // 车型
	private EditText ow_companyname; // 企业名称
	private Spinner ow_carType; // 车型下拉框
	private Spinner ow_cariLength; // 车辆长
	private Spinner ow_cariWidth; // 车辆宽
	private Spinner ow_cariHeight; // 车辆高
	private Spinner ow_cariLoad; // 承载重量
	private Spinner ow_cariVolume; // 承载方
	private EditText ow_etcarpic; // 车辆图片
	private EditText ow_etdrivepic; // 驾驶证图片
	private EditText ow_etcardrivepic;// 行驶证图片
	private ImageButton uploadcarpic; // 上传车辆图片图标
	private ImageButton uploaddrivepic; // 上传驾驶证图片图标
	private ImageButton uplodcardrivepic;// 上传行驶证图标

	private String userId = "";// : userId是否存在对所获取的userId 进行数据查询，是否存在重复插入

	private String caroCpName = "";// ： 企业名称

	private String cariLpnum = "";// ： 车牌号
	private String cartId = "";// ： 车型（范围1-14）

	private String cariLength = "";// : 车辆长
	private String cariWidth = "";// ： 车辆宽
	private String cariHeight = "";// : 车辆高
	private String cariLoad = "";// : 承载重量
	private String cariLunit = "";// ： 单位
	private String cariVolume = "";// : 承载方
	private String cariVunit = "";// : 单位
	private String caroDlicpicurl = "";// 驾驶证图片
	private String cariPicUrl = "";// ： 车辆图片
	private String cariDlicUrl = "";// : 行驶证图片

	/*
	 * 车型（范围1-14）
	 * 
	 * <item>厢式车</item> <item>平板车</item> <item>高低板车</item> <item>高栏车</item>
	 * <item>中栏车</item> <item>低栏车</item> <item>罐式车</item> <item>冷藏车</item>
	 * <item>保温车</item> <item>危险品车</item> <item>铁笼车</item> <item>集装箱车</item>
	 * <item>自动卸货车</item> <item>其他车型</item>
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_carownerinfo);
		userId = ((IDApplication) getApplication()).getUserId();// 获取userId
		initView();

	}

	private void initView() {

		// 初始化
		ow_complete = (Button) findViewById(R.id.ow_carcomplete);
		ow_tiaoguo = (Button) findViewById(R.id.ow_complete);
		ow_tiaoguo.setOnClickListener(this);
		ow_cariLpnum = (EditText) findViewById(R.id.ow_cariLpnum);

		ow_companyname = (EditText) findViewById(R.id.ow_companyname);
		ow_cariLength = (Spinner) findViewById(R.id.ow_cariLength);
		ow_cariWidth = (Spinner) findViewById(R.id.ow_cariWidth);
		ow_cariHeight = (Spinner) findViewById(R.id.ow_cariHeight);
		ow_cariLoad = (Spinner) findViewById(R.id.ow_cariLoad);
		ow_cariVolume = (Spinner) findViewById(R.id.ow_cariVolume);
		ow_carType = (Spinner) findViewById(R.id.ow_carType);
		ow_cariLoad = (Spinner) findViewById(R.id.ow_cariLoad);
		ow_etcarpic = (EditText) findViewById(R.id.ow_etcarpic);
		ow_etdrivepic = (EditText) findViewById(R.id.ow_etdrivepic);
		ow_etcardrivepic = (EditText) findViewById(R.id.ow_etcardrivepic);
		//将图片对应的editext设置为不可编辑
		ow_etcarpic.setEnabled(false);
		ow_etdrivepic.setEnabled(false);
		ow_etcardrivepic.setEnabled(false);
		
		uploadcarpic = (ImageButton) findViewById(R.id.uploadcarpic);
		uploaddrivepic = (ImageButton) findViewById(R.id.uploaddrivepic);
		uplodcardrivepic = (ImageButton) findViewById(R.id.uploadcardrivepic);
		uploadcarpic.setOnClickListener(this);
		uploaddrivepic.setOnClickListener(this);
		uplodcardrivepic.setOnClickListener(this);
		
		
		ow_complete.setOnClickListener(this);
		/* ##############################下拉框的事件监听################## */
		ow_cariLength
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						cariLength = (String) ow_cariLength
								.getItemAtPosition(arg2);// 给车长赋值
						// String i=arg2+"";
						// Log.d("arg2", i);
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}

				});
		ow_cariWidth
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						cariWidth = (String) ow_cariWidth
								.getItemAtPosition(arg2);// 给车宽赋值
						// String i=arg2+"";
						// Log.d("arg2", i);
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}

				});
		ow_cariHeight
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						cariHeight = (String) ow_cariHeight
								.getItemAtPosition(arg2);// 给车高赋值
						// String i=arg2+"";
						// Log.d("arg2", i);

					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}

				});

		ow_carType
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
//						cartId = (String) ow_carType.getItemAtPosition(arg2);//
						cartId = arg2 + 1+"";

						Log.d("cartId", cartId);// 

					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}

				});

		ow_cariVolume
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						cariVolume = (String) ow_cariVolume
								.getItemAtPosition(arg2);// 给车高赋值

						Log.d("cariVolume", cariVolume);// cartId=0,表还没有选择车型

					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}

				});

		ow_cariLoad
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						cariLoad = (String) ow_cariLoad.getItemAtPosition(arg2);// 给车高赋值

						Log.d("cariLoad", cariLoad);// cartId=0,表还没有选择车型

					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}

				});

	}

	private void ow_carcomplete() {

		// 获取用户输入的信息
		caroCpName = ow_companyname.getText().toString().trim();
		cariLpnum = ow_cariLpnum.getText().toString().trim();
		if (cariLpnum.equals("")) {
			Toast.makeText(CarownerInfo.this, "车牌号不能为空！", Toast.LENGTH_SHORT)
					.show();
			return;
		}

		if (cariLength.equals("车辆长/米") || cariWidth.equals("车辆宽/米")
				|| cariHeight.equals("车辆高/米")) {
			Toast.makeText(CarownerInfo.this, "车辆长、宽、高不可为空！",
					Toast.LENGTH_SHORT).show();
			return;

		}
		if (cariVolume.equals("承载方/方")) {

			Toast.makeText(CarownerInfo.this, "车辆体积货主载重不能为空！",
					Toast.LENGTH_SHORT).show();
			return;

		}

		mMap = new HashMap<String, String>();
		mMap.put("method", "changeCarOwnerInfo");
		mMap.put("userId",userId);
		mMap.put("caroDlicpicurl", "caroDlicpicurl");
		mMap.put("caroCpName", caroCpName);
		mMap.put("cariLpnum", cariLpnum);
		mMap.put("cartId", cartId);
		mMap.put("cariLength", cariLength);
		mMap.put("cariWidth", cariWidth);
		mMap.put("cariHeight", cariHeight);
		mMap.put("cariLoad", cariLoad);
		mMap.put("cariLunit", "吨");
		mMap.put("cariVolume", cariVolume);
		mMap.put("cariVunit", "方");
		mMap.put("cariPicUrl", "cariPicUrl");
		mMap.put("cariDlicUrl", "cariDlicUrl");
		Log.d("userId", userId);
		Log.d("caroDlicpicurl", "caroDlicpicurl");
		Log.d("caroCpName", caroCpName);
		Log.d("cariLpnum", cariLpnum);
		Log.d("cartId", cartId);
		
		Log.d("cariLength", cariLength);
		Log.d("cariWidth", cariWidth);
		Log.d("cariHeight", cariHeight);
		Log.d("cariLoad", cariLoad);
		Log.d("cariLunit", cariLunit);
		Log.d("cariVolume", cariVolume);
		Log.d("cariVunit", cariVunit);
		Log.d("cariPicUrl", "cariPicUrl");
		Log.d("cariDlicUrl", "cariDlicUrl");

		// 请求服务
		RequestQueue requestQueue = Volley.newRequestQueue(this);
		JsonObjectPostRequest jsonObjectPostRequest = new JsonObjectPostRequest(
				url, new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						try {

							String ownerState = response
									.getString("ownerState");

							switch (ownerState) {
							case "200":
								Toast.makeText(CarownerInfo.this, "信息完善成功",
										Toast.LENGTH_SHORT).show();

								// 将用户认证状态放进全局变量
								((IDApplication) getApplication())
										.setCarUserFlag("1");// 车主
								((IDApplication) getApplication())
										.setCargoFlag("-1");// 个人货主
								((IDApplication) getApplication())
										.setCompanyFlag("-1");// 货主企业
								((IDApplication) getApplication()).setUserBaseFlag("1");//个人基本信息是否完善
								Intent intent = new Intent(CarownerInfo.this,
										PageFoot.class);
								finish();
								startActivity(intent);
								break;

							case "50":
								Toast.makeText(CarownerInfo.this, "完善失败",
										Toast.LENGTH_SHORT).show();
								break;
							case "40":
								Toast.makeText(CarownerInfo.this, "所给车牌号已被注册",
										Toast.LENGTH_SHORT).show();
								break;
							case "30":
								Toast.makeText(CarownerInfo.this, "所给车牌格式错误",
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
						Toast.makeText(CarownerInfo.this, "获取失败，请检查网络",
								Toast.LENGTH_SHORT).show();

					}
				}, mMap);
		requestQueue.add(jsonObjectPostRequest);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ow_carcomplete:
			ow_carcomplete();
			break;
		case R.id.ow_complete:
			// 点击跳过按钮进入主界面
			Intent intent = new Intent(CarownerInfo.this, PageFoot.class);
			finish();
			startActivity(intent);
			break;
		case R.id.uploadcarpic:
			Intent intent2 = new Intent(CarownerInfo.this, UpLoadPictureActivity.class);
			intent2.putExtra("OBject", "carImages");//车辆照片 将需要保存到的文件夹名传过去
			startActivityForResult(intent2, 1);
			break;
		case R.id.uploadcardrivepic:
			Intent intent3 = new Intent(CarownerInfo.this, UpLoadPictureActivity.class);
			intent3.putExtra("OBject", "drlicenceImages");//行驶证 将需要保存到的文件夹名传过去
			startActivityForResult(intent3, 2);
			break;
		case R.id.uploaddrivepic:
			Intent intent4 = new Intent(CarownerInfo.this, UpLoadPictureActivity.class);
			intent4.putExtra("OBject", "dlicenceImages");//驾驶证 将需要保存到的文件夹名传过去
			startActivityForResult(intent4, 3);
			break;
			
		}

	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {

		case 1:
			// 来自按钮1的请求，作相应业务处理
			cariPicUrl = data.getStringExtra("picname");
			String urlpic = data.getStringExtra("picurl");
			Log.d("车辆照照", cariPicUrl);
			ow_etcarpic.setText(urlpic);

			break;
		case 2:
			cariDlicUrl = data.getStringExtra("picname");
			Log.d("行驶证", cariDlicUrl);
			String urlpic2 = data.getStringExtra("picurl");
			
			ow_etcardrivepic.setText(urlpic2);
			break;
		case 3:
			// 来自按钮1的请求，作相应业务处理
			caroDlicpicurl = data.getStringExtra("picname");
			Log.d("驾驶证", caroDlicpicurl);
			String urlpic3 = data.getStringExtra("picurl");
		
			ow_etdrivepic.setText(urlpic3);

			break;
		

		}
	}
	
	

}
