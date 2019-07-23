package cn.gzsxt.pms.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gzsxt.pms.service.ChargeService;
import cn.gzsxt.pms.service.DictionaryService;
import cn.gzsxt.pms.service.HouseService;
import cn.gzsxt.pms.utils.AJAXResult;
import cn.gzsxt.pms.utils.Global;
import cn.gzsxt.pms.utils.Page;

@Controller
@RequestMapping("/charge")
public class ChargeController {

	@Autowired
	private ChargeService chargeService;
	
	@Autowired
	private DictionaryService dicService;
	
	@Autowired
	private HouseService houseService;
	
	@RequestMapping("/toChargeList")
	public String toChargeList(@RequestParam Map<String, Object> condition,Integer index,HttpServletRequest request) {
		
		if (index==null) {
			index = 0;
		}
		Page page = chargeService.findChargeToPage(condition, index, Global.PAGE_SIZE);
		request.setAttribute("page", page);
		return "chargeList";
	}
	//${pageContext.request.contextPath}/charge/toChargeAdd.do
	@RequestMapping("/toChargeAdd")
	public String toChargeAdd(HttpServletRequest request) {
		List<Map<String, Object>> pays = dicService.findDictionaryByTypeCode(1002);
		request.setAttribute("pays", pays);
		List<Map<String, Object>> houses = houseService.findAllHouse();
		request.setAttribute("houses", houses);
		return "chargeAdd";
	}
	/**${pageContext.request.contextPath }/charge/addCharge.do
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addCharge")
	public Object addCharge(@RequestParam Map<String, Object> entity) {
		System.out.println(entity);
		AJAXResult result = new AJAXResult();
		try {
			int count = chargeService.addCharge(entity);
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
	 * ${pageContext.request.contextPath }/charge/toChargeEdit.do?chargeId=
	 * @return
	 */
	@RequestMapping("/toChargeEdit")
	public String toChargeEdit(Long chargeId,HttpServletRequest request) {
		
		Map<String, Object> charge = chargeService.findChargeById(chargeId);
		request.setAttribute("charge", charge);
		List<Map<String, Object>> pays = dicService.findDictionaryByTypeCode(1002);
		request.setAttribute("pays", pays);
		List<Map<String, Object>> houses = houseService.findAllHouse();
		request.setAttribute("houses", houses);
		return "chargeEdit";
	}
	//${pageContext.request.contextPath }/charge/chargeEdit.do
	@ResponseBody
	@RequestMapping("/chargeEdit")
	public Object chargeEdit(@RequestParam Map<String, Object> entity) {
		System.out.println("entity"+entity);
		AJAXResult result = new AJAXResult();
		try {
			chargeService.chargeEdit(entity);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	//${pageContext.request.contextPath }/charge/deleteCharge.do
	@ResponseBody
	@RequestMapping("/deleteCharge")
	public Object deleteCharge(Long chargeId) {
		AJAXResult result = new AJAXResult();
		try {
			chargeService.deleteById(chargeId);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	/**${pageContext.request.contextPath }/charge/deleteCharge.do  批量删除
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteCharges")
	public Object deleteCharges(Long[] chargeId) {
		AJAXResult result = new AJAXResult();
		System.out.println(chargeId);
		try {
			chargeService.deleteById((Object[])chargeId);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
}
