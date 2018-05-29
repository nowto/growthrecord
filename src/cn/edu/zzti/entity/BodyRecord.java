package cn.edu.zzti.entity;

import java.util.Date;
/**
 * 用户封装生理记录的 数据库Bean
 * @author nowto
 *
 */
public class BodyRecord {
	public static final int TYPE_HEIGHT = 0;
	public static final int TYPE_WEIGHT = 1;
	public static final int TYPE_FIRST = 2;
	public static final int TYPE_OTHER = 3;
	
	private Kid kid;
    private Integer id;

    /**
     * 类型
     */
    private int type;

    /**
     * 详情
     */
    private String note;

    /**
     * 记录时间
     */
    private Date recordDate;

    /**
     * 所属孩子
     */
    private Integer kidId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
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
}