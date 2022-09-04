package com.bluemobi.apito.goods;



import com.appcore.model.AbstractObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 【搜索商品】Request
 * @author AutoCode 309444359@qq.com
 */
@JsonIgnoreProperties
public class SearchGoodsRequestTO extends AbstractObject {

	private static final long serialVersionUID = 1L;

	//搜索类型    1、根据商品分类id搜索  2、字符串模糊搜索
	private Integer searchType;
	//搜索类型    1、根据商品品牌id搜索 
	private Integer brandId;
	//搜索条件
	private String condition;
	//当前页
	private Integer currentPage;
	//排序规则    1、默认排序；  2、销量从高到低；  3、销量从低到高；  4、价格从高到低；  5、价格从低到高。
	private String sort;


    /**设置搜索类型    1、根据商品分类id搜索  2、字符串模糊搜索*/
	public void setSearchType(Integer searchType){
		this.searchType=searchType;
	}
	/**获取搜索类型    1、根据商品分类id搜索  2、字符串模糊搜索*/
	public Integer getSearchType(){
		return this.searchType;
	}
    /**设置搜索条件*/
	public void setCondition(String condition){
		this.condition=condition;
	}
	/**获取搜索条件*/
	public String getCondition(){
		return this.condition;
	}
    /**设置当前页*/
	public void setCurrentPage(Integer currentPage){
		this.currentPage=currentPage;
	}
	/**获取当前页*/
	public Integer getCurrentPage(){
		return this.currentPage;
	}
    /**设置排序规则    1、默认排序；  2、销量从高到低；  3、销量从低到高；  4、价格从高到低；  5、价格从低到高。*/
	public void setSort(String sort){
		this.sort=sort;
	}
	/**获取排序规则    1、默认排序；  2、销量从高到低；  3、销量从低到高；  4、价格从高到低；  5、价格从低到高。*/
	public String getSort(){
		return this.sort;
	}
	/**获取搜索类型    1、根据商品品牌id搜索*/
	public Integer getBrandId() {
		return brandId;
	}
	/**设置搜索类型    1、根据商品品牌id搜索*/
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	

}
