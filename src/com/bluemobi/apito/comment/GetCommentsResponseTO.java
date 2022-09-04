package com.bluemobi.apito.comment;

import java.util.ArrayList;
import java.util.List;


import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.bluemobi.apito.CommentTO;

/**
 * 【获取评论列表】Response
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class GetCommentsResponseTO extends AbstractObject {

	private static final long serialVersionUID = 1L;

	//评论
    private List<CommentTO> comment = new ArrayList<CommentTO>();
	//页码
	private Integer page;
	//每页记录数
	private Integer pageSize;
	//总记录数
	private Long count;


    /**设置评论*/
	public void setComment(List<CommentTO> comment){
		this.comment=comment;
	}
	/**获取评论*/
	public List<CommentTO> getComment(){
		return this.comment;
	}
    /**设置页码*/
	public void setPage(Integer page){
		this.page=page;
	}
	/**获取页码*/
	public Integer getPage(){
		return this.page;
	}
    /**设置每页记录数*/
	public void setPageSize(Integer pageSize){
		this.pageSize=pageSize;
	}
	/**获取每页记录数*/
	public Integer getPageSize(){
		return this.pageSize;
	}
    /**设置总记录数*/
	public void setCount(Long count){
		this.count=count;
	}
	/**获取总记录数*/
	public Long getCount(){
		return this.count;
	}


}
