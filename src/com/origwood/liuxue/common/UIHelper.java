package com.origwood.liuxue.common;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.origwood.liuxue.AppManager;
import com.origwood.liuxue.R;
import com.origwood.liuxue.ui.GroupSpace;
import com.origwood.liuxue.ui.Login;
import com.origwood.liuxue.ui.MyFans;
import com.origwood.liuxue.ui.Publish;
import com.origwood.liuxue.ui.Recommendation;
import com.origwood.liuxue.ui.TopicDetail;
import com.origwood.liuxue.ui.UserSpace;

public class UIHelper {
	public static final int PUBLISH_REPLY = 1;
	public static final int PUBLISH_TOPIC = 0;

	/**
	 * 发送App异常崩溃报告
	 * 
	 * @param cont
	 * @param crashReport
	 */
	public static void sendAppCrashReport(final Context cont,
			final String crashReport) {
		AlertDialog.Builder builder = new AlertDialog.Builder(cont);
		builder.setIcon(android.R.drawable.ic_dialog_info);
		builder.setTitle(R.string.app_error);
		builder.setMessage(R.string.app_error_message);
		builder.setPositiveButton(R.string.submit_report,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						// 发送异常报告
						Intent i = new Intent(Intent.ACTION_SEND);
						// i.setType("text/plain"); //模拟器
						i.setType("message/rfc822"); // 真机
						i.putExtra(Intent.EXTRA_EMAIL,
								new String[] { "jxsmallmouse@163.com" });
						i.putExtra(Intent.EXTRA_SUBJECT, "留学帮Android客户端 - 错误报告");
						i.putExtra(Intent.EXTRA_TEXT, crashReport);
						cont.startActivity(Intent.createChooser(i, "发送错误报告"));
						// 退出
						AppManager.getAppManager().AppExit(cont);
					}
				});
		builder.setNegativeButton(R.string.sure,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						// 退出
						AppManager.getAppManager().AppExit(cont);
					}
				});
		builder.show();
	}

	/**
	 * 显示登录或注册界面
	 * 
	 * @param context
	 */
	public static void toLogin(Context context) {
		context.startActivity(new Intent(context, Login.class));
	}

	/**
	 * 显示用户空间界面
	 * 
	 * @param context
	 * @param userid
	 *            用户id
	 */
	public static void toUserSpace(Context context, String userid) {
		Intent mIntent = new Intent(context, UserSpace.class);
		mIntent.putExtra("userid", userid);
		context.startActivity(mIntent);
	}

	/**
	 * 显示推荐留学帮
	 * 
	 * @param context
	 */
	public static void toRecommendation(Context context) {
		context.startActivity(new Intent(context, Recommendation.class));
	}

	/**
	 * 显示用户粉丝界面
	 * 
	 * @param context
	 * @param userid
	 */
	public static void toFans(Context context, String userId) {
		Intent mIntent = new Intent(context, MyFans.class);
		mIntent.putExtra("title", "粉丝");
		mIntent.putExtra("userid", userId);
		context.startActivity(mIntent);
	}

	public static void toGroup(Context context, String groupId) {
		Intent mIntent = new Intent(context, GroupSpace.class);
		mIntent.putExtra("groupId", groupId);
		context.startActivity(mIntent);
	}

	/**
	 * 显示主题详情
	 * 
	 * @param context
	 * @param topicId
	 *            主题id
	 */
	public static void toTopicDetail(Context context, String topicId) {
		Intent mIntent = new Intent(context, TopicDetail.class);
		mIntent.putExtra("topicId", topicId);

		context.startActivity(mIntent);
	}

	/**
	 * 发布内容
	 * 
	 * @param context
	 * @param pId
	 *            帮ID或者主题ID
	 * @param type
	 *            0，帮；1，主题
	 */
	public static void toPublish(Context context, String pId, int type) {
		Intent mIntent = new Intent(context, Publish.class);
		mIntent.putExtra("type", type);
		mIntent.putExtra("pId", pId);
		context.startActivity(mIntent);
	}

	/**
	 * 显示发布主题界面
	 * 
	 * @param context
	 * @param groupId
	 */
	public static void toPublishTopic(Context context, String groupId) {
		toPublish(context, groupId, PUBLISH_TOPIC);
	}

	/**
	 * 显示回复主题界面
	 * 
	 * @param context
	 * @param topicId
	 */
	public static void toPublishReply(Context context, String topicId) {
		toPublish(context, topicId, PUBLISH_REPLY);
	}
}
