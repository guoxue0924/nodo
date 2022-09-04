package com.bluemobi.to.comment;

import java.util.List;

import com.appcore.model.AbstractObject;

/**
 * 添加订单评论
 * 
 * @author heweiwen 2015-10-22 下午4:31:04
 */
public class OrderCommentTO extends AbstractObject {

	private static final long serialVersionUID = 1099015548524629539L;
	// 物流满意度评分。分值：1 - 5
	private Integer rankLogistics;
	// 发货速度满意度评分。分值：1 - 5
	private Integer rankSpeed;

	private List<ItemCommentTO> itemComments;

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

	public List<ItemCommentTO> getItemComments() {
		return itemComments;
	}

	public void setItemComments(List<ItemCommentTO> itemComments) {
		this.itemComments = itemComments;
	}

}
