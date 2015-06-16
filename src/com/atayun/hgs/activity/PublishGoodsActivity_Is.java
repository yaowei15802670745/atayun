package com.atayun.hgs.activity;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
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
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class PublishGoodsActivity_Is extends Activity implements
		OnClickListener {

	private String transportTypeId = "";// 获取用户点击选择的货运类型对应的id
	private String deliTime = "";// 发货时间
	private String cargoInfoSStreet = "";// 出发详细地址
	private String cargoInfoEStreet = "";// 终点详细地址
	private String cargoInfoStart = "";// 起点
	private String cargoInfoEnd = "";// 终点
	private String cargoInfoPrice = ""; // -运价
	private String cargoInfoLenth = ""; // -货长
	private String cargoInfoWidth = ""; // -货宽
	private String cargoInfoHeight = ""; // -货高
	private String cargoInfoVolume = ""; // -货物体积
	private String cargoInfoLoad = ""; // -货物载重
	private String cargoInfoDesc = ""; // -货物描述
	private String cargoInfoContacts = ""; // -货物联系人
	private String cargoInfoContactWay = ""; // -联系方式
	private String cargoInfoPicturl = ""; // -货物图片
	private String cargoInfoRlen;// -需要的车长
	private String cargoInfoVunit;// -车辆体积单位
	private String cargoInfoLunit;// -车辆载重单位

	private String cargoTypeId;// 获取用户点击选择的货源类型ID

	private int screenwidth, screenheight;

	private Map<String, String> mMap;// 用于封装 完成注册提交的参数
	private String url = ConnData.publishCargoInfo;
	private String url_info = ConnData.getUserBaseMeg;
	private Map<String, String> mMap2;// 用于封装 完成注册提交的参数

	private String userMobile = "获取失败";
	private String userName = "获取失败";
	private String userAddr = "获取失败";
	private String userId = "";
	private Button publishgoods;

	private TextView user_Mobile;//
	private TextView user_Name;//
	private TextView user_Addr;//

	private EditText startCity;// 出发城市
	private EditText startAddress;// 出发详细地址
	private EditText endCity;// 到达城市
	private EditText endAddress;// 到达详细地址
	private Spinner transporttype;// 货源类型
	private Spinner sourcetype;// 货运类型
	private EditText length;// 长
	private EditText width;// 宽
	private EditText height;// 高
	private EditText numbers;// 数量
	private EditText price;// 价格
	private Spinner unit;// 数量单位
	private Spinner needCarType;// 需要车型
	private Spinner needCarLength;// 需要车长
	private EditText sent_time;// 联系人姓名
	private EditText contactsName;// 联系人姓名
	private EditText contactsPhone;// 联系人电话
	private EditText infoDetail;// 信息内容
	final Calendar c = Calendar.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_publishgoods_is);
		userId = ((IDApplication) getApplication()).getUserId();// 获取全局变量的userId
		publishgoods = (Button) findViewById(R.id.publishgoods);
		publishgoods.setOnClickListener(this);

		// getUserBaseMeg();//获取个人基本信息
		Task task = new Task();
		task.execute();
		initView();

		// 用于得到屏幕的宽高
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		screenwidth = dm.widthPixels;
		screenheight = dm.heightPixels;
	}

	private class Task extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Toast.makeText(PublishGoodsActivity_Is.this, "task 开始运行",
			// Toast.LENGTH_SHORT).show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			// 模拟耗时操作 比如网络连接等
			getUserBaseMeg();
			// 判断如果task已经cancel就没有必须继续进行下面的操作
			if (!isCancelled()) {
				// System.out.println("task 如果被cancel,就不会显示");
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			// Toast.makeText(PublishGoodsActivity_Is.this, "task 完成",
			// Toast.LENGTH_SHORT).show();
			// 所有调用当前context的对象要注意判断activity是否还存在
			// 典型的比如弹窗
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

	// 获取个人基本信息的方法

	public void getUserBaseMeg() {
		mMap2 = new HashMap<String, String>();
		mMap2.put("method", "getUserBaseMeg");
		mMap2.put("userId", userId);

		RequestQueue requestQueue = Volley.newRequestQueue(this);
		JsonObjectPostRequest jsonObjectPostRequest = new JsonObjectPostRequest(
				url_info, new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						try {
							String getUserBaseMegState = response
									.getString("getUserBaseMegState");
							switch (getUserBaseMegState) {
							case "200":

								// 解析数据
								JSONArray UserBaseMeg = response
										.getJSONArray("UserBaseMeg");

								// 解析JSONArray
								userMobile = UserBaseMeg.getJSONObject(0)
										.getString("userMobile");
								userName = UserBaseMeg.getJSONObject(0)
										.getString("userName");
								userAddr = UserBaseMeg.getJSONObject(0)
										.getString("userAddr");

								user_Mobile = (TextView) findViewById(R.id.user_phone);
								user_Name = (TextView) findViewById(R.id.user_name);
								user_Addr = (TextView) findViewById(R.id.user_addres);

								user_Name.setText("姓名        ：" + userName);
								user_Mobile.setText("联系方式：" + userMobile);
								user_Addr.setText("地址        ：" + userAddr);

								Log.d("userMobile", userMobile);
								Log.d("userName", userName);
								Log.d("userAddr", userAddr);

								break;
							case "10":

								Toast.makeText(PublishGoodsActivity_Is.this,
										"个人信息获取失败", Toast.LENGTH_SHORT).show();

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
						Toast.makeText(PublishGoodsActivity_Is.this, "个人信息失败",
								Toast.LENGTH_SHORT).show();

					}
				}, mMap2);
		requestQueue.add(jsonObjectPostRequest);

	}

	private void initView() {
		startCity = (EditText) findViewById(R.id.startCity);
		startAddress = (EditText) findViewById(R.id.startAddress);
		endCity = (EditText) findViewById(R.id.endCity);
		endAddress = (EditText) findViewById(R.id.endAddress);
		transporttype = (Spinner) findViewById(R.id.transporttype);
		sourcetype = (Spinner) findViewById(R.id.sourcetype);
		length = (EditText) findViewById(R.id.length);
		width = (EditText) findViewById(R.id.width);
		height = (EditText) findViewById(R.id.height);
		numbers = (EditText) findViewById(R.id.numbers);
		price = (EditText) findViewById(R.id.g_price);
		unit = (Spinner) findViewById(R.id.unit);
		needCarType = (Spinner) findViewById(R.id.needCarType);
		needCarLength = (Spinner) findViewById(R.id.needCarLength);
		sent_time = (EditText) findViewById(R.id.sent_time);
		contactsName = (EditText) findViewById(R.id.contactsName);
		contactsPhone = (EditText) findViewById(R.id.contactsPhone);
		infoDetail = (EditText) findViewById(R.id.infoDetail);

		// 获取需要车长

		needCarLength
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						cargoInfoRlen = (String) needCarLength
								.getItemAtPosition(arg2);
						Log.d("cargoInfoRlen", cargoInfoRlen);

					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}

				});

		transporttype
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// sp1Value = (String) transporttype
						// .getItemAtPosition(arg2);
						transportTypeId = arg2 + 16 + "";
						Log.d("transportTypeId", transportTypeId);

					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// 没有选择默认第一项
						transportTypeId = "16";
					}

				});
		/******************************************************************************************/

		sourcetype
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// sp2Value = (String)
						// sourcetype.getItemAtPosition(arg2);
						cargoTypeId = arg2 + 1 + "";
						Log.d("cargoTypeId", cargoTypeId);
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {

						cargoTypeId = "1";
					}

				});

		sent_time.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				DatePickerDialog dialog = new DatePickerDialog(
						PublishGoodsActivity_Is.this,
						new DatePickerDialog.OnDateSetListener() {
							@Override
							public void onDateSet(DatePicker view, int year,
									int monthOfYear, int dayOfMonth) {
								c.set(year, monthOfYear, dayOfMonth);
								sent_time.setText(DateFormat.format(
										"yyy-MM-dd", c));
								String deliTime = sent_time.getText()
										.toString().trim();
								Log.d("time", deliTime);
							}
						}, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c
								.get(Calendar.DAY_OF_MONTH));
				dialog.show();
			}
		});

	}

	// 系统back键的监听
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// 返回主界面
			// task.cancel(true);
			Intent intent = new Intent(PublishGoodsActivity_Is.this,
					PageFoot.class);
			startActivity(intent);
			finish();
		}

		return false;

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.publishgoods:

			String Time = sent_time.getText().toString().trim();
			deliTime = Time + " 00:00:00.0";
			cargoInfoStart = startCity.getText().toString().trim();
			cargoInfoEnd = endCity.getText().toString().trim();
			cargoInfoPrice = price.getText().toString().trim();
			cargoInfoSStreet = startAddress.getText().toString().trim();
			cargoInfoEStreet = endAddress.getText().toString().trim();
			cargoInfoLenth = length.getText().toString().trim();
			cargoInfoWidth = width.getText().toString().trim();
			cargoInfoHeight = height.getText().toString().trim();
			cargoInfoVolume = numbers.getText().toString().trim();
			cargoInfoLoad = numbers.getText().toString().trim();
			cargoInfoDesc = infoDetail.getText().toString().trim();
			cargoInfoContacts = contactsName.getText().toString().trim();
			cargoInfoContactWay = contactsPhone.getText().toString().trim();
			cargoInfoPicturl = "237237277.jpg";

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
									Toast.makeText(
											PublishGoodsActivity_Is.this,
											"发布成功", Toast.LENGTH_SHORT).show();
									Intent intent = new Intent(
											PublishGoodsActivity_Is.this,
											PubGoodsSuccess.class);
									finish();
									startActivity(intent);

									break;
								case "10":
									Toast.makeText(
											PublishGoodsActivity_Is.this,
											"发布失败" + publishCargoState,
											Toast.LENGTH_SHORT).show();
									Intent intent2 = new Intent(
											PublishGoodsActivity_Is.this,
											PubGoodsFail.class);
									Bundle bundle = new Bundle();

									bundle.putString("userId", userId);
									bundle.putString("deliTime", deliTime);
									bundle.putString("transportTypeId",
											transportTypeId);
									bundle.putString("cargoTypeId", cargoTypeId);
									bundle.putString("cargoInfoStart",
											cargoInfoStart);
									bundle.putString("cargoInfoSStreet",
											cargoInfoSStreet);
									bundle.putString("cargoInfoEnd",
											cargoInfoEnd);
									bundle.putString("cargoInfoEStreet",
											cargoInfoEStreet);
									bundle.putString("cargoInfoLng", "38.2");
									bundle.putString("cargoInfoLat", "38.2");
									bundle.putString("cargoInfoELng", "38.2");
									bundle.putString("cargoInfoELat", "38.2");
									bundle.putString("cargoInfoPrice",
											cargoInfoPrice);
									bundle.putString("cargoInfoLenth",
											cargoInfoLenth);
									bundle.putString("cargoInfoWidth",
											cargoInfoWidth);
									bundle.putString("cargoInfoHeight",
											cargoInfoHeight);
									bundle.putString("cargoInfoRlen",
											cargoInfoRlen);
									bundle.putString("cargoInfoVunit", "方");
									bundle.putString("cargoInfoLunit", "吨");
									bundle.putString("cargoInfoVolume",
											cargoInfoVolume);
									bundle.putString("cargoInfoLoad",
											cargoInfoLoad);

									bundle.putString("cargoInfoDesc",
											cargoInfoDesc);
									bundle.putString("cargoInfoContacts",
											cargoInfoContacts);
									bundle.putString("cargoInfoContactWay",
											cargoInfoContactWay);
									bundle.putString("cargoInfoPicturl",
											cargoInfoPicturl);
									finish();
									intent2.putExtras(bundle);
									startActivity(intent2);

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
							Toast.makeText(PublishGoodsActivity_Is.this,
									"获取失败，请检查网络", Toast.LENGTH_SHORT).show();

						}
					}, mMap);
			requestQueue.add(jsonObjectPostRequest);
			break;

		}

	}

}