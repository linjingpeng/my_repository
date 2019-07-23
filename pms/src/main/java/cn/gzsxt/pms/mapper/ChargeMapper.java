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

import cn.gzsxt.pms.mapper.provider.ChargeProvider;

@Mapper
public interface ChargeMapper {
	/**插入管理员记录
	 * @param entity 数据实体
	 * @return 影响的行号
	 */
	@Insert(value="INSERT INTO tb_charge (house_id, charge_mouth, water_charge, electric_charge, gas_charge,isPay) VALUES ( #{house_id}, #{charge_mouth}, #{water_charge},#{electric_charge}, #{gas_charge}, #{isPay})")
	@Options(useGeneratedKeys=true,keyProperty="charge_id",keyColumn="charge_id")
	int insert(Map<String, Object> entity);

	/**查询所有的户主，并分页
	 * @return
	 */
	@SelectProvider(type=ChargeProvider.class,method="findAllChargeToPage")
	List<Map<String, Object>> findAllChargeToPage(@Param("entity")Map<String, Object> entity, @Param("start")int start, @Param("size")int size);
	
	/**根据条件查询记录数
	 * @param condition
	 * @return
	 */
	@SelectProvider(type=ChargeProvider.class,method="quaryCountByCondition")
	int quaryCountByCondition(Map<String, Object> condition);
	
	/**根据id查询户主
	 * @param chargeId
	 * @return
	 */
	@Select(value="SELECT *	FROM tb_charge WHERE charge_id =#{chargeId}")
	Map<String, Object> quaryById(Long chargeId);

	/**更新户主信息
	 * @param entity
	 */
	@UpdateProvider(type=ChargeProvider.class,method="editCharge")
	void editCharge(Map<String, Object> entity);

	/**通过id删除户主
	 * @param ownId
	 */
	@DeleteProvider(type=ChargeProvider.class,method="deleteById")
	void deleteById(Object... ownId);
}
