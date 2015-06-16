package com.atayun.hgs.modle;
/*
 * 用来保存flagID，userId
 */
import android.app.Application;

public class IDApplication extends Application {
	private String flagID;//0表示用户身份是车主，1表示货主
	private String userId;
	private String gcomp;
	
	
	
	
	private String cargoFlag;/*货主个人信心返回说明bbb
	1   ：已完善
	0   ：未完善
	2   ：已认证
	3   ：正在审核
	-1   : 该用户不是货主（说明也没再个人中心中切换过身份），没有注册过货主的身份*/
	
	private String carUserFlag;/*车主个人信心返回说明
	1   ：已完善
	0   ：未完善
	2   ：已认证
	3   ：正在审核
	-1   : 该用户不是车主（说明也没再个人中心中切换过身份），没有注册过车主的身份文*/
	
	
	private String companyFlag;/*企业货主个人信息返回说明

	1   ：已完善
	0   ：未完善
	2   ：已认证
	3   ：正在审核
	-1   : 该用户不是企业车主，没有完善过企业货主的信息*/
	
	private String userBaseFlag;
	/*
	 * userBaseFlag 个人基本信息（车主货主共用）返回说明
1 ：已完善
0 ：未完善
2 ：已认证
3 ：正在审核
	 */
	
	
	private String userPhone;
	private String userPassword;
	private String username;
	private String codeSMS;
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCodeSMS() {
		return codeSMS;
	}
	public void setCodeSMS(String codeSMS) {
		this.codeSMS = codeSMS;
	}
	

	
	public String getFlagID() {
		return flagID;
	}
	public void setFlagID(String flagID) {
		this.flagID = flagID;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getGcomp() {
		return gcomp;
	}
	public void setGcomp(String gcomp) {
		this.gcomp = gcomp;
	}
	
	public String getCargoFlag() {
		return cargoFlag;
	}
	public void setCargoFlag(String cargoFlag) {
		this.cargoFlag = cargoFlag;
	}
	public String getCarUserFlag() {
		return carUserFlag;
	}
	public void setCarUserFlag(String carUserFlag) {
		this.carUserFlag = carUserFlag;
	}
	public String getCompanyFlag() {
		return companyFlag;
	}
	public void setCompanyFlag(String companyFlag) {
		this.companyFlag = companyFlag;
	}
	public String getUserBaseFlag() {
		return userBaseFlag;
	}
	public void setUserBaseFlag(String userBaseFlag) {
		this.userBaseFlag = userBaseFlag;
	}
	
  

}
