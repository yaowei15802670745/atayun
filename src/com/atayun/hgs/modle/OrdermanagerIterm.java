package com.atayun.hgs.modle;

public class OrdermanagerIterm {

	private String orderFlag;// 订单状态
	private String orderNo;//订单编号
	private String updateTime;//
	private String userId;//
	private String orderPrice;//
	private String orderId;//

	public OrdermanagerIterm(String orderFlag, String orderNo,
			String updateTime, String userId, String orderPrice, String orderId) {
		this.orderFlag = orderFlag;
		this.updateTime = updateTime;
		this.orderNo = orderNo;
		this.userId = userId;
		this.orderPrice = orderPrice;
		this.orderId = orderId;

	}

	public String getOrderFlag() {
		return orderFlag;
	}

	public void setOrderFlag(String orderFlag) {
		this.orderFlag = orderFlag;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public OrdermanagerIterm(){
		super();
	}
}
