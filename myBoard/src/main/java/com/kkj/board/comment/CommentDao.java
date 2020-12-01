package com.kkj.board.comment;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kkj.board.member.MemberVO;

@Repository
public class CommentDao {

	@Autowired CommentVO commentVO;
	@Autowired SqlSessionTemplate sqlSessionTemplate;
	
	final static Logger LOG = LoggerFactory.getLogger(CommentDao.class);
	private final String NAMESPACE = "com.kkj.board.comment.";
	
	public int doInsert(CommentVO commentVO) {
		LOG.debug("==================================================");
        LOG.debug("==doInsert==");
        String statement = NAMESPACE + "doInsert";
        
        LOG.debug("==statement==" + statement);
        LOG.debug("==commentVO==" + commentVO);
        
        int flag = sqlSessionTemplate.insert(statement, commentVO);
        
        LOG.debug("==flag==" + flag);
        LOG.debug("==================================================");
        
        return flag;
	}
	
	public int doDelete(CommentVO commentVO) {
		
		LOG.debug("==================================================");
        LOG.debug("==doDelete==");
        String statement = NAMESPACE + "doDelete";
        
        LOG.debug("==statement==" + statement);
        LOG.debug("==commentVO==" + commentVO);
        
        int flag = sqlSessionTemplate.delete(statement, commentVO);
        
        LOG.debug("==flag==" + flag);
        LOG.debug("==================================================");
        
        return flag;
	}
	
	public int doUpdate(CommentVO commentVO) {
	
		LOG.debug("==================================================");
        LOG.debug("==doUpdate==");
        String statement = NAMESPACE + "doUpdate";
        
        LOG.debug("==statement==" + statement);
        LOG.debug("==commentVO==" + commentVO);
        
        int flag = sqlSessionTemplate.update(statement, commentVO);
        
        LOG.debug("==flag==" + flag);
        LOG.debug("==================================================");
        
        return flag;
	}
	
	public CommentVO doSelectOne(CommentVO commentVO) {
		
		LOG.debug("==================================================");
        LOG.debug("==doSelectOne==");
        String statement = NAMESPACE + "doSelectOne";

        LOG.debug("==statement==" + statement);
        LOG.debug("==commentVO==" + commentVO);

        CommentVO outVO = sqlSessionTemplate.selectOne(statement, commentVO);
        LOG.debug("==outVO==" + outVO);
        LOG.debug("==================================================");

        return outVO;
	}
	
	public List<CommentVO> doSelectListByBoardSeq(CommentVO commentVO){
		LOG.debug("==================================================");
        LOG.debug("==doSelectListByBoardSeq==");
        String statement = NAMESPACE + "doSelectListByBoardSeq";
        
        LOG.debug("==statement==" + statement);
        
        List<CommentVO> outList = sqlSessionTemplate.selectList(statement, commentVO);
        for(CommentVO vo : outList) {
        	LOG.debug("==outVO==" + vo);
        }
        
        LOG.debug("==================================================");
        
        return outList;
	}
	
}
