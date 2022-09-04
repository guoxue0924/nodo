package com.bluemobi.service.trend;

import java.util.List;

import com.appcore.service.MybatisBaseService;
import com.bluemobi.po.trend.TrendRegion;

/**
 * 【】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-07-17 11:22:42
 * 
 */
public interface TrendRegionService extends MybatisBaseService {

    /**
     * 添加地区
     * 
     * @author HeWeiwen 2015-7-30
     * @param region
     */
    void insertRegion(TrendRegion region);

    /**
     * 修改地区
     * 
     * @author HeWeiwen 2015-7-30
     * @param region
     */
    void updateRegion(TrendRegion region);

    /**
     * 初始化地区信息，将地区信息按照搜索规则存入redis缓存中
     * 
     * @auther zhangzheng
     * @date 2015-12-16 下午2:58:18
     */
    void initRegion();

    /**
     * 根据父级 ID 获取子级地区
     * 
     * @auther zhangzheng
     * @date 2016-1-8 下午1:47:49
     * @param pid
     * @return
     */
    List<TrendRegion> getRegionByPid(int pid);

    /**
     * 删除地区，同时删除redis中的缓存信息
     * 
     * @auther zhangzheng
     * @date 2016-1-8 下午2:01:35
     * @param regionId
     * @param parentId
     * @return
     */
    int delete(Integer regionId, Integer parentId);

    /**
     * 查询所有地区(接口)
     * 
     * @auther zhangzheng
     * @date 2016-1-8 下午2:05:35
     * @param parentId
     * @return 返回所有地区信息的json格式
     */
    String selectAllRegions(Integer parentId);

    /**
     * 查询地区全称(接口)
     * 
     * @auther zhangzheng
     * @date 2016-1-8 下午2:10:24
     * @param regionId
     * @return
     */
    String selectRegionFullName(Integer regionId);

    /**
     * 根据父级id查询子集地区(接口)
     * 
     * @auther zhangzheng
     * @date 2016-1-8 下午2:13:16
     * @param parentId
     * @return 返回json字符串
     */
    String selectRegionByParentId(Integer parentId);

}
