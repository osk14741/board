package com.kkj.board;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.kkj.board.board.BoardDao;
import com.kkj.board.cmn.PageVO;

@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) // 스프링 테스트 컨텍스트 프레임워크의 JUnit기능 확장
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
public class BoardTest {

	@Autowired BoardDao boardDao;
	
	@Test
	public void test() {
		
		PageVO pageVO = new PageVO();
		pageVO.setPageNum(4);
		pageVO.setPageSize(5);
		pageVO.setSearchDiv("TW");
		pageVO.setSearchWord("");
		pageVO.setWorkspaceSeq(1);
		
		boardDao.doSelectListWithPaging(pageVO);
		boardDao.doCountTotalVO(pageVO);
		
	}

}
