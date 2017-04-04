package com.daltonicchameleon.portfolio.util.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * portfolio-app by Carbon by BOLD
 * Created in 4/4/17 the following authors:
 * Pedro Okawa - {pedrookawa@carbonbybold.com}
 */

public abstract class BaseAdapter<T> extends RecyclerView.Adapter {

    private Context context;
    private List<T> data;

    public BaseAdapter(Context context) {
        this.context = context;
        this.data = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    /**
     * Returns adapter's context
     *
     * @return
     */
    public Context getContext() {
        return context;
    }

    /**
     * Adds an entire data set
     *
     * @param data
     */
    public void addAll(List<T> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * Adds an item on a specific position of the data set
     *
     * @param position
     * @param item
     */
    public void add(int position, T item) {
        data.add(position, item);
        notifyItemInserted(position);
    }

    /**
     * Adds an item into data set
     *
     * @param item
     */
    public void add(T item) {
        data.add(item);
        notifyItemInserted(getItemPosition(item));
    }

    /**
     * Returns the item on the given position
     *
     * @param position
     * @return item
     */
    public T getItem(int position) {
        return data.get(position);
    }

    /**
     * Removes the item on the given position
     *
     * @param position
     */
    public void remove(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * Removes the given item
     *
     * @param item
     */
    public void remove(T item) {
        data.remove(item);
        notifyItemRemoved(getItemPosition(item));
    }

    /**
     * Remove a collection of items
     *
     * @param ts
     */
    public void removeAll(Collection<T> ts) {
        data.removeAll(ts);
        notifyDataSetChanged();
    }

    /**
     * Clears data set
     */
    public void clear() {
        data.clear();
        notifyDataSetChanged();
    }

    /**
     * Returns the given item position
     *
     * @param item
     * @return itemPosition
     */
    public int getItemPosition(T item) {
        return data.indexOf(item);
    }

    /**
     * Returns adapter data
     *
     * @return data
     */
    public List<T> getDataSet() {
        return data;
    }

    /**
     * Check if the adapter is empty
     *
     * @return true if the data is empty, otherwise false
     */
    public boolean isEmpty() {
        return data.isEmpty();
    }
}

