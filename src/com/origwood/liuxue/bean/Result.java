package com.origwood.liuxue.bean;

public class Result {
	protected String msg;
	protected int subResultType;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getSubResultType() {
		return subResultType;
	}

	public void setSubResultType(int subResultType) {
		this.subResultType = subResultType;
	}

	@Override
	public String toString() {
		return "Result [msg=" + msg + ", subResultType=" + subResultType + "]";
	}

}
