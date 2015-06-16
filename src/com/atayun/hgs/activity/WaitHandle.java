package com.atayun.hgs.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.atayun.hgs.adapter.Om_Adspter;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;

public class WaitHandle extends Activity{
	private ListView wh_listview = null;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.wait_handle);
		initView();

	}

	private void initView() {
		// TODO Auto-generated method stub
		wh_listview = (ListView) findViewById(R.id.wh_list);
		List<Map<String, Object>> data = getData();
		wh_listview.setAdapter(new Om_Adspter(this, data));
//		listview.setOnItemClickListener(new ItemClickEvent());
	}
	private List<Map<String, Object>> getData() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		// 举例，产生8个listview的项目
		for (int i = 0; i < 8; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", i);
			// 把listview显示的内容存入
			map.put("Start_Place", "丰南区" + i);
			map.put("End_Place", "长沙市" + i);
			map.put("Goods_Info", "普货，20吨，求9.6米高栏1台");
			map.put("StartTime", "12月17日起");
			map.put("Match_num", "匹配车源3条");
			list.add(map);
		}
		return list;
	}

	// 点击listvie项目的监听
//	public class ItemClickEvent implements OnItemClickListener {
//		@Override
//		public void onItemClick(AdapterView<?> parent, View view, int position,
//				long id) {
//			// TODO Auto-generated method stub
//			// id和从position0开始
//			Intent intent = new Intent(GoodsSquare.this, GoodsDetail.class);
//			startActivity(intent);
//		}
//	}
}
