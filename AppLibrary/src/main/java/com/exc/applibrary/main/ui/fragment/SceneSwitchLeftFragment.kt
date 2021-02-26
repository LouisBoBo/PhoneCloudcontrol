package com.exc.applibrary.main.ui.fragment

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exc.applibrary.R
import com.exc.applibrary.main.HttpRequest
import com.exc.applibrary.main.adapter.SceneLeftQuickAdapter
import com.exc.applibrary.main.customview.CustomDialog
import com.exc.applibrary.main.customview.SpacesItemDecoration
import com.exc.applibrary.main.model.SceneLeftListBean
import com.exc.applibrary.main.model.SelectBuildModelScene
import com.exc.applibrary.main.ui.activity.SceneExeActivity
import com.exc.applibrary.main.utils.CommonUtils
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import kotlinx.android.synthetic.main.fragment_scene.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import zuo.biao.library.base.BaseFragment
import zuo.biao.library.util.JsonUtils
import zuo.biao.library.util.StringUtil

@RequiresApi(Build.VERSION_CODES.N)
class SceneSwitchLeftFragment : BaseFragment() {
    private lateinit var mAdapter: SceneLeftQuickAdapter
    private var pageNum = 1
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var refreshLayout: SmartRefreshLayout
    private lateinit var loadingDialog: CustomDialog
    private lateinit var mActivity: Activity
    private var partitionId = -1
    private var siteId = -1


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        setContentView(R.layout.fragment_scene)
        loadingDialog = CustomDialog(activity)
        mActivity = this.requireActivity()
        initEventBus(true)
        initView()
        initData()
        initEvent()
        return view
    }

    override fun initView() {
        mRecyclerView = findView(R.id.mRecyclerView)
        refreshLayout = findView(R.id.refreshLayout)
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        mRecyclerView.addItemDecoration(SpacesItemDecoration(16))
    }

    override fun initData() {
        mAdapter = SceneLeftQuickAdapter()
        mRecyclerView.adapter = mAdapter
        refreshLayout.setOnRefreshListener {
            pageNum = 1
            initListData()
        }
        refreshLayout.setOnLoadmoreListener {
            pageNum++
            initListData()
        }
        loadingDialog.show()
        initListData()
    }

    private fun initListData() {
        HttpRequest.getGetSceneLeftList(partitionId, siteId, pageNum, 1) { _: Int, resultJson: String?, _: Exception? ->
            loadingDialog.dismiss()
            if (StringUtil.isEmpty(resultJson)) {
                showShortToast("数据异常")
                return@getGetSceneLeftList
            }
            val sceneLeftListBean = JsonUtils.parseObject(resultJson, SceneLeftListBean::class.java)
            CommonUtils.exitLogin(sceneLeftListBean.code, activity)
            if (null != sceneLeftListBean.data.list && sceneLeftListBean.data.list.size > 0) {
                if (pageNum == 1) {
                    mAdapter.setList(sceneLeftListBean.data.list)
                } else {
                    mAdapter.addData(sceneLeftListBean.data.list)
                }
                refreshLayout.visibility = View.VISIBLE
                tv_no_data.visibility  = View.GONE


            }else{
                if (pageNum == 1) {
                    refreshLayout.visibility = View.GONE
                    tv_no_data.visibility  = View.VISIBLE
                }
            }
            if (pageNum == 1) {
                refreshLayout.finishRefresh()
            } else {
                refreshLayout.finishLoadmore()
            }

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEven(clickItem: SceneLeftListBean.Data.list) {
        toActivity(SceneExeActivity.createIntent(mActivity, clickItem.id, clickItem.name))
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEven(selectBuildModelScene: SelectBuildModelScene) {
        if (selectBuildModelScene.site_id > 0) {
            partitionId = selectBuildModelScene.partition_id
            siteId = selectBuildModelScene.site_id
            pageNum = 1
            initListData()
        }

    }

    override fun initEvent() {}
}