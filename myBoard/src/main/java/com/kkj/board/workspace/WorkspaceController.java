package com.kkj.board.workspace;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

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

import com.google.gson.Gson;
import com.kkj.board.member.MemberVO;

@Controller
public class WorkspaceController {

	final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired WorkspaceService workspaceService;
	
	// 채널 등록
	@RequestMapping(value = "workspace/doRegister.do", method = RequestMethod.POST)
	@ResponseBody
	public void doRegister(WorkspaceVO workspaceVO, HttpServletRequest req, HttpServletResponse res) throws SQLIntegrityConstraintViolationException {
		LOG.debug("===========================");
		LOG.debug("==workspace/doRegister.do==");
		LOG.debug("===========================");
		
		HttpSession session = req.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("sessionId");
		workspaceVO.setRegId(memberVO.getId());
		
		LOG.debug("==workspaceVO==" + workspaceVO);
		int flag = workspaceService.doInsertChk(workspaceVO);
		if(flag == 1) {
			LOG.debug("==채널등록 성공==");
		} else {
			LOG.debug("==채널등록 실패==");
			res.setStatus(404);
		}
	}
	
	// 채널 등록 페이지로 이동
	@RequestMapping(value = "workspace/moveToRegisterPage.do", method = RequestMethod.GET)
	public String moveToRegisterPage(HttpServletRequest req) {
		LOG.debug("===================================");
		LOG.debug("==workspace/moveToRegisterPage.do==");
		LOG.debug("===================================");
		
		return "workspace/register";		
	}
	
	// 채널 목록 페이지로 이동
	@RequestMapping(value = "workspace/moveToChannel.do", method = RequestMethod.GET)
	public String moveToChannel() {
		LOG.debug("===================================");
		LOG.debug("==workspace/moveToChannel.do==");
		LOG.debug("===================================");
		
		return "workspace/channel";		
	}
	
	// 채널 주제 리스트
	@RequestMapping(value = "workspace/doListingTopic.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doListingTopic() {
		LOG.debug("===============================");
		LOG.debug("==workspace/doListingTopic.do==");
		LOG.debug("===============================");
		
		List<WorkspaceVO> outList = workspaceService.doSelectListTopic();
		
		for(WorkspaceVO vo : outList) {
			LOG.debug("==outVO==" + vo);
		}
		
		Gson gson = new Gson();
    	String json = gson.toJson(outList);
		
		return json;
		
	}
	
	// 채널 리스트
	@RequestMapping(value = "workspace/doListing.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doListing() {
		LOG.debug("==========================");
		LOG.debug("==workspace/doListing.do==");
		LOG.debug("==========================");
		
		List<WorkspaceVO> outList = workspaceService.doSelectList();
		
		for(WorkspaceVO vo : outList) {
			LOG.debug("==outVO==" + vo);
		}
		
		Gson gson = new Gson();
    	String json = gson.toJson(outList);
		
		return json;
		
	}
}
