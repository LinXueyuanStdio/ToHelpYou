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

package com.tohelpyou.client.view;

import com.tohelpyou.client.activity_fragment.UserActivity;
import com.tohelpyou.client.model.User;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.tohelpyou.R;
import zuo.biao.library.base.BaseView;
import zuo.biao.library.util.ImageLoaderUtil;
import zuo.biao.library.util.Log;
import zuo.biao.library.util.StringUtil;

/**用户
 * @author Lemon
 * @use
 * <br> UserView userView = new UserView(context, resources);
 * <br> adapter中使用:[具体参考.DemoAdapter2(getView使用自定义View的写法)]
 * <br> convertView = userView.createView(inflater, position, viewType);
 * <br> userView.bindView(data, position, viewType);
 * <br> 或  其它类中使用:
 * <br> containerView.addView(userView.createView(inflater));
 * <br> userView.bindView(data);
 * <br> 然后
 * <br> userView.setOnDataChangedListener(onDataChangedListener);data = userView.getData();//非必需
 * <br> userView.setOnClickListener(onClickListener);//非必需
 * <br> ...
 */
public class HelpView extends BaseView<User> implements OnClickListener {
	private static final String TAG = "HelpView";

	public HelpView(Activity context, Resources resources) {
		super(context, resources);
	}

	public ImageView ivHelpViewHead;

	public TextView tvHelpViewSex;

	public TextView tvHelpViewName;
	public TextView tvHelpViewId;
	public TextView tvHelpViewPhone;
	
	@SuppressLint("InflateParams")
	@Override
	public View createView(LayoutInflater inflater) {
		convertView = inflater.inflate(R.layout.help_view, null);

		ivHelpViewHead = findViewById(R.id.ivHelpViewHead, this);

		tvHelpViewSex = findViewById(R.id.tvHelpViewSex, this);

		tvHelpViewName = findViewById(R.id.tvHelpViewName, this);
		tvHelpViewId = findViewById(R.id.tvHelpViewId);
		tvHelpViewPhone = findViewById(R.id.tvHelpViewPhone, this);

		return convertView;
	}

	@Override
	public void bindView(User data){
		if (data == null) {
			Log.e(TAG, "bindView data == null >> data = new User(); ");
			data = new User();
		}
		this.data = data;

		ImageLoaderUtil.loadImage(ivHelpViewHead, data.getHead(), ImageLoaderUtil.TYPE_OVAL);

		tvHelpViewSex.setBackgroundResource(data.getSex() == User.SEX_FEMALE
				? R.drawable.circle_pink : R.drawable.circle_blue);
		tvHelpViewSex.setText(data.getSex() == User.SEX_FEMALE ?  "女" : "男");
		tvHelpViewSex.setTextColor(getColor(data.getSex() == User.SEX_FEMALE ? R.color.pink : R.color.blue));

		tvHelpViewName.setText(StringUtil.getTrimedString(data.getName()));
		tvHelpViewId.setText("ID:" + data.getId());
		tvHelpViewPhone.setText("Phone:" + StringUtil.getNoBlankString(data.getPhone()));
	}

	@Override
	public void onClick(View v) {
		if (data == null) {
			return;
		}
		toActivity(UserActivity.createIntent(context, data.getId()));
	}
}