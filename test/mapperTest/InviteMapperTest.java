package mapperTest;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.edu.zzti.entity.Invite;
import cn.edu.zzti.mapper.InviteMapper;
import cn.edu.zzti.mapper.UserMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class InviteMapperTest {
	@Resource
	private InviteMapper inviteMapper;
	@Resource
	private UserMapper userMapper;
	
//	@Test
//	public void testSelect(){
//		Invite invite = inviteMapper.select(1);
//		
//		System.out.println(invite.getId());
//		System.out.println(invite.getGongId());
//		System.out.println(invite.getShouId());
//		System.out.println(invite.getGong());
//		System.out.println(invite.getShou());
//	}
//	
//	@Test
//	public void testSelectWithGs(){
//		Invite invite = inviteMapper.selectWithGS(1);
//		
//		System.out.println(invite.getId());
//		System.out.println(invite.getGongId());
//		System.out.println(invite.getShouId());
//		System.out.println(invite.getGong());
//		System.out.println(invite.getShou());
//	}
//	
//	@Test
//	public void testSelectByGong(){
//		
//		
//		List<Invite> invites = inviteMapper.selectByGong(userMapper.select(1));
//		
//		for (Invite invite : invites) {
//			
//			System.out.println(invite.getId());
//			System.out.println(invite.getGongId());
//			System.out.println(invite.getShouId());
//			System.out.println(invite.getGong());
//			System.out.println(invite.getShou());
//		}
//		
//	}
//	@Test
//	public void testSelectByGongWithGs(){
//		
//		
//		List<Invite> invites = inviteMapper.selectByGongWithGS(userMapper.select(1));
//		
//		for (Invite invite : invites) {
//			
//			System.out.println(invite.getId());
//			System.out.println(invite.getGongId());
//			System.out.println(invite.getShouId());
//			System.out.println(invite.getGong());
//			System.out.println(invite.getShou());
//		}
//		
//	}
//	
//	@Test
//	public void testSelectByShou(){
//		
//		
//		List<Invite> invites = inviteMapper.selectByShou(userMapper.select(2));
//		
//		for (Invite invite : invites) {
//			
//			System.out.println(invite.getId());
//			System.out.println(invite.getGongId());
//			System.out.println(invite.getShouId());
//			System.out.println(invite.getGong());
//			System.out.println(invite.getShou());
//		}
//		
//	}
	@Test
	public void testSelectByShouWithGs(){
		
		
		Invite i = inviteMapper.selectWithGS(16);
		System.out.println(i);
		
	}
}
