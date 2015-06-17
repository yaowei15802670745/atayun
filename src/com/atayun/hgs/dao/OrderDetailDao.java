package com.atayun.hgs.dao;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import com.atayun.hgs.modle.OrderDetail;


public class OrderDetailDao {

	
	public static ArrayList<OrderDetail> getorderManagerDao(
			JSONArray Cargo) {
		// 处理获取到的JSonArray
		ArrayList<OrderDetail> orderDetail = new ArrayList<OrderDetail>();

		Field[] field = OrderDetail.class.getDeclaredFields();
		for (int i = 0; i < Cargo.length(); i++) {
			OrderDetail g = new OrderDetail();
			for (Field f : field) {
				try {
					f.setAccessible(true);
					f.set(g, Cargo.getJSONObject(i).getString(f.getName()));
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			orderDetail.add(g);
		}
		return orderDetail;
	}

}
