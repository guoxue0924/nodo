package com.bluemobi.serviceimpl.bts;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.bts.BtsOrderItemDao;
import com.bluemobi.service.bts.BtsOrderItemService;

/**
 * 【订单的商品表(即订单详情表)】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-11-17 13:27:05
 * 
 */
@Service(value = "btsOrderItemService")
public class BtsOrderItemServiceImpl extends MybatisBaseServiceImpl implements BtsOrderItemService {

    @Autowired
    private BtsOrderItemDao btsOrderItemDao;

    @Override
    public MyBatisBaseDao getDao() {
        return btsOrderItemDao;
    }

	/** 
	 * @see com.bluemobi.service.bts.BtsOrderItemService#getItemForRefund(long) 
	 */  
	@Override
	public Map<String, Object> getItemForRefund(long itemId) {
		return this.btsOrderItemDao.getItemForRefund(itemId);
	}

}
