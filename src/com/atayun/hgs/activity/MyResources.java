package com.atayun.hgs.activity;

import java.util.Calendar;
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

import android.support.v7.app.ActionBarActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

public class MyResources extends ActionBarActivity {
	private String url_deleteCargoByCainId = ConnData.url_deleteCargoByCainId;
	private Map<String, String> mMap = new HashMap<String, String>();
	private Map<String, String> mMap3 = new HashMap<String, String>();
	private Map<String, String> mMap2 = new HashMap<String, String>();
	private Button bt_edit;
	private Button bt_delete;
	private Button bt_back;
	private Button bt_save;
	private Button bt_com_ac;

	private TextView mr_com_qualifi;// 公司资职
	private EditText mr_sent_start;// 发货线路
	private EditText mr_sent_end;//
	private EditText mr_t;// 货物规格
	private EditText mr_l;
	private EditText mr_w;
	private EditText mr_h;
	private EditText mr_fang;
	private EditText mr_fee;// 运费
	private Spinner mr_isdiscuss;// 是否议价、
	private Spinner mr_convey_type;// 货运类型
	private Spinner mr_goods_type;// 货源类型
	private Spinner mr_need_cartype;// 需要车型
	private Spinner mr_need_carlength;// 需要车长

	private EditText mr_send_time;// 发货时间
	private EditText mr_contact_name;// 联系人姓名
	private EditText mr_contact_number;// 联系人电话
	private EditText mr_content;// 信息内容
	private EditText mr_start_detail;// 出发详细地址
	private EditText mr_end_detail;// 到达详细地址

	private String cargoType = "";// 货物类型
	private String cargoTypeId = "";// 货源类型ID
	private String transportType = "";// 运输类型
	private String transportTypeId = "";// 运输类型ID 16-20

	private String cargoInfoFlag = "";// 货物状态，（0 未被运输； 1 已运输已运输货物不在编辑）

	private String cargoInfoRlen = "";// 需求车长
	private String cargoInfoRlenId = "";
	private String cargoInfoId = "";// 货物唯一标识
	private String userId = "";
	private String userName = "";// 公司资职
	private String str_com_ac = "已认证";// 公司资职
	private String cargoInfoStart = "长沙";// 发货线路
	private String cargoInfoEnd = "北京";//
	private String cargoInfoLoad = "20";// 货物规格
	private String cargoInfoLenth = "6";
	private String cargoInfoWidth = "3";
	private String cargoInfoHeight = "2";
	private String cargoInfoVolume = "30";
	private String cargoInfoPrice = "5000";// 运费
	private String deliTime;
	private String cargoInfoDeliTime = "2015-6-9";// 发货时间
	private String cargoInfoContacts = "张三";// 联系人姓名
	private String cargoInfoContactWay = "15299999999";// 联系人电话
	private String cargoInfoDesc = "麻烦快点";// 信息内容
	private String cargoInfoSStreet = "长沙市政府";// 出发详细地址
	private String cargoInfoEStreet = "北京市政府";// 到达详细地址
	final Calendar c = Calendar.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.myresource);
		// 使用Bundle获取来自GoodsManagerActivity的数据
		Bundle bundle = this.getIntent().getExtras();
		cargoInfoStart = bundle.getString("cargoInfoStart");
		cargoInfoEnd = bundle.getString("cargoInfoEnd");
		cargoInfoLoad = bundle.getString("cargoInfoLoad");
		cargoInfoLenth = bundle.getString("cargoInfoLenth");
		cargoInfoWidth = bundle.getString("cargoInfoWidth");
		cargoInfoHeight = bundle.getString("cargoInfoHeight");
		cargoInfoVolume = bundle.getString("cargoInfoVolume");
		cargoInfoPrice = bundle.getString("cargoInfoPrice");
		cargoInfoContactWay = bundle.getString("cargoInfoContactWay");
		cargoInfoContacts = bundle.getString("cargoInfoContacts");
		cargoInfoId = bundle.getString("cargoInfoId");
		userId = bundle.getString("userId");
		userName = bundle.getString("userName");
		cargoInfoDeliTime = bundle.getString("cargoInfoDeliTime");
		cargoInfoSStreet = bundle.getString("cargoInfoSStreet");
		cargoInfoEStreet = bundle.getString("cargoInfoEStreet");
		cargoInfoDesc = bundle.getString("cargoInfoDesc");
		cargoType = bundle.getString("cargoType");
		transportType = bundle.getString("transportType");
		cargoInfoFlag = bundle.getString("cargoInfoFlag");
		cargoInfoRlen = bundle.getString("cargoInfoRlen");
		deliTime = cargoInfoDeliTime;
		initView();
		makeFalse(false);
	}

	// 设置内容是否可编辑
	private void makeFalse(boolean flag) {
		mr_sent_start.setEnabled(flag);// 发货线路
		mr_sent_end.setEnabled(flag);//
		mr_t.setEnabled(flag);// 货物规格
		mr_l.setEnabled(flag);
		mr_w.setEnabled(flag);
		mr_h.setEnabled(flag);
		mr_fang.setEnabled(flag);
		mr_fee.setEnabled(flag);// 运费
		mr_isdiscuss.setEnabled(flag);// 是否议价、
		mr_convey_type.setEnabled(flag);// 货运类型
		mr_goods_type.setEnabled(flag);// 货源类型
		mr_need_cartype.setEnabled(flag);// 需要车型
		mr_need_carlength.setEnabled(flag);// 需要车长

		mr_send_time.setEnabled(flag);// 发货时间
		mr_contact_name.setEnabled(flag);// 联系人姓名
		mr_contact_number.setEnabled(flag);// 联系人电话
		mr_content.setEnabled(flag);// 信息内容
		mr_start_detail.setEnabled(flag);// 出发详细地址
		mr_end_detail.setEnabled(flag);// 到达详细地址

		if (flag) {
			bt_save.setVisibility(View.VISIBLE);
		} else {
			bt_save.setVisibility(View.GONE);
		}
	}

	private void initView() {
		MyListener l = new MyListener();
		bt_edit = (Button) findViewById(R.id.mr_edit);
		bt_delete = (Button) findViewById(R.id.mr_delete);
		bt_back = (Button) findViewById(R.id.mr_back);
		bt_save = (Button) findViewById(R.id.mr_save);
		bt_com_ac = (Button) findViewById(R.id.mr_com_ac);

		mr_com_qualifi = (TextView) findViewById(R.id.mr_com_qualifi);// 公司资职
		mr_sent_start = (EditText) findViewById(R.id.mr_sent_start);// 发货线路
		mr_sent_end = (EditText) findViewById(R.id.mr_sent_end);//
		mr_t = (EditText) findViewById(R.id.mr_t);// 货物规格
		mr_l = (EditText) findViewById(R.id.mr_l);
		mr_w = (EditText) findViewById(R.id.mr_w);
		mr_h = (EditText) findViewById(R.id.mr_h);
		mr_fang = (EditText) findViewById(R.id.mr_fang);
		mr_fee = (EditText) findViewById(R.id.mr_fee);// 运费
		mr_isdiscuss = (Spinner) findViewById(R.id.mr_is_discuss);// 是否议价、
		mr_convey_type = (Spinner) findViewById(R.id.mr_convey_type);// 货运类型
		mr_goods_type = (Spinner) findViewById(R.id.mr_goods_type);// 货源类型
		mr_need_cartype = (Spinner) findViewById(R.id.mr_need_cartype);// 需要车型
		mr_need_carlength = (Spinner) findViewById(R.id.mr_need_carlength);// 需要车长

		mr_send_time = (EditText) findViewById(R.id.mr_send_time);// 发货时间
		mr_contact_name = (EditText) findViewById(R.id.mr_contact_name);// 联系人姓名
		mr_contact_number = (EditText) findViewById(R.id.mr_contact_number);// 联系人电话
		mr_content = (EditText) findViewById(R.id.mr_content);// 信息内容
		mr_start_detail = (EditText) findViewById(R.id.mr_start_detail);// 出发详细地址
		mr_end_detail = (EditText) findViewById(R.id.mr_end_detail);// 到达详细地址
		// 设置文字内容

		bt_com_ac.setText(str_com_ac);
		mr_com_qualifi.setText(userName);
		mr_sent_start.setText(cargoInfoStart);// 发货线路
		mr_sent_end.setText(cargoInfoEnd);//
		mr_t.setText(cargoInfoLoad);// 货物规格
		mr_l.setText(cargoInfoLenth);
		mr_w.setText(cargoInfoWidth);
		mr_h.setText(cargoInfoHeight);
		mr_fang.setText(cargoInfoVolume);
		mr_fee.setText(cargoInfoPrice);// 运费

		mr_send_time.setText(cargoInfoDeliTime);// 发货时间
		mr_contact_name.setText(cargoInfoContacts);// 联系人姓名
		mr_contact_number.setText(cargoInfoContactWay);// 联系人电话
		mr_content.setText(cargoInfoDesc);// 信息内容
		mr_start_detail.setText(cargoInfoSStreet);// 出发详细地址
		mr_end_detail.setText(cargoInfoEStreet);// 到达详细地址

		bt_back.setOnClickListener(l);
		bt_delete.setOnClickListener(l);
		bt_edit.setOnClickListener(l);
		bt_save.setOnClickListener(l);
		mr_send_time.setOnClickListener(l);

		// 货运类型，货源类型value，key封装到map，方便讲得到的类型转换成key
		/**************************************************************************/
		mMap.put("零担货物", "0");
		mMap.put("整件货物", "1");
		mMap.put("集装箱", "2");
		mMap.put("特快件货物", "3");
		mMap.put("危险货物", "4");
		mMap.put("设备", "0");
		mMap.put("矿产", "1");
		mMap.put("建材", "2");
		mMap.put("食品", "3");
		mMap.put("蔬菜", "4");
		mMap.put("生鲜", "6");
		mMap.put("药品", "7");
		mMap.put("化工", "8");
		mMap.put("木材", "9");
		mMap.put("家畜", "10");
		mMap.put("纺织品", "11");
		mMap.put("日用品", "12");
		mMap.put("电子电器", "13");
		mMap.put("农副产品", "14");
		mMap.put("其他类型", "15");
		mMap.put("其他类型", "15");
		mMap.put("是", "0");
		mMap.put("否", "1");
		mMap.put("车辆长/米", "0");
		mMap.put("4.5", "1");
		mMap.put("6.2", "2");
		mMap.put("6.8", "3");
		mMap.put("7.2", "4");
		mMap.put("8.2", "5");
		mMap.put("8.6", "6");
		mMap.put("9.6", "7");
		mMap.put("11.7", "8");
		mMap.put("12.5", "9");
		mMap.put("13", "10");
		/********************************************************************/

		transportTypeId = mMap.get(transportType);
		int i = Integer.parseInt(transportTypeId);
		mr_convey_type.setSelection(i);// 初始化显示原来选择的类型
		// 设置下拉列表的监听运输类型
		mr_convey_type
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						transportTypeId = arg2 + 16 + "";
						Log.d("transportTypeId", transportTypeId);
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}

				});
		cargoTypeId = mMap.get(cargoType);
		int a = Integer.parseInt(cargoTypeId);
		mr_goods_type.setSelection(a);// 初始化显示原来选择的类型
		// 下拉列表监听货物类型
		mr_goods_type
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						cargoTypeId = arg2 + 1 + "";
						Log.d("cargoTypeId", cargoTypeId);
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}

				});
		// cargoInfoRlenId = mMap.get(cargoInfoRlen);
		// int b = Integer.parseInt(cargoInfoRlenId);
		// mr_need_carlength.setSelection(b);// 初始化显示原来选择的类型
		// 下拉列表监听选择需求车辆长度
		mr_need_carlength
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						cargoInfoRlen = (String) mr_need_carlength
								.getItemAtPosition(arg2);
						Log.d("cargoInfoRlen", cargoInfoRlen);
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}

				});

	}

	// 删除货物
	private void delectordergoods() {
		mMap3 = new HashMap<String, String>();
		mMap3.put("method", "deleteCargoByCainId");
		mMap3.put("cargoInfoId", cargoInfoId);

		RequestQueue requestQueue = Volley.newRequestQueue(this);
		JsonObjectPostRequest jsonObjectPostRequest = new JsonObjectPostRequest(
				url_deleteCargoByCainId, new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						try {

							// 解析数据
							String deleteCargoState = response
									.getString("deleteCargoState");
							switch (deleteCargoState) {
							case "200":// 查询成功
								Toast.makeText(MyResources.this, "货物删除成功",
										Toast.LENGTH_SHORT).show();
								Intent intent = new Intent(MyResources.this,
										GoodsManagerActivity.class);
								finish();
								startActivity(intent);
								break;
							case "10":// 查询失败

								Toast.makeText(MyResources.this, "货物删除失败",
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
						Toast.makeText(MyResources.this, "网络原因出错",
								Toast.LENGTH_SHORT).show();

					}
				}, mMap3);
		requestQueue.add(jsonObjectPostRequest);

	}

	// 修改货物
	private void modifygoods() {
		if (cargoInfoRlen.equals("车辆长/米")) {
			Toast.makeText(MyResources.this, "请选择需要车长", Toast.LENGTH_SHORT)
					.show();
			return;
		}
		cargoInfoStart = mr_sent_start.getText().toString().trim();
		cargoInfoSStreet = mr_start_detail.getText().toString().trim();
		cargoInfoEnd = mr_sent_end.getText().toString().trim();
		cargoInfoEStreet = mr_end_detail.getText().toString().trim();
		cargoInfoPrice = mr_fee.getText().toString().trim();
		cargoInfoLenth = mr_l.getText().toString().trim();
		cargoInfoWidth = mr_w.getText().toString().trim();
		cargoInfoVolume = mr_fang.getText().toString().trim();
		cargoInfoLoad = mr_t.getText().toString().trim();
		cargoInfoDesc = mr_content.getText().toString().trim();
		cargoInfoContacts = mr_contact_name.getText().toString().trim();
		cargoInfoContactWay = mr_contact_number.getText().toString().trim();

		mMap2 = new HashMap<String, String>();
		mMap2.put("method", "modifyCargoInfoByCainId");
		mMap2.put("cargoInfoId", cargoInfoId);

		mMap2.put("deliTime", deliTime);
		mMap2.put("transportTypeId", transportTypeId);
		mMap2.put("cargoTypeId", cargoTypeId);
		mMap2.put("cargoInfoStart", cargoInfoStart);
		mMap2.put("cargoInfoSStreet", cargoInfoSStreet);
		mMap2.put("cargoInfoEnd", cargoInfoEnd);
		mMap2.put("cargoInfoEStreet", cargoInfoEStreet);
		mMap2.put("cargoInfoLng", "3353.8");
		mMap2.put("cargoInfoLat", "3353.8");
		mMap2.put("cargoInfoELng", "3353.8");
		mMap2.put("cargoInfoELat", "3353.8");
		mMap2.put("cargoInfoPrice", cargoInfoPrice);
		mMap2.put("cargoInfoLenth", cargoInfoLenth);
		mMap2.put("cargoInfoWidth", cargoInfoWidth);
		mMap2.put("cargoInfoHeight", cargoInfoHeight);
		mMap2.put("cargoInfoRlen", cargoInfoRlen);
		mMap2.put("cargoInfoVunit", "立方米");
		mMap2.put("cargoInfoLunit", "吨");
		mMap2.put("cargoInfoVolume", cargoInfoVolume);
		mMap2.put("cargoInfoLoad", cargoInfoLoad);
		mMap2.put("cargoInfoDesc", cargoInfoDesc);
		mMap2.put("cargoInfoContacts", cargoInfoContacts);
		mMap2.put("cargoInfoContactWay", cargoInfoContactWay);
		mMap2.put("cargoInfoPicturl", "69844.jpg");

		Log.d("cargoInfoId", cargoInfoId);
		Log.d("deliTime", deliTime);
		Log.d("transportTypeId", transportTypeId);
		Log.d("cargoTypeId", cargoTypeId);
		Log.d("cargoInfoStart", cargoInfoStart);
		Log.d("cargoInfoSStreet", cargoInfoSStreet);
		Log.d("cargoInfoEnd", cargoInfoEnd);
		Log.d("cargoInfoEStreet", cargoInfoEStreet);
		Log.d("cargoInfoPrice", cargoInfoPrice);
		Log.d("cargoInfoLenth", cargoInfoLenth);
		Log.d("cargoInfoWidth", cargoInfoWidth);
		Log.d("cargoInfoHeight", cargoInfoHeight);
		Log.d("cargoInfoRlen", cargoInfoRlen);
		Log.d("cargoInfoVolume", cargoInfoVolume);
		Log.d("cargoInfoLoad", cargoInfoLoad);
		Log.d("cargoInfoDesc", cargoInfoDesc);
		Log.d("cargoInfoContacts", cargoInfoContacts);
		Log.d("cargoInfoContactWay", cargoInfoContactWay);

		RequestQueue requestQueue = Volley.newRequestQueue(this);
		JsonObjectPostRequest jsonObjectPostRequest = new JsonObjectPostRequest(
				url_deleteCargoByCainId, new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						try {

							// 解析数据
							String modifyCargoState = response
									.getString("modifyCargoState");
							switch (modifyCargoState) {
							case "200":// 查询成功
								Toast.makeText(MyResources.this, "货物修改成功",
										Toast.LENGTH_SHORT).show();
								initView();
								// Intent intent = new Intent(
								// MyResources.this,
								// GoodsManagerActivity.class);
								// finish();
								// startActivity(intent);
								break;
							case "10":// 查询失败

								Toast.makeText(MyResources.this, "货物修改失败",
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
						Toast.makeText(MyResources.this, "获取货物信息失败",
								Toast.LENGTH_SHORT).show();

					}
				}, mMap2);
		requestQueue.add(jsonObjectPostRequest);

	}

	// 监听
	public class MyListener implements OnClickListener {

		@Override
		public void onClick(View view) {
			switch (view.getId()) {
			case R.id.mr_edit:// 编辑
				makeFalse(true);

				break;
			case R.id.mr_delete:// 删除
				createDialog();
				break;
			case R.id.mr_back:// 返回
              Intent intent=new Intent(MyResources.this,GoodsManagerActivity.class);
              finish();
              startActivity(intent);
				break;
			case R.id.mr_save:// 保存
				makeFalse(false);
				modifygoods();
				break;
			case R.id.mr_send_time:// 点击产生日历
				createCalendar();
				break;
			default:
				break;
			}
		}

		// 产生日历
		private void createCalendar() {

			DatePickerDialog dialog = new DatePickerDialog(MyResources.this,
					new DatePickerDialog.OnDateSetListener() {
						@Override
						public void onDateSet(DatePicker view, int year,
								int monthOfYear, int dayOfMonth) {
							c.set(year, monthOfYear, dayOfMonth);
							mr_send_time.setText(DateFormat.format("yyy-MM-dd",
									c));
							deliTime = mr_send_time.getText().toString().trim();
						}
					}, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c
							.get(Calendar.DAY_OF_MONTH));
			dialog.show();

		}

	}

	// 产生确定对话框
	public void createDialog() {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("是否确定删除该货物？");
		builder.setPositiveButton("删除", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface arg0, int arg1) {
				// Toast.makeText(MyResources.this, "确定", Toast.LENGTH_LONG)
				// .show();
				delectordergoods();
			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface arg0, int arg1) {
				Toast.makeText(MyResources.this, "取消", Toast.LENGTH_LONG)
						.show();

			}
		});
		builder.show();
	}
	//系统返回键的监听
	 @Override  
	    public boolean onKeyDown(int keyCode, KeyEvent event)  
	    {  
	        if (keyCode == KeyEvent.KEYCODE_BACK )  
	        {  
	           //当back键按下，返回主界面
	        	
	        	Intent intent2=new Intent(MyResources.this,GoodsManagerActivity.class);
				finish();
				startActivity(intent2);
	        }  
	          
	        return false;  
	          
	    }  
	
	
}
