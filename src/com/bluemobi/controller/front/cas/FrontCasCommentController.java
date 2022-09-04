package com.bluemobi.controller.front.cas;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.appcore.page.Page;
import com.bluemobi.constant.BaseConstant;
import com.bluemobi.constant.CommentConstant;
import com.bluemobi.constant.OrderConstant;
import com.bluemobi.controller.AbstractAPIController;
import com.bluemobi.po.trend.TrendAttachment;
import com.bluemobi.service.bts.BtsOrderItemService;
import com.bluemobi.service.bts.BtsOrderService;
import com.bluemobi.service.comment.CommentAttachmentService;
import com.bluemobi.service.comment.CommentContentService;
import com.bluemobi.service.trend.TrendAttachmentService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.to.comment.CommentDetailTO;
import com.bluemobi.to.comment.ItemCommentTO;
import com.bluemobi.to.comment.OrderCommentTO;



/**
 * 【用户交易评价/晒单】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-01-04 14:42:22
 * 
 */
@Controller
@RequestMapping("front/cas/comment")
public class FrontCasCommentController extends AbstractAPIController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(FrontCasCommentController.class);
    
    @Autowired
    private CommentContentService commentContentService;
    @Autowired
    private BtsOrderService btsOrderService;
    @Autowired
    private BtsOrderItemService btsOrderItemService;
    @Autowired
    private TrendAttachmentService  trendAttachmentService;
    @Autowired
    private CommentAttachmentService commentAttachmentService;
    
    /**
     * 初始化交易评论/晒单页面
     * @author HeWW
     * 2016-1-18
     * @param model
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String comment(Model model) {
        model.addAttribute("loggedInUser", getCasUser());
        return "front/cas/comment.index";
    }
    
    /**
     * 初始化晒单页面
     * @author HeWW
     * 2016-1-21
     * @param model
     * @return
     */
    @RequestMapping(value = "show", method = RequestMethod.GET)
    public String commentShoworder(Integer id, Model model) {
        CommentDetailTO detail = commentContentService.getCommentDetail(id);
        model.addAttribute("detail", detail);
        return "front/cas/comment.show";
    }
    
    /**
     * 保存晒单结果
     * saveCommentShow
     * 
     * @author kevin
     * @param model
     * @param id
     * @param imageIds
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = "show", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO saveCommentShow(Model model, Integer id, Long[] imageIds) {
    	try {
    		commentAttachmentService.saveCommentAttachment(id, imageIds, (long)this.getUserid());
    		return ResultTO.newSuccessResultTO(null);
    	} catch (Exception e) {
    		return ResultTO.newFailResultTO("操作失败", null);
    	}
    }
    
    /**
     * 分页查询交易评论
     * @author HeWW
     * 2016-1-20
     * @param pageSize
     * @param pageIndex
     * @return
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    public String pageComment(Model model, int pageSize, int pageIndex) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userid", getCasUser().getUserid());
    	Page<CommentDetailTO> pages = commentContentService.page(map, pageIndex, pageSize);
    	model.addAttribute("page", pages);
        return "front/cas/comment.list";
    }
    
    /**
     * 进入评价页面
     * goComment
     * 
     * @author kevin
     * @param model
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String goComment(Model model, Long orderId) {
        model.addAttribute("loggedInUser", getCasUser());
        model.addAttribute("order", btsOrderService.getDetailInfo(orderId));
        return "front/cas/comment.edit";
    }
    
    /**
     * 保存评价信息
     * saveComment
     * 
     * @author kevin
     * @param model
     * @param comments
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO saveComment(Model model, OrderCommentTO commentTO) {
        try {
        	commentContentService.addComment(commentTO, this.getUserid(), this.getAccessIp());
        	Map<String, Object> param = new HashMap<String, Object>();
        	param.put("isComment", (byte)1);
        	for (ItemCommentTO itemTO : commentTO.getItemComments()) {
        		param.put("itemId", itemTO.getItemId());
        		btsOrderItemService.update(param);
			}
        	return ResultTO.newSuccessResultTO(null);
        } catch (Exception e) {
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
            uploadResultMap = uploadImage(new MultipartFile[]{file}, CommentConstant.COMMENT_IMAGE);
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
