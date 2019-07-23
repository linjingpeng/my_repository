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

import cn.gzsxt.pms.mapper.provider.CarSpaceProvider;

@Mapper
public interface CarSpaceMapper {
	/**插入管理员记录
	 * @param entity 数据实体
	 * @return 影响的行号
	 */
	@Insert(value="INSERT INTO tb_parkingspace (park_location, park_length, park_width, park_charge, park_status) VALUES ( #{park_location}, #{park_length}, #{park_width},#{park_charge}, #{park_status})")
	@Options(useGeneratedKeys=true,keyProperty="park_id",keyColumn="park_id")
	int insert(Map<String, Object> entity);

	/**查询所有的户主，并分页
	 * @return
	 */
	@SelectProvider(type=CarSpaceProvider.class,method="findAllCarSpaceToPage")
	List<Map<String, Object>> findAllCarSpaceToPage(@Param("entity")Map<String, Object> entity, @Param("start")int start, @Param("size")int size);
	
	/**根据条件查询记录数
	 * @param condition
	 * @return
	 */
	@SelectProvider(type=CarSpaceProvider.class,method="quaryCountByCondition")
	int quaryCountByCondition(Map<String, Object> condition);
	
	/**根据id查询户主
	 * @param parkingspaceId
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_parkingspace WHERE park_id =#{parkId}")
	Map<String, Object> quaryById(Long parkId);

	/**更新户主信息
	 * @param entity
	 */
	@UpdateProvider(type=CarSpaceProvider.class,method="editCarSpace")
	void editCarSpace(Map<String, Object> entity);

	/**通过id删除户主
	 * @param ownId
	 */
	@DeleteProvider(type=CarSpaceProvider.class,method="deleteById")
	void deleteById(Object... ownId);
}
