package com.bluemobi.serviceimpl.trend;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.trend.TrendVersionDao;
import com.bluemobi.po.trend.TrendVersion;
import com.bluemobi.service.trend.TrendVersionService;

/**
 * 【版本管理】 服务类 实现类
 * @author AutoCode 309444359@qq.com
 * @date 2015-07-17 11:22:43
 *
 */
@Service(value="trendVersionService")
public class TrendVersionServiceImpl extends MybatisBaseServiceImpl implements TrendVersionService{
    
    @Autowired
    private TrendVersionDao trendVersionDao;
    
    @Override
    public MyBatisBaseDao getDao() {
        return trendVersionDao;
    }

    public void updateVersion(TrendVersion version) {
        version.setMtime(new Date());
        trendVersionDao.update(version);
    }

    public TrendVersion publishVsersion(int id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        TrendVersion versionResult = (TrendVersion) trendVersionDao.selectObjectList(map).get(0);
        if (versionResult.getStatus() == 0) {
            versionResult.setStatus((byte)1);
        }else if((byte)versionResult.getStatus() ==1){
            versionResult.setStatus((byte)0);
        }
        trendVersionDao.update(versionResult);
        
        return versionResult;
    }
    
    

}


