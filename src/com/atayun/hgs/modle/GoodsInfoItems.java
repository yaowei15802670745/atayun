package com.atayun.hgs.modle;

import java.lang.reflect.Field;

public class GoodsInfoItems {
	String userId = null;
	String cargoInfoId = null;// 货物的唯一标志
	String userName = null;// 用户名（姓名/企业名）
	String cargoType = null;// 货源类型
	String transportType = null;// 运输类型
	String userAddr = null;// 用户联系地址
	String userMobile = null;// 用户手机号（可用于用户登录）
	String cargoInfoPublished = null;// 货源信息发布时间
	String cargoInfoStart = null;// 起点
	String cargoInfoSStreet = null;// 起点详细地址
	String cargoInfoEnd = null;// 终点
	String cargoInfoEStreet = null;// 终点详细地址
	String cargoInfoLng = null;// 起点货源经度
	String cargoInfoLat = null;// 起点货源纬度
	String cargoInfoELng = null;// 终点货源经度
	String cargoInfoELat = null;// 终点货源纬度

	String cargoInfoDeliTime = null;// 发货时间
	String cargoInfoPrice = null;// 运价
	String cargoInfoLenth = null;// 货长
	String cargoInfoWidth = null;// 货宽
	String cargoInfoHeight = null;// 货高
	String cargoInfoRlen = null;// 需要的车长
	String cargoInfoVunit = null;// 车辆体积单位
	String cargoInfoLunit = null;// 车辆载重单位
	String cargoInfoVolume = null;// 货物体积
	String cargoInfoLoad = null;// 载重（单位：吨）
	String cargoInfoDesc = null;// 货物描述
	String cargoInfoContacts = null;// 收货联系人
	String cargoInfoContactWay = null;// 收货联系方式
	String cargoInfoPicturl = null;// 货物图片
	String cargoInfoFlag = null;// 货物的状态（0 未被运输 ； 1 已运输 已运输货物不在编辑）
	String cargoInfoUpdateTime = null;// 货物记录更新时间

	public GoodsInfoItems(String userId, String cargoInfoId,
			String cargoInfoLunit, String cargoInfoVunit, String cargoInfoRlen,
			String cargoInfoELat, String cargoInfoELng,
			String cargoInfoEStreet, String cargoInfoSStreet,
			String userMobile, String userAddr, String userName,
			String cargoType, String transportType, String cargoInfoEnd,
			String cargoInfoLoad, String cargoInfoLat,
			String cargoInfoUpdateTime, String cargoInfoDesc,
			String cargoInfoFlag, String cargoInfoPublished,
			String cargoInfoPicturl, String cargoInfoContactWay,
			String cargoInfoLng, String cargoInfoHeight, String cargoInfoPrice,
			String cargoInfoLenth, String cargoInfoVolume,
			String cargoInfoContacts, String cargoInfoWidth,
			String cargoInfoStart, String cargoInfoDeliTime) {
		this.userId = userId;
		this.cargoInfoId = cargoInfoId;
		this.cargoInfoLunit = cargoInfoLunit;
		this.cargoInfoVunit = cargoInfoVunit;
		this.cargoInfoRlen = cargoInfoRlen;
		this.cargoInfoELat = cargoInfoELat;
		this.cargoInfoELng = cargoInfoELng;
		this.cargoInfoEStreet = cargoInfoEStreet;
		this.cargoInfoSStreet = cargoInfoSStreet;
		this.userMobile = userMobile;
		this.userAddr = userAddr;
		this.userName = userName;
		this.cargoType = cargoType;
		this.cargoInfoEnd = cargoInfoEnd;
		this.cargoInfoLoad = cargoInfoLoad;
		this.transportType = transportType;
		this.cargoInfoLat = cargoInfoLat;
		this.cargoInfoUpdateTime = cargoInfoUpdateTime;
		this.cargoInfoDesc = cargoInfoDesc;
		this.cargoInfoFlag = cargoInfoFlag;
		this.cargoInfoPublished = cargoInfoPublished;
		this.cargoInfoPicturl = cargoInfoPicturl;

		this.cargoInfoContactWay = cargoInfoContactWay;
		this.cargoInfoLng = cargoInfoLng;
		this.cargoInfoHeight = cargoInfoHeight;
		this.cargoInfoPrice = cargoInfoPrice;

		this.cargoInfoLenth = cargoInfoLenth;
		this.cargoInfoVolume = cargoInfoVolume;

		this.cargoInfoContacts = cargoInfoContacts;
		this.cargoInfoWidth = cargoInfoWidth;
		this.cargoInfoStart = cargoInfoStart;
		this.cargoInfoDeliTime = cargoInfoDeliTime;

	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCargoInfoId() {
		return cargoInfoId;
	}

	public void setCargoInfoId(String cargoInfoId) {
		this.cargoInfoId = cargoInfoId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCargoType() {
		return cargoType;
	}

	public void setCargoType(String cargoType) {
		this.cargoType = cargoType;
	}

	public String getTransportType() {
		return transportType;
	}

	public void setTransportType(String transportType) {
		this.transportType = transportType;
	}

	public String getUserAddr() {
		return userAddr;
	}

	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getCargoInfoPublished() {
		return cargoInfoPublished;
	}

	public void setCargoInfoPublished(String cargoInfoPublished) {
		this.cargoInfoPublished = cargoInfoPublished;
	}

	public String getCargoInfoStart() {
		return cargoInfoStart;
	}

	public void setCargoInfoStart(String cargoInfoStart) {
		this.cargoInfoStart = cargoInfoStart;
	}

	public String getCargoInfoSStreet() {
		return cargoInfoSStreet;
	}

	public void setCargoInfoSStreet(String cargoInfoSStreet) {
		this.cargoInfoSStreet = cargoInfoSStreet;
	}

	public String getCargoInfoEnd() {
		return cargoInfoEnd;
	}

	public void setCargoInfoEnd(String cargoInfoEnd) {
		this.cargoInfoEnd = cargoInfoEnd;
	}

	public String getCargoInfoEStreet() {
		return cargoInfoEStreet;
	}

	public void setCargoInfoEStreet(String cargoInfoEStreet) {
		this.cargoInfoEStreet = cargoInfoEStreet;
	}

	public String getCargoInfoLng() {
		return cargoInfoLng;
	}

	public void setCargoInfoLng(String cargoInfoLng) {
		this.cargoInfoLng = cargoInfoLng;
	}

	public String getCargoInfoLat() {
		return cargoInfoLat;
	}

	public void setCargoInfoLat(String cargoInfoLat) {
		this.cargoInfoLat = cargoInfoLat;
	}

	public String getCargoInfoELng() {
		return cargoInfoELng;
	}

	public void setCargoInfoELng(String cargoInfoELng) {
		this.cargoInfoELng = cargoInfoELng;
	}

	public String getCargoInfoELat() {
		return cargoInfoELat;
	}

	public void setCargoInfoELat(String cargoInfoELat) {
		this.cargoInfoELat = cargoInfoELat;
	}

	public String getCargoInfoDeliTime() {
		return cargoInfoDeliTime;
	}

	public void setCargoInfoDeliTime(String cargoInfoDeliTime) {
		this.cargoInfoDeliTime = cargoInfoDeliTime;
	}

	public String getCargoInfoPrice() {
		return cargoInfoPrice;
	}

	public void setCargoInfoPrice(String cargoInfoPrice) {
		this.cargoInfoPrice = cargoInfoPrice;
	}

	public String getCargoInfoLenth() {
		return cargoInfoLenth;
	}

	public void setCargoInfoLenth(String cargoInfoLenth) {
		this.cargoInfoLenth = cargoInfoLenth;
	}

	public String getCargoInfoWidth() {
		return cargoInfoWidth;
	}

	public void setCargoInfoWidth(String cargoInfoWidth) {
		this.cargoInfoWidth = cargoInfoWidth;
	}

	public String getCargoInfoHeight() {
		return cargoInfoHeight;
	}

	public void setCargoInfoHeight(String cargoInfoHeight) {
		this.cargoInfoHeight = cargoInfoHeight;
	}

	public String getCargoInfoRlen() {
		return cargoInfoRlen;
	}

	public void setCargoInfoRlen(String cargoInfoRlen) {
		this.cargoInfoRlen = cargoInfoRlen;
	}

	public String getCargoInfoVunit() {
		return cargoInfoVunit;
	}

	public void setCargoInfoVunit(String cargoInfoVunit) {
		this.cargoInfoVunit = cargoInfoVunit;
	}

	public String getCargoInfoLunit() {
		return cargoInfoLunit;
	}

	public void setCargoInfoLunit(String cargoInfoLunit) {
		this.cargoInfoLunit = cargoInfoLunit;
	}

	public String getCargoInfoVolume() {
		return cargoInfoVolume;
	}

	public void setCargoInfoVolume(String cargoInfoVolume) {
		this.cargoInfoVolume = cargoInfoVolume;
	}

	public String getCargoInfoLoad() {
		return cargoInfoLoad;
	}

	public void setCargoInfoLoad(String cargoInfoLoad) {
		this.cargoInfoLoad = cargoInfoLoad;
	}

	public String getCargoInfoDesc() {
		return cargoInfoDesc;
	}

	public void setCargoInfoDesc(String cargoInfoDesc) {
		this.cargoInfoDesc = cargoInfoDesc;
	}

	public String getCargoInfoContacts() {
		return cargoInfoContacts;
	}

	public void setCargoInfoContacts(String cargoInfoContacts) {
		this.cargoInfoContacts = cargoInfoContacts;
	}

	public String getCargoInfoContactWay() {
		return cargoInfoContactWay;
	}

	public void setCargoInfoContactWay(String cargoInfoContactWay) {
		this.cargoInfoContactWay = cargoInfoContactWay;
	}

	public String getCargoInfoPicturl() {
		return cargoInfoPicturl;
	}

	public void setCargoInfoPicturl(String cargoInfoPicturl) {
		this.cargoInfoPicturl = cargoInfoPicturl;
	}

	public String getCargoInfoFlag() {
		return cargoInfoFlag;
	}

	public void setCargoInfoFlag(String cargoInfoFlag) {
		this.cargoInfoFlag = cargoInfoFlag;
	}

	public String getCargoInfoUpdateTime() {
		return cargoInfoUpdateTime;
	}

	public void setCargoInfoUpdateTime(String cargoInfoUpdateTime) {
		this.cargoInfoUpdateTime = cargoInfoUpdateTime;
	}

	public GoodsInfoItems() {
		super();
	}

}
