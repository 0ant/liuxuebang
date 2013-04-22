package com.origwood.liuxue.service;

import android.content.Context;
import android.widget.Toast;

import com.origwood.liuxue.bean.Result;
import com.origwood.liuxue.util.Loger;

public abstract class AbsAppServiceOnFinished implements AppServiceOnFinished {

	@Override
	public void onSuccess(Result result, Object object) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSuccess(Object object) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFailed(Result result) {
		// TODO Auto-generated method stub

	}

	public void showFaildedMessage(Result result, Context context) {
		Loger.i("onFailt:" + result.getMsg());
		Toast.makeText(context, result.getMsg(), Toast.LENGTH_SHORT).show();
	}

}
