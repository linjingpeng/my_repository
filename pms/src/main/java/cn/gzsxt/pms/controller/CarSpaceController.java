package cn.gzsxt.pms.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gzsxt.pms.service.CarSpaceService;
import cn.gzsxt.pms.service.DictionaryService;
import cn.gzsxt.pms.utils.AJAXResult;
import cn.gzsxt.pms.utils.Global;
import cn.gzsxt.pms.utils.Page;

@Controller
@RequestMapping("/carSpace")
public class CarSpaceController {

	@Autowired
	private CarSpaceService carSpaceService;
	
	@Autowired
	private DictionaryService dicService;
	
	@RequestMapping("/toCarSpaceList")
	public String toCarSpaceList(@RequestParam Map<String, Object> condition,Integer index,HttpServletRequest request) {
		
		if (index==null) {
			index = 0;
		}
		Page page = carSpaceService.findCarSpaceToPage(condition, index, Global.PAGE_SIZE);
		request.setAttribute("page", page);
		return "carSpaceList";
	}
	//${pageContext.request.contextPath}/carSpace/toCarSpaceAdd.do
	@RequestMapping("/toCarSpaceAdd")
	public String toCarSpaceAdd(HttpServletRequest request) {
		List<Map<String, Object>> status = dicService.findDictionaryByTypeCode(1001);
		request.setAttribute("status", status);
		return "carSpaceAdd";
	}
	/**${pageContext.request.contextPath }/carSpace/addCarSpace.do
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addCarSpace")
	public Object addCarSpace(@RequestParam Map<String, Object> entity) {
		System.out.println(entity);
		AJAXResult result = new AJAXResult();
		try {
			int count = carSpaceService.addCarSpace(entity);
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
	 * ${pageContext.request.contextPath }/carSpace/toCarSpaceEdit.do?carSpaceId=
	 * @return
	 */
	@RequestMapping("/toCarSpaceEdit")
	public String toCarSpaceEdit(Long carSpaceId,HttpServletRequest request) {
		
		Map<String, Object> carSpace = carSpaceService.findCarSpaceById(carSpaceId);
		request.setAttribute("carSpace", carSpace);
		List<Map<String, Object>> status = dicService.findDictionaryByTypeCode(1001);
		request.setAttribute("status", status);
		return "carSpaceEdit";
	}
	//${pageContext.request.contextPath }/carSpace/carSpaceEdit.do
	@ResponseBody
	@RequestMapping("/carSpaceEdit")
	public Object carSpaceEdit(@RequestParam Map<String, Object> entity) {
		System.out.println("entity"+entity);
		AJAXResult result = new AJAXResult();
		try {
			carSpaceService.carSpaceEdit(entity);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	//${pageContext.request.contextPath }/carSpace/deleteCarSpace.do
	@ResponseBody
	@RequestMapping("/deleteCarSpace")
	public Object deleteCarSpace(Long carSpaceId) {
		AJAXResult result = new AJAXResult();
		try {
			carSpaceService.deleteById(carSpaceId);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	/**${pageContext.request.contextPath }/carSpace/deleteCarSpace.do  批量删除
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteCarSpaces")
	public Object deleteCarSpaces(Long[] carSpaceId) {
		AJAXResult result = new AJAXResult();
		try {
			carSpaceService.deleteById((Object[])carSpaceId);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
}
