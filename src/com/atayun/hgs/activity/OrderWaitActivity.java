package com.atayun.hgs.activity;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.atayun.hgs.util.JsonObjectPostRequest;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class OrderWaitActivity extends Activity {

	private Map<String, String> mMap;
	// private ArrayList<OrderDetail> orderdetail = new
	// ArrayList<OrderDetail>();
	private String orderId;
	private String url = "http://121.40.50.7:8080/wuLiuServer/order.do";

	private Button bt_back, bt_applyorder;
	private TextView co_OrderNo;// 订单号
	private TextView co_qiye;// 企业单位
	private TextView co_linkman;// 联系人
	private TextView co_goods_info;// 货物信息
	private TextView co_send_line;// 发货线路
	private TextView co_distance;// 公里数
	private TextView co_send_place;// 发货地址
	private TextView co_detail_goal;// 目的地详细地址
	private TextView co_price;// 运费
	private TextView co_phone;// 电话
	private TextView co_orddCSUBSPRICE;// 车主此货物的保证金
	private TextView co_orddHSUBSPRICE;// huo主此货物的保证金
	private ImageView co_example_pic1, co_example_pic2;// 两张图片
	private ImageView co_ac_symbol;// 认证标志
	private ImageButton co_map_line;// 查看线路
	private ImageButton co_call;// 电话标志

	private String str_orddCSUBSPRICE;// 车主此货物的保证金
	private String str_orddHSUBSPRICE;// huo主此货物的保证金
	private String str_OrderNo;
	private String str_qiye;
	private String str_linkman;
	private String str_goods_info;
	private String str_line_start;
	private String str_line_end;
	private String str_distance;
	private String str_send_place;
	private String str_detail_goal;
	private String str_price;
	private String str_phone;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.om_check_order);

		Intent intent = this.getIntent();
		Bundle bundle = intent.getExtras();
		orderId = bundle.getString("OrderId");

		initView();

		Task task = new Task();
		task.execute();
	}

	private class Task extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... params) {
			// 模拟耗时操作 比如网络连接等
			getwait();
			// 判断如果task已经cancel就没有必须继续进行下面的操作
			if (!isCancelled()) {
				// System.out.println("task 如果被cancel,就不会显示");
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			// Toast.makeText(GoodsManagerActivity.this,
			// "task 完成",Toast.LENGTH_SHORT).show();
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

	private void getwait() {
		mMap = new HashMap<String, String>();
		mMap.put("method", "getOrderDetalilByID");
		mMap.put("OrderId", orderId);

		// 请求服务
		RequestQueue requestQueue = Volley.newRequestQueue(this);
		JsonObjectPostRequest jsonObjectPostRequest = new JsonObjectPostRequest(
				url, new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						try {
							if (response == null) {

								Toast.makeText(OrderWaitActivity.this,
										"获取数据成功", Toast.LENGTH_SHORT).show();

							} else {
								String getOrderDetailState = response
										.getString("getOrderDetailState");

								switch (getOrderDetailState) {
								case "200":
									Toast.makeText(OrderWaitActivity.this,
											"登录成功", Toast.LENGTH_SHORT).show();
									JSONObject OrderDetail = response
											.getJSONObject("OrderDetail");

									str_orddCSUBSPRICE = OrderDetail
											.getString("orddCSUBSPRICE");
									str_orddHSUBSPRICE = OrderDetail
											.getString("orddHSUBSPRICE");
									str_OrderNo = OrderDetail
											.getString("orderNo");
									str_qiye = "王老吉";
									str_linkman = OrderDetail
											.getString("CargoInfoContacts");
									str_goods_info = "高"
											+ OrderDetail
													.getString("cargoInfoHeight")
											+ "米"
											+ "宽"
											+ OrderDetail
													.getString("cargoInfoWidth")
											+ "米"
											+ "长"
											+ OrderDetail
													.getString("cargoInfoLenth")
											+ "米";
									str_line_start = OrderDetail
											.getString("CargoInfoStart");
									str_line_end = OrderDetail
											.getString("CargoInfoEnd");
									str_distance = "500公里";
									str_send_place = OrderDetail
											.getString("cargoInfoSStreet");
									str_detail_goal = OrderDetail
											.getString("cargoInfoEStreet");
									str_price = OrderDetail
											.getString("OrderPrice");
									str_phone = OrderDetail
											.getString("CargoInfoContactWay");

									break;
								case "10":
									Toast.makeText(OrderWaitActivity.this,
											"没有待处理订单", Toast.LENGTH_SHORT)
											.show();
									break;

								default:
									break;
								}
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						Toast.makeText(OrderWaitActivity.this, "获取失败，请检查网络",
								Toast.LENGTH_SHORT).show();

					}
				}, mMap);
		requestQueue.add(jsonObjectPostRequest);
	}

	private void initView() {
		// TODO Auto-generated method stub
		bt_back = (Button) findViewById(R.id.co_back);
		bt_applyorder = (Button) findViewById(R.id.co_applyorder);
		co_orddCSUBSPRICE = (TextView) findViewById(R.id.co_orddCSUBSPRICE);
		co_orddHSUBSPRICE = (TextView) findViewById(R.id.co_orddHSUBSPRICE);

		co_OrderNo = (TextView) findViewById(R.id.co_order_num);// 订单号
		co_qiye = (TextView) findViewById(R.id.co_qiye);// 企业单位
		co_linkman = (TextView) findViewById(R.id.co_linkman);// 联系人
		co_goods_info = (TextView) findViewById(R.id.co_goods_info);// 货物信息
		co_send_line = (TextView) findViewById(R.id.co_send_line);// 发货线路
		co_distance = (TextView) findViewById(R.id.co_distance);// 公里数
		co_send_place = (TextView) findViewById(R.id.co_send_place);// 发货地址
		co_detail_goal = (TextView) findViewById(R.id.co_detail_goal);// 目的地详细地址
		co_price = (TextView) findViewById(R.id.co_price);// 运费
		co_phone = (TextView) findViewById(R.id.co_phone);//

		co_example_pic1 = (ImageView) findViewById(R.id.co_example_pic1);
		co_example_pic2 = (ImageView) findViewById(R.id.co_example_pic2);// 两张图片
		co_ac_symbol = (ImageView) findViewById(R.id.co_ac_symbol);// 认证标志
		co_map_line = (ImageButton) findViewById(R.id.co_map_line);// 查看线路
		co_call = (ImageButton) findViewById(R.id.co_call);// 电话标志

		co_orddCSUBSPRICE.setText("车主保证金:" + str_orddCSUBSPRICE);
		co_orddHSUBSPRICE.setText("货主保证金:" + str_orddHSUBSPRICE);

		co_OrderNo.setText("订单号：" + str_OrderNo);// 订单号
		co_qiye.setText(str_qiye);// 企业单位
		co_linkman.setText("联系人：" + str_linkman);// 联系人
		co_goods_info.setText("货物信息：" + str_goods_info);// 货物信息
		co_send_line.setText(str_line_start + "——" + str_line_end);// 发货线路
		co_distance.setText(str_distance + "米");// 公里数
		co_send_place.setText("起点详细地址：" + str_send_place);// 发货地址
		co_detail_goal.setText("终点详细地址：" + str_detail_goal);// 目的地详细地址
		co_price.setText("运费：" + str_price);// 价钱
		co_phone.setText("联系人电话：" + str_phone);// 电话

		co_example_pic1.setImageResource(R.drawable.ic_launcher);
		co_example_pic2.setImageResource(R.drawable.ic_launcher);// 两张图片
	}

}
