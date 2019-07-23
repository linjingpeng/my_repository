package cn.gzsxt.pms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gzsxt.pms.mapper.AdminMapper;
import cn.gzsxt.pms.mapper.DictionaryMapper;
import cn.gzsxt.pms.mapper.ModularMapper;
import cn.gzsxt.pms.mapper.PowerMapper;
import cn.gzsxt.pms.mapper.RoleMapper;
import cn.gzsxt.pms.service.AdminService;
import cn.gzsxt.pms.utils.Page;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminMapper adminMapper;
	
	@Autowired
	private DictionaryMapper dicMapper;
	
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private PowerMapper powerMapper;
	@Autowired
	private ModularMapper modularMapper;
	@Override
	public int addAdmin(Map<String, Object> admin) {
		int count = adminMapper.insert(admin);
		return count;
	}

	@Override
	public Map<String, Object> loginAdmin(Map<String, Object> entity) {
		Object acctName = entity.get("admin_account");
		//1.通过账号查询管理员信息
		Map<String, Object> admin = adminMapper.findByAccount(acctName);
		//如果找不账号，直接返回
		if (admin==null) {
			return null;
		}
		//2.验证密码
		if (entity.get("admin_pwd").equals(admin.get("admin_pwd"))) {
			//登录成功后
			//第一步：获得用户的角色信息
			Object roleId = admin.get("role_id");
			Map<String, Object> role = roleMapper.quaryById(roleId);
			admin.put("role", role);
			//第二步：获得权限的信息
			String rolePowers =(String) role.get("role_powers");
			List<Map<String, Object>> powers = powerMapper.findByIds((Object[])rolePowers.split(","));
			admin.put("powers", powers);
			//第三步：获得权限对应的模块信息
			List<Map<String, Object>> modulars=new ArrayList<Map<String,Object>>();
			for (Map<String, Object> power : powers) {
				Object modularId = power.get("modular_id");
				Map<String, Object> modular = modularMapper.quaryById(modularId);
				
				boolean flag=true;
				for (Map<String, Object> map : modulars) {
					//判断如果集合里面已经有相等的对象，就修改标识为false
					if(modular.get("modular_id")==map.get("modular_id")) {
						flag=false;
						break;
					}
				}
				//必须标识变量为true才增加
				if(flag==true) {
					modulars.add(modular);
				}
			}
			
			admin.put("modulars", modulars);
			
			return admin;
		}
		
		return null;
	}

	@Override
	public Map<String, Object> editAdminPassword(Map<String, Object> admin) {
		adminMapper.editAdmin(admin);
		return adminMapper.quaryById(admin.get("admin_id"));
	}

	@Override
	public Page findAdminToPage(Map<String, Object> condition, int index, int size) {
		//1.通过条件查询记录数
		int count = adminMapper.quaryCountByCondition(condition);
		//2.通过条件查询数据
		//注意：开始位置=索引*每页记录数
		int start=index*size;
		List<Map<String, Object>> admins = adminMapper.findAllAdminToPage(condition, start, size);
		//1.将角色的数据也加入集合里面
		for (Map<String, Object> admin : admins) {
			//第一步：通过Admin的role_id获得角色信息
			Object roleId = admin.get("role_id");
			//第二步：通过role_id获得角色信息
			Map<String, Object> role = roleMapper.quaryById(roleId);
			//第三步：将角色的字段拼接到amdin里面
			//使用拼接在同一层的方式，不同表的字段名是不可以重复的
			//admin.putAll(role);
			//使用拼接在不同一层的方式，不同表的字段点名是可以重复的
			admin.put("role", role);
			
			//2.从数据字典获得管理员状态，管理员状态的类型编码type_code是1000
			 Map<String, Object> dic = dicMapper.findByTypeCodeAndValue(admin.get("admin_status"), 1004);
			 admin.put("status", dic);
		}
			 Page page=new Page(index, size, count, admins);
		return page;
	}

	@Override
	public Map<String, Object> findAdminById(Object adminId) {
		return adminMapper.quaryById(adminId);
	}

	@Override
	public void editAdmin(Map<String, Object> entity) {
		adminMapper.editAdmin(entity);
	}

	@Override
	public void deleteAdminByIds(Object... adminIds) {
		adminMapper.deleteById(adminIds);
	}

	@Override
	public List<Map<String, Object>> findAdminByRoleId(Object roleId) {
		return adminMapper.findAdminByRoleId(roleId);
	}

}
