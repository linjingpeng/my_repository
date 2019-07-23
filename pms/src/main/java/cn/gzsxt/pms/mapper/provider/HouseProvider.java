package cn.gzsxt.pms.mapper.provider;

import java.util.Arrays;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public class HouseProvider {

	/**根据条件模糊查询，并分页
	 * 注意事项：加上注解的对象，必须需要使用@Param绑定的key来获得值
	 * @param entity 条件
	 * @param start 开始页码
	 * @param size 每页记录数
	 * @return
	 */
	public String findAllHouseToPage(@Param("entity")Map<String, Object> entity, @Param("start")int start, @Param("size")int size) {
		String sql = "SELECT *	FROM tb_house WHERE 1=1";
		StringBuilder builder = new StringBuilder(sql);
		//判断条件不为空
		if (entity!=null) {
			
			if (entity.get("house_id")!=null) {
				builder.append(" AND house_id=#{entity.house_id}");
			}
			if (entity.get("house_no")!=null) {
				builder.append(" AND house_no = #{entity.house_no}");
			}
			if (entity.get("house_shape")!=null) {
				builder.append(" AND house_shape like  CONCAT('%',#{entity.house_shape},'%')");
			}
			if (entity.get("house_floor_id")!=null) {
				builder.append(" AND house_floor_id like  CONCAT('%',#{entity.house_floor_id},'%')");
			}
			if (entity.get("house_cell_id")!=null) {
				builder.append(" AND house_cell_id like  CONCAT('%',#{entity.house_cell_id},'%')");
			}
			if (entity.get("house_area")!=null) {
				builder.append(" AND house_area like  CONCAT('%',#{entity.house_area},'%')");
			}
			if (entity.get("owner_id")!=null) {
				builder.append(" AND owner_id=#{entity.owner_id}");
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
		String sql = "SELECT COUNT(*) FROM tb_house WHERE 1=1";
		StringBuilder builder = new StringBuilder(sql);
		//判断条件不为空
		if (condition!=null) {
			
			if (condition.get("house_id")!=null) {
				builder.append(" AND house_id=#{house_id}");
			}
			if (condition.get("house_no")!=null) {
				builder.append(" AND house_no like  CONCAT('%',#{house_no},'%')");
			}
			if (condition.get("house_shape")!=null) {
				builder.append(" AND house_shape like  CONCAT('%',#{house_shape},'%')");
			}
			if (condition.get("house_floor_id")!=null) {
				builder.append(" AND house_floor_id like  CONCAT('%',#{house_floor_id},'%')");
			}
			if (condition.get("house_cell_id")!=null) {
				builder.append(" AND house_cell_id like  CONCAT('%',#{house_cell_id},'%')");
			}
			if (condition.get("house_area")!=null) {
				builder.append(" AND house_area like  CONCAT('%',#{house_area},'%')");
			}
			if (condition.get("owner_id")!=null) {
				builder.append(" AND owner_id=#{owner_id}");
			}
		}
		return builder.toString();
	}
	
	public String editHouse(Map<String, Object> entity) {
		String sql = "UPDATE tb_house SET ";
		StringBuilder builder = new StringBuilder(sql);
		if (entity!=null) {
			
			if (entity.get("house_no")!=null) {
				builder.append(" house_no=#{house_no},");
			}
			if (entity.get("house_shape")!=null) {
				builder.append(" house_shape=#{house_shape},");
			}
			if (entity.get("house_floor_id")!=null) {
				builder.append(" house_floor_id=#{house_floor_id},");
			}
			if (entity.get("house_cell_id")!=null) {
				builder.append(" house_cell_id=#{house_cell_id},");
			}
			if (entity.get("house_area")!=null) {
				builder.append(" house_area=#{house_area},");
			}
			if (entity.get("owner_id")!=null) {
				builder.append(" owner_id=#{owner_id},");
			}
		}
		builder.delete(builder.length()-1, builder.length());
		builder.append(" WHERE house_id=#{house_id}");
		return builder.toString();
	}
	
	public String deleteById(Map<String, Object[]> ownIds) {
		Object[] ids = ownIds.get("array");
		String idStr = Arrays.toString(ids);
		String sql = "DELETE FROM tb_house WHERE house_id in ";
		StringBuilder builder = new StringBuilder(sql);
		builder.append(idStr);
		//将[]修改为()
		builder.setCharAt(builder.indexOf("["),'(');
		builder.setCharAt(builder.indexOf("]"),')');
		return builder.toString();
	}
}
