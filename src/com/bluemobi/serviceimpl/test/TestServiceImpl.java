package com.bluemobi.serviceimpl.test;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.core.pool.ThreadPoolFactory;
import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.appcore.util.TimeUtil;
import com.bluemobi.dao.test.TestDao;
import com.bluemobi.po.test.Test;
import com.bluemobi.po.test.Ticket;
import com.bluemobi.service.test.TestService;

/**
 * 【】 服务类 实现类
 * @author AutoCode 309444359@qq.com
 * @date 2015-07-16 10:59:33
 *
 */
@Service(value="testService")
public class TestServiceImpl extends MybatisBaseServiceImpl implements TestService{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(TestServiceImpl.class);
    
    @Autowired
    private TestDao testDao;
    
    @Override
    public MyBatisBaseDao getDao() {
        return testDao;
    }

    private static Ticket ticket = null;
    
    static{
        ticket = new Ticket();
        ticket.setId(1);
        ticket.setName("G7303");
        ticket.setDate("2015-8-10");
        ticket.setCount(100);
    }
    
    /**
     * 买票，使用synchronized
     */
    @Override
    public String buyTicket(int ticketID) {
        
        //synchronized(ticket){
            String msg = "";
            if(ticket.getCount()>0){
                msg = "购票成功，您的座位号【"+ ticket.getCount() +"】";
                ticket.setCount(ticket.getCount()-1);
            }else{
                msg = "购票失败！车票已售完。";
            }
            return msg;
        //}
        
    }
    
    
    /**
     * 买票，走线程队列
     * @author haojian
     * @date 2015-11-30 上午11:37:43 
     * @param testService
     * @param ticketID
     * @return
     * @return String
     */
    @Override
    public String buyTicketbyThreadPool(int ticketID) {
        
        BuyTicketCallable task = new BuyTicketCallable(this,ticketID);
        //每个ticketID对应一趟车，假设需要开4个线程来购买几百个车次的车票
        int threadNum = ticketID%4;
        Future<String> future = ThreadPoolFactory.submit("BY_TICKET_THREAD_" + threadNum , task);
        String msg = null;
        try {
            msg = future.get();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return msg;
    }
    
    /**
     * Callable的实现类
     * @Description
     * @author haojian 309444359@qq.com
     * @date 2015-11-30 上午11:40:44 
     *
     */
    private static class BuyTicketCallable implements Callable<String>{
        
        private TestService testService;
        private int ticketID;
        public BuyTicketCallable(TestService testService, int ticketID){
            this.testService = testService;
            this.ticketID = ticketID;
        }

        @Override
        public String call() throws Exception {

            return testService.buyTicket(ticketID);
            
        }
    }
    

    
    @Override
    public Ticket getTicketInfo() {
        
        return ticket;
        
    }


    @Override
    public void testUpdate(Test test, long sleepTime) {
        System.out.println(Thread.currentThread()+" - " + TimeUtil.getFormatDate(new Date(), "yyyy-MM-dd hh:mm:ss") + " begin ... " + test.toString());
        getDao().update(test);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread()+" - " + TimeUtil.getFormatDate(new Date(), "yyyy-MM-dd hh:mm:ss") + " end ... " + test.toString());
    }


    @Override 
    public void testTransaction(Test test, boolean hasException) {
        
        LOGGER.info("开始插入数据...");
        
        testDao.insert(test);
        
        if(hasException){
            LOGGER.info("抛出异常...");
            throw new RuntimeException("测试事物异常");
        }
        
        LOGGER.info("方法结束...");
        
    }



    

}


