# LoadMoreRecyclerView 

LoadMore RecyclerView with loading message showing at bottom. It's endless refresh, not pullup refresh.

![](https://github.com/KyoSherlock/LoadMoreRecyclerView/raw/master/screenshots//loadmore_screenshot.gif)

# Usage

We can set OnLoadMoreListener to listen the LoadMore action.

```java
	loadMoreRecyclerView.setOnLoadMoreListener(new LoadMoreRecyclerView.OnLoadMoreListener() {
		@Override
		public void onLoadMore() {
			// load data from background.
		}
	});
```
After background work, we must finish loading UI.

```java
	loadMoreRecyclerView.finishLoading();
	loadMoreRecyclerView.finishLoading(errorMessage); // or some error
```
Usually we want to build a custom footer layout, we can implement the LoadMorePresenter that provides the loading and eorror UI.

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
	// set the Presenter to LoadMoreRecyclerView
	loadMoreRecyclerView.setLoadMorePresenter(new SimpleLoadMorePresenter(retryListener));
```
# Dependencies
[GroupAdapter](https://github.com/KyoSherlock/GroupAdapter)

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