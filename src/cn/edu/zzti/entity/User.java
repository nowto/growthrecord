package cn.edu.zzti.entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 系统使用用户信息 的 数据库Bean
 * @author nowto
 *
 */
public class User {
	private Integer id;

	/**
	 * 用户名
	 */
	@NotBlank(message="用户名必填且非空")
	@Length(min=1, max=30, message="用户名必须1-30个字符")
	
	private String username;

	/**
	 * 密码
	 */
	@NotBlank(message="密码必填且非空")
	@Length(min=6, max=16, message="密码必须6-16个字符")
	private String password;

	/**
	 * 邮箱
	 */
	@Email
	private String email;

	/**
	 * 性别
	 */
	private String sex;

	/**
	 * 家庭ID
	 */
	private Integer homeId;

	/**
	 * 所属家庭
	 */
	private Home home;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex == null ? null : sex.trim();
	}

	public Integer getHomeId() {
		return homeId;
	}

	public void setHomeId(Integer homeId) {
		this.homeId = homeId;
	}

	public Home getHome() {
		return home;
	}

	public void setHome(Home home) {
		this.home = home;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", email=" + email + ", sex=" + sex + ", homeId="
				+ homeId + ", home=" + home + "]";
	}
}