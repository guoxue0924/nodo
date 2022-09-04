package com.bluemobi.po.trend;

import java.math.BigDecimal;
import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【版本管理】持久化对象 数据库表：trend_version
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-12-02 17:27:20
 * 
 */
public class TrendVersion extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 
    private Integer id;
    // 平台类型
    private String platform;
    // 版本号，比如：1、2、3...
    private Short vCode;
    // 版本名称，比如：1.21、3.42
    private BigDecimal vName;
    // 版本详情
    private String content;
    // 文件路径，即版本下载地址
    private String filepath;
    // 安装包大小
    private String size;
    // 二维码图片
    private String filepathTdc;
    // 发布状态。1：已发布；0：未发布；
    private Byte status;
    // 创建时间
    private Date ctime;
    // 最后一次更新时间
    private Date mtime;

    /** 获取  属性 */
    public Integer getId() {
        return id;
    }

    /** 设置  属性 */
    public void setId(Integer id) {
        this.id = id;
    }

    /** 获取 平台类型 属性 */
    public String getPlatform() {
        return platform;
    }

    /** 设置 平台类型 属性 */
    public void setPlatform(String platform) {
        this.platform = platform;
    }

    /** 获取 版本号，比如：1、2、3... 属性 */
    public Short getVCode() {
        return vCode;
    }

    /** 设置 版本号，比如：1、2、3... 属性 */
    public void setVCode(Short vCode) {
        this.vCode = vCode;
    }

    /** 获取 版本名称，比如：1.21、3.42 属性 */
    public BigDecimal getVName() {
        return vName;
    }

    /** 设置 版本名称，比如：1.21、3.42 属性 */
    public void setVName(BigDecimal vName) {
        this.vName = vName;
    }

    /** 获取 版本详情 属性 */
    public String getContent() {
        return content;
    }

    /** 设置 版本详情 属性 */
    public void setContent(String content) {
        this.content = content;
    }

    /** 获取 文件路径，即版本下载地址 属性 */
    public String getFilepath() {
        return filepath;
    }

    /** 设置 文件路径，即版本下载地址 属性 */
    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    /** 获取 安装包大小 属性 */
    public String getSize() {
        return size;
    }

    /** 设置 安装包大小 属性 */
    public void setSize(String size) {
        this.size = size;
    }

    /** 获取 二维码图片 属性 */
    public String getFilepathTdc() {
        return filepathTdc;
    }

    /** 设置 二维码图片 属性 */
    public void setFilepathTdc(String filepathTdc) {
        this.filepathTdc = filepathTdc;
    }

    /** 获取 发布状态。1：已发布；0：未发布； 属性 */
    public Byte getStatus() {
        return status;
    }

    /** 设置 发布状态。1：已发布； ：未发布； 属性 */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /** 获取 创建时间 属性 */
    public Date getCtime() {
        return ctime;
    }

    /** 设置 创建时间 属性 */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /** 获取 最后一次更新时间 属性 */
    public Date getMtime() {
        return mtime;
    }

    /** 设置 最后一次更新时间 属性 */
    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("TrendVersion");
        sb.append("{id=").append(id);
        sb.append(", platform=").append(platform);
        sb.append(", vCode=").append(vCode);
        sb.append(", vName=").append(vName);
        sb.append(", content=").append(content);
        sb.append(", filepath=").append(filepath);
        sb.append(", size=").append(size);
        sb.append(", filepathTdc=").append(filepathTdc);
        sb.append(", status=").append(status);
        sb.append(", ctime=").append(ctime);
        sb.append(", mtime=").append(mtime);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof TrendVersion) {
            TrendVersion trendVersion = (TrendVersion) obj;
            if (this.getId().equals(trendVersion.getId())) {
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