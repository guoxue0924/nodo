package com.bluemobi.serviceimpl.advert;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.advert.AdvertFrendlinkDao;
import com.bluemobi.po.advert.AdvertFrendlink;
import com.bluemobi.service.advert.AdvertFrendlinkService;
import com.bluemobi.to.ResultTO;

/**
 * 【】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-02-25 13:46:10
 * 
 */
@Service(value = "advertFrendlinkService")
public class AdvertFrendlinkServiceImpl extends MybatisBaseServiceImpl implements AdvertFrendlinkService {

    @Autowired
    private AdvertFrendlinkDao advertFrendlinkDao;

    @Override
    public MyBatisBaseDao getDao() {
        return advertFrendlinkDao;
    }

	/** 
	 * @see com.bluemobi.service.advert.AdvertFrendlinkService#saveFriendLink(com.bluemobi.po.advert.AdvertFrendlink) 
	 */  
	@Override
	public ResultTO saveFriendLink(AdvertFrendlink friendLink) {
		//查询是否有同名标签
        Map<String, Object> parameter = new HashMap<String, Object>();
        parameter.put("linkName", friendLink.getLinkName());
        List<AdvertFrendlink> sameNameLink = this.selectObjectList(parameter);
		
        int ret = 0;
        // 新增
        if(friendLink.getLinkId() == null || friendLink.getLinkId() == 0) {
            if(sameNameLink.isEmpty()) {
            	friendLink.setCtime(Calendar.getInstance().getTime());
                ret = this.insert(friendLink);
            } else {
        		return ResultTO.newFailResultTO("保存失败,已存在相同名称的友情链接", null);
            }
        } else {
            //修改
        	if(sameNameLink.size() == 1) {
        		AdvertFrendlink sameAdvertFrendlink = sameNameLink.get(0);
        		if(sameAdvertFrendlink.getLinkId().intValue() == friendLink.getLinkId().intValue()) {
        			friendLink.setMtime(Calendar.getInstance().getTime());
        			ret = this.update(friendLink);
        		} else {
        			return ResultTO.newFailResultTO("保存失败,已存在相同名称的友情链接", null);
        		}
        	} else {
        		friendLink.setMtime(Calendar.getInstance().getTime());
    			ret = this.update(friendLink);
        	}
        }
		if(ret == 1) {
			return ResultTO.newSuccessResultTO(null);
		} else {
			return ResultTO.newFailResultTO("操作失败", null);
		}
	}

	/** 
	 * @see com.bluemobi.service.advert.AdvertFrendlinkService#deleteFriendLink(java.lang.Integer) 
	 */  
	@Override
	public ResultTO deleteFriendLink(Integer linkId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("linkId", linkId);
		int ret = this.advertFrendlinkDao.delete(param);
		if(ret == 1) {
			return ResultTO.newSuccessResultTO(null);
		} else {
			return ResultTO.newFailResultTO("删除失败", null);
		}
	}

	/** 
	 * @see com.bluemobi.service.advert.AdvertFrendlinkService#showFriendLink(java.lang.Integer, boolean) 
	 */  
	@Override
	public ResultTO showFriendLink(Integer linkId, boolean isPulished) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("linkId", linkId);
		param.put("status", isPulished ? 0 : 1);
		int ret = this.advertFrendlinkDao.update(param);
		if(ret == 1) {
			return ResultTO.newSuccessResultTO(null);
		} else {
			return ResultTO.newFailResultTO("操作失败", null);
		}
	}

}
