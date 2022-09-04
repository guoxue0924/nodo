package com.bluemobi.serviceimpl.admin;

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
import com.appcore.util.UUIDService;
import com.bluemobi.dao.admin.AdminGroupPermissionDao;
import com.bluemobi.dao.admin.AdminUserDao;
import com.bluemobi.dao.admin.AdminUserGroupDao;
import com.bluemobi.po.admin.AdminGroupPermission;
import com.bluemobi.po.admin.AdminUser;
import com.bluemobi.po.admin.AdminUserGroup;
import com.bluemobi.service.admin.AdminUserService;

/**
 * 【后台用户】 服务类 实现类
 * @author AutoCode 309444359@qq.com
 * @date 2015-07-17 11:22:43
 *
 */
@Service(value="adminUserService")
public class AdminUserServiceImpl extends MybatisBaseServiceImpl implements AdminUserService{
    
    @Autowired
    private AdminUserDao adminUserDao;
    @Autowired
    private AdminUserGroupDao adminUserGroupDao;
    @Autowired
    private AdminGroupPermissionDao adminGroupPermissionDao;
    
    @Override
    public MyBatisBaseDao getDao() {
        return adminUserDao;
    }

    public void insertUser(AdminUser adminUser) {
        //混淆吗
        String salt = UUIDService.getUUID().subSequence(0, 6).toString();
        //密码进行MD5加密
        String passwordMd5Md5 = StringUtil.md5(StringUtil.md5(adminUser.getPassword()) + salt);
                
        adminUser.setCtime(new Date());
        adminUser.setMtime(new Date());
        adminUser.setPassword(passwordMd5Md5);
        adminUser.setIsOnline((byte)0);
        adminUser.setSalt(salt);
        adminUser.setDomain("local");
        
        adminUserDao.insert(adminUser);
        
    }

    public void updateUser(AdminUser user) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("userid", user.getUserid());
        AdminUser adminUser = this.selectObject(map);
        String pwd = "";
        if (!"".equals(adminUser.getPassword())) {
            pwd = StringUtil.md5(user.getPassword())+adminUser.getSalt();
        }
        
        
        adminUser.setMtime(new Date());
        adminUser.setDomain(user.getDomain());
        adminUser.setEmail(user.getEmail());
        adminUser.setFullname(user.getFullname());
        adminUser.setGender(user.getGender());
        adminUser.setIdcard(user.getIdcard());
        adminUser.setNickname(user.getNickname());
        if (!"".equals(user.getPassword())) {
            adminUser.setPassword(StringUtil.md5(pwd));
        }
        adminUser.setStatus(user.getStatus());
        adminUser.setUsername(user.getUsername());
        
        adminUserDao.update(adminUser);
        
    }

    public void deleteUser(int userid) {
        //获得用户信息
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userid", userid);
        AdminUser user = this.selectObject(map);
        if (user!=null) {
            //删除用户对应的权限组关系 
            Map<String,Object> paraMap = new HashMap<String,Object>();
            paraMap.put("userId", user.getUserid());
            adminUserGroupDao.deleteAll(paraMap);
            
            Map<String,Object> paraMap2 = new HashMap<String,Object>();
            paraMap2.put("userid",userid);
            adminUserGroupDao.delete(paraMap2);
            //删除关联关系数据
        }
        //删除用户
        adminUserDao.delete(map);
        
    }

    public List<Integer> getGroupPermissions(List<AdminUserGroup> groupList) {
        List<Integer> groupIds = new ArrayList<Integer>();
        //数据转换
        for (AdminUserGroup i:groupList) {
            groupIds.add((int)i.getGroupId());
        }
        //根据权限组ID集合查询所有Permission对象
        Map<String, Object> reqMap = new HashMap<String, Object>();
        reqMap.put("grouplist", groupIds);
        List<AdminGroupPermission> list = adminGroupPermissionDao.getGroupPermissionList(reqMap);
        //数据转换
        List<Integer> permissionIds = new ArrayList<Integer>();
        for (AdminGroupPermission j:list) {
            permissionIds.add((int)j.getPermissionId());
        }
        return permissionIds;
    }
    
    public List<AdminUserGroup> getGroupPermissionsByUserId(int userId) {
        Map<String, Object> reqMap = new HashMap<String, Object>();
        reqMap.put("userId", userId);
        List<AdminUserGroup> userGroup = adminUserGroupDao.selectObjectList(reqMap);
        return userGroup;
    }
    
}


