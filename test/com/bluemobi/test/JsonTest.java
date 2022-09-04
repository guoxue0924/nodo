package com.bluemobi.test;

import com.appcore.util.JsonUtil;
import com.bluemobi.apito.bts.CreateOrderRequestTO;
import com.bluemobi.apito.cas.RegisterResponseTO;

public class JsonTest {

    /**
     * @author haojian
     * @date 2016-1-26 下午1:03:58 
     * @param args
     * @return void
     */

    public static void main(String[] args) {
        
       /* CreateOrderRequestTO to = new CreateOrderRequestTO();
        to.setConsigneeId(11);
        to.setPayType(1);
        List<Integer> list = new ArrayList<Integer>();
        list.add(35);
        list.add(92);
        to.setCartId(list);*/
        
        RegisterResponseTO to = new RegisterResponseTO();
        
        String s = JsonUtil.getJsonString(to);
        System.out.println(s);
        
        System.out.println(JsonUtil.getObject(s, RegisterResponseTO.class));
        
    }

}
