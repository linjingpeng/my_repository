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

import cn.gzsxt.pms.mapper.provider.ModularProvider;

@Mapper
public interface ModularMapper {
	/**插入管理员记录
	 * @param entity 数据实体
	 * @return 影响的行号
	 */
	@Insert(value="INSERT INTO tb_modular (modular_name) VALUES ( #{modular_name})")
	@Options(useGeneratedKeys=true,keyProperty="modular_id",keyColumn="modular_id")
	int insert(Map<String, Object> entity);

	/**查询所有的模块，并分页
	 * @return
	 */
	@SelectProvider(type=ModularProvider.class,method="findAllModularToPage")
	List<Map<String, Object>> findAllModularToPage(@Param("entity")Map<String, Object> entity, @Param("start")int start, @Param("size")int size);
	
	/**根据条件查询记录数
	 * @param condition
	 * @return
	 */
	@SelectProvider(type=ModularProvider.class,method="quaryCountByCondition")
	int quaryCountByCondition(Map<String, Object> condition);
	
	/**根据id查询模块
	 * @param modularId
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_modular WHERE modular_id =#{modularId}")
	Map<String, Object> quaryById(Object modularId);

	/**更新模块信息
	 * @param entity
	 */
	@UpdateProvider(type=ModularProvider.class,method="editModular")
	void editModular(Map<String, Object> entity);

	/**通过id删除模块
	 * @param ownId
	 */
	@DeleteProvider(type=ModularProvider.class,method="deleteById")
	void deleteById(Object... ownId);
	
	/**查询所有模块
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_modular")
	List<Map<String, Object>> findAllModular();
}
