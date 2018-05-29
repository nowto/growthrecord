package cn.edu.zzti.dto;

public class InviteForm {
	public static final int OWN = 2;
	public static final int OTHER = 3;
	private String username;
	private Integer leading;
	public InviteForm() {
		// TODO Auto-generated constructor stub
	}
	public InviteForm(String username, Integer leading) {
		super();
		this.username = username;
		this.leading = leading;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getLeading() {
		return leading;
	}
	public void setLeading(Integer leading) {
		this.leading = leading;
	}
	@Override
	public String toString() {
		return "InviteForm [username=" + username + ", leading=" + leading
				+ "]";
	}
	
	
}
