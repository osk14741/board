package com.kkj.board;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.kkj.board.member.MemberDao;
import com.kkj.board.member.MemberService;
import com.kkj.board.member.MemberVO;

@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) // 스프링 테스트 컨텍스트 프레임워크의 JUnit기능 확장
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
public class MemberTest {

	@Autowired MemberDao memberDao;
	@Autowired MemberService memberService;
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Test
	@Ignore
	public void doSelectOne() {
		
		LOG.debug("doSelectOne Test");
		MemberVO memberVO = new MemberVO();
		memberVO.setId("1");
		
		memberDao.doSelectOne(memberVO);
		
	}
	
	@Test
	public void doLogin() {
		MemberVO memberVO = new MemberVO();
		memberVO.setName("hello");
		memberVO.setId("2");
		memberVO.setAuthority(1);
		memberVO.setEmail("ji@naver.com");
		memberVO.setGender(1);
		memberVO.setGrade("gold");
		memberVO.setLoginCount(0);
		memberVO.setModDt("12/21");
		memberVO.setRecommendCount(2);
		memberVO.setPassword("1234");
		memberDao.doInsert(memberVO);
		memberDao.doInsertChk(memberVO);
		memberVO.setPassword("4321");
		
		MemberVO daoVO = memberService.doSelectOne(memberVO);
		
		int flag = memberService.doLogin(daoVO);
		
		LOG.debug("flag : " + flag);
		
	}
	
	@Test
	@Ignore
	public void doSelectList() {
		LOG.debug("doSelectList Test");
		memberDao.doSelectList();
	}

	@Test
	@Ignore
	public void doInsert() {
		LOG.debug("doInsert Test");
		MemberVO memberVO = new MemberVO();
		memberVO.setName("hello");
		memberVO.setId("2");
		memberVO.setAuthority(1);
		memberVO.setEmail("ji@naver.com");
		memberVO.setGender(1);
		memberVO.setGrade("gold");
		memberVO.setLoginCount(0);
		memberVO.setModDt("12/21");
		memberVO.setRecommendCount(2);
		memberVO.setPassword("1234");
		memberDao.doInsert(memberVO);
		memberDao.doInsertChk(memberVO);
		memberVO.setPassword("4321");
		
		memberDao.doUpdate(memberVO);
		
		memberDao.doSelectOne(memberVO);
		
		memberDao.doDelete(memberVO);
	}
}
