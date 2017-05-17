package com.daltonicchameleon.portfolio.util;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.daltonicchameleon.portfolio.util.constants.Constants;

/**
 * portfolio-app
 * Created in 4/27/17 by the following authors:
 * Pedro Okawa
 */
public abstract class EndlessScrollListener extends RecyclerView.OnScrollListener {

    private int previousTotal = 0;

    private int visibleItemCount;
    private int totalItemCount;
    private int firstVisibleItem;

    private LinearLayoutManager linearLayoutManager;

    public abstract void onVisibleThreshold();

    public EndlessScrollListener(LinearLayoutManager linearLayoutManager) {
        this.linearLayoutManager = linearLayoutManager;
        onVisibleThreshold();
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = linearLayoutManager.getItemCount();
        firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();

        if ((totalItemCount - visibleItemCount) <= (firstVisibleItem + Constants.ENDLESS_SCROLL_LISTENER_THRESHOLD)) {
            onVisibleThreshold();
        }
    }
}
