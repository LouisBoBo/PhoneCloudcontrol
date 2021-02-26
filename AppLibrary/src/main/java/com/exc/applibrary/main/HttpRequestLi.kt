package com.exc.applibrary.main

import android.app.Activity
import com.exc.applibrary.main.model.BaseBean
import com.exc.applibrary.main.model.BaseBean2
import zuo.biao.library.interfaces.OnHttpResponseListener
import java.util.*

class
HttpRequestLi {
    companion object {

        //---------------端口-----------
        //	private static final String TAG = "HttpRequest";
        private val SHOW_PORT: String? = ":62126" //节目端口
//        private val SHOW_PORT: String? = "http://framesync.whapp.test.exc-led.cn" //节目端口

        val SHOW_VIDEO_SERVER_PATH = "http://192.168.111.129:60012/originalV/"


        //----------------------apiURL--------------------------------------------
        private val GET_SHOW_SHOWING_LIST_BY_SITE_ID = "$SHOW_PORT/script/playtime"

        private val GET_SHOW_SHOW_LIST_BY_SITE_ID = "${HttpRequest.SERVICES_PORT}/api/srcvideo/findListvideo"

        /**
         * 故障消息列表
         */
        private val MESSAGE_LIST = "${HttpRequest.SERVICES_PORT}/api/notice/list"

        /**
         * 故障消息开关
         */
        private val MESSAGE_SWITCH = "${HttpRequest.SERVICES_PORT}/api/notice/messageStatus"

        /**
         * 修改故障消息状态
         */
        private val UPDATE_MESSAGE = "${HttpRequest.SERVICES_PORT}/api/notice/update"


        //-------------------------------------method------------------------
        fun getShowShowingListBySiteId(siteId: Int, requestCode: Int, listener: OnHttpResponseListener?) {
            val request: MutableMap<String, Any> = HashMap()
            request["nodeId"] = 0
            request["siteId"] = siteId
            HttpManager.getInstance().post(request, HttpRequest.SERVICES_ADDRESS + GET_SHOW_SHOWING_LIST_BY_SITE_ID, requestCode, listener)
        }


        fun getShowShowListBySiteId(pageNum: Int, siteId: Int, requestCode: Int, listener: OnHttpResponseListener?) {
            val request: MutableMap<String, Any> = HashMap()
            request["id"] = siteId
            request["pageNum"] = pageNum
            request["pageSize"] = 10
            HttpManager.getInstance().post(request, HttpRequest.SERVICES_ADDRESS + GET_SHOW_SHOW_LIST_BY_SITE_ID, requestCode, listener)
        }

        fun getMessageList(pageNum: Int, requestCode: Int, listener: OnHttpResponseListener?) {
            val request: MutableMap<String, Any> = HashMap()
            request["status"] = 0
            request["pageNum"] = pageNum
            request["pageSize"] = 20
            HttpManager.getInstance().post(request, HttpRequest.SERVICES_ADDRESS + MESSAGE_LIST, requestCode, listener)
        }

        fun switchMessage(activity: Activity, news: Int, httpListener: HttpListener<BaseBean2>?) {
            val request: MutableMap<String, Any> = HashMap()
            request["news"] = news
            HttpManager.getInstance().postBackModule(activity, request, HttpRequest.SERVICES_ADDRESS + MESSAGE_SWITCH, httpListener)
        }


        fun modifyMessageStatus(id: Int, requestCode: Int, listener: OnHttpResponseListener?) {
            val request: MutableMap<String, Any> = HashMap()
            request["status"] = 1
            if (id > 0) {
                request["id"] = id
            }
            HttpManager.getInstance().post(request, HttpRequest.SERVICES_ADDRESS + UPDATE_MESSAGE, requestCode, listener)
        }
    }
}