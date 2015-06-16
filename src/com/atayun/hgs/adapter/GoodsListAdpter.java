package com.atayun.hgs.adapter;

import java.util.List;
import java.util.Map;

import com.atayun.hgs.activity.R;
import com.atayun.hgs.activity.R.id;
import com.atayun.hgs.activity.R.layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class GoodsListAdpter extends BaseAdapter{

	private List<Map<String ,Object>> data;
	private LayoutInflater layoutInflater;
	private Context context;
	public GoodsListAdpter(Context context,List<Map<String ,Object>> data){
		this.context=context;
		this.data=data;
		this.layoutInflater=LayoutInflater.from(context);
	}
	//组件集合
	public final class MyModule{
		public TextView cargoInfoStart;
		public TextView cargoInfoEnd;
		public TextView cargoInfoDesc;
		public TextView cargoInfoDeliTime;
		public TextView cargoInfoPublished;
//		public TextView Match_num;
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
			convertView=layoutInflater.inflate(R.layout.activity_managergoodsitem, null);
			myMpdule.cargoInfoStart=(TextView) convertView.findViewById(R.id.start_place);
			myMpdule.cargoInfoEnd=(TextView) convertView.findViewById(R.id.end_place);
			myMpdule.cargoInfoDesc=(TextView) convertView.findViewById(R.id.Goods_info);
			
			myMpdule.cargoInfoDeliTime=(TextView) convertView.findViewById(R.id.starttime);
			myMpdule.cargoInfoPublished=(TextView) convertView.findViewById(R.id.arrivetime);
//			myMpdule.Match_num=(TextView) convertView.findViewById(R.id.match_num);
			
			convertView.setTag(myMpdule);
		}else{
			myMpdule=(MyModule) convertView.getTag();
		}
		//绑定数据
		myMpdule.cargoInfoStart.setText((String)data.get(position).get("cargoInfoStart"));
		myMpdule.cargoInfoEnd.setText((String)data.get(position).get("cargoInfoEnd"));
		myMpdule.cargoInfoDesc.setText((String)data.get(position).get("cargoInfoDesc"));
		myMpdule.cargoInfoDeliTime.setText((String)data.get(position).get("cargoInfoDeliTime"));
		myMpdule.cargoInfoPublished.setText((String)data.get(position).get("cargoInfoPublished"));
//		myMpdule.Match_num.setText((String)data.get(position).get("Match_num"));
		
		return convertView;
	}

}
