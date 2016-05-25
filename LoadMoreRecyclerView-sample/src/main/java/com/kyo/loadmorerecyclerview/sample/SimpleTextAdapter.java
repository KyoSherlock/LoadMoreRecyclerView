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

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jianghui on 5/24/16.
 */
public class SimpleTextAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

	private List<String> items;
	private View.OnClickListener onClickListener;

	public SimpleTextAdapter(List<String> items, View.OnClickListener onClickListener) {
		this.items = items;
		this.onClickListener = onClickListener;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_simple_text, parent, false);
		TextViewHolder textViewHolder = new TextViewHolder(itemView, onClickListener);
		return textViewHolder;
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		TextViewHolder textViewHolder = (TextViewHolder) holder;
		textViewHolder.textView.setText(items.get(position));
	}

	@Override
	public int getItemCount() {
		return items == null ? 0 : items.size();
	}

	static class TextViewHolder extends RecyclerView.ViewHolder {

		TextView textView;

		public TextViewHolder(View itemView, View.OnClickListener onClickListener) {
			super(itemView);
			itemView.setOnClickListener(onClickListener);
			textView = (TextView) itemView.findViewById(R.id.textview);
		}
	}
}
