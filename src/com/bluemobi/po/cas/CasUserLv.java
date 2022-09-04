package com.bluemobi.po.cas;
import java.util.Date;

import com.appcore.model.AbstractObject;
/**
 * 【用户等级表】持久化对象
 * 数据库表：cas_user_lv
 * @author AutoCode 309444359@qq.com
 * @date 2015-09-11 16:25:18
 *
 */
public class CasUserLv extends AbstractObject {
	
	public static final long serialVersionUID = 1L;
	
	//
	private Integer userLvId; 
	//用户组别 ID
	private Short userGroupId; 
	//所属店铺 ID。关联表：store_content
	private Integer storeId; 
	//等级名称
	private String name; 
	//是否为会员默认等级。1：是；0：否；
	private Byte isDefault; 
	//是否启用。1：是；0：否；
	private Byte status; 
	//等级描述
	private String description; 
	//创建时间
	private Date ctime; 
	//最后一次更新时间
	private Date mtime; 

	/**获取  属性*/
	public Integer getUserLvId() {
		return userLvId;
	}
	
	/** 设置  属性*/
	public void setUserLvId(Integer userLvId) {
		this.userLvId = userLvId;
	}
	/**获取 用户组别 ID 属性*/
	public Short getUserGroupId() {
		return userGroupId;
	}
	
	/** 设置 用户组别 ID 属性*/
	public void setUserGroupId(Short userGroupId) {
		this.userGroupId = userGroupId;
	}
	/**获取 所属店铺 ID。关联表：store_content 属性*/
	public Integer getStoreId() {
		return storeId;
	}
	
	/** 设置 所属店铺 ID。关联表：store_content 属性*/
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	/**获取 等级名称 属性*/
	public String getName() {
		return name;
	}
	
	/** 设置 等级名称 属性*/
	public void setName(String name) {
		this.name = name;
	}
	/**获取 是否为会员默认等级。1：是；0：否； 属性*/
	public Byte getIsDefault() {
		return isDefault;
	}
	
	/** 设置 是否为会员默认等级。1：是；0：否； 属性*/
	public void setIsDefault(Byte isDefault) {
		this.isDefault = isDefault;
	}
	/**获取 是否启用。1：是；0：否； 属性*/
	public Byte getStatus() {
		return status;
	}
	
	/** 设置 是否启用。1：是；0：否； 属性*/
	public void setStatus(Byte status) {
		this.status = status;
	}
	/**获取 等级描述 属性*/
	public String getDescription() {
		return description;
	}
	
	/** 设置 等级描述 属性*/
	public void setDescription(String description) {
		this.description = description;
	}
	/**获取 创建时间 属性*/
	public Date getCtime() {
		return ctime;
	}
	
	/** 设置 创建时间 属性*/
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	/**获取 最后一次更新时间 属性*/
	public Date getMtime() {
		return mtime;
	}
	
	/** 设置 最后一次更新时间 属性*/
	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}

	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("CasUserLv");
        sb.append("{userLvId=").append(userLvId);
        sb.append(", userGroupId=").append(userGroupId);
        sb.append(", storeId=").append(storeId);
        sb.append(", name=").append(name);
        sb.append(", isDefault=").append(isDefault);
        sb.append(", status=").append(status);
        sb.append(", description=").append(description);
        sb.append(", ctime=").append(ctime);
        sb.append(", mtime=").append(mtime);
		sb.append('}');
        return sb.toString();
    }
	
	public boolean equals(Object obj){
		if (this == obj) {
		    return true;
		}
		if (obj instanceof CasUserLv) {
			CasUserLv casUserLv = (CasUserLv)obj;
			if(this.getUserLvId().equals(casUserLv.getUserLvId())){
				return true;
			}
		}
		return false;
	}
	
    public int hashCode() {
    	String pkStr = "" + this.getUserLvId();
        return pkStr.hashCode();
    }
	
}