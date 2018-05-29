package cn.edu.zzti.mapper;

import java.util.List;

import cn.edu.zzti.dto.UserDto;
import cn.edu.zzti.entity.User;

public interface UserMapper {
	int insert(User user);
	
	int delete(Integer id);
	
	User select(Integer id);
	User selectWithHome(Integer id);
	User selectByUsername(String username);
	User selectByUsernameWithHome(String username);
	
	List<User> selectLikeUsername(String username);
	List<User> selectByAll(UserDto user);
	List<User> selectByHomeId(Integer homeId);
	List<User> selectByHomeIdWithHome(Integer homeId);
	
	int update(User user);
}
