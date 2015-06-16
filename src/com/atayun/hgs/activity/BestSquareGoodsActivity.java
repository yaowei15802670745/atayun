package com.atayun.hgs.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.atayun.hgs.adapter.GoodsListAdpter;
import com.atayun.hgs.modle.MatchGoodsInfoItems;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class BestSquareGoodsActivity extends Activity {
	private ListView listview = null;
	ArrayList<MatchGoodsInfoItems> BestMachgoodsInfoItems=MatchGoodsSqureActivity.bestGoodsDetailInfoItems;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_bestsquregoods);

		listview = (ListView) findViewById(R.id.list);
		List<Map<String, Object>> data = getData();
		listview.setAdapter(new GoodsListAdpter(this, data));
		listview.setOnItemClickListener(new ItemClickEvent());

		
	}

	private List<Map<String, Object>> getData() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		// 
		for (int i = 0; i < BestMachgoodsInfoItems.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
//			Log.d("AGV", goodsInfoItems.get(i).getCargoInfoStart());
//			Toast.makeText(GoodsSquareActivity.this, goodsInfoItems.get(i).getCargoInfoEnd(),
//					Toast.LENGTH_SHORT).show();
			map.put("id", i);
			
			// 把listview显示的内容存入
			map.put("cargoInfoStart", BestMachgoodsInfoItems.get(i).getCargoInfoStart());
			map.put("cargoInfoEnd",BestMachgoodsInfoItems.get(i).getCargoInfoEnd());
			map.put("cargoInfoDesc",BestMachgoodsInfoItems.get(i).getCargoInfoDesc() );
			map.put("cargoInfoDeliTime",BestMachgoodsInfoItems.get(i).getCargoInfoDeliTime());
			map.put("cargoInfoPublished", BestMachgoodsInfoItems.get(i).getCargoInfoPublished());
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
			Intent intent = new Intent(BestSquareGoodsActivity.this, BestMachDetailgoodsActivity.class);
		    Bundle bundle = new Bundle();
           //使用bundle向GoodsDetailActivity传递数据
			bundle.putString("cargoInfoStart", BestMachgoodsInfoItems.get(position).getCargoInfoStart());
			bundle.putString("cargoInfoEnd", BestMachgoodsInfoItems.get(position).getCargoInfoEnd());
			bundle.putString("cargoInfoLoad", BestMachgoodsInfoItems.get(position).getCargoInfoLoad());
			bundle.putString("cargoInfoLenth", BestMachgoodsInfoItems.get(position).getCargoInfoLenth());
			bundle.putString("cargoInfoWidth", BestMachgoodsInfoItems.get(position).getCargoInfoWidth());
			bundle.putString("cargoInfoHeight", BestMachgoodsInfoItems.get(position).getCargoInfoHeight());
			bundle.putString("cargoInfoVolume", BestMachgoodsInfoItems.get(position).getCargoInfoVolume());
			bundle.putString("cargoInfoPrice", BestMachgoodsInfoItems.get(position).getCargoInfoPrice());
			bundle.putString("cargoInfoContactWay", BestMachgoodsInfoItems.get(position).getCargoInfoContactWay());
			bundle.putString("cargoInfoContacts", BestMachgoodsInfoItems.get(position).getCargoInfoContacts());
			bundle.putString("cagoCpName", BestMachgoodsInfoItems.get(position).getCagoCpName());
	
			
			intent.putExtras(bundle);
			
			   finish();
			startActivity(intent);
		}
	}
}
