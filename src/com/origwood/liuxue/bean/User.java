package com.origwood.liuxue.bean;

import java.io.Serializable;

public class User implements Serializable {
	private String email;
	private String id;
	private String loginName;
	private String nickName;
	private int noteAmount;
	private int topicAmount;
	private int attentAmount;
	private int beAttentAmount;
	private int joinGroupAmount;
	private String sex;
	private String headImg;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getNoteAmount() {
		return noteAmount;
	}

	public void setNoteAmount(int noteAmount) {
		this.noteAmount = noteAmount;
	}

	public int getTopicAmount() {
		return topicAmount;
	}

	public void setTopicAmount(int topicAmount) {
		this.topicAmount = topicAmount;
	}

	public int getAttentAmount() {
		return attentAmount;
	}

	public void setAttentAmount(int attentAmount) {
		this.attentAmount = attentAmount;
	}

	public int getBeAttentAmount() {
		return beAttentAmount;
	}

	public void setBeAttentAmount(int beAttentAmount) {
		this.beAttentAmount = beAttentAmount;
	}

	public int getJoinGroupAmount() {
		return joinGroupAmount;
	}

	public void setJoinGroupAmount(int joinGroupAmount) {
		this.joinGroupAmount = joinGroupAmount;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

}
