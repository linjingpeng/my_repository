package cn.gzsxt.pms.mapper.provider;

import java.util.Arrays;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public class AdminProvider {

	public String editAdmin(Map<String, Object> entity) {
		String sql = "UPDATE tb_admin SET ";
		StringBuilder builder = new StringBuilder(sql);
		if (entity.get("admin_name") != null) {
			builder.append("admin_name=#{admin_name},");
		}
		if (entity.get("admin_account") != null) {
			builder.append("admin_account=#{admin_account},");
		}

		if (entity.get("admin_pwd") != null) {
			builder.append("admin_pwd=#{admin_pwd},");
		}

		if (entity.get("admin_status") != null) {
			builder.append("admin_status=#{admin_status},");
		}

		if (entity.get("role_id") != null) {
			builder.append("role_id=#{role_id},");
		}
		builder.delete(builder.length() - 1, builder.length());
		builder.append("WHERE admin_id=#{admin_id}");
		return builder.toString();
	}
	
	/**
	 * 模糊查询并且分页
	 * 注意事项：加上注解的对象，必须需要使用@Param绑定的key来获得值
	 * @param entity
	 * @param start
	 * @param size
	 * @return
	 */
	public String findAllAdminToPage(@Param("entity") Map<String, Object> entity ,@Param("start") int start,@Param("size") int size) {
		String sql="SELECT * FROM tb_admin WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		//1.判断参数不为null
		if(entity!=null) {
			if(entity.get("admin_id")!= null) {
				builder.append(" AND  admin_id=#{entity.admin_id}");
			}
			
			if(entity.get("admin_name")!= null) {
				builder.append(" AND  admin_name like CONCAT('%',#{entity.admin_name},'%')");
			}
			
			if (entity.get("admin_account") != null) {
				builder.append(" AND admin_account like CONCAT('%',#{entity.admin_account},'%')");
			}

			if (entity.get("admin_pwd") != null) {
				builder.append(" AND admin_pwd=#{entity.admin_pwd}");
			}

			if (entity.get("admin_status") != null) {
				builder.append(" AND admin_status=#{entity.admin_status}");
			}

			if (entity.get("role_id") != null) {
				builder.append(" AND role_id=#{entity.role_id}");
			}
		}
		builder.append(" LIMIT #{start},#{size}");
		
		return builder.toString();
		
	}
	
	/**
	 * 根据条件统计记录数
	 * @param entity
	 * @return
	 */
	public String quaryCountByCondition(Map<String, Object> entity) {
		String sql="SELECT count(*) FROM tb_admin WHERE 1=1 ";
		StringBuilder builder=new StringBuilder(sql);
		//1.判断参数不为null
		if(entity!=null) {
			if(entity.get("admin_id")!= null) {
				builder.append(" AND  admin_id=#{admin_id}");
			}
			
			if(entity.get("admin_name")!= null) {
				builder.append(" AND  admin_name like CONCAT('%',#{admin_name},'%')");
			}
			
			if (entity.get("admin_account") != null) {
				builder.append(" AND admin_account like CONCAT('%',#{admin_account},'%')");
			}

			if (entity.get("admin_pwd") != null) {
				builder.append(" AND admin_pwd=#{admin_pwd}");
			}

			if (entity.get("admin_status") != null) {
				builder.append(" AND admin_status=#{admin_status}");
			}

			if (entity.get("role_id") != null) {
				builder.append(" AND role_id=#{role_id}");
			}
		}
		
		return builder.toString();
	}
	

	/**
	 * 批量删除
	 * @param adminIdsMap
	 * @return
	 */
	public String deleteById(Map<String, Object[]> adminIdsMap) {
		Object[] adminIds = adminIdsMap.get("array");
		String idsStr = Arrays.toString(adminIds);
		String sql="DELETE FROM tb_admin WHERE admin_id in ";
		StringBuilder builder=new StringBuilder(sql);		
		builder.append(idsStr);
		//将[]修改为()
		builder.setCharAt(builder.indexOf("["), '(');
		builder.setCharAt(builder.indexOf("]"), ')');
	
		return builder.toString();
	}
}
