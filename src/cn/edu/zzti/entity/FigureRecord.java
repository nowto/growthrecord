package cn.edu.zzti.entity;

import java.util.Date;

public class FigureRecord {
	public static final int TYPE_VIDEO = 1;
	public static final int TYPE_PHOTO = 0;
	private Integer id;
	private Date recordDate;
	private String note;
	private String realFileName;
	private String sourceFileName;
	private int type;
	private Integer kidId;
	private Kid kid;
	
	public FigureRecord() {}
	public FigureRecord(Integer id, Date recordDate, String note,
			String realFileName, String sourceFileName, int type,
			Integer kidId, Kid kid) {
		super();
		this.id = id;
		this.recordDate = recordDate;
		this.note = note;
		this.realFileName = realFileName;
		this.sourceFileName = sourceFileName;
		this.type = type;
		this.kidId = kidId;
		this.kid = kid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getRealFileName() {
		return realFileName;
	}
	public void setRealFileName(String realFileName) {
		this.realFileName = realFileName;
	}
	public String getSourceFileName() {
		return sourceFileName;
	}
	public void setSourceFileName(String sourceFileName) {
		this.sourceFileName = sourceFileName;
	}
	public Integer getKidId() {
		return kidId;
	}
	public void setKidId(Integer kidId) {
		this.kidId = kidId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Kid getKid() {
		return kid;
	}
	public void setKid(Kid kid) {
		this.kid = kid;
	}
	@Override
	public String toString() {
		return "FigureRecord [id=" + id + ", recordDate=" + recordDate
				+ ", note=" + note + ", realFileName=" + realFileName
				+ ", sourceFileName=" + sourceFileName + ", type=" + type
				+ ", kidId=" + kidId + ", kid=" + kid + "]";
	}
	
}
