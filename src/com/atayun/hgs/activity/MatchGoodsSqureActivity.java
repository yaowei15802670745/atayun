package com.atayun.hgs.activity;


/*
 * 实现算法匹配返回的第一级页面，最佳匹配和其他匹配
 */
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
import com.atayun.hgs.activity.GoodsSquareActivity.ItemClickEvent;
import com.atayun.hgs.adapter.GoodsListAdpter;
import com.atayun.hgs.adapter.GoodsMatchAdapter;
import com.atayun.hgs.dao.GetMatchGoodsInfoDao;
import com.atayun.hgs.modle.IDApplication;
import com.atayun.hgs.modle.MatchGoodsInfoItems;
import com.atayun.hgs.modle.GoodsInfoItems;


import com.atayun.hgs.util.JsonObjectPostRequest;






import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MatchGoodsSqureActivity extends Activity {
	private String userId="24";
	private ListView listview = null;
	private String url_machgoods="http://121.40.50.7:8080/wuLiuServer/cargoInfo.do";
	private Map<String, String> mMap;// 用于封装 完成注册提交的参数
	public static ArrayList<MatchGoodsInfoItems> bestGoodsDetailInfoItems=new ArrayList<MatchGoodsInfoItems>();
	ArrayList<MatchGoodsInfoItems> otherGoodsDetailInfoItems=new ArrayList<MatchGoodsInfoItems>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.matchgoods_squre);
		userId = ((IDApplication) getApplication()).getUserId();
		Log.d("Matchgoods-userId", userId);
		Task task = new Task();
		task.execute();
		

		
	}
	
	private void initview(){
		listview = (ListView) findViewById(R.id.list);
		List<Map<String, Object>> data = getData();
		listview.setAdapter(new GoodsMatchAdapter(this, data));
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

			getmatchgoods();

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
	
	private void getmatchgoods(){
		
		
		mMap = new HashMap<String, String>();	
		mMap.put("method", "getCargoInfoByUserAll");
		mMap.put("userId", userId);
		RequestQueue requestQueue = Volley.newRequestQueue(this);
		
		JsonObjectPostRequest jsonObjectPostRequest = new JsonObjectPostRequest(
				url_machgoods, new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						try {

							String bestCargoState = response
									.getString("bestCargoState");
							String ortherCargoState=response.getString("ortherCargoState");
							

							if(bestCargoState.equals("200")) {
//								Toast.makeText(MatchGoodsSqureActivity.this, "获取最佳匹配数据成功",
//										Toast.LENGTH_SHORT).show();
								// 解析数据
								JSONArray BestMatch = response
										.getJSONArray("BestMatch");
								String bestMatch = BestMatch.toString();
								Log.d("bestMatch", bestMatch);
								
								bestGoodsDetailInfoItems =GetMatchGoodsInfoDao.matchGoodsInfoItems(BestMatch);
//                             
								Log.d("aaaa", bestGoodsDetailInfoItems.size()+"");
								if(ortherCargoState.equals("200")) {
	                            	// 解析数据
										JSONArray OrtherMatch = response
												.getJSONArray("OrtherMatch");
										
										Log.d("OrtherMatch", OrtherMatch.toString());
										
										otherGoodsDetailInfoItems =GetMatchGoodsInfoDao.matchGoodsInfoItems(OrtherMatch);
										Log.d("bbbbb", otherGoodsDetailInfoItems.size()+"");
										Log.d("ortherCargoState",ortherCargoState);
									
										
	                              }
								initview();//获取到数据后刷新界面  
								return;
							}else if(bestCargoState.equals("10")) {
								Toast.makeText(MatchGoodsSqureActivity.this, "没有查到相匹配的数据",
										Toast.LENGTH_SHORT).show();
								return;
		
							}
							
							else if(ortherCargoState.equals("10")){
                            	  Toast.makeText(MatchGoodsSqureActivity.this, "没有获取其他数据",
  										Toast.LENGTH_SHORT).show();
                            	  return;
                              }
							
						} catch (JSONException e) {
					
							e.printStackTrace();
						}
					
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						Toast.makeText(MatchGoodsSqureActivity.this, "获取失败，请检查网络",
								Toast.LENGTH_SHORT).show();

					}
				}, mMap);
		requestQueue.add(jsonObjectPostRequest);
		
		
		
		
	}

	private List<Map<String, Object>> getData() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//将最佳匹配的第一条数据装进list
		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("id", 0);
		
		map.put("cargoInfoStart", bestGoodsDetailInfoItems.get(0).getCargoInfoStart());
		map.put("cargoInfoEnd",bestGoodsDetailInfoItems.get(0).getCargoInfoEnd());
		map.put("cargoInfoDesc",bestGoodsDetailInfoItems.get(0).getCargoInfoDesc() );
		map.put("cargoInfoDeliTime",bestGoodsDetailInfoItems.get(0).getCargoInfoDeliTime());
		map.put("cargoInfoPublished", bestGoodsDetailInfoItems.get(0).getCargoInfoPublished());
		map.put("mareNum", "匹配货源"+bestGoodsDetailInfoItems.size()+"条");
		list.add(map);
		
		
		
		//如果存在其他数据，将其一起放到list中
   if( otherGoodsDetailInfoItems.size()>0){
	   
	   for (int i = 0; i < otherGoodsDetailInfoItems.size(); i++) {
			

//			map.put("id", i);
			
			// 把listview显示的内容存入
			map.put("cargoInfoStart", otherGoodsDetailInfoItems.get(i).getCargoInfoStart());
			map.put("cargoInfoEnd",otherGoodsDetailInfoItems.get(i).getCargoInfoEnd());
			map.put("cargoInfoDesc",otherGoodsDetailInfoItems.get(i).getCargoInfoDesc() );
			map.put("cargoInfoDeliTime",otherGoodsDetailInfoItems.get(i).getCargoInfoDeliTime());
			map.put("cargoInfoPublished", otherGoodsDetailInfoItems.get(i).getCargoInfoPublished());
//			map.put("Match_num", "匹配车源3条");
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
			
		// id和从position0开始
//		position=0，跳转到最佳匹配列表
			if(position==0){
				Intent intent=new Intent(MatchGoodsSqureActivity.this,BestSquareGoodsActivity.class);
				startActivity(intent);
		
			}else{
	
				Intent intent = new Intent(MatchGoodsSqureActivity.this, OtherGoodsDetailActivity.class);
			    Bundle bundle = new Bundle();
	           //使用bundle向OtherGoodsDetailActivity传递数据
			    //因为接下来的position从1开始，为了匹配otherGoodsDetailInfoItems中的数据，所以需要position-1
				bundle.putString("cargoInfoStart", otherGoodsDetailInfoItems.get(position-1).getCargoInfoStart());
				bundle.putString("cargoInfoEnd", otherGoodsDetailInfoItems.get(position-1).getCargoInfoEnd());
				bundle.putString("cargoInfoLoad", otherGoodsDetailInfoItems.get(position-1).getCargoInfoLoad());
				bundle.putString("cargoInfoLenth", otherGoodsDetailInfoItems.get(position-1).getCargoInfoLenth());
				bundle.putString("cargoInfoWidth", otherGoodsDetailInfoItems.get(position-1).getCargoInfoWidth());
				bundle.putString("cargoInfoHeight", otherGoodsDetailInfoItems.get(position-1).getCargoInfoHeight());
				bundle.putString("cargoInfoVolume", otherGoodsDetailInfoItems.get(position-1).getCargoInfoVolume());
				bundle.putString("cargoInfoPrice", otherGoodsDetailInfoItems.get(position-1).getCargoInfoPrice());
				bundle.putString("cargoInfoContactWay", otherGoodsDetailInfoItems.get(position-1).getCargoInfoContactWay());
				bundle.putString("cargoInfoContacts", otherGoodsDetailInfoItems.get(position-1).getCargoInfoContacts());
				bundle.putString("cagoCpName", otherGoodsDetailInfoItems.get(position-1).getCagoCpName());
		
				
				intent.putExtras(bundle);
				
				   finish();
				startActivity(intent);
				
				
				
			}
			
			
			
			
		}
	}
}
