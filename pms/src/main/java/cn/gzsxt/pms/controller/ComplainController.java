package cn.gzsxt.pms.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gzsxt.pms.service.ComplainService;
import cn.gzsxt.pms.service.DictionaryService;
import cn.gzsxt.pms.utils.AJAXResult;
import cn.gzsxt.pms.utils.Global;
import cn.gzsxt.pms.utils.Page;

@Controller
@RequestMapping("/complain")
public class ComplainController {

	@Autowired
	private ComplainService complainService;
	
	@Autowired
	private DictionaryService dicService;
	
	@RequestMapping("/toComplainList")
	public String toComplainList(@RequestParam Map<String, Object> condition,Integer index,HttpServletRequest request) {
		
		if (index==null) {
			index = 0;
		}
		Page page = complainService.findComplainToPage(condition, index, Global.PAGE_SIZE);
		request.setAttribute("page", page);
		return "complainList";
	}
	//${pageContext.request.contextPath}/complain/toComplainAdd.do
	@RequestMapping("/toComplainAdd")
	public String toComplainAdd(HttpServletRequest request) {
		List<Map<String, Object>> status = dicService.findDictionaryByTypeCode(1000);
		request.setAttribute("status", status);
		return "complainAdd";
	}
	/**${pageContext.request.contextPath }/complain/addComplain.do
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addComplain")
	public Object addComplain(@RequestParam Map<String, Object> entity) {
		System.out.println(entity);
		AJAXResult result = new AJAXResult();
		try {
			int count = complainService.addComplain(entity);
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
	 * ${pageContext.request.contextPath }/complain/toComplainEdit.do?complainId=
	 * @return
	 */
	@RequestMapping("/toComplainEdit")
	public String toComplainEdit(Long complainId,HttpServletRequest request) {
		
		Map<String, Object> complain = complainService.findComplainById(complainId);
		request.setAttribute("complain", complain);
		List<Map<String, Object>> status = dicService.findDictionaryByTypeCode(1000);
		request.setAttribute("status", status);
		return "complainEdit";
	}
	//${pageContext.request.contextPath }/complain/complainEdit.do
	@ResponseBody
	@RequestMapping("/complainEdit")
	public Object complainEdit(@RequestParam Map<String, Object> entity) {
		System.out.println("entity"+entity);
		AJAXResult result = new AJAXResult();
		try {
			complainService.complainEdit(entity);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	//${pageContext.request.contextPath }/complain/deleteComplain.do
	@ResponseBody
	@RequestMapping("/deleteComplain")
	public Object deleteComplain(Long complainId) {
		AJAXResult result = new AJAXResult();
		try {
			complainService.deleteById(complainId);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	/**${pageContext.request.contextPath }/complain/deleteComplain.do  批量删除
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteComplains")
	public Object deleteComplains(Long[] complainId) {
		AJAXResult result = new AJAXResult();
		System.out.println(complainId);
		try {
			complainService.deleteById((Object[])complainId);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
}
