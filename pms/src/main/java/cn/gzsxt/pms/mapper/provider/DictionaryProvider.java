package cn.gzsxt.pms.mapper.provider;

import java.util.Arrays;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public class DictionaryProvider {

	/**根据条件模糊查询，并分页
	 * 注意事项：加上注解的对象，必须需要使用@Param绑定的key来获得值
	 * @param entity 条件
	 * @param start 开始页码
	 * @param size 每页记录数
	 * @return
	 */
	public String findAllDictionaryToPage(@Param("entity")Map<String, Object> entity, @Param("start")int start, @Param("size")int size) {
		String sql = "SELECT *	FROM tb_dictionary WHERE 1=1";
		StringBuilder builder = new StringBuilder(sql);
		//判断条件不为空
		if (entity!=null) {
			
			if(entity.get("dictionary_id")!= null) {
				builder.append(" AND  dictionary_id=#{entity.dictionary_id}");
			}
			
			if(entity.get("dictionary_name")!= null) {
				builder.append(" AND  dictionary_name like CONCAT('%',#{entity.dictionary_name},'%')");
			}
			
			if(entity.get("dictionary_value")!= null) {
				builder.append(" AND  dictionary_value =#{entity.dictionary_value}");
			}
			
			if(entity.get("dictionary_type_code")!= null) {
				builder.append(" AND  dictionary_type_code=#{entity.dictionary_type_code}");
			}
			
			if(entity.get("dictionary_type_name")!= null) {
				builder.append(" AND  dictionary_type_name like CONCAT('%',#{entity.dictionary_type_name},'%')");
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
		String sql = "SELECT COUNT(*) FROM tb_dictionary WHERE 1=1";
		StringBuilder builder = new StringBuilder(sql);
		//判断条件不为空
		if (condition!=null) {
			if(condition.get("dictionary_id")!= null) {
				builder.append(" AND  dictionary_id=#{dictionary_id}");
			}
			
			if(condition.get("dictionary_name")!= null) {
				builder.append(" AND  dictionary_name like CONCAT('%',#{dictionary_name},'%')");
			}
			
			if(condition.get("dictionary_value")!= null) {
				builder.append(" AND  dictionary_value =#{dictionary_value}");
			}
			
			if(condition.get("dictionary_type_code")!= null) {
				builder.append(" AND  dictionary_type_code=#{dictionary_type_code}");
			}
			
			if(condition.get("dictionary_type_name")!= null) {
				builder.append(" AND  dictionary_type_name like CONCAT('%',#{dictionary_type_name},'%')");
			}
		}
		return builder.toString();
	}
	
	public String editDictionary(Map<String, Object> entity) {
		String sql = "UPDATE tb_dictionary SET ";
		StringBuilder builder = new StringBuilder(sql);
		if (entity!=null) {
			if(entity.get("dictionary_name")!= null) {
				builder.append("dictionary_name=#{dictionary_name},");
			}
			
			if(entity.get("dictionary_value")!= null) {
				builder.append("dictionary_value=#{dictionary_value},");
			}
			
			if(entity.get("dictionary_type_code")!= null) {
				builder.append("dictionary_type_code=#{dictionary_type_code},");
			}
			
			if(entity.get("dictionary_type_name")!= null) {
				builder.append("dictionary_type_name=#{dictionary_type_name},");
			}
		}
		builder.delete(builder.length()-1, builder.length());
		builder.append(" WHERE dictionary_id=#{dictionary_id}");
		return builder.toString();
	}
	
	public String deleteById(Map<String, Object[]> ownIds) {
		Object[] ids = ownIds.get("array");
		String idStr = Arrays.toString(ids);
		String sql = "DELETE FROM tb_dictionary WHERE dictionary_id in ";
		StringBuilder builder = new StringBuilder(sql);
		builder.append(idStr);
		//将[]修改为()
		builder.setCharAt(builder.indexOf("["),'(');
		builder.setCharAt(builder.indexOf("]"),')');
		return builder.toString();
	}
}
