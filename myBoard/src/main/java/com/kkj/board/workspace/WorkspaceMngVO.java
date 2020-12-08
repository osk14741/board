package com.kkj.board.workspace;

import org.springframework.stereotype.Component;

@Component
public class WorkspaceMngVO {

	private int seq;
	private String name;
	private String topic;
	private String regDt;
	private int boardCount;
	private int regBoardCount;

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public int getBoardCount() {
		return boardCount;
	}

	public void setBoardCount(int boardCount) {
		this.boardCount = boardCount;
	}

	public int getRegBoardCount() {
		return regBoardCount;
	}

	public void setRegBoardCount(int regBoardCount) {
		this.regBoardCount = regBoardCount;
	}

	@Override
	public String toString() {
		return "WorkspaceMngVO [seq=" + seq + ", name=" + name + ", topic=" + topic + ", regDt=" + regDt
				+ ", boardCount=" + boardCount + ", regBoardCount=" + regBoardCount + "]";
	}

}
