package com.bluemobi.serviceimpl.bts;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.constant.Symbol;
import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.appcore.util.StringUtil;
import com.bluemobi.dao.bts.BtsCartDao;
import com.bluemobi.po.bts.BtsCart;
import com.bluemobi.service.bts.BtsCartService;

/**
 * 【购物车】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-14 11:01:20
 * 
 */
@Service(value = "btsCartService")
public class BtsCartServiceImpl extends MybatisBaseServiceImpl implements BtsCartService {

    @Autowired
    private BtsCartDao btsCartDao;
    
    @Override
    public MyBatisBaseDao getDao() {
        return btsCartDao;
    }

    @Override
    public BtsCart addGoodsToCart(int userid, Long skuId) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userid", userid);
        map.put("skuId", skuId);
        // 1、查询该用户购物车中是否已经存在当前商品，若存在则将数量加1 ，否则创建一个购物车对象并将数量加1
        List<BtsCart> list = btsCartDao.selectObjectList(map);
        BtsCart btsCart = null;
        if (!list.isEmpty()) {
            btsCart = list.get(0);
            btsCart.setQuantity((short) (btsCart.getQuantity() + 1));
            btsCartDao.update(btsCart);
        } else {
            btsCart = new BtsCart();
            btsCart.setUserid(userid);
            btsCart.setSkuId(skuId);
            btsCart.setQuantity((short) 1);
            btsCart.setCtime(new Date());
            btsCartDao.insert(btsCart);
        }
        return btsCart;
    }

    @Override
    public void deleteBtsCartByIds(int userid, String cartIds) {

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("userid", userid);
        int[] ii = StringUtil.stringArrayToIntArray(cartIds.split(Symbol.douHao));
        map.put("cartIds", ii);

        btsCartDao.deleteBtsCartByIds(map);

    }

    @Override
    public List<BtsCart> selectSkuFromCart(Integer[] cartList) {
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("cartIds", cartList);
        return this.btsCartDao.selectBtsCartByIds(parameter);
    }

}
