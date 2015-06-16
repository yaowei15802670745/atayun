package com.atayun.hgs.activity;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
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
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class PublishCarActivity_Is extends Activity implements OnClickListener {
	private String url = ConnData.publishCarInfo;
	private String url_getOwnerInfo = ConnData.getOwnerInfo;
	private Map<String, String> mMap;// 用于封装 完成注册提交的参数
	private Map<String, String> mMap2;// 用于封装 完成注册提交的参数
	private String userId = "17";
	private LinearLayout car_detail;
	private TextView car_id;// 车牌号
	private TextView car_type;// 车型
	private TextView car_lwh;// 车辆长宽高
	private TextView car_bear;// 承重量
	// private String str_id = "内容";
	// private String str_type = "内容";
	// private String str_lwh = "内容";
	// private String str_bear = "内容";
	private EditText start_place;// 开始城市
	private EditText end_place;// 到达城市
	private EditText car_contactsName;// 姓名
	private EditText car_contactsPhone;// 联系电话
	private String cariStart;
	private String cariEnd;
	private String  caroMobile;
	private String flag_length = "0";// 超长是否被选的标志0为选1选
	private String flag_width = "0";
	private String flag_heigth = "0";
	private String flag_weigth = "0";

	// 他画的那种按钮的样子是单选按钮的，但是要求是多选……
	private CheckBox over_length;
	private CheckBox over_width;
	private CheckBox over_height;
	private CheckBox over_weight;
	private Button bt_publish;// 发布按钮

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.sourcecar_is);
		 userId=((IDApplication)getApplication()).getUserId();
		Task task=new Task();//调用异步类，货物个人信息
		task.execute();
		
		// getOwnerInfo();
		initView();

	}

	private void initView() {
		car_detail = (LinearLayout) findViewById(R.id.car_detail);
		car_id = (TextView) findViewById(R.id.car_id);
		car_type = (TextView) findViewById(R.id.car_type);
		car_lwh = (TextView) findViewById(R.id.car_lwh);
		car_bear = (TextView) findViewById(R.id.car_bear);

		start_place = (EditText) findViewById(R.id.cariStart);
		end_place = (EditText) findViewById(R.id.cariEnd);
		car_contactsName = (EditText) findViewById(R.id.car_contactsName);
		car_contactsPhone = (EditText) findViewById(R.id.car_contactsPhone);

		over_length = (CheckBox) findViewById(R.id.over_lengh);
		over_width = (CheckBox) findViewById(R.id.over_width);
		over_height = (CheckBox) findViewById(R.id.over_height);
		over_weight = (CheckBox) findViewById(R.id.over_weight);
		bt_publish = (Button) findViewById(R.id.car_publish);
		bt_publish.setOnClickListener(this);

		over_length.setOnCheckedChangeListener(new myCheckListener());
		over_width.setOnCheckedChangeListener(new myCheckListener());
		over_height.setOnCheckedChangeListener(new myCheckListener());
		over_weight.setOnCheckedChangeListener(new myCheckListener());
		// car_detail.setOnClickListener(new MyClick());

		// 复选框的时间监听
		over_length
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {

						if (isChecked) {
							flag_length = "1";
						} else {
							flag_length = "0";
						}
					}
				});
		// 复选框的时间监听
		over_width
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {

						if (isChecked) {
							flag_width = "1";
						} else {
							flag_width = "0";
						}
					}
				});
		// 复选框的时间监听
		over_height
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {

						if (isChecked) {
							flag_heigth = "1";
						} else {
							flag_heigth = "0";
						}
					}
				});
		// 复选框的时间监听
		over_weight
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						//
						if (isChecked) {
							flag_weigth = "1";
						} else {
							flag_weigth = "0";
						}
					}
				});

	}
	
	
	
	private class Task extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			Toast.makeText(PublishCarActivity_Is.this, "task 开始运行",
					Toast.LENGTH_SHORT).show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			// 模拟耗时操作 比如网络连接等
			getOwnerInfo();
			// 判断如果task已经cancel就没有必须继续进行下面的操作
			if (!isCancelled()) {
				System.out.println("task 如果被cancel,就不会显示");
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			Toast.makeText(PublishCarActivity_Is.this, "task 完成",
					Toast.LENGTH_SHORT).show();
			//
			// return ;
			// task.cancel(true);
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
	
	

	public void getOwnerInfo() {
		mMap2 = new HashMap<String, String>();
		mMap2.put("method", "getOwnerInfo");
		mMap2.put("userId", userId);

		RequestQueue requestQueue = Volley.newRequestQueue(this);
		JsonObjectPostRequest jsonObjectPostRequest = new JsonObjectPostRequest(
				url_getOwnerInfo, new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						try {

							String infoState = response.getString("infoState");
							switch (infoState) {
							case "200":
								// 解析获取到的个人信息
								JSONObject infoObject = response
										.getJSONObject("infoObject");
								Log.d("infoObject", infoObject.toString());
								String cariLpnum=infoObject.getString("cariLpnum");
								String cariLength=infoObject.getString("cariLength");
								String cariWidth=infoObject.getString("cariWidth");
								String cariHeight=infoObject.getString("cariHeight");
								String cariLoad=infoObject.getString("cariLoad");
								
								
//								String cariLpnum = infoObject.getJSONObject(0)
//										.getString("cariLpnum");// 车牌号
//								String cariLength = infoObject.getJSONObject(0)
//										.getString("cariLength");// 长
//								String cariWidth = infoObject.getJSONObject(0)
//										.getString("cariWidth");// 宽
//								String cariHeight = infoObject.getJSONObject(0)
//										.getString("cariHeight");// 高
//								String cariLoad = infoObject.getJSONObject(0)
//										.getString("cariLoad");// 载重
								// 设置车的参数
								car_id.setText(cariLpnum);
								car_type.setText("未设置");
								car_lwh.setText("l:" + cariLength + " w:"
										+ cariWidth + " h:" + cariHeight);
								car_bear.setText(cariLoad);

								break;
							case "30":
								Toast.makeText(PublishCarActivity_Is.this,
										"获取不到该车主的个人消息", Toast.LENGTH_SHORT)
										.show();
								break;

							default:
								break;
							}

						} catch (JSONException e) {

							e.printStackTrace();
						}

					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						Toast.makeText(PublishCarActivity_Is.this, "获取失败，请检查网络",
								Toast.LENGTH_SHORT).show();

					}
				}, mMap2);
		requestQueue.add(jsonObjectPostRequest);

	}

	private  void car_publish() {

		cariStart = start_place.getText().toString().trim();
		cariEnd = end_place.getText().toString().trim();
		 caroMobile = car_contactsPhone.getText().toString().trim();
		// cariEnd=end_place.getText().toString().trim();

		mMap = new HashMap<String, String>();
		mMap.put("method", "publishCarInfo");
		mMap.put("userId", userId);
		mMap.put("infoFlag", "1");
		
		mMap.put("cariStart", cariStart);
		mMap.put("cariEnd", cariEnd);
		mMap.put("caroMobile", caroMobile);

		mMap.put("cariOlength", flag_length);
		mMap.put("cariOwidth", flag_width);
		mMap.put("cariOheight", flag_heigth);
		mMap.put("cariOload", flag_weigth);
		Log.d("userId", userId);
		Log.d("cariStart", cariStart);
		Log.d("cariEnd", cariEnd);
		Log.d("caroMobile", caroMobile);
		Log.d("cariOlength", flag_length);
		Log.d("cariOwidth", flag_width);
		Log.d("cariOheight", flag_heigth);
		Log.d("cariOload", flag_weigth);

		RequestQueue requestQueue = Volley.newRequestQueue(this);
		JsonObjectPostRequest jsonObjectPostRequest = new JsonObjectPostRequest(
				url, new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						try {

							String carState = response.getString("carState");
							Toast.makeText(PublishCarActivity_Is.this, carState,
									Toast.LENGTH_SHORT).show();
							Log.d("carState", carState);
							switch (carState) {
							case "200":
								Toast.makeText(PublishCarActivity_Is.this, "发布成功,进入货源匹配广场...",
										Toast.LENGTH_SHORT).show();
								// 跳转到匹配货源界面
								Intent intent=new Intent(PublishCarActivity_Is.this,MatchGoodsSqureActivity.class);
								finish();
								startActivity(intent);

								break;
							case "40":
								Toast.makeText(PublishCarActivity_Is.this, "发布失败",
										Toast.LENGTH_SHORT).show();
								break;
							case "20":
								Toast.makeText(PublishCarActivity_Is.this,
										"请完善个人信息", Toast.LENGTH_SHORT).show();
								break;

							default:
								break;
							}

						} catch (JSONException e) {

							e.printStackTrace();
						}

					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						Toast.makeText(PublishCarActivity_Is.this, "获取失败，请检查网络",
								Toast.LENGTH_SHORT).show();

					}
				}, mMap);
		requestQueue.add(jsonObjectPostRequest);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.car_publish:
			car_publish();
			break;

		}
	}

	public class myCheckListener implements OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
		}
	}
}
