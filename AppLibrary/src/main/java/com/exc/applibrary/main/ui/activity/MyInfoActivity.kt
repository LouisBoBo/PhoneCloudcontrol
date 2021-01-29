package com.exc.applibrary.main.ui.activity

import android.app.Activity
import android.os.Bundle
import com.exc.applibrary.R
import com.exc.applibrary.main.HttpListener
import com.exc.applibrary.main.HttpRequest
import com.exc.applibrary.main.customview.CustomDialog
import com.exc.applibrary.main.model.LoginInfo
import com.exc.applibrary.main.model.UserData
import com.exc.applibrary.main.utils.Constant
import kotlinx.android.synthetic.main.activity_myinfo.*
import zuo.biao.library.base.BaseActivity
import zuo.biao.library.interfaces.OnHttpResponseListener
import zuo.biao.library.util.JsonUtils
import zuo.biao.library.util.PreferencesUtil

class MyInfoActivity : BaseActivity(), OnHttpResponseListener {
    private lateinit var mActivity: Activity
    private val REQUSET_USER_DATA_CODE = 1
    private lateinit var loadingDialog:CustomDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_myinfo)
        loadingDialog = CustomDialog(this)
        mActivity = this
        initView()
        initData()
    }


    override fun initView() {}
    override fun initData() {
        loadingDialog.show()
        var loginInfo = JsonUtils.parseObject(PreferencesUtil.getString(mActivity, Constant.LOGIN_INFO_JSON), LoginInfo::class.java)

//        HttpRequest.getUserData(loginInfo.data.userId, REQUSET_USER_DATA_CODE, this)
        HttpRequest.getUserBackModule(mActivity,loginInfo.data.userId, object : HttpListener<UserData>(){
            override fun onSuccess(userData: UserData) {
                loadingDialog.dismiss()
                tv_name.text = userData.data.user.name
                tv_sex.text = if (userData.data.user.gender == 1)  "女" else "男"
                tv_partition.text = userData.data.user.partitionName
                tv_role.text = userData.data.user.roleName
                tv_phone.text = userData.data.user.phone
                tv_email.text = userData.data.user.email
            }

            override fun onError() {
                loadingDialog.dismiss()
            }

        })


//
//                var pairsMap : java . util . HashMap < kotlin . String ?, kotlin.String?>? = java.util.HashMap<kotlin.String?, kotlin.String?>()
//        YConn.httpPost(mContext, YUrl.NEED_JUM_FREE_LING, pairsMap
//                , object : HttpListener<BaseData?>() {
//            override fun onSuccess(baseData: BaseData?) {}
//            override fun onError() {}
//        })


    }

    override fun initEvent() {}
    override fun onHttpResponse(requestCode: Int, resultJson: String?, e: Exception?) {
//        if (StringUtil.isEmpty(resultJson)) {
//            ToastUtils.showErrorToast(mActivity, "数据异常")
//            return
//        }
//        when (requestCode) {
//            REQUSET_USER_DATA_CODE -> {
//                val userData = JsonUtils.parseObject(resultJson, UserData::class.java)
//
//                if (null == userData) {
//                    ToastUtils.showErrorToast(mActivity, "数据异常")
//                    return
//                }
//
//                CommonUtils.exitLogin(userData.code, mActivity)
//                if (userData.code != 200) {
//                    ToastUtils.showErrorToast(mActivity, userData.message)
//                } else {
//                    tv_name.text = userData.data.user.name
//                    tv_sex.text = if (userData.data.user.gender == 1)  "女" else "男"
//                    tv_partition.text = userData.data.user.partitionName
//                    tv_role.text = userData.data.user.roleName
//                    tv_phone.text = userData.data.user.phone
//                    tv_email.text = userData.data.user.email
//                }
//            }
//        }


    }


}