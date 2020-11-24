package com.kkj.board.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemberController {
	
	final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired MemberService memberService;
	
	

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
		return "member/login_success";
	}
	
	@RequestMapping(value = "member/registerView.do", method = RequestMethod.GET)
	public String doRegisterView() {
		LOG.debug("==========================");
		LOG.debug("==member/registerView.do==");
		LOG.debug("==========================");
		
		return "member/register";
	}
	
	@RequestMapping(value = "member/loginView.do", method = RequestMethod.GET)
	public String doLoginView() {
		LOG.debug("=======================");
		LOG.debug("==member/loginView.do==");
		LOG.debug("=======================");
		
		return "member/loginPage";
	}
	
}
