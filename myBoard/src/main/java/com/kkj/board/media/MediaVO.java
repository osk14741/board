package com.kkj.board.media;

import org.springframework.stereotype.Component;

@Component
public class MediaVO {

	private String seq;
	private String memberId;
	private String img;
	private String div;

	public MediaVO(String seq, String memberId, String img, String div) {
		super();
		this.seq = seq;
		this.memberId = memberId;
		this.img = img;
		this.div = div;
	}

	public MediaVO() {
		super();
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getDiv() {
		return div;
	}

	public void setDiv(String div) {
		this.div = div;
	}

	@Override
	public String toString() {
		return "MediaVO [seq=" + seq + ", memberId=" + memberId + ", img=" + img + ", div=" + div + "]";
	}
}
