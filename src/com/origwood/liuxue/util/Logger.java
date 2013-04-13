package com.origwood.liuxue.util;

public class Logger {
	private final static String TAG = "liuxuebang";

	public static void i(String message) {
		android.util.Log.i(TAG, message);
	}

	public static void e(String message) {
		android.util.Log.e(TAG, message);
	}

	/**
	 * 打印从网络获取的内容
	 * 
	 * @param message
	 */
	public static void iInternet(String message) {
		android.util.Log.i(TAG + " Internet", message);
	}

	public static void iListener(String message) {
		android.util.Log.i(TAG + " Listerner", message);
	}
}
