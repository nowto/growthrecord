package cn.edu.zzti.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.edu.zzti.dto.BodyRecordSearchBean;
import cn.edu.zzti.dto.Page;
import cn.edu.zzti.dto.StudyRecordSearchBean;
import cn.edu.zzti.entity.BodyRecord;
import cn.edu.zzti.entity.Kid;
import cn.edu.zzti.entity.StudyRecord;
import cn.edu.zzti.service.BodyRecordService;
import cn.edu.zzti.service.KidService;
import cn.edu.zzti.util.LoginNameUtils;

/**
 * 生理记录控制类
 * 
 * @author nowto
 * 
 */
@Controller
@RequestMapping("/bodyrecord")
public class BodyRecordController {
	@Resource
	private KidService kidService;
	@Resource
	private BodyRecordService bodyRecordService;

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
			BodyRecordSearchBean bodyrecordSB, Model model, HttpSession session) {
		String username = LoginNameUtils.getLoginUsername(session);
		Page<BodyRecord> page = bodyRecordService.search(bodyrecordSB, username);
		model.addAttribute("page", page);
		model.addAttribute("sb", bodyrecordSB);
		return "/bodyrecord/more";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getAdd(@ModelAttribute("kids") List<Kid> kids) {
		return "/bodyrecord/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String postAdd(BodyRecord bodyRecord, Model model,
			HttpSession session) {
		String view = null;
		int type = bodyRecord.getType();
		String username = LoginNameUtils.getLoginUsername(session);
		if (bodyRecordService.add(bodyRecord, username)) {
			model.addAttribute("type", type);
			model.addAttribute("result", "添加成功");
			return "redirect:/bodyrecord/more.do";
		} else {
			model.addAttribute("result", "添加失败");
			return "/bodyrecord/add";
		}
	}
	
	@RequestMapping("linechart")
	public String linechart(BodyRecordSearchBean sb, HttpSession session, Model model){
		List<BodyRecord> list = bodyRecordService.getChart(sb, LoginNameUtils.getLoginUsername(session));
		
		switch (sb.getType()) {
		case 0:
		case 1:
			model.addAttribute("list", list);
			break;
		default:
			break;
		}
		return "/bodyrecord/linechart";
	}
	
	@RequestMapping("/remove")
	public void remove(String ids){
		if(ids == null)
			return;
		String[] strs = ids.split(",");
		List<Integer> list = new ArrayList<>();
		for (String str : strs) {
			list.add(Integer.parseInt(str));
		}
		bodyRecordService.removeAll(list);
	}
	
}
