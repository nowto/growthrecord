package cn.edu.zzti.entity;

/**
 * 封装家庭的数据库Bean 
 * @author nowto
 *
 */
public class Home {
    private Integer id;

    /**
     * 家庭名称
     */
    private String name;

    /**
     * 家庭描述
     */
    private String note;
    
    public Home() {
	}

    public Home(String name, String note) {
		super();
		this.name = name;
		this.note = note;
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
        this.name = name == null ? null : name.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}