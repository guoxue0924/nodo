/**
 * Project Name:nodo 
 * File Name:ArticleContentController.java 
 * Package Name:com.bluemobi.controller.back.article 
 * Date:2016年2月19日下午1:55:39 
 */
package com.bluemobi.controller.back.advert;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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
import com.bluemobi.constant.CouponConstant;
import com.bluemobi.controller.AbstractBackController;
import com.bluemobi.po.advert.AdvertFrendlink;
import com.bluemobi.po.page.PageContent;
import com.bluemobi.service.advert.AdvertFrendlinkService;
import com.bluemobi.service.page.PageContentService;
import com.bluemobi.service.page.PageGroupService;
import com.bluemobi.to.ResultTO;

/**
 * 友情链接控制器
 * ClassName: AdvertFriendLinkController
 * Date: 2016年2月19日下午1:55:39

 * @author kevin
 * @version 
 * @since JDK 7
 */
@Controller
@RequestMapping("back/friendlink")
public class AdvertFriendLinkController extends AbstractBackController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdvertFriendLinkController.class);
	
	@Autowired
	private AdvertFrendlinkService advertFrendlinkService;
	
	/**
	 * 
	 * index 
	 * 单页分组页面
	 * 
	 * @author kevin
	 * @param model
	 * @return 
	 * @since JDK 7
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
		// 加载公共数据
        initIndex(model);
        LOGGER.info("用户【{}】单页分组页", new Object[] { this.getUserid() });
        return "back/advert/friendlink.index";
	}
	
	/**
	 * 分页获取单页分组 列表
	 * getPage
	 * 
	 * @author kevin
	 * @param key
	 * @param pageSize
	 * @param pageIndex
	 * @return 
	 * @since JDK 7
	 */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Page<Map<String, Object>> getPage(String key, int pageSize, int pageIndex) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(!StringUtils.isEmpty(key)) {
        	map.put("key", key);
        }
        return advertFrendlinkService.page(map, pageIndex, pageSize);
    }

    /**
     * 初始化添加页面
     * friendlinkAdd
     * 
     * @author kevin
     * @param model
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String friendlinkAdd(Model model) {
        // 加载公共数据
    	initIndex(model);
        return "back/advert/friendlink.edit";
    }

    /**
     * 初始化修改页面
     * friendlinkEdit
     * 
     * @author kevin
     * @param model
     * @param contentId
     * @return 
     * @since JDK 7
     */
    @RequestMapping( value = "edit", method = RequestMethod.GET)
    public String friendlinkEdit(Model model, int linkId) {
        // 加载公共数据
    	initIndex(model);
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("linkId", linkId);
        model.addAttribute("link", advertFrendlinkService.selectObject(map));
        return "back/advert/friendlink.edit";

    }

    /**
     * 保存操作
     * saveFriendLink
     * 
     * @author kevin
     * @param friendLink
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = {"add", "edit"}, method = RequestMethod.POST)
    @ResponseBody
    public ResultTO saveFriendLink(AdvertFrendlink friendLink) {
        return advertFrendlinkService.saveFriendLink(friendLink);
    }

    /**
     * 删除单页
     * delFriendLink
     * 
     * @author kevin
     * @param linkId
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO delFriendLink(Integer linkId) {
        return advertFrendlinkService.deleteFriendLink(linkId);
    }
    
    /**
     * 前台显示与否操作
     * showFriendLink
     * 
     * @author kevin
     * @param friendLink
     * @return 
     * @since JDK 7
     */
    @RequestMapping(value = "publish", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO showFriendLink(Integer linkId, boolean isPulished) {
        return advertFrendlinkService.showFriendLink(linkId, isPulished);
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
    @RequestMapping(value = "uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadFile(MultipartFile imageFile) {
        Map<String, Object> uploadResultMap = null;
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            uploadResultMap = uploadImage(new MultipartFile[]{imageFile}, CouponConstant.COUPON_IMAGE);
            LOGGER.info("用户【{}】上传图片【{}】成功", new Object[] { this.getUserid(), imageFile.getName() });
        } catch (Exception e) {
            LOGGER.error("用户【{}】上传图片失败。Exception:【{}】", new Object[] { this.getUserid(), e });
            resultMap.put("status", BaseConstant.STATUS_FAILURE);
            return resultMap;
        }
        resultMap.put("status", BaseConstant.STATUS_SUCCESS);
        resultMap.put("url", uploadResultMap.get("imageUrl") == null ? "" : uploadResultMap.get("imageUrl"));
        return resultMap;
    }
}
