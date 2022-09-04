package com.bluemobi.to.comment;

import com.appcore.model.AbstractObject;

/**
 * 添加商品评论
 * @author heweiwen
 * 2015-10-22 下午4:31:04
 */
public class AddGoodsCommentTO extends AbstractObject {

	public static final long serialVersionUID = 1L;

    //父级评论 ID
	private Integer pid;
    //评论分类 ID
	private Integer categoryId;
    //评论所针对的 ID
	private Integer toId;
    //评论针对的订单商品编号
	private Integer toOrderItemId;
    //评论针对的店铺 ID
	private Integer toStoreId;
    //评论标题
	private String title;
    //评论内容
	private String content;
    //商品满意度评分。分值：0 - 100
	private Integer rankBase;
    //物流满意度评分。分值：0 - 100
	private Integer rankLogistics;
    //发货速度满意度评分。分值：0 - 100
	private Integer rankSpeed;
	
	private int userId;

    /**设置父级评论 ID*/
	public void setPid(Integer pid){
		this.pid=pid;
	}
	/**获取父级评论 ID*/
	public Integer getPid(){
		return this.pid;
	}
    public Integer getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
    public Integer getToId() {
        return toId;
    }
    public void setToId(Integer toId) {
        this.toId = toId;
    }
    public Integer getToOrderItemId() {
        return toOrderItemId;
    }
    public void setToOrderItemId(Integer toOrderItemId) {
        this.toOrderItemId = toOrderItemId;
    }
    public Integer getToStoreId() {
        return toStoreId;
    }
    public void setToStoreId(Integer toStoreId) {
        this.toStoreId = toStoreId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Integer getRankLogistics() {
        return rankLogistics;
    }
    public void setRankLogistics(Integer rankLogistics) {
        this.rankLogistics = rankLogistics;
    }
    public Integer getRankSpeed() {
        return rankSpeed;
    }
    public void setRankSpeed(Integer rankSpeed) {
        this.rankSpeed = rankSpeed;
    }
    public Integer getRankBase() {
        return rankBase;
    }
    public void setRankBase(Integer rankBase) {
        this.rankBase = rankBase;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
   
    
}
