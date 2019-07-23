package cn.gzsxt.pms.service;

import java.util.List;
import java.util.Map;

import cn.gzsxt.pms.utils.Page;

public interface ModularService {

	
	/**增加住户
	 * @param entity
	 * @return
	 */
	int addModular(Map<String, Object> entity);
	
	/**根据条件查询，分页返回数据
	 * @param condition 条件
	 * @param index 当前索引
	 * @param size  每页记录数
	 * @return 分页对象
	 */
	Page findModularToPage(Map<String, Object> condition,int index, int size);
	
	/**根据id查询权限
	 * @param modularId
	 * @return
	 */
	Map<String, Object> findModularById(Long modularId);

	/**更新权限信息
	 * @param entity
	 */
	void modularEdit(Map<String, Object> entity);

	/**通过id删除权限信息
	 * @param ownId
	 */
	void deleteById(Object... ownId);
	
	/**查找所有权限
	 * @return
	 */
	List<Map<String, Object>> findAllModular();
}
