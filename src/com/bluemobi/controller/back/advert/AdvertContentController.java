package com.bluemobi.controller.back.advert;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.appcore.page.Page;
import com.appcore.util.TimeUtil;
import com.bluemobi.constant.AdvertConstant;
import com.bluemobi.controller.AbstractBackController;
import com.bluemobi.po.advert.AdvertContent;
import com.bluemobi.po.advert.AdvertPage;
import com.bluemobi.po.trend.TrendAttachment;
import com.bluemobi.service.advert.AdvertContentService;
import com.bluemobi.service.advert.AdvertPageService;
import com.bluemobi.service.trend.TrendAttachmentService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.to.advert.AdvertContentTO;
import com.bluemobi.to.advert.AdvertDetailTO;

/**
 * 广告管理
 * 
 * @author zhangzheng
 * @date 2015-10-13
 * 
 */
@Controller
@RequestMapping("back/advertContent")
public class AdvertContentController extends AbstractBackController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdvertContentController.class);

    @Autowired
    private AdvertContentService advertContentService;
    @Autowired
    private AdvertPageService advertPageService;
    @Autowired
    private TrendAttachmentService trendAttachmentService;

    /* 常用字符串 */
    private static final String PAGES = "pages";
    private static final String PAGE_ID = "pageId";
    private static final String BOARD_ID = "boardId";
    private static final String DATE_FORMATR = "yyyy-MM-dd";
    private static final String CONTENT_ID = "contentId";
    private static final String STATUS = "status";
    private static final String KEY = "key";

    /**
     * 初始化广告管理
     * 
     * @auther zhangzheng
     * @date 2015-10-14 上午9:54:07
     * @param model
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        // 加载公共数据
        initIndex(model);
        Map<String, Object> param = new HashMap<String, Object>();
        List<AdvertPage> pages = advertPageService.selectObjectList(param);
        model.addAttribute(PAGES, pages);
        LOGGER.info("用户【{}】初始化广告管理", new Object[] { this.getUserid() });
        return "back/advert/content.index";
    }

    /**
     * 分页查询广告
     * 
     * @auther zhangzheng
     * @date 2015-10-14 上午9:54:21
     * @param key
     * @param pageId
     * @param boardId
     * @param pageSize
     * @param pageIndex
     * @return
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Page<Map<String, Object>> page(String key, Integer pageId, Integer boardId, Integer pageSize, Integer pageIndex) {
        Map<String, Object> param = new HashMap<String, Object>();
        if (key != null && !"".equals(key)) {
            param.put(KEY, key);
        }
        if (pageId != null && pageId != 0) {
            param.put(PAGE_ID, pageId);
        }
        if (boardId != null && boardId != 0) {
            param.put(BOARD_ID, boardId);
        }
        Page<Map<String, Object>> pages = advertContentService.page(param, pageIndex, pageSize);
        LOGGER.info("用户【{}】根据关键字【{}】广告页【{}】广告位【{}】页码【{}】每页最大数【{}】分页查询广告", new Object[] { this.getUserid(), key, pageId, boardId, pageIndex, pageSize });
        return pages;
    }

    /**
     * 查看广告详情
     * 
     * @auther zhangzheng
     * @date 2015-12-3 上午10:39:59
     * @param model
     * @param contentId
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String detail(Model model, Long contentId) {
        initIndex(model);
        Map<String, Object> param = new HashMap<String, Object>();
        if (contentId != null) {
            param.put("contentId", contentId);
            AdvertDetailTO advert = advertContentService.selectAdvertDetail(param);
            model.addAttribute("advert", advert);
        }
        return "back/advert/content.detail";
    }

    /**
     * 初始化广告新增
     * 
     * @auther zhangzheng
     * @date 2015-10-14 上午9:54:29
     * @param model
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        // 加载公共数据
        initIndex(model);
        Map<String, Object> param = new HashMap<String, Object>();
        List<AdvertPage> pages = advertPageService.selectObjectList(param);
        model.addAttribute(PAGES, pages);
        model.addAttribute("bindTypes", AdvertConstant.bindTypeMap);
        LOGGER.info("用户【{}】初始化广告新增", new Object[] { this.getUserid() });
        return "back/advert/content.edit";
    }

    /**
     * 新增广告
     * 
     * @auther zhangzheng
     * @date 2015-12-3 上午10:33:22
     * @param uploadFile
     * @param contentTO
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addAdvertContent(@RequestParam(value = "uploadFile", required = false) MultipartFile[] uploadFile, @ModelAttribute AdvertContentTO contentTO,
            BindingResult result) {
        Date date = new Date();
        try {
            AdvertContent content = new AdvertContent();
            BeanUtils.copyProperties(content, contentTO);
            String startTimeStr = contentTO.getStartTimeStr();
            String endTimeStr = contentTO.getEndTimeStr();

            uploadAdvertImage(uploadFile, content);

            setBindSourceByBindType(content);
            content.setCtime(date);
            content.setMtime(date);
            if (content.getCount() == null) {
                content.setCount(0);
            }
            if (content.getStatus() == null) {
                content.setStatus(Byte.valueOf("0"));
            }
            if (startTimeStr != null && !"".equals(startTimeStr)) {
                content.setStartTime(TimeUtil.getDateByFormatDate(startTimeStr, DATE_FORMATR));
            }
            if (endTimeStr != null && !"".equals(endTimeStr)) {
                content.setEndTime(TimeUtil.getDateByFormatDate(endTimeStr, DATE_FORMATR));
            }

            advertContentService.insert(content);
            LOGGER.info("用户【{}】新增广告【{}】成功", new Object[] { this.getUserid(), content.getContentId() });
        } catch (Exception e) {
            LOGGER.error("用户【{}】新增广告失败。Exception:【{}】", new Object[] { this.getUserid(), e });
            return ResultTO.newFailResultTO("添加失败", null);
        }
        return ResultTO.newSuccessResultTO("添加成功", null);
    }

    /**
     * 上传广告图片
     * 
     * @auther zhangzheng
     * @date 2015-11-25 下午5:16:27
     * @param uploadFile
     * @param content
     */
    private void uploadAdvertImage(MultipartFile[] uploadFile, AdvertContent content) {
        Map<String, Object> uploadResultMap = null;
        if (uploadFile != null && uploadFile.length > 0) {
            uploadResultMap = uploadImage(uploadFile, AdvertConstant.ADVERT_CONTENT_FILE);
            if ((Boolean) uploadResultMap.get("flag")) {
                TrendAttachment trendAttachment = new TrendAttachment();
                uploadResultMap.put("userid", this.getUserid());
                uploadResultMap.put("filesize", uploadFile.length);
                uploadResultMap.put("fileType", content.getType());
                trendAttachmentService.insertUploadFile(uploadResultMap, trendAttachment);
                content.setAttachmentid(trendAttachment.getAttachmentid());
            }
        }
    }

    /**
     * 根据绑定资源类型设置绑定资源内容
     * 
     * @auther zhangzheng
     * @date 2015-10-19 上午11:50:08
     * @param content
     */
    private void setBindSourceByBindType(AdvertContent content) {
        String[] bindSources = null;
        if (content.getBindSource() != null && !"".equals(content.getBindSource())) {
            bindSources = content.getBindSource().split(",");
        }
        if (bindSources != null && bindSources.length > 0) {
            if (AdvertConstant.BIND_TYPE_LINK_ADDRESS.equals(content.getBindType())) {
                content.setBindSource(bindSources[0]);
            } else if (AdvertConstant.BIND_TYPE_GOODS.equals(content.getBindType())) {
                content.setBindSource(bindSources[1]);
            } else if (AdvertConstant.BIND_TYPE_WORD.equals(content.getBindType())) {
                content.setBindSource(bindSources[2]);
            } else if (AdvertConstant.BIND_TYPE_CUSTOM.equals(content.getBindType())) {
                content.setBindSource(bindSources[3]);
            } else {
                content.setBindSource("");
            }
        } else {
            content.setBindSource("");
        }
    }

    /**
     * 初始化广告修改
     * 
     * @auther zhangzheng
     * @date 2015-10-14 上午9:54:42
     * @param model
     * @param contentId
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model, Integer contentId) {
        // 加载公共数据
        initIndex(model);
        Map<String, Object> contentParam = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
        Map<String, Object> attachmentMap = new HashMap<String, Object>();
        List<AdvertPage> pages = advertPageService.selectObjectList(param);
        contentParam.put(CONTENT_ID, contentId);
        AdvertContent content = advertContentService.selectObject(contentParam);
        attachmentMap.put("attachmentid", content.getAttachmentid());
        TrendAttachment attachment = trendAttachmentService.selectObject(attachmentMap);
        if (content.getStartTime() != null) {
            model.addAttribute("startTimeStr", TimeUtil.getFormatDate(content.getStartTime(), DATE_FORMATR));
        }
        if (content.getEndTime() != null) {
            model.addAttribute("endTimeStr", TimeUtil.getFormatDate(content.getEndTime(), DATE_FORMATR));
        }
        model.addAttribute("content", content);
        model.addAttribute("attachment", attachment);
        model.addAttribute(PAGES, pages);
        model.addAttribute("bindTypes", AdvertConstant.bindTypeMap);
        LOGGER.info("用户【{}】初始化广告修改", new Object[] { this.getUserid() });
        return "back/advert/content.edit";
    }

    /**
     * 修改广告
     * 
     * @auther zhangzheng
     * @date 2015-12-3 上午10:33:57
     * @param uploadFile
     * @param contentTO
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editAdvertContent(@RequestParam(value = "uploadFile", required = false) MultipartFile[] uploadFile, @ModelAttribute AdvertContentTO contentTO,
            BindingResult result) {
        try {
            AdvertContent content = new AdvertContent();
            BeanUtils.copyProperties(content, contentTO);
            String startTimeStr = contentTO.getStartTimeStr();
            String endTimeStr = contentTO.getEndTimeStr();

            uploadAdvertImage(uploadFile, content);

            setBindSourceByBindType(content);
            content.setMtime(new Date());
            if (startTimeStr != null && !"".equals(startTimeStr)) {
                content.setStartTime(TimeUtil.getDateByFormatDate(startTimeStr, DATE_FORMATR));
            }
            if (endTimeStr != null && !"".equals(endTimeStr)) {
                content.setEndTime(TimeUtil.getDateByFormatDate(endTimeStr, DATE_FORMATR));
            }
            // 修改广告
            advertContentService.update(content);
            LOGGER.info("用户【{}】修改广告【{}】成功", new Object[] { this.getUserid(), contentTO.getContentId() });
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改广告【{}】失败。Exception:【{}】", new Object[] { this.getUserid(), contentTO.getContentId(), e });
            return ResultTO.newFailResultTO("修改失败", null);
        }
        return ResultTO.newSuccessResultTO("修改成功", null);
    }

    /**
     * 广告删除
     * 
     * @auther zhangzheng
     * @date 2015-10-14 上午9:54:55
     * @param contentId
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteAdvertContent(Integer contentId) {
        Map<String, Integer> param = new HashMap<String, Integer>();
        try {
            param.put(CONTENT_ID, contentId);
            // 删除广告位
            advertContentService.delete(param);
            LOGGER.info("用户【{}】删除广告【{}】成功", new Object[] { this.getUserid(), contentId });
        } catch (Exception e) {
            LOGGER.error("用户【{}】删除广告【{}】失败。Exception:【{}】", new Object[] { this.getUserid(), contentId, e });
            return ResultTO.newFailResultTO("删除失败", null);
        }
        return ResultTO.newSuccessResultTO("删除成功", null);
    }

    /**
     * 修改发布状态
     * 
     * @auther zhangzheng
     * @date 2015-10-14 下午5:41:54
     * @param contentId
     * @param status
     * @return
     */
    @RequestMapping(value = "publish", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO publish(Integer contentId, Byte status) {
        Map<String, Integer> param = new HashMap<String, Integer>();
        try {
            param.put(CONTENT_ID, contentId);
            if (status == 1) {
                param.put(STATUS, 0);
            } else {
                param.put(STATUS, 1);
            }
            advertContentService.update(param);
            LOGGER.info("用户【{}】修改广告【{}】发布状态【{}】成功", new Object[] { this.getUserid(), contentId, status });
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改广告【{}】发布状态【{}】失败。Exception:【{}】", new Object[] { this.getUserid(), contentId, status, e });
            return ResultTO.newFailResultTO("修改发布状态失败", null);
        }
        return ResultTO.newSuccessResultTO("修改发布状态成功", null);
    }

}
