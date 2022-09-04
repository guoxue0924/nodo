package com.bluemobi.po.page;


import com.appcore.model.AbstractObject;

/**
 * 【】持久化对象 数据库表：page_config
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-02-24 10:07:21
 * 
 */
public class PageConfig extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 
    private Integer id;
    // 
    private String name;
    // 
    private String title;
    // 
    private String value;
    // 
    private String memo;

    /** 获取  属性 */
    public Integer getId() {
        return id;
    }

    /** 设置  属性 */
    public void setId(Integer id) {
        this.id = id;
    }

    /** 获取  属性 */
    public String getName() {
        return name;
    }

    /** 设置  属性 */
    public void setName(String name) {
        this.name = name;
    }

    /** 获取  属性 */
    public String getTitle() {
        return title;
    }

    /** 设置  属性 */
    public void setTitle(String title) {
        this.title = title;
    }

    /** 获取  属性 */
    public String getValue() {
        return value;
    }

    /** 设置  属性 */
    public void setValue(String value) {
        this.value = value;
    }

    /** 获取  属性 */
    public String getMemo() {
        return memo;
    }

    /** 设置  属性 */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("PageConfig");
        sb.append("{id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", title=").append(title);
        sb.append(", value=").append(value);
        sb.append(", memo=").append(memo);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PageConfig) {
            PageConfig pageConfig = (PageConfig) obj;
            if (this.getId().equals(pageConfig.getId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getId();
        return pkStr.hashCode();
    }

}