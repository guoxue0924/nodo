package com.bluemobi.serviceimpl.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.admin.AdminGroupPermissionDao;
import com.bluemobi.po.admin.AdminGroupPermission;
import com.bluemobi.service.admin.AdminGroupPermissionService;

/**
 * 【】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-11-17 11:43:15
 * 
 */
@Service(value = "adminGroupPermissionService")
public class AdminGroupPermissionServiceImpl extends MybatisBaseServiceImpl implements AdminGroupPermissionService {

    @Autowired
    private AdminGroupPermissionDao adminGroupPermissionDao;

    @Override
    public MyBatisBaseDao getDao() {
        return adminGroupPermissionDao;
    }

    @Override
    public boolean hasPermission(int groupId, int permissionId) {
        Map<String, Object> reqMap = new HashMap<String, Object>();
        reqMap.put("groupId", groupId);
        reqMap.put("permissionId", permissionId);
        List<AdminGroupPermission> list = adminGroupPermissionDao.selectObjectList(reqMap);
        if (!list.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public void deleteAdminGroupPermission(int groupId, int permissionId) {
        Map<String, Object> reqMap = new HashMap<String, Object>();
        reqMap.put("groupId", groupId);
        reqMap.put("permissionId", permissionId);
        adminGroupPermissionDao.deleteByPermission(reqMap);
    }

    @Override
    public void deleteByPermissionId(int permissionId) {
        Map<String, Object> reqMap = new HashMap<String, Object>();
        reqMap.put("permissionId", permissionId);
        adminGroupPermissionDao.deleteByPermissionId(reqMap);
        
    }

    @Override
    public void assignPermission(int groupId, boolean isAssign, String permissionIds) {
        String[]  perArr = permissionIds.split(",");
        for (int i = 0; i < perArr.length; i++) {
            if (isAssign) {
                //2.1,判断没有该权限记录，创建权限记录
                if (!this.hasPermission(groupId, Integer.parseInt(perArr[i]))) {
                    AdminGroupPermission groupPermission = new AdminGroupPermission();
                    groupPermission.setGroupId(groupId);
                    groupPermission.setPermissionId(Integer.parseInt(perArr[i]));
                    adminGroupPermissionDao.insert(groupPermission);
                }
            } else {
                //2.2,取消授权，删除权限记录
                this.deleteAdminGroupPermission(groupId, Integer.parseInt(perArr[i]));
            }
        }
    }

}
