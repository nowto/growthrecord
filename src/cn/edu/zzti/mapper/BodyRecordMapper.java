package cn.edu.zzti.mapper;

import java.util.List;

import cn.edu.zzti.entity.BodyRecord;
import cn.edu.zzti.dto.BodyRecordSearchBean;
import cn.edu.zzti.dto.Mover;
import cn.edu.zzti.dto.StudyRecordSearchBean;

public interface BodyRecordMapper {
	int insert(BodyRecord bodyRecord);
	List<BodyRecord> selectBySearch(BodyRecordSearchBean bodyRecordSearchBean);
	List<BodyRecord> selectBySearchByChart(BodyRecordSearchBean bodyRecordSearchBean);
	int selectBySearchCount(BodyRecordSearchBean bodyRecordSearchBean);
	int deleteById(Integer id);
	int updateKidId(Mover mover);
}
