package cn.gzsxt.pms.mapper.provider;

import java.util.Arrays;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public class ChargeProvider {

	/**根据条件模糊查询，并分页
	 * 注意事项：加上注解的对象，必须需要使用@Param绑定的key来获得值
	 * @param entity 条件
	 * @param start 开始页码
	 * @param size 每页记录数
	 * @return
	 */
	public String findAllChargeToPage(@Param("entity")Map<String, Object> entity, @Param("start")int start, @Param("size")int size) {
		String sql = "SELECT *	FROM tb_charge WHERE 1=1";
		StringBuilder builder = new StringBuilder(sql);
		//判断条件不为空
		if (entity!=null) {
			
			if (entity.get("charge_id")!=null) {
				builder.append(" AND charge_id=#{entity.charge_id}");
			}
			if (entity.get("house_id")!=null) {
				builder.append(" AND house_id=#{entity.house_id}");
			}
			if (entity.get("charge_mouth")!=null) {
				builder.append(" AND charge_mouth like  CONCAT('%',#{entity.charge_mouth},'%')");
			}
			if (entity.get("water_charge")!=null) {
				builder.append(" AND water_charge=#{entity.water_charge}");
			}
			if (entity.get("electric_charge")!=null) {
				builder.append(" AND electric_charge=#{entity.electric_charge}");
			}
			if (entity.get("gas_charge")!=null) {
				builder.append(" AND gas_charge=#{entity.gas_charge}");
			}
			if (entity.get("isPay")!=null) {
				builder.append(" AND isPay=#{entity.isPay}");
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
		String sql = "SELECT COUNT(*) FROM tb_charge WHERE 1=1";
		StringBuilder builder = new StringBuilder(sql);
		//判断条件不为空
		if (condition!=null) {
			
			if (condition.get("charge_id")!=null) {
				builder.append(" AND charge_id=#{charge_id}");
			}
			if (condition.get("house_id")!=null) {
				builder.append(" AND house_id= #{house_id}");
			}
			if (condition.get("charge_mouth")!=null) {
				builder.append(" AND charge_mouth like  CONCAT('%',#{charge_mouth},'%')");
			}
			if (condition.get("water_charge")!=null) {
				builder.append(" AND water_charge=#{water_charge}");
			}
			if (condition.get("electric_charge")!=null) {
				builder.append(" AND electric_charge=#{electric_charge}");
			}
			if (condition.get("gas_charge")!=null) {
				builder.append(" AND gas_charge=#{gas_charge}");
			}
			if (condition.get("isPay")!=null) {
				builder.append(" AND isPay=#{isPay}");
			}
		}
		return builder.toString();
	}
	
	public String editCharge(Map<String, Object> entity) {
		String sql = "UPDATE tb_charge SET ";
		StringBuilder builder = new StringBuilder(sql);
		if (entity!=null) {
			
			if (entity.get("house_id")!=null) {
				builder.append(" house_id=#{house_id},");
			}
			if (entity.get("charge_mouth")!=null) {
				builder.append(" charge_mouth=#{charge_mouth},");
			}
			if (entity.get("water_charge")!=null) {
				builder.append(" water_charge=#{water_charge},");
			}
			if (entity.get("electric_charge")!=null) {
				builder.append(" electric_charge=#{electric_charge},");
			}
			if (entity.get("gas_charge")!=null) {
				builder.append(" gas_charge=#{gas_charge},");
			}
			if (entity.get("isPay")!=null) {
				builder.append(" isPay=#{isPay},");
			}
		}
		builder.delete(builder.length()-1, builder.length());
		builder.append(" WHERE charge_id=#{charge_id}");
		return builder.toString();
	}
	
	public String deleteById(Map<String, Object[]> ownIds) {
		Object[] ids = ownIds.get("array");
		String idStr = Arrays.toString(ids);
		String sql = "DELETE FROM tb_charge WHERE charge_id in ";
		StringBuilder builder = new StringBuilder(sql);
		builder.append(idStr);
		//将[]修改为()
		builder.setCharAt(builder.indexOf("["),'(');
		builder.setCharAt(builder.indexOf("]"),')');
		return builder.toString();
	}
}
