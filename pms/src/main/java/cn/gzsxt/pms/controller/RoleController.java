package cn.gzsxt.pms.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gzsxt.pms.service.ModularService;
import cn.gzsxt.pms.service.PowerService;
import cn.gzsxt.pms.service.RoleService;
import cn.gzsxt.pms.utils.AJAXResult;
import cn.gzsxt.pms.utils.Global;
import cn.gzsxt.pms.utils.Page;

@Controller
@RequestMapping("/role")
public class RoleController {
	
	private static final Logger logger =LogManager.getLogger(AdminController.class);
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private ModularService modularService;
	@Autowired
	private PowerService powerService;
	@RequestMapping("/toRoleList")
	public String toRoleList(@RequestParam Map<String, Object> condition,Integer index,HttpServletRequest request) {
		
		if (index==null) {
			index = 0;
		}
		Page page = roleService.findRoleToPage(condition, index, Global.PAGE_SIZE);
		request.setAttribute("page", page);
		return "roleList";
	}
	//${pageContext.request.contextPath}/role/toRoleAdd.do
	@RequestMapping("/toRoleAdd")
	public String toRoleAdd(HttpServletRequest request) {
		logger.debug("跳转到增加角色页面--");
		//1.获得所有模块的信息
		List<Map<String, Object>> modulars = modularService.findAllModular();
		request.setAttribute("modulars", modulars);
		//2.获得所有的权限
		List<Map<String, Object>> powers = powerService.findAllPower();
		request.setAttribute("powers", powers);
		return "roleAdd";
	}
	/**${pageContext.request.contextPath }/role/addRole.do
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addRole")
	public Object addRole(@RequestParam Map<String, Object> entity,String[] rolePower,HttpServletRequest request) {
        logger.debug("增加角色："+entity);
		AJAXResult result = new AJAXResult();
		String rolePowerStr = Arrays.toString(rolePower);
		//.去掉方括号
		StringBuilder builder=new StringBuilder(rolePowerStr);
		builder.deleteCharAt(builder.indexOf("["));
		builder.deleteCharAt(builder.indexOf("]"));
		
		logger.debug("权限字符串："+builder.toString());
		
		entity.put("role_powers", builder.toString());
		try {
			Map<String, Object> role = roleService.addRole(entity);
			if (role!=null) {
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
	 * ${pageContext.request.contextPath }/role/toRoleEdit.do?roleId=
	 * @return
	 */
	@RequestMapping("/toRoleEdit")
	public String toRoleEdit(Long roleId,HttpServletRequest request) {
		
		Map<String, Object> role = roleService.findRoleById(roleId);
		request.setAttribute("role", role);
		//1.获得所有模块的信息
		List<Map<String, Object>> modulars = modularService.findAllModular();
		request.setAttribute("modulars", modulars);
		//2.获得所有的权限
		List<Map<String, Object>> powers = powerService.findAllPower();
		request.setAttribute("powers", powers);
		return "roleEdit";
	}
	//${pageContext.request.contextPath }/role/roleEdit.do
	@ResponseBody
	@RequestMapping("/roleEdit")
	public Object roleEdit(@RequestParam Map<String, Object> entity,String[] rolePower,HttpServletRequest request) {
		//获得角色编号
		logger.debug("表单参数："+entity);
		AJAXResult result = new AJAXResult();
		try {
			String rolePowerStr = Arrays.toString(rolePower);
			//.去掉方括号
			StringBuilder builder=new StringBuilder(rolePowerStr);
			builder.deleteCharAt(builder.indexOf("["));
			builder.deleteCharAt(builder.indexOf("]"));
			
			logger.debug("权限字符串："+builder.toString());
			
			entity.put("role_powers", builder.toString());
			

			Map<String, Object> role = roleService.roleEdit(entity);
			if (role!=null) {
				//更新成功，重设表单值
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
	//${pageContext.request.contextPath }/role/deleteRole.do
	@ResponseBody
	@RequestMapping("/deleteRole")
	public Object deleteRole(Long ownId) {
		AJAXResult result = new AJAXResult();
		try {
			roleService.deleteById(ownId);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	/**${pageContext.request.contextPath }/role/deleteRole.do  批量删除
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteRoles")
	public Object deleteRoles(Long[] ownId) {
		AJAXResult result = new AJAXResult();
		System.out.println(ownId);
		try {
			roleService.deleteById((Object[])ownId);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
}
