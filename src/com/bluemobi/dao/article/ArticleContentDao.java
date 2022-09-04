package com.bluemobi.dao.article;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.appcore.dao.MyBatisBaseDao;
import com.bluemobi.po.article.ArticleContent;

/**
 * 【】 数据访问对象 接口
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-02-19 15:51:48
 * 
 */
public interface ArticleContentDao extends MyBatisBaseDao {
	
	void updateBatch(Map<String, Object> param);
	
	void deleteBatch(@Param("list") List<Integer> contentIdList);

	List<Map<String, Object>> selectObjectListBycondition();
	
	
}
