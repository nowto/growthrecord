package cn.edu.zzti.dto;

import java.util.Date;

public class StudyRecordSearchBean {
	public static final int YOU = 1;
	public static final int XIAO = 2;
	public static final int CHU = 3;
	public static final int GAO = 4;
	public static final int DA = 5;
	
	private Integer homeId;
	private String competition;
	private String grade;
	private Date startdate;
	private Date enddate;
	private int pageIndex = 1;
	private Integer kidId;
	private Integer eduLevel;
	
	
	private Date eduStartDate;
	private Date eduEndDate;
	
	public int getLimitOne(){
		return (getPageIndex() - 1) * getPageSize();
	}
	public int getLimitTwo(){
		return getPageSize();
	}
	public int getPageSize(){
		return Page.DEFAULT_PAGE_SIZE;
	}
	
	public void setCompetition(String competition) {
		this.competition = competition;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public String getCompetition() {
		return competition;
	}

	public String getGrade() {
		return grade;
	}

	public Date getStartdate() {
		return startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		if (pageIndex < 1)
			this.pageIndex = 1;
		else
			this.pageIndex = pageIndex;
	}

	@Override
	public String toString() {
		return "StudyRecordSearchBean [competition=" + competition + ", grade="
				+ grade + ", startdate=" + startdate + ", enddate=" + enddate
				+ ", pageIndex=" + pageIndex + "]";
	}
	public Integer getKidId() {
		return kidId;
	}
	public void setKidId(Integer kidId) {
		this.kidId = kidId;
	}
	public Integer getEduLevel() {
		return eduLevel;
	}
	public void setEduLevel(Integer eduLevel) {
		this.eduLevel = eduLevel;
	}
	public Date getEduStartDate() {
		return eduStartDate;
	}
	public void setEduStartDate(Date eduStartDate) {
		this.eduStartDate = eduStartDate;
	}
	public Date getEduEndDate() {
		return eduEndDate;
	}
	public void setEduEndDate(Date eduEndDate) {
		this.eduEndDate = eduEndDate;
	}
	public Integer getHomeId() {
		return homeId;
	}
	public void setHomeId(Integer homeId) {
		this.homeId = homeId;
	}
}
