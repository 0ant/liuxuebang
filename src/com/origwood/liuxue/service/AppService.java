package com.origwood.liuxue.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedHashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.google.inject.Singleton;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.origwood.liuxue.AppContext;
import com.origwood.liuxue.bean.Result;
import com.origwood.liuxue.bean.ResultGroup;
import com.origwood.liuxue.bean.ResultGroupList;
import com.origwood.liuxue.bean.User;
import com.origwood.liuxue.common.StringUtils;
import com.origwood.liuxue.util.Json2Bean;
import com.origwood.liuxue.util.Jsoner;
import com.origwood.liuxue.util.Loger;

/**
 * ����ҵ�񷽷�
 * 
 * @author wanggang
 * @date�� 2013-4-14 email: 315331371@qq.com
 */
@Singleton
public class AppService {
	public static final int NETTYPE_WIFI = 0x01;
	public static final int NETTYPE_CMWAP = 0x02;
	public static final int NETTYPE_CMNET = 0x03;
	public static final int PAGE_SIZE = 20;// Ĭ�Ϸ�ҳ��С
	private static final int CACHE_TIME = 60 * 60000;// ����ʧЧʱ��
	private Context context;
	private static final String DUG_TAG = "AppService";
	private static Result defaultOnFailureResult;
	private static AsyncHttpClient client = new AsyncHttpClient();

	public AppService() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AppService(Context context) {
		super();
		this.context = context;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public Result getDefaultOnFailureResult() {
		if (defaultOnFailureResult == null) {
			defaultOnFailureResult = new Result();
			defaultOnFailureResult.setMsg("������������");
		}
		return defaultOnFailureResult;
	}

	public static void stop(Context context, boolean b) {
		client.cancelRequests(context, b);
	}

	/**
	 * �����޸�
	 * 
	 * @param newPwd
	 * @param oldPwd
	 * @param a
	 * @param context
	 */
	public void modifyPwd(String newPwd, String oldPwd,
			final AppServiceOnFinished onFinished, final Context context) {
		RequestParams params = new RequestParams();
		params.put("newPwd", newPwd);
		params.put("oldPwd", oldPwd);
		Log.d(DUG_TAG, URLs.MODIFYPWD);
		client.get(URLs.MODIFYPWD, params, new AsyncHttpResponseHandler() {

			@Override
			public void onFailure(Throwable arg0, String arg1) {
				onFinished.onFailed(getDefaultOnFailureResult());
			}

			@Override
			public void onSuccess(String response) {
				Result result = Json2Bean.getResult(response);
				if (result.getSubResultType() == 0) {
					onFinished.onFailed(result);
				} else {
					PersistentCookieStore myCookieStore = new PersistentCookieStore(
							context);
					client.setCookieStore(null);
					client.setCookieStore(myCookieStore);
					onFinished.onSuccess(result);
				}
			}

		});
	}

	public static Object getUserById(String id, final AppServiceOnFinished a) {

		client.get("http://42.96.136.159/app/subUserLogin",
				new AsyncHttpResponseHandler() {
					@Override
					public void onSuccess(String response) {
						Result result = Json2Bean.getResult(response);
						if (result.getSubResultType() == 0) {
							a.onFailed(result);
						} else {
							String str = "wanggang";
							a.onSuccess(str);
						}
					}

				});

		return null;
	}

	/**
	 * �û���¼
	 * 
	 * @param username
	 *            �û���
	 * @param password
	 *            ����
	 * @param a
	 *            ��ɺ�ص��ӿ�
	 * @param context
	 *            �����Ķ���
	 */
	public void login(String username, String password,
			final AppServiceOnFinished onFinished, final Context context) {
		RequestParams params = new RequestParams();
		params.put("loginName", username);
		params.put("password", password);

		client.get(URLs.LOGIN, params, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(String response) {

				Result result = Json2Bean.getResult(response);
				if (result.getSubResultType() == 0) {
					onFinished.onFailed(result);

				} else {
					PersistentCookieStore myCookieStore = new PersistentCookieStore(
							context);
					client.setCookieStore(myCookieStore);
					onFinished.onSuccess(result);
				}
			}

			@Override
			public void onFailure(Throwable arg0, String arg1) {

				onFinished.onFailed(getDefaultOnFailureResult());
			}

		});
	}

	/**
	 * ����û���Ϣ
	 * 
	 * @param onFinished
	 */
	public void getUserInfo(final AppServiceOnFinished onFinished) {

		client.get(URLs.USERINFO, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(String response) {
				Result result = Json2Bean.getResult(response);
				if (result.getSubResultType() == 0) {
					onFinished.onFailed(result);
				} else {
					User user = Json2Bean.getBeanFromHasResult(response,
							User.class);
					onFinished.onSuccess(user);
				}
			}

		});
	}

	/**
	 * �û�ע��
	 * 
	 * @param username
	 * @param password
	 * @param onFinished
	 * @param context
	 */
	public void register(final String username, final String password,
			final AppServiceOnFinished onFinished, final Context context) {
		RequestParams params = new RequestParams();
		params.put("loginName", username);
		params.put("password", password);

		client.get(URLs.REGISTER, params, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(String response) {

				Result result = Json2Bean.getResult(response);
				if (result.getSubResultType() == 0) {
					onFinished.onFailed(result);

				} else {
					Toast.makeText(context, "ע��ɹ�", Toast.LENGTH_SHORT).show();
					login(username, password, onFinished, context);

				}
			}

			@Override
			public void onFailure(Throwable arg0, String arg1) {

				onFinished.onFailed(getDefaultOnFailureResult());
			}

		});
	}

	/**
	 * ����û����Ƿ�ע��
	 * 
	 * @param username
	 *            �û���
	 * @param onFinished
	 *            ���֮��Ļص��ӿ�
	 */
	public void checkIsRegister(String username,
			final AppServiceOnFinished onFinished) {

		RequestParams params = new RequestParams();
		params.put("loginName", username);
		client.get(URLs.CHECKISREGISTER, params,
				new AsyncHttpResponseHandler() {
					@Override
					public void onSuccess(String response) {
						Result result = Json2Bean.getResult(response);
						if (result.getSubResultType() == 0) {
							onFinished.onFailed(result);
						} else {
							onFinished.onSuccess(null);
						}
					}
				});
	}

	/**
	 * ���¸�������
	 * 
	 * @param usericon
	 * @param sex
	 * @param nickname
	 * @param stage
	 * @param phone
	 * @param onFinished
	 * @param context
	 */

	public void subInfoSetting(File usericon, String sex, String nickname,
			String stage, String phone, final AppServiceOnFinished onFinished,
			final Context context) {
		Log.i(DUG_TAG, sex + stage);
		RequestParams params = new RequestParams();
		// byte[] icon = ImageTools.getInstance().Drawable2Bytes(usericon);
		// params.put("headImgFile", new ByteArrayInputStream(icon),
		// "icon.png");
		try {
			params.put("headImgFile", usericon);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		params.put("sex", sex);
		params.put("nickName", nickname);
		params.put("stage", stage);
		params.put("mobilePhone", phone);
		client.post(URLs.INFOSETTING, params, new AsyncHttpResponseHandler() {

			@Override
			public void onFailure(Throwable arg0, String arg1) {
				onFinished.onFailed(getDefaultOnFailureResult());
			}

			@Override
			public void onSuccess(String response) {
				Log.i(DUG_TAG, response);
				Result result = Json2Bean.getResult(response);
				if (result.getSubResultType() == 0) {
					onFinished.onFailed(result);
				} else {
					onFinished.onSuccess(null);
				}
			}

		});
	}

	/**
	 * ��ȡ������ѧ�׶�
	 * 
	 * @param context
	 * @param onFinished
	 */
	public void getUserAllStage(final Context context,
			final AbsAppServiceOnFinished onFinished) {
		final Result result = new Result();
		client.get(URLs.GETUSERALLSTAGE, new AsyncHttpResponseHandler() {
			@Override
			public void onFailure(Throwable arg0, String arg1) {
				onFinished.onFailed(getDefaultOnFailureResult());
			}

			@Override
			public void onSuccess(String content) {
				Log.i(DUG_TAG, content);
				try {
					JSONObject jsonObject = new JSONObject(content);
					int subResultType = jsonObject.optInt("subResultType");
					if (subResultType == 1) {
						LinkedHashMap<String, String> values = new LinkedHashMap<String, String>();
						values.clear();
						JSONArray jsonArray = jsonObject.getJSONArray("items");
						for (int i = 0; i < jsonArray.length(); i++) {
							JSONObject obj = jsonArray.getJSONObject(i);
							String name = obj.getString("name");
							String value = obj.getString("value");
							values.put(name, value);
							Log.i(DUG_TAG, name + "===" + value);
						}
						onFinished.onSuccess(values);
						return;
					}
					result.setMsg("�������쳣");
					onFinished.onFailed(result);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * ��ð��б�
	 * 
	 * @param onFinished
	 * @param pageIndex
	 * @param isRefresh
	 */
	public void getAllGroup(final AbsAppServiceOnFinished onFinished,
			int pageIndex, boolean isRefresh) {

		final String key = "AllGroup_" + pageIndex + "_" + PAGE_SIZE;
		if (isNetworkConnected() && (!isReadDataCache(key) || isRefresh)) {
			RequestParams params = new RequestParams();
			params.put("page", pageIndex + "");
			params.put("pageSize", PAGE_SIZE + "");
			params.put("sortField", "DATE");
			client.post(URLs.GROUP_ALLGROUP, params,
					new AsyncHttpResponseHandler() {

						@Override
						public void onFailure(Throwable arg0, String arg1) {

							onFinished.onFailed(getDefaultOnFailureResult());
						}

						@Override
						public void onSuccess(String arg0) {

							ResultGroupList data = Jsoner.parseObjectAgency(
									arg0, ResultGroupList.class);
							// ���浽����
							saveObject(data, key);
							onFinished.onSuccess(data);
						}

					});
		} else {
			// �ӻ����ȡ
			ResultGroupList data = (ResultGroupList) readObject(key);
			if (data == null)
				data = new ResultGroupList();

			onFinished.onSuccess(data);
		}

	}

	public void getGroupById(String groupId, boolean isRefresh,
			final AbsAppServiceOnFinished onFinished) {
		final String key = "getGroupById_" + groupId;
		if (isNetworkConnected() && (!isReadDataCache(key) || isRefresh)) {
			RequestParams params = new RequestParams();
			params.put("groupId", groupId);
			client.post(URLs.getGroupById, params,
					new AsyncHttpResponseHandler() {

						@Override
						public void onFailure(Throwable arg0, String arg1) {

							onFinished.onFailed(getDefaultOnFailureResult());
						}

						@Override
						public void onSuccess(String arg0) {

							onFinished.onSuccess(Jsoner.parseObjectAgency(arg0,
									ResultGroup.class));
						}

					});
		} else {
			// �ӻ����ȡ
			onFinished.onSuccess(getCache(key, ResultGroup.class));

		}
	}

	/**
	 * �ӻ����ȡ����
	 * 
	 * @param key
	 *            ��
	 * @param clazz
	 *            ����ֽ���
	 * @return
	 */
	public <T> T getCache(String key, Class<T> clazz) {
		@SuppressWarnings("unchecked")
		T bean = (T) readObject(key);
		if (bean == null) {
			try {
				bean = clazz.newInstance();
			} catch (InstantiationException e) {

				e.printStackTrace();
			} catch (IllegalAccessException e) {

				e.printStackTrace();
			}
		}
		if (AppContext.DEBUG)
			Loger.i("�ӻ����ȡ����:key:" + key + "data:" + bean);
		return bean;
	}

	/**
	 * ��������Ƿ����
	 * 
	 * @return
	 */
	public boolean isNetworkConnected() {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		return ni != null && ni.isConnectedOrConnecting();
	}

	/**
	 * ��ȡ��ǰ��������
	 * 
	 * @return 0��û������ 1��WIFI���� 2��WAP���� 3��NET����
	 */
	public int getNetworkType() {
		int netType = 0;
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		if (networkInfo == null) {
			return netType;
		}
		int nType = networkInfo.getType();
		if (nType == ConnectivityManager.TYPE_MOBILE) {
			String extraInfo = networkInfo.getExtraInfo();
			if (!StringUtils.isEmpty(extraInfo)) {
				if (extraInfo.toLowerCase().equals("cmnet")) {
					netType = NETTYPE_CMNET;
				} else {
					netType = NETTYPE_CMWAP;
				}
			}
		} else if (nType == ConnectivityManager.TYPE_WIFI) {
			netType = NETTYPE_WIFI;
		}
		return netType;
	}

	/**
	 * �жϻ��������Ƿ�ɶ�
	 * 
	 * @param cachefile
	 * @return
	 */
	private boolean isReadDataCache(String cachefile) {
		return readObject(cachefile) != null;
	}

	/**
	 * �������
	 * 
	 * @param ser
	 * @param file
	 * @throws IOException
	 */
	public boolean saveObject(Serializable ser, String file) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = context.openFileOutput(file, Context.MODE_PRIVATE);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(ser);
			oos.flush();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				oos.close();
			} catch (Exception e) {
			}
			try {
				fos.close();
			} catch (Exception e) {
			}
		}
	}

	/**
	 * ��ȡ����
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public Serializable readObject(String file) {
		if (!isExistDataCache(file))
			return null;
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = context.openFileInput(file);
			ois = new ObjectInputStream(fis);
			return (Serializable) ois.readObject();
		} catch (FileNotFoundException e) {
		} catch (Exception e) {
			e.printStackTrace();
			// �����л�ʧ�� - ɾ�������ļ�
			if (e instanceof InvalidClassException) {
				File data = context.getFileStreamPath(file);
				data.delete();
			}
		} finally {
			try {
				ois.close();
			} catch (Exception e) {
			}
			try {
				fis.close();
			} catch (Exception e) {
			}
		}
		return null;
	}

	/**
	 * �жϻ����Ƿ����
	 * 
	 * @param cachefile
	 * @return
	 */
	private boolean isExistDataCache(String cachefile) {
		boolean exist = false;
		File data = context.getFileStreamPath(cachefile);
		if (data.exists())
			exist = true;
		return exist;
	}

	/**
	 * ������
	 * 
	 * @param name
	 * @param descript
	 * @param file
	 * @param OnFinished
	 */
	public void subCreateGroupApply(String name, String descript, File file,
			final AbsAppServiceOnFinished OnFinished) {
		RequestParams params = new RequestParams();
		params.put("name", name);
		params.put("note", descript);
		try {
			params.put("imgFile", file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Log.i(DUG_TAG, URLs.SUBCREATEGROUPAPPLY);
		client.post(URLs.SUBCREATEGROUPAPPLY, params,
				new AsyncHttpResponseHandler() {

					@Override
					public void onFailure(Throwable arg0, String content) {
						Log.e(DUG_TAG, arg0 + content);
						Result result = new Result();
						result.setMsg("�����쳣");
						OnFinished.onFailed(result);
					}

					@Override
					public void onSuccess(int arg0, String content) {
						Log.i(DUG_TAG, arg0 + content);
						if (arg0 != 200) {
							OnFinished.onFailed(new Result("������󣬱���ʧ��"));
						}
						try {
							JSONObject jsonObject = new JSONObject(content);
							int subResultType = jsonObject
									.getInt("subResultType");
							if (subResultType == 1) {
								OnFinished.onFailed(new Result("����ɹ�"));
								return;
							}
							JSONObject errorMap = jsonObject
									.getJSONObject("errorMap");
							String msg = errorMap.getString("msg");
							OnFinished.onFailed(new Result(msg));
						} catch (JSONException e) {
							Log.e(DUG_TAG, "���ص�json��ʽ����");
							e.printStackTrace();
						}
						// OnFinished.onSuccess(result);
					}

				});
	}
}
