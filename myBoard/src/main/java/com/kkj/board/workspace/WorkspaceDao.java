package com.kkj.board.workspace;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kkj.board.member.MemberDao;

@Repository
public class WorkspaceDao {
	
	final static Logger LOG = LoggerFactory.getLogger(MemberDao.class);
	private final String NAMESPACE = "com.kkj.board.workspace.";
	
	@Autowired WorkspaceVO workspaceVO;
	@Autowired SqlSessionTemplate sqlSessionTemplate;
	
	public WorkspaceVO doSelectOneByName(WorkspaceVO workspaceVO) {
		LOG.debug("==================================================");
        LOG.debug("==doSelectOneByName==");
        String statement = NAMESPACE + "doSelectOneByName";
        
        LOG.debug("==statement==" + statement);
        LOG.debug("==workspaceVO==" + workspaceVO);

        WorkspaceVO outVO = sqlSessionTemplate.selectOne(statement, workspaceVO);
        LOG.debug("==outVO==" + outVO);
        LOG.debug("==================================================");

        return outVO;
        
	}
	
	public List<WorkspaceVO> doSelectListTopic(){
		LOG.debug("==================================================");
        LOG.debug("==doSelectListTopic==");
        String statement = NAMESPACE + "doSelectListTopic";

		LOG.debug("==statement==" + statement);

		List<WorkspaceVO> outList = sqlSessionTemplate.selectList(statement);
		for (WorkspaceVO vo : outList) {
			LOG.debug("==outVO==" + vo);
        }
        
        LOG.debug("==================================================");
        
        return outList;
	}
	
	public int doInsertChk(WorkspaceVO workspaceVO) {
		LOG.debug("==================================================");
        LOG.debug("==doInsertChk==");

		String statement = NAMESPACE + "doInsertChk";

		LOG.debug("==statement==" + statement);
		LOG.debug("==workspaceVO==" + workspaceVO);

		int flag = sqlSessionTemplate.insert(statement, workspaceVO);

		LOG.debug("==flag==" + flag);
		LOG.debug("==================================================");

		return flag;
        
	}
	
	public int doInsert(WorkspaceVO workspaceVO) {
		LOG.debug("==================================================");
        LOG.debug("==doInsert==");
        String statement = NAMESPACE + "doInsert";
        
		LOG.debug("==statement==" + statement);
		LOG.debug("==workspaceVO==" + workspaceVO);

		int flag = sqlSessionTemplate.insert(statement, workspaceVO);

		LOG.debug("==flag==" + flag);
		LOG.debug("==================================================");

		return flag;
	}
	
	public WorkspaceVO doSelectOne(WorkspaceVO workspaceVO) {
		LOG.debug("==================================================");
        LOG.debug("==doSelectOne==");
        String statement = NAMESPACE + "doSelectOne";
        
        LOG.debug("==statement==" + statement);
        LOG.debug("==workspaceVO==" + workspaceVO);

        WorkspaceVO outVO = sqlSessionTemplate.selectOne(statement, workspaceVO);
        LOG.debug("==outVO==" + outVO);
        LOG.debug("==================================================");

        return outVO;
		
	}
	
	public List<WorkspaceVO> doSelectList(){
		LOG.debug("==================================================");
        LOG.debug("==doSelectList==");
        String statement = NAMESPACE + "doSelectList";

		LOG.debug("==statement==" + statement);

		List<WorkspaceVO> outList = sqlSessionTemplate.selectList(statement);
		for (WorkspaceVO vo : outList) {
			LOG.debug("==outVO==" + vo);
        }
        
        LOG.debug("==================================================");
        
        return outList;
	}
	
	public int doUpdate(WorkspaceVO workspaceVO) {
		LOG.debug("==================================================");
        LOG.debug("==doUpdate==");
        String statement = NAMESPACE + "doUpdate";
        
        LOG.debug("==statement==" + statement);
		LOG.debug("==workspaceVO==" + workspaceVO);

		int flag = sqlSessionTemplate.update(statement, workspaceVO);

		LOG.debug("==flag==" + flag);
		LOG.debug("==================================================");

		return flag;
	}
	
	public int doDelete(WorkspaceVO workspaceVO) {
		LOG.debug("==================================================");
        LOG.debug("==doDelete==");
        String statement = NAMESPACE + "doDelete";
        
        LOG.debug("==statement==" + statement);
        LOG.debug("==workspaceVO==" + workspaceVO);
        
        int flag = sqlSessionTemplate.delete(statement, workspaceVO);
        
        LOG.debug("==flag==" + flag);
        LOG.debug("==================================================");
        
        return flag;
	}
}
