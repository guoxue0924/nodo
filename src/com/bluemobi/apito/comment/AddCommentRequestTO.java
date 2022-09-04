package com.bluemobi.apito.comment;



import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 【发表评论】Request
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class AddCommentRequestTO extends AbstractObject {

	private static final long serialVersionUID = 1L;

	//商品id
	private Integer skuId;
	//评论内容
	private String content;
	//商品满意度评分
	private Integer rankBase;
	//物流满意度评分
	private Integer rankLogistics;
	//发货速度满意度评分
	private Integer rankSpeed;


    /**设置商品id*/
	public void setSkuId(Integer skuId){
		this.skuId=skuId;
	}
	/**获取商品id*/
	public Integer getSkuId(){
		return this.skuId;
	}
    /**设置评论内容*/
	public void setContent(String content){
		this.content=content;
	}
	/**获取评论内容*/
	public String getContent(){
		return this.content;
	}
    /**设置商品满意度评分*/
	public void setRankBase(Integer rankBase){
		this.rankBase=rankBase;
	}
	/**获取商品满意度评分*/
	public Integer getRankBase(){
		return this.rankBase;
	}
    /**设置物流满意度评分*/
	public void setRankLogistics(Integer rankLogistics){
		this.rankLogistics=rankLogistics;
	}
	/**获取物流满意度评分*/
	public Integer getRankLogistics(){
		return this.rankLogistics;
	}
    /**设置发货速度满意度评分*/
	public void setRankSpeed(Integer rankSpeed){
		this.rankSpeed=rankSpeed;
	}
	/**获取发货速度满意度评分*/
	public Integer getRankSpeed(){
		return this.rankSpeed;
	}


}
