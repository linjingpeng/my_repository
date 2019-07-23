package cn.gzsxt.pms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gzsxt.pms.service.AdminService;
import cn.gzsxt.pms.service.DictionaryService;
import cn.gzsxt.pms.service.RoleService;
import cn.gzsxt.pms.utils.AJAXResult;
import cn.gzsxt.pms.utils.Global;
import cn.gzsxt.pms.utils.Page;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	@Autowired
	private DictionaryService dictionaryService;
	@Autowired
	private RoleService roleService;
	@RequestMapping("/doAJAXLogin")
	@ResponseBody
	public Object login(@RequestParam Map<String,Object> entity,HttpSession session) {
		AJAXResult result = new AJAXResult();
		System.out.println(entity);
		Map<String, Object> admin = adminService.loginAdmin(entity);
		if (admin!=null) {
			Long statu = (Long)admin.get("admin_status");
			if(statu==0){
			session.setAttribute("admin_info", admin);
			result.setSuccess(true);
			}else {
				result.setData("该用户已被禁用");
				result.setSuccess(false);
			}
		}else {
			result.setData("账号或密码错误，请输入正确的账号密码");
			result.setSuccess(false);
		}
		return result;
	}
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();	//注销账号
		return "forward:/login.jsp";
	}
	@RequestMapping("/index")
	public String index() {
		return "index";
	}
	@RequestMapping("/toAdminSetting")
	public String toAdminSetting() {
		return "setting";
	}
	/**
	 * 修改当前用户密码
	 * path：${pageContext.request.contextPath }/admin/setAdminPwd.do
	 * @param entity
	 * @param sesssion
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value="/setAdminPwd")
	public Object setAdminPwd(@RequestParam Map<String,Object> entity,HttpSession sesssion) {
		System.out.println("-修改当前登录管理员密码-"+entity);
		AJAXResult result = new AJAXResult();
		try {
			Map<String, Object> admin = (Map<String, Object>) sesssion.getAttribute("admin_info");
			//1.获得表单的原密码与当前的登录管理员校验是否正确
			if(entity.get("source_admin_pwd").equals(admin.get("admin_pwd"))) {
				//2.新的密码与确认密码是否一致
				if(entity.get("new_admin_pwd").equals(entity.get("confirm_admin_pwd"))) {
					Map<String,Object > params=new HashMap<>();
					//根据管理员编号，修改密码
					params.put("admin_id", admin.get("admin_id"));
					params.put("admin_pwd", entity.get("new_admin_pwd") );
					Map<String, Object> resultAdmin = adminService.editAdminPassword(params);
					sesssion.setAttribute("admin_info", resultAdmin);
					result.setSuccess(true);
				}else {
					result.setData("修改密码失败，确认密码不一致");
					result.setSuccess(false);
				}
				
			}else {
				result.setData("修改密码失败，原密码密码不正确");
				result.setSuccess(false);
			}
		} catch (Exception e) {
			result.setData("修改密码失败，出现未知异常，请联系管理员");
			e.printStackTrace();
			result.setSuccess(false);
		}
		
		
		return result;
	}
	@RequestMapping("/toAdminList")
	public String toAdminList(@RequestParam Map<String, Object> condition,Integer index,HttpServletRequest request) {
		if (index==null) {
			index = 0;
		}
		Page page = adminService.findAdminToPage(condition, index, Global.PAGE_SIZE);
		request.setAttribute("page", page);
		return "adminList";
	}
	//${pageContext.request.contextPath}/admin/toAdminAdd.do
	@RequestMapping("/toAdminAdd")
	public String toAdminAdd(HttpServletRequest request) {
		//2.在数据字典获得状态数据
		List<Map<String, Object>> status = dictionaryService.findDictionaryByTypeCode(1004);
		request.setAttribute("status", status);
		//3.获得角色数据
		List<Map<String, Object>> roles = roleService.findAllRole();
		request.setAttribute("roles", roles);
		return "adminAdd";
	}
	/**${pageContext.request.contextPath }/admin/addAdmin.do
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addAdmin")
	public Object addAdmin(@RequestParam Map<String, Object> entity) {
		System.out.println(entity);
		AJAXResult result = new AJAXResult();
		try {
			int count = adminService.addAdmin(entity);
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
	 * ${pageContext.request.contextPath }/admin/toAdminEdit.do?adminId=
	 * @return
	 */
	@RequestMapping("/toAdminEdit")
	public String toAdminEdit(Long adminId,HttpServletRequest request) {
		
		Map<String, Object> admin = adminService.findAdminById(adminId);
		request.setAttribute("admin", admin);
		//2.在数据字典获得状态数据
		List<Map<String, Object>> status = dictionaryService.findDictionaryByTypeCode(1004);
		request.setAttribute("status", status);
		//3.获得角色数据
		List<Map<String, Object>> roles = roleService.findAllRole();
		request.setAttribute("roles", roles);
		return "adminEdit";
	}
	//${pageContext.request.contextPath }/admin/adminEdit.do
	@ResponseBody
	@RequestMapping("/adminEdit")
	public Object adminEdit(@RequestParam Map<String, Object> entity) {
		System.out.println("entity"+entity);
		AJAXResult result = new AJAXResult();
		try {
			adminService.editAdmin(entity);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	//${pageContext.request.contextPath }/admin/deleteAdmin.do
	@ResponseBody
	@RequestMapping("/deleteAdmin")
	public Object deleteAdmin(Long ownId) {
		AJAXResult result = new AJAXResult();
		try {
			adminService.deleteAdminByIds(ownId);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	/**${pageContext.request.contextPath }/admin/deleteAdmins.do  批量删除
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteAdmins")
	public Object deleteAdmins(Long[] ownId) {
		AJAXResult result = new AJAXResult();
		System.out.println(ownId);
		try {
			adminService.deleteAdminByIds((Object[])ownId);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
}
