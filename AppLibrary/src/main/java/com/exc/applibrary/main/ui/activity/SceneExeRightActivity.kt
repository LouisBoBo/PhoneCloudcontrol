package com.exc.applibrary.main.ui.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.exc.applibrary.R
import com.exc.applibrary.main.HttpRequest
import com.exc.applibrary.main.customview.CustomDialog
import com.exc.applibrary.main.customview.ImaginaryLineView
import com.exc.applibrary.main.customview.SpacesItemDecoration
import com.exc.applibrary.main.model.*
import com.exc.applibrary.main.ui.dialog.SceneBatchXIAFATimingDialog
import com.exc.applibrary.main.utils.CommonUtils
import com.exc.applibrary.main.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_scene_list.*
import kotlinx.android.synthetic.main.activity_scene_right_exe.*
import kotlinx.android.synthetic.main.activity_scene_right_exe.header_center_text
import kotlinx.android.synthetic.main.activity_scene_right_exe.header_left_img
import kotlinx.android.synthetic.main.activity_scene_right_exe.header_right_img
import kotlinx.android.synthetic.main.activity_scene_right_exe.mRecyclerView
import kotlinx.android.synthetic.main.activity_scene_right_exe.refreshLayout
import kotlinx.android.synthetic.main.activity_scene_right_exe.tv_no_data
import kotlinx.android.synthetic.main.fragment_scene.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import zuo.biao.library.base.BaseActivity
import zuo.biao.library.interfaces.OnHttpResponseListener
import zuo.biao.library.util.JsonUtils
import zuo.biao.library.util.StringUtil

@RequiresApi(Build.VERSION_CODES.N)
class SceneExeRightActivity : BaseActivity(), View.OnClickListener, OnHttpResponseListener {
    private lateinit var sceneDetail: SceneChooseNodeListData.Data.list
    val REQUEST_LIST_CODE = 1
    val REQUEST_LIJIXIAFA_CODE = 2
    private lateinit var listAdapter: SceneQuickAdapter

    //    private var checkItemData: SceneDataListById.Data? = null
    private lateinit var loadingDialog: CustomDialog
    private var pageNum = 1
    private var listData = arrayListOf<ControllerByIdSceneNameBean.Data.list>()
    private var selectData = arrayListOf<ControllerByIdSceneNameBean.Data.list>()
    private var partitionId = -1
    private var siteId = -1

    companion object {
        private const val sceneDetailKey: String = "sceneDetail"

        @JvmStatic
        fun createIntent(mActivity: Activity, sceneDetail: SceneChooseNodeListData.Data.list): Intent {
            val intent = Intent(mActivity, SceneExeRightActivity::class.java)
            intent.putExtra(sceneDetailKey, sceneDetail)
            return intent
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEven(result: String) {
        if (result == SceneBatchXIAFATimingDialog.XIAFA_SUCCESS) {
            repeat(listData.size) {
                listData[it].value = 0
            }
            selectData.clear()
            listAdapter.setList(listData)


        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEven(selectBuildModelSceneRightDetail: SelectBuildModelSceneRightDetail) {
        if (selectBuildModelSceneRightDetail.site_id > 0) {
            partitionId = selectBuildModelSceneRightDetail.partition_id
            siteId = selectBuildModelSceneRightDetail.site_id
            pageNum = 1
            initListData()
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scene_right_exe)
        initEventBus(true)
        sceneDetail = getIntent().getSerializableExtra(sceneDetailKey) as SceneChooseNodeListData.Data.list
        loadingDialog = CustomDialog(this)
        initView()
        initData()
        initEvent()
    }

    override fun initData() {
        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        mRecyclerView.addItemDecoration(SpacesItemDecoration(16))
        listAdapter = SceneQuickAdapter()
        mRecyclerView.adapter = listAdapter
        refreshLayout.isEnableRefresh = false
//        if (id < 0) {
//            showShortToast("数据有误")
//            return
//        }
        listAdapter.setOnItemClickListener { _, _, position ->
            repeat(listData.size) {
                if (position == it) {//多选
                    if (listData[it].value == 1) {
                        listData[it].value = 0
                    } else {
                        listData[it].value = 1
                    }
                }
                listAdapter.setList(listData)
            }
//            checkItemData = sceneListData[position]
//            SceneNodeDetailByIdDialog(context, checkItemData).show()

        }
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
        cb_all.setOnCheckedChangeListener { _, isChecked ->
            repeat(listData.size) {
                if (isChecked) {
                    listData[it].value = 1
                } else {
                    listData[it].value = 0
                }
            }
            if (listData.size > 0) {
                listAdapter.setList(listData)
            }
        }
    }

    private fun initListData() {
        HttpRequest.getControllerByIdSceneName(partitionId, siteId, pageNum, sceneDetail, REQUEST_LIST_CODE, this)
    }

    override fun initEvent() {
        ll_lijixiafa.setOnClickListener(this)
        ll_dingshixiafa.setOnClickListener(this)
        header_left_img.setOnClickListener(this)
        header_right_img.setOnClickListener(this)
    }

    override fun initView() {
        header_center_text.text = "场景执行"
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.header_left_img -> {
                finish()
            }
            R.id.header_right_img -> {
                toActivity(Intent(this, SearchBuildingActivity::class.java)
                        .putExtra("fromSceneRightDetail", true
                        )
                )
            }
            R.id.ll_lijixiafa -> {
                //找出已经勾选的item
                selectData.clear()
                repeat(listData.size) {
                    if (listData[it].value == 1) {
                        selectData.add(listData[it])
                    }
                }
                if (selectData.size == 0) {
                    showShortToast("请先选择场景")
                    return
                }

                loadingDialog.show()
                HttpRequest.scenePIliangXiafa(selectData, REQUEST_LIJIXIAFA_CODE, this)
            }
            R.id.ll_dingshixiafa -> {//定时下发
                //找出已经勾选的item
                selectData.clear()
                repeat(listData.size) {
                    if (listData[it].value == 1) {
                        selectData.add(listData[it])
                    }
                }
                if (selectData.size == 0) {
                    showShortToast("请先选择场景")
                    return
                }
                SceneBatchXIAFATimingDialog.showDialog(selectData, fragmentManager)

            }
        }
    }

    override fun onHttpResponse(requestCode: Int, resultJson: String?, e: Exception?) {
        loadingDialog.dismiss()
        if (StringUtil.isEmpty(resultJson)) {
            ToastUtils.showErrorToast(this, "数据异常")
            return
        }
        when (requestCode) {
            REQUEST_LIST_CODE -> {

                if (StringUtil.isEmpty(resultJson)) {
                    ToastUtils.showErrorToast(this, "数据异常")
                    return
                }
                val controllerByIdSceneNameBean = JsonUtils.parseObject(resultJson, ControllerByIdSceneNameBean::class.java)
                CommonUtils.exitLogin(controllerByIdSceneNameBean.code, activity)
                if (null != controllerByIdSceneNameBean.data.list && controllerByIdSceneNameBean.data.list.size > 0) {
                    if (pageNum == 1) {
                        listData.clear()
                        listData.addAll(controllerByIdSceneNameBean.data.list)
                        listAdapter.setList(listData)
                    } else {
                        listData.addAll(controllerByIdSceneNameBean.data.list)
                        listAdapter.addData(controllerByIdSceneNameBean.data.list)
                    }
                    tv_no_data.visibility  = View.GONE

                }else{
                    if (pageNum == 1) {
                        tv_no_data.visibility = View.VISIBLE
                    }
                }



                if (pageNum == 1) {
                    refreshLayout.finishRefresh()
                } else {
                    refreshLayout.finishLoadmore()
                }
            }
            REQUEST_LIJIXIAFA_CODE -> {//立即下发返回
                val sceneDataListById = JsonUtils.parseObject(resultJson, BaseBean::class.java)
                CommonUtils.exitLogin(sceneDataListById.code, activity)
                if (sceneDataListById.code != 200) {
                    if (selectData.size > 1) {
                        ToastUtils.showErrorToast(this, selectData[0].nodeName + "等" + selectData.size + "个节点控制失败")

                    } else {
                        ToastUtils.showErrorToast(this, selectData[0].nodeName + "节点控制失败")

                    }
                } else {
                    showShortToast("下发场景成功")
                }
                //清除掉所有可已经勾选
                repeat(listData.size) {
                    listData[it].value = 0
                }
                selectData.clear()
                runUiThread {
                    listAdapter.setList(listData)
                }
            }

        }

    }

    class SceneQuickAdapter : BaseQuickAdapter<ControllerByIdSceneNameBean.Data.list, BaseViewHolder>(R.layout.item_scene_exe_right_list) {
        @SuppressLint("ResourceAsColor")
        override fun convert(holder: BaseViewHolder, item: ControllerByIdSceneNameBean.Data.list) {

            var shuxian = holder.getView<ImaginaryLineView>(R.id.xuxian)
            shuxian.setLineAttribute(0xff555555.toInt(), 2F, floatArrayOf(8f, 8f, 8f, 8f));

            var iv_check = holder.getView<ImageView>(R.id.iv_check)
            var view_left = holder.getView<View>(R.id.view_left)
            if (item.value == 1) {
                iv_check.setImageResource(R.drawable.node_check)
                view_left.setBackgroundResource(R.drawable.scene_left_list_item_select_bg_shape)
            } else {
                iv_check.setImageResource(R.drawable.node_no_check)
                view_left.setBackgroundResource(R.drawable.scene_left_list_item_no_select_bg_shape)
            }
            holder.getView<TextView>(R.id.tv_node_name)!!.text = Html.fromHtml(String.format("节点名称：<font color=\"#ffffff\">%s", item!!.nodeName), Html.FROM_HTML_MODE_COMPACT)
            holder.getView<TextView>(R.id.tv_scene_name)!!.text = Html.fromHtml(String.format("场景名称：<font color=\"#ffffff\">%s", item!!.name), Html.FROM_HTML_MODE_COMPACT)
            holder.getView<TextView>(R.id.tv_part_name)!!.text = Html.fromHtml(String.format("分区名称：<font color=\"#ffffff\">%s", item!!.partitionName), Html.FROM_HTML_MODE_COMPACT)
        }
    }
}