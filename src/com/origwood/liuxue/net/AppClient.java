package com.origwood.liuxue.net;

/**
 * �ͻ��˽ӿڣ����ڷ����������ݣ���Ҫ�Ǳ�AppContext�����
 * 
 * @author wanggang
 * @date�� 2013-3-31 email: 315331371@qq.com
 */
public class AppClient {
	public static final String UTF_8 = "UTF-8";
	public static final String DESC = "descend";
	public static final String ASC = "ascend";

	private final static int TIMEOUT_CONNECTION = 20000;
	private final static int TIMEOUT_SOCKET = 20000;
	private final static int RETRY_TIME = 3;

	private static String appCookie;
	private static String appUserAgent;
}
