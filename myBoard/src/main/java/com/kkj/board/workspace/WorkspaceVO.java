package com.kkj.board.workspace;

import org.springframework.stereotype.Component;

@Component
public class WorkspaceVO {
	private int seq;
	private String name;
	private String topic;
	private int div;
	private String regId;
	private String regDt;

	public WorkspaceVO() {
		super();
	}

	public WorkspaceVO(int seq, String name, String topic, int div, String regId, String regDt) {
		super();
		this.seq = seq;
		this.name = name;
		this.topic = topic;
		this.div = div;
		this.regId = regId;
		this.regDt = regDt;
	}

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

	@Override
	public String toString() {
		return "WorkspaceVO [seq=" + seq + ", name=" + name + ", topic=" + topic + ", div=" + div + ", regId=" + regId
				+ ", regDt=" + regDt + "]";
	}

}
