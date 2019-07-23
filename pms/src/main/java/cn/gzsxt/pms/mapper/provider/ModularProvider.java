package cn.gzsxt.pms.mapper.provider;

import java.util.Arrays;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public class ModularProvider {

	/**根据条件模糊查询，并分页
	 * 注意事项：加上注解的对象，必须需要使用@Param绑定的key来获得值
	 * @param entity 条件
	 * @param start 开始页码
	 * @param size 每页记录数
	 * @return
	 */
	public String findAllModularToPage(@Param("entity")Map<String, Object> entity, @Param("start")int start, @Param("size")int size) {
		String sql = "SELECT *	FROM tb_modular WHERE 1=1";
		StringBuilder builder = new StringBuilder(sql);
		//判断条件不为空
		if (entity!=null) {
			
			if (entity.get("modular_id")!=null) {
				builder.append(" AND modular_id=#{entity.modular_id}");
			}
			if (entity.get("modular_name")!=null) {
				builder.append(" AND modular_name like  CONCAT('%',#{entity.modular_name},'%')");
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
		String sql = "SELECT COUNT(*) FROM tb_modular WHERE 1=1";
		StringBuilder builder = new StringBuilder(sql);
		//判断条件不为空
		if (condition!=null) {
			
			if (condition.get("modular_id")!=null) {
				builder.append(" AND modular_id=#{modular_id}");
			}
			if (condition.get("modular_name")!=null) {
				builder.append(" AND modular_name like  CONCAT('%',#{modular_name},'%')");
			}
		}
		return builder.toString();
	}
	
	public String editModular(Map<String, Object> entity) {
		String sql = "UPDATE tb_modular SET ";
		StringBuilder builder = new StringBuilder(sql);
		if (entity!=null) {
			
			if (entity.get("modular_name")!=null) {
				builder.append(" modular_name=#{modular_name},");
			}
		}
		builder.delete(builder.length()-1, builder.length());
		builder.append(" WHERE modular_id=#{modular_id}");
		return builder.toString();
	}
	
	public String deleteById(Map<String, Object[]> ownIds) {
		Object[] ids = ownIds.get("array");
		String idStr = Arrays.toString(ids);
		String sql = "DELETE FROM tb_modular WHERE modular_id in ";
		StringBuilder builder = new StringBuilder(sql);
		builder.append(idStr);
		//将[]修改为()
		builder.setCharAt(builder.indexOf("["),'(');
		builder.setCharAt(builder.indexOf("]"),')');
		return builder.toString();
	}
}
