package com.origwood.liuxue.bean;

public class Result {
	private String msg;
	private int subResultType;

	/**
	 * @param string ÏûÏ¢
	 */
	public Result(String string) {
		this.msg=string;
	}
	public Result() {
	}

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

}
