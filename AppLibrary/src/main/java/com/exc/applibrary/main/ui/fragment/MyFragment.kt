package com.exc.applibrary.main.ui.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.exc.applibrary.R
import com.exc.applibrary.main.model.LoginInfo
import com.exc.applibrary.main.ui.activity.AboutUsActivity
import com.exc.applibrary.main.ui.activity.MyInfoActivity
import com.exc.applibrary.main.utils.CommonUtils
import com.exc.applibrary.main.utils.Constant
import com.exc.applibrary.main.utils.PreferencesUtil
import zuo.biao.library.base.BaseFragment
import zuo.biao.library.util.JsonUtils

class MyFragment : BaseFragment() {
//    private var mRecyclerView: RecyclerView? = null
//    private var adapter: MineRecycleAdapter? = null
    private var mActivity: Activity? = null
    private var head_title: TextView? = null


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        setContentView(R.layout.fragment_my)
        mActivity = activity
        initView()
        initData()
        initEvent()
        return view
    }

    override fun initView() {
        findView<View>(R.id.loginout) { CommonUtils.exitLogin(401, mActivity) }
        findView<View>(R.id.cl_user_info) {
            toActivity(Intent(activity, MyInfoActivity::class.java))
        }

        findView<View>(R.id.about) {
            toActivity(Intent(activity, AboutUsActivity::class.java))
        }
//        mRecyclerView = findViewById(R.id.mineRecycleview)
        head_title = findViewById(R.id.head_title)
    }

    override fun initData() {
        val loginInfo = JsonUtils.parseObject(PreferencesUtil.getString(mActivity, Constant.LOGIN_INFO_JSON), LoginInfo::class.java)
        head_title!!.text = loginInfo.data.roleName + "： " + loginInfo.data.userName


//        val manager = GridLayoutManager(activity, 1)
//        mRecyclerView!!.layoutManager = manager
//        adapter = MineRecycleAdapter()
//        adapter!!.setmDatas(this@MyFragment)
//        mRecyclerView!!.adapter = adapter
    }

    override fun initEvent() {}
}