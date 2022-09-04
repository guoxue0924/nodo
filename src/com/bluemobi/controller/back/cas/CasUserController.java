package com.bluemobi.controller.back.cas;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
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
import com.bluemobi.po.bts.BtsCart;
import com.bluemobi.po.bts.BtsOrder;
import com.bluemobi.po.cas.CasConsignee;
import com.bluemobi.po.cas.CasUser;
import com.bluemobi.po.cas.CasUserDetail;
import com.bluemobi.po.cas.CasUserLv;
import com.bluemobi.po.cas.CasUserPoint;
import com.bluemobi.po.comment.CommentContent;
import com.bluemobi.service.bts.BtsCartService;
import com.bluemobi.service.bts.BtsOrderService;
import com.bluemobi.service.cas.CasConsigneeService;
import com.bluemobi.service.cas.CasUserDetailService;
import com.bluemobi.service.cas.CasUserLvService;
import com.bluemobi.service.cas.CasUserPointService;
import com.bluemobi.service.cas.CasUserService;
import com.bluemobi.service.comment.CommentContentService;
import com.bluemobi.to.ResultTO;

/**
 *  会员管理
 * @author zhangzheng, liuyt , heweiwen
 * 2015-12-4 下午3:17:23
 */
@Controller
@RequestMapping("back/casuser")
public class CasUserController extends AbstractBackController {
    @Autowired
    private CasUserService casUserService;
    @Autowired
    private CasUserLvService casUserLvService;
    @Autowired
    private CasUserDetailService casUserDetailService;
    @Autowired
    private CasConsigneeService casConsigneeService;
    @Autowired
    private BtsOrderService btsOrderService;
    @Autowired
    private BtsCartService btsCartService;
    @Autowired
    private CommentContentService commentContentService;
    @Autowired
    private CasUserPointService casUserPointService;
    
    

    /** 会员已删除标识 */
    private static final byte IS_DELETEED = 1;

    /** 会员id的属性名 */
    private static final String USERID_FIELD = "userid";

    /**
     * 初始化会员管理页面
     * 
     * @param model
     * @return
     */
    @RequestMapping("index")
    public String index(Model model) {
        Map<String, Object> map = new HashMap<String, Object>();
        //获得等级对象集合
        List<CasUserLv> lvList = casUserLvService.selectObjectList(map);
        initIndex(model);
        model.addAttribute("lvList", lvList);
        
        return "back/cas/casUser.index";
    }

    /**
     * 分页查询会员信息
     * 
     * @param userStatus
     *            会员激活状态
     * @param key
     *            查询关键字
     * @param pageSize
     * @param pageIndex
     * @return
     */
    @RequestMapping(value = "page", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> page(String userLvId, String key,
            int pageSize, int pageIndex) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (!StringUtils.isEmpty(userLvId) && !userLvId.equals("0")) {
            map.put("userLvId", Short.parseShort(userLvId));
        }
        if (!"".equals(key)) {
            map.put("key", key);
        }
       
        Page<Map<String, Object>> userPages = casUserService.page(map,
                pageIndex, pageSize);
        Map<String, Object> mapResult = new HashMap<String, Object>();
        mapResult.put("data", userPages.getData());
        mapResult.put("count", userPages.getCount());
        return mapResult;
    }

    /**
     * 会员详情初始化
     * 
     * @param userid
     * @param model
     * @return
     */
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    public String indexUserDetail(int userid, Model model,
            HttpServletRequest req) {
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put(USERID_FIELD, userid);
        //查询地区数据
        List<CasConsignee> consigneeList = casConsigneeService.selectObjectList(parameter);
        CasUser user = casUserService.selectObject(parameter);
        CasUserDetail userDetail = casUserDetailService.selectObject(parameter);
        
        //统计订单数据
        List<BtsOrder> orderList = btsOrderService.selectObjectList(parameter);
        //统计购物车数据
        List<BtsCart> cartList = btsCartService.selectObjectList(parameter);
        //统计收藏数据
        List<CommentContent> commentList =commentContentService.selectObjectList(parameter);
        
        // 加载公共数据
        initIndex(model);
        model.addAttribute("user", user);
        model.addAttribute("userDetail", userDetail);
        model.addAttribute("userConsignees", consigneeList);
        model.addAttribute("userOrderCount", orderList.size());
        model.addAttribute("userCartCount", cartList.size());
        model.addAttribute("userCommentCount", commentList.size());
        return "back/cas/casUser.detail";
    }
    
    /**
     * 编辑会员初始化
     * 
     * @param userid
     * @param model
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String indexUserEdit(int userid, Model model) {
        Map<String, Object> userMap = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        userMap.put(USERID_FIELD, userid);
        CasUser user = casUserService.selectObject(userMap);
        //获得等级对象集合
        List<CasUserLv> lvList = casUserLvService.selectObjectList(map);
        //获取用户积分
        int point = 0;
        List<CasUserPoint> list = casUserPointService.getCanUsePoint(userid);
        if(list!=null){
        	for(int i=0;i<list.size();i++){
        		point = point + list.get(i).getPoint();
        	}
        }
        // 加载公共数据
        initIndex(model);
        model.addAttribute("user", user);
        model.addAttribute("point",point);
        model.addAttribute("lvList", lvList);

        return "back/cas/casUser.edit";
    }

    /**
     * 编辑会员
     * 
     * @param user
     * @param userDetail
     * @param birthday
     * @param result
     * @return
     */
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO editIndex(@ModelAttribute CasUser user, BindingResult result,int addUserPoint) {
        user.setMtime(new Date());
        if ("".equals(user.getPassword())) {
            user.setPassword(null);
        }
        casUserService.update(user);
        CasUserPoint point = new CasUserPoint();
        point.setUserid(user.getUserid());
        point.setPointType(1);
        point.setPointName("添加用户积分");
        point.setPoint(addUserPoint);
        point.setCtime(new Date());
        point.setIsUsed(0);
        point.setStatus(0);
        casUserPointService.insert(point);
        return ResultTO.newSuccessResultTO("修改成功", null);
    }

    /**
     * 删除会员
     * 
     * @param userid
     * @param model
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteUser(Integer userid) {
        Map<String, Object> userMap = new HashMap<String, Object>();
        userMap.put(USERID_FIELD, userid);
        CasUser user = casUserService.selectObject(userMap);
        if (user == null) {
            return ResultTO.newFailResultTO("删除失败, 用户不存在!", null);
        }
        if (user.getIsDel() == 1) {
            return ResultTO.newFailResultTO("删除失败, 用户已删除!", null);
        }
        user = new CasUser();
        user.setUserid(userid);
        user.setIsDel(IS_DELETEED);
        int count = casUserService.update(user);
        if (count == 1) {
            return ResultTO.newSuccessResultTO("逻辑删除成功", null);
        } else {
            return ResultTO.newFailResultTO("删除失败,请联系管理员", null);
        }
    }

    /**
     * 验证用户名是否可注册接口
     * 
     * @param username
     * @return
     */
    @RequestMapping(value = "sign/isUsernameAvailable", method = RequestMethod.GET)
    @ResponseBody
    public Boolean isUsernameAvailable(String username) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username", username);
        CasUser casUser = casUserService.selectObject(map);
        if (casUser != null) {
            return false;
        }
        return true;
    }

    /**
     * 更新会员审核状态
     * 
     * @param userid
     * @param model
     * @return
     */
    @RequestMapping(value = "isVerify/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO updateIsVerify(long userid, Model model,
            HttpServletRequest req) {
        return casUserService.updateVerify(userid);
    }
}
