package com.bluemobi.serviceimpl.advert;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.advert.AdvertContentDao;
import com.bluemobi.service.advert.AdvertContentService;
import com.bluemobi.to.advert.AdvertDetailTO;

/**
 * 【】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-13 11:11:01
 * 
 */
@Service(value = "advertContentService")
public class AdvertContentServiceImpl extends MybatisBaseServiceImpl implements AdvertContentService {

    @Autowired
    private AdvertContentDao advertContentDao;

    @Override
    public MyBatisBaseDao getDao() {
        return advertContentDao;
    }

    @Override
    public AdvertDetailTO selectAdvertDetail(Map<String, Object> paramMap) {
        return advertContentDao.selectAdvertDetail(paramMap);
    }

    @Override
    public List<AdvertDetailTO> selectAdvertDetails(Map<String, Object> paramMap) {
        return advertContentDao.selectAdvertDetails(paramMap);
    }

}
