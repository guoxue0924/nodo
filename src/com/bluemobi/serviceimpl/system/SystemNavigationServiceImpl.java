package com.bluemobi.serviceimpl.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.appcore.util.StringUtil;
import com.bluemobi.dao.system.SystemNavigationDao;
import com.bluemobi.po.system.SystemNavigation;
import com.bluemobi.service.system.SystemNavigationService;

/**
 * 【system navigation category】 服务类 实现类
 * @author AutoCode 309444359@qq.com
 * @date 2015-07-16 17:15:49
 *
 */
@Service(value="systemNavigationService")
public class SystemNavigationServiceImpl extends MybatisBaseServiceImpl implements SystemNavigationService{
    
    @Autowired
    private SystemNavigationDao systemNavigationDao;
    
    /**返回状态为：1 表示插入数据成功*/
    private static final int RESULT_TYPE = 1;
    
    @Override
    public MyBatisBaseDao getDao() {
        return systemNavigationDao;
    }

    public List<SystemNavigation> getSystemNavigationsByWhere() {
        Map<Object, Object> reqMap = new HashMap<Object, Object>();
        reqMap.put("link", "");
        return systemNavigationDao.selectObjectListByLinkNull(reqMap);
    }
    
    public List<SystemNavigation> findAllSystemNavigation() {
        Map<Object, Object> reqMap = new HashMap<Object, Object>();
        reqMap.put("status", -1);
        return systemNavigationDao.selectObjectListByStatus(reqMap);
    }

    public void insertNavigation(SystemNavigation navigation) {
        SystemNavigation navigationNew = navigation;
        navigationNew.setLoadType((byte)1);
        navigationNew.setCtime(new Date());
        navigationNew.setMtime(new Date());
        int resultInt = systemNavigationDao.insert(navigationNew);
        if (resultInt == RESULT_TYPE ) {
            SystemNavigation navigationResult = (SystemNavigation) systemNavigationDao.selectObjectList(navigation).get(0);
            String hid = "";
            if(navigationResult.getParentId()!=0){
                Map<String, Object> reqMap = new  HashMap<String, Object>();
                reqMap.put("navigationId", navigationResult.getParentId());
                SystemNavigation fj = this.systemNavigationDao.selectObject(reqMap);
                hid = fj.getHid() + ":" + StringUtil.lPad( navigationResult.getNavigationId()+"" , 4, "0");
            }else{
                hid = "0:" + StringUtil.lPad( navigationResult.getNavigationId()+"" , 4, "0");
            }
            navigationResult.setHid(hid);
            //修改用户数据
            systemNavigationDao.update(navigationResult);
        }
    }

    public void updateNavigation(SystemNavigation navigation) {
        
        SystemNavigation navigationNew = navigation;
        String hid = "";
        if(navigationNew.getParentId()!=0){
            Map<String, Object> reqMap = new  HashMap<String, Object>();
            reqMap.put("navigationId", navigationNew.getParentId());
            SystemNavigation fj = systemNavigationDao.selectObject(reqMap);
            hid = fj.getHid() + ":" + StringUtil.lPad( navigationNew.getNavigationId()+"" , 4, "0");
        }else{
            hid = "0:" + StringUtil.lPad( navigationNew.getNavigationId()+"" , 4, "0");
        }
        
        navigationNew.setHid(hid);
        navigationNew.setMtime(new Date());
        navigationNew.setLoadType((byte)1);
        //修改菜单
        systemNavigationDao.update(navigationNew);
    }

    public List<SystemNavigation> findSystemNavigationByParentId(int parentId) {
        HashMap<Object, Object> reqMap = new HashMap<Object, Object>();
        reqMap.put("parentId", parentId);
        return systemNavigationDao.selectObjectListByParentId(reqMap);
    }

    @Override
    public List<Integer> getSytemNavigationByParentId(int parentId) {
        List<Integer> navigationIdList = new ArrayList<Integer>();
        Map<String,Object> parentMap = new HashMap<String, Object>();
        parentMap.put("parentId", parentId);
        List<SystemNavigation> sysNavList =systemNavigationDao.selectObjectList(parentMap);
        for (SystemNavigation data:sysNavList) {
            navigationIdList.add(data.getNavigationId());
        }
        return navigationIdList;
    }

    /**
     * 根据用户id查询用户的一级导航和二级导航
     * 导航类结构：一级导航里面有一个二级导航的list
     * @author haojian
     * @date 2015-11-24 下午1:26:05 
     * @param userid
     * @return
     * @return List<SystemNavigation>
     */
    @Override
    public List<SystemNavigation> selectSystemNavigationByUserid(int userid) {
        //1、查询出用户的二级导航
        List<SystemNavigation> secondNavigationList = systemNavigationDao.selectSecondNavigationsByUserid(userid);
        
        List<SystemNavigation> firstNavigationList = new ArrayList<SystemNavigation>();
        //当查询二级菜单不为空时，查询权限菜单数据
        if (secondNavigationList.size() > 0) {
          //2、根据二级导航 查询出用户的一级导航
           firstNavigationList = systemNavigationDao.selectFirstNavigationsBySecondNavigations(secondNavigationList);
            //3、将用户的二级导航放到一级导航下面
            //临时map，便于根据id快速搜索到一级导航
            Map<Integer,SystemNavigation> firstNavigationMap = new HashMap<Integer,SystemNavigation>();
            for(SystemNavigation f : firstNavigationList){
                firstNavigationMap.put(f.getNavigationId(), f);
            }
            //遍历二级菜单，并把二级菜单放到相应的一级菜单下面
            for(SystemNavigation s : secondNavigationList){
                SystemNavigation f = firstNavigationMap.get(s.getParentId());
                f.getSubList().add(s);
            }
        }
        return firstNavigationList;
    }

    @Override
    public List<SystemNavigation> selectObjectListLikeByHid(int hid) {
         Map<String,Object> reqMap = new HashMap<String, Object>();
         reqMap.put("hid", hid);
        return systemNavigationDao.selectObjectListLikeByHid(reqMap);
    }

 
}


