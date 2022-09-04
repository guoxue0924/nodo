package com.bluemobi.po.trend;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【attachment user mapping】持久化对象 数据库表：trend_attachment
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-14 10:33:23
 * 
 */
public class TrendAttachment extends AbstractObject {

    public static final long serialVersionUID = 1L;

    //
    private Long attachmentid;
    //
    private Integer userid;
    // filename of attached file
    private String title;
    // comment to attached file
    private String description;
    // split with comma
    private String label;
    //
    private String mediatype;
    // MIME type
    private String mimetype;
    // 文件后缀
    private String suffix;
    //
    private Byte imageable;
    //
    private Integer imageWidth;
    //
    private Integer imageHeight;
    //
    private Date ctime;
    //
    private Date mtime;
    // file path at storage system
    private String filepath;
    // byte
    private Integer filesize;
    // md5file code
    private String hashcode;
    // negative 1 is removed
    private Byte status;
    // revise version number, current version
    private Integer rev;

    /** 获取 属性 */
    public Long getAttachmentid() {
        return attachmentid;
    }

    /** 设置 属性 */
    public void setAttachmentid(Long attachmentid) {
        this.attachmentid = attachmentid;
    }

    /** 获取 属性 */
    public Integer getUserid() {
        return userid;
    }

    /** 设置 属性 */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /** 获取 filename of attached file 属性 */
    public String getTitle() {
        return title;
    }

    /** 设置 filename of attached file 属性 */
    public void setTitle(String title) {
        this.title = title;
    }

    /** 获取 comment to attached file 属性 */
    public String getDescription() {
        return description;
    }

    /** 设置 comment to attached file 属性 */
    public void setDescription(String description) {
        this.description = description;
    }

    /** 获取 split with comma 属性 */
    public String getLabel() {
        return label;
    }

    /** 设置 split with comma 属性 */
    public void setLabel(String label) {
        this.label = label;
    }

    /** 获取 属性 */
    public String getMediatype() {
        return mediatype;
    }

    /** 设置 属性 */
    public void setMediatype(String mediatype) {
        this.mediatype = mediatype;
    }

    /** 获取 MIME type 属性 */
    public String getMimetype() {
        return mimetype;
    }

    /** 设置 MIME type 属性 */
    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    /** 获取 文件后缀 属性 */
    public String getSuffix() {
        return suffix;
    }

    /** 设置 文件后缀 属性 */
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    /** 获取 属性 */
    public Byte getImageable() {
        return imageable;
    }

    /** 设置 属性 */
    public void setImageable(Byte imageable) {
        this.imageable = imageable;
    }

    /** 获取 属性 */
    public Integer getImageWidth() {
        return imageWidth;
    }

    /** 设置 属性 */
    public void setImageWidth(Integer imageWidth) {
        this.imageWidth = imageWidth;
    }

    /** 获取 属性 */
    public Integer getImageHeight() {
        return imageHeight;
    }

    /** 设置 属性 */
    public void setImageHeight(Integer imageHeight) {
        this.imageHeight = imageHeight;
    }

    /** 获取 属性 */
    public Date getCtime() {
        return ctime;
    }

    /** 设置 属性 */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /** 获取 属性 */
    public Date getMtime() {
        return mtime;
    }

    /** 设置 属性 */
    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    /** 获取 file path at storage system 属性 */
    public String getFilepath() {
        return filepath;
    }

    /** 设置 file path at storage system 属性 */
    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    /** 获取 byte 属性 */
    public Integer getFilesize() {
        return filesize;
    }

    /** 设置 byte 属性 */
    public void setFilesize(Integer filesize) {
        this.filesize = filesize;
    }

    /** 获取 md5file code 属性 */
    public String getHashcode() {
        return hashcode;
    }

    /** 设置 md5file code 属性 */
    public void setHashcode(String hashcode) {
        this.hashcode = hashcode;
    }

    /** 获取 negative 1 is removed 属性 */
    public Byte getStatus() {
        return status;
    }

    /** 设置 negative 1 is removed 属性 */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /** 获取 revise version number, current version 属性 */
    public Integer getRev() {
        return rev;
    }

    /** 设置 revise version number, current version 属性 */
    public void setRev(Integer rev) {
        this.rev = rev;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("TrendAttachment");
        sb.append("{attachmentid=").append(attachmentid);
        sb.append(", userid=").append(userid);
        sb.append(", title=").append(title);
        sb.append(", description=").append(description);
        sb.append(", label=").append(label);
        sb.append(", mediatype=").append(mediatype);
        sb.append(", mimetype=").append(mimetype);
        sb.append(", suffix=").append(suffix);
        sb.append(", imageable=").append(imageable);
        sb.append(", imageWidth=").append(imageWidth);
        sb.append(", imageHeight=").append(imageHeight);
        sb.append(", ctime=").append(ctime);
        sb.append(", mtime=").append(mtime);
        sb.append(", filepath=").append(filepath);
        sb.append(", filesize=").append(filesize);
        sb.append(", hashcode=").append(hashcode);
        sb.append(", status=").append(status);
        sb.append(", rev=").append(rev);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof TrendAttachment) {
            TrendAttachment trendAttachment = (TrendAttachment) obj;
            if (this.getAttachmentid().equals(trendAttachment.getAttachmentid())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getAttachmentid();
        return pkStr.hashCode();
    }

}