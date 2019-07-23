package cn.gzsxt.pms.mapper.provider;

import java.util.Arrays;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public class OwnerProvider {

	/**根据条件模糊查询，并分页
	 * 注意事项：加上注解的对象，必须需要使用@Param绑定的key来获得值
	 * @param entity 条件
	 * @param start 开始页码
	 * @param size 每页记录数
	 * @return
	 */
	public String findAllOwnerToPage(@Param("entity")Map<String, Object> entity, @Param("start")int start, @Param("size")int size) {
		String sql = "SELECT *	FROM tb_owner WHERE 1=1";
		StringBuilder builder = new StringBuilder(sql);
		//判断条件不为空
		if (entity!=null) {
			
			if (entity.get("owner_id")!=null) {
				builder.append(" AND owner_id=#{entity.owner_id}");
			}
			if (entity.get("owner_name")!=null) {
				builder.append(" AND owner_name like  CONCAT('%',#{entity.owner_name},'%')");
			}
			if (entity.get("owner_gender")!=null) {
				builder.append(" AND owner_gender=#{entity.owner_gender}");
			}
			if (entity.get("owner_phone")!=null) {
				builder.append(" AND owner_phone like  CONCAT('%',#{entity.owner_phone},'%')");
			}
			if (entity.get("owner_identityid")!=null) {
				builder.append(" AND owner_identityid like  CONCAT('%',#{entity.owner_identityid},'%')");
			}
			if (entity.get("register_time")!=null) {
				builder.append(" AND register_time like  CONCAT('%',#{entity.register_time},'%')");
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
		String sql = "SELECT COUNT(*) FROM tb_owner WHERE 1=1";
		StringBuilder builder = new StringBuilder(sql);
		//判断条件不为空
		if (condition!=null) {
			
			if (condition.get("owner_id")!=null) {
				builder.append(" AND owner_id=#{owner_id}");
			}
			if (condition.get("owner_name")!=null) {
				builder.append(" AND owner_name like  CONCAT('%',#{owner_name},'%')");
			}
			if (condition.get("owner_gender")!=null) {
				builder.append(" AND owner_gender=#{owner_gender}");
			}
			if (condition.get("owner_phone")!=null) {
				builder.append(" AND owner_phone like  CONCAT('%',#{owner_phone},'%')");
			}
			if (condition.get("owner_identityid")!=null) {
				builder.append(" AND owner_identityid like  CONCAT('%',#{owner_identityid},'%')");
			}
			if (condition.get("register_time")!=null) {
				builder.append(" AND register_time like  CONCAT('%',#{register_time},'%')");
			}
		}
		return builder.toString();
	}
	
	public String editOwner(Map<String, Object> entity) {
		String sql = "UPDATE tb_owner SET ";
		StringBuilder builder = new StringBuilder(sql);
		if (entity!=null) {
			
			if (entity.get("owner_name")!=null) {
				builder.append(" owner_name=#{owner_name},");
			}
			if (entity.get("owner_gender")!=null) {
				builder.append(" owner_gender=#{owner_gender},");
			}
			if (entity.get("owner_phone")!=null) {
				builder.append(" owner_phone=#{owner_phone},");
			}
			if (entity.get("owner_identityid")!=null) {
				builder.append(" owner_identityid=#{owner_identityid},");
			}
			if (entity.get("register_time")!=null) {
				builder.append(" register_time=#{register_time},");
			}
		}
		builder.delete(builder.length()-1, builder.length());
		builder.append(" WHERE owner_id=#{owner_id}");
		return builder.toString();
	}
	
	public String deleteById(Map<String, Object[]> ownIds) {
		Object[] ids = ownIds.get("array");
		String idStr = Arrays.toString(ids);
		String sql = "DELETE FROM tb_owner WHERE owner_id in ";
		StringBuilder builder = new StringBuilder(sql);
		builder.append(idStr);
		//将[]修改为()
		builder.setCharAt(builder.indexOf("["),'(');
		builder.setCharAt(builder.indexOf("]"),')');
		return builder.toString();
	}
}
