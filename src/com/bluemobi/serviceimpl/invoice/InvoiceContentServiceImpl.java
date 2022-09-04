package com.bluemobi.serviceimpl.invoice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.invoice.InvoiceContentDao;
import com.bluemobi.service.invoice.InvoiceContentService;

/**
 * 【发票表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-12-11 15:44:05
 * 
 */
@Service(value = "invoiceContentService")
public class InvoiceContentServiceImpl extends MybatisBaseServiceImpl implements InvoiceContentService {

    @Autowired
    private InvoiceContentDao invoiceContentDao;

    @Override
    public MyBatisBaseDao getDao() {
        return invoiceContentDao;
    }

}
