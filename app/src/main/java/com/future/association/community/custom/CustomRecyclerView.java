package com.future.association.community.custom;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;


/**
 * Created by HX·罗 on 2017/7/14.
 */

public class CustomRecyclerView extends RecyclerView {

    private boolean isLoading = true ;
    private int currentPage = 1;

    public void setPage(int page){
        currentPage = page ;
    }
    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public interface LoadMoreListener{
        void onLoadMore(int currentPage) ;
    }

    private LoadMoreListener loadMoreListener ;

    public void setLoadMoreListener(LoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    public CustomRecyclerView(Context context) {
        this(context,null) ;
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context){
            @Override
            protected int getExtraLayoutSpace(State state) {
                return 20;
            }
        } ;

        setLayoutManager(linearLayoutManager);
        try{
            addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
                @Override
                public void onLoadMore(int currentPage) {
                    if(loadMoreListener != null){
                        loadMoreListener.onLoadMore(currentPage);
                    }
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public abstract class EndlessRecyclerOnScrollListener extends OnScrollListener {
        int totalItemCount,lastVisibleItem;

        private LinearLayoutManager mLinearLayoutManager;

        public EndlessRecyclerOnScrollListener(
                LinearLayoutManager linearLayoutManager) {
            this.mLinearLayoutManager = linearLayoutManager;
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            totalItemCount = mLinearLayoutManager.getItemCount();
            lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition() ;
            if (!isLoading
                    && lastVisibleItem == totalItemCount-1 && dy > 0) {
                isLoading = true ;
                currentPage++;
                onLoadMore(currentPage);
            }
        }

        public abstract void onLoadMore(int currentPage);
    }
}
