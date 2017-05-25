/*Copyright ©2016 TommyLemon(https://github.com/TommyLemon)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/

package com.tohelpyou.client.activity_fragment;

import java.io.File;

import com.tohelpyou.client.application.ThyApplication;
import com.tohelpyou.client.base.BaseFragment;
import com.tohelpyou.client.interfaces.TopBarMenuCallback;
import com.tohelpyou.client.model.Login;
import com.tohelpyou.client.model.User;
import com.tohelpyou.client.util.HttpRequest;

import zuo.biao.apijson.JSONResponse;
import zuo.biao.apijson.StringUtil;
import zuo.biao.library.base.BaseView.OnDataChangedListener;
import zuo.biao.library.interfaces.OnBottomDragListener;
import zuo.biao.library.manager.HttpManager.OnHttpResponseListener;
import zuo.biao.library.ui.AlertDialog;
import zuo.biao.library.ui.AlertDialog.OnDialogButtonClickListener;
import zuo.biao.library.ui.WebViewActivity;
import zuo.biao.library.util.CommonUtil;
import zuo.biao.library.util.DownloadUtil;
import zuo.biao.library.util.ImageLoaderUtil;
import zuo.biao.library.util.Log;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.tohelpyou.R;

/**设置fragment
 * @author Lemon
 * @use new MineFragment(),详细使用见.DemoFragmentActivity(initData方法内)
 */
public class PublishFragment extends BaseFragment implements OnClickListener, OnBottomDragListener, TopBarMenuCallback {
	private static final String TAG = "PublishFragment";

	//与Activity通信<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	/**创建一个Fragment实例
	 * @return
	 */
	public static PublishFragment createInstance() {
		return new PublishFragment();
	}

	//与Activity通信>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>	



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		//类相关初始化，必须使用<<<<<<<<<<<<<<<<
		super.onCreateView(inflater, container, savedInstanceState);
		setContentView(R.layout.publish_fragment);
		//类相关初始化，必须使用>>>>>>>>>>>>>>>>


		//功能归类分区方法，必须调用<<<<<<<<<<
		initView();
		initData();
		initEvent();
		//功能归类分区方法，必须调用>>>>>>>>>>

		return view;
	}



	//UI显示区(操作UI，但不存在数据获取或处理代码，也不存在事件监听代码)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public void initView() {//必须调用

	}


	private TextView leftMenu;
	@SuppressLint("InflateParams")
	@Override
	public View getLeftMenu(Activity activity) {
		if (leftMenu == null) {
			leftMenu = (TextView) LayoutInflater.from(activity).inflate(R.layout.top_right_tv, null);
			leftMenu.setGravity(Gravity.CENTER);
			leftMenu.setText("扫一扫");
			leftMenu.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					onDragBottom(false);
				}
			});
		}
		return leftMenu;
	}

	private TextView rightMenu;
	@SuppressLint("InflateParams")
	@Override
	public View getRightMenu(Activity activity) {
		if (rightMenu == null) {
			rightMenu = (TextView) LayoutInflater.from(activity).inflate(R.layout.top_right_tv, null);
			rightMenu.setGravity(Gravity.CENTER);
			rightMenu.setText("设置");
			rightMenu.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					onDragBottom(true);
				}
			});
		}
		return rightMenu;
	}

	//UI显示区(操作UI，但不存在数据获取或处理代码，也不存在事件监听代码)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>










	//Data数据区(存在数据获取或处理代码，但不存在事件监听代码)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public void initData() {//必须调用
		super.initData();

	}

	
	//Data数据区(存在数据获取或处理代码，但不存在事件监听代码)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>








	//Event事件区(只要存在事件监听代码就是)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public void initEvent() {//必须调用

		findViewById(R.id.rlPublishHelp).setOnClickListener(this);
		findViewById(R.id.ivPublishHelp).setOnClickListener(this);
		findViewById(R.id.tvPublishHelp).setOnClickListener(this);

		findViewById(R.id.rlPublishGethelp).setOnClickListener(this);
		findViewById(R.id.ivPublishGethelp).setOnClickListener(this);
		findViewById(R.id.tvPublishGethelp).setOnClickListener(this);
	}


	@Override
	public void onDragBottom(boolean rightToLeft) {
		if (isAlive() == false) {
			return;
		}

		if (rightToLeft) {

			toActivity(SettingActivity.createIntent(context));
			return;
		}

		startActivityForResult(ScanActivity.createIntent(context), REQUEST_TO_SCAN);
		context.overridePendingTransition(R.anim.bottom_push_in, R.anim.fade);
	}



	//系统自带监听方法<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	@Override
	public void onClick(View v) {//直接调用不会显示v被点击效果
		switch (v.getId()) {
		default:
			if (verifyLogin() == false) {
				return;
			}
			switch (v.getId()) {
			case R.id.rlPublishHelp:
			case R.id.ivPublishHelp:
			case R.id.tvPublishHelp:
				toActivity(HelpActivity.createIntent(context));
				break;
			case R.id.rlPublishGethelp:
			case R.id.ivPublishGethelp:
			case R.id.tvPublishGethelp:
				toActivity(GethelpActivity.createIntent(context));
				break;
			default:
				break;
			}
			break;
		}
	}

	//类相关监听<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

	private static final int REQUEST_TO_SCAN = 1;
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode != RESULT_OK) {
			return;
		}
		switch (requestCode) {
		case REQUEST_TO_SCAN:
			String result = data == null ? null : data.getStringExtra(ScanActivity.RESULT_QRCODE_STRING);
			if (StringUtil.isEmpty(result, true) == false) {
				if (StringUtil.isUrl(result)) {
					int index = result.indexOf("{\"User\":{");
					if (index > 0) {
						JSONResponse response = new JSONResponse(result.substring(index));
						User user = response.getObject(User.class);
						long id = user == null ? 0 : user.getId();
						if (id > 0) {
							toActivity(UserActivity.createIntent(context, id));
							break;
						}
					}
					toActivity(WebViewActivity.createIntent(context, "扫描结果", result));
				} else {
					CommonUtil.copyText(context, result);
				}
			}
			break;
		default:
			break;
		}
	}



	@Override
	public void onDestroy() {
		if (leftMenu != null) {
			leftMenu.destroyDrawingCache();
			leftMenu = null;
		}
		if (rightMenu != null) {
			rightMenu.destroyDrawingCache();
			rightMenu = null;
		}

		super.onDestroy();
	}


	//类相关监听>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	//系统自带监听方法>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	//Event事件区(只要存在事件监听代码就是)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>









	//内部类,尽量少用<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<



	//内部类,尽量少用>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

}