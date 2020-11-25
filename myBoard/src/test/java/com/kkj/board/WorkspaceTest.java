package com.kkj.board;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.kkj.board.workspace.WorkspaceDao;
import com.kkj.board.workspace.WorkspaceVO;

@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class) // 스프링 테스트 컨텍스트 프레임워크의 JUnit기능 확장
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
public class WorkspaceTest {

	@Autowired WorkspaceDao workspaceDao;
	
	@Test
	public void test() {
		WorkspaceVO workspaceVO = new WorkspaceVO();
		
		workspaceVO.setDiv(10);
		workspaceVO.setName("test");
		workspaceVO.setRegId("hello");
		workspaceVO.setTopic("yo");
		
		workspaceDao.doInsert(workspaceVO);
		workspaceDao.doInsertChk(workspaceVO);
		
		workspaceDao.doSelectListTopic();
		
		List<WorkspaceVO> outList = workspaceDao.doSelectList();
		workspaceVO = outList.get(0);
				
		workspaceVO = workspaceDao.doSelectOne(workspaceVO);
		workspaceVO.setName("as");
		workspaceDao.doUpdate(workspaceVO);
		
		workspaceDao.doDelete(workspaceVO);
		
	}

}
