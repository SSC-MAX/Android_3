package com.test.chatlist.adapter;

import android.util.SparseArray;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class BaseViewHolder extends RecyclerView.ViewHolder {
    /**
     * itemView
     */
    public View itemView;
    /**
     * SparseArray save views
     */
    SparseArray<View> views;

    /**
     * construction method
     * @param itemView
     * @param viewType
     */
    public BaseViewHolder(View itemView, int viewType) {
        super(itemView);
        this.itemView = itemView;
        if (viewType== BaseAdapter.TYPE_EMPTY||viewType== BaseAdapter.TYPE_HEADER||viewType== BaseAdapter.TYPE_FOOTER){
            return;
        }
        views = new SparseArray<>();
    }

    /**
     *
     * @param itemview
     * @param viewType
     * @param <T>
     * @return
     */
    public static <T extends BaseViewHolder> T getHolder(View itemview, int viewType){

        return (T) new BaseViewHolder(itemview,viewType);
    }

    /**
     *
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId){
        View childreView = views.get(viewId);
        if (childreView == null){
            childreView = itemView.findViewById(viewId);
            views.put(viewId,childreView);
        }
        return (T) childreView;
    }

    /**
     *
     * @param viewId
     * @param listener
     * @return
     */
    public BaseViewHolder setOnclickListener(int viewId, View.OnClickListener listener){
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }
}

