package com.bluemobi.service.bts;

import java.util.List;

import com.appcore.service.MybatisBaseService;
import com.bluemobi.po.bts.BtsCart;

/**
 * 【购物车】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-14 11:01:20
 * 
 */
public interface BtsCartService extends MybatisBaseService {

    BtsCart addGoodsToCart(int userid, Long contentId);

    void deleteBtsCartByIds(int userid, String cartIds);

    List<BtsCart> selectSkuFromCart(Integer[] cartList);

}
