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
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.kkj.board.board.BoardService;
import com.kkj.board.cmn.Auth;
import com.kkj.board.cmn.PageVO;
import com.kkj.board.member.MemberVO;

@Controller
public class WorkspaceController {

	final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired WorkspaceService workspaceService;
	@Autowired BoardService boardService;
	
	// 게시판 페이지로 이동
	@Auth
	@RequestMapping(value = "workspace/moveToBoardPage.do", method = RequestMethod.GET)
	public ModelAndView moveToBoardPage(@RequestParam("whereToGo") String workspaceName,
									@RequestParam("search_div") String searchDiv,
									@RequestParam("search_word") String searchWord,
									HttpServletRequest req) {
		LOG.debug("================================");
		LOG.debug("==workspace/moveToBoardPage.do==");
		LOG.debug("================================");
		
		int activePage;
		
		LOG.debug("==workspaceName==" + workspaceName);
		WorkspaceVO workspaceVO = new WorkspaceVO();
		workspaceVO.setName(workspaceName);
		
		workspaceVO = workspaceService.doSelectOneByName(workspaceVO);
		
		PageVO pageVO = new PageVO();
		pageVO.setPageSize(10);
		pageVO.setWorkspaceSeq(workspaceVO.getSeq());
		pageVO.setPageNum(1);
		pageVO.setSearchDiv(searchDiv);
		pageVO.setSearchWord(searchWord);
		
		
		int count = boardService.doCountTotalVO(pageVO);
		
		LOG.debug("==workspaceVO==" + workspaceVO);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/board");
		mav.addObject("workspaceSeq", workspaceVO.getSeq());
		mav.addObject("workspaceName", workspaceName);
		mav.addObject("totalBoardCount", count);
		mav.addObject("whereToGo", workspaceName);
		mav.addObject("searchDiv", searchDiv);
		mav.addObject("searchWord", searchWord);
		
		// 페이지 넘버
		try {
			String pageNum = req.getParameter("page_num");
			LOG.debug("pageNum : " + pageNum);
			activePage = Integer.parseInt(pageNum);
			LOG.debug("activePage : " + activePage);
			LOG.debug("pageNum is not null");
			mav.addObject("pageNumFromC", activePage);
		} catch (NumberFormatException e) {
			LOG.debug("pageNum is null");
			activePage = 1;
			LOG.debug("activePage : " + activePage);
			mav.addObject("pageNumFromC", activePage);
		}
		
		// active Page -> pageNumFromC
		// 시작페이지(minPage), 끝페이지(maxPage), 총 페이지(pageSize) 구하기
		// 총 갯수(count), pageSize(default:10), minPage(), maxPage() 
		double tmp = (double) count / (double) 10;
		int maxPage = (int) Math.ceil(tmp);
		LOG.debug("activePage : " + activePage);
		
		if ((activePage == 1 || activePage == 2) && maxPage <= 5) {
			mav.addObject("minPage", 1);
			mav.addObject("maxPage", maxPage);
			LOG.debug("minPage : " + 1 + ", maxPage : " + maxPage);
			return mav;
		} else if ((activePage == 1 || activePage == 2) && maxPage > 5) {
			mav.addObject("minPage", 1);
			mav.addObject("maxPage", 5);
			LOG.debug("minPage : " + 1 + ", maxPage : " + 5);
			return mav;
		} else if ((activePage + 1) == maxPage || activePage == maxPage) {
			if(activePage == 3 || activePage == 4 || activePage == 5) {
				mav.addObject("minPage", 1);
			} else {
				mav.addObject("minPage", (maxPage - 4));
			}
			mav.addObject("maxPage", maxPage);
			LOG.debug("minPage : " + (maxPage - 4) + ", maxPage : " + maxPage);
			return mav;
		} else {
			mav.addObject("minPage", (activePage - 2));
			mav.addObject("maxPage", (activePage + 2));
			LOG.debug("minPage : " + (activePage - 2) + ", maxPage : " + (activePage + 2));
			return mav;
		}
	}
	
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
	@Auth
	@RequestMapping(value = "workspace/moveToRegisterPage.do", method = RequestMethod.GET)
	public String moveToRegisterPage(HttpServletRequest req) {
		LOG.debug("===================================");
		LOG.debug("==workspace/moveToRegisterPage.do==");
		LOG.debug("===================================");
		
		return "workspace/register";		
	}
	
	// 채널 목록 페이지로 이동
	@Auth
	@RequestMapping(value = "workspace/moveToChannel.do", method = RequestMethod.GET)
	public String moveToChannel() {
		LOG.debug("===================================");
		LOG.debug("==workspace/moveToChannel.do==");
		LOG.debug("===================================");
		
		return "workspace/channel";		
	}
	
	// 채널 주제 리스트
	@Auth
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
	@Auth
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
