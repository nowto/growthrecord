package cn.edu.zzti.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.edu.zzti.dto.Page;
import cn.edu.zzti.dto.VaccinePlanSearchBean;
import cn.edu.zzti.entity.VaccinePlan;
import cn.edu.zzti.service.admin.VaccinePlanService;
import cn.edu.zzti.util.LoginNameUtils;

@Controller
@RequestMapping("/admin/vaccineplan")
public class VaccineplanController {
	
	@Resource
	private VaccinePlanService vaccinePlanService;
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String getAdd(){
		return "/admin/vaccineplan/add";
	}
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String postAdd(VaccinePlan vaccinePlan, Model model){
		boolean ret = vaccinePlanService.add(vaccinePlan);
		if (ret) {
			model.addAttribute("result", "添加成功");
		} else {
			model.addAttribute("result", "添加失败");
		}
		return "/admin/vaccineplan/more";
	}
	
	@RequestMapping(value="/more")
	public String more(VaccinePlanSearchBean vpsb, Model model, HttpSession session){
		String username = LoginNameUtils.getLoginUsername(session);
		Page<VaccinePlan> page = vaccinePlanService.search(vpsb, username);
		model.addAttribute("page", page);
		model.addAttribute("sb", vpsb);
		return "/admin/vaccineplan/more";
	}
	
	/**
	 * 批量删除
	 * @return
	 */
	@RequestMapping("/remove")
	public void remove(String ids){
		String[] strs = ids.split(",");
		List<Integer> list = new ArrayList<>();
		for (String str : strs) {
			list.add(Integer.parseInt(str));
		}
		vaccinePlanService.removeAll(list);
	}
	
	/**
	 * 获取更新视图
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String getUpdate(Integer id, Model model){
		VaccinePlan plan = vaccinePlanService.get(id);
		model.addAttribute("vaccineplan", plan);
		return "/admin/vaccineplan/update";
	}
	

	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String postUpdate(VaccinePlan vaccinePlan, Model model){
		if (vaccinePlanService.update(vaccinePlan)) {
			model.addAttribute("result", "修改成功");
		} else {
			model.addAttribute("result", "修改失败");
		}
		model.addAttribute("vaccineplan", vaccinePlan);
		return "/admin/vaccineplan/update";
	}
	public VaccinePlanService getVaccinePlanService() {
		return vaccinePlanService;
	}
	public void setVaccinePlanService(VaccinePlanService vaccinePlanService) {
		this.vaccinePlanService = vaccinePlanService;
	}
}
