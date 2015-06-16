package com.atayun.hgs.activity;

/*
 * Author:yaowei
 * 2015-5-15
 * 主要功能描述：1、用户在验证码输入正确，设置的密码符合规范的情况下，进行默认的注册
 *          2、验证码超时没有输入，重新获取验证码
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
import com.atayun.hgs.util.JsonObjectPostRequest;
import com.atayun.hgs.modle.IDApplication;
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

public class SetPassWordActivity extends Activity implements OnClickListener {
	private EditText etCode;
	private EditText etPassword;
	private Button Confirm;
	private TimeButton v;
	private String userMobile;// 获取RegisterActivity中用户输入的手机号码
	private String codeSMS = "";/*
								 * 获取RegisterActivity中服务器返回的验证码，
								 * 为后面验证用户输入的验证码与服务器返回的是否一样
								 */
	private String url = ConnData.URL;
	private String FlagID;
	private String sendSMS;// 重新发送验证码返回的参数
	private String registerState;// 点击完成注册返回的参数
	private Map<String, String> mMap;// 用于封装 完成注册提交的参数
	private Map<String, String> mMap2;// 用于封装重新发送验证码提交的参数
	private int screenwidth, screenheight;

	// UserBean userBean=new UserBean();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		setContentView(R.layout.activity_setpassword);

		FlagID = ((IDApplication) getApplication()).getFlagID();
		userMobile = ((IDApplication) getApplication()).getUserPhone();
		Bundle bundle = this.getIntent().getExtras();
		codeSMS = bundle.getString("codeSMS");
		// 用于得到屏幕的宽高
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		screenwidth = dm.widthPixels;
		screenheight = dm.heightPixels;
		// 组件初始化
		etCode = (EditText) findViewById(R.id.etCode);
		etPassword = (EditText) findViewById(R.id.etPassword);
		Confirm = (Button) findViewById(R.id.confirm);
		v = (TimeButton) findViewById(R.id.getCodetrue);// 重新获取验证码view初始化
		v.onCreate(savedInstanceState);
		v.setTextAfter("秒后重新获取").setTextBefore("重新获取验证码").setLenght(60 * 1000);// 设置60s后可重新获取
		Confirm.setOnClickListener(this);
		v.setOnClickListener(this);
	}

	// 创建弹窗，自定义布局，当用户提交注册并且通过验证，启动弹幕，让用户选择继续完善信息还是先进入主页浏览
	private void CreateDialog() {
		// 将xml文件实例化为View对象
		Dialog dialog = new Dialog(this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog_layout);
		dialog.setTitle(null);
		Window dialWindow = dialog.getWindow();
		WindowManager.LayoutParams lp = dialWindow.getAttributes();
		dialWindow.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP);
		lp.y = (int) (screenheight * 1 / 4);
		lp.width = (int) (screenwidth * 3 / 4);
		lp.alpha = 0.6f;// 透明度
		dialWindow.setAttributes(lp);
		dialog.show();

		Button look = (Button) dialog.findViewById(R.id.toast_look);
		Button confirm = (Button) dialog.findViewById(R.id.toast_complete);
		look.setOnClickListener(this);
		confirm.setOnClickListener(this);

	}

	private void confirm() {// 提交信息的方法
		String code = etCode.getText().toString().trim();
		String userPassword = etPassword.getText().toString().trim();

		mMap = new HashMap<String, String>();
		mMap.put("method", "register");
		mMap.put("userPassword", userPassword);
		mMap.put("userMobile", userMobile);
		mMap.put("userFlag", FlagID);

		Log.d("userMobile", userMobile);
		Log.d("FlagID", FlagID);
		Log.d("用户输入的code", code);
		Log.d("系统返回的code", codeSMS);
		Toast.makeText(SetPassWordActivity.this, code + "    " + codeSMS,
				Toast.LENGTH_SHORT).show();

		// String codeSMS=userBean.getCodeSMS();
		// Intent codeintent=getIntent();
		// String codeSMS=codeintent.getStringExtra("codeSMS");

		if (code.equals(codeSMS)) { // 如果用户输入的验证码和服务器返回的验证码一致，进行注册
			// 请求服务
			RequestQueue requestQueue = Volley.newRequestQueue(this);
			JsonObjectPostRequest jsonObjectPostRequest = new JsonObjectPostRequest(
					url, new Response.Listener<JSONObject>() {

						@Override
						public void onResponse(JSONObject response) {
							try {
								if (response == null) {

									Toast.makeText(SetPassWordActivity.this,
											"没有得到数据", Toast.LENGTH_SHORT)
											.show();
									return;

								} else {
									registerState = response
											.getString("registerState");

									Toast.makeText(SetPassWordActivity.this,
											registerState, Toast.LENGTH_SHORT)
											.show();

									switch (registerState) {
									case "200":
										Toast.makeText(
												SetPassWordActivity.this,
												"恭喜您注册成功！", Toast.LENGTH_SHORT)
												.show();
										String userId = response
												.getString("userId");
										((IDApplication) getApplication())
												.setUserId(userId);

										((IDApplication) getApplication())
												.setFlagID(FlagID);

										CreateDialog();// 如果注册成功跳出弹窗

										break;
									case "10":
										Toast.makeText(
												SetPassWordActivity.this,
												"您输入的密码不符合规则",
												Toast.LENGTH_SHORT).show();
										break;
									// case "20":
									// Toast.makeText(SetPassWordActivity.this,
									// "验证码不正确",
									// Toast.LENGTH_SHORT).show();
									// break;
									case "30":
										Toast.makeText(
												SetPassWordActivity.this,
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
							Toast.makeText(SetPassWordActivity.this,
									"获取失败，请检查网络", Toast.LENGTH_SHORT).show();

						}
					}, mMap);
			requestQueue.add(jsonObjectPostRequest);

			return;
		} else {

			Toast.makeText(SetPassWordActivity.this, "验证码输入不正确",
					Toast.LENGTH_SHORT).show();
			return;

		}
	}

	// 分装获取验证码的方法

	private void getCode() {
		Log.d("userMobile", userMobile);
		Log.d("flagid", ((IDApplication) getApplication()).getFlagID());
		mMap2 = new HashMap<String, String>();
		mMap2.put("method", "reValidateMobile");
		mMap2.put("userMobile", userMobile);

		// Log.v("Phone", msg)

		// 请求服务，提交map 参数
		RequestQueue requestQueue2 = Volley.newRequestQueue(this);
		JsonObjectPostRequest jsonObjectPostRequest2 = new JsonObjectPostRequest(
				url, new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						try {
							if (response == null) {

								Toast.makeText(SetPassWordActivity.this,
										"没有得到数据", Toast.LENGTH_SHORT).show();
								return;

							} else {
								sendSMS = response.getString("sendSMS");
								codeSMS = response.getString("codeSMS");
								Log.d("重发的验证码是：", codeSMS);

								switch (sendSMS) {
								case "200":
									Toast.makeText(SetPassWordActivity.this,
											"短信重新发送成功", Toast.LENGTH_SHORT)
											.show();

									break;
								case "10":
									Toast.makeText(SetPassWordActivity.this,
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
						Toast.makeText(SetPassWordActivity.this, "获取失败，请检查网络",
								Toast.LENGTH_SHORT).show();

					}
				}, mMap2);
		requestQueue2.add(jsonObjectPostRequest2);
	}

	// 按钮监听
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.confirm:// 点击完成按钮
			confirm();
			break;

		case R.id.toast_look:// 点击弹幕 先看看 按钮
			Intent intent = new Intent(SetPassWordActivity.this, PageFoot.class);// 跳转到主界面
			if (FlagID.equals("0")) {
				((IDApplication) getApplication()).setUserBaseFlag("0");
				((IDApplication) getApplication()).setCarUserFlag("0");
				((IDApplication) getApplication()).setCargoFlag("-1");
				((IDApplication) getApplication()).setCompanyFlag("-1");
			} else {
				((IDApplication) getApplication()).setUserBaseFlag("0");
				((IDApplication) getApplication()).setCarUserFlag("-1");
				((IDApplication) getApplication()).setCargoFlag("0");
				((IDApplication) getApplication()).setCompanyFlag("-1");
			}

			finish();
			startActivity(intent);
			break;
		case R.id.toast_complete:// 点击完善信息，跳到信息编辑页面
			if (FlagID.equals("1")) {// 如果是货主，跳到货主个人 信息完善界面
				Intent intent2 = new Intent(SetPassWordActivity.this,
						GoodsPersonlInfo.class);//
				finish();
				startActivity(intent2);
			} else {// 如果是车主，跳转到车主个人信息完善
				Intent intent2 = new Intent(SetPassWordActivity.this,
						CarPersonlInfo.class);//
				finish();
				startActivity(intent2);
			}

			break;
		case R.id.getCode:// 点击重新获取验证码按钮

			getCode();

			break;

		}

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		v.onDestroy();
		super.onDestroy();
	}
}