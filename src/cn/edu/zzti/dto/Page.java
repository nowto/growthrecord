package cn.edu.zzti.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Page<T> implements Serializable {
    private static final long serialVersionUID = -4906400433425467744L;
 
    /**
     * 已知
     */
    //页码
    private int pageIndex = 1;
    //默认页大小
    public static int DEFAULT_PAGE_SIZE = 10;
    //页大小
    private int pageSize = DEFAULT_PAGE_SIZE;
    //条件被封装
    
    /**
     * 数据库求的
     */
    private List<T> data;
    private int totalItemsCount;
    //程序需要计算的 以方法提供
    //总页数
    public int getTotalPageCount(){
    	return getTotalItemsCount() % getPageSize() == 0 ? getTotalItemsCount() / getPageSize() : getTotalItemsCount() / getPageSize() + 1;
    }
    
    // 构造方法
    public Page() {
	}
    
    
	public Page(int pageIndex, List<T> data, int totalItemsCount) {
		super();
		this.pageIndex = pageIndex;
		this.data = data;
		this.totalItemsCount = totalItemsCount;
	}
	public Page(int pageIndex, int pageSize, List<T> data, int totalItemsCount) {
		super();
		this.pageIndex = pageIndex;
		this.data = data;
		this.totalItemsCount = totalItemsCount;
		this.pageSize = pageSize;
	}

	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}

	public int getTotalItemsCount() {
		return totalItemsCount;
	}

	public void setTotalItemsCount(int totalItemsCount) {
		this.totalItemsCount = totalItemsCount;
	}
}