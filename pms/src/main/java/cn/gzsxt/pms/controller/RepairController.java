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
import cn.gzsxt.pms.service.HouseService;
import cn.gzsxt.pms.service.RepairService;
import cn.gzsxt.pms.utils.AJAXResult;
import cn.gzsxt.pms.utils.Global;
import cn.gzsxt.pms.utils.Page;

@Controller
@RequestMapping("/repair")
public class RepairController {

	@Autowired
	private RepairService repairService;
	
	@Autowired
	private DictionaryService dicService;
	@Autowired
	private HouseService houseService;
	@RequestMapping("/toRepairList")
	public String toRepairList(@RequestParam Map<String, Object> condition,Integer index,HttpServletRequest request) {
		
		if (index==null) {
			index = 0;
		}
		Page page = repairService.findRepairToPage(condition, index, Global.PAGE_SIZE);
		request.setAttribute("page", page);
		return "repairList";
	}
	//${pageContext.request.contextPath}/repair/toRepairAdd.do
	@RequestMapping("/toRepairAdd")
	public String toRepairAdd(HttpServletRequest request) {
		List<Map<String, Object>> types = dicService.findDictionaryByTypeCode(1003);
		request.setAttribute("types", types);
		List<Map<String, Object>> status = dicService.findDictionaryByTypeCode(1000);
		request.setAttribute("status", status);
		List<Map<String, Object>> houses = houseService.findAllHouse();
		request.setAttribute("houses", houses);
		return "repairAdd";
	}
	/**${pageContext.request.contextPath }/repair/addRepair.do
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addRepair")
	public Object addRepair(@RequestParam Map<String, Object> entity) {
		System.out.println(entity);
		AJAXResult result = new AJAXResult();
		try {
			int count = repairService.addRepair(entity);
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
	 * ${pageContext.request.contextPath }/repair/toRepairEdit.do?repairId=
	 * @return
	 */
	@RequestMapping("/toRepairEdit")
	public String toRepairEdit(Long repairId,HttpServletRequest request) {
		
		Map<String, Object> repair = repairService.findRepairById(repairId);
		request.setAttribute("repair", repair);
		List<Map<String, Object>> types = dicService.findDictionaryByTypeCode(1003);
		request.setAttribute("types", types);
		List<Map<String, Object>> status = dicService.findDictionaryByTypeCode(1000);
		request.setAttribute("status", status);
		List<Map<String, Object>> houses = houseService.findAllHouse();
		request.setAttribute("houses", houses);
		return "repairEdit";
	}
	//${pageContext.request.contextPath }/repair/repairEdit.do
	@ResponseBody
	@RequestMapping("/repairEdit")
	public Object repairEdit(@RequestParam Map<String, Object> entity) {
		System.out.println("entity"+entity);
		AJAXResult result = new AJAXResult();
		try {
			repairService.repairEdit(entity);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	//${pageContext.request.contextPath }/repair/deleteRepair.do
	@ResponseBody
	@RequestMapping("/deleteRepair")
	public Object deleteRepair(Long repairId) {
		AJAXResult result = new AJAXResult();
		try {
			repairService.deleteById(repairId);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	/**${pageContext.request.contextPath }/repair/deleteRepair.do  批量删除
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteRepairs")
	public Object deleteRepairs(Long[] repairId) {
		AJAXResult result = new AJAXResult();
		System.out.println(repairId);
		try {
			repairService.deleteById((Object[])repairId);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
}
