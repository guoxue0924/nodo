package com.bluemobi.service.bts;

import com.appcore.service.MybatisBaseService;
import com.bluemobi.po.bts.BtsOrderRefund;
import com.bluemobi.to.bts.RefundDetailTO;

/**
 * 【退货流程日志表】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-11-17 13:27:05
 * 
 */
public interface BtsOrderRefundService extends MybatisBaseService {

	/** 
	 * getDetailInfo
	 * 
	 * @author kevin
	 * @param refundId
	 * @return 
	 * @since JDK 7 
	 */  
	RefundDetailTO getDetailInfo(int refundId);

	/** 
	 * saveRefund
	 * 
	 * @author kevin
	 * @param refund
	 * @param imageIds 
	 * @since JDK 7 
	 */  
	void saveRefund(BtsOrderRefund refund, Long[] imageIds);

}
