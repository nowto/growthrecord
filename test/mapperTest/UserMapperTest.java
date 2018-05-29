package mapperTest;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.edu.zzti.dto.UserDto;
import cn.edu.zzti.entity.User;
import cn.edu.zzti.mapper.UserMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class UserMapperTest {
	@Resource
	UserMapper userMapper;
	
	@Test
	public void testInsert(){
		List<User> list = userMapper.selectByHomeId(1);
		System.out.println(list.size());
	}
	@Test
	public void testByAll(){
		UserDto user = new UserDto();
		user.setPassword("wlj");
		
		List<User> us = userMapper.selectByAll(user);
		System.out.println(us.size());
		System.out.println(us);
	}
}
