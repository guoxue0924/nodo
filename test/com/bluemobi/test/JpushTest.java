package com.bluemobi.test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cn.jpush.api.push.model.Platform;

import com.bluemobi.push.jpush.JPushUtil;

public class JpushTest {

	/**
	 * @author haojian
	 * @date 2015-10-16 下午5:48:14 
	 * @param args
	 * @return void
	 */
	public static void main(String[] args){
		
		Platform platform = Platform.all();
		String tag = "hao - tag"; 
		String alias = "hao - alias";
		String alert = "hao - alert df " + new Date();
		String title = "hao - title";
		Map<String,String> extras = new HashMap<String, String>();
		extras.put("hao - extras - key1", "hao - extras - value1");
		extras.put("hao - extras - key2", "hao - extras - value2");
		extras.put("hao - extras - key3", "hao - extras - value3");
		
		JPushUtil.pushAll(alert, extras);
		
		System.out.println("--over--");
		
	}
}
