package com.kkj.board.member;

import org.springframework.stereotype.Component;

@Component
public class MemberVO {
	private String id;
	private String password;
	private String name;
	private int gender;
	private String email;
	private int authority;
	private int loginCount;
	private int recommendCount;
	private String grade;
	private String regDt;
	private String modDt;

	public MemberVO(String id, String password, String name, int gender, String email, int authority, int loginCount,
			int recommendCount, String grade, String regDt, String modDt) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.email = email;
		this.authority = authority;
		this.loginCount = loginCount;
		this.recommendCount = recommendCount;
		this.grade = grade;
		this.regDt = regDt;
		this.modDt = modDt;
	}

	public MemberVO() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAuthority() {
		return authority;
	}

	public void setAuthority(int authority) {
		this.authority = authority;
	}

	public int getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(int loginCount) {
		this.loginCount = loginCount;
	}

	public int getRecommendCount() {
		return recommendCount;
	}

	public void setRecommendCount(int recommendCount) {
		this.recommendCount = recommendCount;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
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

	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", password=" + password + ", name=" + name + ", gender=" + gender + ", email="
				+ email + ", authority=" + authority + ", loginCount=" + loginCount + ", recommendCount="
				+ recommendCount + ", grade=" + grade + ", regDt=" + regDt + ", modDt=" + modDt + "]";
	}
}
