package cn.gzsxt.pms.mapper.provider;

import java.util.Arrays;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public class CarSpaceProvider {

	/**根据条件模糊查询，并分页
	 * 注意事项：加上注解的对象，必须需要使用@Param绑定的key来获得值
	 * @param entity 条件
	 * @param start 开始页码
	 * @param size 每页记录数
	 * @return
	 */
	public String findAllCarSpaceToPage(@Param("entity")Map<String, Object> entity, @Param("start")int start, @Param("size")int size) {
		String sql = "SELECT *	FROM tb_parkingspace WHERE 1=1";
		StringBuilder builder = new StringBuilder(sql);
		//判断条件不为空
		if (entity!=null) {
			
			if (entity.get("park_id")!=null) {
				builder.append(" AND park_id=#{entity.park_id}");
			}
			if (entity.get("park_location")!=null) {
				builder.append(" AND park_location like  CONCAT('%',#{entity.park_location},'%')");
			}
			if (entity.get("park_length")!=null) {
				builder.append(" AND park_length=#{entity.park_length}");
			}
			if (entity.get("park_width")!=null) {
				builder.append(" AND park_width=#{entity.park_width}");
			}
			if (entity.get("park_charge")!=null) {
				builder.append(" AND park_charge=#{entity.park_charge}");
			}
			if (entity.get("park_status")!=null) {
				builder.append(" AND park_status=#{entity.park_status}");
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
		String sql = "SELECT COUNT(*) FROM tb_parkingspace WHERE 1=1";
		StringBuilder builder = new StringBuilder(sql);
		//判断条件不为空
		if (condition!=null) {
			
			if (condition.get("park_id")!=null) {
				builder.append(" AND park_id=#{park_id}");
			}
			if (condition.get("park_location")!=null) {
				builder.append(" AND park_location like  CONCAT('%',#{park_location},'%')");
			}
			if (condition.get("park_length")!=null) {
				builder.append(" AND park_length=#{park_length}");
			}
			if (condition.get("park_width")!=null) {
				builder.append(" AND park_width=#{park_width}");
			}
			if (condition.get("park_charge")!=null) {
				builder.append(" AND park_charge=#{park_charge}");
			}
			if (condition.get("park_status")!=null) {
				builder.append(" AND park_status=#{park_status}");
			}
		}
		return builder.toString();
	}
	
	public String editCarSpace(Map<String, Object> entity) {
		String sql = "UPDATE tb_parkingspace SET ";
		StringBuilder builder = new StringBuilder(sql);
		if (entity!=null) {
			
			if (entity.get("park_location")!=null) {
				builder.append(" park_location=#{park_location},");
			}
			if (entity.get("park_length")!=null) {
				builder.append(" park_length=#{park_length},");
			}
			if (entity.get("park_width")!=null) {
				builder.append(" park_width=#{park_width},");
			}
			if (entity.get("park_charge")!=null) {
				builder.append(" park_charge=#{park_charge},");
			}
			if (entity.get("park_status")!=null) {
				builder.append(" park_status=#{park_status},");
			}
		}
		builder.delete(builder.length()-1, builder.length());
		builder.append(" WHERE park_id=#{park_id}");
		return builder.toString();
	}
	
	public String deleteById(Map<String, Object[]> ownIds) {
		Object[] ids = ownIds.get("array");
		String idStr = Arrays.toString(ids);
		String sql = "DELETE FROM tb_parkingspace WHERE park_id in ";
		StringBuilder builder = new StringBuilder(sql);
		builder.append(idStr);
		//将[]修改为()
		builder.setCharAt(builder.indexOf("["),'(');
		builder.setCharAt(builder.indexOf("]"),')');
		return builder.toString();
	}
}
