package cn.gzsxt.pms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gzsxt.pms.mapper.DictionaryMapper;
import cn.gzsxt.pms.service.DictionaryService;
import cn.gzsxt.pms.utils.Page;

@Service
public class DictionaryServiceImpl implements DictionaryService{

	@Autowired
	private DictionaryMapper dictionaryMapper;
	
	@Override
	public int addDictionary(Map<String, Object> entity) {
		 int count = dictionaryMapper.insert(entity);
		 return count;
	}

	@Override
	public Page findDictionaryToPage(Map<String, Object> condition, int index, int size) {
		//通过条件查询记录数
		int count = dictionaryMapper.quaryCountByCondition(condition);
		//通过条件查询数据    注意：开始位置=当前索引*每页记录数
		int start = index*size;
		List<Map<String,Object>> dictionarys = dictionaryMapper.findAllDictionaryToPage(condition, start, size);
		Page page = new Page(index, size, count, dictionarys);
		return page;
	}

	@Override
	public Map<String, Object> findDictionaryById(Long dictionaryId) {

		return dictionaryMapper.quaryById(dictionaryId);
	}

	@Override
	public void dictionaryEdit(Map<String, Object> entity) {
		 dictionaryMapper.editDictionary(entity);
		
	}

	@Override
	public void deleteById(Object... ownId) {
		dictionaryMapper.deleteById(ownId);
	}

	@Override
	public List<Map<String, Object>> findDictionaryByTypeCode(Object typeCode) {
		return dictionaryMapper.findByTypeCode(typeCode);
	}

	@Override
	public List<Map<String, Object>> findAllDictionary() {
		return dictionaryMapper.findAll();
	}

	

}
