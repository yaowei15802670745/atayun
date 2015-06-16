package com.atayun.hgs.activity;

/*@yaowei
 * 2015-5-14
 * 主要功能：根据用户输入的手机号码，验证手机号码是否符合规范，是否已经注册。
 找回密码要在已经注册的情况下，才可以进行。
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
import com.atayun.hgs.url.ConnData;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ResetPhoneActivity extends Activity {
	private EditText phone;
	private Button sendphone;
	public static String codeSMS = "";
	public static String userMobile;
	private String sendSMS;

	private Map<String, String> mMap;
	private String url = ConnData.URL;

	// UserBean userBean=new UserBean();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 去掉标题栏
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 去掉通知栏
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_resetphone);
		phone = (EditText) this.findViewById(R.id.Phone);
		sendphone = (Button) this.findViewById(R.id.Sendphone);
	}

	public void Send(View v) {

		userMobile = phone.getText().toString().trim();
		// userBean.setUserPhone(userPhone);//将用户手机号码存进userBean

		mMap = new HashMap<String, String>();
		mMap.put("method", "validateMobileAtFindPsw");
		mMap.put("userMobile", userMobile);

		// 请求服务
		RequestQueue requestQueue = Volley.newRequestQueue(this);
		JsonObjectPostRequest jsonObjectPostRequest = new JsonObjectPostRequest(
				url, new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						try {

							sendSMS = response.getString("sendSMS");
							codeSMS = response.getString("codeSMS");
							Log.d("codeSMS", codeSMS);

							switch (sendSMS) {
							case "200":
								Toast.makeText(ResetPhoneActivity.this,
										"短信发送成功", Toast.LENGTH_SHORT).show();
								Intent intent = new Intent(
										ResetPhoneActivity.this,
										ResetPasswordActivity.class);
								startActivity(intent);

								break;
							case "10":
								Toast.makeText(ResetPhoneActivity.this,
										"短信发送失败，手机号不符合规范", Toast.LENGTH_SHORT)
										.show();
								break;
							case "20":
								Toast.makeText(ResetPhoneActivity.this,
										"短信发送失败，手机号未注册", Toast.LENGTH_SHORT)
										.show();
								break;

							default:
								break;
							}

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						Toast.makeText(ResetPhoneActivity.this, "获取失败，请检查网络",
								Toast.LENGTH_SHORT).show();

					}
				}, mMap);
		requestQueue.add(jsonObjectPostRequest);

	}
}
