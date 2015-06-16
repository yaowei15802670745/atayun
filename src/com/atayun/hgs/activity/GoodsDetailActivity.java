package com.atayun.hgs.activity;

import com.atayun.hgs.activity.R.color;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class GoodsDetailActivity extends Activity implements OnClickListener{
	//生成订单按钮,资质确认按钮，查看线路按钮，拨打电话按钮,返回按钮
	private Button create_order,company_confirm,check_route,dial_num,detail_back;
	private TextView company;//公司资职
	private TextView convey_route;//发货线路
	private TextView info_content;//信息内容
	private TextView convey_fee;//运费
	private TextView dicuss_fee;//议价
	private TextView distance;//公里数
	private TextView logistics_place;//物流园区
	private TextView phone_number;//手机号码
	private TextView linkman;//联系人
	private RadioGroup radioGroup;//单选按钮组
	private ImageView goods_pic1,goods_pic2;
	
	private String company_str="王老吉";
	private String cargoInfoStart="长沙";
	private String cargoInfoEnd;
	//重量，长、宽、高
	private String cargoInfoLoad;//重量
	private String cargoInfoLenth;//长
	private String cargoInfoWidth;//货宽
	private String cargoInfoHeight;//货高
	private String cargoInfoVolume;//货物体积
//	private String info_content_str="28T  L*W*H  30方";
	private String info_content_str;
	private String cargoInfoPrice;
	private String cargoInfoPrice2="5200元";
	private String distance_str="800公里";
//	private String cargoInfoStart2="长沙中南物流园X06—104";
	private String cargoInfoContactWay;
	private String cargoInfoContacts;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.goods_detail);
		//使用Bundle获取来自GoodsSquareActivity的数据
		Bundle bundle = this.getIntent().getExtras(); 
		cargoInfoStart=bundle.getString("cargoInfoStart");
		cargoInfoEnd=bundle.getString("cargoInfoEnd");
		cargoInfoLoad=bundle.getString("cargoInfoLoad");
		cargoInfoLenth=bundle.getString("cargoInfoLenth");
		cargoInfoWidth=bundle.getString("cargoInfoWidth");
		cargoInfoHeight=bundle.getString("cargoInfoHeight");
		cargoInfoVolume=bundle.getString("cargoInfoVolume");
		cargoInfoPrice=bundle.getString("cargoInfoPrice");
		cargoInfoContactWay=bundle.getString("cargoInfoContactWay");
		cargoInfoContacts=bundle.getString("cargoInfoContacts");
		info_content_str=cargoInfoLoad+"T"+"  L"+cargoInfoLenth+"*W"+cargoInfoWidth+"*H"+cargoInfoHeight+"  "+cargoInfoVolume+"方";
        Log.d("phone", cargoInfoContactWay);
		initView();
		
		}
	private void initView() {
		
		//初始化
		create_order=(Button) findViewById(R.id.create_order);
		company_confirm=(Button) findViewById(R.id.company_confirm);
		check_route=(Button) findViewById(R.id.check_route);
		dial_num=(Button) findViewById(R.id.dial_num);
		detail_back=(Button) findViewById(R.id.detail_back);
		//设置按钮监听
		create_order.setOnClickListener(this);
		company_confirm.setOnClickListener(this);
		check_route.setOnClickListener(this);
		dial_num.setOnClickListener(this);
		detail_back.setOnClickListener(this);
		
		
		company=(TextView) findViewById(R.id.company);
		convey_route=(TextView) findViewById(R.id.convey_route);
		info_content=(TextView) findViewById(R.id.info_content);
		convey_fee=(TextView) findViewById(R.id.convey_fee);
		dicuss_fee=(TextView) findViewById(R.id.dicuss_fee);
		distance=(TextView) findViewById(R.id.distance);
		logistics_place=(TextView) findViewById(R.id.logistics_place);
		phone_number=(TextView) findViewById(R.id.phone_number);
		linkman=(TextView) findViewById(R.id.linkman);
		radioGroup=(RadioGroup) findViewById(R.id.radiogroup_pay);
		goods_pic1=(ImageView) findViewById(R.id.goods_pic1);
		goods_pic2=(ImageView) findViewById(R.id.goods_pic2);
		//下面都是设置字体颜色
		setPartColor("公司资职："+company_str, company, 0, 5);
		setPartColor("发货线路:"+cargoInfoStart+"——"+cargoInfoEnd,convey_route, 0, 5);
		setPartColor("信息内容:"+info_content_str, info_content, 0, 5);
		setPartColor("运费:"+cargoInfoPrice, convey_fee, 0, 3);
		setPartColor("议价:"+cargoInfoPrice2, dicuss_fee, 0, 3);
		setPartColor("公里数:"+distance_str,distance, 0, 4);
		setPartColor("货物地址:"+cargoInfoStart, logistics_place, 0, 5);
		setPartColor("手机号码:"+cargoInfoContactWay, phone_number, 0, 5);
		setPartColor("联系人:"+cargoInfoContacts, linkman, 0, 4);

	}
	//textview的颜色一部分灰色，一半黑色，所以使用这个方法。
	private void setPartColor(String text,TextView textview,int start,int end){
		SpannableString sp=new SpannableString(text);
		sp.setSpan(new ForegroundColorSpan(color.abc_search_url_text_holo), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		textview.setText(sp);
	}
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.dial_num:
			String phoneno=cargoInfoContactWay;
			if(phoneno==null||phoneno.equals(""))
			{
			Toast.makeText(getApplicationContext(), "没有电话号码",
			    Toast.LENGTH_SHORT).show();
			}
			else
			{
//			Toast.makeText(getApplicationContext(), "有电话号码"+phoneno,
//			    Toast.LENGTH_SHORT).show();
			Intent intent=new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+phoneno));
			startActivity(intent);

		}
			break;
		case R.id.detail_back:
			Intent intent=new Intent(GoodsDetailActivity.this,GoodsSquareActivity.class);
		    finish();
			startActivity(intent);
		
	}
	}
}
