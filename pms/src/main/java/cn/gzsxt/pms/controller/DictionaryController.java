package cn.gzsxt.pms.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.gzsxt.pms.service.DictionaryService;
import cn.gzsxt.pms.utils.AJAXResult;
import cn.gzsxt.pms.utils.Global;
import cn.gzsxt.pms.utils.Page;

@Controller
@RequestMapping("/dictionary")
public class DictionaryController {

	@Autowired
	private DictionaryService dictionaryService;
	
	@RequestMapping("/toDictionaryList")
	public String toDictionaryList(@RequestParam Map<String, Object> condition,Integer index,HttpServletRequest request) {
		
		if (index==null) {
			index = 0;
		}
		Page page = dictionaryService.findDictionaryToPage(condition, index, Global.PAGE_SIZE);
		request.setAttribute("page", page);
		return "dictionaryList";
	}
	//${pageContext.request.contextPath}/dictionary/toDictionaryAdd.do
	@RequestMapping("/toDictionaryAdd")
	public String toDictionaryAdd() {
	
		return "dictionaryAdd";
	}
	/**${pageContext.request.contextPath }/dictionary/addDictionary.do
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addDictionary")
	public Object addDictionary(@RequestParam Map<String, Object> entity) {
		System.out.println(entity);
		AJAXResult result = new AJAXResult();
		try {
			int count = dictionaryService.addDictionary(entity);
			if (count>0) {
				result.setSuccess(true);
			}else {
				result.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	/**跳转户主编辑页面
	 * ${pageContext.request.contextPath }/dictionary/toDictionaryEdit.do?dictionaryId=
	 * @return
	 */
	@RequestMapping("/toDictionaryEdit")
	public String toDictionaryEdit(Long dictionaryId,HttpServletRequest request) {
		
		Map<String, Object> dictionary = dictionaryService.findDictionaryById(dictionaryId);
		request.setAttribute("dictionary", dictionary);
		return "dictionaryEdit";
	}
	//${pageContext.request.contextPath }/dictionary/dictionaryEdit.do
	@ResponseBody
	@RequestMapping("/dictionaryEdit")
	public Object dictionaryEdit(@RequestParam Map<String, Object> entity) {
		System.out.println("entity"+entity);
		AJAXResult result = new AJAXResult();
		try {
			dictionaryService.dictionaryEdit(entity);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	//${pageContext.request.contextPath }/dictionary/deleteDictionary.do
	@ResponseBody
	@RequestMapping("/deleteDictionary")
	public Object deleteDictionary(Long dictionaryId) {
		AJAXResult result = new AJAXResult();
		try {
			dictionaryService.deleteById(dictionaryId);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
	/**${pageContext.request.contextPath }/dictionary/deleteDictionary.do  批量删除
	 * @param entity
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteDictionarys")
	public Object deleteDictionarys(Long[] dictionaryId) {
		AJAXResult result = new AJAXResult();
		System.out.println(dictionaryId);
		try {
			dictionaryService.deleteById((Object[])dictionaryId);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return result;
	}
}
