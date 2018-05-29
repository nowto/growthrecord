package cn.edu.zzti.dto;


public class VaccinePlanSearchBean {
	
	private String name;
	private int pageIndex = 1;
	
	public int getLimitOne(){
		return (getPageIndex() - 1) * getPageSize();
	}
	public int getLimitTwo(){
		return getPageSize();
	}
	public int getPageSize(){
		return Page.DEFAULT_PAGE_SIZE;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
