package cn.gzsxt.pms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gzsxt.pms.mapper.RoleMapper;
import cn.gzsxt.pms.service.RoleService;
import cn.gzsxt.pms.utils.Page;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleMapper roleMapper;
	
	@Override
	public Map<String, Object> addRole(Map<String, Object> entity) {
		 int count = roleMapper.insert(entity);
		 if(count>0) {
				return entity;
			}
		return null;
	}

	@Override
	public Page findRoleToPage(Map<String, Object> condition, int index, int size) {
		//通过条件查询记录数
		int count = roleMapper.quaryCountByCondition(condition);
		//通过条件查询数据    注意：开始位置=当前索引*每页记录数
		int start = index*size;
		List<Map<String,Object>> roles = roleMapper.findAllRoleToPage(condition, start, size);
		Page page = new Page(index, size, count, roles);
		return page;
	}

	@Override
	public Map<String, Object> findRoleById(Long roleId) {

		return roleMapper.quaryById(roleId);
	}

	@Override
	public Map<String, Object> roleEdit(Map<String, Object> entity) {
		 int count = roleMapper.editRole(entity);
		 if (count>0) {
			return roleMapper.quaryById(entity.get("role_id"));
		}
		return null;
	}

	@Override
	public void deleteById(Object... ownId) {
		roleMapper.deleteById(ownId);
	}

	@Override
	public List<Map<String, Object>> findAllRole() {
		return roleMapper.findAllRole();
	}

	

}
