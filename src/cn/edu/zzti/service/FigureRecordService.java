package cn.edu.zzti.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.edu.zzti.dto.FigureRecordSearchBean;
import cn.edu.zzti.dto.Page;
import cn.edu.zzti.entity.FigureRecord;
import cn.edu.zzti.entity.Kid;
import cn.edu.zzti.entity.User;
import cn.edu.zzti.mapper.FigureRecordMapper;
import cn.edu.zzti.mapper.KidMapper;
import cn.edu.zzti.mapper.UserMapper;

@Service
public class FigureRecordService {
	@Resource
	private KidMapper kidMapper;
	@Resource
	private FigureRecordMapper figureRecordMapper;
	@Resource
	private UserMapper userMapper;

	/**
	 * 添加
	 * @param FigureRecord
	 * @param username
	 * @return
	 */
	public boolean add(FigureRecord FigureRecord, String username) {
		// TODO Auto-generated method stub
		Integer kidid = FigureRecord.getKidId();
		if (kidid == null) {// 孩子id不存在，返回false
			return false;
		}
		Kid kid = kidMapper.select(kidid);
		User user = userMapper.selectByUsername(username);
		if (user.getHomeId() != kid.getHomeId())// 不是一家，返回false
			return false;

		figureRecordMapper.insert(FigureRecord);
		return true;
	}
	
	//获得分页对象
	public Page<FigureRecord> search(FigureRecordSearchBean figurerecordSB,
			String username) {
		Integer kidId = figurerecordSB.getKidId();
		Kid kid = kidMapper.select(kidId);
		
		int userHomeId = userMapper.selectByUsername(username).getHomeId();
		if(kid != null){/*如果搜索条件中包含孩子信息，查看此孩子是否是登录用户的孩
		不是返回false，是的话，进一步获取教育阶段信息*/
			int kidHomeId = kid.getHomeId();
			if(kidHomeId != userHomeId)
				return new Page<>();
		}
		figurerecordSB.setHomeId(userHomeId);
		List<FigureRecord> data = figureRecordMapper.selectBySearch(figurerecordSB);
		int count = figureRecordMapper.selectBySearchCount(figurerecordSB);
		Page<FigureRecord> page = new Page<>(figurerecordSB.getPageIndex(), figurerecordSB.getPageSize(), data, count);
		return page;
	}
	
	public FigureRecord get(Integer id){
		return figureRecordMapper.selectWithKid(id);
	}

	public KidMapper getKidMapper() {
		return kidMapper;
	}

	public void setKidMapper(KidMapper kidMapper) {
		this.kidMapper = kidMapper;
	}

	public UserMapper getUserMapper() {
		return userMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	public FigureRecordMapper getFigureRecordMapper() {
		return figureRecordMapper;
	}

	public void setFigureRecordMapper(FigureRecordMapper figureRecordMapper) {
		this.figureRecordMapper = figureRecordMapper;
	}

	public void removeAll(List<Integer> list) {
		for (Integer id : list) {
			figureRecordMapper.deleteById(id);
		}
	}


}
