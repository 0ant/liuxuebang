package com.origwood.liuxue.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Group implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7930259034267530034L;
	private String id;
	private String img;
	private int isHot;
	private int isOfficial;
	private String name;
	private int topicAmount;
	private int topicReplyAmount;
	private int userAmount;
	private List<User> lastReplyTopicUsers = new ArrayList<User>();

	public List<User> getLastReplyTopicUsers() {
		return lastReplyTopicUsers;
	}

	public void setLastReplyTopicUsers(List<User> lastReplyTopicUsers) {
		this.lastReplyTopicUsers = lastReplyTopicUsers;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getIsHot() {
		return isHot;
	}

	public void setIsHot(int isHot) {
		this.isHot = isHot;
	}

	public int getIsOfficial() {
		return isOfficial;
	}

	public void setIsOfficial(int isOfficial) {
		this.isOfficial = isOfficial;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTopicAmount() {
		return topicAmount;
	}

	public void setTopicAmount(int topicAmount) {
		this.topicAmount = topicAmount;
	}

	public int getTopicReplyAmount() {
		return topicReplyAmount;
	}

	public void setTopicReplyAmount(int topicReplyAmount) {
		this.topicReplyAmount = topicReplyAmount;
	}

	public int getUserAmount() {
		return userAmount;
	}

	public void setUserAmount(int userAmount) {
		this.userAmount = userAmount;
	}

	@Override
	public String toString() {
		return "Group [id=" + id + ", img=" + img + ", isHot=" + isHot
				+ ", isOfficial=" + isOfficial + ", name=" + name
				+ ", topicAmount=" + topicAmount + ", topicReplyAmount="
				+ topicReplyAmount + ", userAmount=" + userAmount
				+ ", lastReplyTopicUsers=" + lastReplyTopicUsers + "]";
	}

}
