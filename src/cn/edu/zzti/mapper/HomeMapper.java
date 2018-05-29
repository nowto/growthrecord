package cn.edu.zzti.mapper;

import cn.edu.zzti.entity.Home;

public interface HomeMapper {
	int insert(Home home);
	int delete(Integer id);
	int update(Home home);
	
}
