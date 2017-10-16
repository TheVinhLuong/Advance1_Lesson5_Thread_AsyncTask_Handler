package com.example.android.recyclerviewexample.screen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BaseRecyclerViewAdapter<V extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<V> {

    private final Context mContext;

    protected BaseRecyclerViewAdapter(@NonNull Context context) {
        mContext = context;
    }

    protected Context getContext() {
        return mContext;
    }
    
    public interface OnRecyclerViewItemClickListener<T> {
        void onItemRecyclerViewClick(View view, T item);
    }
}
