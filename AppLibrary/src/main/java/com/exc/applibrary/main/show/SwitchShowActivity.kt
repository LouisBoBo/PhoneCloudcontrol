package com.exc.applibrary.main.show

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.exc.applibrary.databinding.ActivitySwitchShowBinding
import com.exc.applibrary.main.HttpRequest
import com.exc.applibrary.main.customview.CustomDialog
import com.exc.applibrary.main.model.LoginInfo
import com.exc.applibrary.main.model.ShowIngListData
import com.exc.applibrary.main.model.ShowListData
import com.exc.applibrary.main.model.SiteModel
import com.exc.applibrary.main.utils.CommonUtils
import com.exc.applibrary.main.utils.Constant
import com.exc.applibrary.main.utils.ToastUtils
import com.shuyu.gsyvideoplayer.GSYVideoManager
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack
import com.shuyu.gsyvideoplayer.utils.OrientationUtils
import zuo.biao.library.base.BaseActivity
import zuo.biao.library.interfaces.OnHttpResponseListener
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
    private lateinit var mActivity: SwitchShowActivity
    private lateinit var loadingDialog: CustomDialog
    private lateinit var currentSiteDetail: SiteModel.DataBean
    private var showingList = arrayListOf<String>()
    var pageNum = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySwitchShowBinding.inflate(LayoutInflater.from(this))
        mActivity = this
        setContentView(binding.root)
        loadingDialog = CustomDialog(this)
        initView()
        initData()
        initEvent()

    }


    override fun initData() {
//        orientationUtils = OrientationUtils(this, binding.headerVideoPlayer)
        //查询当前站点信息
        loadingDialog.show()
        loginInfo = JsonUtils.parseObject(PreferencesUtil.getString(mActivity, Constant.LOGIN_INFO_JSON), LoginInfo::class.java)
        HttpRequest.siteGet(loginInfo.data.site.toString(), GET_SITE_INFO_ETAIL_CODE, this)


//        initHeader()
//        showList.add("http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4")
//        showList.add("http://192.168.111.129:60012//originalV/c7c65405-17da-4644-bd59-cdee16128832.mp4")
//        showList.add("http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4")
//        showList.add("http://192.168.111.129:60012//originalV/c7c65405-17da-4644-bd59-cdee16128832.mp4")
//        showList.add("http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4")
//        showList.add("http://192.168.111.129:60012//originalV/c7c65405-17da-4644-bd59-cdee16128832.mp4")
//        showList.add("http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4")
//
//        showListAdapter.setList(showList)


    }


    private fun initHeader() {


//        if (showingList.size > 0) {
//            val headerVideoPath = HttpRequestLi.SHOW_VIDEO_SERVER_PATH + showingList[0]
//            var headerVideoTitle = "测试视频"
//            initHeaderVideo(headerVideoPath, headerVideoTitle)
//        }
//        //查询节目列表
//        loadingDialog.show()
//        HttpRequestLi.getShowShowListBySiteId(pageNum, currentSiteDetail.site.id, GET_SHOW_LIST, this)


        //测试
        initHeaderVideo("http://192.168.111.129:60012//originalV/c7c65405-17da-4644-bd59-cdee16128832.mp4", "1111")


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
        requestOptions.frame(1 * 1000 * 1000)
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


        binding.headerVideoPlayer.setOnSwitchBtnClickListener {
            showShortToast("切换节目：$headerVideoTitle")

        }

        binding.llNoVideoPlayIng.visibility = View.GONE
        binding.llPlaying.visibility = View.VISIBLE


        //模拟测试节目列表数据
        var showList = arrayListOf<String>()
        showList.add("http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4")
        showList.add("http://192.168.111.129:60012//originalV/c7c65405-17da-4644-bd59-cdee16128832.mp4")
        showList.add("http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4")
        showList.add("http://192.168.111.129:60012//originalV/c7c65405-17da-4644-bd59-cdee16128832.mp4")
        showList.add("http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4")
        showList.add("http://192.168.111.129:60012//originalV/c7c65405-17da-4644-bd59-cdee16128832.mp4")
        showList.add("http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4")
        showListAdapter.setList(showList)
        loadingDialog.dismiss()


    }


    override fun initEvent() {
        binding.mHeaderLeftImg.setOnClickListener { finish() }
    }

    override fun initView() {
        showListAdapter = ShowListAdapter()
        linearLayoutManager = LinearLayoutManager(this)
        binding.mRecyclerViewShowList.layoutManager = linearLayoutManager
        binding.mRecyclerViewShowList.adapter = showListAdapter


        binding.refreshLayout.setOnLoadmoreListener {
            pageNum++

            var showList = arrayListOf<String>()
            showList.add("http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4")
            showList.add("http://192.168.111.129:60012//originalV/c7c65405-17da-4644-bd59-cdee16128832.mp4")
            showList.add("http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4")
            showList.add("http://192.168.111.129:60012//originalV/c7c65405-17da-4644-bd59-cdee16128832.mp4")
            showList.add("http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4")
            showList.add("http://192.168.111.129:60012//originalV/c7c65405-17da-4644-bd59-cdee16128832.mp4")
            showList.add("http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4")
            showListAdapter.addData(showList)

            binding.refreshLayout.finishLoadmore()


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
                //查询当前正在播放的节目
//                HttpRequestLi.getShowShowingListBySiteId(currentSiteDetail.site.id, GET_SHOWING_LIST, this)
                initHeader()//测试

            }
            GET_SHOWING_LIST -> {//正在播放的列表
                val showIngListData = JsonUtils.parseObject(resultJson, ShowIngListData::class.java)
                CommonUtils.exitLogin(showIngListData.code, mActivity)
                loadingDialog.dismiss()
                if (showIngListData.code != 200) {
                    ToastUtils.showErrorToast(mActivity, showIngListData.message)
                    return
                }
                if (null != showIngListData.data && showIngListData.data.nowPlayFileList.size > 0) {
                    showingList = showIngListData.data.nowPlayFileList
                } else {//没有正在播放的节目
                    binding.llNoVideoPlayIng.visibility = View.VISIBLE
                    binding.llPlaying.visibility = View.GONE
                }
                initHeader()

            }

            GET_SHOW_LIST -> {//节目列表
                val showIngListData = JsonUtils.parseObject(resultJson, ShowListData::class.java)
                CommonUtils.exitLogin(showIngListData.code, mActivity)
                loadingDialog.dismiss()
                if (showIngListData.code != 200) {
                    ToastUtils.showErrorToast(mActivity, showIngListData.message)
                    return
                }


                var showList = arrayListOf<String>()
                showList.add("http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4")
                showList.add("http://192.168.111.129:60012//originalV/c7c65405-17da-4644-bd59-cdee16128832.mp4")
                showList.add("http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4")
                showList.add("http://192.168.111.129:60012//originalV/c7c65405-17da-4644-bd59-cdee16128832.mp4")
                showList.add("http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4")
                showList.add("http://192.168.111.129:60012//originalV/c7c65405-17da-4644-bd59-cdee16128832.mp4")
                showList.add("http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4")
                showListAdapter.setList(showList)


            }
        }
    }



}