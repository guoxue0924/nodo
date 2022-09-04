package com.bluemobi.serviceimpl.cas;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.cas.CasConsigneeDao;
import com.bluemobi.po.cas.CasConsignee;
import com.bluemobi.service.cas.CasConsigneeService;

/**
 * 【】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-11-17 17:01:42
 * 
 */
@Service(value = "btsConsigneeService")
public class CasConsigneeServiceImpl extends MybatisBaseServiceImpl implements CasConsigneeService {

    @Autowired
    private CasConsigneeDao casConsigneeDao;

    @Override
    public MyBatisBaseDao getDao() {
        return casConsigneeDao;
    }

    @Override
    public CasConsignee getConsigneeForOrder(int userId, int consigneeId) {
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("userid", userId);
        parameter.put("consigneeId", consigneeId);
        return (CasConsignee) (this.casConsigneeDao.selectObjectList(parameter).size()>0?this.casConsigneeDao.selectObjectList(parameter).get(0):null);
    }

}
