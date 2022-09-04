package com.bluemobi.controller.back.report;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bluemobi.controller.AbstractBackController;
import com.bluemobi.to.report.BasicLineTO;

/**
 * 报表系统测试
 * @author heweiwen
 * 2015-11-12 下午5:52:28
 */
@Controller
@RequestMapping("back/report")
public class ReportController extends AbstractBackController{
    
    /**
     * 初始化Basic line基线报表页面
     * @author HeWeiwen
     * 2015-11-13
     * @param model
     * @param req
     * @return
     */
    @RequestMapping(value ="basicline" , method = RequestMethod.GET)
    public String indexBasicline(Model model,HttpServletRequest req) {
        //伪造数据
        double[] dateStr1 = new double[]{7.1, 6.0, 9.0, 14.0, 18.0, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9,1.0};
        double[] dateStr2 = new double[]{-0.2, 0.8, 5.7, 11.3, 17.0, 22.0, 24.8, 24.1, 20.1, 14.1, 8.6, 2.0};
        double[] dateStr3 = new double[]{-0.9, 0.6, 3.5, 8.4, 13.5, 17.0, 18.6, 17.9, 14.3, 9.0, 3.9, 3.0};
        double[] dateStr4 = new double[]{3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.0};
        List<double[]> dataStrList = new ArrayList<double[]>();
        dataStrList.add(dateStr1);
        dataStrList.add(dateStr2);
        dataStrList.add(dateStr3);
        dataStrList.add(dateStr4);
        String names ="Tokyo,New York,Berlin,London";
        String[] nameStr = names.split(",");
        String categoriesStr = "Jan,Feb,Mar,Apr,May,Jun,Jul,Aug,Sep,Oct,Nov,Dec".trim();
        String yAxisTitle = "'Temperature (°C)'";
        
        //加载公共数据
        initIndex(model);
        String[] categories = categoriesStr.split(",");
        
        List<BasicLineTO> seriesList = new ArrayList<BasicLineTO>();
        for (int i = 0; i < nameStr.length; i++) {
            BasicLineTO lineTO = new BasicLineTO();
            lineTO.setName(nameStr[i]);
            lineTO.setData(dataStrList.get(i));
            seriesList.add(lineTO);
        }
        String seriesStr = JSONArray.fromObject(seriesList).toString();
        
        model.addAttribute("categories", JSONArray.fromObject(categories).toString());
        model.addAttribute("title", yAxisTitle);
        model.addAttribute("series", seriesStr);
        return "back/report/report.basicline";
    }

    
    /**
     * 初始化Basic column报表页面
     * @author HeWeiwen
     * 2015-11-13
     * @param model
     * @param req
     * @return
     */
    @RequestMapping(value = "basiccolumn", method = RequestMethod.GET)
    public String indexBasiccolumn(Model model,HttpServletRequest req) {
        //伪造数据
        double[] dateStr1 = new double[]{7.1, 6.0, 9.0, 14.0, 18.0, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9,1.0};
        double[] dateStr2 = new double[]{-0.2, 0.8, 5.7, 11.3, 17.0, 22.0, 24.8, 24.1, 20.1, 14.1, 8.6, 2.0};
        double[] dateStr3 = new double[]{-0.9, 0.6, 3.5, 8.4, 13.5, 17.0, 18.6, 17.9, 14.3, 9.0, 3.9, 3.0};
        double[] dateStr4 = new double[]{3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.0};
        List<double[]> dataStrList = new ArrayList<double[]>();
        dataStrList.add(dateStr1);
        dataStrList.add(dateStr2);
        dataStrList.add(dateStr3);
        dataStrList.add(dateStr4);
        String names ="Tokyo,New York,Berlin,London";
        String[] nameStr = names.split(",");
        String categoriesStr = "Jan,Feb,Mar,Apr,May,Jun,Jul,Aug,Sep,Oct,Nov,Dec".trim();
        String yAxisTitle = "'Temperature(°C)'";
        
        //加载公共数据
        initIndex(model);
        String[] categories = categoriesStr.split(",");
        
        List<BasicLineTO> seriesList = new ArrayList<BasicLineTO>();
        for (int i = 0; i < nameStr.length; i++) {
            BasicLineTO lineTO = new BasicLineTO();
            lineTO.setName(nameStr[i]);
            lineTO.setData(dataStrList.get(i));
            seriesList.add(lineTO);
        }
        String seriesStr = JSONArray.fromObject(seriesList).toString();
        
        model.addAttribute("categories", JSONArray.fromObject(categories).toString());
        model.addAttribute("title", yAxisTitle);
        model.addAttribute("series", seriesStr);
        return "back/report/report.basiccolumn";
    }
    
    /**
     * 初始化Pie报表页面
     * @author HeWeiwen
     * 2015-11-13
     * @param model
     * @param req
     * @return
     */
    @RequestMapping(value = "pie", method = RequestMethod.GET)
    public String indexPie(Model model,HttpServletRequest req) {
        //加载公共数据
        initIndex(model);
        return "back/report/report.pie";
    }
    
    /**
     * 初始化rotation Demo报表页面
     * @author HeWeiwen
     * 2015-11-13
     * @param model
     * @param req
     * @return
     */
    @RequestMapping(value = "rotationdemo", method = RequestMethod.GET)
    public String indexRotationdemo(Model model,HttpServletRequest req) {
        //加载公共数据
        initIndex(model);
        return "back/report/report.rotationdemo";
    }
}

