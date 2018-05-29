package cn.edu.zzti.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.zzti.entity.Kid;
import cn.edu.zzti.entity.User;
import cn.edu.zzti.mapper.KidMapper;
import cn.edu.zzti.mapper.UserMapper;

/**
 * Kid操作的业务层类
 * 
 * @author nowto
 * 
 */
@Service
@Transactional
public class KidService {
	@Resource
	private UserMapper userMapper;
	@Resource
	private KidMapper kidMapper;

	/**
	 * 
	 * 把孩子添加到用户名为toWho的用户家庭
	 * 
	 * @param kid
	 *            欲添加的孩子
	 * @param toWho
	 *            用户的名为toWho
	 * @return 操作结果 成功 true，失败false
	 */
	public boolean add(Kid kid, String toWho) {
		try {
			User to = userMapper.selectByUsername(toWho);
			kid.setHomeId(to.getHomeId());
			kidMapper.insert(kid);
			/*
			 * select @rownum:=@rownum+1 as rownum, xx.* from (select
			 * 
			 * @rownum:=0) r, xx;
			 */} catch (Exception e) {
			return false;
		}
		return true;
	}
	/**
	 * 更新孩子对象
	 * @param kid
	 */
	public boolean update(Kid kid) {
		if(kidMapper.select(kid.getId()) == null){
			return false;
		}
		kidMapper.update(kid);
		return true;
	}

	/**
	 * // 得到用户名为username的孩子列表
	 * 
	 * @param username
	 *            用户名
	 * @return List<Kid> 查找到孩子列表
	 */
	public List<Kid> get(String username) {
		User user = userMapper.selectByUsername(username);

		return kidMapper.selectByHomeId(user.getHomeId());
	}

	/**
	 * 
	 * 单条删除id为kidid的孩子 及其记录
	 * 
	 * @param id
	 * @return
	 */
	public boolean remove(Integer id) {
		Kid kid = kidMapper.select(id);
		// id为kidid的孩子不存在，
		if (kid == null) {
			return false;
		}

		// 引用 KID表id的都为级联删除casecade,不需调用其他Mapper
		if(kidMapper.delete(id) == 1){
			return true;
		}
		return true;
	}
	public Kid get(Integer id){
		return kidMapper.select(id);
	}

	// getter and setter
	public UserMapper getUserMapper() {
		return userMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	public KidMapper getKidMapper() {
		return kidMapper;
	}

	public void setKidMapper(KidMapper kidMapper) {
		this.kidMapper = kidMapper;
	}


}
