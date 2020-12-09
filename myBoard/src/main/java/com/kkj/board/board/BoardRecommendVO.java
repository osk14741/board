package com.kkj.board.board;

import org.springframework.stereotype.Component;

@Component
public class BoardRecommendVO {

	private int seq;
	private int boardSeq;
	private String recommendId;

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getBoardSeq() {
		return boardSeq;
	}

	public void setBoardSeq(int boardSeq) {
		this.boardSeq = boardSeq;
	}

	public String getRecommendId() {
		return recommendId;
	}

	public void setRecommendId(String recommendId) {
		this.recommendId = recommendId;
	}

	@Override
	public String toString() {
		return "BoardRecommendVO [seq=" + seq + ", boardSeq=" + boardSeq + ", recommendId=" + recommendId + "]";
	}

}
