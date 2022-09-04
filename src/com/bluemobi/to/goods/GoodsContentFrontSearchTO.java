package com.bluemobi.to.goods;

import java.util.ArrayList;
import java.util.List;

import com.appcore.model.AbstractObject;

public class GoodsContentFrontSearchTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

    // 商品ID
    private Long contentId;
    // 商品名称
    private String name;

    private List<GoodsContentSkuFrontSearchTO> skus = new ArrayList<GoodsContentSkuFrontSearchTO>();

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GoodsContentSkuFrontSearchTO> getSkus() {
        return skus;
    }

    public void setSkus(List<GoodsContentSkuFrontSearchTO> skus) {
        this.skus = skus;
    }

}
