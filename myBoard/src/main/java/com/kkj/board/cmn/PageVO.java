package com.kkj.board.cmn;

import org.springframework.stereotype.Component;

@Component
public class PageVO {

	private String searchWord;
	private int pageSize;
	private int pageNum;
	private int div;
	/**
	 * T : 제목, W : 작성자, C : 내용
	 */
	private String searchDiv;
	private int totalBoardVO;
	private int workspaceSeq;
	private int boardSeq;

	public int getBoardSeq() {
		return boardSeq;
	}

	public void setBoardSeq(int boardSeq) {
		this.boardSeq = boardSeq;
	}

	public int getWorkspaceSeq() {
		return workspaceSeq;
	}

	public void setWorkspaceSeq(int workspaceSeq) {
		this.workspaceSeq = workspaceSeq;
	}

	public int getTotalBoardVO() {
		return totalBoardVO;
	}

	public void setTotalBoardVO(int totalBoardVO) {
		this.totalBoardVO = totalBoardVO;
	}

	public String getSearchDiv() {
		return searchDiv;
	}

	public void setSearchDiv(String searchDiv) {
		this.searchDiv = searchDiv;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getDiv() {
		return div;
	}

	public void setDiv(int div) {
		this.div = div;
	}

	public String[] getTypeArr() {

		return searchDiv == null ? new String[] {} : searchDiv.split("");
	}

}
