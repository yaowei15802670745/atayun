package com.atayun.hgs.dao;

//处理未经匹配的查看货源，返回的JSON数组
import java.lang.reflect.Field;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;

import com.atayun.hgs.modle.GoodsInfoItems;

public class GetGoodsInfoDao {

	public static ArrayList<GoodsInfoItems> getGoodsInfoDao(JSONArray Cargo) {

		// 处理获取到的JSonArray
		ArrayList<GoodsInfoItems> goodsInfoItems = new ArrayList<GoodsInfoItems>();

//		for (int i = 0; i < Cargo.length(); i++) {
//			String userId = null;
//			String cargoInfoId = null;// 货物的唯一标志
//			String userName = null;// 用户名（姓名/企业名）
//			String cargoType = null;// 货源类型
//			String transportType = null;// 运输类型
//			String userAddr = null;// 用户联系地址
//			String userMobile = null;// 用户手机号（可用于用户登录）
//			String cargoInfoPublished = null;// 货源信息发布时间
//			String cargoInfoStart = null;// 起点
//			String cargoInfoSStreet = null;// 起点详细地址
//			String cargoInfoEnd = null;// 终点
//			String cargoInfoEStreet = null;// 终点详细地址
//			String cargoInfoLng = null;// 起点货源经度
//			String cargoInfoLat = null;// 起点货源纬度
//			String cargoInfoELng = null;// 终点货源经度
//			String cargoInfoELat = null;// 终点货源纬度
//			String cargoInfoDeliTime = null;// 发货时间
//			String cargoInfoPrice = null;// 运价
//			String cargoInfoLenth = null;// 货长
//			String cargoInfoWidth = null;// 货宽
//			String cargoInfoHeight = null;// 货高
//			String cargoInfoRlen = null;// 需要的车长
//			String cargoInfoVunit = null;// 车辆体积单位
//			String cargoInfoLunit = null;// 车辆载重单位
//			String cargoInfoVolume = null;// 货物体积
//			String cargoInfoLoad = null;// 载重（单位：吨）
//			String cargoInfoDesc = null;// 货物描述
//			String cargoInfoContacts = null;// 收货联系人
//			String cargoInfoContactWay = null;// 收货联系方式
//			String cargoInfoPicturl = null;// 货物图片
//			String cargoInfoFlag = null;// 货物的状态（0 未被运输 ； 1 已运输 已运输货物不在编辑）
//			String cargoInfoUpdateTime = null;// 货物记录更新时间
//
//			try {
//				userId = Cargo.getJSONObject(i).getString("userId");
//				cargoInfoId = Cargo.getJSONObject(i).getString("cargoInfoId");
//				userName = Cargo.getJSONObject(i).getString("userName");
//				cargoType = Cargo.getJSONObject(i).getString("cargoType");
//				transportType = Cargo.getJSONObject(i).getString(
//						"transportType");
//				userAddr = Cargo.getJSONObject(i).getString("userAddr");
//				userMobile = Cargo.getJSONObject(i).getString("userMobile");
//				cargoInfoPublished = Cargo.getJSONObject(i).getString(
//						"cargoInfoPublished");
//				cargoInfoStart = Cargo.getJSONObject(i).getString(
//						"cargoInfoStart");
//				cargoInfoSStreet = Cargo.getJSONObject(i).getString(
//						"cargoInfoSStreet");
//				cargoInfoEnd = Cargo.getJSONObject(i).getString("cargoInfoEnd");
//				cargoInfoEStreet = Cargo.getJSONObject(i).getString(
//						"cargoInfoEStreet");
//				cargoInfoLng = Cargo.getJSONObject(i).getString("cargoInfoLng");
//				cargoInfoContactWay = Cargo.getJSONObject(i).getString(
//						"cargoInfoContactWay");
//
//				cargoInfoLat = Cargo.getJSONObject(i).getString("cargoInfoLat");
//
//				cargoInfoELng = Cargo.getJSONObject(i).getString(
//						"cargoInfoELng");
//				cargoInfoELat = Cargo.getJSONObject(i).getString(
//						"cargoInfoELat");
//				cargoInfoDeliTime = Cargo.getJSONObject(i).getString(
//						"cargoInfoDeliTime");
//				cargoInfoPrice = Cargo.getJSONObject(i).getString(
//						"cargoInfoPrice");
//				cargoInfoLenth = Cargo.getJSONObject(i).getString(
//						"cargoInfoLenth");
//				cargoInfoWidth = Cargo.getJSONObject(i).getString(
//						"cargoInfoWidth");
//				cargoInfoHeight = Cargo.getJSONObject(i).getString(
//						"cargoInfoHeight");
//				cargoInfoRlen = Cargo.getJSONObject(i).getString(
//						"cargoInfoRlen");
//				cargoInfoVunit = Cargo.getJSONObject(i).getString(
//						"cargoInfoVunit");
//				cargoInfoLunit = Cargo.getJSONObject(i).getString(
//						"cargoInfoLunit");
//				cargoInfoDesc = Cargo.getJSONObject(i).getString(
//						"cargoInfoDesc");
//				cargoInfoContacts = Cargo.getJSONObject(i).getString(
//						"cargoInfoContacts");
//				cargoInfoContactWay = Cargo.getJSONObject(i).getString(
//						"cargoInfoContactWay");
//				cargoInfoPicturl = Cargo.getJSONObject(i).getString(
//						"cargoInfoPicturl");
//				cargoInfoFlag = Cargo.getJSONObject(i).getString(
//						"cargoInfoFlag");
//				cargoInfoUpdateTime = Cargo.getJSONObject(i).getString(
//						"cargoInfoUpdateTime");
//				cargoInfoVolume = Cargo.getJSONObject(i).getString(
//						"cargoInfoVolume");
//				cargoInfoLoad = Cargo.getJSONObject(i).getString(
//						"cargoInfoLoad");
//
//			} catch (JSONException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//			goodsInfoItems.add(new GoodsInfoItems(userId, cargoInfoId,
//					cargoInfoLunit, cargoInfoVunit, cargoInfoRlen,
//					cargoInfoELat, cargoInfoELng, cargoInfoEStreet,
//					cargoInfoSStreet, userMobile, userAddr, userName,
//					cargoType, transportType, cargoInfoEnd, cargoInfoLoad,
//					cargoInfoLat, cargoInfoUpdateTime, cargoInfoDesc,
//					cargoInfoFlag, cargoInfoPublished, cargoInfoPicturl,
//					cargoInfoContactWay, cargoInfoLng, cargoInfoHeight,
//					cargoInfoPrice, cargoInfoLenth, cargoInfoVolume,
//					cargoInfoContacts, cargoInfoWidth, cargoInfoStart,
//					cargoInfoDeliTime));
//		}

		Field[] field = GoodsInfoItems.class.getDeclaredFields();
		for (int i = 0; i < Cargo.length(); i++) {
			GoodsInfoItems g = new GoodsInfoItems();
			for (Field f : field) {
				try {
					f.setAccessible(true);
					f.set(g, Cargo.getJSONObject(i).getString(f.getName()));
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			goodsInfoItems.add(g);
		}

		return goodsInfoItems;

	}

}
