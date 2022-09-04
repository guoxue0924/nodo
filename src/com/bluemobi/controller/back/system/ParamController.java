package com.bluemobi.controller.back.system;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.controller.AbstractBackController;
import com.bluemobi.service.system.SystemParamService;
import com.bluemobi.to.ResultTO;

/**
 * 系统参数控制器 ClassName: ParamController Date: 2016年2月18日下午5:05:40
 * 
 * @author kevin
 * @version
 * @since JDK 7
 */
@Controller
@RequestMapping("back/systemParam")
public class ParamController extends AbstractBackController {

	@Autowired
	private SystemParamService systemParamService;

	/**
	 * 初始化左侧菜单
	 * indexSystemParam
	 * 
	 * @author kevin
	 * @param model
	 * @param req
	 * @return 
	 * @since JDK 7
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String indexSystemParam(Model model) {
		// 1,查询所有菜单数据
		Map<String, String> paramMap = systemParamService.getAllSystemParam();
		// 2,加载公共数据
		initIndex(model);
		model.addAttribute("paramMap", paramMap);
		return "back/system/param.index";
	}

	/**
	 * 保存参数值
	 * saveParamValue
	 * 
	 * @author kevin
	 * @param navigation
	 * @return 
	 * @since JDK 7
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public ResultTO saveParamValue(String paramName, String paramValue) {
		// 修改菜单
		systemParamService.saveSystemParam(paramName, paramValue);
		return ResultTO.newSuccessResultTO(null);
	}

}
