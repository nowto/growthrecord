package cn.edu.zzti.mapper;

import java.util.List;

import cn.edu.zzti.entity.BodyRecord;
import cn.edu.zzti.entity.FigureRecord;
import cn.edu.zzti.dto.BodyRecordSearchBean;
import cn.edu.zzti.dto.FigureRecordSearchBean;
import cn.edu.zzti.dto.Mover;
import cn.edu.zzti.dto.StudyRecordSearchBean;

public interface FigureRecordMapper {
	int insert(FigureRecord figureRecord);
	FigureRecord select(Integer id);
	FigureRecord selectWithKid(Integer id);
	List<FigureRecord> selectBySearch(FigureRecordSearchBean figureRecordSearchBean);
	int selectBySearchCount(FigureRecordSearchBean figureRecordSearchBean);
	int deleteById(Integer id);
	int updateKidId(Mover mover);
}
