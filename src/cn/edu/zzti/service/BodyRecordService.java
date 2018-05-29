package cn.edu.zzti.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.stereotype.Service;

import cn.edu.zzti.dto.BodyRecordSearchBean;
import cn.edu.zzti.dto.Page;
import cn.edu.zzti.dto.StudyRecordSearchBean;
import cn.edu.zzti.entity.BodyRecord;
import cn.edu.zzti.entity.Kid;
import cn.edu.zzti.entity.StudyRecord;
import cn.edu.zzti.entity.User;
import cn.edu.zzti.mapper.BodyRecordMapper;
import cn.edu.zzti.mapper.KidMapper;
import cn.edu.zzti.mapper.UserMapper;

@Service
public class BodyRecordService {
	@Resource
	private KidMapper kidMapper;
	@Resource
	private BodyRecordMapper bodyRecordMapper;
	@Resource
	private UserMapper userMapper;

	/**
	 * 添加
	 * @param bodyRecord
	 * @param username
	 * @return
	 */
	public boolean add(BodyRecord bodyRecord, String username) {
		// TODO Auto-generated method stub
		Integer kidid = bodyRecord.getKidId();
		if (kidid == null) {// 孩子id不存在，返回false
			return false;
		}
		Kid kid = kidMapper.select(kidid);
		User user = userMapper.selectByUsername(username);
		if (user.getHomeId() != kid.getHomeId())// 不是一家，返回false
			return false;

		bodyRecordMapper.insert(bodyRecord);
		return true;
	}
	
	//获得分页对象
	public Page<BodyRecord> search(BodyRecordSearchBean bodyrecordSB,
			String username) {
		Integer kidId = bodyrecordSB.getKidId();
		Kid kid = kidMapper.select(kidId);
		
		int userHomeId = userMapper.selectByUsername(username).getHomeId();
		if(kid != null){/*如果搜索条件中包含孩子信息，查看此孩子是否是登录用户的孩
		不是返回false，是的话，进一步获取教育阶段信息*/
			int kidHomeId = kid.getHomeId();
			if(kidHomeId != userHomeId)
				return new Page<>();
		}
		bodyrecordSB.setHomeId(userHomeId);
		List<BodyRecord> data = bodyRecordMapper.selectBySearch(bodyrecordSB);
		int count = bodyRecordMapper.selectBySearchCount(bodyrecordSB);
		Page<BodyRecord> page = new Page<>(bodyrecordSB.getPageIndex(), data, count);
		return page;
	}
	
	public List<BodyRecord> getChart(BodyRecordSearchBean sb, String username){
		Integer kidId = sb.getKidId();
		Kid kid = kidMapper.select(kidId);
		
		int userHomeId = userMapper.selectByUsername(username).getHomeId();
		
		if(kid != null){
			int kidHomeId = kid.getHomeId();
			if(kidHomeId != userHomeId){
				return new ArrayList<BodyRecord>();
			}
		}
		sb.setHomeId(userHomeId);
		return bodyRecordMapper.selectBySearchByChart(sb);
	}

	public KidMapper getKidMapper() {
		return kidMapper;
	}

	public void setKidMapper(KidMapper kidMapper) {
		this.kidMapper = kidMapper;
	}

	public BodyRecordMapper getBodyRecordMapper() {
		return bodyRecordMapper;
	}

	public void setBodyRecordMapper(BodyRecordMapper bodyRecordMapper) {
		this.bodyRecordMapper = bodyRecordMapper;
	}

	public UserMapper getUserMapper() {
		return userMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	public boolean removeAll(List<Integer> ids) {
		for (Integer id : ids) {
			bodyRecordMapper.deleteById(id);
		}
		return true;
	}


}
