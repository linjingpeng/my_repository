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

import cn.gzsxt.pms.mapper.provider.PowerProvider;

@Mapper
public interface PowerMapper {
	/**插入管理员记录
	 * @param entity 数据实体
	 * @return 影响的行号
	 */
	@Insert(value="INSERT INTO tb_power (power_name, power_action, power_parent, power_is_show, modular_id) VALUES ( #{power_name}, #{power_action}, #{power_parent}, #{power_is_show}, #{modular_id})")
	@Options(useGeneratedKeys=true,keyProperty="power_id",keyColumn="power_id")
	int insert(Map<String, Object> entity);

	/**查询所有的户主，并分页
	 * @return
	 */
	@SelectProvider(type=PowerProvider.class,method="findAllPowerToPage")
	List<Map<String, Object>> findAllPowerToPage(@Param("entity")Map<String, Object> entity, @Param("start")int start, @Param("size")int size);
	
	/**根据条件查询记录数
	 * @param condition
	 * @return
	 */
	@SelectProvider(type=PowerProvider.class,method="quaryCountByCondition")
	int quaryCountByCondition(Map<String, Object> condition);
	
	/**根据id查询户主
	 * @param powerId
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_power WHERE power_id =#{powerId}")
	Map<String, Object> quaryById(Object powerId);

	/**更新户主信息
	 * @param entity
	 */
	@UpdateProvider(type=PowerProvider.class,method="editPower")
	void editPower(Map<String, Object> entity);

	/**通过id删除户主
	 * @param ownId
	 */
	@DeleteProvider(type=PowerProvider.class,method="deleteById")
	void deleteById(Object... ownId);
	
	/**查询所有户主
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_power")
	List<Map<String, Object>> findAllPower();
	/**
	 * 通过一组权限编号操作一组权限记录
	 * @param ids
	 * @return
	 */
	@SelectProvider(type=PowerProvider.class,method="findByIds")
	List<Map<String, Object>> findByIds(Object... powerIds);
}
