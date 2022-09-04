package com.bluemobi.serviceimpl.comment;

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
import com.bluemobi.dao.comment.CommentContentDao;
import com.bluemobi.po.comment.CommentContent;
import com.bluemobi.service.comment.CommentContentService;
import com.bluemobi.to.comment.AddGoodsCommentTO;
import com.bluemobi.to.comment.CommentDetailTO;
import com.bluemobi.to.comment.ItemCommentTO;
import com.bluemobi.to.comment.OrderCommentTO;

/**
 * 【】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-21 14:55:28
 * 
 */
@Service(value = "commentContentService")
public class CommentContentServiceImpl extends MybatisBaseServiceImpl implements CommentContentService {

    @Autowired
    private CommentContentDao commentContentDao;

    @Override
    public MyBatisBaseDao getDao() {
        return commentContentDao;
    }

    @Override
    public void deleteLogic(String id) {
        String[] strs = id.split(",");
        //循环修改状态(是否标记为删除。1：是；0：否；)6
        for (int i = 0; i < strs.length; i++) {
            Map<String, Object> reqMap = new HashMap<String, Object>();
            reqMap.put("id", Integer.parseInt(strs[i]));
            CommentContent commentContent = commentContentDao.selectObject(reqMap);
            commentContent.setIsDel((byte)1);
            commentContentDao.update(commentContent);
        }
        
    }

    public List<CommentContent> getCommentContentList(int categoryId, int toId) {
        Map<String, Object> reqMap = new HashMap<String, Object>();
        reqMap.put("categoryId", categoryId);
        reqMap.put("toId", toId);
        List<CommentContent> commentContentList = commentContentDao.getCommentContentList(reqMap);
        return commentContentList;
    }

    @Override
    public void saveCommentContentToApi(AddGoodsCommentTO addGoodsCommentTO) {
        //1，数据重组
        CommentContent commentContent = new CommentContent();
        
        commentContent.setCategoryId(addGoodsCommentTO.getCategoryId());
        commentContent.setContent(addGoodsCommentTO.getContent());
        commentContent.setCtime(new Date());
        commentContent.setMtime(new Date());
        commentContent.setRankBase(addGoodsCommentTO.getRankBase().byteValue());
        commentContent.setRankLogistics(addGoodsCommentTO.getRankLogistics().byteValue());
        commentContent.setRankSpeed(addGoodsCommentTO.getRankSpeed().byteValue());
        commentContent.setUserid((long)addGoodsCommentTO.getUserId());
        commentContent.setToId(addGoodsCommentTO.getToId().longValue());
        commentContent.setPid(addGoodsCommentTO.getPid());
        commentContent.setTitle(addGoodsCommentTO.getTitle());
        commentContent.setToOrderItemId(addGoodsCommentTO.getToOrderItemId().longValue());
        commentContent.setToStoreId(addGoodsCommentTO.getToStoreId());
        
        //2，持久化对象
        commentContentDao.insert(commentContent);
        
    }

	/** 
	 * @see com.bluemobi.service.comment.CommentContentService#addComment(com.bluemobi.to.comment.OrderCommentTO, int, String) 
	 */  
	@Override
	public void addComment(OrderCommentTO commentTO, int userid, String ip) {
		List<CommentContent> contents = new ArrayList<CommentContent>();
		CommentContent content = null;
		for(ItemCommentTO ito : commentTO.getItemComments()) {
			content = new CommentContent();
			content.setToOrderItemId(ito.getItemId());
			content.setContent(ito.getContent());
			content.setRankBase(ito.getRankBase().byteValue());
			content.setRankLogistics(commentTO.getRankLogistics().byteValue());
			content.setRankSpeed(commentTO.getRankSpeed().byteValue());
			content.setUserid((long)userid);
			content.setIp(ip);
			content.setCtime(Calendar.getInstance().getTime());
			content.setToId(ito.getSkuId());
			content.setCategoryId(2);
			contents.add(content);
		}
		commentContentDao.batchInsert(contents);
	}

	/** 
	 * @see com.bluemobi.service.comment.CommentContentService#getCommentDetail(java.lang.Integer) 
	 */  
	@Override
	public CommentDetailTO getCommentDetail(Integer id) {
		return commentContentDao.getCommentDetail(id);
	}

	/** 
	 * @see com.bluemobi.service.comment.CommentContentService#getCommentStatistics(java.lang.Long) 
	 */  
	@Override
	public Map<String, Object> getCommentStatistics(Long skuId) {
		Map<String, Object> ret = new HashMap<String, Object>();
		List<Map<String, Object>> statisticsList = commentContentDao.getCommentStatistics(skuId);
		BigDecimal totalRank = new BigDecimal(0);
		for (Map<String, Object> map : statisticsList) {
			switch (((Long)map.get("rankBase")).intValue()) {
			case 0:
				ret.put("totalUser", map.get("totalUser"));
				totalRank = (BigDecimal) map.get("totalRank");
				break;
			case 1:
				ret.put("badComment", map.get("totalUser"));
				ret.put("badCommentRank", map.get("totalRank"));
				break;
			case 2:
				ret.put("badComment", ((Long)ret.get("badComment") + (Long)map.get("totalUser")));
				ret.put("badCommentRank", ((BigDecimal)ret.get("badCommentRank")).add((BigDecimal)map.get("totalRank")));
				break;
			case 3:
				ret.put("normalComment", map.get("totalUser"));
				ret.put("normalCommentRank", map.get("totalRank"));
				break;
			case 4:
				ret.put("normalComment", ((Long)ret.get("normalComment") + (Long)map.get("totalUser")));
				ret.put("normalCommentRank", ((BigDecimal)ret.get("normalCommentRank")).add((BigDecimal)map.get("totalRank")));
				break;
			case 5:
				ret.put("goodComment", map.get("totalUser"));
				ret.put("goodCommentRank", map.get("totalRank"));
				break;
			default:
				break;
			}
		}
		if(ret.get("badComment") == null) {
			ret.put("badComment", 0);
		}
		BigDecimal rank = new BigDecimal(0);
		if(ret.get("badCommentRank") != null) {
			rank = ((BigDecimal)ret.get("badCommentRank")).divide(totalRank);
		}
		ret.put("badCommentRank", rank.doubleValue());
		
		if(ret.get("normalComment") == null) {
			ret.put("normalComment", 0);
		}
		rank = new BigDecimal(0);
		if(ret.get("normalCommentRank") != null) {
			rank = ((BigDecimal)ret.get("normalCommentRank")).divide(totalRank);
		}
		ret.put("normalCommentRank", rank.doubleValue());
		
		if(ret.get("goodComment") == null) {
			ret.put("goodComment", 0);
		}
		rank = new BigDecimal(0);
		if(ret.get("goodCommentRank") != null) {
			rank = ((BigDecimal)ret.get("goodCommentRank")).divide(totalRank);
		}
		ret.put("goodCommentRank", rank.doubleValue());
		return ret;
	}

}
