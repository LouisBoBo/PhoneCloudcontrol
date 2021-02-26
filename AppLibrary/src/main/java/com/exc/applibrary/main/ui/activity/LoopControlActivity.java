package com.exc.applibrary.main.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.exc.applibrary.R;
import com.exc.applibrary.main.HttpRequest;
import com.exc.applibrary.main.adapter.LoopControlAdapter;
import com.exc.applibrary.main.adapter.LoopDeviceAdapter;
import com.exc.applibrary.main.customview.CustomDialog;
import com.exc.applibrary.main.customview.YFHeaderView;
import com.exc.applibrary.main.model.ChannelAllModel;
import com.exc.applibrary.main.model.SelectBuildModel;
import com.exc.applibrary.main.model.TypeRealyModel;
import com.exc.applibrary.main.ui.dialog.LoopConrolDialog;
import com.exc.applibrary.main.ui.dialog.SelectControlDialog;
import com.exc.applibrary.main.ui.fragment.MapFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import zuo.biao.library.base.BaseActivity;
import zuo.biao.library.interfaces.OnHttpResponseListener;
import zuo.biao.library.ui.AlertDialog;
import zuo.biao.library.ui.BottomMenuWindow;
import zuo.biao.library.util.JsonUtils;
import zuo.biao.library.util.Log;

public class LoopControlActivity extends BaseActivity implements OnHttpResponseListener, LoopConrolDialog.OnDialogConfirmClickListener,SelectControlDialog.onSelectControlListener, AlertDialog.OnDialogButtonClickListener {
    private RecyclerView leftRecyclerView;
    private RecyclerView rightRecyclerView;
    private LoopDeviceAdapter deviceAdapter;
    private LoopControlAdapter controlAdapter;
    private CustomDialog customDialog;
    private SmartRefreshLayout refreshLayout;
    private LoopConrolDialog loopConrolDialog;
    private SelectControlDialog selectControlDialog;


    private YFHeaderView headerView;
    private TextView control_open;
    private TextView control_close;
    private TextView btn_online;
    private TextView btn_offline;
    private TextView btn_open;
    private TextView btn_close;

    private List<ChannelAllModel.DataBean.ListBean> dataBeanList;
    private TypeRealyModel typeRealyModel;
    private ChannelAllModel channelAllModel;
    private ChannelAllModel.DataBean.ListBean select_listBean;
    private String select_channelTypeName;
    private int select_index;

    private int pageNum;
    private int partitionId;
    private int siteId;
    private String buildingName;
    private int nid;
    private int offline;
    private int status;
    private int channelTypeId;

    private final int TYPEREALY_REQUEST_CODE = 1;
    private final int CHANNELALL_REQUEST_CODE = 2;
    private final int CANCHANNEL_REQUEST_CODE = 3;
    private final int CHANNELCONTROL_REQUEST_CODE = 4;
    private final int DIALOG_CONTROL_OPEN_CODE = 5;
    private final int DIALOG_CONTROL_CLOSE_CODE = 6;

    private static final String[] TOPBAR_COLOR_NAMES = {"灰色", "蓝色", "黄色"};

    //收到消息回主UI刷新界面
    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 123:
                    controlAdapter.setmDatas(dataBeanList);
                    break;
            }

            super.handleMessage(msg);
        }
    };

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(SelectBuildModel model){
        pageNum = 1;
        partitionId = model.getPartition_id();
        siteId = model.getSite_id();
        buildingName = model.getBuild_name();
        nid = model.getNode_id();
        channelAllHttp();
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loop_control);
        EventBus.getDefault().register(this);
        initMainView();
        initView();
        initData();
    }

    public void initMainView(){
        customDialog = new CustomDialog(getActivity());
        headerView = findViewById(R.id.head_view);
        leftRecyclerView = findViewById(R.id.leftRecycleview);
        rightRecyclerView = findViewById(R.id.rightRecycleview);
        btn_online = findViewById(R.id.btn_online);
        btn_offline = findViewById(R.id.btn_offline);
        btn_open = findViewById(R.id.btn_open);
        btn_close = findViewById(R.id.btn_close);
        control_open = findViewById(R.id.control_open);
        control_close = findViewById(R.id.control_close);

        headerView.setImg_right(R.mipmap.icon_searchblue);
        headerView.img_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoopControlActivity.this, SearchBuildingActivity.class);
                intent.putExtra("fromLoopControlRight",true);
                LoopControlActivity.this.startActivityForResult(intent,1);
            }
        });

        GridLayoutManager device_manager = new GridLayoutManager(getActivity(),1);
        leftRecyclerView.setLayoutManager(device_manager);
        deviceAdapter = new LoopDeviceAdapter();

        GridLayoutManager control_manager = new GridLayoutManager(getActivity(),1);
        rightRecyclerView.setLayoutManager(control_manager);
        controlAdapter = new LoopControlAdapter();

        deviceAdapter.setOnItemClickListener(new LoopDeviceAdapter.OnItemClickListener() {
            @Override
            public void onItemSiteClick(String select_text, int id) {
                setViewLayoutParams(control_open,1,1);
                setViewLayoutParams(control_close,1,1);

                channelTypeId = id;
                pageNum = 1;
                channelAllHttp();
            }
        });

        controlAdapter.setOnItemClickListener(new LoopControlAdapter.OnItemClickListener() {
            @Override
            public void onItemSiteClick(String select_text, int index, int type) {
                select_listBean = dataBeanList.get(index);
                select_index = index;
                if(type == 1){
                    customDialogShow();
                    HttpRequest.channelControlHttp(select_listBean.getValue(),select_listBean.getId(),select_listBean.getDsn(),select_listBean.getTagId(),select_listBean.getNid(),select_listBean.getName(),CHANNELCONTROL_REQUEST_CODE,LoopControlActivity.this::onHttpResponse);
                }else if(type ==2){
                    showBottomDialog();
                }else if(type == 3){
                    Boolean select_item = false;
                    for (ChannelAllModel.DataBean.ListBean listBean : dataBeanList) {
                        if(listBean.getItem_select()){
                            select_item = listBean.getItem_select();
                            break;
                        }
                    }
                    if(select_item){
                        setViewLayoutParams(control_open,70,160);
                        setViewLayoutParams(control_close,70,160);
                    }else {
                        setViewLayoutParams(control_open,1,1);
                        setViewLayoutParams(control_close,1,1);
                    }
                }
            }
        });


        btn_online.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                setViewLayoutParams(control_open,1,1);
                setViewLayoutParams(control_close,1,1);

                btn_online.setSelected(true);
                btn_offline.setSelected(false);

                btn_online.setTextColor(Color.WHITE);
                btn_offline.setTextColor(Color.GRAY);

                offline = 0;
                pageNum = 1;
                channelAllHttp();
            }
        });

        btn_offline.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                setViewLayoutParams(control_open,1,1);
                setViewLayoutParams(control_close,1,1);

                btn_online.setSelected(false);
                btn_offline.setSelected(true);

                btn_offline.setTextColor(Color.WHITE);
                btn_online.setTextColor(Color.GRAY);

                offline = 1;
                pageNum = 1;
                channelAllHttp();
            }
        });

        btn_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setViewLayoutParams(control_open,1,1);
                setViewLayoutParams(control_close,1,1);

                btn_open.setSelected(true);
                btn_close.setSelected(false);

                btn_open.setTextColor(Color.WHITE);
                btn_close.setTextColor(Color.GRAY);

                status = 1;
                pageNum = 1;
                channelAllHttp();
            }
        });

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setViewLayoutParams(control_open,1,1);
                setViewLayoutParams(control_close,1,1);

                btn_open.setSelected(false);
                btn_close.setSelected(true);

                btn_open.setTextColor(Color.GRAY);
                btn_close.setTextColor(Color.WHITE);

                status = 0;
                pageNum = 1;
                channelAllHttp();
            }
        });

        control_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog(context, "", "此操作将打开当前选中的所有回路，是否仍然执行？", true, DIALOG_CONTROL_OPEN_CODE,LoopControlActivity.this::onDialogButtonClick);
                dialog.show();
            }
        });

        control_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog(context, "", "此操作将关闭当前选中的所有回路，是否仍然执行？", true, DIALOG_CONTROL_CLOSE_CODE,LoopControlActivity.this::onDialogButtonClick);
                dialog.show();
            }
        });
    }

    /**显示列表选择弹窗
     */
    private void showItemDialog() {
        loopConrolDialog = new LoopConrolDialog(context,1,select_listBean.getName(),select_listBean.getChannelTypeName(),this::onDialogConfirmClick,this::showSelectControlDialog);
        loopConrolDialog.show();
    }
    private void showBottomDialog(){
        toActivity(new Intent(context, BottomMenuWindow.class)
                        .putExtra(BottomMenuWindow.INTENT_TITLE, "请选择")
                        .putExtra(BottomMenuWindow.INTENT_ITEMS, new String[]{"编辑", "取消"})
                , 1, false);
    }
    private void showSelectControlDialog(){
        if(selectControlDialog == null){
            selectControlDialog = new SelectControlDialog(context,typeRealyModel,select_listBean.getChannelTypeName(),this::onSelectControl);
        }else {
            selectControlDialog.setChannelTypeName(typeRealyModel,select_listBean.getChannelTypeName());
        }
        selectControlDialog.show();
    }

    @Override
    public void initView() {
        refreshLayout = findView(R.id.refreshLayout);
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                pageNum ++;
                channelAllHttp();
            }
        });
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {

            }
        });
    }

    @Override
    public void initData() {
        dataBeanList = new ArrayList<>();
        HttpRequest.channel_type_relayHttp(TYPEREALY_REQUEST_CODE,this::onHttpResponse);
        pageNum = 1;partitionId = 0;siteId = 0;buildingName = "";nid = 0;offline = 999; status = 999;channelTypeId = 0;
        channelAllHttp();
    }

    @Override
    public void initEvent() {

    }

    public void channelAllHttp(){
        if(pageNum == 1){
            customDialogShow();
        }
        HttpRequest.channel_allHttp(10,pageNum,partitionId,siteId,buildingName,nid,offline,status,channelTypeId,CHANNELALL_REQUEST_CODE,this::onHttpResponse);
    }
    @Override
    public void onHttpResponse(int requestCode, String resultJson, Exception e) {

        if(requestCode == TYPEREALY_REQUEST_CODE){
            if (null == resultJson) {
                showShortToast("请求异常，请检查网络");
                return;
            }
            typeRealyModel = JsonUtils.parseObject(resultJson, TypeRealyModel.class);
            if(typeRealyModel.getCode() == 200){
                if(typeRealyModel.getData().size()>0){
                    leftRecyclerView.setAdapter(deviceAdapter);
                    deviceAdapter.setmDatas(typeRealyModel);
                }
            }

        }else if(requestCode == CHANNELALL_REQUEST_CODE){
            customDialog.dismiss();
            refreshLayout.finishLoadmore();
            if (null == resultJson) {
                showShortToast("请求异常，请检查网络");
                return;
            }
            channelAllModel = JsonUtils.parseObject(resultJson, ChannelAllModel.class);
            if(channelAllModel.getCode() == 200){
                if(channelAllModel.getData()!=null){
                    if(pageNum==1){
                        dataBeanList.clear();
                        rightRecyclerView.setAdapter(controlAdapter);
                    }
                    for (ChannelAllModel.DataBean.ListBean listBean : channelAllModel.getData().getList()) {
                        listBean.setItem_select(false);
                    }
                    dataBeanList.addAll(channelAllModel.getData().getList());
                    controlAdapter.setmDatas(dataBeanList);
                }
            }
        }else if(requestCode == CANCHANNEL_REQUEST_CODE){
            customDialog.dismiss();
            if (null == resultJson) {
                showShortToast("请求异常，请检查网络");
                return;
            }

            JSONObject jsonObj = JSONObject.parseObject(resultJson);
            String code = jsonObj.getString("code");
            String message = jsonObj.getString("message");
            if(code.equals("200")){

                select_listBean.setChannelTypeName(select_channelTypeName);
                dataBeanList.get(select_index).setChannelTypeName(select_channelTypeName);
                myHandler.sendEmptyMessage(123);
                message = "修改成功";
            }else {

            }

            showShortToast(message);
        }else if(requestCode == CHANNELCONTROL_REQUEST_CODE){
            customDialog.dismiss();
            if (null == resultJson) {
                showShortToast("请求异常，请检查网络");
                return;
            }

            JSONObject jsonObj = JSONObject.parseObject(resultJson);
            String code = jsonObj.getString("code");
            String message = jsonObj.getString("message");
            if(code.toString() == "200"){

            }else {

            }
            int value = dataBeanList.get(select_index).getValue();
            dataBeanList.get(select_index).setValue(value==0?1:0);
            myHandler.sendEmptyMessage(123);

            showShortToast(message);
        }
    }

    //加载提示
    public void customDialogShow(){
        if (null != customDialog) {
            customDialog.dismiss();
            customDialog = new CustomDialog(this);
            customDialog.show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            switch (requestCode) {
                case 1:
                    if (data != null) {
                        switch (data.getIntExtra(BottomMenuWindow.RESULT_ITEM_ID, -1)) {
                            case 0:
                                Log.d("点击了","编辑");
                                showItemDialog();
                                return;
                            case 1:
                                Log.d("点击了","取消");
                                return;
                            default:
                                break;
                        }
                    }
                    break;

                default:
                    break;
            }
        }
    }

    @Override
    public void onDialogConfirmClick(int requestCode) {
        Log.d("确认点击","返回了");
        customDialogShow();
        HttpRequest.canchannelHttp(select_listBean.getCanChannelTypeId(),select_listBean.getId(),select_listBean.getName(),CANCHANNEL_REQUEST_CODE,this::onHttpResponse);
    }

    @Override
    public void onSelectControl(String name , int id) {
        select_channelTypeName = name;
        selectControlDialog.dismiss();
        loopConrolDialog.refreshUI(name);
        select_listBean.setCanChannelTypeId(id);
    }

    //重设view高宽
    public void setViewLayoutParams(View view,int height,int width){
        ConstraintLayout.LayoutParams linearParams =(ConstraintLayout.LayoutParams) view.getLayoutParams(); //取控件textView当前的布局参数
        linearParams.width = width;// 控件的宽强制设成30
        linearParams.height = height;// 控件的高强制设成20
        view.setLayoutParams(linearParams);
    }

    //弹框
    public void onDialogButtonClick(int requestCode, boolean isPositive) {
        if (! isPositive) {
            return;
        }

        switch (requestCode) {
            case DIALOG_CONTROL_OPEN_CODE:

                break;

            case DIALOG_CONTROL_CLOSE_CODE:

                break;
            default:
        }
    }
}
