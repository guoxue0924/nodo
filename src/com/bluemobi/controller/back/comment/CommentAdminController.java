
package com.bluemobi.controller.back.comment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appcore.page.Page;
import com.bluemobi.controller.AbstractBackController;
import com.bluemobi.po.comment.CommentContent;
import com.bluemobi.po.trend.TrendAttachment;
import com.bluemobi.service.comment.CommentContentService;
import com.bluemobi.service.trend.TrendAttachmentService;
import com.bluemobi.to.ResultTO;

/**
 * 评论控制器逻辑处理
 * @author heweiwen
 * 2015-10-21 下午3:20:59
 */
@Controller
@RequestMapping("back/commentadmin")
public class CommentAdminController extends AbstractBackController{
    private static Log LOGGER = LogFactory.getLog(CommentAdminController.class);
    @Autowired
    private CommentContentService commentContentService;
    @Autowired
    private TrendAttachmentService trendAttachmentService;
    
    /**
     * 初始化评论管理页面
     */
    @RequestMapping("index")
    public String index(Model model,HttpServletRequest req) {
        //加载公共数据
        initIndex(model);
        
        return "back/comment/comment.index";
    }
    
    @RequestMapping("trash")
    public String indexTrash(Model model,HttpServletRequest req) {
        //加载公共数据
        initIndex(model);
        
        return "back/comment/trash.index";
    }
    
    /**
     * 查看评论详情页面
     * @author HeWeiwen
     * 2015-10-21
     * @param model
     * @param req
     * @param id
     * @return
     */
    @RequestMapping("index/detail")
    public String indexDetail(Model model,HttpServletRequest req,int id) {
        //1，根据评论Id查询评论对象
        Map<String,Object> reqMap = new HashMap<String, Object>();
        reqMap.put("id", id);
        CommentContent commentContent = commentContentService.selectObject(reqMap);
        //2，根据评论Id获得附件对象集合
        Map<String,Object> reqCommentAttachmentMap = new HashMap<String, Object>();
        reqCommentAttachmentMap.put("mainId", id);
        reqCommentAttachmentMap.put("type", 1);//晒单图片附件：1
        List<TrendAttachment> attachment = trendAttachmentService.selectTrendAttachmentListByMainId(reqCommentAttachmentMap);
        
        //加载公共数据
        initIndex(model);
        model.addAttribute("comment", commentContent);
        model.addAttribute("attachment", attachment);
        
        return "back/comment/comment.detail";
    
    }
    

   	/**
   	 * 分页查询评论列表信息
   	 * @author HeWeiwen
   	 * 2015-11-2
   	 * @param key
   	 * @param pageSize
   	 * @param pageIndex
   	 * @return
   	 */
    @RequestMapping(value = "page", method = RequestMethod.POST)
   	@ResponseBody
   	public Map<String, Object> pagefind(String key,int pageSize, int pageIndex) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (null != key && !"".equals(key)) {
            map.put("content", key);
        }
        //查询父级评论（第一层（pid：0）评论）
        map.put("pid", 0);
        map.put("isDel",0);
        Page<Map<String, Object>> pages = commentContentService.page("pageCommentContent", "pageCommentContentCount", map, pageIndex, pageSize);
    	
    	Map<String, Object> mapResult = new HashMap<String, Object>();
    	mapResult.put("data", pages.getData());
    	mapResult.put("count", pages.getCount());
   		return mapResult;
   	}
    
    /**
     * 分页查询评论回收站信息
     * @author HeWeiwen
     * 2015-11-2
     * @param key
     * @param pageSize
     * @param pageIndex
     * @return
     */
    @RequestMapping(value = "trash/page", method = RequestMethod.POST)
   	@ResponseBody
   	public Map<String, Object> pageTrash(String key,int pageSize, int pageIndex) {
    	
        Map<String, Object> map = new HashMap<String, Object>();
        if (null != key && !"".equals(key)) {
            map.put("content", key);
        }
        //查询逻辑删除的评论（isDel：1）
        map.put("isDel", 1);
        Page<Map<String, Object>> pages = commentContentService.page("pageCommentContent", "pageCommentContentCount", map, pageIndex, pageSize); 		

    	//将数据对象转换页面显示对象数据
    	Collection<Map<String, Object>> datas = pages.getData();
    	Collection<Map<String, Object>> dataReture = new ArrayList<Map<String,Object>>();
    	for (Map<String, Object> d:datas) {
    		Map<String, Object> sturtsmap = new HashMap<String, Object>();
    		sturtsmap = d;
    		//转换对象
    		Date ctime = (Date) d.get("ctime");
    		sturtsmap.put("ctime", ctime.toString());
    		dataReture.add(sturtsmap);
		}
    	Map<String, Object> mapResult = new HashMap<String, Object>();
        mapResult.put("data", dataReture);
        mapResult.put("count", pages.getCount());
        return mapResult;
   	}
       
    /**
     * 修改评论前台显示
     * @author HeWeiwen
     * 2015-4-27
     * @param storeContents
     * @param result
     * @return
     */
    @RequestMapping(value = "index/publish" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTO publishComment(int id) {
    	try {
    	    Map<String,Object> reqMap = new HashMap<String, Object>();
            reqMap.put("id", id);
    		//发布(是否在前台显示。1：是；0：否；)
    		CommentContent commentContent = commentContentService.selectObject(reqMap);
    		if (commentContent.getStatus() == 1) {
    		    commentContent.setStatus((byte)0);
            }else {
                commentContent.setStatus((byte)1);
            }
    		commentContentService.update(commentContent);
		} catch (Exception e) {
			LOGGER.error("Exception:"+e);
	    	return ResultTO.newFailResultTO("修改失败",null);
		}
    	return ResultTO.newSuccessResultTO("修改成功", null);
    }
    /**
     * 删除评论
     * @author HeWeiwen
     * 2015-4-27
     * @param storeContents
     * @param result
     * @return
     */
    @RequestMapping(value = "index/delete" , method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteComment(String id) {
    	try {
    		//逻辑删除多条数据
    		commentContentService.deleteLogic(id);
		} catch (Exception e) {
		    LOGGER.error("Exception:"+e);
	    	return ResultTO.newFailResultTO("修改失败", null);
		}
    	return ResultTO.newSuccessResultTO("修改成功", null);
    }
	
}

