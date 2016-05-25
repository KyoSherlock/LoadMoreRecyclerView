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

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.kyo.loadmore.LoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

	SimpleLoadMoreRecyclerView loadMoreRecyclerView;
	List<String> items = new ArrayList<>();
	SimpleTextAdapter adapter;
	int page;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		loadMoreRecyclerView = (SimpleLoadMoreRecyclerView) this.findViewById(R.id.loadMoreRecyclerView);
		loadMoreRecyclerView.setLayoutManager(new LinearLayoutManager(this));

		adapter = new SimpleTextAdapter(items, onItemClickListener);
		loadMoreRecyclerView.setAdapter(adapter);
		loadMoreRecyclerView.setHasFixedSize(true);
		loadMoreRecyclerView.setOnLoadMoreListener(onLoadMoreListener);

		// mock a network request
		populateData();
	}

	private LoadMoreRecyclerView.OnLoadMoreListener onLoadMoreListener = new LoadMoreRecyclerView.OnLoadMoreListener() {
		int count = 0;

		@Override
		public void onLoadMore() {
			count++;
			if (count == 4) {
				showError();
			} else if (count == 6) {
				showNoNewData();
			} else {
				page++;
				populateData(10, true);
			}
		}
	};


	private View.OnClickListener onItemClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {

		}
	};

	private void populateData() {
		populateData(5, false);
	}


	private void populateData(final int count, final boolean finishLoading) {
		loadMoreRecyclerView.postDelayed(new Runnable() {
			@Override
			public void run() {
//				Toast.makeText(MainActivity.this, "populate data, page = " + page, Toast.LENGTH_SHORT).show();
				for (int i = 0; i < count; i++) {
					items.add(String.valueOf(items.size()));
				}
				adapter.notifyDataSetChanged();
				if (finishLoading) {
					loadMoreRecyclerView.finishLoading();
				}
			}
		}, 500);
	}

	private void showError() {

		loadMoreRecyclerView.postDelayed(new Runnable() {
			@Override
			public void run() {
//				Toast.makeText(MainActivity.this, "Some error", Toast.LENGTH_SHORT).show();
				loadMoreRecyclerView.finishLoadingWithError("Error, please click me to retry!");
			}
		}, 500);
	}

	private void showNoNewData() {
		loadMoreRecyclerView.postDelayed(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(MainActivity.this, "No new data", Toast.LENGTH_SHORT).show();
				loadMoreRecyclerView.setLoadMoreEnable(false);
			}
		}, 500);
	}
}
