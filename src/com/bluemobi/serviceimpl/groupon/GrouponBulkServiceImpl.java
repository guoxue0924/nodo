package com.bluemobi.serviceimpl.groupon;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.groupon.GrouponBulkDao;
import com.bluemobi.po.goods.GoodsContentSku;
import com.bluemobi.po.groupon.GrouponBulk;
import com.bluemobi.service.goods.GoodsContentSkuService;
import com.bluemobi.service.groupon.GrouponBulkCategoryService;
import com.bluemobi.service.groupon.GrouponBulkService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.to.groupon.GrouponBulkTO;

/**
 * 【团购表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-20 16:51:17
 * 
 */
@Service(value = "grouponBulkService")
public class GrouponBulkServiceImpl extends MybatisBaseServiceImpl implements
        GrouponBulkService {

    @Autowired
    private GrouponBulkDao grouponBulkDao;
    @Autowired
    private GoodsContentSkuService goodsContentSkuService;
    @Autowired
    private GrouponBulkCategoryService grouponBulkCategoryService;
    
    @Override
    public MyBatisBaseDao getDao() {
        return grouponBulkDao;
    }

    @Override
    public ResultTO saveGrouponBulk(GrouponBulk bulk, Integer[] categoryIds) {
    	// 判断是否可以修改
    	Date now = Calendar.getInstance().getTime();
    	if(bulk.getBulkId() != null && !now.before(bulk.getStartTime())) {
    		return ResultTO.newFailResultTO("活动已开始,不能修改信息", null);
    	}
    	
    	Map<String, Object> parameter = new HashMap<String, Object>();
    	//1. 校验sku是否存在
    	parameter.put("skuId", bulk.getSkuId());
    	GoodsContentSku sku = goodsContentSkuService.selectObject(parameter);
    	if(sku == null) {
            return ResultTO.newFailResultTO("符合条件的商品sku不存在", null);
        } 
        
        //2. 校验团购价格是否大于销售价
        if(new BigDecimal(bulk.getPrice()).compareTo(sku.getPrice()) > -1) {
            return ResultTO.newFailResultTO("团购价不能大于销售价", null);
        }
        //3. 校验团购库存是否小于实际库存
        if(bulk.getInventorySum() > sku.getStock()) {
            return ResultTO.newFailResultTO("团购库存不能大于实际库存", null);
        }
        //6. 检查当前商品的当前活动时间否则已包含于其他活动中
        parameter.put("startTime", bulk.getStartTime());
        parameter.put("endTime", bulk.getEndTime());
        List<GrouponBulk> bulkList = grouponBulkDao.selectForCheckTime(parameter);
        
        //7. 根据bulk判断是更新还是创建操作
        int bulkRet = 0;
        int bulkCategoryRet = 0;
        if(bulk.getBulkId() == null || bulk.getBulkId() == 0) {
            bulk.setCtime(Calendar.getInstance().getTime());
            if(bulkList == null || bulkList.size() == 0) {
            	bulkRet = this.insert(bulk);
            } else {
                return ResultTO.newFailResultTO("该商品存在时间冲突的团购活动", null);
            }
        } else {
            if(bulkList != null  && bulkList.size() > 1) {
                return ResultTO.newFailResultTO("该商品存在时间冲突的团购活动", null);
            }
            parameter.clear();
            parameter.put("bulkId", bulk.getBulkId());
            GrouponBulk bulkInDB = grouponBulkDao.selectObject(parameter);
            if(bulkInDB == null) {
                return ResultTO.newFailResultTO("当前活动已被删除", null);
            }
            bulk.setInventory(bulk.getInventorySum());
            bulkRet = this.update(bulk);
            grouponBulkCategoryService.delete(bulk.getBulkId());
            
        }
        
      //5. 处理分类标签
        if(categoryIds != null && categoryIds.length > 0) {
        	bulkCategoryRet = grouponBulkCategoryService.batchSaveGrouponBulkCategory(bulk.getBulkId(), categoryIds);
        }
        if(bulkRet == 1 && (bulkCategoryRet == (categoryIds == null ? 0 : categoryIds.length))) {
        	return ResultTO.newSuccessResultTO(null);
        } else {
        	return ResultTO.newFailResultTO("操作失败,请联系管理员", null);
        }
    }

    @Override
    public ResultTO deleteBulk(Integer bulkId) {
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("bulkId", bulkId);
        GrouponBulk bulk = this.selectObject(parameter);
        if(Calendar.getInstance().getTime().before(bulk.getStartTime())) {
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
    public GrouponBulk searchBySku(GoodsContentSku sku) {
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("currentTime", Calendar.getInstance().getTime());
        parameter.put("sku", sku.getSku());
        return grouponBulkDao.selectBySku(parameter);
    }

	/** 
	 * @see com.bluemobi.service.groupon.GrouponBulkService#getGrouponBulkDetail(int) 
	 */  
	@Override
	public GrouponBulkTO getGrouponBulkDetail(int bulkId) {
		return grouponBulkDao.selectDetail(bulkId);
	}

}
