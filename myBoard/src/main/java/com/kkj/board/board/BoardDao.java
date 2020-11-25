package com.kkj.board.board;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kkj.board.member.MemberDao;
import com.kkj.board.member.MemberVO;

@Repository
public class BoardDao {
	
	@Autowired SqlSessionTemplate sqlSessionTemplate;
	@Autowired BoardVO boardVO;
	final static Logger LOG = LoggerFactory.getLogger(MemberDao.class);
	private final String NAMESPACE = "com.kkj.board.board.";

	/**
	 * 
	 * @param boardVO
	 * @return
	 */
	public BoardVO doSelectOne(BoardVO boardVO) {
		LOG.debug("==================================================");
        LOG.debug("==doSelectOne==");
        String statement = NAMESPACE + "doSelectOne";

        LOG.debug("==statement==" + statement);
        LOG.debug("==boardVO==" + boardVO);

        BoardVO outVO = sqlSessionTemplate.selectOne(statement, boardVO);
        LOG.debug("==outVO==" + outVO);
        LOG.debug("==================================================");

        return outVO;
	}

	public List<BoardVO> doSelectList(BoardVO boardVO) {
		LOG.debug("==================================================");
        LOG.debug("==doSelectList==");
        String statement = NAMESPACE + "doSelectList";
        
        LOG.debug("==statement==" + statement);
        
        List<BoardVO> outList = sqlSessionTemplate.selectList(statement, boardVO);
        for(BoardVO vo : outList) {
        	LOG.debug("==outVO==" + vo);
        }
        
        LOG.debug("==================================================");
        
        return outList;
	}

	public int doInsert(BoardVO boardVO) {
		LOG.debug("==================================================");
        LOG.debug("==doInsert==");
        String statement = NAMESPACE + "doInsert";
        
        LOG.debug("==statement==" + statement);
        LOG.debug("==boardVO==" + boardVO);
        
        int flag = sqlSessionTemplate.insert(statement, boardVO);
        
        LOG.debug("==flag==" + flag);
        LOG.debug("==================================================");
        
        return flag;
	}

	public int doUpdate(BoardVO boardVO) {
		LOG.debug("==================================================");
        LOG.debug("==doUpdate==");
        String statement = NAMESPACE + "doUpdate";
        
        LOG.debug("==statement==" + statement);
        LOG.debug("==memberVO==" + boardVO);
        
        int flag = sqlSessionTemplate.update(statement, boardVO);
        
        LOG.debug("==flag==" + flag);
        LOG.debug("==================================================");
        
        return flag;
	}

	public int doDelete(BoardVO boardVO) {
		LOG.debug("==================================================");
        LOG.debug("==doDelete==");
        String statement = NAMESPACE + "doDelete";
        
        LOG.debug("==statement==" + statement);
        LOG.debug("==memberVO==" + boardVO);
        
        int flag = sqlSessionTemplate.delete(statement, boardVO);
        
        LOG.debug("==flag==" + flag);
        LOG.debug("==================================================");
        
        return flag;
	}

}
