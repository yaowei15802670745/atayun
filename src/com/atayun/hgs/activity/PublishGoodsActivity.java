package com.atayun.hgs.activity;
/*
 * 货源发布
 */
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.atayun.hgs.dao.GetGoodsInfoDao;
import com.atayun.hgs.url.ConnData;
import com.atayun.hgs.util.JsonObjectPostRequest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class PublishGoodsActivity extends  Activity implements OnClickListener{
	private String userId;//用户ID
	private String userName;//用户名（姓名/企业名）
	private String userMobile;//用户手机号（可用于用户登录）
	private String userPicUrl;//用户头像图片url地址
	private int userRoleId;//用户权限（1 normal;16 vip; 31 admin; 63 sadmin;)
	private Button submit;
	private String publishCargo_url="http://121.40.50.7:8080/wuLiuServer/cargoInfo.do";
	
	/***************发布货源提交的参数******************/
	private String deliTime="2015-05-26 15:57:33.0";//-货源发货时间
	private String transportTypeId="17";// -货物运输类型的ID
	private String cargoTypeId="1";// 货源类型ID
	private String cargoInfoStart="湖南省长沙市";// 起点
	private String cargoInfoEnd="广东省广州市";// 终点
	private String cargoInfoLong="116.380";//起点坐标 
	private String cargoInfoLat="39.913";// 终点坐标
	private String cargoInfoPrice="5000";//运价
	private String cargoInfoLenth="10";//货长
	private String cargoInfoWidth="3";//货宽
	private String cargoInfoHeigh="2";//货高
	private String cargoInfoVolume="60";//货体积
	private String cargoInfoLoad="30";//货载重
	private String cargoInfoDesc="仪器设备，请装车卸货务必小心";//货描述
	private String cargoInfoContacts="周杰伦";//货联系人
	private String cargoInfoContactWay="15802670745";//货联系方式
	private String cargoInfoPicturl="2015052677747.jpg";//货图片
	private Map<String, String> mMap;


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		setContentView(R.layout.activity_publishgoods);
		Bundle bundle = this.getIntent().getExtras(); 
		userId=bundle.getString("userId");
		userName=bundle.getString("userName");
		userMobile=bundle.getString("userMobile");
		userPicUrl=bundle.getString("userPicUrl");
		userRoleId=bundle.getInt("userRoleId");
	Log.d("bundle", "userId"+userId+"userName"+userName+"userMobile"+userMobile+"userPicUrl"+userPicUrl+"userRoleId"+userRoleId);	
	submit=(Button)findViewById(R.id.button1);
	submit.setOnClickListener(this);
	
	}
	
	
	
	

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.button1:
			mMap= new HashMap<String, String>();
			mMap.put("method", "publishCargoInfo");
			mMap.put("userId", userId);
			mMap.put("deliTime", deliTime);
			mMap.put("transportTypeId", transportTypeId);
			mMap.put("cargoTypeId", cargoTypeId);
			mMap.put("cargoInfoStart", cargoInfoStart);
			mMap.put("cargoInfoEnd", cargoInfoEnd);
			mMap.put("cargoInfoLong", cargoInfoLong);
			mMap.put("cargoInfoLat", cargoInfoLat);
			mMap.put("cargoInfoPrice", cargoInfoPrice);
			mMap.put("cargoInfoLenth", cargoInfoLenth);
			mMap.put("cargoInfoWidth", cargoInfoWidth);
			mMap.put("cargoInfoHeigh", cargoInfoHeigh);
			mMap.put("cargoInfoVolume", cargoInfoVolume);
			mMap.put("cargoInfoLoad", cargoInfoLoad);
			mMap.put("cargoInfoDesc", cargoInfoDesc);
			mMap.put("cargoInfoContacts", cargoInfoContacts);
			mMap.put("cargoInfoContactWay", cargoInfoContactWay);
			mMap.put("cargoInfoPicturl", cargoInfoPicturl);
			RequestQueue requestQueue = Volley.newRequestQueue(this);
			JsonObjectPostRequest jsonObjectPostRequest = new JsonObjectPostRequest(
					publishCargo_url, new Response.Listener<JSONObject>() {

						@Override
						public void onResponse(JSONObject response) {
							try {

								String publishCargoState = response
										.getString("publishCargoState");

								switch (publishCargoState) {
								case "200":
									Toast.makeText(PublishGoodsActivity.this, "发布成功",
											Toast.LENGTH_SHORT).show();
									
									break;
								case "10":
									Toast.makeText(PublishGoodsActivity.this, "发布失败",
											Toast.LENGTH_SHORT).show();
									break;
								case "30":
									Toast.makeText(PublishGoodsActivity.this, "请完善信息",
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
							Toast.makeText(PublishGoodsActivity.this, "获取失败，请检查网络",
									Toast.LENGTH_SHORT).show();

						}
					}, mMap);
			requestQueue.add(jsonObjectPostRequest);

			break;
			
			
			
			
			
		
		
		
		}
		
	}

}
