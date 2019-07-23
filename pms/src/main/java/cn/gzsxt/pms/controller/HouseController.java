package cn.gzsxt.pms.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gzsxt.pms.service.HouseService;
import cn.gzsxt.pms.service.OwnerService;
import cn.gzsxt.pms.utils.AJAXResult;
import cn.gzsxt.pms.utils.Global;
import cn.gzsxt.pms.utils.Page;

@Controller
@RequestMapping("/house")
public class HouseController {

	@Autowired
	private HouseService houseService;
	
	@Autowired
	private OwnerService ownerService;
	
	@RequestMapping("/toHouseList")
	public String toHouseList(@RequestParam Map<String, Object> condition,Integer index,HttpServletRequest request) {
		
		if (index==null) {
			index = 0;
		}
		Page page = houseService.findHouseToPage(condition, index, Global.PAGE_SIZE);
		request.setAttribute("page", page);
		return "houseList";
	}
	//${pageContext.request.contextPath}/house/toHouseAdd.do
	@RequestMapping("/toHouseAdd")
	public String toHouseAdd(HttpServletRequest request) {
		List<Map<String, Object>> owners = ownerService.findAllOwner();
		request.setAttribute("owners", owners);
		return "houseAdd";
	}
	/**${pageContext.request.contextPath }/house/addHouse.do
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addHouse")
	public Object addHouse(@RequestParam Map<String, Object> entity) {
		System.out.println(entity);
		AJAXResult result = new AJAXResult();
		try {
			int count = houseService.addHouse(entity);
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
	 * ${pageContext.request.contextPath }/house/toHouseEdit.do?houseId=
	 * @return
	 */
	@RequestMapping("/toHouseEdit")
	public String toHouseEdit(Long houseId,HttpServletRequest request) {
		
		Map<String, Object> house = houseService.findHouseById(houseId);
		request.setAttribute("house", house);
		List<Map<String, Object>> owners = ownerService.findAllOwner();
		request.setAttribute("owners", owners);
		return "houseEdit";
	}
	//${pageContext.request.contextPath }/house/houseEdit.do
	@ResponseBody
	@RequestMapping("/houseEdit")
	public Object houseEdit(@RequestParam Map<String, Object> entity) {
		System.out.println("entity"+entity);
		AJAXResult result = new AJAXResult();
		try {
			houseService.houseEdit(entity);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	//${pageContext.request.contextPath }/house/deleteHouse.do
	@ResponseBody
	@RequestMapping("/deleteHouse")
	public Object deleteHouse(Long houId) {
		AJAXResult result = new AJAXResult();
		try {
			houseService.deleteById(houId);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	/**${pageContext.request.contextPath }/house/deleteHouse.do  批量删除
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteHouses")
	public Object deleteHouses(Long[] houId) {
		AJAXResult result = new AJAXResult();
		System.out.println(houId);
		try {
			houseService.deleteById((Object[])houId);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
}
