package com.kkj.board.member;

import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {
	
	final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired MemberService memberService;
	
	// 프로필 페이지 이동
	@RequestMapping(value = "member/moveToProfile.do", method = RequestMethod.GET)
	public String moveToProfile() {
		LOG.debug("===========================");
		LOG.debug("==member/moveToProfile.do==");
		LOG.debug("===========================");
		
		return "member/memberModify";
	}
	
	// 회원가입
	@RequestMapping(value = "member/doRegister.do", method = RequestMethod.POST)
	@ResponseBody
	public void doRegister(MemberVO memberVO, HttpServletResponse res) throws SQLIntegrityConstraintViolationException {
		LOG.debug("========================");
		LOG.debug("==member/doRegister.do==");
		LOG.debug("========================");
		
		memberVO.setGrade("Bronze");
		
		LOG.debug("==memberVO==" + memberVO);
		int flag = memberService.doInsertChk(memberVO);
		if(flag == 1) {
			LOG.debug("==회원가입 성공==");
		} else {
			LOG.debug("==회원가입 실패==");
			res.setStatus(404);
		}
	}

	// 로그인
	@RequestMapping(value = "member/doLogin.do", method = RequestMethod.POST)
	public String doLogin(@RequestParam("inputMemberId") String memberId,
						  @RequestParam("inputPassword") String memberPassword,
						  HttpServletRequest req) {
		LOG.debug("=====================");
		LOG.debug("==member/doLogin.do==");
		LOG.debug("=====================");
		
		HttpSession httpSession = req.getSession();
		
		MemberVO memberVO = new MemberVO();
		memberVO.setId(memberId);
		memberVO.setPassword(memberPassword);
		
		try {
			MemberVO daoVO = memberService.doSelectOne(memberVO);
			
			if(!daoVO.getPassword().equals(memberVO.getPassword())) {
				LOG.debug("==login fail==");
				LOG.debug("==비밀번호가 틀림==");
				return "member/login_fail";
			}
			
			httpSession.setAttribute("sessionId", daoVO);
			
		} catch (NullPointerException e) {
			LOG.debug("==login fail==");
			LOG.debug("==존재하지 않는 아이디==");
			return "member/login_fail";
		}
		
		LOG.debug("==login success==");
		return "workspace/channel";
	}
	
	// 회원가입 페이지로 이동
	@RequestMapping(value = "member/registerView.do", method = RequestMethod.GET)
	public String doRegisterView() {
		LOG.debug("==========================");
		LOG.debug("==member/registerView.do==");
		LOG.debug("==========================");
		
		return "member/register";
	}
	
	// 로그인 페이지로 이동
	@RequestMapping(value = "member/loginView.do", method = RequestMethod.GET)
	public String doLoginView() {
		LOG.debug("=======================");
		LOG.debug("==member/loginView.do==");
		LOG.debug("=======================");
		
		return "member/loginPage";
	}
	
}
