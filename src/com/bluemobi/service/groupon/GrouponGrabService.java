package com.bluemobi.service.groupon;

import com.appcore.service.MybatisBaseService;
import com.bluemobi.po.goods.GoodsContentSku;
import com.bluemobi.po.groupon.GrouponGrab;
import com.bluemobi.to.ResultTO;

/**
 * 【抢购表】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-20 16:51:18
 * 
 */
public interface GrouponGrabService extends MybatisBaseService {

    /**
     * 保存GrouponGrab对象, 根据是否有grabId来判断更新还是创建
     * @author liuyt
     * @date 2015-10-23 下午2:55:51
     * @param grab
     * @param categoryIds
     * @return
     * @version
     */
    ResultTO saveGrouponGrab(GrouponGrab grab, Integer[] categoryIds);

    /**
     * 删除GrouponGrab对象, 只能删除未开始的活动
     * 
     * @author liuyt
     * @date 2015-10-23 下午2:56:07
     * @param grabId
     * @return
     * @version
     */
    ResultTO deleteGrab(Integer grabId);

    /**
     * 根据给定的sku,查询当前时间有效的抢购活动
     * @author liuyt
     * @date 2015-11-18 下午3:21:02
     * @param sku
     * @return
     * @version
     */
    GrouponGrab searchBySku(GoodsContentSku sku);

}
