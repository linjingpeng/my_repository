package cn.gzsxt.pms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gzsxt.pms.mapper.ModularMapper;
import cn.gzsxt.pms.service.ModularService;
import cn.gzsxt.pms.utils.Page;

@Service
public class ModularServiceImpl implements ModularService{

	@Autowired
	private ModularMapper modularMapper;
	
	@Override
	public int addModular(Map<String, Object> entity) {
		 int count = modularMapper.insert(entity);
		 return count;
	}

	@Override
	public Page findModularToPage(Map<String, Object> condition, int index, int size) {
		//通过条件查询记录数
		int count = modularMapper.quaryCountByCondition(condition);
		//通过条件查询数据    注意：开始位置=当前索引*每页记录数
		int start = index*size;
		List<Map<String,Object>> modulars = modularMapper.findAllModularToPage(condition, start, size);
		Page page = new Page(index, size, count, modulars);
		return page;
	}

	@Override
	public Map<String, Object> findModularById(Long modularId) {

		return modularMapper.quaryById(modularId);
	}

	@Override
	public void modularEdit(Map<String, Object> entity) {
		 modularMapper.editModular(entity);
		
	}

	@Override
	public void deleteById(Object... ownId) {
		modularMapper.deleteById(ownId);
	}

	@Override
	public List<Map<String, Object>> findAllModular() {
		return modularMapper.findAllModular();
	}

	

}
