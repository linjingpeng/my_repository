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

import cn.gzsxt.pms.mapper.provider.AdminProvider;

@Mapper
public interface AdminMapper {
	/**插入管理员记录
	 * @param entity 数据实体
	 * @return 影响的行号
	 */
	@Insert(value="INSERT INTO tb_admin (admin_id, admin_name, admin_account, admin_pwd, admin_status, role_id) VALUES (#{admin_id}, #{admin_name}, #{admin_account}, #{admin_pwd},#{admin_status}, #{role_id})")
	@Options(useGeneratedKeys=true,keyProperty="admin_id",keyColumn="admin_id")
	int insert(Map<String, Object> entity);

	/**通过账号查询管理员
	 * @return
	 */
	@Select(value="SELECT * FROM tb_admin WHERE admin_account=#{acctName}")
	Map<String, Object> findByAccount(Object acctName);
	/**查询所有的用户，并分页
	 * @return
	 */
	@SelectProvider(type=AdminProvider.class,method="findAllAdminToPage")
	List<Map<String, Object>> findAllAdminToPage(@Param("entity")Map<String, Object> entity, @Param("start")int start, @Param("size")int size);
	
	/**根据条件查询记录数
	 * @param condition
	 * @return
	 */
	@SelectProvider(type=AdminProvider.class,method="quaryCountByCondition")
	int quaryCountByCondition(Map<String, Object> condition);
	
	/**根据id查询用户
	 * @param adminId
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_admin WHERE admin_id =#{adminId}")
	Map<String, Object> quaryById(Object adminId);

	/**更新用户信息
	 * @param entity
	 */
	@UpdateProvider(type=AdminProvider.class,method="editAdmin")
	void editAdmin(Map<String, Object> entity);

	/**通过id删除用户
	 * @param adminId
	 */
	@DeleteProvider(type=AdminProvider.class,method="deleteById")
	void deleteById(Object... adminId);
	/*
	 * 通过角色查找用户
	 */
	@Select(value="SELECT *	FROM tb_admin  WHERE role_id = #{roleId}")
	List<Map<String, Object>> findAdminByRoleId(Object roleId);
}
