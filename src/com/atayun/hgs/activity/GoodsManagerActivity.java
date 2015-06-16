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
import com.atayun.hgs.adapter.GoodsListAdpter;
import com.atayun.hgs.adapter.GoodsManaAdapter;
import com.atayun.hgs.dao.GetGoodsInfoDao;
import com.atayun.hgs.fragment.HomeLayoutLeft;
import com.atayun.hgs.modle.MatchGoodsInfoItems;
import com.atayun.hgs.modle.GoodsInfoItems;
import com.atayun.hgs.modle.IDApplication;
import com.atayun.hgs.url.ConnData;
import com.atayun.hgs.util.JsonObjectPostRequest;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class GoodsManagerActivity extends Activity {

	private String userId = "";
	boolean flag = false;// 是否获取到货物数据的标志
	private ListView listview = null;
	private ArrayList<GoodsInfoItems> goodsInfoItems = new ArrayList<GoodsInfoItems>();
	private Map<String, String> mMap;// 用于封装 完成注册提交的参数
	private String url = "http://121.40.50.7:8080/wuLiuServer/cargoInfo.do";

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_goodsmanager);
		userId = ((IDApplication) getApplication()).getUserId();

		Task task = new Task();
		task.execute();
	}

	private void initview() {
		listview = (ListView) findViewById(R.id.list);
		List<Map<String, Object>> data = getData();
		listview.setAdapter(new GoodsManaAdapter(this, data));
		listview.setOnItemClickListener(new ItemClickEvent());

	}

	private class Task extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Toast.makeText(GoodsManagerActivity.this, "task 开始运行",
			// Toast.LENGTH_SHORT).show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			// 模拟耗时操作 比如网络连接等

			getmanagergoods();

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

	// 请求获取我已发的货物
	private void getmanagergoods() {
		mMap = new HashMap<String, String>();
		mMap.put("method", "getMyCargoInfoById");
		mMap.put("userId", userId);
		// Toast.makeText(GoodsManagerActivity.this,userId,
		// Toast.LENGTH_SHORT).show();
		RequestQueue requestQueue = Volley.newRequestQueue(this);
		JsonObjectPostRequest jsonObjectPostRequest = new JsonObjectPostRequest(
				url, new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						try {

							// 解析数据
							String getMyCarInfoState = response
									.getString("getMyCarInfoState");
							switch (getMyCarInfoState) {
							case "200":// 查询成功

								JSONArray Cargo = response
										.getJSONArray("Cargo");
								goodsInfoItems = GetGoodsInfoDao
										.getGoodsInfoDao(Cargo);
								Log.d("size", goodsInfoItems.size() + "");

								flag = true;
								Toast.makeText(GoodsManagerActivity.this,
										"200", Toast.LENGTH_SHORT).show();
								Log.d("Cargo", Cargo.toString());
								break;
							case "10":// 查询失败

								Toast.makeText(GoodsManagerActivity.this,
										"获取货物信息失败2", Toast.LENGTH_SHORT).show();

								break;
							default:
								break;
							}
							// 获取到数据，更新ui
							initview();
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						Toast.makeText(GoodsManagerActivity.this, "获取货物信息失败",
								Toast.LENGTH_SHORT).show();

					}
				}, mMap);
		requestQueue.add(jsonObjectPostRequest);
		// Log.d("size2", goodsInfoItems.size()+"");
		// Toast.makeText(GoodsManagerActivity.this, goodsInfoItems.size()+"",
		// Toast.LENGTH_SHORT).show();
	}

	private List<Map<String, Object>> getData() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		//
		for (int i = 0; i < goodsInfoItems.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();

			map.put("id", i);

			// 把listview显示的内容存入
			map.put("cargoInfoStart", goodsInfoItems.get(i).getCargoInfoStart());
			map.put("cargoInfoEnd", goodsInfoItems.get(i).getCargoInfoEnd());
			map.put("cargoInfoDesc", goodsInfoItems.get(i).getCargoInfoDesc());
			map.put("cargoInfoDeliTime", goodsInfoItems.get(i)
					.getCargoInfoDeliTime());
			map.put("cargoInfoPublished", goodsInfoItems.get(i)
					.getCargoInfoPublished());
			list.add(map);
		}
		return list;
	}

	public class ItemClickEvent implements OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Intent intent = new Intent(GoodsManagerActivity.this,
					MyResources.class);

			Bundle bundle = new Bundle();
			// 使用bundle向GoodsDetailActivity传递数据
			bundle.putString("cargoInfoStart", goodsInfoItems.get(position)
					.getCargoInfoStart());
			bundle.putString("cargoInfoEnd", goodsInfoItems.get(position)
					.getCargoInfoEnd());
			bundle.putString("cargoInfoLoad", goodsInfoItems.get(position)
					.getCargoInfoLoad());
			bundle.putString("cargoInfoLenth", goodsInfoItems.get(position)
					.getCargoInfoLenth());
			bundle.putString("cargoInfoWidth", goodsInfoItems.get(position)
					.getCargoInfoWidth());
			bundle.putString("cargoInfoHeight", goodsInfoItems.get(position)
					.getCargoInfoHeight());
			bundle.putString("cargoInfoVolume", goodsInfoItems.get(position)
					.getCargoInfoVolume());
			bundle.putString("cargoInfoPrice", goodsInfoItems.get(position)
					.getCargoInfoPrice());
			bundle.putString("cargoInfoContactWay", goodsInfoItems
					.get(position).getCargoInfoContactWay());
			bundle.putString("cargoInfoContacts", goodsInfoItems.get(position)
					.getCargoInfoContacts());
			// 后添加
			bundle.putString("userName", goodsInfoItems.get(position)
					.getUserName());
			bundle.putString("cargoInfoSStreet", goodsInfoItems.get(position)
					.getCargoInfoSStreet());
			bundle.putString("cargoInfoRlen", goodsInfoItems.get(position)
					.getCargoInfoRlen());
			bundle.putString("cargoInfoDeliTime", goodsInfoItems.get(position)
					.getCargoInfoDeliTime());
			bundle.putString("cargoType", goodsInfoItems.get(position)
					.getCargoType());
			bundle.putString("cargoInfoId", goodsInfoItems.get(position)
					.getCargoInfoId());
			bundle.putString("userId", goodsInfoItems.get(position).getUserId());
			// Log.d("cargoInfoStart", goodsInfoItems.get(position)
			// .getCargoInfoStart());
			// Log.d("cargoInfoId",
			// goodsInfoItems.get(position).getCargoInfoId());

			bundle.putString("userName", goodsInfoItems.get(position)
					.getUserName());
			bundle.putString("cargoInfoDeliTime", goodsInfoItems.get(position)
					.getCargoInfoDeliTime());
			bundle.putString("cargoInfoSStreet", goodsInfoItems.get(position)
					.getCargoInfoSStreet());
			bundle.putString("cargoInfoEStreet", goodsInfoItems.get(position)
					.getCargoInfoEStreet());
			bundle.putString("cargoInfoDesc", goodsInfoItems.get(position)
					.getCargoInfoDesc());
			bundle.putString("cargoType", goodsInfoItems.get(position)
					.getCargoType());
			bundle.putString("transportType", goodsInfoItems.get(position)
					.getTransportType());
			bundle.putString("cargoInfoFlag", goodsInfoItems.get(position)
					.getCargoInfoFlag());
			bundle.putString("cargoInfoRlen", goodsInfoItems.get(position)
					.getCargoInfoRlen());

			intent.putExtras(bundle);
			startActivity(intent);

		}

	}
	
	//系统返回键的监听
		 @Override  
		    public boolean onKeyDown(int keyCode, KeyEvent event)  
		    {  
		        if (keyCode == KeyEvent.KEYCODE_BACK )  
		        {  
		           //当back键按下，返回主界面
		        	
		        	Intent intent2=new Intent(GoodsManagerActivity.this,GoodsPersonCenter.class);
					finish();
					startActivity(intent2);
		        }  
		          
		        return false;  
		          
		    }  
	
	
}
