package cn.gzsxt.pms.service;

import java.util.Map;

import cn.gzsxt.pms.utils.Page;

public interface CarSpaceService {

	
	/**增加住户
	 * @param entity
	 * @return
	 */
	int addCarSpace(Map<String, Object> entity);
	
	/**根据条件查询，分页返回数据
	 * @param condition 条件
	 * @param index 当前索引
	 * @param size  每页记录数
	 * @return 分页对象
	 */
	Page findCarSpaceToPage(Map<String, Object> condition,int index, int size);
	
	/**根据id查询户主
	 * @param carSpaceId
	 * @return
	 */
	Map<String, Object> findCarSpaceById(Long carSpaceId);

	/**更新户主信息
	 * @param entity
	 */
	void carSpaceEdit(Map<String, Object> entity);

	/**通过id删除户主信息
	 * @param ownId
	 */
	void deleteById(Object... ownId);
}
