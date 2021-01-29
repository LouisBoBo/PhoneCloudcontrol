package com.exc.applibrary.main.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.exc.applibrary.R;
import com.exc.applibrary.main.customview.YFHeaderView;

import zuo.biao.library.base.BaseActivity;

public class PwdChangeActivity extends BaseActivity {
    private YFHeaderView headerView;
    private TextView submitBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwd_change);

        initView();
        initData();
        initEvent();
    }

    @Override
    public void initView() {
        headerView = findViewById(R.id.head_view);
        submitBtn = findViewById(R.id.submitPwd);

        //提交新设置的密码
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

}
