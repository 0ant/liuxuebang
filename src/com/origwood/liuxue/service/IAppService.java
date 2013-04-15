package com.origwood.liuxue.service;

import android.content.Context;

public interface IAppService {
	/**
	 * �û���¼
	 * 
	 * @param username
	 *            �û���
	 * @param password
	 *            ����
	 * @param onFinished
	 *            ��ɺ�Ļص��ӿ�
	 * @param context
	 *            ���������ģ�����cookie��ʱ����Ҫ
	 */
	public void login(String username, String password,
			AppServiceOnFinished onFinished, Context context);

	/**
	 * ��ȡ�û���Ϣ
	 * 
	 * @param onFinished
	 */
	public void getUserInfo(AppServiceOnFinished onFinished);

	// public void checkIsRegister(AppServiceOnFinished onFinished);
}
