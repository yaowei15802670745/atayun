package com.atayun.hgs.activity;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.atayun.hgs.url.ConnData;
import com.atayun.hgs.util.JsonObjectPostRequest;
import com.atayun.hgs.modle.IDApplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class PublishCarActivity_No extends Activity implements OnClickListener {
	private String url=ConnData.publishCarInfo;
	private String userId;
	private String userBaseFlag="";//userBaseFlag 个人基本信息（车主货主共用
	private Map<String, String> mMap;// 用于封装 完成注册提交的参数
	private Button bt_car_detail;
	private EditText start_place;// 开始城市
	private EditText end_place;// 到达城市

	private CheckBox over_length;
	private CheckBox over_width;
	private CheckBox over_height;
	private CheckBox over_weight;
	private Button bt_publish;// 发布按钮
	private String cariStart;
	private String cariEnd;
	private String flag_length="0";//超长是否被选的标志0为选1选
	private String flag_width="0";
	private String flag_heigth="0";
	private String flag_weigth="0";
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.sourcecar_not);
		userBaseFlag=((IDApplication)getApplication()).getUserBaseFlag();
		initView();
		
	}
	private void initView() {
		// TODO Auto-generated method stub
		bt_car_detail=(Button) findViewById(R.id.car_detail);
		bt_car_detail.setOnClickListener(this);
		
		start_place=(EditText) findViewById(R.id.cariStart);
		end_place=(EditText) findViewById(R.id.cariEnd);
		
		over_length = (CheckBox) findViewById(R.id.over_lengh);
		over_width = (CheckBox) findViewById(R.id.over_width);
		over_height = (CheckBox) findViewById(R.id.over_height);
		over_weight = (CheckBox) findViewById(R.id.over_weight);
		bt_publish=(Button) findViewById(R.id.ng_publish);
		bt_publish.setOnClickListener(this);
		
		
		
		// 复选框的时间监听
		over_length.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {
							flag_length = "1";
						} else {
							flag_length = "0";
						}
					}
				});
		// 复选框的时间监听
		over_width.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
							@Override
							public void onCheckedChanged(CompoundButton buttonView,
									boolean isChecked) {
								// TODO Auto-generated method stub
								if (isChecked) {
									flag_width = "1";
								} else {
									flag_width = "0";
								}
							}
						});
		// 复选框的时间监听
		over_height.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
							@Override
							public void onCheckedChanged(CompoundButton buttonView,
									boolean isChecked) {
								// TODO Auto-generated method stub
								if (isChecked) {
									flag_heigth = "1";
								} else {
									flag_heigth = "0";
								}
							}
						});
		// 复选框的时间监听
		over_weight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
							@Override
							public void onCheckedChanged(CompoundButton buttonView,
									boolean isChecked) {
								// TODO Auto-generated method stub
								if (isChecked) {
									flag_weigth = "1";
								} else {
									flag_weigth = "0";
								}
							}
						});
		
	}
	private void ng_publish(){
		cariStart=start_place.getText().toString().trim();
		cariEnd=end_place.getText().toString().trim();
		userId=((IDApplication)getApplication()).getUserId();
		mMap = new HashMap<String, String>();
		mMap.put("method", "publishCarInfo");
		mMap.put("userId", userId);
		
		mMap.put("cariStart", cariStart);
		mMap.put("cariEnd", cariEnd);
		mMap.put("ocariLength", flag_length);
		mMap.put("ocariWidth", flag_width);
		mMap.put("ocariHeight", flag_heigth);
		mMap.put("ocariLoad", flag_weigth);
		
		Log.d("cariStart", cariStart);
		Log.d("cariEnd", cariEnd);
		Log.d("ocariLength", flag_length);
		Log.d("ocariWidth", flag_width);
		Log.d("ocariHeight", flag_heigth);
		Log.d("ocariLoad", flag_weigth);
		Log.d("userId", userId);
		
		RequestQueue requestQueue = Volley.newRequestQueue(this);
		JsonObjectPostRequest jsonObjectPostRequest = new JsonObjectPostRequest(
				url,
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						try {

							String carState = response
									.getString("carState");
	                        Log.d("carState", carState);
							switch (carState) {
							case "200":
								Toast.makeText(PublishCarActivity_No.this,
										"发布成功", Toast.LENGTH_SHORT)
										.show();

								break;
							case "20":
								Toast.makeText(PublishCarActivity_No.this,
										"发布失败" ,
										Toast.LENGTH_SHORT).show();
								break;
							case "10":
								Toast.makeText(PublishCarActivity_No.this,
										"请完善个人信息",
										Toast.LENGTH_SHORT).show();
								Intent intent=new Intent(PublishCarActivity_No.this,GoodsPersonlInfo.class);
								startActivity(intent);
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
						Toast.makeText(PublishCarActivity_No.this,
								"获取失败，请检查网络", Toast.LENGTH_SHORT)
								.show();

					}
				}, mMap);
		requestQueue.add(jsonObjectPostRequest);
	}
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.ng_publish:
			ng_publish();
			break;
		case R.id.car_detail:
			if(userBaseFlag.equals("0")){//车主个人基本信息未完善,跳转到完善车主个人基本信息界面
				Intent intent=new Intent(PublishCarActivity_No.this,CarPersonlInfo.class);
				startActivity(intent);
			}else{//如果车主基本个人信息已经完善，跳转到车辆信息完善界面
				Intent intent=new Intent(PublishCarActivity_No.this,CarownerInfo.class);
				startActivity(intent);
			}
			
			break;
		
		}
		
	}
	public class myCheckListener implements OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
		}}
}
