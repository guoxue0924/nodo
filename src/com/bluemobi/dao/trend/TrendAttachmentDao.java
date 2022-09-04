package com.bluemobi.dao.trend;

import java.util.List;
import java.util.Map;

import com.appcore.dao.MyBatisBaseDao;
import com.bluemobi.po.trend.TrendAttachment;

/**
 * 【attachment user mapping】 数据访问对象 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-14 10:33:23
 * 
 */
public interface TrendAttachmentDao extends MyBatisBaseDao {

    /**
     * 根据mainId和type查询附件对象集合
     * 
     * @author HeWeiwen 2015-12-2
     * @param map
     * @return
     */
    List<TrendAttachment> selectTrendAttachmentListByMainId(Map<String, Object> param);

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
