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

public class Om_Adspter extends BaseAdapter{

	private List<Map<String ,Object>> data;
	private LayoutInflater layoutInflater;
	private Context context;
	public Om_Adspter(Context context,List<Map<String ,Object>> data){
		this.context=context;
		this.data=data;
		this.layoutInflater=LayoutInflater.from(context);
	}
	//组件集合
	public final class MyModule{
		public TextView Start_Place;
		public TextView End_Place;
		public TextView Goods_Info;
		public TextView StartTime;
		public TextView Match_num;
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
			myMpdule.Goods_Info=(TextView) convertView.findViewById(R.id.wh_Goods_info);
			
			myMpdule.StartTime=(TextView) convertView.findViewById(R.id.wh_starttime);
			myMpdule.Match_num=(TextView) convertView.findViewById(R.id.wh_match_num);
			
			convertView.setTag(myMpdule);
		}else{
			myMpdule=(MyModule) convertView.getTag();
		}
		//绑定数据
		myMpdule.Start_Place.setText((String)data.get(position).get("Start_Place"));
		myMpdule.End_Place.setText((String)data.get(position).get("End_Place"));
		myMpdule.Goods_Info.setText((String)data.get(position).get("Goods_Info"));
		myMpdule.StartTime.setText((String)data.get(position).get("StartTime"));
		myMpdule.Match_num.setText((String)data.get(position).get("Match_num"));
		
		return convertView;
	}

}
