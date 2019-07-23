package cn.gzsxt.pms.service;

import java.util.List;
import java.util.Map;

import cn.gzsxt.pms.utils.Page;

public interface PowerService {

	
	/**增加住户
	 * @param entity
	 * @return
	 */
	int addPower(Map<String, Object> entity);
	
	/**根据条件查询，分页返回数据
	 * @param condition 条件
	 * @param index 当前索引
	 * @param size  每页记录数
	 * @return 分页对象
	 */
	Page findPowerToPage(Map<String, Object> condition,int index, int size);
	
	/**根据id查询权限
	 * @param powerId
	 * @return
	 */
	Map<String, Object> findPowerById(Long powerId);

	/**更新权限信息
	 * @param entity
	 */
	void powerEdit(Map<String, Object> entity);

	/**通过id删除权限信息
	 * @param ownId
	 */
	void deleteById(Object... ownId);
	
	/**查找所有权限
	 * @return
	 */
	List<Map<String, Object>> findAllPower();
}
