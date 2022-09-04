package com.bluemobi.apito.comment;



import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 【获取评论列表】Request
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class GetCommentsRequestTO extends AbstractObject {

	private static final long serialVersionUID = 1L;

	//商品id
	private Integer skuId;
	//页码
	private Integer pageNum;
	//每页记录数
	private Integer pageSize;
	//是否默认评论（默认评论只取前3条记录）
	private Boolean isDefault;


    /**设置商品id*/
	public void setSkuId(Integer skuId){
		this.skuId=skuId;
	}
	/**获取商品id*/
	public Integer getSkuId(){
		return this.skuId;
	}
    /**设置页码*/
	public void setPageNum(Integer pageNum){
		this.pageNum=pageNum;
	}
	/**获取页码*/
	public Integer getPageNum(){
		return this.pageNum;
	}
    /**设置每页记录数*/
	public void setPageSize(Integer pageSize){
		this.pageSize=pageSize;
	}
	/**获取每页记录数*/
	public Integer getPageSize(){
		return this.pageSize;
	}
    /**设置是否默认评论（默认评论只取前3条记录）*/
	public void setIsDefault(Boolean isDefault){
		this.isDefault=isDefault;
	}
	/**获取是否默认评论（默认评论只取前3条记录）*/
	public Boolean getIsDefault(){
		return this.isDefault;
	}


}
