package com.bluemobi.serviceimpl.cas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.appcore.util.TimeUtil;
import com.bluemobi.dao.cas.CasUserDao;
import com.bluemobi.dao.cas.CasUserPointDao;
import com.bluemobi.dao.cas.CasUserPointRuleDao;
import com.bluemobi.po.cas.CasUserPoint;
import com.bluemobi.po.cas.CasUserPointRule;
import com.bluemobi.service.cas.CasUserPointService;

/**
 * 【用户积分表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-03-07 14:48:40
 * 
 */
@Service(value = "casUserPointService")
public class CasUserPointServiceImpl extends MybatisBaseServiceImpl implements CasUserPointService {

    @Autowired
    private CasUserPointDao casUserPointDao;
    @Autowired
    private CasUserPointRuleDao casUserPointRuleDao;
    @Autowired
    private CasUserDao casUserDao;

    @Override
    public MyBatisBaseDao getDao() {
        return casUserPointDao;
    }

    @Override
    public int insertCasUserPoint(Integer pointType, Integer userid) {
        int i = 0;
        // 判断今日是否已签到
        boolean flag = checkTodayIsRegister(userid);
        if (flag) {
            i = 2;
            return i;
        }
        
        Date date = new Date();
        Map<String, Object> param = new HashMap<String, Object>();
        CasUserPoint point = new CasUserPoint();
        CasUserPointRule pointRule = null;
        param.put("pointType", pointType);
        List<CasUserPointRule> pointRules = casUserPointRuleDao.selectObjectList(param);
        if (pointRules != null && !pointRules.isEmpty()) {
            pointRule = pointRules.get(0);
        }
        if (pointRule != null) {
            point.setUserid(userid);
            point.setPointType(pointRule.getPointType());
            point.setPointName(pointRule.getPointName());
            point.setPoint(getPoint(pointRule.getPoint(), pointRule.getPointCoefficient()));
            point.setCtime(new Date());
            point.setIsUsed(0);
            point.setStatus(0);
            point.setOverdueTime(getDateTime(date));
            casUserPointDao.insert(point);
        } else {
            i = 1;
        }
        return i;
    }

    @Override
    public int insertCasUserPoint(Integer pointType, Double money, Integer userid) {
        int i = 0;
        Map<String, Object> param = new HashMap<String, Object>();
        CasUserPoint point = new CasUserPoint();
        param.put("pointType", pointType);
        CasUserPointRule pointRule = casUserPointRuleDao.selectObject(param);
        if (pointRule != null) {
            point.setUserid(userid);
            point.setPointType(pointRule.getPointType());
            point.setPointName(pointRule.getPointName());
            point.setPoint(getPoint(pointRule.getPoint(), money, pointRule.getPointCoefficient()));
            point.setCtime(new Date());
            casUserPointDao.insert(point);
        } else {
            i = 1;
        }
        return i;
    }

    /**
     * 根据积分规则表的积分和系数计算出应获得的积分
     * 
     * @auther zhangzheng
     * @date 2016-3-7 下午3:22:34
     * @param point
     *            积分
     * @param pointCoefficient
     *            系数
     * @return
     */
    private Integer getPoint(Integer point, BigDecimal pointCoefficient) {
        pointCoefficient = pointCoefficient.multiply(new BigDecimal(point));
        return pointCoefficient.intValue();
    }

    /**
     * 根据积分规则表的积分、金额和系数计算出应获得的积分
     * 
     * @auther zhangzheng
     * @date 2016-3-8 下午1:50:46
     * @param point
     * @param money
     * @param pointCoefficient
     * @return
     */
    private Integer getPoint(Integer point, Double money, BigDecimal pointCoefficient) {
        pointCoefficient = pointCoefficient.multiply(new BigDecimal(point));
        pointCoefficient = pointCoefficient.multiply(new BigDecimal(money));
        return pointCoefficient.intValue();
    }

    @Override
    public boolean checkTodayIsRegister(Integer userid) {
        Date date = casUserPointDao.selectUserLastRegister(userid);
        boolean flag = false;
        if (date != null) {
            flag = TimeUtil.checkIsSameDay(System.currentTimeMillis(), date.getTime());
        }
        return flag;
    }

    @Override
    public int updateUserPoint(Integer userid, Integer pointTypeId, Double money, boolean flag) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userid", userid);
        param.put("point_type", pointTypeId);
        param.put("status", 1);
        casUserPointDao.update(param);
        return 0;
    }

    @Override
    public List<CasUserPoint> getCanUsePoint(Integer userid) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userid", userid);
        param.put("status", 0);
        List<CasUserPoint> list = casUserPointDao.selectObjectList(param);
        return list;
    }

    @Override
    public int updateUserLevel(Integer userid) {
        Map<String, Object> userParam = new HashMap<String, Object>();
        Map<String, Object> lvParam = new HashMap<String, Object>();
        userParam.put("userid", userid);
        Integer allPoint = casUserPointDao.selectUserAllPoint(userid);
        Integer isUsedPoint = casUserPointDao.selectUserIsUsedPoint(userid);
        if (allPoint - isUsedPoint == 0) {
            lvParam.put("userLvId", 0);
        } else if (allPoint - isUsedPoint < 1999) {
            lvParam.put("userLvId", 1);
        } else if (allPoint - isUsedPoint < 9999) {
            lvParam.put("userLvId", 2);
        } else if (allPoint - isUsedPoint < 2999) {
            lvParam.put("userLvId", 3);
        } else {
            lvParam.put("userLvId", 4);
        }
        lvParam.put("userid", userid);
        casUserDao.update(lvParam);
        return 0;
    }

    @Override
    public int pointIsTimeUp(Integer userid) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userid", userid);
        Date date = new Date();
        param.put("date", date);
        List<CasUserPoint> points = casUserPointDao.selectTimeUpPoint(param);
        int sum = 0;
        if (points != null && !points.isEmpty()) {
            for (int i = 0; i < points.size(); i++) {
                sum += i;
            }
        }
        return sum;
    }

    @Override
    public int changePoint(Integer changePoint, Integer userid) {
        Date date = new Date();
        // 4表示互动积分
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userid", userid);
        param.put("overdueTime", new Date());
        param.put("status", 0);
        param.put("pointType", 4);
        List<CasUserPoint> points = casUserPointDao.selectObjectList(param);

        int sum = changePoint;
        List<CasUserPoint> updateIdList = new ArrayList<CasUserPoint>();
        CasUserPoint casUserPoint = null;
        int harfId = 0;
        if (changePoint != null) {
            for (CasUserPoint point : points) {
                if (changePoint - (point.getPoint() - point.getIsUsed()) > 0) {
                    changePoint = changePoint - (point.getPoint() - point.getIsUsed());
                    casUserPoint = new CasUserPoint();
                    casUserPoint.setStatus(1);
                    casUserPoint.setIsUsed(point.getPoint());
                    casUserPoint.setUsedTime(date);
                    casUserPoint.setPointId(point.getPointId());
                    updateIdList.add(casUserPoint);
                } else {
                    harfId = point.getPointId();
                    break;
                }
            }
            casUserPointDao.updateUserPoint(updateIdList);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("pointId", harfId);
            map.put("isUsed", changePoint);
            map.put("usedTime", date);
            casUserPointDao.update(map);
            CasUserPoint point = new CasUserPoint();
            point.setCtime(date);
            point.setPoint(sum);
            point.setOverdueTime(getDateTime(date));
            point.setIsUsed(0);
            point.setPointName("互动积分兑换");
            point.setUserid(userid);
            point.setStatus(0);
            casUserPointDao.insert(point);
        }
        return 0;
    }

    @Override
    public void deductPoint(Integer userid) {
        Date date = new Date();
        Date overdueTime = getThisYearFirstDay();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("overdueTime", overdueTime);
        param.put("userid", userid);
        param.put("date", date);
        casUserPointDao.deductPoint(param);
    }

    @Override
    public int getTotlePoint(Integer userid) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userid", userid);
        Integer totalPoint = casUserPointDao.getTotlePoint(userid);
        return totalPoint;
    }

    @SuppressWarnings("unused")
    private int getThisYear() {
        Date d = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        int year = c.get(Calendar.YEAR);
        return year;
    }

    /**
     * 获取一年后的今天
     * 
     * @auther zhangzheng
     * @date 2016-3-9 下午5:57:53
     * @param date
     * @return
     */
    private Date getDateTime(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int year = c.get(Calendar.YEAR);
        c.set(Calendar.YEAR, year + 1);
        Date d = c.getTime();
        return d;
    }

    private Date getThisYearFirstDay() {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.MONTH, 1);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c.getTime();
    }
}
