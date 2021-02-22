package com.exc.applibrary.main.message

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.exc.applibrary.databinding.ActivityMyMessageBinding
import com.exc.applibrary.main.HttpRequestLi
import com.exc.applibrary.main.customview.CustomDialog
import com.exc.applibrary.main.model.BaseBean
import com.exc.applibrary.main.utils.CommonUtils
import com.exc.applibrary.main.utils.ToastUtils
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import zuo.biao.library.base.BaseActivity
import zuo.biao.library.util.JsonUtils
import zuo.biao.library.util.Log
import zuo.biao.library.util.StringUtil

@RequiresApi(Build.VERSION_CODES.N)
class MyMessageListActivity : BaseActivity() {
    private lateinit var binding: ActivityMyMessageBinding
    private lateinit var mAdapter: MessageListQuickAdapter
    private var pageNum = 1
    private lateinit var loadingDialog: CustomDialog
    var listData = arrayListOf<MessageListBean.DataBeanX.DataBean>()
    var fistInitListEnd = false


    /**
     * 更新消息状态成功———手动修改状态为已读
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEven(eventUpdateMessageStatusBean: EventUpdateMessageStatusBean) {
        repeat(listData.size) {
            if (eventUpdateMessageStatusBean.id == listData[it].id) {
                listData[it].status = 1
            }
        }
        mAdapter.notifyDataSetChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyMessageBinding.inflate(inflater)
        initEventBus(true)
        loadingDialog = CustomDialog(activity)
        setContentView(binding.root)

        initView()
        initData()
        initEvent()

    }


    override fun initView() {
        binding.mRecyclerView.layoutManager = LinearLayoutManager(activity)
        mAdapter = MessageListQuickAdapter()
        binding.mRecyclerView.adapter = mAdapter
        binding.refreshLayout.setOnRefreshListener {
            pageNum = 1
            initListData()
        }
        binding.refreshLayout.setOnLoadmoreListener {
            pageNum++
            initListData()
        }
        mAdapter.setOnItemClickListener { _, _, position ->
            toActivity(MessageDetailActivity.createIntent(activity, listData[position]))
        }


        loadingDialog.show()
    }


    override fun initData() {
        initListData()

    }


    private fun initListData() {
        HttpRequestLi.getMessageList(pageNum, 1) { _: Int, resultJson: String?, _: Exception? ->
            if (StringUtil.isEmpty(resultJson)) {
                showShortToast("数据异常")
                return@getMessageList
            }
            val messageListBean = JsonUtils.parseObject(resultJson, MessageListBean::class.java)
            CommonUtils.exitLogin(messageListBean.code, activity)
            if (null != messageListBean.data.data && messageListBean.data.data.size > 0) {
                if (pageNum == 1) {
                    fistInitListEnd = true
                    listData = messageListBean.data.data
                    mAdapter.setList(messageListBean.data.data)
                    loadingDialog.dismiss()
                } else {
                    listData.addAll(messageListBean.data.data)
                    mAdapter.addData(messageListBean.data.data)
                }
                binding.refreshLayout.visibility = View.VISIBLE
                binding.tvNoData.visibility = View.GONE


            } else {
                if (pageNum == 1) {
                    binding.refreshLayout.visibility = View.GONE
                    binding.tvNoData.visibility = View.VISIBLE
                }
            }
            if (pageNum == 1) {
                binding.refreshLayout.finishRefresh()
            } else {
                binding.refreshLayout.finishLoadmore()
            }

        }
    }

    override fun initEvent() {
        binding.headerLeftImg.setOnClickListener {
            finish()
        }
        binding.shuazi.setOnClickListener {
            if (!fistInitListEnd) {
                showShortToast("初始化中，请稍后再试")
                return@setOnClickListener
            }
            if (listData.size == 0) {
                showShortToast("暂无消息")
                return@setOnClickListener
            }

            HttpRequestLi.modifyMessageStatus(-1, 2) { _: Int, resultJson: String?, _: java.lang.Exception? ->
                if (StringUtil.isEmpty(resultJson)) {
                    showShortToast("数据异常")
                    return@modifyMessageStatus
                }
                val baseBean = JsonUtils.parseObject(resultJson, BaseBean::class.java)
                if (baseBean.code != 200) {
                    ToastUtils.showErrorToast(activity, baseBean.message)
                    return@modifyMessageStatus
                }

                repeat(listData.size) {
                    listData[it].status = 1
                }
                mAdapter.notifyDataSetChanged()
                showShortToast("已清除未读消息！")
            }


        }
    }


}