package com.kkj.board.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

	@Autowired MemberDao memberDao;
	
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
	
	/**
	 * doInsertChk
	 * @param memberVO
	 * @return flag = 1(성공) : 0(중복)
	 */
	public int doInsertChk(MemberVO memberVO) {
		return memberDao.doInsertChk(memberVO);
	}
}
