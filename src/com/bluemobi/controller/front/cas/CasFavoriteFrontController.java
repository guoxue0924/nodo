package com.bluemobi.controller.front.cas;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appcore.page.Page;
import com.bluemobi.controller.AbstractAPIController;
import com.bluemobi.po.cas.CasUserFavorite;
import com.bluemobi.po.goods.GoodsContentSku;
import com.bluemobi.service.cas.CasUserFavoriteService;
import com.bluemobi.service.goods.GoodsContentService;
import com.bluemobi.service.goods.GoodsContentSkuService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.to.goods.GoodsContentAndSkuTO;



/**
 * 【用户收藏】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-01-04 14:42:22
 * 
 */
@Controller
@RequestMapping("front/cas")
public class CasFavoriteFrontController extends AbstractAPIController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CasFavoriteFrontController.class);
    
    @Autowired
    private CasUserFavoriteService casUserFavoriteService;
    @Autowired
    private GoodsContentSkuService goodsContentSkuService;
    
    /**
     * 初始化我的收藏页面
     * @author HeWW
     * 2016-1-18
     * @param model
     * @return
     */
    @RequestMapping(value = "favorite", method = RequestMethod.GET)
    public String favorite(Model model) {
        model.addAttribute("loggedInUser", getCasUser());
        
        return "front/cas/favorite.index";
    }
    
    /**
     * 获得用户所有收藏信息
     * @author HeWW
     * 2016-1-21
     * @param pageSize
     * @param pageIndex
     * @return
     */
    @RequestMapping(value = "favorite", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> pageFavorite(int pageSize, int pageIndex) {
      //1,根据用户名模糊分页查询用户列表
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userid", getCasUser().getUserid());
        Page<Map<String, Object>> pages = casUserFavoriteService.page(map, pageIndex, pageSize);
        Map<String, Object> dataResult = new HashMap<String, Object>();
        List<Map<String, Object>> favoriteList = new ArrayList<Map<String,Object>>();
        dataResult.put("user_info", getCasUser());
        dataResult.put("favorite", favoriteList);
        for (Map<String, Object> data:pages.getData()) {
            Map<String, Object> favoriteMap = new HashMap<String, Object>();
            favoriteMap.putAll(data);
            long skuId = Long.valueOf(data.get("specificationid").toString());
            GoodsContentSku goodsSku =  goodsContentSkuService.selectGoodsContentSku(skuId);
            if (goodsSku != null) {
                favoriteMap.put("relate_content", goodsSku);
                favoriteMap.put("DefaultImage", goodsSku.getImages().get(0));
                favoriteMap.put("favoriteCount", casUserFavoriteService.countsByskuId(skuId));
                favoriteList.add(favoriteMap);
            }
        }
        
        Map<String, Object> mapResult = new HashMap<String, Object>();
        mapResult.put("data", dataResult);
        mapResult.put("count", pages.getCount());
        return mapResult;
    }
  
    /**
     * 添加用户收藏信息
     * @author HeWW
     * 2016-1-4
     * @param type
     * @param content
     * @return
     */
    @RequestMapping(value = "favorite/add", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO addCasUserFavorite(String content,Integer type) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("userid", getUserid());
        map.put("specificationid", Integer.parseInt(content));
        List<CasUserFavorite> favorites = casUserFavoriteService.selectObjectList(map);
        if (favorites.size() > 0) {
            return ResultTO.newFailResultTO("您已经添加过该商品！", null);
        }
        //数据转换
        CasUserFavorite favorite = new CasUserFavorite();
        favorite.setContent(content);
        favorite.setCtime(new Date());
        favorite.setSpecificationid(Integer.parseInt(content));
        favorite.setUserid(getUserid());
        favorite.setType(type);
        casUserFavoriteService.insert(favorite);
        
        //统计该商品的收藏数
        int favoriteNum = casUserFavoriteService.countsByskuId(Integer.parseInt(content));
        return ResultTO.newSuccessResultTO("收藏成功", favoriteNum);
    }
    
    
    
    /**
     * 删除【用户收藏】数据
     * @param favoriteId
     * @return ResultTO
     * @author AutoCode
     * @date 2016-01-04 14:42:22
     */
    @RequestMapping(value = "deleteFavorite", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteCasUserFavorite(Integer favoriteId) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        try {
            map.put("favoriteId", favoriteId); 
            casUserFavoriteService.delete(map);
            LOGGER.info("用户【{}】删除用户收藏数据【{}】成功", new Object[] { this.getUserid(), favoriteId });
        } catch (Exception e) {
            LOGGER.error("用户【{}】删除用户收藏数据【{}】失败 Exception:【{}】", new Object[] { this.getUserid(), favoriteId, e });
            return ResultTO.newFailResultTO("删除失败", favoriteId);
        }
        return ResultTO.newSuccessResultTO("删除成功", favoriteId);
    }
    
    /**
     * 多选删除
     * @author HeWW
     * 2016-1-21
     * @param favoriteIds
     * @return
     */
    @RequestMapping(value = "deleteMultipleFavorite", method = RequestMethod.POST)
    @ResponseBody
    public ResultTO deleteCasUserFavorite(String favoriteIds) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            String[] ids = favoriteIds.split(",");
            map.put("favoriteIds", ids); 
            casUserFavoriteService.deleteByIds(map);
            LOGGER.info("用户【{}】删除用户收藏数据【{}】成功", new Object[] { this.getUserid(), favoriteIds });
        } catch (Exception e) {
            LOGGER.error("用户【{}】删除用户收藏数据【{}】失败 Exception:【{}】", new Object[] { this.getUserid(), favoriteIds, e });
            return ResultTO.newFailResultTO("删除失败", null);
        }
        Map<String,String> resultMap = new HashMap<String, String>();
        resultMap.put("favoriteIds", favoriteIds);
        return ResultTO.newSuccessResultTO("删除成功", resultMap);
    }
    
}
