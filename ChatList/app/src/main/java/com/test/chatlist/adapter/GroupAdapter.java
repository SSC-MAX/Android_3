package com.test.chatlist.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.chatlist.R;
import com.test.chatlist.model.Group;

import java.util.List;

public class GroupAdapter extends BaseAdapter<Group> {

    /**
     * Construction method
     *
     * @param mContext
     * @param mList
     * @param itemLayoutId
     */
    public GroupAdapter(Context mContext, List<Group> mList, int itemLayoutId) {
        super(mContext, mList, itemLayoutId);
    }

    @Override
    public void onBind(BaseViewHolder holder, Group group, int position) {
        TextView name = holder.getView(R.id.tv_name);
        name.setText(group.getName());

        TextView creator = holder.getView(R.id.tv_creator);
        creator.setText(group.getCreator());

        ImageView imageView = holder.getView(R.id.iv_icon);
        imageView.setImageResource(group.getIconId());
    }
}
