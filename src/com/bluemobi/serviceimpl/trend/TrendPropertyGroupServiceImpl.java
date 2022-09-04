package com.bluemobi.serviceimpl.trend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.trend.TrendPropertyGroupDao;
import com.bluemobi.dao.trend.TrendPropertyToGroupDao;
import com.bluemobi.po.trend.TrendPropertyGroup;
import com.bluemobi.po.trend.TrendPropertyToGroup;
import com.bluemobi.service.trend.TrendPropertyGroupService;
import com.bluemobi.to.trend.PropertyGroupTO;

/**
 * 【属性资源分组表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-07-30 10:42:29
 * 
 */
@Service(value = "trendPropertyGroupService")
public class TrendPropertyGroupServiceImpl extends MybatisBaseServiceImpl implements TrendPropertyGroupService {

    @Autowired
    private TrendPropertyGroupDao trendPropertyGroupDao;
    @Autowired
    private TrendPropertyToGroupDao trendPropertyToGroupDao;
    
    @Override
    public MyBatisBaseDao getDao() {
        return trendPropertyGroupDao;
    }

    public void saveTrendPropertyGroup(PropertyGroupTO property) {
        // 重组对象
        TrendPropertyGroup trendPropertyGroup = new TrendPropertyGroup();
        trendPropertyGroup.setPropertyGroupName(property.getPropertyGroupName());
        trendPropertyGroup.setSortOrder(property.getSortOrder());

        // 分割String对象
        String[] propertyIds = property.getPropertyIds().split(",");

        // 保存分组对象
        trendPropertyGroupDao.insert(trendPropertyGroup);

        // 批量新增TrendPropertyToGroup数据
        List<TrendPropertyToGroup> ptoGroupList = new ArrayList<TrendPropertyToGroup>();
        TrendPropertyToGroup trendPropertyToGroup = null;
        for (int i = 0; i < propertyIds.length; i++) {
            trendPropertyToGroup = new TrendPropertyToGroup();
            trendPropertyToGroup.setPropertyGroupId(trendPropertyGroup.getPropertyGroupId());
            trendPropertyToGroup.setPropertyId(Integer.parseInt(propertyIds[i]));
            trendPropertyToGroup.setSortOrder(i + 1);
            ptoGroupList.add(trendPropertyToGroup);
        }
        trendPropertyToGroupDao.insertTrendPropertyToGroups(ptoGroupList);
    }

    public void updateTrendPropertyGroup(PropertyGroupTO property) {
        // 重组对象
        TrendPropertyGroup trendPropertyGroup = new TrendPropertyGroup();
        trendPropertyGroup.setPropertyGroupId(property.getPropertyGroupId());
        trendPropertyGroup.setPropertyGroupName(property.getPropertyGroupName());
        trendPropertyGroup.setSortOrder(property.getSortOrder());
        // 分割String对象
        String[] propertyIds = property.getPropertyIds().split(",");
        // 保存分组对象
        trendPropertyGroupDao.update(trendPropertyGroup);

        // 先删除TrendPropertyToGroup中的所有记录
        Map<String, Object> ptgMap = new HashMap<String, Object>();
        ptgMap.put("propertyGroupId", property.getPropertyGroupId());
        trendPropertyToGroupDao.deleteByGroupId(ptgMap);

        // 批量新增TrendPropertyToGroup数据
        List<TrendPropertyToGroup> ptoGroupList = new ArrayList<TrendPropertyToGroup>();
        TrendPropertyToGroup trendPropertyToGroup = null;
        for (int i = 0; i < propertyIds.length; i++) {
            trendPropertyToGroup = new TrendPropertyToGroup();
            trendPropertyToGroup.setPropertyGroupId(trendPropertyGroup.getPropertyGroupId());
            trendPropertyToGroup.setPropertyId(Integer.parseInt(propertyIds[i]));
            trendPropertyToGroup.setSortOrder(i + 1);
            ptoGroupList.add(trendPropertyToGroup);
        }
        trendPropertyToGroupDao.insertTrendPropertyToGroups(ptoGroupList);
    }

    public void deleteTrendPropertyGroup(String propertyGroupIdStr) {
        Map<String, Object> map = new HashMap<String, Object>();
        String[] ids = propertyGroupIdStr.split(",");
        map.put("propertyGroupIds", ids);
        // 查询分组下是否有关联的属性
        Integer count = trendPropertyToGroupDao.selectPropertyCountByGroupId(map);
        // 如果有关联的属性
        if (count != null && count > 0) {
            // 根据分组id批量删除关联表中的数据
            trendPropertyToGroupDao.deleteByPropertyGroupIds(map);
        }
        // 根据分组id批量删除分组数据
        trendPropertyGroupDao.deleteByGroupIds(map);
    }
}
