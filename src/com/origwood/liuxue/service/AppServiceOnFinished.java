package com.origwood.liuxue.service;

import com.origwood.liuxue.bean.Result;

public interface AppServiceOnFinished {
	/**
	 * �����ɹ�
	 * 
	 * @param object
	 *            �������ȡ�������ݣ�������һ���򵥶���Ҳ������һ��List
	 * @param result
	 *            �������
	 * 
	 */
	public void onSuccess(Result result, Object object);

	/**
	 * �����ɹ�
	 * 
	 * @param object
	 *            �������ȡ�������ݣ�������һ���򵥶���Ҳ������һ��List
	 */
	public void onSuccess(Object object);

	/**
	 * ����ʧ�ܣ��û������󲻷���Ҫ��
	 * 
	 * @param result
	 *            �������
	 */
	public void onFailed(Result result);

}
