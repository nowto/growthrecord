package cn.edu.zzti.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.zzti.dto.InviteForm;
import cn.edu.zzti.dto.InviteCheckResult;
import cn.edu.zzti.dto.Mover;
import cn.edu.zzti.dto.UserDto;
import cn.edu.zzti.dto.VaccineNotify;
import cn.edu.zzti.entity.Home;
import cn.edu.zzti.entity.Invite;
import cn.edu.zzti.entity.Kid;
import cn.edu.zzti.entity.User;
import cn.edu.zzti.mapper.BodyRecordMapper;
import cn.edu.zzti.mapper.FigureRecordMapper;
import cn.edu.zzti.mapper.HomeMapper;
import cn.edu.zzti.mapper.InviteMapper;
import cn.edu.zzti.mapper.KidMapper;
import cn.edu.zzti.mapper.StudyRecordMapper;
import cn.edu.zzti.mapper.UserMapper;

/**
 * User操作的服务类
 * 
 * @author nowto
 * 
 */
@Service
@Transactional
public class UserService {

	/**
	 * 
	 */
	// public static final int __ = 2;

	@Resource
	private UserMapper userMapper;
	@Resource
	private InviteMapper inviteMapper;
	@Resource
	private HomeMapper homeMapper;

	@Resource
	private KidMapper kidMapper;
	@Resource
	BodyRecordMapper bodyRecordMapper;
	@Resource
	StudyRecordMapper studyRecordMapper;
	@Resource
	FigureRecordMapper figureRecordMapper;

	public UserService() {
	}

	/**
	 * 用户注册
	 * 
	 * @param user
	 *            , 欲注册的用户
	 * @return 结果
	 */
	public boolean regist(UserDto user) {
		User regUser = (User) user;

		// 用户名冲突
		if (find(regUser.getUsername())) {
			return false;
		}

		// 默认每个用户注册时添加一个家
		Home home = regUser.getHome();
		int homer = homeMapper.insert(home);

		// 添加家后，把家的id赋给user
		regUser.setHomeId(home.getId());

		int userr = userMapper.insert(regUser);
		if (homer == 1 && userr == 1) {

			return true;
		}
		return false;

	}

	/**
	 * 用户登录
	 * 
	 * @param dto
	 * @return User 如果用户名密码验证无误， 返回这个User对象
	 */
	public User login(UserDto dto) {
		List<User> users = userMapper.selectByAll(dto);
		return users.size() == 0 ? null : users.get(0);
	}

	/**
	 * 用户注销
	 * 
	 * @param user
	 * @return
	 */
	public boolean delete(User user) {
		user = get(user.getId());
		user = get(user.getUsername()) == null ? user : get(user.getUsername());

		// 如果是单亲用户 删除 家 和孩子
		if (userMapper.selectByHomeId(user.getHomeId()).size() == 0) {
			// 删除孩子
			kidMapper.deleteByHomeId(user.getHomeId());
			// 删除他的家 会casecade删除自己
			homeMapper.delete(user.getHomeId());
		} else {// 如果是双亲用户，使对方恢复单身
			inviteMapper.deleteByGongOrShou(user.getId());
			// 删掉自己
			userMapper.delete(user.getId());
		}

		return true;

	}

	/**
	 * 获取用户名为username的用户
	 * 
	 * @param username
	 * @return 没有返回null
	 */
	public User get(String username) {
		User user = userMapper.selectByUsernameWithHome(username);

		return user;
	}

	/**
	 * 用户名为username的用户是否存在
	 * 
	 * @param username
	 * @return
	 */
	public boolean find(String username) {
		User user = get(username);

		return user != null;
	}

	/**
	 * 通过id获取User
	 * 
	 * @param id
	 * @return
	 */
	public User get(Integer id) {
		User user = userMapper.select(id);

		return user;
	}

	/**
	 * 通过id判断是否存在
	 * 
	 * @param id
	 * @return
	 */
	public boolean find(Integer id) {
		User user = get(id);

		return user != null;
	}

	/**
	 * 更新用户对象
	 * 
	 * @param user
	 */
	public boolean update(User user) {
		// 数据库有此记录
		if (find(user.getUsername())) {
			userMapper.update(user);
			if (user.getHome() != null)
				homeMapper.update(user.getHome());
			return true;
		}
		// 没有
		return false;
	}

	public UserMapper getUserMapper() {
		return userMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	public InviteMapper getInviteMapper() {
		return inviteMapper;
	}

	public void setInviteMapper(InviteMapper inviteMapper) {
		this.inviteMapper = inviteMapper;
	}

	public HomeMapper getHomeMapper() {
		return homeMapper;
	}

	public void setHomeMapper(HomeMapper homeMapper) {
		this.homeMapper = homeMapper;
	}

	public KidMapper getKidMapper() {
		return kidMapper;
	}

	public void setKidMapper(KidMapper kidMapper) {
		this.kidMapper = kidMapper;
	}

	/**
	 * 修改密码
	 * 
	 * @param user
	 *            封装了用户名，原密码，新密码，重复新密码的 user对象
	 * @return true 修改成功 false 修改失败
	 */
	public boolean changepwd(UserDto user) {
		String prePassword = user.getPrePassword();
		String password = user.getPassword();
		String confirmPassword = user.getConfirmPassword();

		if (password == null || password.equals(""))
			return false;
		if (confirmPassword == null || password.equals(""))
			return false;
		if (!password.equals(confirmPassword))
			return false;

		User db_user = userMapper.selectByUsername(user.getUsername());
		String db_prePassword = db_user.getPassword();

		if (!db_prePassword.equals(prePassword))
			return false;

		user.setId(db_user.getId());
		userMapper.update(user);
		return true;
	}

	/**
	 * 只能邀请一个异性的好友组成家庭 邀请用户名为username的用户，组成家庭
	 * 
	 * @param username
	 * @return
	 */

	public InviteCheckResult invite(InviteForm inviteForm, String session_username) {
		String username = inviteForm.getUsername();
		User session_user = get(session_username);
		// 自己已和某人组成家庭
		User lover = getLover(session_user);
		if(lover != null){
			return new InviteCheckResult(InviteCheckResult.INVITE_HOME, lover);
		}

		// 您正在邀请某人，对方还没同意 已测试
		List<Invite> list = inviteMapper
				.selectNoReplyByGongWithGS(session_user);
		if (list != null && list.size() > 0) {
			Invite invite = list.get(0);
			return new InviteCheckResult(InviteCheckResult.INVITE_NO_REPLY,
					invite.getShou());
		}

		// 有人已经邀请了你，请处理, 被人邀请
		if (getByInviteNotifyNum(username) > 0) {
			return new InviteCheckResult(InviteCheckResult.INVITE_HAVE_BYINVITED, null);
		}

		// 邀请的用户不存在 已测试
		if (username == null || username.equals("") || !find(username)) {
			return new InviteCheckResult(InviteCheckResult.INVITE_NOT_EXIST, null);
		}

		// 邀请的用户是自己 已测试
		if (username.equals(session_username)) {
			return new InviteCheckResult(InviteCheckResult.INVITE_OWN, session_user);
		}

		// 对方已经与别人组为了家庭
		User shou = get(username);
		lover = getLover(shou);
		if (lover != null) {
			return new InviteCheckResult(InviteCheckResult.INVITE_TO_OTHER, lover);
		}

		// 同性不能邀请 已测试
		if (session_user.getSex().equals(shou.getSex())) {
			return new InviteCheckResult(InviteCheckResult.INVITE_SAME_SEX, shou);
		}

		// 可以开始邀请了，呵呵
		Invite invite = new Invite();
		invite.setGongId(session_user.getId());
		invite.setShouId(shou.getId());

		if (inviteForm.getLeading().intValue() == inviteForm.OWN) {
			invite.setLeading(session_user.getId());
		} else if (inviteForm.getLeading().intValue() == inviteForm.OTHER) {
			invite.setLeading(shou.getId());
		}
		inviteMapper.insert(invite);

		return new InviteCheckResult(InviteCheckResult.INVITE_SENTED, shou);
	}

	public User getLover(String username){
		return getLover(get(username));
	}
	public User getLover(User user) {
		if (user != null) {
			List<User> list = userMapper.selectByHomeId(user.getHomeId());
			
			if (list != null && list.size() >= 2) {
				for (User u : list) {
					if(u.getId() != user.getId()){
						return u;
					}
				}
			}
		}
		return  null;
	}

	/**
	 * 获取被邀请通知
	 * 
	 * @param username
	 */
	public int getByInviteNotifyNum(String username) {

		List<Invite> invites = inviteMapper
				.selectNoReplyByShouWithGS(get(username));
		if (invites != null)
			return invites.size();
		return 0;
	}

	/**
	 * 获取邀请
	 * 
	 * @param username
	 *            登录用户名
	 */

	public List<Invite> getByInviteNotify(String username) {
		List<Invite> list = null;
		list = inviteMapper.selectNoReplyByShouWithGS(get(username));

		return list;
	}

	/**
	 * 拒绝邀请方的组为家庭的请求
	 * 
	 * @param id
	 * @return
	 */
	public boolean refuse(Integer id) {
		// TODO Auto-generated method stub
		int result = inviteMapper.updateShouRefuse(id);

		if (result == 1) {
			return true;
		}
		return false;
	}

	/**
	 * 获取邀请有回应（包含"拒绝"或"同意"]），且未读的, 数量
	 * 
	 * @param username
	 * @return
	 */
	public int getInviteNotifyNum(String username) {
		List<Invite> list = inviteMapper.selectReplyByGong(get(username));
		if (list != null) {
			return list.size();
		}
		return 0;
	}

	/**
	 * 邀请方获取被邀请方的回复，包括 拒绝 或 同意
	 * 
	 * @param username
	 *            用户名 登录的
	 * @return List<Invite> 邀请有回复的列表
	 */
	public List<Invite> getInviteNotify(String username) {
		// TODO Auto-generated method stub
		List<Invite> list = inviteMapper.selectReplyByGongWithGS(get(username));
		return list;
	}

	/**
	 * 标记id为id的Invite为已读
	 * 
	 * @param id
	 */
	public void havereadInvite(Integer id) {
		inviteMapper.delete(id);
	}

	/**
	 * 合并两个用户为家庭
	 * 
	 * @param id
	 * @return
	 */
	public boolean together(Integer id) {
		Invite invite = inviteMapper.selectWithGS(id);
		if (invite == null)
			return false;
		User gong = invite.getGong();
		User shou = invite.getShou();
		User leader = null;
		User follower = null;
		Integer leading = invite.getLeading();

		boolean isMergeChildAndRecord = leading != null;
		// 默认以邀请方为主导
		leading = leading == null ? gong.getId() : leading;

		if (leading == gong.getId()) {
			leader = gong;
			follower = shou;
		} else {
			leader = shou;
			follower = gong;
		}

		Integer oldHomeId = follower.getHomeId();
		Integer newHomeId = leader.getHomeId();
		follower.setHomeId(leader.getHomeId());
		userMapper.update(follower);

		// select * from kid where id in (select id from kid where (name, sex,
		// birthday) in (select distinct name, sex, birthday from kid) and
		// homeId != 7);
		// 是否合并孩子及记录
		if (isMergeChildAndRecord) {
			List<Kid> list = kidMapper.selectTemplet(newHomeId);

			for (Kid templet : list) {
				List<Kid> fs = kidMapper.selectFollower(templet);
				for (Kid f : fs) {
					Integer fId = f.getId();
					Integer lId = templet.getId();
					Mover mover = new Mover(lId, fId);
					studyRecordMapper.updateKidId(mover);
					bodyRecordMapper.updateKidId(mover);
					figureRecordMapper.updateKidId(mover);
					kidMapper.delete(fId);
				}
			}
		}

		List<Kid> kids = kidMapper.selectByHomeId(oldHomeId);
		for (Kid kid : kids) {
			kid.setHomeId(leader.getHomeId());
			kidMapper.update(kid);
		}
		homeMapper.delete(oldHomeId);
		inviteMapper.updateShouAgree(id);
		return true;
	}

	public BodyRecordMapper getBodyRecordMapper() {
		return bodyRecordMapper;
	}

	public void setBodyRecordMapper(BodyRecordMapper bodyRecordMapper) {
		this.bodyRecordMapper = bodyRecordMapper;
	}

	public StudyRecordMapper getStudyRecordMapper() {
		return studyRecordMapper;
	}

	public void setStudyRecordMapper(StudyRecordMapper studyRecordMapper) {
		this.studyRecordMapper = studyRecordMapper;
	}

	public int getVaccineNotifyNum(String username) {
		User user = userMapper.selectByUsername(username);
		if(user == null)
			return 0;
		return kidMapper.selectVaccineNotifyNum(user.getHomeId());
	}
	public List<VaccineNotify> getVaccineNotify(String username) {
		User user = userMapper.selectByUsername(username);
		return kidMapper.selectVaccineNotify(user.getHomeId());
	}

	public FigureRecordMapper getFigureRecordMapper() {
		return figureRecordMapper;
	}

	public void setFigureRecordMapper(FigureRecordMapper figureRecordMapper) {
		this.figureRecordMapper = figureRecordMapper;
	}
}
