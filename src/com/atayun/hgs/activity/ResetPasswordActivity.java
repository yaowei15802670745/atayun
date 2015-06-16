package com.atayun.hgs.activity;

/*Author:yaowei
 * 2015-5-15
 * 主要功能描述：1、用户使用找回密码时候，输入正确的验证码和重置密码，可以完成密码重置。
 *           2、重新发送验证码
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
import com.atayun.hgs.util.JsonObjectPostRequest;
import com.atayun.hgs.modle.TimeButton;
import com.atayun.hgs.url.ConnData;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ResetPasswordActivity extends Activity implements OnClickListener {
	private EditText et1;
	private EditText et2;
	private Button button2;
	private TimeButton v;
	private String url = ConnData.URL;;
	private String userMobile = ResetPhoneActivity.userMobile;
	private String codeSMS = ResetPhoneActivity.codeSMS;
	private String sendSMS;
	private String resetPswState;
	private Map<String, String> mMap;
	private Map<String, String> mMap2;

	// UserBean userBean=new UserBean();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		setContentView(R.layout.activity_resetpassword);
		// 用于得到屏幕的宽高
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		// 组件初始化
		et1 = (EditText) findViewById(R.id.Code);
		et2 = (EditText) findViewById(R.id.Password);
		v = (TimeButton) findViewById(R.id.getCode);

		button2 = (Button) findViewById(R.id.confirm);
		v.onCreate(savedInstanceState);
		v.setTextAfter("秒后重新获取").setTextBefore("重新获取验证码").setLenght(60 * 1000);

		button2.setOnClickListener(this);
		v.setOnClickListener(this);
	}

	// 按钮监听
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.getCode:// 点击获取验证码按钮
			// String userMobile=userBean.getUserPhone();
			mMap2 = new HashMap<String, String>();
			mMap2.put("method", "reValidateMobile");
			mMap2.put("userMobile", userMobile);
			// Log.v("Phone", msg)

			// 请求服务
			RequestQueue requestQueue2 = Volley.newRequestQueue(this);
			JsonObjectPostRequest jsonObjectPostRequest2 = new JsonObjectPostRequest(
					url, new Response.Listener<JSONObject>() {

						@Override
						public void onResponse(JSONObject response) {
							try {
								if (response == null) {

									Toast.makeText(ResetPasswordActivity.this,
											"没有得到数据", Toast.LENGTH_SHORT)
											.show();
									return;

								} else {
									sendSMS = response.getString("sendSMS");
									codeSMS = response.getString("codeSMS");
									Log.v("重发的验证码是：", codeSMS);

									switch (sendSMS) {
									case "200":
										Toast.makeText(
												ResetPasswordActivity.this,
												"短信重新发送成功", Toast.LENGTH_SHORT)
												.show();

										break;
									case "10":
										Toast.makeText(
												ResetPasswordActivity.this,
												"短信发送失败", Toast.LENGTH_SHORT)
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
							Toast.makeText(ResetPasswordActivity.this,
									"获取失败，请检查网络", Toast.LENGTH_SHORT).show();

						}
					}, mMap2);
			requestQueue2.add(jsonObjectPostRequest2);
			break;

		case R.id.confirm:
			String code = et1.getText().toString().trim();
			String userPassword = et2.getText().toString().trim();
			mMap = new HashMap<String, String>();
			mMap.put("method", "resetUserPassword");
			mMap.put("code", code);
			mMap.put("userPassword", userPassword);
			mMap.put("userMobile", userMobile);
			Log.d("用户输入的code", code);
			Log.d("服务器返回的的code", codeSMS);
			Toast.makeText(ResetPasswordActivity.this, code + "    " + codeSMS,
					Toast.LENGTH_SHORT).show();

			// 请求服务
			if (code.equals(codeSMS)) {
				RequestQueue requestQueue = Volley.newRequestQueue(this);
				JsonObjectPostRequest jsonObjectPostRequest = new JsonObjectPostRequest(
						url, new Response.Listener<JSONObject>() {

							@Override
							public void onResponse(JSONObject response) {
								try {
									if (response == null) {

										Toast.makeText(
												ResetPasswordActivity.this,
												"没有得到数据", Toast.LENGTH_SHORT)
												.show();
										return;

									} else {
										resetPswState = response
												.getString("resetPswState");

										Toast.makeText(
												ResetPasswordActivity.this,
												resetPswState,
												Toast.LENGTH_SHORT).show();

										switch (resetPswState) {
										case "200":
											Toast.makeText(
													ResetPasswordActivity.this,
													"密码重置成功！",
													Toast.LENGTH_SHORT).show();
											Intent intent = new Intent(
													ResetPasswordActivity.this,
													LoginActivity.class);
											finish();
											startActivity(intent);
											break;
										case "10":
											Toast.makeText(
													ResetPasswordActivity.this,
													"您输入的密码不符合规则",
													Toast.LENGTH_SHORT).show();
											break;
										case "20":
											Toast.makeText(
													ResetPasswordActivity.this,
													"验证码不正确",
													Toast.LENGTH_SHORT).show();
											break;
										case "30":
											Toast.makeText(
													ResetPasswordActivity.this,
													"网络原因出错，请稍候再试",
													Toast.LENGTH_SHORT).show();
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
								Toast.makeText(ResetPasswordActivity.this,
										"获取失败，请检查网络", Toast.LENGTH_SHORT)
										.show();

							}
						}, mMap);
				requestQueue.add(jsonObjectPostRequest);

				break;

			} else {
				Toast.makeText(ResetPasswordActivity.this, "验证码输入不正确",
						Toast.LENGTH_SHORT).show();
			}

		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		v.onDestroy();
		super.onDestroy();
	}
}