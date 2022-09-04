package com.bluemobi.util.alipay.config;
  
import java.util.Properties;  
  
//import com.test.util.PropertiesUtil;  
  
public class AlipayConfig {  
      
    private static AlipayConfig  alconfig = null;  
    private AlipayConfig(){  
          
    }  
     public static AlipayConfig getInstance(){  
          
        if(alconfig==null){  
            alconfig = new AlipayConfig();  
        }  
          
        return alconfig;  
    }  
      
     // 如何获取安全校验码和合作身份者ID  
     // 1.访问支付宝商户服务中心(b.alipay.com)，然后用您的签约支付宝账号登陆.  
     // 2.访问“技术服务”→“下载技术集成文档”（https://b.alipay.com/support/helperApply.htm?action=selfIntegration）  
     // 3.在“自助集成帮助”中，点击“合作者身份(Partner ID)查询”、“安全校验码(Key)查询”  
   
     // ↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓  
     // 合作身份者ID，以2088开头由16位纯数字组成的字符串  
    public static String it_b_pay = "1h";  
  
//    public static String partner = "2088601003079118";  
    public static String partner = "2088011577655537";
    
    // 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
    public static String seller_id = partner;
  
    // MD5密钥，安全检验码，由数字和字母组成的32位字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
//    public static String key = "zxcdvxgksaam2zjrmv5cv0p4jqesaioh";  
    public static String key = "zbv6iauwk9s4m5sg52qd7w4djmfgezqx";
    
    // notify_url 交易过程中服务器通知的页面 要用 http://格式的完整路径，不允许加?id=123这类自定义参数  
//    public static String notify_url = "http://shop.jjzxmall.com/front/create_direct_pay_by_user-JAVA-UTF-8/notify_url.jsp";
//    public static String notify_url = "http://localhost:8080/nodo/front/index/index";
    public static String notify_url = "http://shop.jjzxmall.com/front/bts/order/pay/goDetail";
    
    public static String service = "create_direct_pay_by_user";  
  
 	// 签约支付宝账号或卖家收款支付宝帐户  
//    public static String seller_email = "zhangw@yahoo.com.cn"; 
    public static String seller_email = "13932138193"; 
    // 读配置文件 
  
  
  
  	 // 付完款后跳转的页面 要用 http://格式的完整路径，不允许加?id=123这类自定义参数  
     // return_url的域名不能写成http://localhost/js_jsp_utf8/return_url.jsp，否则会导致return_url执行无效  
     //public static String return_url = "http:www.xxx.com/projectName/alipayTrade.action";  
   
     // 网站商品的展示地址，不允许加?id=123这类自定义参数  
    public static String show_url = "http://www.alipay.com";  
  
    // 收款方名称，如：公司名称、网站名称、收款人姓名等 
    public static String mainname = "收款方名称";  
    // ↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑  
  
    // 字符编码格式 目前支持 gbk 或 utf-8  
    public static String input_charset = "UTF-8";  
  
    // 签名方式 不需修改
    public static String sign_type = "MD5";  
    
    // 支付类型 ，无需修改
 	public static String payment_type = "1";
  
    // 访问模式,根据自己的服务器是否支持ssl访问，若支持请选择https；若不支持请选择http   
    public static String transport = "http";  
    
    // 调试用，创建TXT日志文件夹路径，见AlipayCore.java类中的logResult(String sWord)打印方法。
// 	public static String log_path = "C:\\";
 	public static String log_path = "/usr/local/tomcat/logs/";
 	
	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	//public static String return_url = "http://shop.jjzxmall.com/front/create_direct_pay_by_user-JAVA-UTF-8/return_url.jsp";
//	public static String return_url = "http://localhost:8080/nodo/front/bts/order/pay/goDetail";
 	public static String return_url = "http://shop.jjzxmall.com/front/bts/order/pay/goDetail";

    
    // 防钓鱼时间戳  若要使用请调用类文件submit中的query_timestamp函数
 	public static String anti_phishing_key = "AlipaySubmit.query_timestamp()";
 	
 	// 客户端的IP地址 非局域网的外网IP地址，如：221.0.0.1
// 	public static String exter_invoke_ip = "10.58.150.50";
// 	public static String exter_invoke_ip = "shop.jjzxmall.com";
 	public static String exter_invoke_ip = "http://shop.jjzxmall.com/front/bts/order.list.jsp";
} 