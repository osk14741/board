package com.kkj.board.media;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kkj.board.member.MemberVO;

@Repository
public class MediaDao {
	
	final static Logger LOG = LoggerFactory.getLogger(MediaDao.class);
	private final String NAMESPACE = "com.kkj.board.media.";
	
	@Autowired MediaVO mediaVO;
	@Autowired SqlSessionTemplate sqlSessionTemplate;
	
	public int doUpdate(MediaVO mediaVO) {
		LOG.debug("==================================================");
        LOG.debug("==doUpdate==");
        String statement = NAMESPACE + "doUpdate";
        
        LOG.debug("==statement==" + statement);
        LOG.debug("==mediaVO==" + mediaVO);
        
        int flag = sqlSessionTemplate.update(statement, mediaVO);
        
        LOG.debug("==flag==" + flag);
        LOG.debug("==================================================");
        
        return flag;
	}
	
	public int doDelete(MediaVO mediaVO) {
		LOG.debug("==================================================");
        LOG.debug("==doDelete==");
        String statement = NAMESPACE + "doDelete";
        
        LOG.debug("==statement==" + statement);
        LOG.debug("==mediaVO==" + mediaVO);
        
        int flag = sqlSessionTemplate.delete(statement, mediaVO);
        
        LOG.debug("==flag==" + flag);
        LOG.debug("==================================================");
        
        return flag;
	}
	
	public int doInsert(MediaVO mediaVO) {
		LOG.debug("==================================================");
        LOG.debug("==doInsert==");
        String statement = NAMESPACE + "doInsert";
        
        LOG.debug("==statement==" + statement);
        LOG.debug("==mediaVO==" + mediaVO);
        
        int flag = sqlSessionTemplate.insert(statement, mediaVO);
        
        LOG.debug("==flag==" + flag);
        LOG.debug("==================================================");
        
        return flag;
	}
	
	public MediaVO doSelectOne(MediaVO mediaVO) {
		LOG.debug("==================================================");
        LOG.debug("==doSelectOne==");
        String statement = NAMESPACE + "doSelectOne";

        LOG.debug("==statement==" + statement);
        LOG.debug("==mediaVO==" + mediaVO);

        MediaVO outVO = sqlSessionTemplate.selectOne(statement, mediaVO);
        LOG.debug("==outVO==" + outVO);
        LOG.debug("==================================================");

        return outVO;
	}
}
