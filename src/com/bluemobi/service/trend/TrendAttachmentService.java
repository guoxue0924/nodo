package com.bluemobi.service.trend;

import java.util.List;
import java.util.Map;

import com.appcore.service.MybatisBaseService;
import com.bluemobi.po.trend.TrendAttachment;

/**
 * 【attachment user mapping】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-14 10:33:23
 * 
 */
public interface TrendAttachmentService extends MybatisBaseService {

    abstract Long insertUploadFile(Map<String, Object> uploadResultMap, TrendAttachment trendAttachment);

    /**
     * 根据mainId和type查询附件对象集合
     * 
     * @author HeWeiwen 2015-12-2
     * @param map
     * @return
     */
    List<TrendAttachment> selectTrendAttachmentListByMainId(Map<String, Object> map);

    /**
     * 根据多个附件ID查询附件
     * 
     * @auther zhangzheng
     * @date 2015-12-4 下午6:14:14
     * @param param
     * @return
     */
    List<TrendAttachment> selectTrendAttachmentListByIds(Map<String, Object> param);

}
