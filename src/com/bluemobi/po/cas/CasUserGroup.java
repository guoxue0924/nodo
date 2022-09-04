package com.bluemobi.po.cas;

import com.appcore.model.AbstractObject;
/**
 * 【】持久化对象
 * 数据库表：cas_user_group
 * @author AutoCode 309444359@qq.com
 * @date 2015-09-11 10:53:21
 *
 */
public class CasUserGroup extends AbstractObject {
	
	public static final long serialVersionUID = 1L;
	
	//
	private Short userGroupId; 
	//
	private String groupName; 

	/**获取  属性*/
	public Short getUserGroupId() {
		return userGroupId;
	}
	
	/** 设置  属性*/
	public void setUserGroupId(Short userGroupId) {
		this.userGroupId = userGroupId;
	}
	/**获取  属性*/
	public String getGroupName() {
		return groupName;
	}
	
	/** 设置  属性*/
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("CasUserGroup");
        sb.append("{userGroupId=").append(userGroupId);
        sb.append(", groupName=").append(groupName);
		sb.append('}');
        return sb.toString();
    }
	
	public boolean equals(Object obj){
		if (this == obj) {
		    return true;
		}
		if (obj instanceof CasUserGroup) {
			CasUserGroup casUserGroup = (CasUserGroup)obj;
			if(this.getUserGroupId().equals(casUserGroup.getUserGroupId())){
				return true;
			}
		}
		return false;
	}
	
    public int hashCode() {
    	String pkStr = "" + this.getUserGroupId();
        return pkStr.hashCode();
    }
	
}