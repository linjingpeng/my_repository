package cn.gzsxt.pms.utils;

/**ajax请求的结果
 * @author Administrator
 *
 */
public class AJAXResult {

	private boolean success;
	
	private Object data;

	
	
	public AJAXResult() {
		super();
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
