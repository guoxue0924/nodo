package com.bluemobi.controller.api.advert;

import java.util.ArrayList;
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
import com.bluemobi.apito.AdvertTO;
import com.bluemobi.apito.advert.GetAdvertContentRequestTO;
import com.bluemobi.apito.advert.GetAdvertContentResponseTO;
import com.bluemobi.controller.AbstractAPIController;
import com.bluemobi.service.advert.AdvertContentService;
import com.bluemobi.to.ResultTO;
import com.bluemobi.to.advert.AdvertDetailTO;

/**
 * 【广告】控制器
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-02-26 16:04:39
 * 
 */
@Controller(value = "advertContentAPIController")
@RequestMapping("api/advertContent")
public class AdvertContentAPIController extends AbstractAPIController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdvertContentAPIController.class);

    @Autowired
    private AdvertContentService advertContentService;

    /**
     * 获取广告内容
     * 
     * @param request
     * @param getAdvertContentRequestTO
     * @return ResultTO
     * @author AutoCode 309444359@qq.com
     * @date 2016-02-26 16:04:39
     */
    @RequestMapping(value = "getAdvertContent")
    @ResponseBody
    public ResultTO getAdvertContent(HttpServletRequest request, String json ) {
        
        GetAdvertContentRequestTO getAdvertContentRequestTO = JsonUtil.getObject(json, GetAdvertContentRequestTO.class);
        
        LOGGER.debug("请求ip【{}】，请求信息【{}】", new Object[] { request.getRemoteHost(), getAdvertContentRequestTO.toString() });
        GetAdvertContentResponseTO getAdvertContentResponseTO = new GetAdvertContentResponseTO();
        Map<String, Object> param = new HashMap<String, Object>();
        List<AdvertDetailTO> advertContents = null;
        List<AdvertTO> adverts = new ArrayList<AdvertTO>();
        AdvertTO advertTO = null;
        AdvertDetailTO advertDetailTO = null;
        try {
            param.put("pageId", getAdvertContentRequestTO.getPageId());
            param.put("boardId", getAdvertContentRequestTO.getBoardId());
            // 查询广告
            advertContents = advertContentService.selectAdvertDetails(param);
            // 拼装广告结果
            if (advertContents != null && !advertContents.isEmpty()) {
                for (int i = 0; i < advertContents.size(); i++) {
                    advertDetailTO = advertContents.get(i);
                    advertTO = new AdvertTO();
                    advertTO.setAdvertId(advertDetailTO.getContentId());
                    advertTO.setBindSource(advertDetailTO.getBindSource());
                    advertTO.setContent(advertDetailTO.getContent());
                    advertTO.setImage(advertDetailTO.getAttachmentFilePath());
                    advertTO.setTitle(advertDetailTO.getTitle());
                    adverts.add(advertTO);
                }
            }
            getAdvertContentResponseTO.setAdvertContent(adverts);
            return ResultTO.newSuccessResultTO("获取广告内容成功！", getAdvertContentResponseTO);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("获取广告内容出现异常【{}】，请求ip【{}】，请求信息【{}】", new Object[] { e.getMessage(), request.getRemoteHost(), getAdvertContentRequestTO.toString() });
            return ResultTO.newFailResultTO("获取广告内容失败！", null);
        }
    }

}
