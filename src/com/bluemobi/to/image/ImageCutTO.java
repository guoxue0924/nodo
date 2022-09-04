package com.bluemobi.to.image;

import com.appcore.model.AbstractObject;

public class ImageCutTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

    // 截取起点x坐标
    private Integer x1;
    // 截取起点y坐标
    private Integer y1;
    // 截取终点x坐标
    private Integer x2;
    // 截取终点y坐标
    private Integer y2;
    // 截取图片宽度
    private Integer width;
    // 截取图片高度
    private Integer height;
    // 图片路径
    private String imageUrl;
    // 图片在前端放大或缩小的倍数
    private Double multiple;
    // 图片保存路径
    private String saveUrl;

    public Double getMultiple() {
        return multiple;
    }

    public void setMultiple(Double multiple) {
        this.multiple = multiple;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getX1() {
        return x1;
    }

    public void setX1(Integer x1) {
        this.x1 = x1;
    }

    public Integer getY1() {
        return y1;
    }

    public void setY1(Integer y1) {
        this.y1 = y1;
    }

    public Integer getX2() {
        return x2;
    }

    public void setX2(Integer x2) {
        this.x2 = x2;
    }

    public Integer getY2() {
        return y2;
    }

    public void setY2(Integer y2) {
        this.y2 = y2;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getSaveUrl() {
        return saveUrl;
    }

    public void setSaveUrl(String saveUrl) {
        this.saveUrl = saveUrl;
    }

}
