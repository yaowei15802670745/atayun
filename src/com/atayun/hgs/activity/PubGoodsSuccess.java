package com.atayun.hgs.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class PubGoodsSuccess extends Activity implements OnClickListener{
	private Button check_home;
	private Button pubgoods_conti;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_pubgoodssuccess);
		check_home = (Button)findViewById(R.id.check_home);
		check_home.setOnClickListener(this);
		pubgoods_conti = (Button)findViewById(R.id.pubgoods_conti);
		pubgoods_conti.setOnClickListener(this);		
	}


	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.check_home:
			Intent intent = new Intent(PubGoodsSuccess.this, PageFoot.class);
			startActivity(intent);
			finish();
			
			break;
			
			
			
		case R.id.pubgoods_conti:
			Intent intent2 = new Intent(PubGoodsSuccess.this, PublishGoodsActivity_Is.class);
			startActivity(intent2);
			finish();
			
			
			break;
		
		
		
		}
		
	}
	

	
	
	

}
