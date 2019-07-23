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

import cn.gzsxt.pms.mapper.provider.HouseProvider;

@Mapper
public interface HouseMapper {
	/**插入住房记录
	 * @param entity 数据实体
	 * @return 影响的行号
	 */
	@Insert(value="INSERT INTO tb_house (house_no,house_shape, house_floor_id, house_cell_id, house_area, owner_id) VALUES ( #{house_no}, #{house_shape}, #{house_floor_id},#{house_cell_id}, #{house_area},#{owner_id})")
	@Options(useGeneratedKeys=true,keyProperty="admin_id",keyColumn="admin_id")
	int insert(Map<String, Object> entity);

	/**查询所有的住房，并分页
	 * @return
	 */
	@SelectProvider(type=HouseProvider.class,method="findAllHouseToPage")
	List<Map<String, Object>> findAllHouseToPage(@Param("entity")Map<String, Object> entity, @Param("start")int start, @Param("size")int size);
	
	/**根据条件查询记录数
	 * @param condition
	 * @return
	 */
	@SelectProvider(type=HouseProvider.class,method="quaryCountByCondition")
	int quaryCountByCondition(Map<String, Object> condition);
	
	/**根据id查询住房
	 * @param houseId
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_house WHERE house_id =#{houseId}")
	Map<String, Object> quaryById(Object houseId);

	/**更新住房信息
	 * @param entity
	 */
	@UpdateProvider(type=HouseProvider.class,method="editHouse")
	void editHouse(Map<String, Object> entity);

	/**通过id删除住房
	 * @param ownId
	 */
	@DeleteProvider(type=HouseProvider.class,method="deleteById")
	void deleteById(Object... ownId);
	/**查询所有房子
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_house")
	List<Map<String, Object>> findAll();
}
