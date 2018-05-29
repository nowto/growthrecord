package cn.edu.zzti.dto;
/**
 * 用于封装处理结果，并跳转到某一页面的 转发 Bean
 * @author nowto
 *
 */
public class ForwardBean {
	/**
	 * 转发到哪一视图
	 */
	private String view;
	/**
	 * 这个视图的url
	 */
	private String target;
	/**
	 * 发生了什么事件导致这一结果
	 */
	private String message;
	
	public ForwardBean() {
		// TODO Auto-generated constructor stub
	}

	public ForwardBean(String view, String target, String message) {
		super();
		this.view = view;
		this.target = target;
		this.message = message;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
