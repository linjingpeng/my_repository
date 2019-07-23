package cn.gzsxt.pms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gzsxt.pms.mapper.OwnerMapper;
import cn.gzsxt.pms.service.OwnerService;
import cn.gzsxt.pms.utils.Page;

@Service
public class OwnerServiceImpl implements OwnerService{

	@Autowired
	private OwnerMapper ownerMapper;
	
	@Override
	public int addOwner(Map<String, Object> entity) {
		 int count = ownerMapper.insert(entity);
		 return count;
	}

	@Override
	public Page findOwnerToPage(Map<String, Object> condition, int index, int size) {
		//通过条件查询记录数
		int count = ownerMapper.quaryCountByCondition(condition);
		//通过条件查询数据    注意：开始位置=当前索引*每页记录数
		int start = index*size;
		List<Map<String,Object>> owners = ownerMapper.findAllOwnerToPage(condition, start, size);
		Page page = new Page(index, size, count, owners);
		return page;
	}

	@Override
	public Map<String, Object> findOwnerById(Long ownerId) {

		return ownerMapper.quaryById(ownerId);
	}

	@Override
	public void ownerEdit(Map<String, Object> entity) {
		 ownerMapper.editOwner(entity);
		
	}

	@Override
	public void deleteById(Object... ownId) {
		ownerMapper.deleteById(ownId);
	}

	@Override
	public List<Map<String, Object>> findAllOwner() {
		return ownerMapper.findAllOwner();
	}

	

}
