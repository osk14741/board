package com.kkj.board.member;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.UUID;

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
import org.springframework.web.multipart.MultipartFile;

import com.kkj.board.media.MediaService;
import com.kkj.board.media.MediaVO;

@Controller
public class MemberController {
	
	final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired MemberService memberService;
	@Autowired MediaService mediaService;
	
	// profile 이미지 수정
	@RequestMapping(value = "member/doUpdateProfileImg.do", method = RequestMethod.POST)
	@ResponseBody
	public String doUpdateProfileImg(HttpServletRequest req, MultipartFile file) throws IOException {
		LOG.debug("================================");
		LOG.debug("==member/doUpdateProfileImg.do==");
		LOG.debug("================================");
				
		MultipartFile profileImgResized = mediaService.doResizeProfile(file);
		
		HttpSession session = req.getSession();
		
		MemberVO sessionId = (MemberVO) session.getAttribute("sessionId");
		
		LOG.debug("==sessionId==" + sessionId.getId());
		
		String uuid = UUID.randomUUID().toString();
		String keyNameProfile = "profileImg/" + sessionId.getId() + "/" + uuid + "_profile.jpg";
		
		LOG.debug("Profile image Upload to S3");
		mediaService.doFileUpload(keyNameProfile, profileImgResized);
		
		String profileUrl = mediaService.doFileDownload(keyNameProfile).toString();
		LOG.debug("==profileUrl==" + profileUrl);
		
		MediaVO mediaVO = new MediaVO();
		mediaVO.setDiv("10");
		mediaVO.setMemberId(sessionId.getId());
		mediaVO.setImg(profileUrl);
		
		session.setAttribute("sessionProfile", profileUrl);
		
		mediaService.doDelete(mediaVO);
		mediaService.doInsert(mediaVO);		
		
		return null;
	}
	
	
	// 회원 탈퇴
	@RequestMapping(value = "member/doDeleteUser.do", method = RequestMethod.POST)
	@ResponseBody
	public void doDeleteUser(MemberVO memberVO, HttpServletResponse res, HttpServletRequest req) {
		LOG.debug("==========================");
		LOG.debug("==member/doDeleteUser.do==");
		LOG.debug("==========================");
		
		int flag = memberService.doDelete(memberVO);
		if(flag == 1) {
			LOG.debug("==회원탈퇴 성공==");
		} else {
			LOG.debug("==회원탈퇴 실패==");
			res.setStatus(404);
		}
		
		req.getSession().invalidate();
	}
	
	// 로그아웃
	@RequestMapping(value = "member/logout.do", method = RequestMethod.GET)
	public String logout(HttpServletRequest req) {
		LOG.debug("====================");
		LOG.debug("==member/logout.do==");
		LOG.debug("====================");
		
		HttpSession session = req.getSession();
		session.invalidate();
		
		return "member/loginPage";
	}
	
	// 프로필 수정
	@RequestMapping(value = "member/doUpdateProfile.do", method = RequestMethod.POST)
	@ResponseBody
	public String doUpdateProfile(MemberVO memberVO, HttpServletRequest req) {
		LOG.debug("=============================");
		LOG.debug("==member/doUpdateProfile.do==");
		LOG.debug("=============================");
		
		HttpSession session = req.getSession();
		MemberVO sessionId = (MemberVO) session.getAttribute("sessionId");
		
		sessionId.setEmail(memberVO.getEmail());
		sessionId.setPassword(memberVO.getPassword());
		
		memberService.doUpdate(sessionId);
		
		session.setAttribute("sessionId", sessionId);
		
		return null;
	}
	
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
		
		LOG.debug("==memberVO==" + memberVO);
		
		try {
			MemberVO daoVO = memberService.doSelectOne(memberVO);
			
			if(!daoVO.getPassword().equals(memberVO.getPassword())) {
				LOG.debug("==login fail==");
				LOG.debug("==비밀번호가 틀림==");
				return "member/login_fail";
			}
			MediaVO mediaVO = new MediaVO();
			mediaVO.setMemberId(memberId);
			mediaVO.setDiv("10");
			mediaVO = mediaService.doSelectOne(mediaVO);
			httpSession.setAttribute("sessionId", daoVO);
			httpSession.setAttribute("sessionProfile", mediaVO.getImg());
			
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
