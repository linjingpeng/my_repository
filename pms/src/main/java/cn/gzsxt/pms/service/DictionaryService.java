package cn.gzsxt.pms.service;

import java.util.List;
import java.util.Map;

import cn.gzsxt.pms.utils.Page;

public interface DictionaryService {

	
	/**增加住房
	 * @param entity
	 * @return
	 */
	int addDictionary(Map<String, Object> entity);
	
	/**根据条件查询，分页返回数据
	 * @param condition 条件
	 * @param index 当前索引
	 * @param size  每页记录数
	 * @return 分页对象
	 */
	Page findDictionaryToPage(Map<String, Object> condition,int index, int size);
	
	/**根据id查询住房
	 * @param dictionaryId
	 * @return
	 */
	Map<String, Object> findDictionaryById(Long dictionaryId);

	/**更新住房信息
	 * @param entity
	 */
	void dictionaryEdit(Map<String, Object> entity);

	/**通过id删除住房信息
	 * @param ownId
	 */
	void deleteById(Object... ownId);
	/**
	 * 通过类型编码获得数据字典的值列表
	 * 
	 * @param typeCode
	 * @return
	 */
	List<Map<String, Object>> findDictionaryByTypeCode(Object typeCode);
	/**
	 * 查询所有的字典
	 * @return
	 */
	List<Map<String, Object>> findAllDictionary();
}
