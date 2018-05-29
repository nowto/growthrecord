package cn.edu.zzti.entity;

import java.util.Calendar;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 封装孩子信息的数据库Bean
 * 
 * @author nowto
 * 
 */
public class Kid {
	private Integer id;

	/**
	 * 姓名
	 */
	@NotBlank
	@Length(max = 10)
	private String name;

	/**
	 * 小名
	 */
	@Length(max = 10)
	private String nickname;

	/**
	 * 性别
	 */
	private String sex;

	/**
	 * 生日
	 */
	@NotNull
	private Date birthday;

	// 幼儿教育 起始，终止时间
	private Date youstart;
	private Date youend;
	// 小学教育 起始，终止时间
	private Date xiaostart;
	private Date xiaoend;
	// 初中教育 起始，终止时间
	private Date chustart;
	private Date chuend;
	// 高中教育 起始，终止时间
	private Date gaostart;
	private Date gaoend;
	// 高等教育 起始，终止时间
	private Date dastart;
	private Date daend;
	/**
	 * 所属家庭
	 */
	private Integer homeId;

	public Kid() {
		// TODO Auto-generated constructor stub
	}

	public Kid(String name, String nickname, String sex, Date birthday,
			Integer homeId) {
		super();
		this.name = name;
		this.nickname = nickname;
		this.sex = sex;
		this.birthday = birthday;
		this.homeId = homeId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getHomeId() {
		return homeId;
	}

	public void setHomeId(Integer homeId) {
		this.homeId = homeId;
	}

	public Date getYoustart() {
		return youstart;
	}

	public void setYoustart(Date youstart) {
		this.youstart = youstart;
	}

	public Date getYouend() {
		return youend;
	}

	public void setYouend(Date youend) {
		this.youend = youend;
	}

	public Date getXiaostart() {
		return xiaostart;
	}

	public void setXiaostart(Date xiaostart) {
		this.xiaostart = xiaostart;
	}

	public Date getXiaoend() {
		return xiaoend;
	}

	public void setXiaoend(Date xiaoend) {
		this.xiaoend = xiaoend;
	}

	public Date getChustart() {
		return chustart;
	}

	public void setChustart(Date chustart) {
		this.chustart = chustart;
	}

	public Date getChuend() {
		return chuend;
	}

	public void setChuend(Date chuend) {
		this.chuend = chuend;
	}

	public Date getGaostart() {
		return gaostart;
	}

	public void setGaostart(Date gaostart) {
		this.gaostart = gaostart;
	}

	public Date getGaoend() {
		return gaoend;
	}

	public void setGaoend(Date gaoend) {
		this.gaoend = gaoend;
	}

	public Date getDastart() {
		return dastart;
	}

	public void setDastart(Date dastart) {
		this.dastart = dastart;
	}

	public Date getDaend() {
		return daend;
	}

	public void setDaend(Date daend) {
		this.daend = daend;
	}

	public int getMonthAge() {
		Calendar now = Calendar.getInstance();
		Calendar birth = Calendar.getInstance();
		birth.setTime(birthday);

		int year = getAge();

		int month = Math.abs(now.get(Calendar.MONTH)
				- birth.get(Calendar.MONDAY));
		return 12 * year + month;
	}
	public int getAge(){
		Calendar now = Calendar.getInstance();
		Calendar birth = Calendar.getInstance();
		birth.setTime(birthday);
		return Math.abs(now.get(Calendar.YEAR) - birth.get(Calendar.YEAR));
	}

	@Override
	public String toString() {
		return "Kid [id=" + id + ", name=" + name + ", nickname=" + nickname
				+ ", sex=" + sex + ", birthday=" + birthday + ", youstart="
				+ youstart + ", youend=" + youend + ", xiaostart=" + xiaostart
				+ ", xiaoend=" + xiaoend + ", chustart=" + chustart
				+ ", chuend=" + chuend + ", gaostart=" + gaostart + ", gaoend="
				+ gaoend + ", dastart=" + dastart + ", daend=" + daend
				+ ", homeId=" + homeId + "]";
	}
}