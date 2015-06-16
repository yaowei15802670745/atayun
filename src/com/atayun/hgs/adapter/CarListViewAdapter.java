package com.atayun.hgs.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atayun.hgs.activity.R;
import com.atayun.hgs.modle.CarItem;

public class CarListViewAdapter extends BaseAdapter {

	 private List<Map<String, Object>> data;  
	    private LayoutInflater layoutInflater;  
	    private Context context;  
	    public CarListViewAdapter(Context context,List<Map<String, Object>> data){  
	        this.context=context;  
	        this.data=data;  
	        this.layoutInflater=LayoutInflater.from(context);  
	    }  
	    
	  
	    @Override  
	    public int getCount() {  
	        return data.size();  
	    }  
	    /** 
	     * 获得某一位置的数据 
	     */  
	    @Override  
	    public Object getItem(int position) {  
	        return data.get(position);  
	    }  
	    /** 
	     * 获得唯一标识 
	     */  
	    @Override  
	    public long getItemId(int position) {  
	        return position;  
	    }  
	  
	    @Override  
	    public View getView(int position, View convertView, ViewGroup parent) {  
	        CarItem itemdata=null;  
	        if(convertView==null){  
	        	itemdata=new CarItem();  
	            //获得组件，实例化组件  
	            convertView=layoutInflater.inflate(R.layout.listitemlayout, null);
	            itemdata.carowner=(TextView) convertView.findViewById(R.id.carowner);
	            itemdata.carId=(TextView) convertView.findViewById(R.id.carId);
	            itemdata.carPicture=(ImageView) convertView.findViewById(R.id.carPicture);
	            itemdata.carType=(TextView) convertView.findViewById(R.id.carType);
	            itemdata.carHeight=(TextView)convertView.findViewById(R.id.carHeight);
	            itemdata.addressText=(TextView)convertView.findViewById(R.id.addressText);
	            itemdata.distance=(TextView)convertView.findViewById(R.id.distanceText);
	            itemdata.evaluation=(TextView)convertView.findViewById(R.id.evaluation);  
	            convertView.setTag(itemdata);  
	        }else{  
	        	itemdata=(CarItem)convertView.getTag();  
	        }  
	        //绑定数据  
	        itemdata.carowner.setText((String) data.get(position).get("carowner"));
	        itemdata.carId.setText((String) data.get(position).get("carId"));
	        itemdata.carPicture.setBackgroundResource((Integer)data.get(position).get("carPicture"));
	        itemdata.carType.setText((String) data.get(position).get("carType"));
	        itemdata.carHeight.setText((String)data.get(position).get("carHeight"));
	        itemdata.addressText.setText((String)data.get(position).get("address"));
	        itemdata.distance.setText((String)data.get(position).get("distance"));
	        itemdata.evaluation.setText((String)data.get(position).get("evaluation"));
	        return convertView;  
	    }  
	  
	}  
