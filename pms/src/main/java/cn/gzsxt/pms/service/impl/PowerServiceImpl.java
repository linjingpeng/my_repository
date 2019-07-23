package cn.gzsxt.pms.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.gzsxt.pms.mapper.DictionaryMapper;
import cn.gzsxt.pms.mapper.ModularMapper;
import cn.gzsxt.pms.mapper.PowerMapper;
import cn.gzsxt.pms.service.PowerService;
import cn.gzsxt.pms.utils.Page;

@Service
public class PowerServiceImpl implements PowerService{

	@Autowired
	private PowerMapper powerMapper;
	
	@Autowired
	private ModularMapper modularMapper;
	
	@Autowired
	private DictionaryMapper dicMapper;
	@Override
	public int addPower(Map<String, Object> entity) {
		 int count = powerMapper.insert(entity);
		 return count;
	}

	@Override
	public Page findPowerToPage(Map<String, Object> condition, int index, int size) {
		//通过条件查询记录数
		int count = powerMapper.quaryCountByCondition(condition);
		//通过条件查询数据    注意：开始位置=当前索引*每页记录数
		int start = index*size;
		List<Map<String,Object>> powers = powerMapper.findAllPowerToPage(condition, start, size);
		for (Map<String, Object> power : powers) {
			Object modularId = power.get("modular_id");
			Map<String, Object> modular = modularMapper.quaryById(modularId);
			power.put("modular", modular);
			
			Object isShow = power.get("power_is_show");
			Map<String, Object> dic = dicMapper.findByTypeCodeAndValue(isShow, 1005);
			power.put("isShow", dic);
			
			//3.显示父权限
			Object parentPowerId = power.get("power_parent");
			if ((Long)parentPowerId==0) {
				Map<String, Object> parent=new HashMap<>();
				parent.put("power_name", "顶级菜单");
				power.put("parent", parent);
			}else {
				Map<String, Object> parent = powerMapper.quaryById(parentPowerId);
				power.put("parent", parent);
			}
			
		}
		Page page = new Page(index, size, count, powers);
		return page;
	}

	@Override
	public Map<String, Object> findPowerById(Long powerId) {

		return powerMapper.quaryById(powerId);
	}

	@Override
	public void powerEdit(Map<String, Object> entity) {
		 powerMapper.editPower(entity);
		
	}

	@Override
	public void deleteById(Object... ownId) {
		powerMapper.deleteById(ownId);
	}

	@Override
	public List<Map<String, Object>> findAllPower() {
		return powerMapper.findAllPower();
	}

	

}
