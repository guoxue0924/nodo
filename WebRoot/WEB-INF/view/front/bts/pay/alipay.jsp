<%@page import="com.bluemobi.util.alipay.config.AlipayConfig"%>  
<%@page import="com.bluemobi.util.alipay.util.AlipayService"%> 
<%  
    /*  
    功能：设置商品有关信息（入口页）  
     *详细：该页面是接口入口页面，生成支付时的URL  
     *版本：3.1  
     *日期：2010-11-01  
     *说明：  
     *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。  
     *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。  
      
     *************************注意*****************  
    如果您在接口集成过程中遇到问题，  
    您可以到商户服务中心（https://b.alipay.com/support/helperApply.htm?action=consultationApply），提交申请集成协助，我们会有专业的技术工程师主动联系您协助解决，  
    您也可以到支付宝论坛（http://club.alipay.com/read-htm-tid-8681712.html）寻找相关解决方案  
    要传递的参数要么不允许为空，要么就不要出现在数组与隐藏控件或URL链接里。  
     **********************************************  
     */  
%>  
<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
    <head>  
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
        <title>支付宝 - 网上支付 安全快速！</title>  
        <link rel="SHORTCUT ICON" href="favicon.ico">  
<meta name="keywords" content="" />  
<meta name="description" content="" />  
<style type="text/css">  
.font_content{  
    font-family:"宋体";  
    font-size:14px;  
    color:#FF6600;  
}  
.font_title{  
    font-family:"宋体";  
    font-size:16px;  
    color:#FF0000;  
    font-weight:bold;  
}  
table{  
    border: 1px solid #CCCCCC;  
}  
        </style>  
        <script type="text/javascript">  
          var _gaq = _gaq || [];  
          _gaq.push(['_setAccount', 'UA-25469955-1']);  
          _gaq.push(['_trackPageview']);  
          
          
          (function() {  
            var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;  
            ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';  
            var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);  
          })();  
    </script>  
    </head>  
    <%  
            //request.setCharacterEncoding("UTF-8");  
            //AlipyConfig.java中配置信息（不可以修改）  
            String input_charset = AlipayConfig.getInstance().input_charset;  
            String sign_type = AlipayConfig.getInstance().sign_type;  
            String seller_email = AlipayConfig.getInstance().seller_email;  
            String partner = AlipayConfig.getInstance().partner;  
            String key = AlipayConfig.getInstance().key;  
  
            String show_url = AlipayConfig.getInstance().show_url;  
            String notify_url = AlipayConfig.getInstance().notify_url;  
            String return_url = AlipayConfig.getInstance().return_url;  
            String it_b_pay = AlipayConfig.getInstance().it_b_pay;  
              
            ///////////////////////////////////////////////////////////////////////////////////  
              
            //以下参数是需要通过下单时的订单数据传入进来获得  
            //必填参数  
            String out_trade_no = (String)request.getAttribute("out_trade_no");//请与贵网站订单系统中的唯一订单号匹配  
            //订单名称，显示在支付宝收银台里的“商品名称”里，显示在支付宝的交易管理的“商品名称”的列表里。  
            String subject = (String)request.getAttribute("subject");  
            //订单描述、订单详细、订单备注，显示在支付宝收银台里的“商品描述”里  
            String body = (String)request.getAttribute("body");  
            //订单总金额，显示在支付宝收银台里的“应付总额”里  
            String total_fee = (String)request.getAttribute("total_fee");  
              
            //扩展功能参数——默认支付方式  
           // String pay_mode = request.getParameter("pay_bank");  
            String paymethod = "";        //默认支付方式，四个值可选：bankPay(网银); cartoon(卡通); directPay(余额); CASH(网点支付)  
            String defaultbank = "";    //默认网银代号，代号列表见http://club.alipay.com/read.php?tid=8681379  
            /*if(pay_mode.equals("directPay")){  
                paymethod = "directPay";  
            }  
            else{  
                paymethod = "bankPay";  
                defaultbank = pay_mode;  
            }*/  
              
            //扩展功能参数——防钓鱼  
            //请慎重选择是否开启防钓鱼功能  
            //exter_invoke_ip、anti_phishing_key一旦被设置过，那么它们就会成为必填参数  
            //开启防钓鱼功能后，服务器、本机电脑必须支持远程XML解析，请配置好该环境。  
            //建议使用POST方式请求数据  
            String anti_phishing_key  = "";                //防钓鱼时间戳  
            String exter_invoke_ip= "";                    //获取客户端的IP地址，建议：编写获取客户端IP地址的程序  
            //如：  
            //anti_phishing_key = AlipayFunction.query_timestamp(partner);    //获取防钓鱼时间戳函数  
            //exter_invoke_ip = "202.1.1.1";  
              
            //扩展功能参数——其他  
            String extra_common_param = "";                //自定义参数，可存放任何内容（除=、&等特殊字符外），不会显示在页面上  
            String buyer_email = "137672927";                    //默认买家支付宝账号  
            String extend_param = "";  
              
              
            //扩展功能参数——分润(若要使用，请按照注释要求的格式赋值)  
            String royalty_type = "";                    //提成类型，该值为固定值：10，不需要修改  
            String royalty_parameters ="";  
            //提成信息集，与需要结合商户网站自身情况动态获取每笔交易的各分润收款账号、各分润金额、各分润说明。最多只能设置10条  
            //各分润金额的总和须小于等于total_fee  
            //提成信息集格式为：收款方Email_1^金额1^备注1|收款方Email_2^金额2^备注2  
            //如：  
            //royalty_type = "10"  
            //royalty_parameters    = "111@126.com^0.01^分润备注一|222@126.com^0.01^分润备注二"  
              
              
            //之前设置的1h将返回  
            //错误描述: 抱歉，商户没有开通自定义超时权限，请联系您的商家。  
            //错误代码: SELF_TIMEOUT_NOT_SUPPORT  
              
            it_b_pay="";  
              
            //构造函数，生成请求URL  
            String sHtmlText = AlipayService.BuildForm(partner,seller_email,return_url,notify_url,show_url,out_trade_no,  
            subject,body,total_fee,paymethod,defaultbank,anti_phishing_key,exter_invoke_ip,extra_common_param,buyer_email,  
            royalty_type,royalty_parameters,input_charset,key,sign_type,it_b_pay);  
            
           System.out.print(sHtmlText);
    %>  
  
    <body>  
        <table align="center" width="350" cellpadding="5" cellspacing="0">  
            <tr>  
                <td align="center" class="font_title" colspan="2">订单确认</td>  
            </tr>  
            <tr>  
                <td class="font_content" align="right">订单号：</td>  
                <td class="font_content" align="left"><%=out_trade_no%></td>  
            </tr>  
            <tr>  
                <td class="font_content" align="right">付款总金额：</td>  
                <td class="font_content" align="left"><%=total_fee%></td>  
            </tr>  
            <tr>  
                <td align="center" colspan="2"><%=sHtmlText%></td>  
            </tr>  
        </table>  
    </body>  
</html>  