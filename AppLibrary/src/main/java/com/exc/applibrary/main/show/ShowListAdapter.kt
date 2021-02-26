package com.exc.applibrary.main.show

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.exc.applibrary.R
import com.exc.applibrary.main.HttpRequestLi
import com.exc.applibrary.main.model.ShowListData
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder
import org.greenrobot.eventbus.EventBus
import zuo.biao.library.ui.AlertDialog

class ShowListAdapter : BaseQuickAdapter<ShowListData.DataBean.ListBean, BaseViewHolder>(R.layout.item_switch_show) {
    override fun convert(holder: BaseViewHolder, item: ShowListData.DataBean.ListBean) {

        var position = holder.adapterPosition
        var itemVideoPlayer = holder.getView<ShowListVideoPlayer>(R.id.itemVideoPlayer)


        var showName = item.name.split(".")[0]
        itemVideoPlayer.setTitle(showName)


        var url = HttpRequestLi.SHOW_VIDEO_SERVER_PATH + item.vidId + "." + item.type


        val imageView = ImageView(context)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        var requestOptions = RequestOptions()
        requestOptions.frame(1 * 1000 * 8000)
        Glide.with(context).setDefaultRequestOptions(requestOptions).load(url).into(imageView)

        itemVideoPlayer.isLooping = true
        itemVideoPlayer.backButton.visibility = View.GONE

        val gsyVideoOption = GSYVideoOptionBuilder()
        gsyVideoOption
                .setIsTouchWiget(true)
                .setThumbImageView(imageView)
                .setRotateViewAuto(false)
                .setLockLand(false)
                .setAutoFullWithSize(false)
                .setShowFullAnimation(false)
                .setNeedLockFull(true)
                .setUrl(url)
                .setCacheWithPlay(false)
                .setNeedShowWifiTip(false)
//                .setVideoTitle("测试视频" + holder.adapterPosition)
                .build(itemVideoPlayer)

        itemVideoPlayer.fullscreenButton.visibility = View.GONE

        itemVideoPlayer.setOnSwitchBtnClickListener {
            AlertDialog(context, "操作提示", "即将切换“" + item.vidName + "”为当前播放节目", true, 0) { _: Int, isPositive: Boolean ->
                if (isPositive) {
                    var xfBean = ShowQHShowBean()
                    xfBean.duration = item.duration
                    xfBean.frameNumber = item.frameNumber
                    xfBean.ftpId = item.ftpId
                    xfBean.name = item.name
                    xfBean.nodeNum = item.nodeNum
                    xfBean.siteId = item.siteId
                    xfBean.srcvId = item.srcvId
                    xfBean.type = item.type
                    xfBean.vidId = item.vidId
                    xfBean.vidName = item.vidName
                    EventBus.getDefault().post(xfBean)
                }
            }.show()
        }
    }

}