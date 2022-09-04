package com.bluemobi.to.comment;

import com.appcore.model.AbstractObject;

/**
 * 添加订单评论
 * 
 * @author heweiwen 2015-10-22 下午4:31:04
 */
public class ItemCommentTO extends AbstractObject {

	private static final long serialVersionUID = 5702535590573266638L;
	// orderItemId
	private Long itemId;
	// skuId
	private Long skuId;
	// 商品满意度评分
	private Integer rankBase;
	// 评论内容
	private String content;

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Integer getRankBase() {
		return rankBase;
	}

	public void setRankBase(Integer rankBase) {
		this.rankBase = rankBase;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

}
