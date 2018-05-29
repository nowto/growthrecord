package cn.edu.zzti.dto;

public class Mover {
	Integer newInt;
	Integer oldInt;
	
	public Mover() {
		// TODO Auto-generated constructor stub
	}

	public Mover(Integer newInt, Integer oldInt) {
		super();
		this.newInt = newInt;
		this.oldInt = oldInt;
	}

	public Integer getNewInt() {
		return newInt;
	}

	public void setNewInt(Integer newInt) {
		this.newInt = newInt;
	}

	public Integer getOldInt() {
		return oldInt;
	}

	public void setOldInt(Integer oldInt) {
		this.oldInt = oldInt;
	}
	
}
