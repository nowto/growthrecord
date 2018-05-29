package cn.edu.zzti.mapper.admin;

import java.util.List;

import cn.edu.zzti.dto.VaccinePlanSearchBean;
import cn.edu.zzti.entity.VaccinePlan;

public interface VaccinePlanMapper {
	int insert(VaccinePlan vaccinePlan);

	List<VaccinePlan> selectBySearch(VaccinePlanSearchBean vpsb);

	int selectBySearchCount(VaccinePlanSearchBean vpsb);

	void deleteById(Integer id);

	VaccinePlan selectById(Integer id);

	void update(VaccinePlan vaccinePlan);
}
