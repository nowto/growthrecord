package cn.edu.zzti.dto;

import cn.edu.zzti.entity.User;
/**
 * 用户封装 表单提交的User对象的Bean， 继承自 数据库UserBean
 * @author nowto
 *
 */
public class UserDto extends User {
	/**
	 * 原密码
	 */
	
	private String prePassword;
	
	/**
	 * 确认密码
	 */
	private String confirmPassword;
	/**
	 * 原密码
	 */
	
	public UserDto() {
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getPrePassword() {
		return prePassword;
	}

	public void setPrePassword(String prePassword) {
		this.prePassword = prePassword;
	}

}
