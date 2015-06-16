package com.atayun.hgs.activity;

import android.app.Activity;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.SupportMapFragment;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.overlayutil.PoiOverlay;
import com.baidu.mapapi.search.core.CityInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;


/**
 * 演示poi搜索功能
 */


import com.baidu.mapapi.search.sug.SuggestionSearchOption;
import com.baidu.mapapi.SDKInitializer;

	public class PoiSearchDemo extends Activity implements OnGetPoiSearchResultListener, OnGetSuggestionResultListener{  
		private PoiSearch mPoiSearch = null;
		private SuggestionSearch mSuggestionSearch = null;
//	    MapView mMapView = null;  
//	    BaiduMap mBaiduMap = null;
		/**
		 * 搜索关键字输入窗口
		 */
		private AutoCompleteTextView keyWorldsView = null;
		private ArrayAdapter<String> sugAdapter = null;
		private int load_Index = 0;
	    @Override  
	    protected void onCreate(Bundle savedInstanceState) {  
	        super.onCreate(savedInstanceState);   
	        //在使用SDK各组件之前初始化context信息，传入ApplicationContext  
	        //注意该方法要再setContentView方法之前实现  
	        SDKInitializer.initialize(getApplicationContext());  
	        setContentView(R.layout.activity_poisearch); 
	        
	     // 初始化搜索模块，注册搜索事件监听
			mPoiSearch = PoiSearch.newInstance();
			mPoiSearch.setOnGetPoiSearchResultListener(this);
			mSuggestionSearch = SuggestionSearch.newInstance();
			mSuggestionSearch.setOnGetSuggestionResultListener(this);
			keyWorldsView = (AutoCompleteTextView) findViewById(R.id.searchkey);
			sugAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_dropdown_item_1line);
			keyWorldsView.setAdapter(sugAdapter);
//			mBaiduMap = ((SupportMapFragment) (getSupportFragmentManager()
//					.findFragmentById(R.id.map))).getBaiduMap();

	        
	    
//	        //获取地图控件引用  
//	        mMapView = (MapView) findViewById(R.id.bmapView); 
//	    	mBaiduMap = mMapView.getMap();
	    

			/**
			 * 当输入关键字变化时，动态更新建议列表
			 */
			keyWorldsView.addTextChangedListener(new TextWatcher() {

				@Override
				public void afterTextChanged(Editable arg0) {

				}

				@Override
				public void beforeTextChanged(CharSequence arg0, int arg1,
						int arg2, int arg3) {

				}

				@Override
				public void onTextChanged(CharSequence cs, int arg1, int arg2,
						int arg3) {
					if (cs.length() <= 0) {
						return;
					}
					String city = ((EditText) findViewById(R.id.city)).getText()
							.toString();
					/**
					 * 使用建议搜索服务获取建议列表，结果在onSuggestionResult()中更新
					 */
					mSuggestionSearch
							.requestSuggestion((new SuggestionSearchOption())
									.keyword(cs.toString()).city(city));
				}
			});

		}
	    
	    
	    
	    
	      
	    @Override  
	    protected void onDestroy() {  
	        super.onDestroy();  
	        mPoiSearch.destroy();
			mSuggestionSearch.destroy();
	        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理  
	       
	    }  
	    @Override  
	    protected void onResume() {  
	        super.onResume();  
	        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理  
	      
	        }  
	    @Override  
	    protected void onPause() {  
	        super.onPause();  
//	        在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理  
	    
	        }

	
	    /**
		 * 影响搜索按钮点击事件
		 * 
		 * @param v
		 */
		public void searchButtonProcess(View v) {
			EditText editCity = (EditText) findViewById(R.id.city);
			EditText editSearchKey = (EditText) findViewById(R.id.searchkey);
			mPoiSearch.searchInCity((new PoiCitySearchOption())
					.city(editCity.getText().toString())
					.keyword(editSearchKey.getText().toString())
					.pageNum(load_Index));
		}

		public void goToNextPage(View v) {
			load_Index++;
			searchButtonProcess(null);
		}
		@Override
		public void onGetPoiResult(PoiResult result) {
			if (result == null
					|| result.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
				Toast.makeText(PoiSearchDemo.this, "未找到结果", Toast.LENGTH_LONG)
				.show();
				return;
			}
//			if (result.error == SearchResult.ERRORNO.NO_ERROR) {
//				mBaiduMap.clear();
//				PoiOverlay overlay = new MyPoiOverlay(mBaiduMap);
//				mBaiduMap.setOnMarkerClickListener(overlay);
//				overlay.setData(result);
//				overlay.addToMap();
//				overlay.zoomToSpan();
//				return;
//			}
			if (result.error == SearchResult.ERRORNO.AMBIGUOUS_KEYWORD) {

				// 当输入关键字在本市没有找到，但在其他城市找到时，返回包含该关键字信息的城市列表
				String strInfo = "在";
				for (CityInfo cityInfo : result.getSuggestCityList()) {
					strInfo += cityInfo.city;
					strInfo += ",";
				}
				strInfo += "找到结果";
				Toast.makeText(PoiSearchDemo.this, strInfo, Toast.LENGTH_LONG)
						.show();
			}
		}
		@Override
		public void onGetPoiDetailResult(PoiDetailResult result) {
			if (result.error != SearchResult.ERRORNO.NO_ERROR) {
				Toast.makeText(PoiSearchDemo.this, "抱歉，未找到结果", Toast.LENGTH_SHORT)
						.show();
			} else {
				Toast.makeText(PoiSearchDemo.this, result.getName() + ": " + result.getAddress(), Toast.LENGTH_SHORT)
				.show();
			}
		}

		@Override
		public void onGetSuggestionResult(SuggestionResult res) {
			if (res == null || res.getAllSuggestions() == null) {
				return;
			}
			sugAdapter.clear();
			for (SuggestionResult.SuggestionInfo info : res.getAllSuggestions()) {
				if (info.key != null)
					sugAdapter.add(info.key);
			}
			sugAdapter.notifyDataSetChanged();
		}

		private class MyPoiOverlay extends PoiOverlay {

			public MyPoiOverlay(BaiduMap baiduMap) {
				super(baiduMap);
			}

			@Override
			public boolean onPoiClick(int index) {
				super.onPoiClick(index);
				PoiInfo poi = getPoiResult().getAllPoi().get(index);
				// if (poi.hasCaterDetails) {
					mPoiSearch.searchPoiDetail((new PoiDetailSearchOption())
							.poiUid(poi.uid));
				// }
				return true;
			}
		}

	
	  
	    }
