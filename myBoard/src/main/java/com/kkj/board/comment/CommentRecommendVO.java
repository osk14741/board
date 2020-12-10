package com.kkj.board.comment;

import org.springframework.stereotype.Component;

@Component
public class CommentRecommendVO {

	private int seq;
	private int commentSeq;
	private String recommendId;

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getCommentSeq() {
		return commentSeq;
	}

	public void setCommentSeq(int commentSeq) {
		this.commentSeq = commentSeq;
	}

	public String getRecommendId() {
		return recommendId;
	}

	public void setRecommendId(String recommendId) {
		this.recommendId = recommendId;
	}

	@Override
	public String toString() {
		return "CommentRecommendVO [seq=" + seq + ", commentSeq=" + commentSeq + ", recommendId=" + recommendId + "]";
	}

}
