package com.atayun.hgs.activity;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.atayun.hgs.activity.HomePage;
import com.atayun.hgs.activity.R;
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

public class PubGoodsFail extends Activity implements OnClickListener {

	private Map<String, String> mMap;// 用于封装 完成注册提交的参数
	private String url = ConnData.publishCargoInfo;
	private String userId;// -用户唯一标识ID（可以测试userId为12的用户）
	private String deliTime;// -货源发货时间(2019-)
	private String transportTypeId;// -货物运输类型的ID
	private String cargoTypeId;// -货源类型ID
	private String cargoInfoStart;// -起点
	private String cargoInfoSStreet;// -起点详细地址
	private String cargoInfoEnd;// - 终点
	private String cargoInfoEStreet;// -终点详细地址
	private String cargoInfoLng;// -起点经度
	private String cargoInfoLat;// -起点纬度
	private String cargoInfoELng;// -终点经度
	private String cargoInfoELat;// -终点纬度
	private String cargoInfoPrice;// -运价
	private String cargoInfoLenth;// -货长
	private String cargoInfoWidth;// -货宽
	private String cargoInfoHeight;// -货高
	private String cargoInfoRlen;// -需要的车长
	private String cargoInfoVunit;// -货物体积单位
	private String cargoInfoLunit;// -货物载重单位
	private String cargoInfoVolume;// -货物体积
	private String cargoInfoLoad;// -货物载重
	private String cargoInfoDesc;// -货物描述
	private String cargoInfoContacts;// -货物联系人
	private String cargoInfoContactWay;// -联系方式
	private String cargoInfoPicturl;// -货物图片

	private Button failcheck_home;
	private Button pubgoods_retry;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_pubgoodsfail);
		failcheck_home = (Button) findViewById(R.id.failcheck_home);
		failcheck_home.setOnClickListener(this);
		pubgoods_retry = (Button) findViewById(R.id.pubgoods_retry);
		pubgoods_retry.setOnClickListener(this);
		// 解析Bundle传递过来的数据
		Bundle bundle = this.getIntent().getExtras();

		userId = bundle.getString("userId");
		deliTime = bundle.getString("deliTime");
		transportTypeId = bundle.getString("transportTypeId");
		cargoTypeId = bundle.getString("cargoTypeId");
		cargoInfoStart = bundle.getString("cargoInfoStart");
		cargoInfoSStreet = bundle.getString("cargoInfoSStreet");
		cargoInfoEnd = bundle.getString("cargoInfoEnd");
		cargoInfoEStreet = bundle.getString("cargoInfoEStreet");
		cargoInfoLng = bundle.getString("cargoInfoLng");
		cargoInfoLat = bundle.getString("cargoInfoLat");
		cargoInfoELng = bundle.getString("cargoInfoELng");
		cargoInfoELat = bundle.getString("cargoInfoELat");
		cargoInfoPrice = bundle.getString("cargoInfoPrice");
		cargoInfoLenth = bundle.getString("cargoInfoLenth");
		cargoInfoWidth = bundle.getString("cargoInfoWidth");
		cargoInfoHeight = bundle.getString("cargoInfoHeight");
		cargoInfoRlen = bundle.getString("cargoInfoRlen");
		cargoInfoVunit = bundle.getString("cargoInfoVunit");
		cargoInfoLunit = bundle.getString("cargoInfoLunit");
		cargoInfoVolume = bundle.getString("cargoInfoVolume");
		cargoInfoLoad = bundle.getString("cargoInfoLoad");

		cargoInfoDesc = bundle.getString("cargoInfoDesc");
		cargoInfoContacts = bundle.getString("cargoInfoContacts");
		cargoInfoContactWay = bundle.getString("cargoInfoContactWay");
		cargoInfoPicturl = bundle.getString("cargoInfoPicturl");

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.failcheck_home:
			Intent intent = new Intent(PubGoodsFail.this, PageFoot.class);
			startActivity(intent);
			finish();

			break;

		case R.id.pubgoods_retry:

			mMap = new HashMap<String, String>();
			mMap.put("method", "publishCargoInfo");
			mMap.put("userId", userId);
			mMap.put("deliTime", deliTime);
			mMap.put("transportTypeId", transportTypeId);
			mMap.put("cargoTypeId", cargoTypeId);
			mMap.put("cargoInfoStart", cargoInfoStart);
			mMap.put("cargoInfoSStreet", cargoInfoSStreet);
			mMap.put("cargoInfoEnd", cargoInfoEnd);
			mMap.put("cargoInfoEStreet", cargoInfoEStreet);
			mMap.put("cargoInfoLng", "38.2");
			mMap.put("cargoInfoLat", "38.2");
			mMap.put("cargoInfoELng", "38.2");
			mMap.put("cargoInfoELat", "38.2");
			mMap.put("cargoInfoPrice", cargoInfoPrice);
			mMap.put("cargoInfoLenth", cargoInfoLenth);
			mMap.put("cargoInfoWidth", cargoInfoWidth);
			mMap.put("cargoInfoHeight", cargoInfoHeight);
			mMap.put("cargoInfoRlen", cargoInfoRlen);
			// Log.d("cargoInfoRlen", cargoInfoRlen);
			// 体积单位，重量单位直接写
			mMap.put("cargoInfoVunit", "方");
			mMap.put("cargoInfoLunit", "吨");
			mMap.put("cargoInfoVolume", cargoInfoVolume);
			mMap.put("cargoInfoLoad", cargoInfoLoad);
			mMap.put("cargoInfoDesc", cargoInfoDesc);
			mMap.put("cargoInfoContacts", cargoInfoContacts);
			mMap.put("cargoInfoContactWay", cargoInfoContactWay);
			mMap.put("cargoInfoPicturl", cargoInfoPicturl);
			RequestQueue requestQueue = Volley.newRequestQueue(this);
			JsonObjectPostRequest jsonObjectPostRequest = new JsonObjectPostRequest(
					url, new Response.Listener<JSONObject>() {

						@Override
						public void onResponse(JSONObject response) {
							try {

								String publishCargoState = response
										.getString("publishCargoState");
								Log.d("publishCargoState", publishCargoState);
								switch (publishCargoState) {
								case "200":
									Toast.makeText(PubGoodsFail.this, "发布成功",
											Toast.LENGTH_SHORT).show();
									Intent intent = new Intent(
											PubGoodsFail.this,
											PubGoodsSuccess.class);
									finish();
									startActivity(intent);

									break;
								case "10":
									Toast.makeText(PubGoodsFail.this,
											"发布失败" + publishCargoState,
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
							Toast.makeText(PubGoodsFail.this, "获取失败，请检查网络",
									Toast.LENGTH_SHORT).show();

						}
					}, mMap);
			requestQueue.add(jsonObjectPostRequest);
			break;

		}

	}

}
