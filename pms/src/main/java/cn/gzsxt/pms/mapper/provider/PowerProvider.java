package cn.gzsxt.pms.mapper.provider;

import java.util.Arrays;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public class PowerProvider {

	/**根据条件模糊查询，并分页
	 * 注意事项：加上注解的对象，必须需要使用@Param绑定的key来获得值
	 * @param entity 条件
	 * @param start 开始页码
	 * @param size 每页记录数
	 * @return
	 */
	public String findAllPowerToPage(@Param("entity")Map<String, Object> entity, @Param("start")int start, @Param("size")int size) {
		String sql = "SELECT *	FROM tb_power WHERE 1=1";
		StringBuilder builder = new StringBuilder(sql);
		//判断条件不为空
		if (entity!=null) {
			
			if (entity.get("power_id")!=null) {
				builder.append(" AND power_id=#{entity.power_id}");
			}
			if (entity.get("power_name")!=null) {
				builder.append(" AND power_name like  CONCAT('%',#{entity.power_name},'%')");
			}
			if (entity.get("power_action")!=null) {
				builder.append(" AND power_action=#{entity.power_action}");
			}
			if (entity.get("power_parent")!=null) {
				builder.append(" AND power_parent=#{entity.power_parent}");
			}
			if (entity.get("power_is_show")!=null) {
				builder.append(" AND power_is_show=#{entity.power_is_show}");
			}
			if (entity.get("modular_id")!=null) {
				builder.append(" AND modular_id=#{entity.modular_id}");
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
		String sql = "SELECT COUNT(*) FROM tb_power WHERE 1=1";
		StringBuilder builder = new StringBuilder(sql);
		//判断条件不为空
		if (condition!=null) {
			
			if (condition.get("power_id")!=null) {
				builder.append(" AND power_id=#{power_id}");
			}
			if (condition.get("power_name")!=null) {
				builder.append(" AND power_name like  CONCAT('%',#{power_name},'%')");
			}
			if (condition.get("power_action")!=null) {
				builder.append(" AND power_action=#{power_action}");
			}
			if (condition.get("power_parent")!=null) {
				builder.append(" AND power_parent=#{power_parent}");
			}
			if (condition.get("power_is_show")!=null) {
				builder.append(" AND power_is_show=#{power_is_show}");
			}
			if (condition.get("modular_id")!=null) {
				builder.append(" AND modular_id=#{modular_id}");
			}
		}
		return builder.toString();
	}
	
	public String editPower(Map<String, Object> entity) {
		String sql = "UPDATE tb_power SET ";
		StringBuilder builder = new StringBuilder(sql);
		if (entity!=null) {
			
			if (entity.get("power_name")!=null) {
				builder.append(" power_name=#{power_name},");
			}
			if (entity.get("power_action")!=null) {
				builder.append(" power_action=#{power_action},");
			}
			if (entity.get("power_parent")!=null) {
				builder.append(" power_parent=#{power_parent},");
			}
			if (entity.get("power_is_show")!=null) {
				builder.append(" power_is_show=#{power_is_show},");
			}
			if (entity.get("modular_id")!=null) {
				builder.append(" modular_id=#{modular_id},");
			}
		}
		builder.delete(builder.length()-1, builder.length());
		builder.append(" WHERE power_id=#{power_id}");
		return builder.toString();
	}
	
	public String deleteById(Map<String, Object[]> ownIds) {
		Object[] ids = ownIds.get("array");
		String idStr = Arrays.toString(ids);
		String sql = "DELETE FROM tb_power WHERE power_id in ";
		StringBuilder builder = new StringBuilder(sql);
		builder.append(idStr);
		//将[]修改为()
		builder.setCharAt(builder.indexOf("["),'(');
		builder.setCharAt(builder.indexOf("]"),')');
		return builder.toString();
	}
	public String findByIds(Map<String, Object[]> powerIdsMap) {
		Object[] powerIds = powerIdsMap.get("array");
		String idsStr = Arrays.toString(powerIds);
		String sql="SELECT * FROM tb_power WHERE power_id in ";
		StringBuilder builder=new StringBuilder(sql);		
		builder.append(idsStr);
		//将[]修改为()
		builder.setCharAt(builder.indexOf("["), '(');
		builder.setCharAt(builder.indexOf("]"), ')');
	
		return builder.toString();
	}
}
