package com.atayun.hgs.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
/*
 * 广告服务
 */
public class AdService extends Activity{
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.ad_service);
		}
}
