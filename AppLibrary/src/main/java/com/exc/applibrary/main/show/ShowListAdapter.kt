package com.exc.applibrary.main.show

import android.app.Activity
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.exc.applibrary.R
import com.exc.applibrary.main.utils.ToastUtils
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack
import com.shuyu.gsyvideoplayer.utils.OrientationUtils

class ShowListAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_switch_show) {
    override fun convert(holder: BaseViewHolder, item: String) {

        var position = holder.adapterPosition
        var itemVideoPlayer = holder.getView<ShowListVideoPlayer>(R.id.itemVideoPlayer)

        itemVideoPlayer.setTitle("第" + position + "个节目")
        val imageView = ImageView(context)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        var requestOptions = RequestOptions()
        requestOptions.frame(1 * 1000 * 1000)
        Glide.with(context).setDefaultRequestOptions(requestOptions).load(item).into(imageView)

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
                .setUrl(item)
                .setCacheWithPlay(false)
                .setNeedShowWifiTip(false)
//                .setVideoTitle("测试视频" + holder.adapterPosition)
                .build(itemVideoPlayer)

        itemVideoPlayer.fullscreenButton.visibility = View.GONE

        itemVideoPlayer.setOnSwitchBtnClickListener {
            ToastUtils.showToast(context, "切换第" + position + "个节目", false)
        }

    }

}