package cn.edu.zzti.dto;

import cn.edu.zzti.entity.User;

/**
 * 封装邀请结果的 Dto Bean
 * 
 * @author nowto
 * 
 */
public class InviteCheckResult {
	// 邀请码，已经和对方组为了家庭
	public static final int INVITE_HOME = 0;
	// 邀请码,已经邀请了对方，他（她）还未回复
	public static final int INVITE_NO_REPLY = 1;
	// 邀请码， 不能邀请自己
	public static final int INVITE_OWN = 2;
	// 邀请的用户不存在
	public static final int INVITE_NOT_EXIST = 3;
	// 邀请成功，发出了邀请
	public static final int INVITE_SENTED = 4;
	// 对方已经与别人组了家庭
	public static final int INVITE_TO_OTHER = 5;
	// 异性用户不能邀请
	public static final int INVITE_SAME_SEX = 6;
	// 有人已经邀请了你，全部处理后，才能执行邀请
	public static final int INVITE_HAVE_BYINVITED = 7;

	private int status;
	private User user;

	public int getStatus() {
		return status;
	}

	public InviteCheckResult(int status, User user) {
		super();
		this.status = status;
		this.user = user;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
