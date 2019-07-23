package cn.gzsxt.pms.service;

import java.util.List;
import java.util.Map;

import cn.gzsxt.pms.utils.Page;

public interface RoleService {

	
	/**增加住户
	 * @param entity
	 * @return
	 */
	Map<String, Object> addRole(Map<String, Object> entity);
	
	/**根据条件查询，分页返回数据
	 * @param condition 条件
	 * @param index 当前索引
	 * @param size  每页记录数
	 * @return 分页对象
	 */
	Page findRoleToPage(Map<String, Object> condition,int index, int size);
	
	/**根据id查询户主
	 * @param roleId
	 * @return
	 */
	Map<String, Object> findRoleById(Long roleId);

	/**更新户主信息
	 * @param entity
	 * @return 
	 */
	Map<String, Object> roleEdit(Map<String, Object> entity);

	/**通过id删除户主信息
	 * @param ownId
	 */
	void deleteById(Object... ownId);
	
	/**查找所有户主
	 * @return
	 */
	List<Map<String, Object>> findAllRole();
}
