package com.atayun.hgs.activity;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.atayun.hgs.modle.IDApplication;
import com.atayun.hgs.url.ConnData;
import com.atayun.hgs.util.JsonObjectPostRequest;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class CarPersonlInfo extends Activity implements OnClickListener {
	private EditText per_etName;// 姓名
	private EditText per_etPhone;// 电话
	private EditText per_etID;// 身份证号
	private ImageButton per_UploadPositive;// 上传正面
	private ImageButton per_UploadNegative;// 上传反面
	private Spinner per_Rovince;// 选择省份
	private Spinner per_City;// 选择城市
	private EditText per_etDetaileAddre;// 详细地址
	private Button SUbmit;// 完成
	private String  userIDCardURLN = "";
	private String  userIDCardURLP = "";
	private String userProvince;// 省
	private String userCity;
	private String url = ConnData.improveCarUser;
	private Map<String, String> mMap;// 用于封装 完成参数
	private int screenwidth, screenheight;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_carpersoninfo);
		// 用于得到屏幕的宽高
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		screenwidth = dm.widthPixels;
		screenheight = dm.heightPixels;
		initView();
	}

	private void initView() {
		// per_complete=(Button) findViewById(R.id.per_complete);
		per_etName = (EditText) findViewById(R.id.per_etName);
		per_etPhone = (EditText) findViewById(R.id.per_etPhone);
		per_etID = (EditText) findViewById(R.id.per_etID);
		per_UploadPositive = (ImageButton) findViewById(R.id.per_UploadPositive);
		per_UploadNegative = (ImageButton) findViewById(R.id.per_UploadNegative);
		// per_etPhone=(EditText) findViewById(R.id.per_etName);
		per_Rovince = (Spinner) findViewById(R.id.per_Rovince);
		per_City = (Spinner) findViewById(R.id.per_City);
		per_etDetaileAddre = (EditText) findViewById(R.id.per_etDetaileAddre);
		SUbmit = (Button) findViewById(R.id.SUbmit);
		per_UploadPositive.setOnClickListener(this);
		per_UploadNegative.setOnClickListener(this);
		SUbmit.setOnClickListener(this);
		per_Rovince
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						userProvince = (String) per_Rovince
								.getItemAtPosition(arg2);

					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {

					}

				});

		per_City.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				userCity = (String) per_City.getItemAtPosition(arg2);

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}

		});

	}

	private void improveCargoUser() {
		String userId = ((IDApplication) getApplication()).getUserId();
		String userName = per_etName.getText().toString().trim();
		String caroMobile = per_etPhone.getText().toString().trim();
		String userIDCard = per_etID.getText().toString().trim();
		String userAddr = per_etDetaileAddre.getText().toString().trim();

		 if(userIDCardURLP.equals("")||userIDCardURLN.equals("")){
			 Toast.makeText(CarPersonlInfo.this, "身份证正面，反面不能为空", Toast.LENGTH_SHORT)
				.show();
		return;
		 }
		if (userName == null || userName.equals("")) {
			Toast.makeText(CarPersonlInfo.this, "用户名不能为空", Toast.LENGTH_SHORT)
					.show();
			return;
		}
		if (caroMobile == null || caroMobile.equals("")) {
			Toast.makeText(CarPersonlInfo.this, "手机号码不能为空", Toast.LENGTH_SHORT)
					.show();
			return;
		}
		if (userProvince == null || userProvince.equals("")) {
			Toast.makeText(CarPersonlInfo.this, "省份不能为空", Toast.LENGTH_SHORT)
					.show();
			return;
		}
		if (userCity == null || userCity.equals("")) {
			Toast.makeText(CarPersonlInfo.this, "城市不能为空", Toast.LENGTH_SHORT)
					.show();
			return;
		}

		mMap = new HashMap<String, String>();
		mMap.put("method", "improveCarUser");
		mMap.put("userId", userId);
		mMap.put("userName", userName);
		mMap.put("cagoMobile", caroMobile);
		mMap.put("userIDCard", userIDCard);
		mMap.put("userIDCardURLP", userIDCardURLP);
		mMap.put("userIDCardURLN", userIDCardURLN);
		mMap.put("userProvince", userProvince);
		mMap.put("userCity", userCity);
		mMap.put("userAddr", userAddr);

		Log.d("method", "improveCargoUser");
		Log.d("userId", userId);
		Log.d("userName", userName);
		Log.d("userMobile", caroMobile);
		Log.d("cagoIdCard", userIDCard);
		Log.d("cagoIdCardURLP", userIDCardURLP);
		Log.d("cagoIdCardURLN", userIDCardURLN);
		Log.d("cagoProvince", userProvince);
		Log.d("userCity", userCity);
		Log.d("userAddr", userAddr);

		// 请求服务
		RequestQueue requestQueue = Volley.newRequestQueue(this);
		JsonObjectPostRequest jsonObjectPostRequest = new JsonObjectPostRequest(
				url, new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						try {

							String improveUserState = response
									.getString("improveUserState");

							switch (improveUserState) {
							case "200":
								Toast.makeText(CarPersonlInfo.this, "信息提交成功",
										Toast.LENGTH_SHORT).show();
	
							
									// 将用户认证状态放进全局变量
									((IDApplication) getApplication())
											.setCarUserFlag("-1");
									((IDApplication) getApplication())
											.setCargoFlag("0");
									((IDApplication) getApplication())
											.setCompanyFlag("-1");
									((IDApplication) getApplication()).setUserBaseFlag("1");
									Intent intent = new Intent(
											CarPersonlInfo.this,
											CarownerInfo.class);
									finish();
									startActivity(intent);
									break;

							case "10":
								Toast.makeText(CarPersonlInfo.this, "个人信息完善操作失败",
										Toast.LENGTH_SHORT).show();
								break;
							case"20":
								Toast.makeText(CarPersonlInfo.this, "身份证已使用,信息完善失败",
										Toast.LENGTH_SHORT).show();
								break;
							case"30":
								Toast.makeText(CarPersonlInfo.this, "用户信息完善失败，身份证不符合规范",
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
						Toast.makeText(CarPersonlInfo.this, "获取失败，请检查网络",
								Toast.LENGTH_SHORT).show();

					}
				}, mMap);
		requestQueue.add(jsonObjectPostRequest);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.SUbmit:
			improveCargoUser();
			break;
		case R.id.per_UploadPositive:
			
			Intent intent=new Intent(CarPersonlInfo.this,UpLoadPictureActivity.class);
			intent.putExtra("OBject", "idcardImages");//将需要保存到的文件夹名传过去
			startActivityForResult( intent,  1);
//			startActivity(intent);
			break;
		case R.id.per_UploadNegative:
			Intent intent2=new Intent(CarPersonlInfo.this,UpLoadPictureActivity.class);
			intent2.putExtra("OBject", "idcardImages");
			startActivityForResult( intent2,  2);
//			startActivity(intent2);
			break;
		}
	
	}

	
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode){

            case 1:
            //来自按钮1的请求，作相应业务处理
            	userIDCardURLP=data.getStringExtra("picname");
            	Log.d("正面", userIDCardURLP);
            	//当图片上传成功，将图片给按钮赋值

//        		Bitmap bm = null;
//        		// 外界的程序访问ContentProvider所提供数据 可以通过ContentResolver接口
//        		ContentResolver resolver = getContentResolver();
            	  if(userIDCardURLP.equals("")==false){
        			
        			Bitmap bm=UpLoadPictureActivity.bm;
        			per_UploadPositive.setImageBitmap(bm);
        		}
        		
//            	String picurl=data.getStringExtra("picurl");
//            	per_UploadPositive.setb
            	break;
            case 2:
            	userIDCardURLN=data.getStringExtra("picname");
            	Log.d("反面", userIDCardURLN);
             if(userIDCardURLN.equals("")==false){
        			
        			Bitmap bm=UpLoadPictureActivity.bm;
        			per_UploadNegative.setImageBitmap(bm);
        		}
            	break;
            //来自按钮2的请求，作相应业务处理
         }
   }
	
	
}
