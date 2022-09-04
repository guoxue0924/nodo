package com.bluemobi.to.cas;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * ClassName: CasUserPointTO Date: 2016年6月29日 下午9:50:13
 * 
 * @author guoxue
 * @version
 * @since JDK 7
 */
public class CasUserPointTO extends AbstractObject {


    /**
	 * 
	 */
	private static final long serialVersionUID = -8121200606370306301L;

	/**
	 * 开始日期
	 */
	private String startDate;

	/**
	 * 结束日期
	 */
	private String endDate;
	/**
	 * 操作
	 */
    private String stage;
    /**
	 * 每页显示数量
	 */
	private Integer pageSize;

	/**
	 * 页码
	 */
	private Integer pageIndex;
	
	private String description;

	

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	
    
    
}
