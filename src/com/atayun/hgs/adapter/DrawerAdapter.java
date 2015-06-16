package com.atayun.hgs.adapter;

import java.util.List;
import java.util.Map;

import com.atayun.hgs.activity.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/*
 * 侧边栏，抽屉的listview
 */
public class DrawerAdapter extends BaseAdapter{

	private List<Map<String ,Object>> data;
	private LayoutInflater layoutInflater;
	private Context context;
	public DrawerAdapter(Context context,List<Map<String ,Object>> data){
		this.context=context;
		this.data=data;
		this.layoutInflater=LayoutInflater.from(context);
	}
	//组件集合
	public final class MyModule{
		public ImageView drawer_image;
		public TextView drawer_text;
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
			convertView=layoutInflater.inflate(R.layout.drawer_list_item, null);
			myMpdule.drawer_image=(ImageView) convertView.findViewById(R.id.drawer_image);
			myMpdule.drawer_text=(TextView) convertView.findViewById(R.id.drawer_text);
			convertView.setTag(myMpdule);
		}else{
			myMpdule=(MyModule) convertView.getTag();
		}
		//绑定数据,没有图片资源，暂时使用同一个
		if((Integer)data.get(position).get("DrawerPic")==0){
			myMpdule.drawer_image.setBackgroundResource(R.drawable.first_view);
		}else if((Integer)data.get(position).get("DrawerPic")==1){
			myMpdule.drawer_image.setBackgroundResource(R.drawable.first_view);
		}else if((Integer)data.get(position).get("DrawerPic")==2){
			myMpdule.drawer_image.setBackgroundResource(R.drawable.first_view);
		}else if((Integer)data.get(position).get("DrawerPic")==3){
			myMpdule.drawer_image.setBackgroundResource(R.drawable.first_view);
		}
		
		myMpdule.drawer_text.setText((String)data.get(position).get("DrawerText"));
		return convertView;
	}

}
