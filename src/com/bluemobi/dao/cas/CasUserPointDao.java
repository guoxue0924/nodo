package com.bluemobi.dao.cas;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.appcore.dao.MyBatisBaseDao;
import com.bluemobi.po.cas.CasUserPoint;
import com.bluemobi.to.bts.RefundPageTO;
import com.bluemobi.to.cas.CasUserPointPageTO;

/**
 * 【用户积分表】 数据访问对象 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-03-07 14:48:40
 * 
 */
public interface CasUserPointDao extends MyBatisBaseDao {

    /**
     * 获取用户最后一次签到时间
     * 
     * @auther zhangzheng
     * @date 2016-3-8 上午11:22:09
     * @param userid
     * @return
     */
    Date selectUserLastRegister(Integer userid);

    /**
     * 查询用户所有积分
     * 
     * @auther zhangzheng
     * @date 2016-3-8 下午6:12:24
     * @param userid
     * @return
     */
    Integer selectUserAllPoint(Integer userid);
    
    /**
     * 查询用户已使用积分
     * 
     * @auther zhangzheng
     * @date 2016-3-8 下午6:12:35
     * @param userid
     * @return
     */
    Integer selectUserIsUsedPoint(Integer userid);

    /**
     * 查询今年用户未使用积分
     * 
     * @auther zhangzheng
     * @date 2016-3-8 下午6:12:43
     * @param param
     * @return
     */
    List<CasUserPoint> selectTimeUpPoint(Map<String, Object> param);

    /**
     * 互动积分兑换消费积分时使用的查询
     * 
     * @auther zhangzheng
     * @date 2016-3-9 下午5:04:51
     * @param param
     * @return
     */
    List<CasUserPoint> selectChangePoint(Map<String, Object> param);

    /**
     * 使用积分
     * 
     * @auther zhangzheng
     * @date 2016-3-9 下午5:14:24
     * @param list
     */
    void updateUserPoint(List<CasUserPoint> list);

    /**
     * 获取用户累计积分
     * 
     * @auther zhangzheng
     * @date 2016-3-9 下午5:32:26
     * @param userid
     * @return
     */
    int getTotlePoint(Integer userid);
    
    /**
     * 定期删除积分
     * 
     * @auther zhangzheng
     * @date 2016-3-9 下午5:32:26
     * @param userid
     * @return
     */
    int deductPoint(Map<String, Object> param);
    
    List<CasUserPointPageTO> frontPage(Map<String, Object> param);
	
	int frontPageCount(Map<String, Object> param);

}
