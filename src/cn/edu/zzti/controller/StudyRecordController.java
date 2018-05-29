package cn.edu.zzti.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.edu.zzti.dto.Page;
import cn.edu.zzti.dto.StudyRecordSearchBean;
import cn.edu.zzti.entity.Kid;
import cn.edu.zzti.entity.StudyRecord;
import cn.edu.zzti.service.KidService;
import cn.edu.zzti.service.StudyRecordService;
import cn.edu.zzti.util.LoginNameUtils;

/**
 * 学业记录管理控制类
 * 
 * @author nowto
 * 
 */
@Controller
@RequestMapping(value = "/studyrecord")
public class StudyRecordController {
	@Resource
	private KidService kidService;
	@Resource
	private StudyRecordService studyRecordService;

	@ModelAttribute("kids")
	List<Kid> getKids(HttpSession session) {
		String username = LoginNameUtils.getLoginUsername(session);
		List<Kid> kids = kidService.get(username);
		return kids;
	}

	/**
	 * 到记录页 可搜索
	 * 
	 * @param session
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/more", method = RequestMethod.GET)
	public String getMoreStudyRecord(@ModelAttribute("kids") List<Kid> kids,
			StudyRecordSearchBean studyRecordSB, Model model, HttpSession session) {
		String username = LoginNameUtils.getLoginUsername(session);
		Page<StudyRecord> page = studyRecordService.search(studyRecordSB, username);
		model.addAttribute("page", page);
		model.addAttribute("sb", studyRecordSB);
		return "/studyrecord/more";
	}

	/**
	 * 到添加记录页
	 * 
	 * @param kids
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getAdd(@ModelAttribute("kids") List<Kid> kids) {
		return "/studyrecord/add";
	}

	/**
	 * 处理添加
	 * 
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String postAdd(StudyRecord studyRecord, Model model,
			HttpSession session) {
		String username = LoginNameUtils.getLoginUsername(session);
		if (studyRecordService.add(studyRecord, username))
			model.addAttribute("result", "添加成功");
		else
			model.addAttribute("result", "添加失败");

		return "/studyrecord/more";
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
		studyRecordService.removeAll(list);
	}
	public KidService getKidService() {
		return kidService;
	}

	public void setKidService(KidService kidService) {
		this.kidService = kidService;
	}

	public StudyRecordService getStudyRecordService() {
		return studyRecordService;
	}

	public void setStudyRecordService(StudyRecordService studyRecordService) {
		this.studyRecordService = studyRecordService;
	}
}
