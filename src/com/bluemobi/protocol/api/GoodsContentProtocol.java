package com.bluemobi.protocol.api;

import java.util.ArrayList;
import java.util.List;

import com.bluemobi.apito.GoodsSkuSimpleTO;
import com.bluemobi.apito.GoodsSkuSimplesTO;
import com.bluemobi.apito.GoodsSkuTO;
import com.bluemobi.apito.goods.GetDefaultGoodsResponseTO;
import com.bluemobi.apito.goods.GetGoodsSkuResponseTO;
import com.bluemobi.apito.goods.SearchGoodsResponseTO;
import com.bluemobi.po.goods.GoodsContent;
import com.bluemobi.po.goods.GoodsContentSku;
import com.bluemobi.po.trend.TrendAttachment;
import com.bluemobi.serviceimpl.goods.GoodsContentSkuServiceImpl;

/**
 * 商品相关协议
 * @Description
 * @author haojian 309444359@qq.com
 * @date 2016-2-18 下午3:54:23 
 *
 */
public class GoodsContentProtocol {
    
    /**
     * 
     * @author haojian
     * @date 2016-2-19 下午12:16:00 
     * @param goodsContent
     * @param goodsContentSku
     * @return
     * @return GetGoodsSkuResponseTO
     */
    public static GetGoodsSkuResponseTO newGetGoodsSkuResponseTO(GoodsContent goodsContent, GoodsContentSku goodsContentSku){
        
        GetGoodsSkuResponseTO getGoodsSkuResponseTO = new GetGoodsSkuResponseTO();
        
        GoodsSkuTO goodsSku = GoodsContentProtocol.newGoodsSkuTO(goodsContent, goodsContentSku);
        getGoodsSkuResponseTO.setGoodsSku(goodsSku);
        
        return getGoodsSkuResponseTO;
        
    }
    
    /**
     * 创建 GoodsSkuTO
     * @author haojian
     * @date 2016-2-19 下午12:07:10 
     * @param goodsContent
     * @param goodsContentSku
     * @return
     * @return GoodsSkuTO
     */
    public static GoodsSkuTO newGoodsSkuTO(GoodsContent goodsContent, GoodsContentSku goodsContentSku) {
        
        GoodsSkuTO to = new GoodsSkuTO();
        to.setSkuId(goodsContentSku.getSkuId().intValue());
        to.setName(goodsContentSku.getName());
        to.setImages(goodsContentSku.getImages());
        to.setPrice(goodsContentSku.getPrice().floatValue());
        to.setStock(goodsContentSku.getStock());
        to.setPropertys(goodsContent.getPropertyTOList());//商品属性
        to.setSkuPropertys(goodsContentSku.getSkuPropertyTOList());//sku自身属性id
        to.setContentId(goodsContentSku.getContentId().intValue());
        to.setPriceMarket(goodsContentSku.getPriceMarket().floatValue());
        to.setBody(goodsContent.getBody());
        
        return to;
    }
    
    private static String[] trendAttachmentListToImages(List<TrendAttachment> list){
        String[] ss = new String[list.size()];
        for(int i=0; i<list.size(); i++){
            TrendAttachment t = list.get(i);
            ss[i] = t.getFilepath();
        }
        return ss;
    }
    
    /**
     * 将多个商品sku信息，转换成一个商品列表信息
     * 用于搜索商品列表的展示
     * @author haojian
     * @date 2016-2-18 下午4:00:38 
     * @param list
     * @return
     * @return SearchGoodsResponseTO
     */
    public static SearchGoodsResponseTO newSearchGoodsResponseTO(List<List<GoodsContentSku>> list) {

        SearchGoodsResponseTO searchGoodsResponseTO = new SearchGoodsResponseTO();
//        List<GoodsSkuSimplesTO> goodsSkuSimplesTOList = searchGoodsResponseTO.getGoodsSkuSimples();
//        for (List<GoodsContentSku> skuList : list) {
//            GoodsSkuSimplesTO goodsTO = GoodsContentProtocol.newGoodsSkuSimplesTO(skuList);
//            goodsSkuSimplesTOList.add(goodsTO);
//        }
        GoodsContentSkuServiceImpl skuService = new GoodsContentSkuServiceImpl();
        List<GoodsSkuSimpleTO> goodsSkuSimples = null;
        GoodsSkuSimpleTO skuSimpleTO = null;
        List<GoodsContentSku> skus = null;
        GoodsContentSku sku = null;
        GoodsSkuTO skuTO = null;
        for (int i = 0; i < list.size(); i++) {
            goodsSkuSimples = new ArrayList<GoodsSkuSimpleTO>();
            skus = list.get(i);
            for (int j = 0; j < skus.size(); j++) {
                sku = skus.get(j);
                skuTO = skuService.getGoodsContentSku(sku.getSkuId());
                skuSimpleTO = new GoodsSkuSimpleTO();
                skuSimpleTO.setSkuId(skuTO.getSkuId());
                skuSimpleTO.setName(skuTO.getName());
                if (!skuTO.getImages().isEmpty()) {
                    skuSimpleTO.setImage(skuTO.getImages().get(0));
                }
                skuSimpleTO.setPrice(skuTO.getPrice());
                goodsSkuSimples.add(skuSimpleTO);
            }
            // 这是一个商品
            searchGoodsResponseTO.setGoodsSkuSimples(goodsSkuSimples);
        }
        return searchGoodsResponseTO;
    }

    
    /**
     * 将多个商品sku信息，转换成一个商品列表信息
     * 用于推荐商品的展示
     * @author haojian
     * @date 2016-2-18 下午3:53:42 
     * @param list
     * @return
     * @return GetDefaultGoodsResponseTO
     */
    public static GetDefaultGoodsResponseTO newGetDefaultGoodsResponseTO(List<List<GoodsContentSku>> list) {

        GetDefaultGoodsResponseTO getDefaultGoodsResponseTO = new GetDefaultGoodsResponseTO();
//        List<GoodsSkuSimplesTO> goodsSkuSimplesTOList = getDefaultGoodsResponseTO.getGoodsSkuSimples();
//        for (List<GoodsContentSku> skuList : list) {
//            GoodsSkuSimplesTO goodsTO = GoodsContentProtocol.newGoodsSkuSimplesTO(skuList);
//            goodsSkuSimplesTOList.add(goodsTO);
//        }

        return getDefaultGoodsResponseTO;
    }

    
    /**
     * 将一个商品的一组sku转换成一个简单信息
     * @author haojian
     * @date 2016-2-18 下午3:51:46 
     * @param goodsContentSkuList
     * @return
     * @return GoodsSkuSimplesTO
     */
    public static GoodsSkuSimplesTO newGoodsSkuSimplesTO(List<GoodsContentSku> goodsContentSkuList) {

        GoodsSkuSimplesTO to = new GoodsSkuSimplesTO();
        
        for (GoodsContentSku sku : goodsContentSkuList) {
            GoodsSkuSimpleTO goodsSkuSimpleTO = GoodsContentProtocol.newGoodsSkuSimpleTO(sku);
            to.getGoodsSkuSimple().add(goodsSkuSimpleTO);
        }
        
        return to;
    }

    /**
     * 将一个GoodsContentSku对象转换成一个GoodsSkuSimpleTO对象
     * 
     * @author haojian
     * @date 2016-2-18 下午3:46:29
     * @param goodsContentSku
     * @return
     * @return GoodsSkuSimpleTO
     */
    public static GoodsSkuSimpleTO newGoodsSkuSimpleTO(GoodsContentSku goodsContentSku) {

        GoodsSkuSimpleTO to = new GoodsSkuSimpleTO();
        to.setSkuId(goodsContentSku.getSkuId().intValue());
        to.setName(goodsContentSku.getName());
        
        if (!goodsContentSku.getImages().isEmpty()) {
            to.setImage(goodsContentSku.getImages().get(0));
        } else {
            to.setImage("");
        }
        to.setPrice(goodsContentSku.getPrice().floatValue());

        return to;
    }

}
