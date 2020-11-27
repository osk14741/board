package com.kkj.board.member;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

	@Autowired MemberDao memberDao;
	final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	public MemberVO doSelectOneByNameAndEmail(MemberVO memberVO) {
		return memberDao.doSelectOneByNameAndEmail(memberVO);
	}
	
	public MemberVO doSelectOneByIdAndEmail(MemberVO memberVO) {
		return memberDao.doSelectOneByIdAndEmail(memberVO);
	}
	
	public int doUpdate(MemberVO memberVO) {
		return memberDao.doUpdate(memberVO);
	}
	
	public int doDelete(MemberVO memberVO) {
		return memberDao.doDelete(memberVO);
	}
	
	public int doInsert(MemberVO memberVO) {
		return memberDao.doInsert(memberVO);
	}
	
	public MemberVO doSelectOne(MemberVO memberVO) {
		return memberDao.doSelectOne(memberVO);
	}
	
	public List<MemberVO> doSelectList(){
		return memberDao.doSelectList();
	}
	
	public int doLogin(MemberVO memberVO) {
		int flag = 0;
		
		String loginDay = memberVO.getLoginDt();
		LOG.debug("loginDay : " + loginDay);
		
		SimpleDateFormat format2 = new SimpleDateFormat ( "MM/dd");
		Date time = new Date();
		String time2 = format2.format(time);
				
		LOG.debug("today : " + time2);
		
		if(!loginDay.equals(time2)) {
			int tmp = memberVO.getLoginCount();
			tmp = tmp + 1;
			memberVO.setLoginCount(tmp);
			doUpdate(memberVO);
			flag = 1;
		}
		return flag;
	}
	
	/**
	 * doInsertChk
	 * @param memberVO
	 * @return flag = 1(성공) : 0(중복)
	 */
	public int doInsertChk(MemberVO memberVO) {
		return memberDao.doInsertChk(memberVO);
	}
}
