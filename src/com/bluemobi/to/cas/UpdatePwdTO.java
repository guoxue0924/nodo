package com.bluemobi.to.cas;

import com.appcore.model.AbstractObject;

public class UpdatePwdTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

    // 用户Id
    private int userId;
    // 原密码
    private String oldPassword;
    // 新密码
    private String password;
    // 重复新密码
    private String repassword;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

}
