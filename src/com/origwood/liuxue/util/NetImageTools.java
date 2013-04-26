package com.origwood.liuxue.util;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.callback.BitmapAjaxCallback;
import com.origwood.liuxue.AppContext;

public class NetImageTools {

	private static NetImageTools mNetImageTools;

	public static NetImageTools getInstance() {
		if (mNetImageTools == null) {
			mNetImageTools = new NetImageTools();
		}
		return mNetImageTools;
	}

	// public
	public interface OnSetFinishedListner {
		public void onFinished(Bitmap bm);
	}

	public void setImage(final ImageView imageView, final int defaultRecource,
			String url) {
		if (AppContext.DEBUG)
			Loger.i("图片地址:" + url);
		imageView.setImageResource(defaultRecource);
		if (url != null && !"".equals(url.trim())) {
			new AQuery(imageView).image(url, true, true, 0, defaultRecource,
					new BitmapAjaxCallback() {

						@Override
						protected void callback(String url, ImageView iv,
								Bitmap bm, AjaxStatus status) {

							if (bm == null) {
								imageView.setImageResource(defaultRecource);
							} else {
								if (AppContext.DEBUG)
									Loger.i("获取到的图像大小:" + bm.getByteCount());
							}
							super.callback(url, iv, bm, status);
						}
					});
		} else {
			imageView.setImageResource(defaultRecource);
		}
	}

	// public static void setImageFromHtml(ImageView imageView, int flag,
	// String content) {
	//
	// if (content != null) {
	// List<String> imgs = HtmlTool.getImgStr(content);
	// if (imgs.size() > 0) {
	// String imgSrc = imgs.get(0);
	// if (imgSrc.startsWith("http://")) {
	// new AQuery(imageView).image(imgs.get(0), true, true, 0, 0,
	// null, AQuery.FADE_IN_NETWORK, 1.0f);
	// } else {
	// new AQuery(imageView).image(Host.Host + imgs.get(0), true,
	// true, 0, 0, null, AQuery.FADE_IN_NETWORK, 1.0f);
	// }
	//
	// return;
	// } else {
	// imageView.setVisibility(flag);
	// }
	// } else {
	// imageView.setVisibility(flag);
	// }
	// }

	// public static void setViewGroupBackgroundImage(ViewGroup view,
	// int defaultRecource, String url) {
	// if (DEBUG)
	// Logger.i("ImagePath:" + url);
	// if (url != null) {
	//
	// new AQuery(view).image(url, true, true, 0, 0, null, AQuery.FADE_IN);
	// view.setBackgroundDrawable(new BitmapDrawable(new AQuery(view)
	// .getCachedImage(Host.Host + url)));
	// // view.setb
	// } else {
	// view.setBackgroundResource(defaultRecource);
	// // imageView.setImageResource(defaultRecource);
	// }
	// }

	// public static void addFootView(LayoutInflater inflater, ListView
	// listView,
	// final IRefresh iRefresh) {
	// View footview = inflater.inflate(R.layout.footview, null);
	// final TextView tvMsg = (TextView) footview.findViewById(R.id.tv_msg);
	// final LinearLayout loading = (LinearLayout) footview
	// .findViewById(R.id.loading);
	// tvMsg.setOnClickListener(new OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	// iRefresh.getData();
	// tvMsg.setVisibility(View.GONE);// ���ظ����ʾ��TextView
	// loading.setVisibility(View.VISIBLE);// ��ʾ���·��Ľ����
	// }
	// });
	// listView.addFooterView(footview);
	// }
}
