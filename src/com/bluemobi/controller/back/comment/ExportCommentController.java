package com.bluemobi.controller.back.comment;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bluemobi.controller.AbstractBackController;
import com.bluemobi.service.comment.CommentContentService;
import com.bluemobi.util.excel.ExcelBean;
import com.bluemobi.util.excel.ExcelTO;

/**
 * 评论数据导出Excel表格
 * @author heweiwen
 * 2015-12-16 下午2:59:07
 */
@Controller
@RequestMapping("back/commentadmin")
public class ExportCommentController extends AbstractBackController {
    
    @Autowired
    private CommentContentService commentContentService;
	
	/**
	 * 导出excel表格
	 * @author HeWeiwen
	 * 2015-12-16
	 * @return
	 * @throws ParseException
	 * @throws UnsupportedEncodingException
	 */
    @RequestMapping("exportCommentExcel")
	public void exportExcel(Model model,String key,HttpServletRequest request,HttpServletResponse response) throws ParseException, UnsupportedEncodingException{
	    //加载公共数据
        initIndex(model);
		Map<String, Object> map = new HashMap<String, Object>();
		if (null != key && !"".equals(key)) {
            map.put("content", key);
        }
        //查询父级评论（第一层（pid：0）评论）
        map.put("pid", 0);
        map.put("isDel",0);
        List<Map<String, Object>> dataList = commentContentService.selectMapList(map);
        //重组对象数据
        List<Object[]> resultList = new ArrayList<Object[]>();
        for (Map<String, Object> dataMap : dataList) {
            Object[] objects = null;
            objects = new Object[6];
            objects[0] = dataMap.get("userid");
            objects[1] = dataMap.get("toId");
            objects[2] = dataMap.get("rankBase");
            objects[3] = dataMap.get("rankLogistics");
            objects[4] = dataMap.get("rankSpeed");
            objects[5] = dataMap.get("ctime");
            resultList.add(objects);
        }
        //组合报表对象
		String fileName = "评论信息报表";
		String detailTitle = "评论人,评论对象,商品满意度评分,物流评分,发货速度评分,评论时间";
		String[] titles = detailTitle.split(",");
		
		ExcelTO excelTO = new ExcelTO();
		excelTO.setTitleName(Arrays.asList(titles));
		excelTO.setResultList(resultList);
		excelTO.setResultName(fileName);
		//报表导出
		ExcelBean.exportDataToExcel(excelTO,response);
	}
}
