package cn.gzsxt.pms.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gzsxt.pms.service.ModularService;
import cn.gzsxt.pms.utils.AJAXResult;
import cn.gzsxt.pms.utils.Global;
import cn.gzsxt.pms.utils.Page;

@Controller
@RequestMapping("/modular")
public class ModularController {

	@Autowired
	private ModularService modularService;
	
	@RequestMapping("/toModularList")
	public String toModularList(@RequestParam Map<String, Object> condition,Integer index,HttpServletRequest request) {
		
		if (index==null) {
			index = 0;
		}
		Page page = modularService.findModularToPage(condition, index, Global.PAGE_SIZE);
		request.setAttribute("page", page);
		return "modularList";
	}
	//${pageContext.request.contextPath}/modular/toModularAdd.do
	@RequestMapping("/toModularAdd")
	public String toModularAdd() {
	
		return "modularAdd";
	}
	/**${pageContext.request.contextPath }/modular/addModular.do
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addModular")
	public Object addModular(@RequestParam Map<String, Object> entity) {
		System.out.println(entity);
		AJAXResult result = new AJAXResult();
		try {
			int count = modularService.addModular(entity);
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
	 * ${pageContext.request.contextPath }/modular/toModularEdit.do?modularId=
	 * @return
	 */
	@RequestMapping("/toModularEdit")
	public String toModularEdit(Long modularId,HttpServletRequest request) {
		
		Map<String, Object> modular = modularService.findModularById(modularId);
		request.setAttribute("modular", modular);
		return "modularEdit";
	}
	//${pageContext.request.contextPath }/modular/modularEdit.do
	@ResponseBody
	@RequestMapping("/modularEdit")
	public Object modularEdit(@RequestParam Map<String, Object> entity) {
		System.out.println("entity"+entity);
		AJAXResult result = new AJAXResult();
		try {
			modularService.modularEdit(entity);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	//${pageContext.request.contextPath }/modular/deleteModular.do
	@ResponseBody
	@RequestMapping("/deleteModular")
	public Object deleteModular(Long modularId) {
		AJAXResult result = new AJAXResult();
		try {
			modularService.deleteById(modularId);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	/**${pageContext.request.contextPath }/modular/deleteModular.do  批量删除
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteModulars")
	public Object deleteModulars(Long[] modularId) {
		AJAXResult result = new AJAXResult();
		System.out.println(modularId);
		try {
			modularService.deleteById((Object[])modularId);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
}
