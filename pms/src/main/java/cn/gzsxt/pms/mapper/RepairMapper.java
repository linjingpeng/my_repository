package cn.gzsxt.pms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import cn.gzsxt.pms.mapper.provider.RepairProvider;

@Mapper
public interface RepairMapper {
	/**插入管理员记录
	 * @param entity 数据实体
	 * @return 影响的行号
	 */
	@Insert(value="INSERT INTO tb_repair (house_id, repair_type, repair_desc, repair_time, repair_status) VALUES ( #{house_id}, #{repair_type}, #{repair_desc},#{repair_time}, #{repair_status})")
	@Options(useGeneratedKeys=true,keyProperty="repair_id",keyColumn="repair_id")
	int insert(Map<String, Object> entity);

	/**查询所有的户主，并分页
	 * @return
	 */
	@SelectProvider(type=RepairProvider.class,method="findAllRepairToPage")
	List<Map<String, Object>> findAllRepairToPage(@Param("entity")Map<String, Object> entity, @Param("start")int start, @Param("size")int size);
	
	/**根据条件查询记录数
	 * @param condition
	 * @return
	 */
	@SelectProvider(type=RepairProvider.class,method="quaryCountByCondition")
	int quaryCountByCondition(Map<String, Object> condition);
	
	/**根据id查询户主
	 * @param repairId
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_repair WHERE repair_id =#{repairId}")
	Map<String, Object> quaryById(Long repairId);

	/**更新户主信息
	 * @param entity
	 */
	@UpdateProvider(type=RepairProvider.class,method="editRepair")
	void editRepair(Map<String, Object> entity);

	/**通过id删除户主
	 * @param ownId
	 */
	@DeleteProvider(type=RepairProvider.class,method="deleteById")
	void deleteById(Object... ownId);
}
