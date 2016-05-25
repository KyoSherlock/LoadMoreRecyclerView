/**
 * Copyright 2015, KyoSherlock
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.kyo.loadmorerecyclerview.sample;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kyo.loadmore.LoadMoreRecyclerView;

/**
 * Created by jianghui on 5/24/16.
 */
public class SimpleLoadMoreRecyclerView extends LoadMoreRecyclerView {


	public SimpleLoadMoreRecyclerView(Context context) {
		this(context, null);
	}

	public SimpleLoadMoreRecyclerView(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SimpleLoadMoreRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.setLoadMorePresenter(new SimpleLoadMorePresenter(retryListener));
	}

	private View.OnClickListener retryListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			performLoadMore();
		}
	};


	static class SimpleLoadMorePresenter extends LoadMorePresenter {

		TextView message;
		View.OnClickListener onClickListener;

		public SimpleLoadMorePresenter(View.OnClickListener onClickListener) {
			this.onClickListener = onClickListener;
		}

		@Override
		public View onCreateView(ViewGroup parent) {
			View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_loading_more, parent, false);
			message = (TextView) view.findViewById(R.id.message);
			view.setOnClickListener(onClickListener);
			return view;
		}

		@Override
		public void onLoading(View view) {
			view.setClickable(false);
			message.setText(R.string.loading_more);
		}

		@Override
		public void onError(View view, String msg) {
			view.setClickable(true);
			message.setText(msg);
		}
	}
}
