package com.bluemobi.controller.front.bts;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.appcore.page.Page;
import com.bluemobi.constant.BaseConstant;
import com.bluemobi.controller.AbstractAPIController;
import com.bluemobi.po.bts.BtsOrderRefund;
import com.bluemobi.po.trend.TrendAttachment;
import com.bluemobi.service.bts.BtsOrderItemService;
import com.bluemobi.service.bts.BtsOrderRefundService;
import com.bluemobi.service.trend.TrendAttachmentService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.to.bts.RefundPageTO;
import com.bluemobi.to.bts.RefundQueryTO;

/**
 * Web端退货单中心 
 * @author heweiwen
 * 2016-1-7 上午11:12:11
 */
@Controller
@RequestMapping("front/bts/refund")
public class FrontBtsRefundController extends AbstractAPIController {

	private static final Logger LOGGER = LoggerFactory.getLogger(FrontBtsRefundController.class);
	
    @Autowired
    private BtsOrderItemService btsOrderItemService;
    @Autowired
    private TrendAttachmentService trendAttachmentService;
    @Autowired
    private BtsOrderRefundService btsOrderRefundService;
    
    @InitBinder
    protected void initBinder(HttpServletRequest request,
            ServletRequestDataBinder binder) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor = new CustomDateEditor(df, true);
        binder.registerCustomEditor(Date.class, editor);
    }
    
    /**
     * 初始化实物交易订单页面
     * @author HeWW
     * 2016-1-11
     * @param model
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String userIndex(Model model, Integer status) {
        return "front/bts/refund.index";
    }
    
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Page<RefundPageTO> getPage(RefundQueryTO queryTO) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("key", queryTO.getKey());
        map.put("startDate", queryTO.getStartDate());
        map.put("endDate", queryTO.getEndDate());
    	map.put("searchType", queryTO.getSearchType()); 
    	map.put("userid", this.getUserid());
        Page<RefundPageTO> pages = btsOrderRefundService.page("frontPage","frontPageCount",map,
        		queryTO.getPageIndex(), queryTO.getPageSize());
        return pages;
    }
    
    /**
     * 订单详情页
     * getOrderDetail
     * 
     * @author kevin
     * @param model
     * @param orderId
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String getOrderDetail(Model model, int refundId) {
    	model.addAttribute("refund", btsOrderRefundService.getDetailInfo(refundId));
        return "front/bts/refund.detail";
    }
    
    /**
     * 退货页面
     * addRefund
     *  
     * @author kevin
     * @param model
     * @param itemId
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addRefund(Model model, long itemId) {
        model.addAttribute("item", btsOrderItemService.getItemForRefund(itemId));
        return "front/bts/refund.add";
    }
    
    /**
     * 保存退货信息
     * saveRefund
     * 
     * @author kevin
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO saveRefund(BtsOrderRefund refund, Long[] imageIds) {
    	refund.setIp(this.getAccessIp());
    	refund.setOperatorUserid(this.getUserid());
    	try {
    		btsOrderRefundService.saveRefund(refund, imageIds);
    		return ResultTO.newSuccessResultTO(null);
    	} catch (Exception e) {
    		e.printStackTrace();
    		LOGGER.error(e.getMessage());
    		return ResultTO.newFailResultTO("操作失败", null);
    	}
    }
    
    /**
     * 上传图片
     * uploadFile
     * 
     * @author kevin
     * @param files
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadFile(MultipartFile file) {
        Map<String, Object> uploadResultMap = null;
        Map<String, Object> resultMap = new HashMap<String, Object>();
        long attachmentId = 0l;
        try {
            uploadResultMap = uploadImage(new MultipartFile[]{file}, "/refund/");
            LOGGER.info("用户【{}】上传图片【{}】成功", new Object[] { this.getUserid(), file.getName() });
            uploadResultMap.put("userid", this.getUserid());
            uploadResultMap.put("filesize", file.getSize()); // 单文件上传，获取数组第0个大小即可
            uploadResultMap.put("fileType", Byte.valueOf("1"));
            attachmentId = trendAttachmentService.insertUploadFile(uploadResultMap, new TrendAttachment());
        } catch (Exception e) {
            LOGGER.error("用户【{}】上传图片失败。Exception:【{}】", new Object[] { this.getUserid(), e });
            resultMap.put("status", BaseConstant.STATUS_FAILURE);
            return resultMap;
        }
        resultMap.put("status", BaseConstant.STATUS_SUCCESS);
        resultMap.put("url", uploadResultMap.get("imageUrl") == null ? "" : uploadResultMap.get("imageUrl"));
        resultMap.put("attachmentId", attachmentId);
        return resultMap;
    }
    
}
