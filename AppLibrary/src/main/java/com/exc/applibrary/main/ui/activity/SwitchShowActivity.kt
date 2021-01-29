package com.exc.applibrary.main.ui.activity

import android.os.Bundle
import com.exc.applibrary.R
import kotlinx.android.synthetic.main.activity_switch_show.*
import zuo.biao.library.base.BaseActivity

class SwitchShowActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_switch_show)
        initView()
        initEvent()
        initData()

    }


    override fun initData() {
    }
    override fun initEvent() {
        mHeaderLeftImg.setOnClickListener { finish() }
    }
    override fun initView() {
    }


}