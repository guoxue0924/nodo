/**
 * Project Name:nodo 
 * File Name:SkuTO.java 
 * Package Name:com.bluemobi.to.groupon 
 * Date:2016年1月6日下午2:19:59 
 */
package com.bluemobi.to.groupon;

import com.appcore.model.AbstractObject;

/**
 * ClassName: SkuTO Date: 2016年1月6日下午2:19:59
 * 
 * @author kevin
 * @version
 * @since JDK 7
 */
public class SkuTO extends AbstractObject {

	private static final long serialVersionUID = 8305125960139272952L;

	private long skuId;

	private String name;

	private float price;

	private int stock;

	private String brandName;

	public long getSkuId() {
		return skuId;
	}

	public void setSkuId(long skuId) {
		this.skuId = skuId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

}
