package com.atayun.hgs.fragment;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.atayun.hgs.activity.CarSquareActivity;
import com.atayun.hgs.activity.GoodsSquareActivity;
import com.atayun.hgs.activity.LoginActivity;
import com.atayun.hgs.activity.R;
import com.atayun.hgs.dao.GetCarInfoDao;
import com.atayun.hgs.dao.GetGoodsInfoDao;
import com.atayun.hgs.modle.CarDetailItems;
import com.atayun.hgs.modle.MatchGoodsInfoItems;
import com.atayun.hgs.modle.GoodsInfoItems;
import com.atayun.hgs.modle.IDApplication;
import com.atayun.hgs.url.ConnData;
import com.atayun.hgs.util.JsonObjectPostRequest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class HomeLayoutLeft extends Fragment {
	private String userId=LoginActivity.userId;
	private String url_getAllCargoInfo = ConnData.getAllCargoInfo;
	private String url_getcarInfo = ConnData.getcarInfo;
	
	public static ArrayList<GoodsInfoItems> goodsInfoItems = new ArrayList<GoodsInfoItems>();
	public static ArrayList<CarDetailItems> carInfoItems = new ArrayList<CarDetailItems>();
	
	private Map<String, String> mMap;// 用于封装 完成注册提交的参数
	private Map<String, String> mMap2;// 用于封装 完成注册提交的参数


//    private Button search_goods;
  
	
 
    private View layout = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    	
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.layout = inflater.inflate(R.layout.home_main_left, null);
        
        Button search_goods = (Button)this.layout.findViewById(R.id.search_goods);
        Button search_car= (Button)this.layout.findViewById(R.id.search_car);
        search_car.setOnClickListener(new ButtonListener());
        search_goods.setOnClickListener(new ButtonListener());
        return this.layout;
    }
    public class ButtonListener implements OnClickListener {
        @Override
        public void onClick(View v) {
        	switch (v.getId()) {
			// 打开侧边栏的按钮监听
			case R.id.search_goods://首页查看货物
//            Toast.makeText(getActivity(), "button1", Toast.LENGTH_SHORT).show();
            
         // 获取网络数据
         			// 请求服务
         			mMap = new HashMap<String, String>();
         			mMap.put("method", "getAllCargoInfo");
//         			mMap.put("userId", userId);
         			RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
         			JsonObjectPostRequest jsonObjectPostRequest = new JsonObjectPostRequest(
         					url_getAllCargoInfo, new Response.Listener<JSONObject>() {

         						@Override
         						public void onResponse(JSONObject response) {
         							try {

         								String CarInfoState = response
         										.getString("getCarInfoState");

         								switch (CarInfoState) {
         								case "200":
         									Toast.makeText(getActivity(), "获取数据成功",
         											Toast.LENGTH_SHORT).show();
         									// 解析数据
         									JSONArray Cargo = response
         											.getJSONArray("Cargo");
         									String cargo = Cargo.toString();
         									Log.d("Cargo", cargo);
         									// 处理获取到的JSonArray

         									goodsInfoItems = GetGoodsInfoDao
         											.getGoodsInfoDao(Cargo);

         									Intent intent = new Intent(
         											getActivity(),
         											GoodsSquareActivity.class);

         									
         									startActivity(intent);
         									break;
         								case "10":
         									Toast.makeText(getActivity(), "获取数据失败",
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
         							Toast.makeText(getActivity(), "获取失败，请检查网络",
         									Toast.LENGTH_SHORT).show();

         						}
         					}, mMap);
         			requestQueue.add(jsonObjectPostRequest);
         			break;
			case R.id.search_car://点击查看车源
				
				// 请求服务
				mMap2 = new HashMap<String, String>();
				mMap2.put("method", "getAllCarInfo");
				mMap2.put("userId", userId);
				RequestQueue requestQueue2 = Volley.newRequestQueue(getActivity());
				JsonObjectPostRequest jsonObjectPostRequest2 = new JsonObjectPostRequest(
						url_getcarInfo, new Response.Listener<JSONObject>() {

							@Override
							public void onResponse(JSONObject response) {
								try {

									String carState = response
											.getString("carState");

									switch (carState) {
									case "200":
										Toast.makeText(getActivity(), "获取数据成功",
												Toast.LENGTH_SHORT).show();
										// 解析数据
										JSONArray car = response
												.getJSONArray("car");
										String cars = car.toString();
										Log.d("car", cars);
										
										carInfoItems = GetCarInfoDao
												.GetCarInfoDao(car);
										Intent intent=new Intent(getActivity(),CarSquareActivity.class);
									
										startActivity(intent);

										break;
									case "30":
										Toast.makeText(getActivity(), "获取数据失败",
												Toast.LENGTH_SHORT).show();
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
								Toast.makeText(getActivity(), "获取失败，请检查网络",
										Toast.LENGTH_SHORT).show();

							}
						}, mMap2);
				requestQueue2.add(jsonObjectPostRequest2);

				break;
				
			default:
				break;
            
        	}     
        }
    }
}