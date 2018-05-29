package mapperTest;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.edu.zzti.entity.Home;
import cn.edu.zzti.mapper.HomeMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class HomeTest {

	@Resource
	HomeMapper homeMapper;
	@Test
	public void testInsert() {
		Home home = null;
		System.out.println(homeMapper.insert(home));
	}
	@Test
	public void testDelete() {
		System.out.println(homeMapper.delete(6));
	}

}
