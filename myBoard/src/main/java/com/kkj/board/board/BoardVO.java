package com.kkj.board.board;

import org.springframework.stereotype.Component;

@Component
public class BoardVO {
	private int seq;
	private String header;
	private String title;
	private String content;
	private int readCount;
	private int recommend;
	private int div;
	private String regId;
	private String regDt;
	private String modDt;
	private int isModded;
	private int workspaceSeq;

	public BoardVO() {
		super();
	}

	public BoardVO(int seq, String header, String title, String content, int readCount, int recommend, int div,
			String regId, String regDt, String modDt, int isModded) {
		super();
		this.seq = seq;
		this.header = header;
		this.title = title;
		this.content = content;
		this.readCount = readCount;
		this.recommend = recommend;
		this.div = div;
		this.regId = regId;
		this.regDt = regDt;
		this.modDt = modDt;
		this.isModded = isModded;
	}

	public int getWorkspaceSeq() {
		return workspaceSeq;
	}

	public void setWorkspaceSeq(int workspaceSeq) {
		this.workspaceSeq = workspaceSeq;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}

	public int getDiv() {
		return div;
	}

	public void setDiv(int div) {
		this.div = div;
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

	@Override
	public String toString() {
		return "BoardVO [seq=" + seq + ", header=" + header + ", title=" + title + ", content=" + content
				+ ", readCount=" + readCount + ", recommend=" + recommend + ", div=" + div + ", regId=" + regId
				+ ", regDt=" + regDt + ", modDt=" + modDt + ", isModded=" + isModded + ", workspaceSeq=" + workspaceSeq
				+ "]";
	}

	

}
