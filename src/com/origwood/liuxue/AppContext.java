package com.origwood.liuxue;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

/**
 * ����Ӧ�õ�ȫ�����ԣ��ṩӦ�÷����������ݵĽӿ�
 * 
 * @author wanggang
 * @date�� 2013-3-31 email: 315331371@qq.com
 */
public class AppContext extends Application {
	public static final int NETTYPE_WIFI = 0x01;
	public static final int NETTYPE_CMWAP = 0x02;
	public static final int NETTYPE_CMNET = 0x03;

	public static final int PAGE_SIZE = 20;// Ĭ�Ϸ�ҳ��С
	private static final int CACHE_TIME = 60 * 60000;// ����ʧЧʱ��
	private boolean login = false; // ��¼״̬
	private int loginUid = 0; // ��¼�û���id

	@Override
	public void onCreate() {
		super.onCreate();
		// ע��App�쳣����������
		Thread.setDefaultUncaughtExceptionHandler(AppException
				.getAppExceptionHandler());
	}

	/**
	 * ��ȡApp��װ����Ϣ
	 * 
	 * @return
	 */
	public PackageInfo getPackageInfo() {
		PackageInfo info = null;
		try {
			info = getPackageManager().getPackageInfo(getPackageName(), 0);
		} catch (NameNotFoundException e) {
			e.printStackTrace(System.err);
		}
		if (info == null)
			info = new PackageInfo();
		return info;
	}
}
