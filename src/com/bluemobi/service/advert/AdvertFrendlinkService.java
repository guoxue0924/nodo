package com.bluemobi.service.advert;

import com.appcore.service.MybatisBaseService;
import com.bluemobi.po.advert.AdvertFrendlink;
import com.bluemobi.to.ResultTO;

/**
 * 【】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-02-25 13:46:10
 * 
 */
public interface AdvertFrendlinkService extends MybatisBaseService {

	/** 
	 * saveFriendLink
	 * 
	 * @author kevin
	 * @param friendLink
	 * @return 
	 * @since JDK 7 
	 */  
	ResultTO saveFriendLink(AdvertFrendlink friendLink);

	/** 
	 * deleteFriendLink
	 * 
	 * @author kevin
	 * @param linkId
	 * @return 
	 * @since JDK 7 
	 */  
	ResultTO deleteFriendLink(Integer linkId);

	/** 
	 * showFriendLink
	 * 
	 * @author kevin
	 * @param linkId
	 * @param isPulished
	 * @return 
	 * @since JDK 7 
	 */  
	ResultTO showFriendLink(Integer linkId, boolean isPulished);

}
