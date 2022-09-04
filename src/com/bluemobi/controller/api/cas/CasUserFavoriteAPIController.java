package com.bluemobi.controller.api.cas;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appcore.util.JsonUtil;
import com.bluemobi.apito.FavoriteTO;
import com.bluemobi.apito.cas.AddFavoriteRequestTO;
import com.bluemobi.apito.cas.AddFavoriteResponseTO;
import com.bluemobi.apito.cas.GetFavoritesRequestTO;
import com.bluemobi.apito.cas.GetFavoritesResponseTO;
import com.bluemobi.apito.cas.DeleteFavoriteRequestTO;
import com.bluemobi.apito.cas.DeleteFavoriteResponseTO;
import com.bluemobi.controller.AbstractAPIController;
import com.bluemobi.po.cas.CasUserFavorite;
import com.bluemobi.po.goods.GoodsContent;
import com.bluemobi.po.goods.GoodsContentSku;
import com.bluemobi.service.cas.CasUserFavoriteService;
import com.bluemobi.service.goods.GoodsContentService;
import com.bluemobi.service.goods.GoodsContentSkuService;
import com.bluemobi.to.ResultTO;

/**
 * 【收藏】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-02-24 17:02:45
 * 
 */
@Controller(value = "casUserFavoriteAPIController")
@RequestMapping("api/casUserFavorite")
public class CasUserFavoriteAPIController extends AbstractAPIController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CasUserFavoriteAPIController.class);

    @Autowired
    private CasUserFavoriteService casUserFavoriteService;
    @Autowired
    private GoodsContentSkuService goodsContentSkuService;
    
    /** 定义公共名字 */
    private static final int FAVORITE_TYPE_GOODS = 1;//1:商品收藏

	/**
	 * 添加收藏信息
     * @param request
     * @param addFavoriteRequestTO
     * @return ResultTO
	 * @author AutoCode 309444359@qq.com
     * @date 2016-02-24 17:02:45
	 */
    @RequestMapping(value = "addFavorite")
    @ResponseBody
    public ResultTO addFavorite(HttpServletRequest request, String json) {
        
        AddFavoriteRequestTO addFavoriteRequestTO = JsonUtil.getObject(json, AddFavoriteRequestTO.class);

        LOGGER.debug("请求ip【{}】，请求信息【{}】", new Object[] { request.getRemoteHost(), addFavoriteRequestTO.toString() });
        
        try{
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("userid", getUserid());
            map.put("specificationid", Integer.parseInt(addFavoriteRequestTO.getContent()));
            List<CasUserFavorite> favorites = casUserFavoriteService.selectObjectList(map);
            if (favorites.size() > 0) {
                return ResultTO.newFailResultTO("您已经添加过该商品！", null);
            }
            //数据转换
            CasUserFavorite favorite = new CasUserFavorite();
            favorite.setContent(addFavoriteRequestTO.getContent());
            favorite.setCtime(new Date());
            favorite.setSpecificationid(Integer.parseInt(addFavoriteRequestTO.getContent()));
            favorite.setType(addFavoriteRequestTO.getType());
            favorite.setUserid(getUserid());
            casUserFavoriteService.insert(favorite);
            
            AddFavoriteResponseTO addFavoriteResponseTO = new AddFavoriteResponseTO();
            return ResultTO.newSuccessResultTO("添加收藏信息成功！", addFavoriteResponseTO);
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.error("添加收藏信息出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost(), addFavoriteRequestTO.toString() });
            return ResultTO.newFailResultTO("添加收藏信息失败！", null);
        }
		
    }

	/**
	 * 获取所有收藏信息
     * @param request
     * @param getFavoritesRequestTO
     * @return ResultTO
	 * @author AutoCode 309444359@qq.com
     * @date 2016-02-24 17:02:45
	 */
    @RequestMapping(value = "getFavorites")
    @ResponseBody
    public ResultTO getFavorites(HttpServletRequest request, String json) {
        
        GetFavoritesRequestTO getFavoritesRequestTO = JsonUtil.getObject(json, GetFavoritesRequestTO.class);

        LOGGER.debug("请求ip【{}】，请求信息【{}】", new Object[] { request.getRemoteHost(), getFavoritesRequestTO.toString() });
        
        try{
            //查询业务
            Map<String,Object> reqMap = new HashMap<String, Object>();
            reqMap.put("userid", getUserid());
            List<CasUserFavorite> facorites = casUserFavoriteService.selectObjectList(reqMap);
            
            //转换数据
            GetFavoritesResponseTO getFavoritesResponseTO = new GetFavoritesResponseTO();
            List<FavoriteTO> favorites = new ArrayList<FavoriteTO>();
            for (CasUserFavorite data:facorites) {
                FavoriteTO favoriteTO = new FavoriteTO();
                favoriteTO.setContent(data.getContent());
                favoriteTO.setCtime(data.getCtime().toString());
                favoriteTO.setFavoriteId(data.getFavoriteId());
                
                //判断是否为商品收藏
                if (data.getType().equals(FAVORITE_TYPE_GOODS)) {
                    int skuId = data.getSpecificationid();
                    GoodsContentSku goodsSku =  goodsContentSkuService.selectGoodsContentSku(skuId);
                    favoriteTO.setImage(goodsSku.getImages().size() > 0 ?goodsSku.getImages().get(0):"");
                    favoriteTO.setName(goodsSku.getName());
                    favoriteTO.setPrice(goodsSku.getPrice().floatValue());
                    favoriteTO.setType(FAVORITE_TYPE_GOODS);
                }
                
                favorites.add(favoriteTO);
            }
            
            getFavoritesResponseTO.setFavorites(favorites);
            
            return ResultTO.newSuccessResultTO("获取所有收藏信息成功！", getFavoritesResponseTO);
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.error("获取所有收藏信息出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost(), getFavoritesRequestTO.toString() });
            return ResultTO.newFailResultTO("获取所有收藏信息失败！", null);
        }
		
    }

	/**
	 * 删除收藏
     * @param request
     * @param deleteFavoriteRequestTO
     * @return ResultTO
	 * @author AutoCode 309444359@qq.com
     * @date 2016-02-24 17:02:45
	 */
    @RequestMapping(value = "deleteFavorite")
    @ResponseBody
    public ResultTO deleteFavorite(HttpServletRequest request, String json) {
        
        DeleteFavoriteRequestTO deleteFavoriteRequestTO = JsonUtil.getObject(json, DeleteFavoriteRequestTO.class);

        LOGGER.debug("请求ip【{}】，请求信息【{}】", new Object[] { request.getRemoteHost(), deleteFavoriteRequestTO.toString() });
        
        try{
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("favoriteId", deleteFavoriteRequestTO.getFavoriteId());
            map.put("userid", getUserid());
            if (deleteFavoriteRequestTO.getContent() != null && !deleteFavoriteRequestTO.getContent().equals("")) {
                map.put("specificationid", Integer.parseInt(deleteFavoriteRequestTO.getContent()));
            }
            //删除用户收藏处理
            casUserFavoriteService.delete(map);
            
            DeleteFavoriteResponseTO deleteFavoriteResponseTO = new DeleteFavoriteResponseTO();
            return ResultTO.newSuccessResultTO("删除收藏成功！", deleteFavoriteResponseTO);
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.error("删除收藏出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost(), deleteFavoriteRequestTO.toString() });
            return ResultTO.newFailResultTO("删除收藏失败！", null);
        }
		
    }




}
