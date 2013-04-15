package com.origwood.liuxue.service;

import com.origwood.liuxue.bean.Result;

public interface AppServiceOnFinished {
	/**
	 * 操作成功
	 * 
	 * @param object
	 *            从网络获取到的数据，可能是一个简单对象，也可能是一个List
	 * @param result
	 *            操作结果
	 * 
	 */
	public void onSuccess(Result result, Object object);

	/**
	 * 操作成功
	 * 
	 * @param object
	 *            从网络获取到的数据，可能是一个简单对象，也可能是一个List
	 */
	public void onSuccess(Object object);

	/**
	 * 操作失败，用户的请求不符合要求。
	 * 
	 * @param result
	 *            操作结果
	 */
	public void onFailed(Result result);

}
