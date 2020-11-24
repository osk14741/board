package com.kkj.board.member;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MemberController {
	
	final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired MemberDao memberDao;
	
	@RequestMapping(value = "member/login.do", method = RequestMethod.GET)
	public String doLogin() {
		LOG.debug("==================================================");
		LOG.debug("==doLogin==");
		
		return "member/loginPage";
	}
	
	
}
