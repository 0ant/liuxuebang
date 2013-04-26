package com.origwood.liuxue.util;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.origwood.liuxue.BuildConfig;

/**
 * ʹ��fastjson���� http://www.oschina.net/p/fastjson
 * <p>
 * ��������ǽ��������ؽ����items����������
 * </p>
 * 
 * @author wanggang
 * @date�� 2013-4-26 email: 315331371@qq.com
 */
public class Jsoner {
	public static <T> T parseObject(String text, Class<T> clazz) {

		return JSON.parseObject(JSON.parseObject(text).getString("items"),
				clazz);
	}

	public static <T> List<T> parseArray(String text, Class<T> clazz) {
		return JSON
				.parseArray(JSON.parseObject(text).getString("items"), clazz);
	}

	public static <T> T parseObjectAgency(String text, Class<T> clazz) {
		if (BuildConfig.DEBUG) {
			Loger.i("JSON:" + text);
		}
		return JSON.parseObject(text, clazz);
	}

	public static <T> List<T> parseArrayAgency(String text, Class<T> clazz) {
		return JSON.parseArray(text, clazz);
	}
}
