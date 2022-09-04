package com.bluemobi.serviceimpl.store;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.appcore.util.TimeUtil;
import com.bluemobi.dao.cas.CasUserDao;
import com.bluemobi.dao.store.StoreContentDao;
import com.bluemobi.po.cas.CasUser;
import com.bluemobi.po.store.StoreContent;
import com.bluemobi.service.store.StoreContentService;
import com.bluemobi.to.store.StoreAndUserTO;

/**
 * 【已签约商户表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-09-15 17:24:48
 * 
 */
@Service(value = "storeContentService")
public class StoreContentServiceImpl extends MybatisBaseServiceImpl implements StoreContentService {

    @Autowired
    private StoreContentDao storeContentDao;
    @Autowired
    private CasUserDao casUserDao;

    @Override
    public MyBatisBaseDao getDao() {
        return storeContentDao;
    }

    @Override
    public Map<String, Object> selectMapStoreInfo(Map<String, Object> parameter) {
        return storeContentDao.selectMapStoreInfo(parameter);
    }

    @Override
    public int insert(Date date, StoreAndUserTO storeAndUserTO) throws IllegalAccessException, InvocationTargetException {
        CasUser user = new CasUser();
        StoreContent store = new StoreContent();
        BeanUtils.copyProperties(user, storeAndUserTO);
        BeanUtils.copyProperties(store, storeAndUserTO);
        // 新增用户
        user.setMtime(date);
        user.setStatus((byte) 0);
        casUserDao.insert(user);
        // 新增店铺
        store.setMtime(date);
        store.setUserid(user.getUserid());
        store.setSigningTimeStart(TimeUtil.getDateByFormatDate(storeAndUserTO.getSigningTimeStartStr(), "yyyy-MM-dd"));
        store.setSigningTimeEnd(TimeUtil.getDateByFormatDate(storeAndUserTO.getSigningTimeEndStr(), "yyyy-MM-dd"));
        storeContentDao.insert(store);
        return store.getStoreId();
    }

}
