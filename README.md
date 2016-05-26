# LoadMoreRecyclerView 

LoadMore RecyclerView with loading message showing at bottom. It refreshes data at scroll end, not pullup.

![](https://github.com/KyoSherlock/LoadMoreRecyclerView/raw/master/screenshot//loadmore_screenshot.gif)

# Usage

It provide a OnLoadMoreListener.

```java
	loadMoreRecyclerView.setOnLoadMoreListener(new LoadMoreRecyclerView.OnLoadMoreListener() {
		@Override
		public void onLoadMore() {
			// load data from background.
		}
	});
```
You can finish loading UI after background work.

```java
	loadMoreRecyclerView.finishLoading();
	loadMoreRecyclerView.finishLoading(errorMessage); // or some error
```
You can customize footer layout.

```java
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
```
# Dependencies
[GroupAdapter](https://github.com/KyoSherlock/GroupAdapter) A specialized RecyclerView.Adapter that presents data from a sequence of RecyclerView.Adapter. 

# Changelog

### Version: 1.0
  * Initial Build
  
# License

    Copyright 2015, KyoSherlock
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
       http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
