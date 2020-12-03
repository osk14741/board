package com.kkj.board.board;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
import com.kkj.board.cmn.PageVO;
import com.kkj.board.member.MemberVO;

@Controller
public class BoardController {

	final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired BoardService boardService;
	
	// 보드 삭제하기
	@Auth
	@RequestMapping(value = "board/doDelete.do", method = RequestMethod.POST)
	@ResponseBody
	public String doDelete(BoardVO boardVO) {
		 
		LOG.debug("==board/doDelete.do==");
		
		LOG.debug("boardVO : "+boardVO);
		
		boardService.doDelete(boardVO);
		
		return null;
	}
	
	// 보드 수정하기
	@Auth
	@RequestMapping(value = "board/doUpdate.do", method = RequestMethod.GET)
	public ModelAndView doUpdate(@RequestParam("notice_content") String content,
			@RequestParam("board_seq") int boardSeq, 
			@RequestParam("title") String title,
			@RequestParam("board_header") String header,
			@RequestParam("board_readCnt") int readCount,
			@RequestParam("board_recommend") int recommend,
			@RequestParam("workspace_name") String workspaceName,
			HttpServletRequest req) {

		LOG.debug("content : " + content);

		HttpSession session = req.getSession();
		MemberVO sessionId = (MemberVO) session.getAttribute("sessionId");
		
		BoardVO boardVO = new BoardVO();

		boardVO.setHeader(header);
		boardVO.setTitle(title);
		boardVO.setContent(content);
		boardVO.setReadCount(readCount);
		boardVO.setRecommend(recommend);
		boardVO.setSeq(boardSeq);
		boardVO.setRegId(sessionId.getId());

		LOG.debug("==boardVO==" + boardVO);

		boardService.doUpdate(boardVO);

		ModelAndView mav = new ModelAndView();

		try {
			workspaceName = URLEncoder.encode(workspaceName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		mav.setViewName("redirect:/board/moveToBoardElement.do?whereToGo="+boardSeq+"&workspaceName=" + workspaceName);
//		mav.addObject("workspaceSeq", workspaceSeq);
		
		return mav;
	}
	
	// 수정 페이지로 이동
	@Auth
	@RequestMapping(value = "board/moveToUpdatePage.do", method = RequestMethod.POST)
	public ModelAndView moveToUpdatePage(@RequestParam("board_header") String boardHeader,
										@RequestParam("board_title") String boardTitle,
										@RequestParam("board_content") String boardContent,
										@RequestParam("board_readCnt") int boardReadCnt,
										@RequestParam("board_recommend") int boardRecommend,
										@RequestParam("board_seq") int boardSeq,
										@RequestParam("workspace_name") String workspaceName) {
		
		LOG.debug("==board/moveToUpdatePage.do==");
		
		BoardVO boardVO = new BoardVO();
		boardVO.setHeader(boardHeader);
		boardVO.setTitle(boardTitle);
		boardVO.setContent(boardContent);
		boardVO.setReadCount(boardReadCnt);
		boardVO.setRecommend(boardRecommend);
		boardVO.setSeq(boardSeq);
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("board/update_board");
		mav.addObject("boardVO", boardVO);
		mav.addObject("workspaceName", workspaceName);
		
		return mav;
	}
	
	// 보드 요소 하나 클릭하기
	@Auth
	@RequestMapping(value = "board/moveToBoardElement.do", method = RequestMethod.GET)
	public ModelAndView moveToBoardElement(@RequestParam("whereToGo") int boardSeq,
										@RequestParam("workspaceName") String workspaceName) {
		
		LOG.debug("==board/moveToBoardElement.do==");
		
		BoardVO boardVO = new BoardVO();
		boardVO.setSeq(boardSeq);
		
		boardVO = boardService.doSelectOne(boardVO);
		LOG.debug("==boardVO==" + boardVO);
		LOG.debug("workspaceName" + workspaceName);
		
		ModelAndView mav = new ModelAndView();
				
		mav.setViewName("board/board_detail");
		mav.addObject("boardVO", boardVO);
		mav.addObject("workspaceName", workspaceName);
		
		return mav;
	}
	
	
	// 보드 글쓰기
	@Auth
	@RequestMapping(value = "board/doInsert.do", method = RequestMethod.GET)
	public ModelAndView doInsert(@RequestParam("notice_content") String content,
						@RequestParam("workspaceSeq") int workspaceSeq,
						@RequestParam("title") String title,
						@RequestParam("workspaceName") String workspaceName,
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
		
		try {
			workspaceName = URLEncoder.encode(workspaceName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		ModelAndView mav = new ModelAndView();
				
		mav.setViewName("redirect:/workspace/moveToBoardPage.do?whereToGo="+workspaceName);
		mav.addObject("workspaceSeq", workspaceSeq);
				
		return mav;
	}
	
	// 보드 리스트
	@Auth
	@RequestMapping(value = "board/doSelectList.do", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String doSelectList(PageVO pageVO) {
		LOG.debug("=========================");
		LOG.debug("==board/doSelectList.do==");
		LOG.debug("=========================");
		
		List<BoardVO> outList = boardService.doSelectListWithPaging(pageVO);
		
		Gson gson = new Gson();
		String json = gson.toJson(outList);
		
		return json;
	}
	
	// 글쓰는 페이지로 이동
	@Auth
	@RequestMapping(value = "board/moveToWritePage.do", method = RequestMethod.GET)
	public ModelAndView moveToWritePage(@RequestParam("workspaceSeq") String workspaceSeq,
										@RequestParam("workspaceName") String workspaceName) {
		LOG.debug("==board/moveToWritePage.do==");

		LOG.debug("workspaceSeq : " + workspaceSeq);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/write_board");
		mav.addObject("workspaceSeq", workspaceSeq);
		mav.addObject("workspaceName", workspaceName);
		
		return mav;
	}
}
