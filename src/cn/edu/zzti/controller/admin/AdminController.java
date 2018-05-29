package cn.edu.zzti.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.edu.zzti.dto.UserDto;
import cn.edu.zzti.entity.User;
import cn.edu.zzti.entity.admin.Admin;
import cn.edu.zzti.service.UserService;
import cn.edu.zzti.service.admin.AdminService;

@RequestMapping("/admin")
@Controller
public class AdminController {
	@Resource
	private AdminService adminService;
	@Resource
	private UserService userService;
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String getLogin(){
		System.out.println("Hello");
		return "/admin/login";
	}
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String postLogin(Admin admin, HttpSession session, Model model){
		Admin loginAdmin = adminService.login(admin);
		if (loginAdmin != null) {
			session.setAttribute("admin", "admin");
			return "redirect:main.do";
		}
		model.addAttribute("msg", "密码错误");
		return "/admin/login";
	}
	@RequestMapping(value="/main")
	public String getMain(){
		return "/admin/main";
	}
	@RequestMapping(value="/logoff")
	public String postLogoff(String username, Model model){
		List<User> list = userService.getUserMapper().selectLikeUsername(username);
		model.addAttribute("list", list);
		return "/admin/logoff";
	}
	@RequestMapping(value="/logout")
	public String logout(HttpSession session){
		session.removeAttribute("admin");
		session.invalidate();
		return "redirect:login.do";
	}
	@RequestMapping(value="/delete")
	public String delete(Integer id, Model model){
		if(id != null){
			User user = new User();
			user.setId(id);
			userService.delete(user);
			model.addAttribute("msg", "该用户注销成功");
		}
		return "/admin/logoff";
	}
	public AdminService getAdminService() {
		return adminService;
	}
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
}
