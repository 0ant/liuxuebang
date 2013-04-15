package com.origwood.liuxue.service;

import android.content.Context;

public interface IAppService {
	/**
	 * 用户登录
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param onFinished
	 *            完成后的回调接口
	 * @param context
	 *            程序上下文，保存cookie的时候需要
	 */
	public void login(String username, String password,
			AppServiceOnFinished onFinished, Context context);

	/**
	 * 获取用户信息
	 * 
	 * @param onFinished
	 */
	public void getUserInfo(AppServiceOnFinished onFinished);

	// public void checkIsRegister(AppServiceOnFinished onFinished);
}
