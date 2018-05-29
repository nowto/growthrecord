package cn.edu.zzti.dto;

import java.util.Date;

import cn.edu.zzti.entity.BodyRecord;

public class FigureRecordSearchBean {
	private Date startdate;
	private Date enddate;
	private String note;
	private Integer homeId;
	private int pageIndex = 1;
	private Integer kidId;
	private int pageSize = Page.DEFAULT_PAGE_SIZE;
	private int type;
	
	public int getLimitOne(){
		return (getPageIndex() - 1) * getPageSize();
	}
	public int getLimitTwo(){
		return getPageSize();
	}
	

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
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

	public Integer getKidId() {
		return kidId;
	}
	public void setKidId(Integer kidId) {
		this.kidId = kidId;
	}
	@Override
	public String toString() {
		return "BodyRecordSearchBean [startdate=" + startdate + ", enddate="
				+ enddate + ", pageIndex=" + pageIndex + ", kidId=" + kidId
				+ ", type=" + type + "]";
	}
	public Integer getHomeId() {
		return homeId;
	}
	public void setHomeId(Integer homeId) {
		this.homeId = homeId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
