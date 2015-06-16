package com.atayun.hgs.activity;

/*
 * 用户注册时候完善个人信息
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
import com.atayun.hgs.url.ConnData;
import com.atayun.hgs.util.JsonObjectPostRequest;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class ComInfo extends Activity implements OnClickListener {
	private Map<String, String> mMap;
	private String url = ConnData.accreditationCargoOwner;
	private EditText com_CompanyName;//
	private EditText com_etNationTaxnum;// 国税号
	private EditText com_etPhone;// 企业电话
	private EditText com_etBusLican;// 营业执照
	private EditText com_etRegistTx;// 税务登记证副本
	private EditText com_etOpenPemit;// 开户许可证
	private EditText com_etTemModel;// 需求税票模板
	private EditText com_etDetailedAddre;// 详细地址
	private String compCPPicURL = "";// -营业执照
	private String compSWDJFBURL = "";// -税务登记证副本
	private String compKHXKZURL = "";// -开户许可证
	private String compXQSWMBURL = "";// -需求税票模板

	private ImageButton ph_etBusLican, ph_etRegistTx, ph_etOpenPemit,
			ph_etTemModel;
	private Spinner ow_cariLoad;// 选择省
	private Spinner ow_cariVolume;// 选择市
	private String compProvice;// 省
	private String compCity;// 市
	private Button onClick_Skip;
	private Button com_Submit;// 完成
	private String userId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_com_info);
		userId = ((IDApplication) getApplication()).getUserId();
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		com_CompanyName = (EditText) findViewById(R.id.com_CompanyName);
		com_etNationTaxnum = (EditText) findViewById(R.id.com_etNationTaxnum);
		com_etPhone = (EditText) findViewById(R.id.com_etPhone);
		com_etBusLican = (EditText) findViewById(R.id.com_etBusLican);
		com_etRegistTx = (EditText) findViewById(R.id.com_etRegistTx);
		com_etOpenPemit = (EditText) findViewById(R.id.com_etOpenPemit);
		com_etTemModel = (EditText) findViewById(R.id.com_etTemModel);
		com_etDetailedAddre = (EditText) findViewById(R.id.com_etDetailedAddre);
		// 设置图片路径的editext不可编辑
		com_etBusLican.setEnabled(false);
		com_etRegistTx.setEnabled(false);
		com_etOpenPemit.setEnabled(false);
		com_etTemModel.setEnabled(false);

		onClick_Skip = (Button) findViewById(R.id.com_Skip);
		com_Submit = (Button) findViewById(R.id.com_Submit);
		onClick_Skip.setOnClickListener(this);
		com_Submit.setOnClickListener(this);

		ph_etBusLican = (ImageButton) findViewById(R.id.ph_etBusLican);
		ph_etRegistTx = (ImageButton) findViewById(R.id.ph_etRegistTx);
		ph_etOpenPemit = (ImageButton) findViewById(R.id.ph_etOpenPemit);
		ph_etTemModel = (ImageButton) findViewById(R.id.ph_etTemModel);
		ph_etBusLican.setOnClickListener(this);
		ph_etRegistTx.setOnClickListener(this);
		ph_etOpenPemit.setOnClickListener(this);
		ph_etTemModel.setOnClickListener(this);

		ow_cariLoad = (Spinner) findViewById(R.id.ow_cariLoad);
		ow_cariVolume = (Spinner) findViewById(R.id.ow_cariVolume);
		com_Submit = (Button) findViewById(R.id.com_Submit);
		onClick_Skip = (Button) findViewById(R.id.com_Skip);

		ow_cariLoad
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						compProvice = (String) ow_cariLoad
								.getItemAtPosition(arg2);

					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {

					}

				});
		ow_cariVolume
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						compCity = (String) ow_cariVolume
								.getItemAtPosition(arg2);

					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {

					}

				});

	}

	public void comfic() {

		String compName = com_CompanyName.getText().toString().trim();// -企业名称
		String compTaxNo = com_etNationTaxnum.getText().toString().trim();// -国税号
		String compWorkPhone = com_etPhone.getText().toString().trim();// -企业电话
		// compCPPicURL=com_etBusLican.getText().toString().trim();// -营业执照
		// compSWDJFBURL=com_etRegistTx.getText().toString().trim();// -税务登记证副本
		// compKHXKZURL=com_etOpenPemit.getText().toString().trim();// -开户许可证
		// compXQSWMBURL=com_etTemModel.getText().toString().trim();// -需求税票模板
		// String compProvice=ow_cariLoad.getText().toString().trim();//
		// -公司注册地址-省
		// String compCity=com_CompanyName.getText().toString().trim();//
		// -公司注册地址-市
		String compStreet = com_etDetailedAddre.getText().toString().trim();// -详细地址

		mMap = new HashMap<String, String>();
		mMap.put("method", "accreditationCargoOwner");
		mMap.put("userId", userId);
		mMap.put("compName", compName);
		mMap.put("compTaxNo", compTaxNo);
		mMap.put("compWorkPhone", compWorkPhone);
		mMap.put("compCPPicURL", compCPPicURL);
		mMap.put("compSWDJFBURL", compSWDJFBURL);
		mMap.put("compKHXKZURL", compKHXKZURL);
		mMap.put("compXQSWMBURL", compXQSWMBURL);
		mMap.put("compProvice", compProvice);
		mMap.put("compCity", compCity);
		mMap.put("compStreet", compStreet);
		Log.d("method", "accreditationCargoOwner");
		Log.d("userId", userId);
		Log.d("compName", compName);
		Log.d("compTaxNo", compTaxNo);
		Log.d("compWorkPhone", compWorkPhone);
		Log.d("compCPPicURL", compCPPicURL);
		Log.d("compSWDJFBURL", compSWDJFBURL);
		Log.d("compKHXKZURL", compKHXKZURL);
		Log.d("compXQSWMBURL", compXQSWMBURL);
		Log.d("compProvice", compProvice);
		Log.d("compCity", compCity);
		Log.d("compStreet", compStreet);

		RequestQueue requestQueue = Volley.newRequestQueue(this);
		JsonObjectPostRequest jsonObjectPostRequest = new JsonObjectPostRequest(
				url, new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						try {

							String accdttCargoState = response
									.getString("accdttCargoState");

							switch (accdttCargoState) {
							case "200":
								Toast.makeText(ComInfo.this, "信息完善成功",
										Toast.LENGTH_SHORT).show();
								// 将用户认证状态放进全局变量
								((IDApplication) getApplication())
										.setCarUserFlag("-1");
								((IDApplication) getApplication())
										.setCargoFlag("1");
								((IDApplication) getApplication())
										.setCompanyFlag("1");
								((IDApplication) getApplication()).setUserBaseFlag("1");
								// 跳转到主界面
								Intent intent = new Intent(ComInfo.this,
										PageFoot.class);
								startActivity(intent);
								finish();
								break;
							case "10":
								Toast.makeText(ComInfo.this, "信息完善失败",
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
						Toast.makeText(ComInfo.this, "获取失败，请检查网络",
								Toast.LENGTH_SHORT).show();

					}
				}, mMap);
		requestQueue.add(jsonObjectPostRequest);

	}

	public void onClick_Skip() {

		Intent intent = new Intent(ComInfo.this, PageFoot.class);
		startActivity(intent);
		finish();

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ph_etBusLican:
			Intent intent = new Intent(ComInfo.this, UpLoadPictureActivity.class);
			intent.putExtra("OBject", "blicenceImages");// 将需要保存到的文件夹名传过去
			startActivityForResult(intent, 1);

			break;
		case R.id.ph_etRegistTx:
			Intent intent2 = new Intent(ComInfo.this, UpLoadPictureActivity.class);
			intent2.putExtra("OBject", "compSWDJFBURL");// 将需要保存到的文件夹名传过去
			startActivityForResult(intent2, 2);
			break;
		case R.id.ph_etOpenPemit:
			Intent intent3 = new Intent(ComInfo.this, UpLoadPictureActivity.class);
			intent3.putExtra("OBject", "compKHXKZURL");// 将需要保存到的文件夹名传过去
			startActivityForResult(intent3, 3);
			break;
		case R.id.ph_etTemModel:
			Intent intent4 = new Intent(ComInfo.this, UpLoadPictureActivity.class);
			intent4.putExtra("OBject", "compXQSWMBURL");// 将需要保存到的文件夹名传过去
			startActivityForResult(intent4, 4);
			break;
		case R.id.com_Submit:
			comfic();// 点击提交按钮
			break;

		case R.id.com_Skip:
			onClick_Skip();// 点击了跳过按钮进入主界面
			break;
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {

		case 1:
			// 来自按钮1的请求，作相应业务处理
			compCPPicURL = data.getStringExtra("picname");
			String urlpic = data.getStringExtra("picurl");
			Log.d("营业执照", compCPPicURL);
			com_etBusLican.setText(urlpic);

			break;
		case 2:
			compSWDJFBURL = data.getStringExtra("picname");
			Log.d("税务登记证副本", compSWDJFBURL);
			String urlpic2 = data.getStringExtra("picurl");
		
			com_etRegistTx.setText(urlpic2);
			break;
		case 3:
			// 来自按钮1的请求，作相应业务处理
			compKHXKZURL = data.getStringExtra("picname");
			Log.d("开户许可证", compKHXKZURL);
			String urlpic3 = data.getStringExtra("picurl");
			
			com_etOpenPemit.setText(urlpic3);

			break;
		case 4:
			compXQSWMBURL = data.getStringExtra("picname");
			Log.d("需求税票模板", compXQSWMBURL);
			String urlpic4 = data.getStringExtra("picurl");
		
			com_etTemModel.setText(urlpic4);
			break;

		}
	}
}
