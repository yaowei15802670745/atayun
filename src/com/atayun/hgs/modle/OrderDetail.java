package com.atayun.hgs.modle;

public class OrderDetail {
	private String orddUpdateTime;//
	private String orddprice;//
	private String cargoInfoId;//
	private String cargoInfoHeight;//
	private String cargoTypeId;//
	private String cargoInfoLenth;//
	private String cargoInfoVolume;//
	private String carLpNum;//
	private String cargoInfoLunit;//
	private String orderId;//
	private String cargoInfoEnd;//
	private String cargoInfoLoad;//
	private String cargoInfoEStreet;// 终点详细地址
	private String cargoInfoDesc;// 货物描述
	private int cargoInfoFlag;// 货物的状态（0 未被运输 ； 1 已运输 已运输货物不在编辑）
	private Integer cariId;// 车辆的唯一标识
	private float orddHSUBSPRICE;// 货主此货物的保证金
	private String cargoInfoPicturl;// 货物图片
	private float cargoInfoRlen;// 需要的车长
	private String cargoInfoContactWay;// 收货联系方式
	private Integer orddFlag;// 订单的状态标识
	private String orderNo;// 订单号
	private float cargoInfoPrice;// 运价
	private float orddCSUBSPRICE;// 车主此货物的保证金
	private String cargoInfoSStreet;// 起点详细地址
	private String cargoInfoVunit;// 车辆体积单位
	private float cargoInfoWidth;// 货宽
	private String cargoInfoContacts;// 收货联系人
	private float orderPrice;// 订单总价格
	private String cargoInfoStart;// 起点
	private Integer orddId;// 订单详情的唯一标识 ID
	private java.util.Date cargoInfoDeliTime;// 发货时间

	public OrderDetail(String orddUpdateTime, String orddprice,
			String cargoInfoId, String cargoInfoHeight, String cargoTypeId,
			String cargoInfoLenth, String cargoInfoVolume, String carLpNum,
			String cargoInfoLunit, String orderId, String cargoInfoEnd,
			String cargoInfoLoad, String cargoInfoEStreet,
			String cargoInfoDesc, int cargoInfoFlag,Integer cariId,
			float orddHSUBSPRICE, String cargoInfoPicturl, float cargoInfoRlen,
			String cargoInfoContactWay, Integer orddFlag, String orderNo,
			float cargoInfoPrice, float orddCSUBSPRICE,
			String cargoInfoSStreet, String cargoInfoVunit,
			float cargoInfoWidth, String cargoInfoContacts, float orderPrice,
			String cargoInfoStart, Integer orddId,
			java.util.Date cargoInfoDeliTime) {
		
		this.orddUpdateTime=orddUpdateTime;
		this.orddprice=orddprice;//
		this.cargoInfoId=cargoInfoId;//
		this.cargoInfoHeight=cargoInfoHeight;//
		this.cargoTypeId=cargoTypeId;//
		this.cargoInfoLenth=cargoInfoLenth;//
		this.cargoInfoVolume=cargoInfoVolume;//
		this.carLpNum=carLpNum;//
		this.cargoInfoLunit=cargoInfoLunit;//
		this.orderId=orderId;//
		this.cargoInfoEnd=cargoInfoEnd;//
		this.cargoInfoLoad=cargoInfoLoad;//
		this.cargoInfoEStreet=cargoInfoEStreet;// 终点详细地址
		this.cargoInfoDesc=cargoInfoDesc;// 货物描述
		this.cargoInfoFlag=cargoInfoFlag;// 货物的状态（0 未被运输 ； 1 已运输 已运输货物不在编辑）
		this.cariId=cariId;// 车辆的唯一标识
		this.orddHSUBSPRICE=orddHSUBSPRICE;// 货主此货物的保证金
		this.cargoInfoPicturl=cargoInfoPicturl;// 货物图片
		this.cargoInfoRlen=cargoInfoRlen;// 需要的车长
		this.cargoInfoContactWay=cargoInfoContactWay;// 收货联系方式
		this.orddFlag=orddFlag;// 订单的状态标识
		this.orderNo=orderNo;// 订单号
		this.cargoInfoPrice=cargoInfoPrice;// 运价
		this.orddCSUBSPRICE=orddCSUBSPRICE;// 车主此货物的保证金
		this.cargoInfoSStreet=cargoInfoSStreet;// 起点详细地址
		this.cargoInfoVunit=cargoInfoVunit;// 车辆体积单位
		this.cargoInfoWidth=cargoInfoWidth;// 货宽
		this.cargoInfoContacts=cargoInfoContacts;// 收货联系人
		this.orderPrice=orderPrice;// 订单总价格
		this.cargoInfoStart=cargoInfoStart;// 起点
		this.orddId=orddId;// 订单详情的唯一标识 ID
		this.cargoInfoDeliTime=cargoInfoDeliTime;// 发货时间

	}

	public String getOrddUpdateTime() {
		return orddUpdateTime;
	}

	public void setOrddUpdateTime(String orddUpdateTime) {
		this.orddUpdateTime = orddUpdateTime;
	}

	public String getOrddprice() {
		return orddprice;
	}

	public void setOrddprice(String orddprice) {
		this.orddprice = orddprice;
	}

	public String getCargoInfoId() {
		return cargoInfoId;
	}

	public void setCargoInfoId(String cargoInfoId) {
		this.cargoInfoId = cargoInfoId;
	}

	public String getCargoInfoHeight() {
		return cargoInfoHeight;
	}

	public void setCargoInfoHeight(String cargoInfoHeight) {
		this.cargoInfoHeight = cargoInfoHeight;
	}

	public String getCargoTypeId() {
		return cargoTypeId;
	}

	public void setCargoTypeId(String cargoTypeId) {
		this.cargoTypeId = cargoTypeId;
	}

	public String getCargoInfoLenth() {
		return cargoInfoLenth;
	}

	public void setCargoInfoLenth(String cargoInfoLenth) {
		this.cargoInfoLenth = cargoInfoLenth;
	}

	public String getCargoInfoVolume() {
		return cargoInfoVolume;
	}

	public void setCargoInfoVolume(String cargoInfoVolume) {
		this.cargoInfoVolume = cargoInfoVolume;
	}

	public String getCarLpNum() {
		return carLpNum;
	}

	public void setCarLpNum(String carLpNum) {
		this.carLpNum = carLpNum;
	}

	public String getCargoInfoLunit() {
		return cargoInfoLunit;
	}

	public void setCargoInfoLunit(String cargoInfoLunit) {
		this.cargoInfoLunit = cargoInfoLunit;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCargoInfoEnd() {
		return cargoInfoEnd;
	}

	public void setCargoInfoEnd(String cargoInfoEnd) {
		this.cargoInfoEnd = cargoInfoEnd;
	}

	public String getCargoInfoLoad() {
		return cargoInfoLoad;
	}

	public void setCargoInfoLoad(String cargoInfoLoad) {
		this.cargoInfoLoad = cargoInfoLoad;
	}

	public String getCargoInfoEStreet() {
		return cargoInfoEStreet;
	}

	public void setCargoInfoEStreet(String cargoInfoEStreet) {
		this.cargoInfoEStreet = cargoInfoEStreet;
	}

	public String getCargoInfoDesc() {
		return cargoInfoDesc;
	}

	public void setCargoInfoDesc(String cargoInfoDesc) {
		this.cargoInfoDesc = cargoInfoDesc;
	}

	public int getCargoInfoFlag() {
		return cargoInfoFlag;
	}

	public void setCargoInfoFlag(int cargoInfoFlag) {
		this.cargoInfoFlag = cargoInfoFlag;
	}

	public Integer getCariId() {
		return cariId;
	}

	public void setCariId(Integer cariId) {
		this.cariId = cariId;
	}

	public float getOrddHSUBSPRICE() {
		return orddHSUBSPRICE;
	}

	public void setOrddHSUBSPRICE(float orddHSUBSPRICE) {
		this.orddHSUBSPRICE = orddHSUBSPRICE;
	}

	public String getCargoInfoPicturl() {
		return cargoInfoPicturl;
	}

	public void setCargoInfoPicturl(String cargoInfoPicturl) {
		this.cargoInfoPicturl = cargoInfoPicturl;
	}

	public float getCargoInfoRlen() {
		return cargoInfoRlen;
	}

	public void setCargoInfoRlen(float cargoInfoRlen) {
		this.cargoInfoRlen = cargoInfoRlen;
	}

	public String getCargoInfoContactWay() {
		return cargoInfoContactWay;
	}

	public void setCargoInfoContactWay(String cargoInfoContactWay) {
		this.cargoInfoContactWay = cargoInfoContactWay;
	}

	public Integer getOrddFlag() {
		return orddFlag;
	}

	public void setOrddFlag(Integer orddFlag) {
		this.orddFlag = orddFlag;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public float getCargoInfoPrice() {
		return cargoInfoPrice;
	}

	public void setCargoInfoPrice(float cargoInfoPrice) {
		this.cargoInfoPrice = cargoInfoPrice;
	}

	public float getOrddCSUBSPRICE() {
		return orddCSUBSPRICE;
	}

	public void setOrddCSUBSPRICE(float orddCSUBSPRICE) {
		this.orddCSUBSPRICE = orddCSUBSPRICE;
	}

	public String getCargoInfoSStreet() {
		return cargoInfoSStreet;
	}

	public void setCargoInfoSStreet(String cargoInfoSStreet) {
		this.cargoInfoSStreet = cargoInfoSStreet;
	}

	public String getCargoInfoVunit() {
		return cargoInfoVunit;
	}

	public void setCargoInfoVunit(String cargoInfoVunit) {
		this.cargoInfoVunit = cargoInfoVunit;
	}

	public float getCargoInfoWidth() {
		return cargoInfoWidth;
	}

	public void setCargoInfoWidth(float cargoInfoWidth) {
		this.cargoInfoWidth = cargoInfoWidth;
	}

	public String getCargoInfoContacts() {
		return cargoInfoContacts;
	}

	public void setCargoInfoContacts(String cargoInfoContacts) {
		this.cargoInfoContacts = cargoInfoContacts;
	}

	public float getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(float orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getCargoInfoStart() {
		return cargoInfoStart;
	}

	public void setCargoInfoStart(String cargoInfoStart) {
		this.cargoInfoStart = cargoInfoStart;
	}

	public Integer getOrddId() {
		return orddId;
	}

	public void setOrddId(Integer orddId) {
		this.orddId = orddId;
	}

	public java.util.Date getCargoInfoDeliTime() {
		return cargoInfoDeliTime;
	}

	public void setCargoInfoDeliTime(java.util.Date cargoInfoDeliTime) {
		this.cargoInfoDeliTime = cargoInfoDeliTime;
	}
	public OrderDetail(){
		super();
	}
}
