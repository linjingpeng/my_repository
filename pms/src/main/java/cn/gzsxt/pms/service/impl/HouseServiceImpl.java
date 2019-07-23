package cn.gzsxt.pms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gzsxt.pms.mapper.HouseMapper;
import cn.gzsxt.pms.mapper.OwnerMapper;
import cn.gzsxt.pms.service.HouseService;
import cn.gzsxt.pms.utils.Page;

@Service
public class HouseServiceImpl implements HouseService{

	@Autowired
	private HouseMapper houseMapper;
	
	@Autowired
	private OwnerMapper ownerMapper;
	
	@Override
	public int addHouse(Map<String, Object> entity) {
		 int count = houseMapper.insert(entity);
		 return count;
	}

	@Override
	public Page findHouseToPage(Map<String, Object> condition, int index, int size) {
		//通过条件查询记录数
		int count = houseMapper.quaryCountByCondition(condition);
		//通过条件查询数据    注意：开始位置=当前索引*每页记录数
		int start = index*size;
		List<Map<String,Object>> houses = houseMapper.findAllHouseToPage(condition, start, size);
		for (Map<String, Object> house : houses) {
			//1.通过house的own_id获取户主信息
			Object ownId = house.get("owner_id");
			Map<String, Object> owner = ownerMapper.quaryById(ownId);
			//2.将户主信息封装到house里面
			house.put("owner", owner);
		}
		Page page = new Page(index, size, count, houses);
		return page;
	}

	@Override
	public Map<String, Object> findHouseById(Long houseId) {

		return houseMapper.quaryById(houseId);
	}

	@Override
	public void houseEdit(Map<String, Object> entity) {
		 houseMapper.editHouse(entity);
		
	}

	@Override
	public void deleteById(Object... ownId) {
		houseMapper.deleteById(ownId);
	}

	@Override
	public List<Map<String, Object>> findAllHouse() {
		return houseMapper.findAll();
	}

	

}
