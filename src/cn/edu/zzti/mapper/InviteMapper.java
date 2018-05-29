package cn.edu.zzti.mapper;

import java.util.List;

import cn.edu.zzti.entity.Invite;
import cn.edu.zzti.entity.User;

public interface InviteMapper {
	int insert(Invite invite);
	
	int updateShouRefuse(Integer id);
	int updateShouAgree(Integer id);
	int updateForRecover(Integer uid);

	int delete(Integer id);
	int deleteByGongOrShou(Integer id);

	Invite select(Integer id);

	Invite selectWithGS(Integer id);

	// Invite selectByGong(User user);
	// Invite selectByGongWithGs(User suer);

	// by 邀请用户
	List<Invite> selectRefuseByGong(User user);

	List<Invite> selectRefuseByGongWithGS(User user);

	List<Invite> selectNoReplyByGong(User user);

	List<Invite> selectNoReplyByGongWithGS(User user);

	List<Invite> selectAgreeByGong(User user);

	List<Invite> selectAgreeByGongWithGS(User user);
	
	List<Invite> selectReplyByGong(User user);
	
	List<Invite> selectReplyByGongWithGS(User user);

	// by 受邀用户
	List<Invite> selectAgreeByShou(User user);

	List<Invite> selectAgreeByShouWithGS(User user);

	List<Invite> selectNoReplyByShou(User user);

	List<Invite> selectNoReplyByShouWithGS(User user);

	int updateHavereadByGong(Integer id);

}