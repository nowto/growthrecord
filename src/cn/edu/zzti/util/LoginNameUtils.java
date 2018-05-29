package cn.edu.zzti.util;

import javax.servlet.http.HttpSession;

public class LoginNameUtils {
	public static String getLoginUsername(HttpSession session){
		String username = (String) session.getAttribute("username");
		return username;
	}
}
