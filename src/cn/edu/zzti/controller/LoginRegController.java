package cn.edu.zzti.controller;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.edu.zzti.dto.ForwardBean;
import cn.edu.zzti.dto.UserDto;
import cn.edu.zzti.entity.User;
import cn.edu.zzti.service.UserService;

/**
 * 登录 注册 等不需要权限验证的功能处理类
 * 
 * @author nowto
 * 
 */
@Controller
public class LoginRegController {
	@Resource
	private UserService userService;

	/**
	 * 获取注册视图
	 * 
	 * @return 注册视图名
	 */
	@RequestMapping(value = "/register.do", method = RequestMethod.GET)
	public String getRegister() {
		return "register";
	}

	/**
	 * 处理注册action
	 * 
	 * @param user
	 *            用户对象
	 * @param errors
	 *            发生了验证错误，供获取验证状态
	 * @param model
	 *            供添加注册结果，转发
	 * @return
	 */
	@RequestMapping(value = "/register.do", method = RequestMethod.POST)
	public String register(@ModelAttribute("user") @Valid UserDto user,
			Errors errors, Model model) {
		if (errors.hasErrors()) {
			// 后台校验出错
			String view = "register";
			String target = "注册";
			String message = "后台校验数据失败";

			ForwardBean forward = new ForwardBean(view, target, message);
			model.addAttribute("forward", forward);
			return "forward";
		}

		boolean flag = userService.regist(user);
		if (flag) {// 注册成功
			ForwardBean forward = new ForwardBean("login", "登录", "注册成功");
			model.addAttribute("forward", forward);
			return "forward";
		}

		// 注册失败
		ForwardBean forward = new ForwardBean("register", "注册",
				"注册失败，可能是用户重名...");
		model.addAttribute("forward", forward);
		return "forward";
	}

	/**
	 * 检测是否数据库已有用户名为username的用户
	 * 
	 * @param username
	 *            用户名
	 * @param writer
	 *            客户端输出流
	 */
	@RequestMapping(value = "/usernamedb")
	public void usernamedb(String username, PrintWriter writer) {
		if (userService.find(username)) {
			writer.println("{\"valid\":false}");
		} else {
			writer.println("{\"valid\":true}");

		}
	}

	/**
	 * 获取 登录表单
	 * 
	 * @return 表单视图名
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLogin() {
		return "login";
	}

	/**
	 * 处理用户登录
	 * @param user 表单提交用户
	 * @param session 会话对象，供添加登录用户username属性
	 * @return 首页视图
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String postLogin(UserDto user, Model model, HttpSession session) {
		User loginUser = userService.login(user);
		if (loginUser != null) {
			session.setAttribute("username", loginUser.getUsername());
			return "redirect:morekid.do";
		}
		model.addAttribute("result", "用户名或密码错误");
		return "login";
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
