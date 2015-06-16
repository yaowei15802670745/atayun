package com.atayun.hgs.dao;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import com.atayun.hgs.modle.CarDetailItems;

public class GetCarInfoDao {

	public static ArrayList<CarDetailItems> GetCarInfoDao(JSONArray Car) {

		// 处理获取到的JSonArray
		ArrayList<CarDetailItems> CarInfoItems = new ArrayList<CarDetailItems>();

		for (int i = 0; i < Car.length(); i++) {

			String updateTime = null;
			String cariHeight = null;
			String cariPicFlag = null;
			String cariId = null;
			String cartId = null;
			String cariDlicFlag = null;
			String cariRouteNum = null;
			String cariDlicUrl = null;
			String cariPicUrl = null;
			String cariLoad = null;
			String cariLength = null;
			String cariVolume = null;
			String userId = null;
			String cariFlag = null;

			String cariWidth = null;
			String cariLpnum = null;
			try {
				updateTime = Car.getJSONObject(i).getString("updateTime");
				cariHeight = Car.getJSONObject(i).getString("cariHeight");
				cariPicFlag = Car.getJSONObject(i).getString("cariPicFlag");
				cariId = Car.getJSONObject(i).getString("cariId");
				cartId = Car.getJSONObject(i).getString("cartId");
				cariDlicFlag = Car.getJSONObject(i).getString("cariDlicFlag");
				cariRouteNum = Car.getJSONObject(i).getString("cariRouteNum");
				cariDlicUrl = Car.getJSONObject(i).getString("cariDlicUrl");
				cariPicUrl = Car.getJSONObject(i).getString("cariPicUrl");
				cariLoad = Car.getJSONObject(i).getString("cariLoad");
				cariLength = Car.getJSONObject(i).getString("cariLength");
				cariVolume = Car.getJSONObject(i).getString("cariVolume");
				userId = Car.getJSONObject(i).getString("userId");

				cariFlag = Car.getJSONObject(i).getString("cariFlag");
				cariWidth = Car.getJSONObject(i).getString("cariWidth");
				cariLpnum = Car.getJSONObject(i).getString("cariLpnum");

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			CarInfoItems.add(new CarDetailItems(updateTime, cariHeight,
					cariPicFlag, cariId, cartId, cariDlicFlag, cariRouteNum,
					cariDlicUrl, cariPicUrl, cariLoad, cariLength, cariVolume,
					userId, cariFlag, cariWidth, cariLpnum));
		}

		return CarInfoItems;

	}

}
