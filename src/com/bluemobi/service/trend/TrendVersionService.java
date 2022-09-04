package com.bluemobi.service.trend;


import com.appcore.service.MybatisBaseService;
import com.bluemobi.po.trend.TrendVersion;

/**
 * 【版本管理】 服务类 接口
 * @author AutoCode 309444359@qq.com
 * @date 2015-07-17 11:22:43
 *
 */
public interface TrendVersionService extends MybatisBaseService{    
    
    /**
     * 修改版本信息
     * @author HeWeiwen
     * 2015-8-5
     * @param version
     */
    void updateVersion(TrendVersion version);
    
    /**
     * 根据Id修改版本状态
     * @author HeWeiwen
     * 2015-8-5
     * @param id
     * @return
     */
    TrendVersion publishVsersion(int id);


}

