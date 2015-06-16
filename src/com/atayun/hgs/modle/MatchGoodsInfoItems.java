package com.atayun.hgs.modle;

public class MatchGoodsInfoItems {

	private String cagoIdecardFlag;// 货主认证状态0 未认证1 已认证
	private String cagoCpName;// 货主所在公司名称或者货主名
	private String cagoCpPicFlag;// 公司营业执照认证状态0 未认证1 已认
	private String transportType;// 运输类型
	private String cargoType;// 货源类型
	private String cargoInfoId;// 货源的唯一ID
	private String cargoInfoPublished;// 货源信息发布时间
	private String cargoInfoStart;// 起点
	private String cargoInfoSStreet;// 起点详细地址
	private String cargoInfoEnd;// 终点
	private String cargoInfoEStreet;// 终点详细地址
	private String cargoInfoLng;// 起点货源经度
	private String cargoInfoLat;// 起点货源纬度
	private String cargoInfoELng;// 终点货源经度
	private String cargoInfoELat;// 终点货源纬度
	private String cargoInfoDeliTime;// 发货时间
	private String cargoInfoPrice;// 运价
	private String cargoInfoLenth;// 货长
	private String cargoInfoWidth;// 货宽
	private String cargoInfoHeight;// 货高
	private String cargoInfoRlen;// 需要的车长
	private String cargoInfoVunit;// 车辆体积单位
	private String cargoInfoLunit;// 车辆载重单位
	private String cargoInfoVolume;// 货物体积
	private String cargoInfoLoad;// 载重（单位：吨）
	private String cargoInfoDesc;// 货物描述
	private String cargoInfoContacts;// 收货联系人
	private String cargoInfoContactWay;// 收货联系方式
	private String cargoInfoPicturl;// 货物图片
	private String cargoInfoFlag;// 货物的状态（0 未被运输； 1 已运输已运输货
	private String cargoInfoUpdateTime;// 货物记录更新时间
	private String mareId;// 匹配ID
	private String mareNum;// 匹配ID
	public MatchGoodsInfoItems(
			String cagoIdecardFlag, String cagoCpName,
			String cagoCpPicFlag,String transportType, 
			String cargoType, String cargoInfoId,
			String cargoInfoPublished, String cargoInfoStart,
			String cargoInfoSStreet, String cargoInfoEnd,
			String cargoInfoEStreet, String cargoInfoLng,
			String cargoInfoLat,String cargoInfoELng, 
			String cargoInfoELat,String cargoInfoDeliTime,
			String cargoInfoPrice,String cargoInfoLenth, 
			String cargoInfoWidth,String cargoInfoHeight, 
			String cargoInfoRlen,String cargoInfoVunit, 
			String cargoInfoLunit,String cargoInfoVolume, 
			String cargoInfoLoad, String cargoInfoDesc,
			String cargoInfoContacts, String cargoInfoContactWay,
			String cargoInfoPicturl, String cargoInfoFlag,
			String cargoInfoUpdateTime, String mareId,
			String mareNum) {
		this.cagoIdecardFlag = cagoIdecardFlag;
		this.cagoCpName = cagoCpName;
		this.cagoCpPicFlag = cagoCpPicFlag;
		this.transportType = transportType;
		this.cargoType = cargoType;
		this.cargoInfoId = cargoInfoId;
		this.cargoInfoPublished = cargoInfoPublished;
		this.cargoInfoStart = cargoInfoStart;
		this.cargoInfoSStreet = cargoInfoSStreet;
		this.cargoInfoEnd = cargoInfoEnd;
		this.cargoInfoEStreet = cargoInfoEStreet;
		this.cargoInfoLng = cargoInfoLng;
		this.cargoInfoLat = cargoInfoLat;
		this.cargoInfoELng = cargoInfoELng;
		this.cargoInfoELat = cargoInfoELat;
		this.cargoInfoDeliTime = cargoInfoDeliTime;
		this.cargoInfoPrice = cargoInfoPrice;
		this.cargoInfoLenth = cargoInfoLenth;
		this.cargoInfoWidth = cargoInfoWidth;
		this.cargoInfoHeight = cargoInfoHeight;
		this.cargoInfoRlen = cargoInfoRlen;
		this.cargoInfoVunit = cargoInfoVunit;
		this.cargoInfoLunit = cargoInfoLunit;
		this.cargoInfoVolume = cargoInfoVolume;
		this.cargoInfoLoad = cargoInfoLoad;
		this.cargoInfoDesc = cargoInfoDesc;
		this.cargoInfoContacts = cargoInfoContacts;
		this.cargoInfoContactWay = cargoInfoContactWay;
		this.cargoInfoPicturl = cargoInfoPicturl;
		this.cargoInfoFlag = cargoInfoFlag;
		this.cargoInfoUpdateTime = cargoInfoUpdateTime;
		this.mareId = mareId;
		this.mareNum = mareNum;

	}

	public String getCagoIdecardFlag() {
		return cagoIdecardFlag;
	}

	public void setCagoIdecardFlag(String cagoIdecardFlag) {
		this.cagoIdecardFlag = cagoIdecardFlag;
	}

	public String getCagoCpName() {
		return cagoCpName;
	}

	public void setCagoCpName(String cagoCpName) {
		this.cagoCpName = cagoCpName;
	}

	public String getCagoCpPicFlag() {
		return cagoCpPicFlag;
	}

	public void setCagoCpPicFlag(String cagoCpPicFlag) {
		this.cagoCpPicFlag = cagoCpPicFlag;
	}

	public String getTransportType() {
		return transportType;
	}

	public void setTransportType(String transportType) {
		this.transportType = transportType;
	}

	public String getCargoType() {
		return cargoType;
	}

	public void setCargoType(String cargoType) {
		this.cargoType = cargoType;
	}

	public String getCargoInfoId() {
		return cargoInfoId;
	}

	public void setCargoInfoId(String cargoInfoId) {
		this.cargoInfoId = cargoInfoId;
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

	public String getMareId() {
		return mareId;
	}

	public void setMareId(String mareId) {
		this.mareId = mareId;
	}

	public String getMareNum() {
		return mareNum;
	}

	public void setMareNum(String mareNum) {
		this.mareNum = mareNum;
	}

	public MatchGoodsInfoItems(){
		super();
	}
}
