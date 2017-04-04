package com.daltonicchameleon.portfolio.util.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * portfolio-app by Carbon by BOLD
 * Created in 4/4/17 the following authors:
 * Pedro Okawa - {pedrookawa@carbonbybold.com}
 */
public abstract class BindingAdapter<T, K extends ViewDataBinding> extends BaseAdapter<T> {

    public BindingAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(layoutToInflate(viewType), parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = ViewHolder.class.cast(holder);
        doOnBindViewHolder(viewHolder, getItem(position), position);
        viewHolder.getDataBinding().executePendingBindings();
    }

    /**
     * Returns layout to inflate from the extended adapter
     *
     * @param viewType
     * @return layoutId
     */
    protected abstract @LayoutRes int layoutToInflate(int viewType);

    /**
     * Binds the view holder and initializes all adapter members
     *
     * @param viewHolder
     * @param item
     * @param position
     */
    protected abstract void doOnBindViewHolder(ViewHolder viewHolder, T item, int position);

    /**
     * View that holds layout data binding
     */
    protected class ViewHolder extends RecyclerView.ViewHolder {

        private K dataBinding;

        public ViewHolder(View itemView) {
            super(itemView);
            dataBinding = DataBindingUtil.bind(itemView);
        }

        public K getDataBinding() {
            return dataBinding;
        }
    }
}