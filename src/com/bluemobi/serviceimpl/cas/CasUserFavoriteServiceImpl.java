package com.bluemobi.serviceimpl.cas;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.cas.CasUserFavoriteDao;
import com.bluemobi.service.cas.CasUserFavoriteService;

/**
 * 【用户收藏】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-01-04 14:42:22
 * 
 */
@Service(value = "casUserFavoriteService")
public class CasUserFavoriteServiceImpl extends MybatisBaseServiceImpl implements CasUserFavoriteService {

    @Autowired
    private CasUserFavoriteDao casUserFavoriteDao;

    @Override
    public MyBatisBaseDao getDao() {
        return casUserFavoriteDao;
    }

    @Override
    public int deleteByIds(Map<String, Object> parameter) {
        return casUserFavoriteDao.deleteMultiple(parameter);
    }

    @Override
    public int countsByskuId(long skuId) {
        Map<String, Object> reqMap = new HashMap<String, Object>();
        reqMap.put("specificationid", skuId);
        return casUserFavoriteDao.countsByskuId(reqMap);
    }


}
