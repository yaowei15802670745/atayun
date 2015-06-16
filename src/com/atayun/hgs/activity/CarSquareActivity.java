package com.atayun.hgs.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.atayun.hgs.adapter.CarListViewAdapter;

public class CarSquareActivity extends ActionBarActivity {
	private ListView listView;
//	private ArrayAdapter<String> Sadapter;
//	private Spinner spinner1;
//	private Spinner spinner2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_carsquare);
        listView=(ListView)findViewById(R.id.lv);
        List<Map<String, Object>> list=getData();
        CarListViewAdapter adapter=new CarListViewAdapter(this,list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new ItemClickEvent());
        /*
         * 觉得下拉列表不好看，而且如果选择地址的话，太局限了   就做成了输入框  如果继续做成下拉列表的话，就去掉注释
         */
//        spinner1 = (Spinner) findViewById(R.id.start);
//        spinner2=(Spinner) findViewById(R.id.end);
//        //将可选内容与ArrayAdapter连接起来
//         Sadapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,place);
//         
//        //设置下拉列表的风格
//        Sadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//         
//        //将adapter 添加到spinner中
//        spinner1.setAdapter(Sadapter);
//        spinner2.setAdapter(Sadapter);
//        //添加事件Spinner事件监听  
////        spinner1.setOnItemSelectedListener(new SpinnerSelectedListener());
////        spinner2.setOnItemSelectedListener(new SpinnerSelectedListener());
//        //设置默认值
//        spinner1.setVisibility(View.VISIBLE);
//        spinner2.setVisibility(View.VISIBLE);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    /**
     * 传入具体数据到listView中
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getData(){  
        List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
        String[] name={"董浩华","王兵","张海洋","张三","李四"};
        String[] carId={"京QG9039","翼EB3665","京QXK385","湘AF6883","津BG7283"};
        String[] carType={"普通车型","高低板车","厢式车","高低板车","厢式车"};
        String[] carHeight={"3.8","4.0","4.2","4.5","17.5"};
        for (int i = 0; i < 5; i++) {  
            Map<String, Object> map=new HashMap<String, Object>();  
            map.put("carowner",name[i]);  
            map.put("carId",carId[i]);  
            map.put("carPicture", R.drawable.ic_launcher);
            map.put("carType",carType[i]);
            map.put("carHeight",carHeight[i]+"米");
            map.put("address","北京市朝阳区朝阳路辅路靠近绿奇苑");
            map.put("distance","距离"+i+"公里");
            map.put("evaluation", "暂无评价");
            list.add(map);  
        }  
        return list;  
    }
    	
	// 点击listvie项目的监听
	public class ItemClickEvent implements OnItemClickListener {
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) { 
		Log.d("ssss", "msgsdsdsdsds");
		Toast.makeText(getApplicationContext(), "点击获取",
			    Toast.LENGTH_SHORT).show();
			Intent intent=new Intent(CarSquareActivity.this,CarDetailActivity.class);
	        startActivity(intent); 
	        
	}


	}

	
}
