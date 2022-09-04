package com.bluemobi.service.groupon;

import com.appcore.service.MybatisBaseService;
import com.bluemobi.po.groupon.GrouponCategory;
import com.bluemobi.to.ResultTO;

/**
 * 【团购标签】 服务类 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-20 16:51:18
 * 
 */
public interface GrouponCategoryService extends MybatisBaseService {

    /**
     * 保存GrouponCategory对象, 根据是否有categoryId来判断更新还是创建
     * @author liuyt
     * @date 2015-10-23 上午11:29:31
     * @param category
     * @return
     * @version
     */
    ResultTO saveGrouponCategory(GrouponCategory category);

    /**
     * 删除GrouponCategory对象, 只能删除未使用的活动
     * @author liuyt
     * @date 2015-10-23 上午11:30:06
     * @param categoryId
     * @return
     * @version
     */
    ResultTO deleteCategory(Integer categoryId);

}
