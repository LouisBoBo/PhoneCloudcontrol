package com.exc.applibrary.main.show

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.exc.applibrary.databinding.ActivitySwitchShowBinding
import com.exc.applibrary.main.HttpRequest
import com.exc.applibrary.main.HttpRequestLi
import com.exc.applibrary.main.customview.CustomDialog
import com.exc.applibrary.main.model.*
import com.exc.applibrary.main.ui.activity.SearchBuildingActivity
import com.exc.applibrary.main.utils.CommonUtils
import com.exc.applibrary.main.utils.Constant
import com.exc.applibrary.main.utils.ToastUtils
import com.shuyu.gsyvideoplayer.GSYVideoManager
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import zuo.biao.library.base.BaseActivity
import zuo.biao.library.interfaces.OnHttpResponseListener
import zuo.biao.library.ui.AlertDialog
import zuo.biao.library.util.JsonUtils
import zuo.biao.library.util.PreferencesUtil
import zuo.biao.library.util.StringUtil

//节目切换
class SwitchShowActivity : BaseActivity(), OnHttpResponseListener {
    private lateinit var binding: ActivitySwitchShowBinding
    private lateinit var showListAdapter: ShowListAdapter

    //    private lateinit var orientationUtils: OrientationUtils
    var linearLayoutManager: LinearLayoutManager? = null
    lateinit var loginInfo: LoginInfo
    val GET_SITE_INFO_ETAIL_CODE = 1
    val GET_SHOWING_LIST = 2
    val GET_SHOW_LIST = 3
    var TS3_FAST_CODE = 4
    val GET_SHOWING_LIST2 = 5

    private lateinit var mActivity: SwitchShowActivity
    private lateinit var loadingDialog: CustomDialog
    private lateinit var currentSiteDetail: SiteModel.DataBean
    private var firstShowingListShowUrl = ""
    var pageNum = 1
    var currentSiteId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySwitchShowBinding.inflate(LayoutInflater.from(this))
        mActivity = this
        setContentView(binding.root)
        initEventBus(true)
        loadingDialog = CustomDialog(this)
        loginInfo = JsonUtils.parseObject(PreferencesUtil.getString(mActivity, Constant.LOGIN_INFO_JSON), LoginInfo::class.java)
        currentSiteId = loginInfo.data.site
        initView()
        initData()
        initEvent()

    }


    override fun initData() {
        loadingDialog.show()
        //查询当前站点信息
        HttpRequest.siteGet(currentSiteId.toString(), GET_SITE_INFO_ETAIL_CODE, this)
    }


    private fun initHeader() {
        if (!StringUtil.isEmpty(firstShowingListShowUrl)) {
            val headerVideoPath = HttpRequestLi.SHOW_VIDEO_SERVER_PATH + firstShowingListShowUrl
            var headerVideoTitle = firstShowingListShowUrl.split(".")[0]
            initHeaderVideo(headerVideoPath, headerVideoTitle)
        }
        //查询节目列表
        loadingDialog.show()
        HttpRequestLi.getShowShowListBySiteId(pageNum, currentSiteDetail.site.id, GET_SHOW_LIST, this)
    }

    private fun initHeaderVideo(headerVideoPath: String, headerVideoTitle: String) {
        binding.headerVideoPlayer.setUp(headerVideoPath, false, headerVideoTitle)
        binding.headerVideoPlayer.isLooping = true
        binding.headerVideoPlayer.backButton.visibility = View.GONE
        binding.headerVideoPlayer.setTitle(headerVideoTitle)

        //初始化不打开外部的旋转
//        orientationUtils.isEnable = false
        val imageView = ImageView(this)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        var requestOptions = RequestOptions()
        requestOptions.frame(1 * 1000 * 8000)
        Glide.with(this).setDefaultRequestOptions(requestOptions).load(headerVideoPath).into(imageView)
        val gsyVideoOption = GSYVideoOptionBuilder()
        gsyVideoOption
                .setIsTouchWiget(true)
                .setThumbImageView(imageView)
                .setRotateViewAuto(false)
                .setLockLand(false)
                .setAutoFullWithSize(false)
                .setShowFullAnimation(false)
                .setNeedLockFull(true)
                .setUrl(headerVideoPath)
                .setNeedShowWifiTip(false)
                .setCacheWithPlay(false)
                .setNeedShowWifiTip(false)
//                .setVideoTitle("测试视频")


                .build(binding.headerVideoPlayer)
        binding.headerVideoPlayer.fullscreenButton.visibility = View.GONE


        binding.headerVideoPlayer.startPlayLogic()




        binding.llNoVideoPlayIng.visibility = View.GONE
        binding.llPlaying.visibility = View.VISIBLE
        loadingDialog.dismiss()


    }


    override fun initEvent() {
        binding.mHeaderLeftImg.setOnClickListener { finish() }
        binding.tvSelectSite.setOnClickListener {

            //和场景的共用
            toActivity(Intent(this, SearchBuildingActivity::class.java)
                    .putExtra("fromScene", true
                    )


            )


        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEven(selectBuildModelScene: SelectBuildModelScene) {
        if (selectBuildModelScene.site_id > 0) {
            currentSiteId = selectBuildModelScene.site_id
            pageNum = 1
            initData()
        }
    }

    //切换节目
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEven(showXFShowBean: ShowQHShowBean) {
//        showShortToast("切换到："+showXFShowBean.name)
        loadingDialog.show()
        val frame: String = showXFShowBean.frameNumber.toString()
        val duration: String = showXFShowBean.duration
        val videoName: String = showXFShowBean.name
        val content: String = ShowUtil.getxmldata(frame, duration, videoName, "1");
        val partition_id: String = currentSiteDetail.site.partitionId.toString()

        var snsS = arrayOf(showXFShowBean.nodeNum)

        val site_id: String = showXFShowBean.siteId.toString()
        HttpRequest.ts3ScriptsFastHttp2(content, "快速策略", partition_id, site_id, snsS, TS3_FAST_CODE, this)

    }


    override fun initView() {
        showListAdapter = ShowListAdapter()
        linearLayoutManager = LinearLayoutManager(this)
        binding.mRecyclerViewShowList.layoutManager = linearLayoutManager
        binding.mRecyclerViewShowList.adapter = showListAdapter


        binding.refreshLayout.setOnLoadmoreListener {
            pageNum++
            HttpRequestLi.getShowShowListBySiteId(pageNum, currentSiteDetail.site.id, GET_SHOW_LIST, this)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        GSYVideoManager.releaseAllVideos()
    }


    override fun onHttpResponse(requestCode: Int, resultJson: String?, e: Exception?) {
        if (StringUtil.isEmpty(resultJson)) {
            loadingDialog.dismiss()
            ToastUtils.showErrorToast(mActivity, "数据异常")
            return
        }
        when (requestCode) {
            GET_SITE_INFO_ETAIL_CODE -> {//站点信息
                val siteModel = JsonUtils.parseObject(resultJson, SiteModel::class.java)
                CommonUtils.exitLogin(siteModel.code, mActivity)
                if (siteModel.code != 200) {
                    loadingDialog.dismiss()
                    ToastUtils.showErrorToast(mActivity, siteModel.message)
                    return
                }

                currentSiteDetail = siteModel.data
                binding.mTvSiteName.text = currentSiteDetail.site.name

                //查询当前正在播放的节目
                HttpRequestLi.getShowShowingListBySiteId(currentSiteDetail.site.id, GET_SHOWING_LIST, this)

            }
            GET_SHOWING_LIST -> {//正在播放的列表
                val showIngListData = JsonUtils.parseObject(resultJson, ShowIngListData::class.java)
                CommonUtils.exitLogin(showIngListData.code, mActivity)
                loadingDialog.dismiss()
                if (showIngListData.code != 200) {
                    binding.llNoVideoPlayIng.visibility = View.VISIBLE
                    binding.llPlaying.visibility = View.GONE

                }
                if (null != showIngListData.nowPlayFileList && showIngListData.nowPlayFileList.size > 0) {
                    repeat(showIngListData.nowPlayFileList.size) {
                        if (!StringUtil.isEmpty(showIngListData.nowPlayFileList[it])) {
                            firstShowingListShowUrl = showIngListData.nowPlayFileList[it]
                            return@repeat
                        }
                    }
                    if (StringUtil.isEmpty(firstShowingListShowUrl)) {
                        binding.llNoVideoPlayIng.visibility = View.VISIBLE
                        binding.llPlaying.visibility = View.GONE
                    }
                } else {//没有正在播放的节目
                    binding.llNoVideoPlayIng.visibility = View.VISIBLE
                    binding.llPlaying.visibility = View.GONE
                }
                initHeader()//填充正在播放的节目

            }

            GET_SHOW_LIST -> {//节目列表
                val showIngListData = JsonUtils.parseObject(resultJson, ShowListData::class.java)
                CommonUtils.exitLogin(showIngListData.code, mActivity)
                loadingDialog.dismiss()
                if (showIngListData.code != 200) {
                    if (pageNum > 1) {
                        binding.refreshLayout.finishLoadmore()
                    }
                    ToastUtils.showErrorToast(mActivity, showIngListData.message)
                    return
                }
                var showList = showIngListData.data.list

                if (pageNum == 1) {
                    showListAdapter.setList(showList)
                } else {
                    showListAdapter.addData(showList)
                }
            }

            TS3_FAST_CODE -> {
                loadingDialog.dismiss()

                val showXFrequestBackData = JsonUtils.parseObject(resultJson, ShowXFrequestBackData::class.java)
                val content = showXFrequestBackData.returnMsg.errDesc + "\n" +
                        "站点下节点数：" + showXFrequestBackData.data.defaultNum + "\n" +
                        "成功节点数：" + showXFrequestBackData.data.successNum
                AlertDialog(context, "节目切换", content, false, 0) { _: Int, isPositive: Boolean ->
                    if (isPositive) {

                    }
                }.show()

                if (showXFrequestBackData.data.successNum > 0) {//更新正在播放的节目

                    //切换后重新查询当前正在播放的节目
                    HttpRequestLi.getShowShowingListBySiteId(currentSiteDetail.site.id, GET_SHOWING_LIST2, this)

                }

            }
            GET_SHOWING_LIST2 ->{//切换后重新查询当前正在播放的节目结果
                // TODO: 2021/2/24 0024


            }




        }
    }


}