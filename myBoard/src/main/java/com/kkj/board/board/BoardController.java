package com.kkj.board.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import com.kkj.board.cmn.Auth;
import com.kkj.board.member.MemberVO;

@Controller
public class BoardController {

	final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired BoardService boardService;
	
	// 보드 글쓰기
	@Auth
	@RequestMapping(value = "board/doInsert.do", method = RequestMethod.GET)
	public ModelAndView doInsert(@RequestParam("notice_content") String content,
						@RequestParam("workspaceSeq") int workspaceSeq,
						@RequestParam("title") String title,
						HttpServletRequest req) {
		
		LOG.debug("content : " + content);
		
		BoardVO boardVO = new BoardVO();
		HttpSession session = req.getSession();
		MemberVO sessionId = (MemberVO) session.getAttribute("sessionId");
		
		boardVO.setHeader("temp header");
		boardVO.setTitle(title);
		boardVO.setDiv(10);
		boardVO.setRegId(sessionId.getId());
		boardVO.setContent(content);
		boardVO.setWorkspaceSeq(workspaceSeq);
		
		LOG.debug("==boardVO==" + boardVO);
		
		boardService.doInsert(boardVO);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/board");
		mav.addObject("workspaceSeq", workspaceSeq);
				
		return mav;
	}
	
	// 보드 리스트
	@Auth
	@RequestMapping(value = "board/doSelectList.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doSelectList(BoardVO boardVO) {
		LOG.debug("=========================");
		LOG.debug("==board/doSelectList.do==");
		LOG.debug("=========================");
		
		List<BoardVO> outList = boardService.doSelectList(boardVO);
		
		Gson gson = new Gson();
		String json = gson.toJson(outList);
		
		return json;
	}
	
	// 글쓰는 페이지로 이동
	@Auth
	@RequestMapping(value = "board/moveToWritePage.do", method = RequestMethod.GET)
	public ModelAndView moveToWritePage(@RequestParam("workspaceSeq") String workspaceSeq) {
		LOG.debug("==board/moveToWritePage.do==");

		LOG.debug("workspaceSeq : " + workspaceSeq);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/write_board");
		mav.addObject("workspaceSeq", workspaceSeq);
		
		return mav;
	}
}
