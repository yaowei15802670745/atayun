package com.atayun.hgs.modle;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * 使用SharedPreferences保存用户登录信息
 * 
 * @param context
 * @param username
 * @param password
 */
public class SaveLoginInfo {

	public static void saveLoginInfo(Context context, String username,
			String password) {
		// 获取SharedPreferences对象
		SharedPreferences sharedPre = context.getSharedPreferences("config",
				context.MODE_PRIVATE);
		// 获取Editor对象
		Editor editor = sharedPre.edit();
		// 设置参数
		editor.putString("username", username);
		editor.putString("password", password);
		// 提交
		editor.commit();
	}

}