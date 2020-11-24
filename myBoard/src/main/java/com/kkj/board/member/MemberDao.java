package com.kkj.board.member;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDao {

	final static Logger LOG = LoggerFactory.getLogger(MemberDao.class);
	private final String NAMESPACE = "com.kkj.board.member.";
	
	@Autowired MemberVO memberVO;
	@Autowired SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * doUpdate(회원 수정)
	 * @param memberVO
	 * @return flag
	 */
	public int doUpdate(MemberVO memberVO) {
		LOG.debug("==================================================");
        LOG.debug("==doUpdate==");
        String statement = NAMESPACE + "doUpdate";
        
        LOG.debug("==statement==" + statement);
        LOG.debug("==memberVO==" + memberVO);
        
        int flag = sqlSessionTemplate.update(statement, memberVO);
        
        LOG.debug("==flag==" + flag);
        LOG.debug("==================================================");
        
        return flag;
	}
	
	/**
	 * doDelete(계정 삭제)
	 * @param memberVO(id, password)
	 * @return flag
	 */
	public int doDelete(MemberVO memberVO) {
		LOG.debug("==================================================");
        LOG.debug("==doDelete==");
        String statement = NAMESPACE + "doDelete";
        
        LOG.debug("==statement==" + statement);
        LOG.debug("==memberVO==" + memberVO);
        
        int flag = sqlSessionTemplate.delete(statement, memberVO);
        
        LOG.debug("==flag==" + flag);
        LOG.debug("==================================================");
        
        return flag;
	}
	
	/**
	 * doInsert(회원가입)
	 * @param memberVO(id, password, name, gender, email, authority, loginCount, recommendCount, grade)
	 * @return flag
	 */
	public int doInsert(MemberVO memberVO) {
		LOG.debug("==================================================");
        LOG.debug("==doInsert==");
        String statement = NAMESPACE + "doInsert";
        
        LOG.debug("==statement==" + statement);
        LOG.debug("==memberVO==" + memberVO);
        
        int flag = sqlSessionTemplate.insert(statement, memberVO);
        
        LOG.debug("==flag==" + flag);
        LOG.debug("==================================================");
        
        return flag;
	}
	
	/**
	 * doInsertChk
	 * @param memberVO
	 * @return flag = 1(성공) : 0(중복)
	 */
	public int doInsertChk(MemberVO memberVO) {
		LOG.debug("==================================================");
        LOG.debug("==doInsertChk==");
        String statement = NAMESPACE + "doInsertChk";
        
        LOG.debug("==statement==" + statement);
        LOG.debug("==memberVO==" + memberVO);
        
        int flag = sqlSessionTemplate.insert(statement, memberVO);
        
        LOG.debug("==flag==" + flag);
        LOG.debug("==================================================");
        
        return flag;
	}
	
	/**
	 * doSelectOne(id 값으로 유저 찾기)
	 * @param memberVO(id)
	 * @return MemberVO
	 */
	public MemberVO doSelectOne(MemberVO memberVO) {
		LOG.debug("==================================================");
        LOG.debug("==doSelectOne==");
        String statement = NAMESPACE + "doSelectOne";

        LOG.debug("==statement==" + statement);
        LOG.debug("==memberVO==" + memberVO);

        MemberVO outVO = sqlSessionTemplate.selectOne(statement, memberVO);
        LOG.debug("==outVO==" + outVO);
        LOG.debug("==================================================");

        return outVO;
	}
	
	/**
	 * doSelectList(가입된 모든 유저 찾기)
	 * @return List<MemberVO>
	 */
	public List<MemberVO> doSelectList(){
		LOG.debug("==================================================");
        LOG.debug("==doSelectList==");
        String statement = NAMESPACE + "doSelectList";
        
        LOG.debug("==statement==" + statement);
        
        List<MemberVO> outList = sqlSessionTemplate.selectList(statement);
        for(MemberVO vo : outList) {
        	LOG.debug("==outVO==" + vo);
        }
        
        LOG.debug("==================================================");
        
        return outList;
	}
}
