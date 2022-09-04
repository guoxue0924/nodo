package com.bluemobi.to.cas;

import com.appcore.model.AbstractObject;

public class SetNewPasswordTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

    // 用户Id
    private String mobile;
    // 原密码
    private String vcode;
    // 新密码
    private String newPassword;
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getVcode() {
		return vcode;
	}
	public void setVcode(String vcode) {
		this.vcode = vcode;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
    


}
