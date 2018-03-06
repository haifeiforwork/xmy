package com.zfj.xmy.user.persistence.pojo.dto.app;

/**
 * 用户注册/恢复密码 接受参数DTO
 * @author Administrator
 *
 */
public class UserRegisterInDto {

	/**
	 * 手机号
	 */
	private String mobilePhone;
	/**
	 * 密码 / 重设的新密码
	 */
    private String password;
    /**
     * 短信验证码
     */
    private String code;
   
    /**
     * 图形验证码
     */
    private String imgCode;
    
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getImgCode() {
		return imgCode;
	}
	public void setImgCode(String imgCode) {
		this.imgCode = imgCode;
	}
    
    
}
