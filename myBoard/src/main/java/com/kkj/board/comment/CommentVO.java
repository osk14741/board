package com.kkj.board.comment;

import org.springframework.stereotype.Component;

@Component
public class CommentVO {

	private int seq;
	private int boardSeq;
	private int parentSeq;
	private String content;
	private String regId;
	private String regDt;
	private String modDt;
	private int isModded;
	private int recommend;

	public CommentVO() {
		super();
	}

	public CommentVO(int seq, int boardSeq, int parentSeq, String content, String regId, String regDt, String modDt,
			int isModded, int recommend) {
		super();
		this.seq = seq;
		this.boardSeq = boardSeq;
		this.parentSeq = parentSeq;
		this.content = content;
		this.regId = regId;
		this.regDt = regDt;
		this.modDt = modDt;
		this.isModded = isModded;
		this.recommend = recommend;
	}

	
	

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

	public int getParentSeq() {
		return parentSeq;
	}

	public void setParentSeq(int parentSeq) {
		this.parentSeq = parentSeq;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getModDt() {
		return modDt;
	}

	public void setModDt(String modDt) {
		this.modDt = modDt;
	}

	public int getIsModded() {
		return isModded;
	}

	public void setIsModded(int isModded) {
		this.isModded = isModded;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}

	@Override
	public String toString() {
		return "CommentVO [seq=" + seq + ", boardSeq=" + boardSeq + ", parentSeq=" + parentSeq + ", content=" + content
				+ ", regId=" + regId + ", regDt=" + regDt + ", modDt=" + modDt + ", isModded=" + isModded
				+ ", recommend=" + recommend + "]";
	}

	
}
