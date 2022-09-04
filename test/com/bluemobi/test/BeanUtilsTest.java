package com.bluemobi.test;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class BeanUtilsTest {

    /**
     * 
     * @author haojian
     * @date 2015-11-2 下午2:24:11
     * @param args
     * @throws Exception
     * @return void
     */
    public static void main(String[] args) throws Exception {
       
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", 101); 
        map.put("str", "test-101");
        
        TestBean bean = new TestBean();

        BeanUtils.copyProperties(bean,map);
        
        System.out.println("-----" + bean);
       

    }

}
