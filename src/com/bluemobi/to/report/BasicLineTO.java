package com.bluemobi.to.report;


public class BasicLineTO {

    private String title; //标题
    private String subtitle; //副标题
    private String yAxisTitle; //Y轴名称
    private String categories; //数据分类
    private String name; // 数据列表
    private Object data; //数据集合体
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getSubtitle() {
        return subtitle;
    }
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
    public String getyAxisTitle() {
        return yAxisTitle;
    }
    public void setyAxisTitle(String yAxisTitle) {
        this.yAxisTitle = yAxisTitle;
    }
    public String getCategories() {
        return categories;
    }
    public void setCategories(String categories) {
        this.categories = categories;
    }
    
}
