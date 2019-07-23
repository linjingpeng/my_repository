package cn.gzsxt.pms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gzsxt.pms.mapper.DictionaryMapper;
import cn.gzsxt.pms.mapper.HouseMapper;
import cn.gzsxt.pms.mapper.RepairMapper;
import cn.gzsxt.pms.service.RepairService;
import cn.gzsxt.pms.utils.Page;

@Service
public class RepairServiceImpl implements RepairService{

	@Autowired
	private RepairMapper repairMapper;
	
	@Autowired
	private HouseMapper houseMapper;
	
	@Autowired
	private DictionaryMapper dicMapper;
	
	
	@Override
	public int addRepair(Map<String, Object> entity) {
		 int count = repairMapper.insert(entity);
		 return count;
	}

	@Override
	public Page findRepairToPage(Map<String, Object> condition, int index, int size) {
		//通过条件查询记录数
		int count = repairMapper.quaryCountByCondition(condition);
		//通过条件查询数据    注意：开始位置=当前索引*每页记录数
		int start = index*size;
		List<Map<String,Object>> repairs = repairMapper.findAllRepairToPage(condition, start, size);
		for (Map<String, Object> repair : repairs) {
			Object houseId = repair.get("house_id");
			Map<String, Object> house = houseMapper.quaryById(houseId);
			repair.put("house", house);
			
			Object type = repair.get("repair_type");
			Map<String, Object> types = dicMapper.findByTypeCodeAndValue(type, 1003);
			repair.put("types", types);
			
			Object statu = repair.get("repair_status");
			Map<String, Object> status = dicMapper.findByTypeCodeAndValue(statu, 1000);
			repair.put("status", status);
		}
		Page page = new Page(index, size, count, repairs);
		return page;
	}

	@Override
	public Map<String, Object> findRepairById(Long repairId) {

		return repairMapper.quaryById(repairId);
	}

	@Override
	public void repairEdit(Map<String, Object> entity) {
		 repairMapper.editRepair(entity);
		
	}

	@Override
	public void deleteById(Object... ownId) {
		repairMapper.deleteById(ownId);
	}

	

}
