package com.bluemobi.controller.front.index;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.apache.commons.net.nntp.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.appcore.context.AppContext;
import com.bluemobi.ServerInit;
import com.bluemobi.WebServerInit;
import com.bluemobi.constant.ArticleConstant;
import com.bluemobi.controller.AbstractAPIController;
import com.bluemobi.po.goods.GoodsCategory;
import com.bluemobi.service.advert.AdvertContentService;
import com.bluemobi.service.article.ArticleContentService;
import com.bluemobi.service.goods.GoodsCategoryService;
import com.bluemobi.service.goods.GoodsContentService;
import com.bluemobi.to.advert.AdvertDetailTO;

@Controller
@RequestMapping("front/index")
public class IndexController extends AbstractAPIController {

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private GoodsContentService goodsContentService;
    @Autowired
    private AdvertContentService advertContentService;
    @Autowired
    private ArticleContentService articleContentService;
    
    @Autowired
    private GoodsCategoryService goodsCategoryService;

    /**
     * 初始化首页
     * 
     * @auther zhangzheng
     * @date 2016-1-18 下午4:42:01
     * @param model
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) {
        Map<String, Object> param = new HashMap<String, Object>();
        /** 首页正中央大广告 */
        List<AdvertDetailTO> topBigAdvertList = null;
        /** 首页中间的横幅广告 */
        List<AdvertDetailTO> bannerAdvertList = null;
        /** 首页上部正中央大广告下面的滚动商品 */
        List<Map<String, Object>> topGoods = null;
        /** 热卖商品 */
        List<Map<String, Object>> hotSales = null;
        /** 疯狂抢购 */
        List<Map<String, Object>> panicBuying = null;
        /** 猜您喜欢 */
        List<Map<String, Object>> guessYouWouldLike = null;
        /** 热评商品 */
        List<Map<String, Object>> hotReview = null;
        /** 新品上架 */
        List<Map<String, Object>> newGoods = null;
        /** 首页上部右侧近期抢购 */
        List<Map<String, Object>> nearPanicBuying = null;
        /** 限时折扣 */
        List<Map<String, Object>> specialDiscount = null;
        /** 所有商品数据，页面填充用 */
        List<Map<String, Object>> list = null;
        try {
        	 //公告栏标题列表
            param.clear();
            list =articleContentService.selectObjectListBycondition();
            model.addAttribute("articleContentList", list);
            // 获取首页滚动广告
            param.put("status", 1);
            param.put("boardId", 6);
            topBigAdvertList = advertContentService.selectAdvertDetails(param);
            model.addAttribute("topBigAdvertList", topBigAdvertList);
            // 获取首页横幅广告
            param.put("boardId", 8);
            bannerAdvertList = advertContentService.selectAdvertDetails(param);
            model.addAttribute("bannerAdvertList", bannerAdvertList);
            // 获取首页上部正中央大广告下面的滚动商品
            param.clear();
            topGoods = goodsContentService.getTopGoods(param, 1, 10);
            model.addAttribute("topGoods", topGoods);

            // 热卖商品
            param.clear();
//            hotSales = goodsContentService.getHotSales(param, 1, 5);
            hotSales = goodsContentService.selectHotSales(null);
            model.addAttribute("hotSales", hotSales);
            // 疯狂抢购
            param.clear();
            panicBuying = goodsContentService.getPanicBuying(param, 1, 5);
            model.addAttribute("panicBuying", panicBuying);
            // 猜您喜欢
            param.clear();
            guessYouWouldLike = goodsContentService.getGuessYouWouldLike(param, 1, 5);
            model.addAttribute("guessYouWouldLike", guessYouWouldLike);
            // 热评商品
            param.clear();
            hotReview = goodsContentService.getHotReview(param, 1, 5);
            model.addAttribute("hotReview", hotReview);
            // 新品上架
            param.clear();
            newGoods = goodsContentService.getNewGoods(param, 1, 5);
            model.addAttribute("newGoods", newGoods);

            // 首页上部右侧近期抢购
            param.clear();
            nearPanicBuying = goodsContentService.getNearPanicBuying(param, 3, 2);
            model.addAttribute("nearPanicBuying", nearPanicBuying);
            // 限时折扣
            param.clear();
            specialDiscount = goodsContentService.getSpecialDiscount(param, 2, 3);
            model.addAttribute("specialDiscount", specialDiscount);

            // 查询所有商品，页面填充用
            param.clear();
            list = goodsContentService.getTopGoods(param, 1, 100);
            model.addAttribute("goodsList", list);
            
            //公告栏标题列表
            param.clear();
            list =articleContentService.selectObjectListBycondition();
            model.addAttribute("articleContentList", list);
            model.addAttribute("loggedInUser", getCasUser());
           
            
            //2、初始化商品分类
            List<GoodsCategory> categories = goodsCategoryService.loadCategories();
            model.addAttribute("LOAD_CATEGORIES", categories);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "front/index/index.index";
    }
}
