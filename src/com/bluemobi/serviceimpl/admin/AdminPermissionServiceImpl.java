package com.bluemobi.serviceimpl.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.admin.AdminPermissionDao;
import com.bluemobi.po.admin.AdminPermission;
import com.bluemobi.service.admin.AdminPermissionService;

/**
 * 【应用权限表】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-11-17 11:43:15
 * 
 */
@Service(value = "adminPermissionService")
public class AdminPermissionServiceImpl extends MybatisBaseServiceImpl implements AdminPermissionService {

    public static final ConcurrentMap<String, AdminPermission> adminPermissionMap = new ConcurrentHashMap<String, AdminPermission>();
    @Autowired
    private AdminPermissionDao adminPermissionDao;

    @Override
    public MyBatisBaseDao getDao() {
        return adminPermissionDao;
    }
    

    public List<AdminPermission> getPermissionListByNavigationIdList(List<Integer> navigationIdList) {
        Map<String, Object> perMap = new HashMap<String, Object>();
        perMap.put("navigationIdList",navigationIdList);
        List<AdminPermission> userPermission = adminPermissionDao.getAdminPermissionByNavigationIdList(perMap);
        return userPermission;
    }

    
    /**
     * 根据用户id，查询用户的所有权限信息
     * @author haojian
     * @date 2015-11-24 上午11:15:40 
     * @param userid
     * @return
     * @return List<AdminPermission>
     */
    @Override
    public List<AdminPermission> selectAdminPermissionListByUserid(int userid) {
        return adminPermissionDao.selectAdminPermissionListByUserid(userid);
    }


    @Override
    public ConcurrentMap<String, AdminPermission> findAdminPermission() {
        if (adminPermissionMap.size() <= 0) {
            Map<String, Object> perMap = new HashMap<String, Object>();
            List<AdminPermission> list = adminPermissionDao.selectObjectList(perMap);
            for (int i = 0; i < list.size(); i++) {
                adminPermissionMap.put(list.get(i).getUrl(), list.get(i));
            }
            return adminPermissionMap;
        } else {
            return adminPermissionMap;
        }
    }


    @Override
    public void updateAdminPermissionAll() {
        Map<String, Object> perMap = new HashMap<String, Object>();
        List<AdminPermission> list = adminPermissionDao.selectObjectList(perMap);
        //清理原有数据
        adminPermissionMap.clear();
        //往Map中赋值
        for (int i = 0; i < list.size(); i++) {
            adminPermissionMap.put(list.get(i).getUrl(), list.get(i));
        }
    }


    @Override
    public boolean isAddPermissionByURI(String uri) {
        // 1,获得所有权限数据
        ConcurrentMap<String,AdminPermission> findAdminPermission = this.findAdminPermission();
        // 2,判断URI请求是否在权限数据中（不在数据中 直接放开过滤）
        AdminPermission adminPermission = findAdminPermission.get(uri);
        if (adminPermission == null) {
            return true;
        }
        return false;
    }
    

}
