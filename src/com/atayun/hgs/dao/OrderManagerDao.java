package com.atayun.hgs.dao;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import com.atayun.hgs.modle.OrdermanagerIterm;

public class OrderManagerDao {
	public static ArrayList<OrdermanagerIterm> getorderManagerDao(JSONArray Cargo) {
	// 处理获取到的JSonArray
	ArrayList<OrdermanagerIterm> ordermanagerIterm = new ArrayList<OrdermanagerIterm>();
	
	
	Field[] field = OrdermanagerIterm.class.getDeclaredFields();
	for (int i = 0; i < Cargo.length(); i++) {
		OrdermanagerIterm g = new OrdermanagerIterm();
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
		ordermanagerIterm.add(g);
	}

	return ordermanagerIterm;

}
	
	

}
