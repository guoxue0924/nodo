package com.bluemobi.util.alipay.util;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bluemobi.to.bts.OrderDetailTO;


/**
 * 快递Util
 * @author zhangw
 *
 */
public class KuaiDiUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(KuaiDiUtil.class);

	private static final String KEY="c4079ec7aa423b2a";
	/**
     * 获取物流信息
     * @param orderDetailTO 订单信息
     * @return returnContent 快递信息URL
     */
	public static String getKuaiDi(OrderDetailTO orderDetailTO) {
		LOGGER.debug("【获取物流信息开始】");
		String returnContent="null";
		if(orderDetailTO.getLogisticsCom() ==null || orderDetailTO.getLogisticsNumber()==null){
			return returnContent;
		}
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("http://www.kuaidi100.com/applyurl?key=").append(KEY);
			sb.append("&com=").append(orderDetailTO.getLogisticsCom());
			sb.append("&nu=").append(orderDetailTO.getLogisticsNumber());
			LOGGER.debug("【请求URL：】"+sb);
			URL url = new URL(sb.toString());
			URLConnection con = url.openConnection();
			con.setAllowUserInteraction(false);
			InputStream urlStream = url.openStream();
			byte b[] = new byte[10000];
			int numRead = urlStream.read(b);
			returnContent = new String(b, 0, numRead);
			while (numRead != -1){
			    numRead = urlStream.read(b);
			    if (numRead != -1){
			        // String newContent = new String(b, 0, numRead);
			        String newContent = new String(b, 0, numRead, "UTF-8");
			        returnContent += newContent;
			    }
			}
			urlStream.close();
		}catch (Exception e) {
			LOGGER.debug("【获取物流信息失败！】");
		}
		LOGGER.debug("【获取物流信息结束，返回值为：】"+returnContent);
		return returnContent;
	}
}
