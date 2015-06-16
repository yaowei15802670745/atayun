package com.atayun.hgs.activity;

/*Author:yaowei
 * 2015-5-15
 * 主要功能描述：注册时候根据用户输入的手机号码，验证手机号码是否符合规范，是否已经注册，获取用户输入的手机号码userMobile，
 * 获取服务端返回的验证码，为下一步SetPassWordActivity提交验证码时候起到验证验证码输入是否正确做准备
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	
	RadioGroup typegroup;//单选框初始化
	RadioButton carowner;
	RadioButton goodsowner;
	private String flagID="";
	private EditText phone;
	private Button sendphone;
	private String sendSMS = "";
	private String codeSMS = "";// 存储服务器返回的验证码，为下一步SetPassWordActivity提交验证码时候起到验证验证码输入是否正确做准备
	private String userMobile;// 存储用户输入的手机号码为下一步SetPassWordActivity获取用户手机号码做准备

	private Map<String, String> mMap;// 存储网络请求提交的参数
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
		setContentView(R.layout.activity_register);
		phone = (EditText) this.findViewById(R.id.Phone);
		sendphone = (Button) this.findViewById(R.id.Sendphone);
		
		typegroup = (RadioGroup) findViewById(R.id.type);
		carowner = (RadioButton) findViewById(R.id.carowner);
		goodsowner = (RadioButton) findViewById(R.id.goodsowner);
		// 单选框事件监听
		typegroup.setOnCheckedChangeListener(radiogpchange);
		
	}
	
	
	// 单选框事件
	private RadioGroup.OnCheckedChangeListener radiogpchange = new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == carowner.getId()) {
					flagID = "0";
					((IDApplication)getApplication()).setFlagID("0");//将全局变量用户身份设为0，表示车主
				} else if (checkedId == goodsowner.getId()) {
					flagID = "1";
					((IDApplication)getApplication()).setFlagID("1");
				}
			
			}
		};
	
	
	

	public void Send(View v) {
		Log.d("flagID", flagID);
		if(flagID.equals("0")||flagID.equals("1")){
			
			userMobile = phone.getText().toString().trim();
			((IDApplication)getApplication()).setUserPhone(userMobile);//将用户手机号码存进全局变量

			// userBean.setUserPhone(userPhone);//将用户手机号码存进userBean

			mMap = new HashMap<String, String>();
			mMap.put("method", "validateMobile");
			mMap.put("userMobile", userMobile);

			// 请求服务
			RequestQueue requestQueue = Volley.newRequestQueue(this);
			JsonObjectPostRequest jsonObjectPostRequest = new JsonObjectPostRequest(
					url, new Response.Listener<JSONObject>() {

						@Override
						public void onResponse(JSONObject response) {
							try {

								sendSMS = response.getString("sendSMS");

								// Toast.makeText(RegisterActivity.this,
								// "codeSMS"+codeSMS,
								// Toast.LENGTH_SHORT).show();
								// userBean.setCodeSMS(codeSMS);

								switch (sendSMS) {
								case "200":
									Toast.makeText(RegisterActivity.this,
											"短信发送成功,请填写验证码", Toast.LENGTH_SHORT)
											.show();
									codeSMS = response.getString("codeSMS");
									Log.d("验证码是", codeSMS);
									Bundle bundle=new Bundle();
									bundle.putString("codeSMS", codeSMS);
									

									Intent intent = new Intent(
											RegisterActivity.this,
											SetPassWordActivity.class);
									intent.putExtras(bundle);
									startActivity(intent);


									break;
								case "10":
									Toast.makeText(RegisterActivity.this,
											"短信发送失败，手机号不符合规范", Toast.LENGTH_SHORT)
											.show();
									break;
								case "20":
									Toast.makeText(RegisterActivity.this,
											"手机号已经注册,请更换手机号码",
											Toast.LENGTH_SHORT).show();
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
							Toast.makeText(RegisterActivity.this, "获取失败，请检查网络",
									Toast.LENGTH_SHORT).show();

						}
					}, mMap);
			requestQueue.add(jsonObjectPostRequest);
		}else{
			Toast.makeText(RegisterActivity.this,
					"请选择您要注册的版本", Toast.LENGTH_SHORT)
					.show();
			return;
			
		}

		

	}
}
