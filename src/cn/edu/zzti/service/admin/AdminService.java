package cn.edu.zzti.service.admin;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import cn.edu.zzti.entity.admin.Admin;
import cn.edu.zzti.mapper.admin.AdminMapper;

@Service
public class AdminService {
	@Resource
	private AdminMapper adminMapper;
	/**
	 * 登录
	 * @param admin
	 * @return
	 */
	public Admin login(Admin admin) {
		return adminMapper.select(admin);
	}
	public AdminMapper getAdminMapper() {
		return adminMapper;
	}
	public void setAdminMapper(AdminMapper adminMapper) {
		this.adminMapper = adminMapper;
	}
}
