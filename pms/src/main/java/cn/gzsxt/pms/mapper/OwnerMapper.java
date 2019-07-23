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

import cn.gzsxt.pms.mapper.provider.OwnerProvider;

@Mapper
public interface OwnerMapper {
	/**插入管理员记录
	 * @param entity 数据实体
	 * @return 影响的行号
	 */
	@Insert(value="INSERT INTO tb_owner (owner_name, owner_gender, owner_phone, owner_identityid, register_time) VALUES ( #{owner_name}, #{owner_gender}, #{owner_phone},#{owner_identityid}, #{register_time})")
	@Options(useGeneratedKeys=true,keyProperty="owner_id",keyColumn="owner_id")
	int insert(Map<String, Object> entity);

	/**查询所有的户主，并分页
	 * @return
	 */
	@SelectProvider(type=OwnerProvider.class,method="findAllOwnerToPage")
	List<Map<String, Object>> findAllOwnerToPage(@Param("entity")Map<String, Object> entity, @Param("start")int start, @Param("size")int size);
	
	/**根据条件查询记录数
	 * @param condition
	 * @return
	 */
	@SelectProvider(type=OwnerProvider.class,method="quaryCountByCondition")
	int quaryCountByCondition(Map<String, Object> condition);
	
	/**根据id查询户主
	 * @param ownerId
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_owner WHERE owner_id =#{ownerId}")
	Map<String, Object> quaryById(Object ownerId);

	/**更新户主信息
	 * @param entity
	 */
	@UpdateProvider(type=OwnerProvider.class,method="editOwner")
	void editOwner(Map<String, Object> entity);

	/**通过id删除户主
	 * @param ownId
	 */
	@DeleteProvider(type=OwnerProvider.class,method="deleteById")
	void deleteById(Object... ownId);
	
	/**查询所有户主
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_owner")
	List<Map<String, Object>> findAllOwner();
}
