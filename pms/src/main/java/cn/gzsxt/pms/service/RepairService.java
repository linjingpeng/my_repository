package cn.gzsxt.pms.service;

import java.util.Map;

import cn.gzsxt.pms.utils.Page;

public interface RepairService {

	
	/**增加住房
	 * @param entity
	 * @return
	 */
	int addRepair(Map<String, Object> entity);
	
	/**根据条件查询，分页返回数据
	 * @param condition 条件
	 * @param index 当前索引
	 * @param size  每页记录数
	 * @return 分页对象
	 */
	Page findRepairToPage(Map<String, Object> condition,int index, int size);
	
	/**根据id查询住房
	 * @param repairId
	 * @return
	 */
	Map<String, Object> findRepairById(Long repairId);

	/**更新住房信息
	 * @param entity
	 */
	void repairEdit(Map<String, Object> entity);

	/**通过id删除住房信息
	 * @param ownId
	 */
	void deleteById(Object... ownId);
}
