package com.exc.applibrary.main.monitoring;


import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.exc.applibrary.R;

import org.jetbrains.annotations.NotNull;

public class MonitoringListAdapter extends BaseQuickAdapter<MonitoringListData.DataBean.RecordsBean, BaseViewHolder> {

    public MonitoringListAdapter() {
        super(R.layout.item_monitor_list);
    }
    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, MonitoringListData.DataBean.RecordsBean itemData) {

        TextView tv_camera_name = baseViewHolder.getView(R.id.tv_camera_name);
        TextView tv_camera_num = baseViewHolder.getView(R.id.tv_camera_num);
        tv_camera_name.setText(itemData.getCameraName()+"");
        tv_camera_num.setText(baseViewHolder.getAdapterPosition()+"");



    }
}






