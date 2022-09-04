package com.bluemobi.service.test;

import com.appcore.service.MybatisBaseService;
import com.bluemobi.po.test.Test;
import com.bluemobi.po.test.Ticket;



/**
 * 【测试】 服务类 接口
 * @author AutoCode 309444359@qq.com
 * @date 2015-07-16 10:59:33
 *
 */
public interface TestService extends MybatisBaseService{    
    
    /**买票*/
    String buyTicket(int ticketID);
    
    /**
     * 买票，走线程队列
     * @author haojian
     * @date 2015-11-30 上午11:38:39 
     * @param testService
     * @param ticketID
     * @return
     * @return String
     */
    String buyTicketbyThreadPool(int ticketID);
    
    /**获取车票信息*/
    Ticket getTicketInfo();

    
    void testUpdate(Test test,long sleepTime);
    
    /**
     * 测试事物
     * @author haojian
     * @date 2015-12-1 上午10:26:19 
     * @param test
     * @param times循环次数
     * @return void
     */
    void testTransaction(Test test, boolean hasException);

}

