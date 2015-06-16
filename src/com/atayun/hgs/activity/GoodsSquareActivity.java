package com.atayun.hgs.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.atayun.hgs.adapter.GoodsListAdpter;
import com.atayun.hgs.fragment.HomeLayoutLeft;
import com.atayun.hgs.modle.GoodsInfoItems;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class GoodsSquareActivity extends ActionBarActivity {
	private ListView listview = null;
	ArrayList<GoodsInfoItems> goodsInfoItems=HomeLayoutLeft.goodsInfoItems;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.goods_square);

		listview = (ListView) findViewById(R.id.list);
		List<Map<String, Object>> data = getData();
		listview.setAdapter(new GoodsListAdpter(this, data));
		listview.setOnItemClickListener(new ItemClickEvent());

		
	}

	private List<Map<String, Object>> getData() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		// 
		for (int i = 0; i < goodsInfoItems.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
//			Log.d("AGV", goodsInfoItems.get(i).getCargoInfoStart());
//			Toast.makeText(GoodsSquareActivity.this, goodsInfoItems.get(i).getCargoInfoEnd(),
//					Toast.LENGTH_SHORT).show();
			map.put("id", i);
			
			// 把listview显示的内容存入
			map.put("cargoInfoStart", goodsInfoItems.get(i).getCargoInfoStart());
			map.put("cargoInfoEnd",goodsInfoItems.get(i).getCargoInfoEnd());
			map.put("cargoInfoDesc",goodsInfoItems.get(i).getCargoInfoDesc() );
			map.put("cargoInfoDeliTime",goodsInfoItems.get(i).getCargoInfoDeliTime());
			map.put("cargoInfoPublished", goodsInfoItems.get(i).getCargoInfoPublished());
//			map.put("Match_num", "匹配车源3条");
			list.add(map);
		}
		return list;
	}

	// 点击listvie项目的监听
	public class ItemClickEvent implements OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
//			goodsInfoItems
			
	
			
			
			// id和从position0开始
			Intent intent = new Intent(GoodsSquareActivity.this, GoodsDetailActivity.class);
		    Bundle bundle = new Bundle();
           //使用bundle向GoodsDetailActivity传递数据
			bundle.putString("cargoInfoStart", goodsInfoItems.get(position).getCargoInfoStart());
			bundle.putString("cargoInfoEnd", goodsInfoItems.get(position).getCargoInfoEnd());
			bundle.putString("cargoInfoLoad", goodsInfoItems.get(position).getCargoInfoLoad());
			bundle.putString("cargoInfoLenth", goodsInfoItems.get(position).getCargoInfoLenth());
			bundle.putString("cargoInfoWidth", goodsInfoItems.get(position).getCargoInfoWidth());
			bundle.putString("cargoInfoHeight", goodsInfoItems.get(position).getCargoInfoHeight());
			bundle.putString("cargoInfoVolume", goodsInfoItems.get(position).getCargoInfoVolume());
			bundle.putString("cargoInfoPrice", goodsInfoItems.get(position).getCargoInfoPrice());
			bundle.putString("cargoInfoContactWay", goodsInfoItems.get(position).getCargoInfoContactWay());
			bundle.putString("cargoInfoContacts", goodsInfoItems.get(position).getCargoInfoContacts());
			//后添加
			bundle.putString("userName", goodsInfoItems.get(position).getUserName());
			bundle.putString("cargoInfoSStreet", goodsInfoItems.get(position).getCargoInfoSStreet());
			bundle.putString("cargoInfoRlen", goodsInfoItems.get(position).getCargoInfoRlen());
			bundle.putString("cargoInfoDeliTime", goodsInfoItems.get(position).getCargoInfoDeliTime());
			bundle.putString("cargoType", goodsInfoItems.get(position).getCargoType());
//			bundle.putString("cargoInfoId", goodsInfoItems.get(position).getCargoInfoId());
//			bundle.putString("userId", goodsInfoItems.get(position).getUserId());
			
			
			intent.putExtras(bundle);
			
			   finish();
			startActivity(intent);
		}
	}
}
