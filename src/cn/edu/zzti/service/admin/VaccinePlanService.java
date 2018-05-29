package cn.edu.zzti.service.admin;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.edu.zzti.dto.Page;
import cn.edu.zzti.dto.VaccinePlanSearchBean;
import cn.edu.zzti.entity.BodyRecord;
import cn.edu.zzti.entity.Kid;
import cn.edu.zzti.entity.VaccinePlan;
import cn.edu.zzti.mapper.admin.VaccinePlanMapper;

@Service
public class VaccinePlanService {
	@Resource
	private VaccinePlanMapper vaccinePlanMapper;

	public VaccinePlanMapper getVaccinePlanMapper() {
		return vaccinePlanMapper;
	}

	public void setVaccinePlanMapper(VaccinePlanMapper vaccinePlanMapper) {
		this.vaccinePlanMapper = vaccinePlanMapper;
	}
	
	public boolean add(VaccinePlan vaccinePlan) {
		if(vaccinePlanMapper.insert(vaccinePlan) == 1){
			return true;
		};
		return false;
	}

	public Page<VaccinePlan> search(VaccinePlanSearchBean vpsb, String username) {
		List<VaccinePlan> data = vaccinePlanMapper.selectBySearch(vpsb);
		int count = vaccinePlanMapper.selectBySearchCount(vpsb);
		Page<VaccinePlan> page = new Page<>(vpsb.getPageIndex(), data, count);
		return page;
	}

	public void removeAll(List<Integer> list) {
		for (Integer id : list) {
			vaccinePlanMapper.deleteById(id);
		}
	}

	public VaccinePlan get(Integer id) {
		// TODO Auto-generated method stub
		return vaccinePlanMapper.selectById(id);
	}

	/**
	 * 更新疫苗对象
	 * @param kid
	 */
	public boolean update(VaccinePlan plan) {
		if(vaccinePlanMapper.selectById(plan.getId()) == null){
			return false;
		}
		vaccinePlanMapper.update(plan);
		return true;
	}
}
