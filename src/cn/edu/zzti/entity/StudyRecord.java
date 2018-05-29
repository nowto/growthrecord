package cn.edu.zzti.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 学业记录 数据库Bean
 * @author nowto
 *
 */
public class StudyRecord {
    private Integer id;

    /**
     * 赛事、活动
     */
    @NotBlank
    @Length(max=200)
    private String competition;

    /**
     * 什么样的一个成绩
     */
    @NotBlank
    @Length(max=40)
    private String grade;

    /**
     * 记录日期
     */
    private Date recordDate;

    /**
     * 所属孩子
     */
    private Integer kidId;
    
    private Kid kid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompetition() {
        return competition;
    }

    public void setCompetition(String competition) {
        this.competition = competition == null ? null : competition.trim();
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
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

	public Kid getKid() {
		return kid;
	}

	public void setKid(Kid kid) {
		this.kid = kid;
	}
}