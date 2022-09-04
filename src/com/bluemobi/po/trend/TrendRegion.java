package com.bluemobi.po.trend;

import java.util.LinkedHashMap;
import java.util.Map;

import com.appcore.model.AbstractObject;
/**
 * 【】持久化对象
 * 数据库表：trend_region
 * @author AutoCode 309444359@qq.com
 * @date 2015-07-17 11:29:54
 *
 */
public class TrendRegion extends AbstractObject {
    
    public static final long serialVersionUID = 1L;
    
    //
    private Integer regionId; 
    //
    private String pack; 
    //
    private Integer pid; 
    //
    private String hid; 
    //
    private Integer grade; 
    //
    private String regionName; 
    //
    private String enName; 
    //
    private String p1; 
    //
    private String p2; 
    //
    private Integer sortOrder; 
    //是否启用。1：是；0：否；
    private Byte status; 
    //快递费
    //add by guoxue 2016/06/22 begin  
    private Integer expressFee;
    //add by guoxue 2016/06/22 end  以下相关全部修改
    /**
     * 当前地区的子集地图map
     * @author haojian
     * @date 2015-12-16 下午12:16:00 
     */
    private Map<Integer, TrendRegion> subMap = new LinkedHashMap<Integer, TrendRegion>();

    /**获取  属性*/
    public Integer getRegionId() {
        return regionId;
    }
    
    /** 设置  属性*/
    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }
    /**获取  属性*/
    public String getPack() {
        return pack;
    }
    
    /** 设置  属性*/
    public void setPack(String pack) {
        this.pack = pack;
    }
    /**获取  属性*/
    public Integer getPid() {
        return pid;
    }
    
    /** 设置  属性*/
    public void setPid(Integer pid) {
        this.pid = pid;
    }
    /**获取  属性*/
    public String getHid() {
        return hid;
    }
    
    /** 设置  属性*/
    public void setHid(String hid) {
        this.hid = hid;
    }
    /**获取  属性*/
    public Integer getGrade() {
        return grade;
    }
    
    /** 设置  属性*/
    public void setGrade(Integer grade) {
        this.grade = grade;
    }
    /**获取  属性*/
    public String getRegionName() {
        return regionName;
    }
    
    /** 设置  属性*/
    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
    /**获取  属性*/
    public String getEnName() {
        return enName;
    }
    
    /** 设置  属性*/
    public void setEnName(String enName) {
        this.enName = enName;
    }
    /**获取  属性*/
    public String getP1() {
        return p1;
    }
    
    /** 设置  属性*/
    public void setP1(String p1) {
        this.p1 = p1;
    }
    /**获取  属性*/
    public String getP2() {
        return p2;
    }
    
    /** 设置  属性*/
    public void setP2(String p2) {
        this.p2 = p2;
    }
    /**获取  属性*/
    public Integer getSortOrder() {
        return sortOrder;
    }
    
    /** 设置  属性*/
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }
    /**获取 是否启用。1：是；0：否； 属性*/
    public Byte getStatus() {
        return status;
    }
    
    /** 设置 是否启用。1：是；0：否； 属性*/
    public void setStatus(Byte status) {
        this.status = status;
    }
    public Map<Integer, TrendRegion> getSubMap() {
        return subMap;
    }
    public void setSubMap(Map<Integer, TrendRegion> subMap) {
        this.subMap = subMap;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("TrendRegion");
        sb.append("{regionId=").append(regionId);
        sb.append(", pack=").append(pack);
        sb.append(", pid=").append(pid);
        sb.append(", hid=").append(hid);
        sb.append(", grade=").append(grade);
        sb.append(", regionName=").append(regionName);
        sb.append(", enName=").append(enName);
        sb.append(", p1=").append(p1);
        sb.append(", p2=").append(p2);
        sb.append(", sortOrder=").append(sortOrder);
        sb.append(", status=").append(status);
        sb.append(", expressFee=").append(expressFee);
        sb.append('}');
        return sb.toString();
    }
    
    public boolean equals(Object obj){
        if (this == obj) {
            return true;
        }
        if (obj instanceof TrendRegion) {
            TrendRegion trendRegion = (TrendRegion)obj;
            if(this.getRegionId().equals(trendRegion.getRegionId())){
                return true;
            }
        }
        return false;
    }
    
    public int hashCode() {
        String pkStr = "" + this.getRegionId();
        return pkStr.hashCode();
    }

	public Integer getExpressFee() {
		return expressFee;
	}

	public void setExpressFee(Integer expressFee) {
		this.expressFee = expressFee;
	}
    
}