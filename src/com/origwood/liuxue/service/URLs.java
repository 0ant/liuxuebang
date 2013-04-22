package com.origwood.liuxue.service;

public class URLs {
	private static final String HOST = "http://42.96.136.159/";
	private static final String ACTION = "app/";
	public static final String LOGIN = HOST + ACTION + "subUserLogin?";
	public static final String USERINFO = HOST + ACTION + "user/detail";
	public static final String CHECKISREGISTER = HOST + ACTION
			+ "validateLoginNameUsed?";
	public static final String REGISTER = HOST + ACTION + "subUserRegister?";
	public static final String MODIFYPWD = HOST + ACTION + "user/mofifyPwd";
	public static final String INFOSETTING = HOST + ACTION
			+ "user/subInfoSetting";
	public static final String GETUSERALLSTAGE = HOST + ACTION
			+ "getUserAllStage";
}
