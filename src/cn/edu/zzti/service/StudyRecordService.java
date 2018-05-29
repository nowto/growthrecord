package cn.edu.zzti.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.zzti.dto.Page;
import cn.edu.zzti.dto.StudyRecordSearchBean;
import cn.edu.zzti.entity.Kid;
import cn.edu.zzti.entity.StudyRecord;
import cn.edu.zzti.entity.User;
import cn.edu.zzti.mapper.KidMapper;
import cn.edu.zzti.mapper.StudyRecordMapper;
import cn.edu.zzti.mapper.UserMapper;

/**
 * 学业记录服务类
 * @author nowto
 *
 */
@Service
@Transactional
public class StudyRecordService {
	@Resource
	private KidMapper kidMapper;
	@Resource
	private UserMapper userMapper;
	@Resource
	private StudyRecordMapper studyRecordMapper;

	/**
	 * 添加学业成长记录
	 * @param studyRecord
	 * @param username
	 * @return
	 */
	public boolean add(StudyRecord studyRecord, String username) {
		Integer kidid = studyRecord.getKidId();
		if(kidid == null){//孩子id不存在，返回false
			return false;
		}
		Kid kid = kidMapper.select(kidid);
		User user = userMapper.selectByUsername(username);
		if(user.getHomeId() != kid.getHomeId())//不是一家，返回false
			return false;
		
		studyRecordMapper.insert(studyRecord);
		return true;
	}

	/**
	 * 搜索孩子记录信息
	 * @param studyRecordSB 封装搜索条件的对象
	 * @return Pgge  分页对象
	 */
	public Page<StudyRecord> search(StudyRecordSearchBean studyRecordSB, String sessionUsername) {
		Integer kidId = studyRecordSB.getKidId();
		Kid kid = kidMapper.select(kidId);
		
		int userHomeId = userMapper.selectByUsername(sessionUsername).getHomeId();
		if(kid != null){/*如果搜索条件中包含孩子信息，查看此孩子是否是登录用户的孩
		不是返回false，是的话，进一步获取教育阶段信息*/
			int kidHomeId = kid.getHomeId();
			if(kidHomeId != userHomeId)
				return new Page<>();
		
			
			int eduLevel = studyRecordSB.getEduLevel();
			
			switch (eduLevel) {
			case StudyRecordSearchBean.YOU:
				studyRecordSB.setEduStartDate(kid.getYoustart());
				studyRecordSB.setEduEndDate(kid.getYouend());
				break;
			case StudyRecordSearchBean.XIAO:
				studyRecordSB.setEduStartDate(kid.getXiaostart());
				studyRecordSB.setEduEndDate(kid.getXiaoend());
				break;
			case StudyRecordSearchBean.CHU:
				studyRecordSB.setEduStartDate(kid.getChustart());
				studyRecordSB.setEduEndDate(kid.getChuend());
				break;
			case StudyRecordSearchBean.GAO:
				studyRecordSB.setEduStartDate(kid.getGaostart());
				studyRecordSB.setEduEndDate(kid.getGaoend());
				break;
			case StudyRecordSearchBean.DA:
				studyRecordSB.setEduStartDate(kid.getDastart());
				studyRecordSB.setEduEndDate(kid.getDaend());
				break;

			default:
				break;
			}
		}
		studyRecordSB.setHomeId(userHomeId);
		List<StudyRecord> data = studyRecordMapper.selectBySearch(studyRecordSB);
		int count = studyRecordMapper.selectBySearchCount(studyRecordSB);
		System.out.println("count:"+count);
		Page<StudyRecord> page = new Page<>(studyRecordSB.getPageIndex(), data, count);
		return page;
	}

	public boolean removeAll(List<Integer> ids) {
		for (Integer integer : ids) {
			studyRecordMapper.deleteById(integer);
		}
		return true;
	}
	
}
