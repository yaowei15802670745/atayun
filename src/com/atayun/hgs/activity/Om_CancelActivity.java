package com.atayun.hgs.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.atayun.hgs.adapter.Wh_Adspter;
import com.atayun.hgs.dao.OrderManagerDao;
import com.atayun.hgs.modle.GoodsInfoItems;
import com.atayun.hgs.modle.OrdermanagerIterm;
import com.atayun.hgs.util.JsonObjectPostRequest;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class Om_CancelActivity extends Activity {
	private Map<String, String> mMap;
	ArrayList<OrdermanagerIterm> ordermanagerIterm = new ArrayList<OrdermanagerIterm>();
	ArrayList<GoodsInfoItems> goodsInfoItems = new ArrayList<GoodsInfoItems>();
	private String userId = "23";
	private String url = "http://121.40.50.7:8080/wuLiuServer/order.do";
	private ListView wh_listview = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.wait_handle);
		// userId=((IDApplication) getApplication()).getUserId();
		initView();
		Task task = new Task();
		task.execute();
	}

	// 异步类
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
			// Toast.makeText(GoodsManagerActivity.this, "task 完成",
			// Toast.LENGTH_SHORT).show();

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
		mMap.put("method", "getOrderListByID");
		mMap.put("userId", userId);
		mMap.put("orderFlag", "3");

		// 请求服务
		RequestQueue requestQueue = Volley.newRequestQueue(this);
		JsonObjectPostRequest jsonObjectPostRequest = new JsonObjectPostRequest(
				url, new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						try {
							if (response == null) {

								Toast.makeText(Om_CancelActivity.this, "获取数据成功",
										Toast.LENGTH_SHORT).show();

							} else {
								String getOrderState = response.getString("getOrderState");

								switch (getOrderState) {
								case "200":
									Toast.makeText(Om_CancelActivity.this, "登录成功",Toast.LENGTH_SHORT).show();
									JSONArray OrderList = response.getJSONArray("OrderList");
									
									Log.d("OrderList", OrderList.toString());

									ordermanagerIterm = OrderManagerDao.getorderManagerDao(OrderList);

									// String
									// orderNo=OrderList.getJSONObject(0).getString("orderNo");

									break;
								case "10":
									Toast.makeText(Om_CancelActivity.this, "没有已取消订单",
											Toast.LENGTH_SHORT).show();
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
						Toast.makeText(Om_CancelActivity.this, "获取失败，请检查网络",
								Toast.LENGTH_SHORT).show();

					}
				}, mMap);
		requestQueue.add(jsonObjectPostRequest);

	}

	private void initView() {
		// TODO Auto-generated method stub
		wh_listview = (ListView) findViewById(R.id.wh_list);
		List<Map<String, Object>> data = getData();
		wh_listview.setAdapter(new Wh_Adspter(this, data));
		wh_listview.setOnItemClickListener(new ItemClickEvent());
	}

	private List<Map<String, Object>> getData() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (ordermanagerIterm.size() > 0) {
			for (int i = 0; i < ordermanagerIterm.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", i);
				// 把listview显示的内容存入
				map.put("wh_OrderNo", ordermanagerIterm.get(i).getOrderNo());//订单编号
				map.put("wh_UpdateTime", ordermanagerIterm.get(i).getUpdateTime());//更新时间
				map.put("wh_OrderPrice", ordermanagerIterm.get(i).getOrderPrice());//价格
				map.put("Start_Place", "----");
				map.put("End_Place", "----");
				map.put("StartTime", "----");
				list.add(map);
			}
		}
		return list;
	}

	// 点击listvie项目的监听
	public class ItemClickEvent implements OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Bundle bundle = new Bundle();
			bundle.putString("orderId", ordermanagerIterm.get(position).getOrderId());
			Intent intent = new Intent(Om_CancelActivity.this, OrderWaitActivity.class);
			intent.putExtras(bundle);
			startActivity(intent);
		}
	}
}
