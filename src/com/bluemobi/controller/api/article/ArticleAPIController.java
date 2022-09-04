package com.bluemobi.controller.api.article;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.appcore.util.JsonUtil;
import com.bluemobi.apito.article.GetArticleInfoRequestTO;
import com.bluemobi.apito.article.GetArticleInfoResponseTO;
import com.bluemobi.controller.AbstractAPIController;
import com.bluemobi.to.ResultTO;

/**
 * 【文章】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-02-29 15:00:04
 * 
 */
@Controller(value = "articleAPIController")
@RequestMapping("api/article")
public class ArticleAPIController extends AbstractAPIController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleAPIController.class);


	/**
	 * 获取文章详情
     * @param request
     * @param getArticleInfoRequestTO
     * @return ResultTO
	 * @author AutoCode 309444359@qq.com
     * @date 2016-02-29 15:00:04
	 */
    @RequestMapping(value = "getArticleInfo")
    @ResponseBody
    public ResultTO getArticleInfo(HttpServletRequest request,String json ) {

        GetArticleInfoRequestTO getArticleInfoRequestTO = JsonUtil.getObject(json, GetArticleInfoRequestTO.class);
        
        LOGGER.debug("请求ip【{}】，请求信息【{}】", new Object[] { request.getRemoteHost(), getArticleInfoRequestTO.toString() });
        
        try{
            //处理业务
            
            
            GetArticleInfoResponseTO getArticleInfoResponseTO = new GetArticleInfoResponseTO();
            return ResultTO.newSuccessResultTO("获取文章详情成功！", getArticleInfoResponseTO);
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.error("获取文章详情出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost(), getArticleInfoRequestTO.toString() });
            return ResultTO.newFailResultTO("获取文章详情失败！", null);
        }
		
    }




}
