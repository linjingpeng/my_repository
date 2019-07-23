package cn.gzsxt.pms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gzsxt.pms.mapper.ComplainMapper;
import cn.gzsxt.pms.mapper.DictionaryMapper;
import cn.gzsxt.pms.service.ComplainService;
import cn.gzsxt.pms.utils.Page;

@Service
public class ComplainServiceImpl implements ComplainService{

	@Autowired
	private ComplainMapper complainMapper;
	
	@Autowired
	private DictionaryMapper dicMapper;
	
	@Override
	public int addComplain(Map<String, Object> entity) {
		 int count = complainMapper.insert(entity);
		 return count;
	}

	@Override
	public Page findComplainToPage(Map<String, Object> condition, int index, int size) {
		//通过条件查询记录数
		int count = complainMapper.quaryCountByCondition(condition);
		//通过条件查询数据    注意：开始位置=当前索引*每页记录数
		int start = index*size;
		List<Map<String,Object>> complains = complainMapper.findAllComplainToPage(condition, start, size);
		for (Map<String, Object> complain : complains) {
			Object statu = complain.get("deal_status");
			Map<String, Object> status = dicMapper.findByTypeCodeAndValue(statu, 1000);
			complain.put("status", status);
		}
		Page page = new Page(index, size, count, complains);
		return page;
	}

	@Override
	public Map<String, Object> findComplainById(Long complainId) {

		return complainMapper.quaryById(complainId);
	}

	@Override
	public void complainEdit(Map<String, Object> entity) {
		 complainMapper.editComplain(entity);
		
	}

	@Override
	public void deleteById(Object... ownId) {
		complainMapper.deleteById(ownId);
	}

	

}
