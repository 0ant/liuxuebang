package com.origwood.liuxue.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResultGroupList extends Result implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4633550730157251541L;
	private List<Group> items = new ArrayList<Group>();

	public List<Group> getItems() {
		return items;
	}

	public void setItems(List<Group> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "ResultGroupList [items=" + items + ", msg=" + msg
				+ ", subResultType=" + subResultType + "]";
	}

}
