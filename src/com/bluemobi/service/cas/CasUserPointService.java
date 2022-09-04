package com.bluemobi.service.cas;

import java.util.List;

import com.appcore.service.MybatisBaseService;
import com.bluemobi.po.cas.CasUserPoint;

/**
 * 【用户积分表】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-03-07 14:48:40
 * 
 */
public interface CasUserPointService extends MybatisBaseService {

    /**
     * 新增积分
     * 
     * @auther zhangzheng
     * @date 2016-3-8 下午1:34:26
     * @param pointType
     *            积分类型
     * @param userId
     *            用户ID
     * @return
     */
    int insertCasUserPoint(Integer pointType, Integer userid);

    /**
     * 新增积分
     * 
     * @auther zhangzheng
     * @date 2016-3-8 下午1:35:08
     * @param pointType
     *            积分类型
     * @param money
     *            金额
     * @param userId
     *            用户ID
     * @return
     */
    int insertCasUserPoint(Integer pointType, Double money, Integer userid);

    /**
     * 校验用户今天是否已经签到过
     * 
     * @auther zhangzheng
     * @date 2016-3-8 上午11:22:26
     * @param userid
     * @return true:已经签到; false:未签到;
     */
    boolean checkTodayIsRegister(Integer userid);

    /**
     * 更新会员等级
     * 
     * @auther zhangzheng
     * @date 2016-3-8 下午5:41:49
     * @param userid
     * @return
     */
    int updateUserLevel(Integer userid);

    /**
     * 即将到期积分查询
     * 
     * @auther zhangzheng
     * @date 2016-3-8 下午6:01:27
     * @param userid
     * @return
     */
    int pointIsTimeUp(Integer userid);

    /**
     * 获取用户可用积分
     * 
     * @auther zhangzheng
     * @date 2016-3-8 下午6:14:35
     * @param userid
     * @return
     */
    List<CasUserPoint> getCanUsePoint(Integer userid);

    /**
     * 退货带来的成长、积分归还
     * 
     * @auther zhangzheng
     * @date 2016-3-8 下午6:19:48
     * @param userid
     * @param pointTypeId
     * @param money
     * @param flag
     *            新增还是减少，true:新增;false:减少;
     * @return
     */
    int updateUserPoint(Integer userid, Integer pointTypeId, Double money, boolean flag);

    /**
     * 互动积分兑换消费积分
     * 
     * @auther zhangzheng
     * @date 2016-3-9 下午4:44:22
     * @param changePoint
     * @param userid
     */
    int changePoint(Integer changePoint, Integer userid);

    /**
     * 定期扣除消费积分
     * 
     * @auther zhangzheng
     * @date 2016-3-9 下午4:50:18
     * @param userid
     */
    void deductPoint(Integer userid);

    /**
     * 获取用户累计积分
     * 
     * @auther zhangzheng
     * @date 2016-3-9 下午5:30:21
     * @param userid
     * @return
     */
    int getTotlePoint(Integer userid);

}
