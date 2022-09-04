package com.bluemobi.po.cas;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【用户收藏表】持久化对象 数据库表：cas_user_favorite
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-01-21 16:56:24
 * 
 */
public class CasUserFavorite extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 
    private Integer favoriteId;
    // 收藏类型。1,goods：商品；2,article：文章；3,link：链接；4,other : 其他 ;
    private Integer type;
    // 收藏内容，link类型填入链接地址，其他填入 ID
    private String content;
    // 用户 ID
    private Integer userid;
    // 收藏类型。goods：商品；article：文章；link：链接；other : 其他
    private Integer specificationid;
    // 创建时间
    private Date ctime;

    /** 获取  属性 */
    public Integer getFavoriteId() {
        return favoriteId;
    }

    /** 设置  属性 */
    public void setFavoriteId(Integer favoriteId) {
        this.favoriteId = favoriteId;
    }

    /** 获取 收藏类型。goods：商品；article：文章；link：链接；other : 其他 ; 属性 */
    public Integer getType() {
        return type;
    }

    /** 设置 收藏类型。goods：商品；article：文章；link：链接；other : 其他 ; 属性 */
    public void setType(Integer type) {
        this.type = type;
    }

    /** 获取 收藏内容，link类型填入链接地址，其他填入 ID 属性 */
    public String getContent() {
        return content;
    }

    /** 设置 收藏内容，link类型填入链接地址，其他填入 ID 属性 */
    public void setContent(String content) {
        this.content = content;
    }

    /** 获取 用户 ID 属性 */
    public Integer getUserid() {
        return userid;
    }

    /** 设置 用户 ID 属性 */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /** 获取 收藏类型。goods：商品；article：文章；link：链接；other : 其他 属性 */
    public Integer getSpecificationid() {
        return specificationid;
    }

    /** 设置 收藏类型。goods：商品；article：文章；link：链接；other : 其他 属性 */
    public void setSpecificationid(Integer specificationid) {
        this.specificationid = specificationid;
    }

    /** 获取 创建时间 属性 */
    public Date getCtime() {
        return ctime;
    }

    /** 设置 创建时间 属性 */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("CasUserFavorite");
        sb.append("{favoriteId=").append(favoriteId);
        sb.append(", type=").append(type);
        sb.append(", content=").append(content);
        sb.append(", userid=").append(userid);
        sb.append(", specificationid=").append(specificationid);
        sb.append(", ctime=").append(ctime);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CasUserFavorite) {
            CasUserFavorite casUserFavorite = (CasUserFavorite) obj;
            if (this.getFavoriteId().equals(casUserFavorite.getFavoriteId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getFavoriteId();
        return pkStr.hashCode();
    }

}