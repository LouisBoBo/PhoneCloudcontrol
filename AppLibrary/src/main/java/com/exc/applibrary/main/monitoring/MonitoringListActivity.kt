package com.exc.applibrary.main.monitoring

import android.os.Bundle
import android.view.LayoutInflater
import com.exc.applibrary.databinding.ActivityMonitoringListBinding
import zuo.biao.library.base.BaseActivity

class MonitoringListActivity  : BaseActivity() {

    private lateinit var binding:ActivityMonitoringListBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMonitoringListBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        initView()
        initData()
        initEvent()

    }

    override fun initView() {
    }

    override fun initData() {
    }

    override fun initEvent() {
    }
}