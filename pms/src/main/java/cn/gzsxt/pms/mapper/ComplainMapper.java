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

import cn.gzsxt.pms.mapper.provider.ComplainProvider;

@Mapper
public interface ComplainMapper {
	/**插入管理员记录
	 * @param entity 数据实体
	 * @return 影响的行号
	 */
	@Insert(value="INSERT INTO tb_complain (complain_desc, complain_people, complain_phone, complain_date, complain_rname,deal_status) VALUES ( #{complain_desc}, #{complain_people}, #{complain_phone},#{complain_date}, #{complain_rname}, #{deal_status})")
	@Options(useGeneratedKeys=true,keyProperty="complain_id",keyColumn="complain_id")
	int insert(Map<String, Object> entity);

	/**查询所有的户主，并分页
	 * @return
	 */
	@SelectProvider(type=ComplainProvider.class,method="findAllComplainToPage")
	List<Map<String, Object>> findAllComplainToPage(@Param("entity")Map<String, Object> entity, @Param("start")int start, @Param("size")int size);
	
	/**根据条件查询记录数
	 * @param condition
	 * @return
	 */
	@SelectProvider(type=ComplainProvider.class,method="quaryCountByCondition")
	int quaryCountByCondition(Map<String, Object> condition);
	
	/**根据id查询户主
	 * @param complainId
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_complain WHERE complain_id =#{complainId}")
	Map<String, Object> quaryById(Long complainId);

	/**更新户主信息
	 * @param entity
	 */
	@UpdateProvider(type=ComplainProvider.class,method="editComplain")
	void editComplain(Map<String, Object> entity);

	/**通过id删除户主
	 * @param ownId
	 */
	@DeleteProvider(type=ComplainProvider.class,method="deleteById")
	void deleteById(Object... ownId);
}
