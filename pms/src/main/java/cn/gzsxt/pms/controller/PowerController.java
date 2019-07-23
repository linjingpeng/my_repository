package cn.gzsxt.pms.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gzsxt.pms.service.DictionaryService;
import cn.gzsxt.pms.service.ModularService;
import cn.gzsxt.pms.service.PowerService;
import cn.gzsxt.pms.utils.AJAXResult;
import cn.gzsxt.pms.utils.Global;
import cn.gzsxt.pms.utils.Page;

@Controller
@RequestMapping("/power")
public class PowerController {

	@Autowired
	private PowerService powerService;
	
	@Autowired
	private DictionaryService dicService;
	
	@Autowired
	private ModularService modularService;
	@RequestMapping("/toPowerList")
	public String toPowerList(@RequestParam Map<String, Object> condition,Integer index,HttpServletRequest request) {
		
		if (index==null) {
			index = 0;
		}
		Page page = powerService.findPowerToPage(condition, index, Global.PAGE_SIZE);
		request.setAttribute("page", page);
		return "powerList";
	}
	//${pageContext.request.contextPath}/power/toPowerAdd.do
	@RequestMapping("/toPowerAdd")
	public String toPowerAdd(HttpServletRequest request) {
		List<Map<String, Object>> modulars = modularService.findAllModular();
		request.setAttribute("modulars", modulars);
		List<Map<String, Object>> isShow = dicService.findDictionaryByTypeCode(1005);
		request.setAttribute("isShow", isShow);
		List<Map<String, Object>> powers = powerService.findAllPower();
		request.setAttribute("powers", powers);
		return "powerAdd";
	}
	/**${pageContext.request.contextPath }/power/addPower.do
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addPower")
	public Object addPower(@RequestParam Map<String, Object> entity) {
		System.out.println(entity);
		AJAXResult result = new AJAXResult();
		try {
			int count = powerService.addPower(entity);
			if (count>0) {
				result.setSuccess(true);
			}else {
				result.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	/**跳转户主编辑页面
	 * ${pageContext.request.contextPath }/power/toPowerEdit.do?powerId=
	 * @return
	 */
	@RequestMapping("/toPowerEdit")
	public String toPowerEdit(Long powerId,HttpServletRequest request) {
		
		Map<String, Object> power = powerService.findPowerById(powerId);
		request.setAttribute("power", power);
		List<Map<String, Object>> modulars = modularService.findAllModular();
		request.setAttribute("modulars", modulars);
		List<Map<String, Object>> isShow = dicService.findDictionaryByTypeCode(1005);
		request.setAttribute("isShow", isShow);
		List<Map<String, Object>> powers = powerService.findAllPower();
		request.setAttribute("powers", powers);
		return "powerEdit";
	}
	//${pageContext.request.contextPath }/power/powerEdit.do
	@ResponseBody
	@RequestMapping("/powerEdit")
	public Object powerEdit(@RequestParam Map<String, Object> entity) {
		System.out.println("entity"+entity);
		AJAXResult result = new AJAXResult();
		try {
			powerService.powerEdit(entity);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	//${pageContext.request.contextPath }/power/deletePower.do
	@ResponseBody
	@RequestMapping("/deletePower")
	public Object deletePower(Long powerId) {
		AJAXResult result = new AJAXResult();
		try {
			powerService.deleteById(powerId);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	/**${pageContext.request.contextPath }/power/deletePower.do  批量删除
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deletePowers")
	public Object deletePowers(Long[] powerId) {
		AJAXResult result = new AJAXResult();
		System.out.println(powerId);
		try {
			powerService.deleteById((Object[])powerId);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
}
