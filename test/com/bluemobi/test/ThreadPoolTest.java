package com.bluemobi.test;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.appcore.core.pool.ThreadPoolFactory;
import com.appcore.util.TimeUtil;
import com.bluemobi.to.ResultTO;

public class ThreadPoolTest {
    
    public ResultTO service(int i,String s){
        
        String data = s+"_"+i;
        return ResultTO.newFailResultTO(s, data);
        
    }
    
    
    public static void main(String[] args){
        
        MyTask2 task = new MyTask2(3000, "haoj");
        
        
        Future<ResultTO> future = ThreadPoolFactory.submit("TEST_THREAD_POOL", task);
        
        try {
            ResultTO resultTO = future.get();
            
            log("返回结果="+resultTO);
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        
    }
    
    
    public static class MyTask implements Callable<ResultTO>{
        
        private int i;
        private String s;
        
        public MyTask(int i,String s){
            this.i = i;
            this.s = s;
        }

        @Override
        public ResultTO call() throws Exception {
            
            log("开始睡眠...");
            Thread.sleep(i);
            log("睡眠结束...");
            
            String data = s+"_"+i;
            return ResultTO.newFailResultTO(s, data);
            
        }
        
    }
    
    
    public static class MyTask2 implements Runnable{
        
        private int i;
        private String s;
        
        public MyTask2(int i,String s){
            this.i = i;
            this.s = s;
        }

        @Override
        public void run() {
            
            log("开始睡眠...");
            try {
                Thread.sleep(i);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            log("睡眠结束...");
            
            String data = s+"_"+i;
            
        }
        
    }
    
    
    public static void log(String log){
        
        System.out.println(Thread.currentThread()+" - " + TimeUtil.getFormatDate(new Date(), "yyyy-MM-dd hh:mm:ss") + log);
        
    }

}
