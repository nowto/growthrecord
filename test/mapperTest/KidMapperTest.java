package mapperTest;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.edu.zzti.dto.VaccineNotify;
import cn.edu.zzti.mapper.KidMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class KidMapperTest {
	@Resource
	KidMapper kidMapper;
	
	@Test
	public void testInsert(){
		List<VaccineNotify> list = kidMapper.selectVaccineNotify(11);
		System.out.println(list.size());
	}
}
