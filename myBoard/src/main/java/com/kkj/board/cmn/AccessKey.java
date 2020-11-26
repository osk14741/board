package com.kkj.board.cmn;

import org.springframework.stereotype.Component;

@Component
public class AccessKey {

	final String ACCESS_KEY = "AKIA3I6CC67EO3R7MZON";
	final String SECRET_KEY = "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";

	public String getACCESS_KEY() {
		return ACCESS_KEY;
	}

	public String getSECRET_KEY() {
		return SECRET_KEY;
	}

}
