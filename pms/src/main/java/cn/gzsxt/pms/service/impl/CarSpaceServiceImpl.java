package cn.gzsxt.pms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gzsxt.pms.mapper.CarSpaceMapper;
import cn.gzsxt.pms.mapper.DictionaryMapper;
import cn.gzsxt.pms.service.CarSpaceService;
import cn.gzsxt.pms.utils.Page;

@Service
public class CarSpaceServiceImpl implements CarSpaceService{

	@Autowired
	private CarSpaceMapper carSpaceMapper;
	
	@Autowired
	private DictionaryMapper dicMapper;
	@Override
	public int addCarSpace(Map<String, Object> entity) {
		 int count = carSpaceMapper.insert(entity);
		 return count;
	}

	@Override
	public Page findCarSpaceToPage(Map<String, Object> condition, int index, int size) {
		//通过条件查询记录数
		int count = carSpaceMapper.quaryCountByCondition(condition);
		//通过条件查询数据    注意：开始位置=当前索引*每页记录数
		int start = index*size;
		List<Map<String,Object>> carSpaces = carSpaceMapper.findAllCarSpaceToPage(condition, start, size);
		for (Map<String, Object> carSpace : carSpaces) {
			Object statu = carSpace.get("park_status");
			Map<String, Object> dic = dicMapper.findByTypeCodeAndValue(statu, 1001);
			carSpace.put("status", dic);
		}
		Page page = new Page(index, size, count, carSpaces);
		return page;
	}

	@Override
	public Map<String, Object> findCarSpaceById(Long carSpaceId) {

		return carSpaceMapper.quaryById(carSpaceId);
	}

	@Override
	public void carSpaceEdit(Map<String, Object> entity) {
		 carSpaceMapper.editCarSpace(entity);
		
	}

	@Override
	public void deleteById(Object... ownId) {
		carSpaceMapper.deleteById(ownId);
	}

	

}
