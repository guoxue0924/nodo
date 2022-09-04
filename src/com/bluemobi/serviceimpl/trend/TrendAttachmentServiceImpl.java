package com.bluemobi.serviceimpl.trend;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appcore.dao.MyBatisBaseDao;
import com.appcore.service.impl.MybatisBaseServiceImpl;
import com.bluemobi.dao.trend.TrendAttachmentDao;
import com.bluemobi.po.trend.TrendAttachment;
import com.bluemobi.service.trend.TrendAttachmentService;

/**
 * 【attachment user mapping】 服务类 实现类
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-14 10:33:23
 * 
 */
@Service(value = "trendAttachmentService")
public class TrendAttachmentServiceImpl extends MybatisBaseServiceImpl implements TrendAttachmentService {

    @Autowired
    private TrendAttachmentDao trendAttachmentDao;

    @Override
    public MyBatisBaseDao getDao() {
        return trendAttachmentDao;
    }

    /**
     * 上传附件
     * 
     * @auther zhangzheng
     * @date 2015-11-26 下午1:34:18
     * @param uploadResultMap
     * @param trendAttachment
     * @return
     */
    public Long insertUploadFile(Map<String, Object> uploadResultMap, TrendAttachment trendAttachment) {
        Date date = new Date();
        if ((Boolean) uploadResultMap.get("flag")) {
            String imageUrl = uploadResultMap.get("imageUrl").toString();
            String fileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1, imageUrl.length());
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
            Byte fileType = (Byte) uploadResultMap.get("fileType");
            String mediatype = "";
            String imageWidth = (String) uploadResultMap.get("imageWidth");
            String imageHeight = (String) uploadResultMap.get("imageHeight");
            if (fileType == 1) {
                mediatype = "image";
            } else if (fileType == 2) {
                mediatype = "word";
            } else if (fileType == 3) {
                mediatype = "flash";
            } else if (fileType == 4) {
                mediatype = "video";
            } else if (fileType == 5) {
                mediatype = "carousel";
            }

            trendAttachment.setUserid(Integer.valueOf(uploadResultMap.get("userid").toString()));
            trendAttachment.setTitle(fileName); // 附加文件的文件名
            trendAttachment.setDescription(""); // 附加文件注释
            trendAttachment.setLabel(""); // 用逗号分隔
            trendAttachment.setMediatype(mediatype);
            trendAttachment.setMimetype(mediatype + "/" + suffix); // MIME类型
            trendAttachment.setSuffix(suffix); // 文件后缀
            if (imageWidth != null && !"".equals(imageWidth)) {
                trendAttachment.setImageWidth(Integer.valueOf(imageWidth));
            }
            if (imageHeight != null && !"".equals(imageHeight)) {
                trendAttachment.setImageHeight(Integer.valueOf(imageHeight));
            }
            trendAttachment.setCtime(date);
            trendAttachment.setMtime(date);
            trendAttachment.setFilepath(imageUrl); // 存储系统中的文件路径
            trendAttachment.setFilesize(Integer.valueOf(uploadResultMap.get("filesize").toString()));
            trendAttachment.setHashcode(uploadResultMap.get("hashcode").toString()); // 文件MD5码
            trendAttachment.setStatus(Byte.valueOf("1"));
            trendAttachment.setRev(1); // 修改版本号，当前版本
        }
        trendAttachmentDao.insert(trendAttachment);
        return trendAttachment.getAttachmentid();
    }

    @Override
    public List<TrendAttachment> selectTrendAttachmentListByMainId(Map<String, Object> map) {
        return trendAttachmentDao.selectTrendAttachmentListByMainId(map);
    }

    @Override
    public List<TrendAttachment> selectTrendAttachmentListByIds(Map<String, Object> param) {
        return trendAttachmentDao.selectTrendAttachmentListByIds(param);
    }

}
