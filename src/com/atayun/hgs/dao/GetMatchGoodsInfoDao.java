package com.atayun.hgs.dao;

/*
 * 处理经过算法匹配返回的JSON数据，最佳匹配和其他匹配共用这一个方法
 */
import java.lang.reflect.Field;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import com.atayun.hgs.modle.GoodsInfoItems;
import com.atayun.hgs.modle.MatchGoodsInfoItems;

public class GetMatchGoodsInfoDao {

	public static ArrayList<MatchGoodsInfoItems> matchGoodsInfoItems(
			JSONArray Cargo) {

		// 处理获取到的JSonArray
		ArrayList<MatchGoodsInfoItems> matchGoodsInfoItems = new ArrayList<MatchGoodsInfoItems>();

		Field[] field = MatchGoodsInfoItems.class.getDeclaredFields();
		for (int i = 0; i < Cargo.length(); i++) {
			MatchGoodsInfoItems g = new MatchGoodsInfoItems();
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
			matchGoodsInfoItems.add(g);
		}

		return matchGoodsInfoItems;

	}
}
