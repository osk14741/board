package com.kkj.board;

import org.junit.FixMethodOrder;
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

import com.kkj.board.media.MediaDao;
import com.kkj.board.media.MediaVO;

@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) // 스프링 테스트 컨텍스트 프레임워크의 JUnit기능 확장
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
public class MediaTest {

	@Autowired MediaDao mediaDao;
	Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void doInsert() {
		MediaVO mediaVO = new MediaVO();
		mediaVO.setDiv("10");
		mediaVO.setImg("hheeee.jpg");
		mediaVO.setMemberId("1234");
		
		mediaDao.doInsert(mediaVO);
		
		mediaDao.doSelectOne(mediaVO);
		
		mediaVO.setImg("asdasd");
		mediaDao.doUpdate(mediaVO);
		
		mediaDao.doSelectOne(mediaVO);
		
		mediaDao.doDelete(mediaVO);
	}

}
