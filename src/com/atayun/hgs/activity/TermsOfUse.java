package com.atayun.hgs.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
/*
 * 使用协议
 */
public class TermsOfUse extends Activity{
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.terms_of_use);
		}
}
