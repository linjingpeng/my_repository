package cn.gzsxt.pms.mapper.provider;

import java.util.Arrays;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public class RoleProvider {

	/**根据条件模糊查询，并分页
	 * 注意事项：加上注解的对象，必须需要使用@Param绑定的key来获得值
	 * @param entity 条件
	 * @param start 开始页码
	 * @param size 每页记录数
	 * @return
	 */
	public String findAllRoleToPage(@Param("entity")Map<String, Object> entity, @Param("start")int start, @Param("size")int size) {
		String sql = "SELECT *	FROM tb_role WHERE 1=1";
		StringBuilder builder = new StringBuilder(sql);
		//判断条件不为空
		if (entity!=null) {
			
			if (entity.get("role_id")!=null) {
				builder.append(" AND role_id=#{entity.role_id}");
			}
			if (entity.get("role_name")!=null) {
				builder.append(" AND role_name like  CONCAT('%',#{entity.role_name},'%')");
			}
			if (entity.get("role_powers")!=null) {
				builder.append(" AND role_powers=#{entity.role_powers}");
			}
			
		}
		builder.append(" LIMIT #{start},#{size}");
		return builder.toString();
		
	}
	
	/**根据条件查询总记录数
	 * @param condition
	 * @return
	 */
	public String quaryCountByCondition(Map<String, Object> condition) {
		String sql = "SELECT COUNT(*) FROM tb_role WHERE 1=1";
		StringBuilder builder = new StringBuilder(sql);
		//判断条件不为空
		if (condition!=null) {
			
			if (condition.get("role_id")!=null) {
				builder.append(" AND role_id=#{role_id}");
			}
			if (condition.get("role_name")!=null) {
				builder.append(" AND role_name like  CONCAT('%',#{role_name},'%')");
			}
			if (condition.get("role_powers")!=null) {
				builder.append(" AND role_powers=#{role_powers}");
			}
			
		}
		return builder.toString();
	}
	
	public String editRole(Map<String, Object> entity) {
		String sql = "UPDATE tb_role SET ";
		StringBuilder builder = new StringBuilder(sql);
		if (entity!=null) {
			
			if (entity.get("role_name")!=null) {
				builder.append(" role_name=#{role_name},");
			}
			if (entity.get("role_powers")!=null) {
				builder.append(" role_powers=#{role_powers},");
			}
			
		}
		builder.delete(builder.length()-1, builder.length());
		builder.append(" WHERE role_id=#{role_id}");
		return builder.toString();
	}
	
	public String deleteById(Map<String, Object[]> ownIds) {
		Object[] ids = ownIds.get("array");
		String idStr = Arrays.toString(ids);
		String sql = "DELETE FROM tb_role WHERE role_id in ";
		StringBuilder builder = new StringBuilder(sql);
		builder.append(idStr);
		//将[]修改为()
		builder.setCharAt(builder.indexOf("["),'(');
		builder.setCharAt(builder.indexOf("]"),')');
		return builder.toString();
	}
}
