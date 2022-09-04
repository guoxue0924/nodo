package com.bluemobi.po.goods;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.appcore.model.AbstractObject;
import com.bluemobi.apito.SkuPropertyTO;
import com.bluemobi.po.trend.TrendAttachment;
import com.bluemobi.po.trend.TrendProperty;

/**
 * 【商品主表】持久化对象 数据库表：goods_content_sku
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-11-19 16:46:21
 * 
 */
public class GoodsContentSku extends AbstractObject {

    public static final long serialVersionUID = 1L;

    //
    private Long skuId;
    // 主商品id
    private Long contentId;
    // 商品货号
    private String sku;
    // 库存
    private Integer stock;
    // 重量。单位：克
    private String weight;
    // 长。单位：毫米（mm）
    private String length;
    // 宽。单位：毫米（mm）
    private String wide;
    // 高。单位：毫米（mm）
    private String height;
    // 销售价格
    private BigDecimal price;
    // 市场价格，即参考价格
    private BigDecimal priceMarket;
    //积分价格
    private Integer priceIntegral;
    // 成本价格。只在后台查看，前台不显示
    private BigDecimal priceCost;
    // 用户保存sku和属性、属性值的关联，数据结构：“属性id_属性值id，属性id_属性值id”，如：“1_12,5_32”。
    private String property;
    // 单件商品销量
    private Integer salesVolume;
    // 单件商品销售额
    private BigDecimal salesAmount;
    // 单件商品评分（平均值）
    private BigDecimal rankAverage;
    // 商品浏览数
    private Integer viewed;
    // 当前版本号
    private Long rev;
    // 序号。值越大，排位越靠前（同一商品不同属性的排序）。
    private Integer sortOrder;
    // 商品图片 ID，多个以半角逗号分隔
    private String attachmentids;
    // 是否上架销售。1：是；0：否；
    private Byte isShelf;
    // 是否标记为删除。1：是；0：否；
    private Byte isDel;
    // 创建时间
    private Date ctime;
    // 最后一次更新时间
    private Date mtime;
    
    //商品名称
    private String name;
    //sku自身的属性和属性值
    private List<SkuPropertyTO> skuPropertyTOList = new ArrayList<SkuPropertyTO>();
    //商品sku图片地址
    private List<String> images;
  
    
	public Integer getPriceIntegral() {
		return priceIntegral;
	}

	public void setPriceIntegral(Integer priceIntegral) {
		this.priceIntegral = priceIntegral;
	}

	/** 获取 属性 */
    public Long getSkuId() {
        return skuId;
    }

    /** 设置 属性 */
    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    /** 获取 主商品id 属性 */
    public Long getContentId() {
        return contentId;
    }

    /** 设置 主商品id 属性 */
    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    /** 获取 商品货号 属性 */
    public String getSku() {
        return sku;
    }

    /** 设置 商品货号 属性 */
    public void setSku(String sku) {
        this.sku = sku;
    }

    /** 获取 库存 属性 */
    public Integer getStock() {
        return stock;
    }

    /** 设置 库存 属性 */
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    /** 获取 重量。单位：克 属性 */
    public String getWeight() {
        return weight;
    }

    /** 设置 重量。单位：克 属性 */
    public void setWeight(String weight) {
        this.weight = weight;
    }

    /** 获取 长。单位：毫米（mm） 属性 */
    public String getLength() {
        return length;
    }

    /** 设置 长。单位：毫米（mm） 属性 */
    public void setLength(String length) {
        this.length = length;
    }

    /** 获取 宽。单位：毫米（mm） 属性 */
    public String getWide() {
        return wide;
    }

    /** 设置 宽。单位：毫米（mm） 属性 */
    public void setWide(String wide) {
        this.wide = wide;
    }

    /** 获取 高。单位：毫米（mm） 属性 */
    public String getHeight() {
        return height;
    }

    /** 设置 高。单位：毫米（mm） 属性 */
    public void setHeight(String height) {
        this.height = height;
    }

    /** 获取 销售价格 属性 */
    public BigDecimal getPrice() {
        return price;
    }

    /** 设置 销售价格 属性 */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /** 获取 市场价格，即参考价格 属性 */
    public BigDecimal getPriceMarket() {
        return priceMarket;
    }

    /** 设置 市场价格，即参考价格 属性 */
    public void setPriceMarket(BigDecimal priceMarket) {
        this.priceMarket = priceMarket;
    }

	/** 获取 成本价格。只在后台查看，前台不显示 属性 */
    public BigDecimal getPriceCost() {
        return priceCost;
    }

    /** 设置 成本价格。只在后台查看，前台不显示 属性 */
    public void setPriceCost(BigDecimal priceCost) {
        this.priceCost = priceCost;
    }

    /** 获取 用户保存sku和属性、属性值的关联，数据结构：“属性id_属性值id，属性id_属性值id”，如：“1_12,5_32”。 属性 */
    public String getProperty() {
        return property;
    }

    /** 设置 用户保存sku和属性、属性值的关联，数据结构：“属性id_属性值id，属性id_属性值id”，如：“1_12,5_32”。 属性 */
    public void setProperty(String property) {
        this.property = property;
    }

    /** 获取 单件商品销量 属性 */
    public Integer getSalesVolume() {
        return salesVolume;
    }

    /** 设置 单件商品销量 属性 */
    public void setSalesVolume(Integer salesVolume) {
        this.salesVolume = salesVolume;
    }

    /** 获取 单件商品销售额 属性 */
    public BigDecimal getSalesAmount() {
        if (salesAmount == null) {
            return new BigDecimal(0);
        }
        return salesAmount;
    }

    /** 设置 单件商品销售额 属性 */
    public void setSalesAmount(BigDecimal salesAmount) {
        this.salesAmount = salesAmount;
    }

    /** 获取 单件商品评分（平均值） 属性 */
    public BigDecimal getRankAverage() {
        if (rankAverage == null) {
            return new BigDecimal(0);
        }
        return rankAverage;
    }

    /** 设置 单件商品评分（平均值） 属性 */
    public void setRankAverage(BigDecimal rankAverage) {
        this.rankAverage = rankAverage;
    }

    /** 获取 商品浏览数 属性 */
    public Integer getViewed() {
        return viewed;
    }

    /** 设置 商品浏览数 属性 */
    public void setViewed(Integer viewed) {
        this.viewed = viewed;
    }

    /** 获取 当前版本号 属性 */
    public Long getRev() {
        return rev;
    }

    /** 设置 当前版本号 属性 */
    public void setRev(Long rev) {
        this.rev = rev;
    }

    /** 获取 序号。值越大，排位越靠前（同一商品不同属性的排序）。 属性 */
    public Integer getSortOrder() {
        return sortOrder;
    }

    /** 设置 序号。值越大，排位越靠前（同一商品不同属性的排序）。 属性 */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    /** 获取 商品图片 ID，多个以半角逗号分隔 属性 */
    public String getAttachmentids() {
        return attachmentids;
    }

    /** 设置 商品图片 ID，多个以半角逗号分隔 属性 */
    public void setAttachmentids(String attachmentids) {
        this.attachmentids = attachmentids;
    }

    /** 获取 是否上架销售。1：是；0：否； 属性 */
    public Byte getIsShelf() {
        return isShelf;
    }

    /** 设置 是否上架销售。1：是；0：否； 属性 */
    public void setIsShelf(Byte isShelf) {
        this.isShelf = isShelf;
    }

    /** 获取 是否标记为删除。1：是；0：否； 属性 */
    public Byte getIsDel() {
        return isDel;
    }

    /** 设置 是否标记为删除。1：是；0：否； 属性 */
    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }

    /** 获取 创建时间 属性 */
    public Date getCtime() {
        return ctime;
    }

    /** 设置 创建时间 属性 */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /** 获取 最后一次更新时间 属性 */
    public Date getMtime() {
        return mtime;
    }

    /** 设置 最后一次更新时间 属性 */
    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public List<SkuPropertyTO> getSkuPropertyTOList() {
        return skuPropertyTOList;
    }

    public void setSkuPropertyTOList(List<SkuPropertyTO> skuPropertyTOList) {
        this.skuPropertyTOList = skuPropertyTOList;
    }
    
    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("GoodsContentSku");
        sb.append("{skuId=").append(skuId);
        sb.append(", contentId=").append(contentId);
        sb.append(", sku=").append(sku);
        sb.append(", stock=").append(stock);
        sb.append(", weight=").append(weight);
        sb.append(", length=").append(length);
        sb.append(", wide=").append(wide);
        sb.append(", height=").append(height);
        sb.append(", price=").append(price);
        sb.append(", priceIntegral=").append(priceIntegral);
        sb.append(", priceMarket=").append(priceMarket);
        sb.append(", priceCost=").append(priceCost);
        sb.append(", property=").append(property);
        sb.append(", salesVolume=").append(salesVolume);
        sb.append(", salesAmount=").append(salesAmount);
        sb.append(", rankAverage=").append(rankAverage);
        sb.append(", viewed=").append(viewed);
        sb.append(", rev=").append(rev);
        sb.append(", sortOrder=").append(sortOrder);
        sb.append(", attachmentids=").append(attachmentids);
        sb.append(", isShelf=").append(isShelf);
        sb.append(", isDel=").append(isDel);
        sb.append(", ctime=").append(ctime);
        sb.append(", mtime=").append(mtime);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GoodsContentSku) {
            GoodsContentSku goodsContentSku = (GoodsContentSku) obj;
            if (this.getSkuId().equals(goodsContentSku.getSkuId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getSkuId();
        return pkStr.hashCode();
    }

}