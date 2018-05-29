package cn.edu.zzti.mapper;

import java.util.List;

import cn.edu.zzti.dto.Mover;
import cn.edu.zzti.dto.StudyRecordSearchBean;
import cn.edu.zzti.entity.StudyRecord;

public interface StudyRecordMapper {

	int insert(StudyRecord studyRecord);
	List<StudyRecord> selectBySearch(StudyRecordSearchBean studyRecordSearchBean);
	int selectBySearchCount(StudyRecordSearchBean studyRecordSearchBean);
	int deleteById(Integer id);
	int updateKidId(Mover mover);
}
