package com.atayun.hgs.activity;
//git地址https://github.com/yaowei15802670745/atayun.git
/*@anthor：yaowei
 * 2015-5-15
 * 主要功能描述：根据用户输入的手机号码，加密后的用户密码进行登陆
 * 
 * 
 */
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.atayun.hgs.modle.IDApplication;
import com.atayun.hgs.util.JsonObjectPostRequest;
import com.atayun.hgs.url.*;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private EditText username, userpass;

//	private String flagID = "";// 当flag=0表示用户身份是车主，1表示为货主
	public static String g_ifcomplete = "";
	private Button login, register, forget;
	private String loginState = "";
	public static String userId;
	private Map<String, String> mMap;
//	private Map<String, String> mMap2;
	private String url = ConnData.URL;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		setContentView(R.layout.activity_login);

		username = (EditText) this.findViewById(R.id.username);
		userpass = (EditText) this.findViewById(R.id.password);

		login = (Button) findViewById(R.id.login);
		register = (Button) findViewById(R.id.register);
		forget = (Button) findViewById(R.id.forget);

	}

	public void loginClick(View v) {

		// login.setBackgroundColor(Color.parseColor("#F555DC"));
		String userAccount = username.getText().toString().trim();
		String userPassword = userpass.getText().toString().trim();
		Log.d("userMobile", userAccount);
		Log.d("userPassword", userPassword);
		mMap = new HashMap<String, String>();
		mMap.put("method", "login");
		mMap.put("userAccount", userAccount);
		mMap.put("userPassword", userPassword);

		// 请求服务
		RequestQueue requestQueue = Volley.newRequestQueue(this);
		JsonObjectPostRequest jsonObjectPostRequest = new JsonObjectPostRequest(
				url, new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						try {
							if (response == null) {

								Toast.makeText(LoginActivity.this, "没有得到数据",
										Toast.LENGTH_SHORT).show();

							} else {
								loginState = response.getString("loginState");

								switch (loginState) {
								case "200":
									Toast.makeText(LoginActivity.this, "登录成功",
											Toast.LENGTH_SHORT).show();
									userId = response.getString("userId");
									String userFlag = response
											.getString("userFlag");
									String cargoFlag=response.getString("cargoFlag");
									String carUserFlag=response.getString("carUserFlag");
									String companyFlag=response.getString("companyFlag");
									String userBaseFlag=response.getString("userBaseFlag");
									Log.d("userId", userId);
									Log.d("userFlag", userFlag);
									// 设置flagID,userId

									((IDApplication) getApplication())
											.setFlagID(userFlag);
									((IDApplication) getApplication())
											.setUserId(userId);
									// 将用户认证状态放进全局变量
									((IDApplication) getApplication()).setCarUserFlag(carUserFlag);
									((IDApplication) getApplication()).setCargoFlag(cargoFlag);
									((IDApplication) getApplication()).setCompanyFlag(companyFlag);
									((IDApplication) getApplication()).setUserBaseFlag(userBaseFlag);
									

									//

									Intent intent = new Intent(
											LoginActivity.this, PageFoot.class);
									
									finish();
									startActivity(intent);
									break;
								case "10":
									Toast.makeText(LoginActivity.this, "未知错误",
											Toast.LENGTH_SHORT).show();
									break;
								case "20":
									Toast.makeText(LoginActivity.this, "密码错误",
											Toast.LENGTH_SHORT).show();
									break;
								case "30":
									Toast.makeText(LoginActivity.this,
											"用户账号输入不规范", Toast.LENGTH_SHORT)
											.show();
									break;
								case "40":
									Toast.makeText(LoginActivity.this,
											"用户账号为空", Toast.LENGTH_SHORT)
											.show();
									break;
								case "50":
									Toast.makeText(LoginActivity.this, "密码为空",
											Toast.LENGTH_SHORT).show();
									break;
								case "60":
									Toast.makeText(LoginActivity.this,
											"账号不存在，手机号未注册", Toast.LENGTH_SHORT)
											.show();
									break;
								case "70":
									Toast.makeText(LoginActivity.this,
											"账号不存在，用户名不存在", Toast.LENGTH_SHORT)
											.show();
									break;
								default:
									break;
								}

							}

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						Toast.makeText(LoginActivity.this, "获取失败，请检查网络",
								Toast.LENGTH_SHORT).show();

					}
				}, mMap);
		requestQueue.add(jsonObjectPostRequest);

	}

	public void registerClick(View v) {

		Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
		startActivity(intent);

		return;

	}

	public void forgetClick(View v) {

		Intent intent = new Intent(LoginActivity.this, ResetPhoneActivity.class);
		startActivity(intent);

		return;

	}

}
