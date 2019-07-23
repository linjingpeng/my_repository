package cn.gzsxt.pms.utils;

import java.util.List;
import java.util.Map;

public class Page {
	//当前索引
	private int index;
	//每页记录数
	private int pageSize;
	//总记录数
	private int count;
	//数据
	private List<Map<String, Object>> data;
	//总页数
	private int pageNum;
	
	
	public Page() {
		super();
	}


	public Page(int index, int pageSize, int count, List<Map<String, Object>> data) {
		super();
		this.index = index;
		this.pageSize = pageSize;
		this.count = count;
		this.data = data;
		//计算总页数
		if (count%pageSize==0) {
			this.pageNum=count/pageSize;
		} else {
			this.pageNum=count/pageSize+1;
		}
	}


	public int getIndex() {
		return index;
	}


	public void setIndex(int index) {
		this.index = index;
	}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	public List<Map<String, Object>> getData() {
		return data;
	}


	public void setData(List<Map<String, Object>> data) {
		this.data = data;
	}


	public int getPageNum() {
		return pageNum;
	}


	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	/**判断是否有上一页
	 * @return
	 */
	public boolean isPrevious() {
		if (index>0) {
			return true;
		}
		return false;
	}
	
	/**判断是否有下一页
	 * @return
	 */
	public boolean isNext() {
		//因为索引index是从0开始的，而pageNum是从1开始的
		if (index<(pageNum-1)) {
			return true;
		}
		return false;
	}
}
