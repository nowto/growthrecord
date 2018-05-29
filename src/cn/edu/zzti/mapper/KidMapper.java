package cn.edu.zzti.mapper;

import java.util.List;

import cn.edu.zzti.dto.VaccineNotify;
import cn.edu.zzti.entity.Kid;

public interface KidMapper {
	int insert(Kid kid);

	// 引用 KID表id的都为级联删除casecade
	int delete(Integer id);

	int deleteByHomeId(Integer id);

	Kid select(Integer id);
	List<Kid> selectTemplet(Integer newHomeId);
	List<Kid> selectFollower(Kid kid);
	List<VaccineNotify> selectVaccineNotify(Integer homeId);
	int selectVaccineNotifyNum(Integer homeId);
	List<Kid> selectByHomeId(Integer homeId);

	int update(Kid kid);
}
