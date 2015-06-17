package com.atayun.hgs.adapter;

import java.util.List;
import java.util.Map;

import com.atayun.hgs.activity.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Wh_Adspter extends BaseAdapter{

	private List<Map<String ,Object>> data;
	private LayoutInflater layoutInflater;
	private Context context;
	public Wh_Adspter(Context context,List<Map<String ,Object>> data){
		this.context=context;
		this.data=data;
		this.layoutInflater=LayoutInflater.from(context);
	}
	//组件集合
	public final class MyModule{
		public TextView Start_Place;
		public TextView End_Place;
		public TextView Order_price;
		public TextView StartTime;
		public TextView Updata_time;
	}
	
	@Override
	public int getCount() {
		return data.size();
	}
	//获得某一位置的数据
	@Override
	public Object getItem(int position) {
		return data.get(position);
	}
	//获得唯一标识
	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		MyModule myMpdule;
		if(convertView==null){
			myMpdule=new MyModule();
			//实例化组件
			convertView=layoutInflater.inflate(R.layout.wh_listitem, null);
			myMpdule.Start_Place=(TextView) convertView.findViewById(R.id.wh_start_place);
			myMpdule.End_Place=(TextView) convertView.findViewById(R.id.wh_end_place);
			myMpdule.Order_price=(TextView) convertView.findViewById(R.id.wh_order_price);
			
			myMpdule.StartTime=(TextView) convertView.findViewById(R.id.wh_starttime);
			myMpdule.Updata_time=(TextView) convertView.findViewById(R.id.wh_updata_time);
			
			convertView.setTag(myMpdule);
		}else{
			myMpdule=(MyModule) convertView.getTag();
		}
		//绑定数据
		myMpdule.Start_Place.setText((String)data.get(position).get("Start_Place"));
		myMpdule.End_Place.setText((String)data.get(position).get("End_Place"));
		myMpdule.Order_price.setText("价格是："+(String)data.get(position).get("Goods_Info"));
		myMpdule.StartTime.setText((String)data.get(position).get("StartTime"));
		
		myMpdule.Updata_time.setText((String)data.get(position).get("wh_UserId"));
		
		return convertView;
	}

}
