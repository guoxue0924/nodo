package com.bluemobi.serviceimpl.trend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

import com.appcore.cache.JedisPoolManager;
import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.appcore.util.JsonUtil;
import com.appcore.util.StringUtil;
import com.bluemobi.constant.RegionConstant;
import com.bluemobi.dao.trend.TrendRegionDao;
import com.bluemobi.po.trend.TrendRegion;
import com.bluemobi.service.trend.TrendRegionService;
import com.bluemobi.util.JedisUtil;

/**
 * 【】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-07-17 11:22:42
 * 
 */
@Service(value = "trendRegionService")
public class TrendRegionServiceImpl extends MybatisBaseServiceImpl implements TrendRegionService {

    @Autowired
    private TrendRegionDao trendRegionDao;
    @Autowired
    private JedisPoolManager jedisPoolManager;

    @Override
    public MyBatisBaseDao getDao() {
        return trendRegionDao;
    }

    private static Map<Integer, TrendRegion> regionMap = new HashMap<Integer, TrendRegion>();

    public void insertRegion(TrendRegion region) {
        // 1、添加非空数据
        if (region.getPid() != 0) {
            region.setPack("mainland");
        } else {
            region.setPack("");
        }
        // 2、保存对象，并通过数据库生成id
        trendRegionDao.insert(region);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("hid", region.getHid());
        map.put("pack", region.getPack());
        map.put("pid", region.getPid());
        map.put("regionName", region.getRegionName());
        map.put("sortOrder", region.getSortOrder());
        TrendRegion regions = (TrendRegion) trendRegionDao.selectObjectList(map).get(0);
        // 3、根据当前id和父类hid，拼接自己的hid
        String hid = "";
        if (region.getPid() != 0) {
            Map<String, Object> reqMap = new HashMap<String, Object>();
            reqMap.put("regionId", regions.getRegionId());
            TrendRegion fj = this.trendRegionDao.selectObject(reqMap);
            hid = fj.getHid() + ":" + StringUtil.lPad(regions.getRegionId() + "", 6, "0");
        } else {
            hid = "0:" + StringUtil.lPad(regions.getRegionId() + "", 6, "0");
        }

        int grade = hid.split(":").length - 1;

        regions.setHid(hid);
        regions.setStatus((byte) 1);
        regions.setGrade(grade);
        // add by guoxue 2016/06/22 begin
        regions.setExpressFee(region.getExpressFee());
        // add by guoxue 2016/06/22 end
        trendRegionDao.update(regions);

        initRegion();
    }

    public void updateRegion(TrendRegion region) {
        Map<String, Object> reqMap = new HashMap<String, Object>();
        reqMap.put("regionId", region.getRegionId());
        TrendRegion regionRes = trendRegionDao.selectObject(reqMap);

        regionRes.setRegionName(region.getRegionName());
        regionRes.setSortOrder(region.getSortOrder());
        // add by guoxue 2016/06/22 begin
        regionRes.setExpressFee(region.getExpressFee());
        // add by guoxue 2016/06/22 end
        trendRegionDao.update(regionRes);

        initRegion();
    }

    public void initRegion() {
        Jedis jedis = null;
        Map<String, Object> regionAloneMap = new HashMap<String, Object>();
        Map<String, Object> regionListMap = new HashMap<String, Object>();
        Map<String, Object> regionFullNameMap = new HashMap<String, Object>();
        try {
            jedis = jedisPoolManager.getResource();
            List<TrendRegion> regions = trendRegionDao.selectObjectList(null);
            for (TrendRegion region : regions) {
                regionMap.put(region.getRegionId(), region);
                // 单独存储每一个地区的信息
                regionAloneMap.put(region.getRegionId() + "", JsonUtil.getJsonString(region));
            }
            JedisUtil.putObjectsToMap(jedis, RegionConstant.CACHE_REGION_ALONE_MAP, regionAloneMap);
            for (TrendRegion region : regions) {
                TrendRegion parent = regionMap.get(region.getPid());
                if (parent != null) {
                    parent.getSubMap().put(region.getRegionId(), region);
                }
            }
            // 存储中国所有地区
            jedis.set("regions", JsonUtil.getJsonString(regionMap.get(3743)));

            /*
             * 存储该分类下的所有子类，保存结构：key:"region:list:regionId",value:List<TrendRegion
             * >
             */
            List<TrendRegion> list = null;
            TrendRegion trendRegion = null;
            for (Integer key : regionMap.keySet()) {
                TrendRegion region = regionMap.get(key);
                if (region.getSubMap() != null && !region.getSubMap().isEmpty()) {
                    Map<Integer, TrendRegion> map = region.getSubMap();
                    list = new ArrayList<TrendRegion>();
                    for (Entry<Integer, TrendRegion> entry : map.entrySet()) {
                        TrendRegion obj = map.get(entry.getKey());
                        trendRegion = new TrendRegion();
                        trendRegion.setEnName(obj.getEnName());
                        trendRegion.setGrade(obj.getGrade());
                        trendRegion.setPid(obj.getPid());
                        trendRegion.setRegionId(obj.getRegionId());
                        trendRegion.setRegionName(obj.getRegionName());
                        trendRegion.setSortOrder(obj.getSortOrder());
                        list.add(trendRegion);
                    }
                    regionListMap.put(region.getRegionId() + "", JsonUtil.getJsonString(list));
                }
            }
            JedisUtil.putObjectsToMap(jedis, RegionConstant.CACHE_REGION_LIST_MAP, regionListMap);

            /* 存全部名称，保存结构：key:"region:fullName:regionId",value:"省 市 县区" */
            for (int i = 0; i < regions.size(); i++) {
                TrendRegion region = regions.get(i);
                String name = "";
                name = region.getRegionName();
                name = getName(region, name);
                regionFullNameMap.put(region.getRegionId() + "", name);
            }
            JedisUtil.putObjectsToMap(jedis, RegionConstant.CACHE_REGION_FULL_NAME_MAP, regionFullNameMap);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedisPoolManager.returnResourceObject(jedis);
        }
    }

    /**
     * 根据子地区的名称查询全称
     * 
     * @auther zhangzheng
     * @date 2015-12-16 下午5:52:27
     * @param region
     * @return
     */
    private String getName(TrendRegion region, String name) {
        if (region.getPid() != null && region.getPid() > 0) {
            TrendRegion parent = regionMap.get(region.getPid());
            if (parent != null) {
                name = parent.getRegionName() + " " + name;
                return getName(parent, name);
            }
        }
        return name;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<TrendRegion> getRegionByPid(int pid) {
        Jedis jedis = null;
        List<TrendRegion> regions = null;
        try {
            jedis = jedisPoolManager.getResource();
            String jsonStr = JedisUtil.getObjectFromMapById(jedis, RegionConstant.CACHE_REGION_LIST_MAP, pid + "", String.class);
            if (jsonStr != null && !"".equals(jsonStr)) {
                regions = JsonUtil.getObject(jsonStr, List.class);
            } else {
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("pid", pid);
                regions = trendRegionDao.selectObjectList(param);
                Map<String, Object> regionListMap = new HashMap<String, Object>();
                regionListMap.put(pid + "", JsonUtil.getJsonString(regions));
                JedisUtil.putObjectToMap(jedis, RegionConstant.CACHE_REGION_LIST_MAP, pid + "", regionListMap);
            }
        } finally {
            jedisPoolManager.returnResourceObject(jedis);
        }
        return regions;
    }

    @Override
    public int delete(Integer regionId, Integer parentId) {
        int i = 0;
        // 校验是否有子地区，如果有禁止删除
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("pid", regionId);
        List<TrendRegion> sonRegions = trendRegionDao.selectObjectList(param);
        if (sonRegions != null && !sonRegions.isEmpty()) {
            i = 2;
            return i;
        }
        Map<String, Object> reqMap = new HashMap<String, Object>();
        reqMap.put("regionId", regionId);
        i = trendRegionDao.delete(reqMap);

        initRegion();
        return i;
    }

    @Override
    public String selectAllRegions(Integer parentId) {
        Jedis jedis = null;
        String regions = "";
        try {
            jedis = jedisPoolManager.getResource();
            regions = jedis.get("regions");
            if (regions == null || "".equals(regions)) {
                List<TrendRegion> regions1 = trendRegionDao.selectObjectList(null);
                Map<Integer, TrendRegion> regionMap = new HashMap<Integer, TrendRegion>();
                for (TrendRegion region : regions1) {
                    regionMap.put(region.getRegionId(), region);
                }
                for (TrendRegion region : regions1) {
                    TrendRegion parent = regionMap.get(region.getPid());
                    if (parent != null) {
                        parent.getSubMap().put(region.getRegionId(), region);
                    }
                }
                jedis.set("regions", JsonUtil.getJsonString(regionMap.get(3743)));
                regions = jedis.get("regions");
            }
        } finally {
            jedisPoolManager.returnResourceObject(jedis);
        }
        return regions;
    }

    @Override
    public String selectRegionFullName(Integer regionId) {
        Jedis jedis = null;
        String regions = "";
        try {
            jedis = jedisPoolManager.getResource();
            regions = JedisUtil.getObjectFromMapById(jedis, RegionConstant.CACHE_REGION_FULL_NAME_MAP, regionId + "", String.class);
            if (regions == null || "".equals(regions)) {
                String name = "";
                name = getName(regionId, name);
                regions = name;
                Map<String, Object> regionFullNameMap = new HashMap<String, Object>();
                regionFullNameMap.put(regionId + "", name);
                JedisUtil.putObjectToMap(jedis, RegionConstant.CACHE_REGION_FULL_NAME_MAP, regionId + "", regionFullNameMap);
            }
        } finally {
            jedisPoolManager.returnResourceObject(jedis);
        }
        return regions;
    }

    /**
     * 获取地区全称
     * 
     * @auther zhangzheng
     * @date 2016-1-7 上午11:25:05
     * @param regionId
     * @param name
     * @return
     */
    private String getName(Integer regionId, String name) {
        Map<String, Object> param = new HashMap<String, Object>();
        TrendRegion region = null;
        if (regionId != null) {
            param.put("regionId", regionId);
            region = trendRegionDao.selectObject(param);
            name = region.getRegionName();
            name = getParentName(region.getPid(), name);
        }
        return name;
    }

    private String getParentName(Integer pid, String name) {
        Map<String, Object> param = new HashMap<String, Object>();
        TrendRegion parent = null;
        if (pid != null) {
            param.put("regionId", pid);
            parent = trendRegionDao.selectObject(param);
            if (parent != null) {
                name = parent.getRegionName() + " " + name;
                getParentName(parent.getPid(), name);
            }
        }
        return name;
    }

    @Override
    public String selectRegionByParentId(Integer parentId) {
        Jedis jedis = null;
        String regions = "";
        try {
            jedis = jedisPoolManager.getResource();
            regions = JedisUtil.getObjectFromMapById(jedis, RegionConstant.CACHE_REGION_LIST_MAP, parentId + "", String.class);
            if (regions == null || !"".equals(regions)) {
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("pid", parentId);
                List<TrendRegion> list = trendRegionDao.selectObjectList(param);
                if (list != null && !list.isEmpty()) {
                    regions = JsonUtil.getJsonString(list);
                    Map<String, Object> regionListMap = new HashMap<String, Object>();
                    regionListMap.put(parentId + "", regions);
                    JedisUtil.putObjectToMap(jedis, RegionConstant.CACHE_REGION_LIST_MAP, parentId + "", regionListMap);
                }
            }
        } finally {
            jedisPoolManager.returnResourceObject(jedis);
        }
        return regions;
    }

}