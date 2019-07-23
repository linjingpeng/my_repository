package cn.gzsxt.pms.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gzsxt.pms.service.OwnerService;
import cn.gzsxt.pms.utils.AJAXResult;
import cn.gzsxt.pms.utils.Global;
import cn.gzsxt.pms.utils.Page;

@Controller
@RequestMapping("/owner")
public class OwnerController {

	@Autowired
	private OwnerService ownerService;
	
	@RequestMapping("/toOwnerList")
	public String toOwnerList(@RequestParam Map<String, Object> condition,Integer index,HttpServletRequest request) {
		
		if (index==null) {
			index = 0;
		}
		Page page = ownerService.findOwnerToPage(condition, index, Global.PAGE_SIZE);
		request.setAttribute("page", page);
		return "ownerList";
	}
	//${pageContext.request.contextPath}/owner/toOwnerAdd.do
	@RequestMapping("/toOwnerAdd")
	public String toOwnerAdd() {
	
		return "ownerAdd";
	}
	/**${pageContext.request.contextPath }/owner/addOwner.do
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addOwner")
	public Object addOwner(@RequestParam Map<String, Object> entity) {
		System.out.println(entity);
		AJAXResult result = new AJAXResult();
		try {
			int count = ownerService.addOwner(entity);
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
	 * ${pageContext.request.contextPath }/owner/toOwnerEdit.do?ownerId=
	 * @return
	 */
	@RequestMapping("/toOwnerEdit")
	public String toOwnerEdit(Long ownerId,HttpServletRequest request) {
		
		Map<String, Object> owner = ownerService.findOwnerById(ownerId);
		request.setAttribute("owner", owner);
		return "ownerEdit";
	}
	//${pageContext.request.contextPath }/owner/ownerEdit.do
	@ResponseBody
	@RequestMapping("/ownerEdit")
	public Object ownerEdit(@RequestParam Map<String, Object> entity) {
		System.out.println("entity"+entity);
		AJAXResult result = new AJAXResult();
		try {
			ownerService.ownerEdit(entity);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	//${pageContext.request.contextPath }/owner/deleteOwner.do
	@ResponseBody
	@RequestMapping("/deleteOwner")
	public Object deleteOwner(Long ownId) {
		AJAXResult result = new AJAXResult();
		try {
			ownerService.deleteById(ownId);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	/**${pageContext.request.contextPath }/owner/deleteOwner.do  批量删除
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteOwners")
	public Object deleteOwners(Long[] ownId) {
		AJAXResult result = new AJAXResult();
		System.out.println(ownId);
		try {
			ownerService.deleteById((Object[])ownId);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
}
