package cn.gzsxt.pms.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import cn.gzsxt.pms.mapper.provider.DictionaryProvider;

@Mapper
public interface DictionaryMapper {
	/**
	 * 通过类型编码获得数据字典的值
	 * @param code
	 * @return
	 */
	 @Select(value="SELECT * FROM tb_dictionary WHERE dictionary_type_code=#{code}")
	 List<Map<String,Object>> findByTypeCode(Object code);
	 
	 /**
	   *  通过字典类型编码与值确定唯一的记录
	  * @param value
	  * @param code
	  * @return
	  */
	 @Select(value="SELECT * FROM tb_dictionary WHERE dictionary_type_code=#{code} AND dictionary_value=#{value}")
	 Map<String,Object> findByTypeCodeAndValue(@Param("value") Object value,@Param("code") Object code);
	/**插入管理员记录
	 * @param entity 数据实体
	 * @return 影响的行号
	 */
	@Insert(value="INSERT INTO tb_dictionary (dictionary_value, dictionary_type_code, dictionary_type_name, dictionary_name) VALUES ( #{dictionary_value}, #{dictionary_type_code}, #{dictionary_type_name},#{dictionary_name})")
	@Options(useGeneratedKeys=true,keyProperty="dictionary_id",keyColumn="dictionary_id")
	int insert(Map<String, Object> entity);

	/**查询所有的户主，并分页
	 * @return
	 */
	@SelectProvider(type=DictionaryProvider.class,method="findAllDictionaryToPage")
	List<Map<String, Object>> findAllDictionaryToPage(@Param("entity")Map<String, Object> entity, @Param("start")int start, @Param("size")int size);
	
	/**根据条件查询记录数
	 * @param condition
	 * @return
	 */
	@SelectProvider(type=DictionaryProvider.class,method="quaryCountByCondition")
	int quaryCountByCondition(Map<String, Object> condition);
	
	/**根据id查询户主
	 * @param dictionaryId
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_dictionary WHERE dictionary_id =#{dictionaryId}")
	Map<String, Object> quaryById(Long dictionaryId);

	/**更新户主信息
	 * @param entity
	 */
	@UpdateProvider(type=DictionaryProvider.class,method="editDictionary")
	void editDictionary(Map<String, Object> entity);

	/**通过id删除户主
	 * @param ownId
	 */
	@DeleteProvider(type=DictionaryProvider.class,method="deleteById")
	void deleteById(Object... ownId);
	
	/**
	 * 查询所有数据字典的数据
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_dictionary")
	List<Map<String, Object>> findAll();
}
