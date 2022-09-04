package com.bluemobi.service.groupon;

import com.appcore.service.MybatisBaseService;
import com.bluemobi.po.goods.GoodsContentSku;
import com.bluemobi.po.groupon.GrouponBulk;
import com.bluemobi.to.ResultTO;
import com.bluemobi.to.groupon.GrouponBulkTO;

/**
 * 【团购表】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-20 16:51:17
 * 
 */
public interface GrouponBulkService extends MybatisBaseService {

    /**
     * 保存GrouponBulk对象, 根据是否有bulkId来判断更新还是创建
     * @author liuyt
     * @date 2015-10-22 下午1:37:23
     * @param bulk
     * @return
     * @version
     * @param categoryIds 
     */
    ResultTO saveGrouponBulk(GrouponBulk bulk, Integer[] categoryIds);

    /**
     * 删除GrouponBulk对象, 只能删除未开始的活动
     * @author liuyt
     * @date 2015-10-22 下午4:05:46
     * @param bulkId
     * @return
     * @version
     */
    ResultTO deleteBulk(Integer bulkId);
    
    /**
     * 根据给定的sku,查询当前时间有效的抢购活动
     * @author liuyt
     * @date 2015-11-18 下午3:21:02
     * @param sku
     * @return
     * @version
     */
    GrouponBulk searchBySku(GoodsContentSku sku);
    
    /**
     * 获取指定的抢购活动详情
     * getGrouponBulkDetail
     * 
     * @author kevin
     * @param bulkId
     * @return 
     * @since JDK 7
     */
    GrouponBulkTO getGrouponBulkDetail(int bulkId);

}
