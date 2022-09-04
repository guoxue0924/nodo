 package com.bluemobi.serviceimpl.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.admin.AdminUserGroupDao;
import com.bluemobi.po.admin.AdminUserGroup;
import com.bluemobi.service.admin.AdminUserGroupService;

/**
 * 【】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-11-17 11:43:15
 * 
 */
@Service(value = "adminUserGroupService")
public class AdminUserGroupServiceImpl extends MybatisBaseServiceImpl implements AdminUserGroupService {

    @Autowired
    private AdminUserGroupDao adminUserGroupDao;
    @Override
    public MyBatisBaseDao getDao() {
        return adminUserGroupDao;
    }

    @Override
    public void updateUserGroup(int userid, int[] groupids) {
        Map<String, Object> delUser = new HashMap<String, Object>();
        delUser.put("userId", userid);
        adminUserGroupDao.deleteAll(delUser);
        for (int i = 0; i < groupids.length; i++) {
            AdminUserGroup group = new AdminUserGroup();
            group.setGroupId(groupids[i]);
            group.setUserId(userid);
            adminUserGroupDao.insert(group);
        }
        
    }

    @Override
    public List<AdminUserGroup> getAdminUserGroupByUserId(int userid) {
        Map<String, Object> userMap = new HashMap<String, Object>();
        userMap.put("userid", userid);
        List<AdminUserGroup> list = null;
        list = adminUserGroupDao.selectObjectList(userMap);
        if (!list.isEmpty()) {
            return list;
        }
        return list;
    }

    @Override
    public void deleteByUserId(int userid) {
        //根据userId删除所有用户组数据
        Map<String, Object> delUser = new HashMap<String, Object>();
        delUser.put("userId", userid);
        adminUserGroupDao.deleteAll(delUser);
    }

}
