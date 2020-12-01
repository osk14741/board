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

import com.kkj.board.comment.CommentDao;
import com.kkj.board.comment.CommentVO;

@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) // 스프링 테스트 컨텍스트 프레임워크의 JUnit기능 확장
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
public class CommentTest {

	@Autowired CommentDao commentDao;
	
	@Test
	public void test() {
	
		CommentVO commentVO = new CommentVO();
		commentVO.setBoardSeq(99999);
		commentVO.setContent("content");
		commentVO.setRegId("commentId");
		
		commentDao.doInsert(commentVO);
		
		commentVO = commentDao.doSelectListByBoardSeq(commentVO).get(0);
		
		commentDao.doSelectOne(commentVO);
		
		commentDao.doUpdate(commentVO);
		
		commentDao.doDelete(commentVO);
	}

}
