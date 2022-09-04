package com.bluemobi.serviceimpl.groupon;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.groupon.GrouponGrabDao;
import com.bluemobi.po.goods.GoodsContent;
import com.bluemobi.po.goods.GoodsContentSku;
import com.bluemobi.po.groupon.GrouponGrab;
import com.bluemobi.service.goods.GoodsContentService;
import com.bluemobi.service.goods.GoodsContentSkuService;
import com.bluemobi.service.groupon.GrouponGrabService;
import com.bluemobi.to.ResultTO;

/**
 * 【抢购表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-20 16:51:18
 * 
 */
@Service(value = "grouponGrabService")
public class GrouponGrabServiceImpl extends MybatisBaseServiceImpl implements
        GrouponGrabService {

    @Autowired
    private GrouponGrabDao grouponGrabDao;
    @Autowired
    private GoodsContentService goodsContentService;
    @Autowired
    private GoodsContentSkuService goodsContentSkuService;

    @Override
    public MyBatisBaseDao getDao() {
        return grouponGrabDao;
    }

    @Override
    public ResultTO saveGrouponGrab(GrouponGrab grab, Integer[] categoryIds) {
        //1. 校验sku是否存在
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("name", grab.getGoodsName());
        List<GoodsContent> goodsList = goodsContentService.selectObjectList(parameter);
        if(goodsList == null || goodsList.size() ==0) {
            return ResultTO.newFailResultTO("符合name商品不存在", null);
        } else if(goodsList.size() > 1){
            return ResultTO.newFailResultTO("符合name的商品存在多个", null);
        }
        parameter.clear();
        parameter.put("sku", grab.getSku());
        List<GoodsContentSku> skuList = goodsContentSkuService.selectObjectList(parameter);
        if(skuList == null || skuList.size() ==0) {
            return ResultTO.newFailResultTO("符合sku的商品不存在", null);
        } else if(skuList.size() > 1){
            return ResultTO.newFailResultTO("符合sku的商品存在多个", null);
        }
        //2. 校验抢购价格是否小于销售价
        if(new BigDecimal(grab.getPrice()).compareTo(skuList.get(0).getPrice()) > -1) {
            return ResultTO.newFailResultTO("抢购价不能大于销售价", null);
        }
        //3. 校验抢购总库存是否小于实际库存
        if(grab.getInventorySum() > skuList.get(0).getStock()) {
            return ResultTO.newFailResultTO("抢购总库存不能大于实际库存", null);
        }
        //4. 校验库存标量是否小于抢购总库存
        if(grab.getInventory() >= grab.getInventorySum()) {
            return ResultTO.newFailResultTO("库存标量大于或等于抢购总库存量", null);
        }
        //5. 处理分类标签
        if(categoryIds != null && categoryIds.length > 0) {
            StringBuffer categoryId = new StringBuffer();
            for (Integer id : categoryIds) {
                categoryId.append(id).append(",");
            }
            categoryId.delete(categoryId.length() - 1, categoryId.length());
            grab.setCategoryId(categoryId.toString());
        }
        
      //6. 检查当前商品的当前活动时间否则已包含于其他活动中
        parameter.put("startTime", grab.getStartTime());
        parameter.put("endTime", grab.getEndTime());
        List<GrouponGrab> grabList = grouponGrabDao.selectForCheckTime(parameter);
        
        //7. 根据grab判断是更新还是创建操作
        int ret = 0;
        if(grab.getBulkId() == null || grab.getBulkId() == 0) {
            grab.setCtime(Calendar.getInstance().getTime());
            if(grabList == null || grabList.size() == 0) {
                ret = this.insert(grab);
            } else {
                return ResultTO.newFailResultTO("该商品存在时间冲突的抢购活动", null);
            }
        } else {
            if(grabList != null  && grabList.size() > 1) {
                return ResultTO.newFailResultTO("该商品存在时间冲突的抢购活动", null);
            } if (grabList.get(0).getBulkId().intValue() != grab.getBulkId().intValue()) {
                return ResultTO.newFailResultTO("当前活动已被删除", null);
            }
            ret = this.update(grab);
        }
        if(ret == 1) {
        	return ResultTO.newSuccessResultTO(null);
        } else {
            return ResultTO.newFailResultTO("操作失败,请联系管理员", null);
        }
    }

    @Override
    public ResultTO deleteGrab(Integer grabId) {
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("bulkId", grabId);
        GrouponGrab grab = this.selectObject(parameter);
        if(Calendar.getInstance().getTime().before(grab.getStartTime())) {
            int ret = this.delete(parameter);
            if(ret == 1) {
            	return ResultTO.newSuccessResultTO(null);
            } else {
            	return ResultTO.newFailResultTO("操作失败,请联系管理员", null);
            }
        } else {
            return ResultTO.newFailResultTO("活动已开始,不能删除", null);
        }
    }

    @Override
    public GrouponGrab searchBySku(GoodsContentSku sku) {
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("currentTime", Calendar.getInstance().getTime());
        parameter.put("sku", sku.getSku());
        return grouponGrabDao.selectBySku(parameter);
    }

}
