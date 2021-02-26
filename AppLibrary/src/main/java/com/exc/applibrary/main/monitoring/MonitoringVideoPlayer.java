package com.exc.applibrary.main.monitoring;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.exc.applibrary.R;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import zuo.biao.library.util.StringUtil;

public class MonitoringVideoPlayer extends StandardGSYVideoPlayer {
    public MonitoringVideoPlayer(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public MonitoringVideoPlayer(Context context) {
        super(context);
    }

    public MonitoringVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrValues(context,attrs);
    }

    private void initAttrValues(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShowListVideoPlayer);
        boolean isShowSwitchBTN = typedArray.getBoolean(R.styleable.ShowListVideoPlayer_showSwitchBTN,true);

        if(!isShowSwitchBTN){
            iv_switch.setVisibility(GONE);
        }
    }

    public TextView show_title;
    public ImageView iv_switch;

    @Override
    protected void init(Context context) {
        super.init(context);

        show_title = findViewById(R.id.show_title);
        iv_switch = findViewById(R.id.iv_switch);






    }


    @Override
    public int getLayoutId() {
        return R.layout.monitoring_video_layout;
    }

    public void setTitle(String title) {
        if (StringUtil.isEmpty(title)) {
            show_title.setVisibility(GONE);
            return;
        }
        show_title.setText(title);
        iv_switch.setOnClickListener(v -> {
            switchBTNOnClickListener.onSwitchClick();
        });

    }
    SwitchBTNOnClickListener switchBTNOnClickListener;
    public void setOnSwitchBtnClickListener(SwitchBTNOnClickListener btnOnClickListener) {
        this.switchBTNOnClickListener = btnOnClickListener;
    }
    public interface SwitchBTNOnClickListener {
        void onSwitchClick();
    }
}
