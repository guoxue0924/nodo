package com.bluemobi.controller.back.advert;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appcore.page.Page;
import com.bluemobi.controller.AbstractBackController;
import com.bluemobi.po.advert.AdvertBoard;
import com.bluemobi.po.advert.AdvertPage;
import com.bluemobi.service.advert.AdvertBoardService;
import com.bluemobi.service.advert.AdvertPageService;
import com.bluemobi.to.ResultTO;

@Controller
@RequestMapping("back/advertBoard")
public class AdvertBoardController extends AbstractBackController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdvertBoardController.class);

    @Autowired
    private AdvertBoardService advertBoardService;
    @Autowired
    private AdvertPageService advertPageService;

    /* 常用字符串 */
    private static final String PAGES = "pages";
    private static final String PAGE_ID = "pageId";
    private static final String BOARD_ID = "boardId";
    private static final String STATUS = "status";
    private static final String KEY = "key";

    /**
     * 初始化广告位管理页
     * 
     * @auther zhangzheng
     * @date 2015-10-13 下午5:39:01
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
        LOGGER.info("用户【{}】初始化广告位管理页", new Object[] { this.getUserid() });
        return "back/advert/board.index";
    }

    /**
     * 分页查询广告位
     * 
     * @auther zhangzheng
     * @date 2015-10-13 下午5:39:34
     * @param pageId
     * @param key
     * @param pageSize
     * @param pageIndex
     * @return
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Page<Map<String, Object>> page(Integer pageId, String key, Integer pageSize, Integer pageIndex) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(KEY, key);
        if (pageId != null && pageId > 0) {
            param.put(PAGE_ID, pageId);
        }
        Page<Map<String, Object>> pages = advertBoardService.page(param, pageIndex, pageSize);
        LOGGER.info("用户【{}】根据关键字【{}】广告页【{}】页码【{}】每页最大数【{}】分页查询广告位", new Object[] { this.getUserid(), key, pageId, pageIndex, pageSize });
        return pages;
    }

    /**
     * 初始化广告位新增
     * 
     * @auther zhangzheng
     * @date 2015-10-13 下午5:39:55
     * @param model
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        Map<String, Object> param = new HashMap<String, Object>();
        List<AdvertPage> pages = advertPageService.selectObjectList(param);
        // 加载公共数据
        initIndex(model);
        model.addAttribute(PAGES, pages);
        LOGGER.info("用户【{}】初始化广告位新增", new Object[] { this.getUserid() });
        return "back/advert/board.edit";
    }

    /**
     * 新增广告位
     * 
     * @auther zhangzheng
     * @date 2015-10-13 下午5:40:03
     * @param board
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addAdvertBoard(@ModelAttribute AdvertBoard board, BindingResult result) {
        try {
            Date date = new Date();
            board.setCtime(date);
            board.setMtime(date);
            advertBoardService.insert(board);
            LOGGER.info("用户【{}】新增广告位【{}】成功", new Object[] { this.getUserid(), board.getBoardId() });
        } catch (Exception e) {
            LOGGER.error("用户【{}】新增广告位失败。Exception:【{}】", new Object[] { this.getUserid(), e });
            return ResultTO.newFailResultTO("添加失败", null);
        }
        return ResultTO.newSuccessResultTO("添加成功", null);
    }

    /**
     * 初始化广告位修改
     * 
     * @auther zhangzheng
     * @date 2015-10-13 下午5:40:09
     * @param model
     * @param boardId
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(Model model, Integer boardId) {
        // 加载公共数据
        initIndex(model);
        Map<String, Object> boardParam = new HashMap<String, Object>();
        Map<String, Object> advertParam = new HashMap<String, Object>();
        boardParam.put(BOARD_ID, boardId);
        AdvertBoard board = advertBoardService.selectObject(boardParam);
        List<AdvertPage> pages = advertPageService.selectObjectList(advertParam);
        model.addAttribute("board", board);
        model.addAttribute(PAGES, pages);
        LOGGER.info("用户【{}】初始化广告位修改", new Object[] { this.getUserid() });
        return "back/advert/board.edit";
    }

    /**
     * 修改广告位
     * 
     * @auther zhangzheng
     * @date 2015-10-13 下午5:40:16
     * @param board
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editAdvertBoard(@ModelAttribute AdvertBoard board, BindingResult result) {
        try {
            board.setMtime(new Date());
            advertBoardService.update(board);
            LOGGER.info("用户【{}】修改广告位【{}】成功", new Object[] { this.getUserid(), board.getBoardId() });
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改广告位【{}】失败。Exception:【{}】", new Object[] { this.getUserid(), board.getBoardId(), e });
            return ResultTO.newFailResultTO("修改失败", null);
        }
        return ResultTO.newSuccessResultTO("修改成功", null);
    }

    /**
     * 删除广告位
     * 
     * @auther zhangzheng
     * @date 2015-10-13 下午5:40:24
     * @param boardId
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteAdvertBoard(Integer boardId) {
        Map<String, Integer> param = new HashMap<String, Integer>();
        try {
            param.put(BOARD_ID, boardId);
            advertBoardService.delete(param);
            LOGGER.info("用户【{}】删除广告位【{}】成功", new Object[] { this.getUserid(), boardId });
        } catch (Exception e) {
            LOGGER.error("用户【{}】删除广告位【{}】失败。Exception:【{}】", new Object[] { this.getUserid(), boardId, e });
            return ResultTO.newFailResultTO("删除失败", null);
        }
        return ResultTO.newSuccessResultTO("删除成功", null);
    }

    /**
     * 修改广告位的发布状态
     * 
     * @auther zhangzheng
     * @date 2015-10-16 下午3:08:35
     * @param boardId
     * @param status
     * @return
     */
    @RequestMapping(value = "publish", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO publish(Integer boardId, Byte status) {
        Map<String, Integer> param = new HashMap<String, Integer>();
        try {
            param.put(BOARD_ID, boardId);
            if (status == 1) {
                param.put(STATUS, 0);
            } else {
                param.put(STATUS, 1);
            }
            advertBoardService.update(param);
            LOGGER.info("用户【{}】修改广告位【{}】的发布状态【{}】成功", new Object[] { this.getUserid(), boardId, status });
        } catch (Exception e) {
            LOGGER.error("用户【{}】修改广告位【{}】的发布状态【{}】失败。Exception:【{}】", new Object[] { this.getUserid(), boardId, status, e });
            return ResultTO.newFailResultTO("修改发布状态失败", null);
        }
        return ResultTO.newSuccessResultTO("修改发布状态成功", null);
    }

    /**
     * 根据广告页获得广告位
     * 
     * @auther zhangzheng
     * @date 2015-10-13 下午5:40:34
     * @param pageId
     * @return
     */
    @RequestMapping(value = "getBoardByPageId", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO getBoardByPageId(Integer pageId) {
        Map<String, Integer> param = new HashMap<String, Integer>();
        param.put(PAGE_ID, pageId);
        List<AdvertBoard> boards = advertBoardService.selectObjectList(param);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("boards", boards);
        LOGGER.info("用户【{}】根据广告页【{}】获得广告位", new Object[] { this.getUserid(), pageId });
        return ResultTO.newSuccessResultTO(map);
    }

}
