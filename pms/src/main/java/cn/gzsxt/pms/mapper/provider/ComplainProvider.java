package cn.gzsxt.pms.mapper.provider;

import java.util.Arrays;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public class ComplainProvider {

	/**根据条件模糊查询，并分页
	 * 注意事项：加上注解的对象，必须需要使用@Param绑定的key来获得值
	 * @param entity 条件
	 * @param start 开始页码
	 * @param size 每页记录数
	 * @return
	 */
	public String findAllComplainToPage(@Param("entity")Map<String, Object> entity, @Param("start")int start, @Param("size")int size) {
		String sql = "SELECT *	FROM tb_complain WHERE 1=1";
		StringBuilder builder = new StringBuilder(sql);
		//判断条件不为空
		if (entity!=null) {
			
			if (entity.get("complain_id")!=null) {
				builder.append(" AND complain_id=#{entity.complain_id}");
			}
			if (entity.get("complain_desc")!=null) {
				builder.append(" AND complain_desc like  CONCAT('%',#{entity.complain_desc},'%')");
			}
			if (entity.get("complain_people")!=null) {
				builder.append(" AND complain_people like  CONCAT('%',#{entity.complain_people},'%')");
			}
			if (entity.get("complain_phone")!=null) {
				builder.append(" AND complain_phone like  CONCAT('%',#{entity.complain_phone},'%')");
			}
			if (entity.get("complain_date")!=null) {
				builder.append(" AND complain_date like  CONCAT('%',#{entity.complain_date},'%')");
			}
			if (entity.get("complain_rname")!=null) {
				builder.append(" AND complain_rname like  CONCAT('%',#{entity.complain_rname},'%')");
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
		String sql = "SELECT COUNT(*) FROM tb_complain WHERE 1=1";
		StringBuilder builder = new StringBuilder(sql);
		//判断条件不为空
		if (condition!=null) {
			
			if (condition.get("complain_id")!=null) {
				builder.append(" AND complain_id=#{complain_id}");
			}
			if (condition.get("complain_desc")!=null) {
				builder.append(" AND complain_desc like  CONCAT('%',#{complain_desc},'%')");
			}
			if (condition.get("complain_people")!=null) {
				builder.append(" AND complain_people like  CONCAT('%',#{complain_people},'%')");
			}
			if (condition.get("complain_phone")!=null) {
				builder.append(" AND complain_phone like  CONCAT('%',#{complain_phone},'%')");
			}
			if (condition.get("complain_date")!=null) {
				builder.append(" AND complain_date like  CONCAT('%',#{complain_date},'%')");
			}
			if (condition.get("complain_rname")!=null) {
				builder.append(" AND complain_rname like  CONCAT('%',#{complain_rname},'%')");
			}
		}
		return builder.toString();
	}
	
	public String editComplain(Map<String, Object> entity) {
		String sql = "UPDATE tb_complain SET ";
		StringBuilder builder = new StringBuilder(sql);
		if (entity!=null) {
			
			if (entity.get("complain_desc")!=null) {
				builder.append(" complain_desc=#{complain_desc},");
			}
			if (entity.get("complain_people")!=null) {
				builder.append(" complain_people=#{complain_people},");
			}
			if (entity.get("complain_phone")!=null) {
				builder.append(" complain_phone=#{complain_phone},");
			}
			if (entity.get("complain_date")!=null) {
				builder.append(" complain_date=#{complain_date},");
			}
			if (entity.get("complain_rname")!=null) {
				builder.append(" complain_rname=#{complain_rname},");
			}
			if (entity.get("deal_status")!=null) {
				builder.append(" deal_status=#{deal_status},");
			}
		}
		builder.delete(builder.length()-1, builder.length());
		builder.append(" WHERE complain_id=#{complain_id}");
		return builder.toString();
	}
	
	public String deleteById(Map<String, Object[]> ownIds) {
		Object[] ids = ownIds.get("array");
		String idStr = Arrays.toString(ids);
		String sql = "DELETE FROM tb_complain WHERE complain_id in ";
		StringBuilder builder = new StringBuilder(sql);
		builder.append(idStr);
		//将[]修改为()
		builder.setCharAt(builder.indexOf("["),'(');
		builder.setCharAt(builder.indexOf("]"),')');
		return builder.toString();
	}
}
