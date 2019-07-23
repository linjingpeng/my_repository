package cn.gzsxt.pms.mapper.provider;

import java.util.Arrays;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public class RepairProvider {

	/**根据条件模糊查询，并分页
	 * 注意事项：加上注解的对象，必须需要使用@Param绑定的key来获得值
	 * @param entity 条件
	 * @param start 开始页码
	 * @param size 每页记录数
	 * @return
	 */
	public String findAllRepairToPage(@Param("entity")Map<String, Object> entity, @Param("start")int start, @Param("size")int size) {
		String sql = "SELECT *	FROM tb_repair WHERE 1=1";
		StringBuilder builder = new StringBuilder(sql);
		//判断条件不为空
		if (entity!=null) {
			
			if (entity.get("repair_id")!=null) {
				builder.append(" AND repair_id=#{entity.repair_id}");
			}
			if (entity.get("house_id")!=null) {
				builder.append(" AND house_id=#{entity.house_id}");
			}
			if (entity.get("repair_type")!=null) {
				builder.append(" AND repair_type=#{entity.repair_type}");
			}
			if (entity.get("repair_desc")!=null) {
				builder.append(" AND repair_desc=#{entity.repair_desc}");
			}
			if (entity.get("repair_time")!=null) {
				builder.append(" AND repair_time like  CONCAT('%',#{entity.repair_time},'%')");
			}
			if (entity.get("repair_status")!=null) {
				builder.append(" AND repair_status=#{entity.repair_status}");
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
		String sql = "SELECT COUNT(*) FROM tb_repair WHERE 1=1";
		StringBuilder builder = new StringBuilder(sql);
		//判断条件不为空
		if (condition!=null) {
			
			if (condition.get("repair_id")!=null) {
				builder.append(" AND repair_id=#{repair_id}");
			}
			if (condition.get("house_id")!=null) {
				builder.append(" AND house_id =#{house_id}");
			}
			if (condition.get("repair_type")!=null) {
				builder.append(" AND repair_type=#{repair_type}");
			}
			if (condition.get("repair_desc")!=null) {
				builder.append(" AND repair_desc=#{repair_desc}");
			}
			if (condition.get("repair_time")!=null) {
				builder.append(" AND repair_time like  CONCAT('%',#{repair_time},'%')");
			}
			if (condition.get("repair_status")!=null) {
				builder.append(" AND repair_status=#{repair_status}");
			}
		}
		return builder.toString();
	}
	
	public String editRepair(Map<String, Object> entity) {
		String sql = "UPDATE tb_repair SET ";
		StringBuilder builder = new StringBuilder(sql);
		if (entity!=null) {
			
			if (entity.get("house_id")!=null) {
				builder.append(" house_id=#{house_id},");
			}
			if (entity.get("repair_type")!=null) {
				builder.append(" repair_type=#{repair_type},");
			}
			if (entity.get("repair_desc")!=null) {
				builder.append(" repair_desc=#{repair_desc},");
			}
			if (entity.get("repair_time")!=null) {
				builder.append(" repair_time=#{repair_time},");
			}
			if (entity.get("repair_status")!=null) {
				builder.append(" repair_status=#{repair_status},");
			}
		}
		builder.delete(builder.length()-1, builder.length());
		builder.append(" WHERE repair_id=#{repair_id}");
		return builder.toString();
	}
	
	public String deleteById(Map<String, Object[]> ownIds) {
		Object[] ids = ownIds.get("array");
		String idStr = Arrays.toString(ids);
		String sql = "DELETE FROM tb_repair WHERE repair_id in ";
		StringBuilder builder = new StringBuilder(sql);
		builder.append(idStr);
		//将[]修改为()
		builder.setCharAt(builder.indexOf("["),'(');
		builder.setCharAt(builder.indexOf("]"),')');
		return builder.toString();
	}
}
