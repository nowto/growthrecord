package cn.edu.zzti.entity;

import java.util.Date;

/**
 * 疫苗注册安排表的javaBean对象
 * @author nowto
 *
 */
public class VaccinePlan {
    private Integer id;

    /**
     * 疫苗名称
     */
    private String name;

    /**
     * 疫苗描述
     */
    private String note;

    /**
     * 疫苗注射开始时间
     */
    private Date startDate;

    /**
     * 疫苗注射结束时间
     */
    private Date endDate;

    /**
     * 可注射的最小年龄
     */
    private Integer minAge;

    /**
     * 可注射的最大年龄
     */
    private Integer maxAge;

    /**
     * 注射地点
     */
    private String site;

    private int maxYear;
    private int maxMonth;
    private int minYear;
    private int minMonth;
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public int getMaxYear() {
		
		return maxYear;
	}

	public void setMaxYear(int maxYear) {
		this.maxYear = maxYear;
	}

	public int getMaxMonth() {
		return maxMonth;
	}

	public void setMaxMonth(int maxMonth) {
		this.maxMonth = maxMonth;
	}

	public int getMinYear() {
		return minYear;
	}

	public void setMinYear(int minYear) {
		this.minYear = minYear;
	}

	public int getMinMonth() {
		return minMonth;
	}

	public void setMinMonth(int minMonth) {
		this.minMonth = minMonth;
	}

	public Integer getMinAge() {
		if(minAge == null) {
			minAge = minYear * 12 + minMonth;
		}
		return minAge;
	}

	public void setMinAge(Integer minAge) {
		this.minAge = minAge;
	}

	public Integer getMaxAge() {
		if(maxAge == null){
			maxAge = maxYear * 12 + maxMonth;
		}
		return maxAge;
	}

	public void setMaxAge(Integer maxAge) {
		this.maxAge = maxAge;
	}

	@Override
	public String toString() {
		return "VaccinePlan [id=" + id + ", name=" + name + ", note=" + note
				+ ", startDate=" + startDate + ", endDate=" + endDate
				+ ", minAge=" + minAge + ", maxAge=" + maxAge + ", site="
				+ site + ", maxYear=" + maxYear + ", maxMonth=" + maxMonth
				+ ", minYear=" + minYear + ", minMonth=" + minMonth + "]";
	}
	
	
}