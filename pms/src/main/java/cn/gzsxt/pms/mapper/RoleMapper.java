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

import cn.gzsxt.pms.mapper.provider.RoleProvider;

@Mapper
public interface RoleMapper {
	/**插入管理员记录
	 * @param entity 数据实体
	 * @return 影响的行号
	 */
	@Insert(value="INSERT INTO tb_role (role_name, role_powers) VALUES ( #{role_name}, #{role_powers})")
	@Options(useGeneratedKeys=true,keyProperty="role_id",keyColumn="role_id")
	int insert(Map<String, Object> entity);

	/**查询所有的户主，并分页
	 * @return
	 */
	@SelectProvider(type=RoleProvider.class,method="findAllRoleToPage")
	List<Map<String, Object>> findAllRoleToPage(@Param("entity")Map<String, Object> entity, @Param("start")int start, @Param("size")int size);
	
	/**根据条件查询记录数
	 * @param condition
	 * @return
	 */
	@SelectProvider(type=RoleProvider.class,method="quaryCountByCondition")
	int quaryCountByCondition(Map<String, Object> condition);
	
	/**根据id查询户主
	 * @param roleId
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_role WHERE role_id =#{roleId}")
	Map<String, Object> quaryById(Object roleId);

	/**更新户主信息
	 * @param entity
	 */
	@UpdateProvider(type=RoleProvider.class,method="editRole")
	int editRole(Map<String, Object> entity);

	/**通过id删除户主
	 * @param ownId
	 */
	@DeleteProvider(type=RoleProvider.class,method="deleteById")
	void deleteById(Object... ownId);
	
	/**查询所有户主
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_role")
	List<Map<String, Object>> findAllRole();
}
