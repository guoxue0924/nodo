package com.bluemobi.serviceimpl.bts;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.bts.BtsOrderRefundDao;
import com.bluemobi.po.bts.BtsOrderRefund;
import com.bluemobi.service.bts.BtsOrderAttachmentService;
import com.bluemobi.service.bts.BtsOrderRefundService;
import com.bluemobi.to.bts.RefundDetailTO;

/**
 * 【退货流程日志表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-11-17 13:27:05
 * 
 */
@Service(value = "btsOrderRefundService")
public class BtsOrderRefundServiceImpl extends MybatisBaseServiceImpl implements BtsOrderRefundService {

    @Autowired
    private BtsOrderRefundDao btsOrderRefundDao;
    
    @Autowired
    private BtsOrderAttachmentService btsOrderAttachmentService;

    @Override
    public MyBatisBaseDao getDao() {
        return btsOrderRefundDao;
    }

	/** 
	 * @see com.bluemobi.service.bts.BtsOrderRefundService#getDetailInfo(java.lang.Long) 
	 */  
	@Override
	public RefundDetailTO getDetailInfo(int refundId) {
		return btsOrderRefundDao.getDetailInfo(refundId);
	}

	/** 
	 * @see com.bluemobi.service.bts.BtsOrderRefundService#saveRefund(com.bluemobi.po.bts.BtsOrderRefund, java.lang.Long[]) 
	 */  
	@Override
	public void saveRefund(BtsOrderRefund refund, Long[] imageIds) {
		refund.setStatus((byte) 2);
		refund.setCtime(Calendar.getInstance().getTime());
		refund.setRefundSn(generateRefundSn());
		this.btsOrderRefundDao.insert(refund);
		if(imageIds.length > 0) {
			btsOrderAttachmentService.saveRefundAttachment(refund.getRefundId(), imageIds);
		}
	}
	
	private String generateRefundSn() {
		StringBuffer refundSn = new StringBuffer();
		refundSn.append("400").append(System.currentTimeMillis());
		return refundSn.toString();
	}

}
