package cn.gzsxt.pms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gzsxt.pms.mapper.ChargeMapper;
import cn.gzsxt.pms.mapper.DictionaryMapper;
import cn.gzsxt.pms.mapper.HouseMapper;
import cn.gzsxt.pms.service.ChargeService;
import cn.gzsxt.pms.utils.Page;

@Service
public class ChargeServiceImpl implements ChargeService{

	@Autowired
	private ChargeMapper chargeMapper;
	
	@Autowired
	private DictionaryMapper dicMapper;
	@Autowired
	private HouseMapper houseMapper;
	@Override
	public int addCharge(Map<String, Object> entity) {
		 int count = chargeMapper.insert(entity);
		 return count;
	}

	@Override
	public Page findChargeToPage(Map<String, Object> condition, int index, int size) {
		//通过条件查询记录数
		int count = chargeMapper.quaryCountByCondition(condition);
		//通过条件查询数据    注意：开始位置=当前索引*每页记录数
		int start = index*size;
		List<Map<String,Object>> charges = chargeMapper.findAllChargeToPage(condition, start, size);
		for (Map<String, Object> charge : charges) {
			Object pay = charge.get("isPay");
			Map<String, Object> pays = dicMapper.findByTypeCodeAndValue(pay, 1002);
			charge.put("pays", pays);
			Map<String, Object> house = houseMapper.quaryById(charge.get("house_id"));
			charge.put("house", house);
		}
		Page page = new Page(index, size, count, charges);
		return page;
	}

	@Override
	public Map<String, Object> findChargeById(Long chargeId) {

		return chargeMapper.quaryById(chargeId);
	}

	@Override
	public void chargeEdit(Map<String, Object> entity) {
		 chargeMapper.editCharge(entity);
		
	}

	@Override
	public void deleteById(Object... ownId) {
		chargeMapper.deleteById(ownId);
	}

	

}
