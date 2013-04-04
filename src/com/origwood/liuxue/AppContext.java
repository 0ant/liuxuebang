package com.origwood.liuxue;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

/**
 * 配置应用的全局属性，提供应用访问网络数据的接口
 * 
 * @author wanggang
 * @date： 2013-3-31 email: 315331371@qq.com
 */
public class AppContext extends Application {
	public static final int NETTYPE_WIFI = 0x01;
	public static final int NETTYPE_CMWAP = 0x02;
	public static final int NETTYPE_CMNET = 0x03;

	public static final int PAGE_SIZE = 20;// 默认分页大小
	private static final int CACHE_TIME = 60 * 60000;// 缓存失效时间
	private boolean login = false; // 登录状态
	private int loginUid = 0; // 登录用户的id

	@Override
	public void onCreate() {
		super.onCreate();
		// 注册App异常崩溃处理器
		Thread.setDefaultUncaughtExceptionHandler(AppException
				.getAppExceptionHandler());
	}

	/**
	 * 获取App安装包信息
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
