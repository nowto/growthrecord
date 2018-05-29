package cn.edu.zzti.entity;

public class Invite {
    private Integer id;

    private Integer gongId;

    private Integer shouId;
    
    private Boolean agree;
    private Integer leading;
    private Boolean read;
    
    private User gong;
    private User shou;
    
    public Invite() {
		// TODO Auto-generated constructor stub
	}
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getGongId() {
		return gongId;
	}
	public void setGongId(Integer gongId) {
		this.gongId = gongId;
	}
	public Integer getShouId() {
		return shouId;
	}
	public void setShouId(Integer shouId) {
		this.shouId = shouId;
	}
	public User getGong() {
		return gong;
	}
	public void setGong(User gong) {
		this.gong = gong;
	}
	public User getShou() {
		return shou;
	}
	public void setShou(User shou) {
		this.shou = shou;
	}

	public Integer getLeading() {
		return leading;
	}

	public void setLeading(Integer leading) {
		this.leading = leading;
	}

	public Boolean getRead() {
		return read;
	}

	public void setRead(Boolean read) {
		this.read = read;
	}

	public Boolean getAgree() {
		return agree;
	}

	public void setAgree(Boolean agree) {
		this.agree = agree;
	}


}