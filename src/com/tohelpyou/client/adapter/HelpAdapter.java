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

package com.tohelpyou.client.adapter;

import zuo.biao.library.base.BaseViewAdapter;

import com.tohelpyou.client.model.User;
import com.tohelpyou.client.view.HelpView;
import android.app.Activity;
import android.view.ViewGroup;

/**用户adapter
 * @author Lemon
 */
public class HelpAdapter extends BaseViewAdapter<User, HelpView> {
	//	private static final String TAG = "UserAdapter";

	public HelpAdapter(Activity context) {
		super(context);
	}

	@Override
	public HelpView createView(int position, ViewGroup parent) {
		return new HelpView(context, resources);
	}

	@Override
	public long getItemId(int position) {
		return getItem(position).getId();
	}
}