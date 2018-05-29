package service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.edu.zzti.entity.User;
import cn.edu.zzti.mapper.UserMapper;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class TestMyBatis {
	@Resource
	private UserMapper userMapper;
	
	@Test
	public void test(){
		User user = userMapper.select(1);
//		System.out.println(user);
		
		User user2 = userMapper.selectWithHome(1);
		System.out.println(user2);
	}
	
	@Test
	public void testByUsername(){
		User user = userMapper.selectByUsername("liweibor");
//		System.out.println(user);
		
		User user2 = userMapper.selectByUsernameWithHome("liweibor");
		System.out.println(user2);
	}
}
