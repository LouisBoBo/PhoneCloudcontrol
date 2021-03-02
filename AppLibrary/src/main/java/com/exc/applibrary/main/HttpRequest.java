/*Copyright ©2015 TommyLemon(https://github.com/TommyLemon)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/

package com.exc.applibrary.main;

import android.app.Activity;

import com.exc.applibrary.main.model.CanChannelModel;
import com.exc.applibrary.main.model.ControllerByIdSceneNameBean;
import com.exc.applibrary.main.model.CreateOrderBean;
import com.exc.applibrary.main.model.SceneBatchXiaFaRequestBean;
import com.exc.applibrary.main.model.SceneChooseNodeListData;
import com.exc.applibrary.main.model.SceneDataListById;
import com.exc.applibrary.main.model.WorkOrderDetail;
import com.exc.applibrary.main.utils.Constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import zuo.biao.library.interfaces.OnHttpResponseListener;
import zuo.biao.library.model.Parameter;
import zuo.biao.library.util.MD5Util;
import zuo.biao.library.util.PreferencesUtil;
import zuo.biao.library.util.StringUtil;

/**
 * HTTP请求工具类
 *
 * @author Lemon
 * @use 添加请求方法xxxMethod >> HttpRequest.xxxMethod(...)
 * @must 所有请求的url、请求方法(GET, POST等)、请求参数(key-value方式，必要key一定要加，没提供的key不要加，value要符合指定范围)
 * 都要符合后端给的接口文档
 */
public class HttpRequest {
    //	private static final String TAG = "HttpRequest";

    /**
     * 端口
     */
//    public static final String SERVICES_PORT = ":9701";//吴自力
//    public static final String SERVICES_PORT1 = ":8080";
//    public static final String SERVICES_PORT2 = ":8088";
//    public static final String SERVICES_PORT3 = ":8083";
//    public static final String SERVICES_PORT_ORDER = ":8085";
//    public static final String SERVICES_PORT_ORDER = ":8089";//武汉洪山

    /**
     * 基础URL，这里服务器设置可切换
     */
//    public static final String SERVICES_ADDRESS = "http://192.168.112.78";//吴自力
//    public static final String SERVICES_FILE_PATH = "http://192.168.112.78" + SERVICES_PORT_ORDER + "/";


    public static final String SERVICES_PORT = "http://iot.whapp.test.exc-led.cn";
    public static final String SERVICES_PORT1 = "http://iotwar.whapp.test.exc-led.cn";
    public static final String SERVICES_PORT2 = ":8088";
    public static final String SERVICES_PORT3 = "http://iotwar.whapp.test.exc-led.cn";
    public static final String SERVICES_PORT_ORDER = "http://manage.whapp.test.exc-led.cn";
    public static final String SERVICES_PORT_MONITOR = "http://surveillance.whapp.test.exc-led.cn";
    public static final String SERVICES_ADDRESS = "";
    public static final String SERVICES_FILE_PATH = "" + SERVICES_PORT_ORDER + "/";


    /**
     * 联俊测试
     */
//    public static final String SERVICES_ADDRESS = "http://192.168.111.155";
//    public static final String SERVICES_FILE_PATH = "http://192.168.111.155" + SERVICES_PORT_ORDER + "/";


    /**
     * 联俊测试
     */
//    public static final String SERVICES_ADDRESS = "http://192.168.111.155";
//    public static final String SERVICES_FILE_PATH = "http://192.168.111.155" + SERVICES_PORT_ORDER + "/";


//    public static final String SERVICES_ADDRESS = "http://192.168.112.61";//小戴
//    public static final String SERVICES_FILE_PATH = "http://192.168.112.61:8089/";

    //武汉生产环境
//    public static final String SERVICES_ADDRESS = "http://10.192.47.10";
//    public static final String SERVICES_FILE_PATH = "http://10.192.47.10" + SERVICES_PORT_ORDER + "/";


//    public static final String SERVICES_PORT = ":8088";//武汉亮化测试平台
//----------------------------------接口列表-------------------------------------------
    /**
     * 登录
     */
//    public static final String LOGIN_URL = ":9701/api/user/login";
    public static final String LOGIN_URL = SERVICES_PORT + "/api/user/login";
    /**
     * 我的工单列表
     */
    public static final String MY_WORK_ORDER_LIST = SERVICES_PORT_ORDER + "/api/order/new/list";

    /**
     * 我的工单列表(待处理)
     */
    public static final String MY_WORK_ORDER_LIST_WAIT_OPERATOR = SERVICES_PORT_ORDER + "/api/order/new/web/postList";

    /**
     * 搜索 分区名称/站点名称/建筑物名称
     */
    public static final String API_PARTITION_FINDLIST = SERVICES_PORT + "/api/partition/findlist";

    /**
     * 进入搜索页
     */
    public static final String API_PARTITION_QUERY = SERVICES_PORT + "/api/partition/query";

    /**
     * 根据站点ID查站点详情
     */
    public static final String API_SITE_GET = SERVICES_PORT + "/api/site/get";

    /**
     * 根据分区ID查分区详情
     */
    public static final String API_PARTITION_GET = SERVICES_PORT + "/api/partition/get";

    /**
     * 根据站点ID或分区ID查询建筑物
     */
    public static final String API_SITE_QUERYBUILDING = SERVICES_PORT + "/api/site/queryBuilding";

    /**
     * 站点回路控制开关（站点）接口
     */
    public static final String API_ELECTRICITY_SITE_CHANNEL = SERVICES_PORT + "/api/electricity/site/channel";

    /**
     * 节目暂停
     */
    public static final String API_PROGRAM_STOP_SITE = SERVICES_PORT + "/api/program/stop/site";

    public static final String AIP_QUERY_ORDER = SERVICES_PORT_ORDER + "/api/order/new";

    /**
     * 查询所有的故障类型
     */
    public static final String AIP_QUERY_ALL_ERROR_TYPE = SERVICES_PORT_ORDER + "/api/order/new/auto?pageSize=0&pageNum=0";

    /**
     * 节点节目下发
     */
    public static final String IOT_API_FILES_TS3_SCRIPTS = SERVICES_PORT3 + "/iot/api/files/ts3/scripts";

    /**
     * 站点节目切换
     */
    public static final String IOT_API_FILES_TS3_SCRIPTS_FAST = SERVICES_PORT1 + "/iot/api/files/ts3/scripts/fast";

    /**
     * 根据建筑物id/name查建筑物详情
     */
    public static final String API_BUILDING_GET = SERVICES_PORT + "/api/building/get";

    /**
     * 建筑物强电开启关闭接口
     */
    public static final String API_ELECTRICITY_BUILDING_CHANNEL = SERVICES_PORT + "/api/electricity/building/channel";

    /**
     * 电源控制开关（分区）接口
     */
    public static final String API_ELECTRICITY_PARTITION_CHANNEL = SERVICES_PORT + "/api/electricity/partition/channel";

    /**
     * 获取回路控制类型列表接口
     */
    public static final String API_CAN_CHANNEL_TYPE_RELAY = SERVICES_PORT + "/api/can/channel/type/relay";

    /**
     * 回路控制查询接口 根据分区ｉｄ、站点、节点、建筑名、开关状态、在离线状态查询—>回路条件查询数据
     */
    public static final String API_CAN_CHANNEL_ALL = SERVICES_PORT + "/api/can/channel/all";

    /**
     * 回路控制编辑接口
     */
    public static final String API_CAN_CHANNEL = SERVICES_PORT + "/api/can/channel";

    /**
     * 修改实时状态接口
     */
    public static final String API_CAN_CHANNEL_CONTROL = SERVICES_PORT + "/api/can/channel/control";

    /**
     * 查询分区下的策略
     */
    public static final String API_STRATEGY_INTER = SERVICES_PORT + "/api/strategy/inter";

    /**
     * 分区策略下发
     */
    public static final String TP_LIGHT_CONTROL_STRATEGY = SERVICES_PORT2 + "/tp/light/control/strategy";

    /**
     * 建筑节目停止
     */
    public static final String IOT_API_CONTROL_PRGM_STOP = SERVICES_PORT1 + "/iot/api/control/prgm/stop";

    /**
     * 分区紧急关闭节目
     */
    public static final String TP_LIGHT_CONTROL = SERVICES_PORT2 + "/tp/light/control";

    /**
     * 根据分区、站点、节点、建筑名查询回路数据接口
     */
    public static final String API_ELECTRICITY_NODE = SERVICES_PORT + "/api/electricity/node";

    /**
     * 修改密码
     */
    public static final String API_USER_UPDATEPASSWORD = SERVICES_PORT + "/api/user/updatepassword";

    /**
     * 修改个人信息
     */
    public static final String API_USER_UPDATE = SERVICES_PORT + "/api/user/update";

    public static final String PAGE_NUM = "pageNum";


    /**
     * 图片上传
     */
    public static final String UP_LOAD_IMG = SERVICES_PORT_ORDER + "/api/order/new/upload";
    /**
     * 修改工单
     */
    public static final String UPDATE_WORK_ORDER = SERVICES_PORT_ORDER + "/api/order/new/update";
    /**
     * 修改工单
     */
    public static final String CREATE_ORDER = SERVICES_PORT_ORDER + "/api/order/new/add";
    /**
     * 节点选择场景列表
     */
    public static final String GET_SCENE_LEFT_LIST = SERVICES_PORT + "/api/electricity/node";

    /**
     * 根据节点id选择场景回路—>查询场景回路数据
     */
    public static final String QUERY_SCENE_BY_ID = SERVICES_PORT + "/api/can/scene/node/";

    /**
     * 立即下发—> 批量控制回路接口
     */
    public static final String SCENE_LI_JI_XIA_FA =SERVICES_PORT + "/api/can/channel/control";


    /**
     * 场景定时下发
     */
    public static final String SCENE_PRE_TIME_XIA_FA =SERVICES_PORT + "/api/can/timing";

    /**
     * 场景选择节点列表
     */
    public static final String SCENE_CHOOSE_JIEDIAN =SERVICES_PORT + "/api/can/scene";
    /**
     * 根据场景名称查询强电场景控制结果数据
     */
    public static final String SCENE_CHOOSE_CONTROLLER_BY_NAME = SERVICES_PORT +"/api/can/scene/name";
    /**
     * 批量控制场景回路（立即下发）
     */
    public static final String SCENE_XIAFA_PILIANG =SERVICES_PORT + "/api/can/channel/control/scene";

    /**
     * 批量控制场景回路（定时下发）
     */
    public static final String SCENE_XIAFA_PILIANG_TIMING = SERVICES_PORT +"/api/can/timing/patch";

    /**
     * 待初审（市级）—>获取用户列表 接口
     */
    public static final String ORDER_CITY_GET_USER_LIST = SERVICES_PORT_ORDER + "/api/order/new/user/new";
    /**
     * 初审(市级)
     */
    public static final String ORDER_INIT_AUDIT_CITY = SERVICES_PORT_ORDER + "/api/order/new/first/trial/city";

    /**
     * 初审(区级)
     */
    public static final String ORDER_INIT_AUDIT_PART = SERVICES_PORT_ORDER + "/api/order/new/first/trial";

    /**
     * 催单
     */
    public static final String ORDER_RUSH_ORDER = SERVICES_PORT_ORDER + "/api/order/new/urge";

    /**
     * 问题申报
     */
    public static final String ORDER_ERROR_REPORT = SERVICES_PORT_ORDER + "/api/order/new/problemDeclaration";

    /**
     * 根据分区查询处理人员角色
     */
    public static final String ORDER_QUERY_ROLE_BY_PARTITION = SERVICES_PORT_ORDER + "/api/order/new/role";

    /**
     * 根据处理人角色查询处理人员列表
     */
    public static final String ORDER_QUERY_OPERATOR_LIST_BY_ROLE = SERVICES_PORT_ORDER + "/api/order/new/user";

    /**
     * 待审核（区级）- 审核
     */
    public static final String ORDER_DO_AUDIT_PARTITION = SERVICES_PORT_ORDER + "/api/order/new/second/trial";
    /**
     * 待审核（市级）- 审核
     */
    public static final String ORDER_DO_AUDIT_CITY = SERVICES_PORT_ORDER + "/api/order/new/second/trial/city";

    /**
     * 获取用户信息
     */
    public static final String GET_USR_DATA =SERVICES_PORT + "/api/user/get";

    /**
     * 处理完成进入待审核
     */
    public static final String DO_OPERATOR = SERVICES_PORT_ORDER + "/api/order/new/complete";


    //示例代码<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<


    //user<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    public static final String RANGE = "range";

    public static final String ID = "id";
    public static final String USER_ID = "userId";
    public static final String CURRENT_USER_ID = "currentUserId";

    public static final String PHONE = "phone";
    public static final String PASSWORD = "password";


    /**
     * 翻译，根据有道翻译API文档请求
     * http://fanyi.youdao.com/openapi?path=data-mode
     * <br > 本Demo中只有这个是真正可用，其它需要自己根据接口文档新增或修改
     *
     * @param word
     * @param requestCode
     * @param listener
     */
    public static void translate(String word, final int requestCode, final OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();
        request.put("username", "15988888888");
        request.put("password", MD5Util.computeMD5("123456789Aa@"));

        request.put("doctype", "json");

        HttpManager.getInstance().post(request, "http://192.168.112.78:9701/api/user/login", requestCode, listener);


    }


    //account<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * 注册
     *
     * @param phone
     * @param password
     * @param listener
     */
    public static void register(final String phone, final String password,
                                final int requestCode, final OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();
        request.put(PHONE, phone);
        request.put(PASSWORD, MD5Util.MD5(password));

//		HttpManager.getInstance().post(request, URL_BASE + "/register", requestCode, listener);
    }

    /**
     * 登陆
     *
     * @param username
     * @param password
     * @param listener
     */
    public static void login(final String username, final String password,
                             final int requestCode, final OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();
        request.put("username", username);
        request.put("password", MD5Util.computeMD5(password));

        HttpManager.getInstance().post(request, SERVICES_ADDRESS + LOGIN_URL, requestCode, listener);
    }


    public static void getMyWorkOrderList(int userId, int overtime, int pageNum, ArrayList<Integer> statusIds, int requestCode, OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();
        request.put("userId", PreferencesUtil.getInt(PhoneColdControlApp.instance, Constant.USER_ID));
        request.put("pageSize", 10);
        request.put("pageNum", pageNum);
        request.put("orderName", "");
        request.put("startTime", "");
        request.put("endTime", "");
        request.put("faultTypeId", "");
        request.put("partitionId", "");


        if (overtime < 0) {
            request.put("overtime", null);

        } else {
            request.put("overtime", overtime);

        }

        if (statusIds.size() != 0) {
            if (statusIds.get(0) == -1) {
                request.put("statusIds", null);
            } else {
                request.put("statusIds", statusIds);
            }
        }

        if (statusIds.size() > 0 && statusIds.get(0) == 3) {//待处理
            request.put("operator", userId);
            HttpManager.getInstance().post(request, SERVICES_ADDRESS + MY_WORK_ORDER_LIST_WAIT_OPERATOR, requestCode, listener);
            return;
        }
        HttpManager.getInstance().post(request, SERVICES_ADDRESS + MY_WORK_ORDER_LIST, requestCode, listener);
    }


    public static void getWorkOrderDetail(int orderId, int requestCode, OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();
        request.put("userId", PreferencesUtil.getInt(PhoneColdControlApp.instance, Constant.USER_ID));
        request.put("orderId", orderId);
        HttpManager.getInstance().get(request, SERVICES_ADDRESS + AIP_QUERY_ORDER, requestCode, listener);
    }

    public static void getAllErrorType(int requestCode, OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();
        HttpManager.getInstance().get(request, SERVICES_ADDRESS + AIP_QUERY_ALL_ERROR_TYPE, requestCode, listener);
    }


    /**
     * 搜索 分区名称/站点名称/建筑物名称
     */
    public static void getPartitionFindlist(int requestCode, OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();
        request.put("name", "");
        HttpManager.getInstance().post(request, SERVICES_ADDRESS + API_PARTITION_FINDLIST, requestCode, listener);
    }

    /**
     * 进入搜索页
     */
    public static void getPartitionQuery(String username, String password, int requestCode, OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();
        request.put("username", PreferencesUtil.getString(PhoneColdControlApp.instance, Constant.LOCATION_USER_NAME_KEY));
        request.put("password", MD5Util.MD5(PreferencesUtil.getString(PhoneColdControlApp.instance, Constant.LOCATION_PWD_KEY)));

        HttpManager.getInstance().post(request, SERVICES_ADDRESS + API_PARTITION_QUERY, requestCode, listener);
    }

    /**
     * 根据站点ID查站点详情
     */
    public static void siteGet(String siteid, int requestCode, OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();
        request.put("id", siteid);

        HttpManager.getInstance().post(request, SERVICES_ADDRESS + API_SITE_GET, requestCode, listener);
    }

    /**
     *
     */
    public static void partitionGet(String partitionid, int requestCode, OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();
        request.put("id", partitionid);

        HttpManager.getInstance().post(request, SERVICES_ADDRESS + API_PARTITION_GET, requestCode, listener);
    }

    /**
     * 根据站点ID或分区ID查询建筑物
     */
    public static void getSiteBuilding(String partitionId, String siteId, int pageNum, int pageSize, int requestCode, OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();
        request.put("id", siteId);
        request.put("partitionId", partitionId);
        request.put("pageNum", pageNum);
        request.put("pageSize", pageSize);

        HttpManager.getInstance().post(request, SERVICES_ADDRESS + API_SITE_QUERYBUILDING, requestCode, listener);
    }

    /**
     * 站点回路控制开关（站点）接口
     */
    public static void getSiteChannel(int channelTypeId
            , int siteId, int state, int requestCode, OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();
        request.put("id", siteId);
        request.put("channelTypeId", channelTypeId);
        request.put("state", state);

        HttpManager.getInstance().put(request, SERVICES_ADDRESS + API_ELECTRICITY_SITE_CHANNEL, requestCode, listener);
    }

    /**
     * 节目暂停
     */
    public static void getStopSite(int siteId, int requestCode, OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();
        request.put("siteId", siteId);

        HttpManager.getInstance().post(request, SERVICES_ADDRESS + API_PROGRAM_STOP_SITE, requestCode, listener);
    }

    /**
     * 节点节目下发
     */
    public static void iotapifilests3scripts(String content, String filename, String partitionId, String siteId, String sns, int requestCode, OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();
        request.put("content", content);
        request.put("filename", filename);
        request.put("partitionId", partitionId);
        request.put("siteId", siteId);

        String snss[] = {sns};
        request.put("sns", snss);

        HttpManager.getInstance().post(request, SERVICES_ADDRESS + IOT_API_FILES_TS3_SCRIPTS, requestCode, listener);
    }

    /**
     * 站点节目切换
     */
    public static void ts3ScriptsFastHttp(String content, String filename, String partitionId, String siteId, String sns, int requestCode, OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();
        request.put("content", content);
        request.put("filename", filename);
        request.put("partitionId", partitionId);
        request.put("siteId", siteId);
        request.put("sns", sns);

        HttpManager.getInstance().post(request, SERVICES_ADDRESS + IOT_API_FILES_TS3_SCRIPTS_FAST, requestCode, listener);
    }


    /**
     * 节目切换-站点节目切换
     */
    public static void ts3ScriptsFastHttp2(String content, String filename, String partitionId, String siteId, String[] sns, int requestCode, OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();
        request.put("content", content);
        request.put("filename", filename);
        request.put("partitionId", partitionId);
        request.put("siteId", siteId);
        request.put("sns", sns);

        HttpManager.getInstance().post(request, SERVICES_ADDRESS + IOT_API_FILES_TS3_SCRIPTS_FAST, requestCode, listener);
    }


    /**
     * 根据建筑物id/name查建筑物详情
     */
    public static void buildingGetHttp(String id, String name, int requestCode, OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();
        request.put("id", id);
        request.put("name", name);

        HttpManager.getInstance().post(request, SERVICES_ADDRESS + API_BUILDING_GET, requestCode, listener);
    }

    /**
     * 建筑物强电开启关闭接口
     */
    public static void buildingChannelHttp(int id, int channelTypeId, int state, int requestCode, OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();
        request.put("id", id);
        request.put("state", state);
        request.put("channelTypeId", channelTypeId);

        HttpManager.getInstance().put(request, SERVICES_ADDRESS + API_ELECTRICITY_BUILDING_CHANNEL, requestCode, listener);
    }

    /**
     * 电源控制开关（分区）接口
     */
    public static void partitionChannelHttp(int channelTypeId, int partitionId, int state, int requestCode, OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();
        request.put("id", partitionId);
        request.put("channelTypeId", channelTypeId);
        request.put("state", state);

        HttpManager.getInstance().put(request, SERVICES_ADDRESS + API_ELECTRICITY_PARTITION_CHANNEL, requestCode, listener);
    }

    /**
     * 获取回路控制类型列表接口
     */
    public static void channel_type_relayHttp(int requestCode, OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();
        HttpManager.getInstance().get(request, SERVICES_ADDRESS + API_CAN_CHANNEL_TYPE_RELAY, requestCode, listener);
    }

    /**
     * 回路控制查询接口 根据分区ｉｄ、站点、节点、建筑名、开关状态、在离线状态查询—>回路条件查询数据
     */
    public static void channel_allHttp(int pageSize, int pageNum, int partitionId, int siteId, String buildingName, int nid, int offline, int status, int channelTypeId, int requestCode, OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();
        request.put("pageSize", pageSize);
        request.put("pageNum", pageNum);
        request.put("partitionId", partitionId != 0 ? partitionId : "");
        request.put("siteId", siteId != 0 ? siteId : "");
        request.put("buildingName", buildingName);
        request.put("nid", nid != 0 ? nid : "");
        request.put("offline", offline != 999 ? offline : "");
        request.put("status", status != 999 ? status : "");
        request.put("channelTypeId", channelTypeId != 0 ? channelTypeId : "");

        HttpManager.getInstance().get(request, SERVICES_ADDRESS + API_CAN_CHANNEL_ALL, requestCode, listener);
    }

    /**
     * 回路控制接口
     */
    public static void canchannelHttp(int canChannelTypeId, int id, String name, int requestCode, OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();

        CanChannelModel model = new CanChannelModel();
        model.setCanChannelTypeId(canChannelTypeId);
        model.setId(id);
        model.setName(name);

        List<CanChannelModel> list = new ArrayList();
        list.add(model);

        request.put("canChannels", list);

        HttpManager.getInstance().put(request, SERVICES_ADDRESS + API_CAN_CHANNEL, requestCode, listener);
    }

    /**
     * 修改实时状态接口
     */
    public static void channelControlHttp(int value, int id, int dsn, int tagId, int nid, String name, int requestCode, OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();
        HashMap<String, Object> map = new HashMap<>();
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        map.put("dsn", dsn);
        map.put("nid", nid);
        map.put("id", id);
        map.put("tagId", tagId);
        map.put("value", value);
        map.put("name", name);
        list.add(map);
        request.put("canChannelControls", list);

        HttpManager.getInstance().put(request, SERVICES_ADDRESS + API_CAN_CHANNEL_CONTROL, requestCode, listener);
    }

    /**
     * 查询分区下的策略
     */
    public static void strategyinterHttp(String type, int partitionId, int requestCode, OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();
//        request.put("type",type);
        request.put("partitionId", partitionId);
        HttpManager.getInstance().get(request, SERVICES_ADDRESS + API_STRATEGY_INTER, requestCode, listener);
    }

    /**
     * 分区策略下发
     */
    public static void controlstrategyHttp(int strategyNum, int strategyType, int requestCode, OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();
        request.put("strategyNum", strategyNum);
        request.put("strategyType", strategyType);
        HttpManager.getInstance().get(request, SERVICES_ADDRESS + TP_LIGHT_CONTROL_STRATEGY, requestCode, listener);
    }

    /**
     * 建筑节目停止
     */
    public static void controlPrgmHttp(String sn, int requestCode, OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();
        request.put("sn", sn);
        HttpManager.getInstance().post(request, SERVICES_ADDRESS + IOT_API_CONTROL_PRGM_STOP, requestCode, listener);
    }

    /**
     * 分区紧急关闭节目
     */
    public static void lightcontrolHttp(String strategyId, String mode, int requestCode, OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();
        request.put("strategyId", strategyId);
        request.put("mode", mode);
        HttpManager.getInstance().get(request, SERVICES_ADDRESS + TP_LIGHT_CONTROL, requestCode, listener);
    }

    /**
     * 根据分区、站点、节点、建筑名查询回路数据接口
     */
    public static void electricitynodeHttp(int requestCode, OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();
        request.put("name", "");
        request.put("pageNum", 1);
        request.put("pageSize", 1000);
        HttpManager.getInstance().get(request, SERVICES_ADDRESS + API_ELECTRICITY_NODE, requestCode, listener);
    }

    /**
     * 修改密码
     */
    public static void pwdChangeHttp(String usedPasswrod, String password, int requestCode, OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();
        request.put("usedPasswrod", MD5Util.computeMD5(usedPasswrod));
        request.put("password", MD5Util.computeMD5(password));
        HttpManager.getInstance().post(request, SERVICES_ADDRESS + API_USER_UPDATEPASSWORD, requestCode, listener);
    }

    /**
     * 修改个人信息
     */
    public static void updateUserHttp(String id, String email, String gender, String name, String phone, int requestCode, OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();
        request.put("id", id);
        if (email != null && email.length() > 0) {
            request.put("email", email);
        }

        if (gender != null && gender.length() > 0) {
            request.put("gender", gender);
        }

        if (name != null && name.length() > 0) {
            request.put("name", name);
        }

        if (phone != null && phone.length() > 0) {
            request.put("phone", phone);
        }

        HttpManager.getInstance().post(request, SERVICES_ADDRESS + API_USER_UPDATE, requestCode, listener);
    }
    //account>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    public static void upDateWorkOrder(WorkOrderDetail.Data.OrderNew orderNew,
                                       ArrayList<WorkOrderDetail.Data.OrderNew.OrderPics> showPicList
            , int requestCode, OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();
        request.put("orderId", orderNew.getId());
        request.put("faultTypeId", orderNew.getFaultTypeId());
        request.put("partitionId", orderNew.getPartitionId());
        request.put("addr", orderNew.getAddr());
        request.put("description", orderNew.getDescription());
        ArrayList<Integer> imgIdList = new ArrayList<>();
        for (WorkOrderDetail.Data.OrderNew.OrderPics picInfo : showPicList) {
            if (picInfo.getFileType() == 1) {
                imgIdList.add(picInfo.getId());
            }
        }
        request.put("imgIdList", imgIdList);
        HttpManager.getInstance().post(request, SERVICES_ADDRESS + UPDATE_WORK_ORDER, requestCode, listener);
    }

    public static void addWorkOrder(CreateOrderBean createOrderBean,
                                    ArrayList<WorkOrderDetail.Data.OrderNew.OrderPics> showPicList
            , int requestCode, OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();
        request.put("name", createOrderBean.getName());
        request.put("faultTypeId", createOrderBean.getFaultTypeId());
        request.put("partitionId", createOrderBean.getPartitionId());
        request.put("addr", createOrderBean.getAddr());
        request.put("description", createOrderBean.getDescription());
        ArrayList<Integer> imgIdList = new ArrayList<>();
        for (WorkOrderDetail.Data.OrderNew.OrderPics picInfo : showPicList) {
            if (picInfo.getFileType() == 1) {
                imgIdList.add(picInfo.getId());
            }
        }
        request.put("imgIdList", imgIdList);
        HttpManager.getInstance().post(request, SERVICES_ADDRESS + CREATE_ORDER, requestCode, listener);
    }

    public static void getGetSceneLeftList(int partitionId, int siteId, int pageNum, int requestCode, OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();

        if (partitionId >= 0 && siteId >= 0) {
            request.put("partitionId", partitionId);
            request.put("siteId", siteId);
        }

        request.put("pageNum", pageNum);
        request.put("pageSize", "10");
        HttpManager.getInstance().get(request, SERVICES_ADDRESS + GET_SCENE_LEFT_LIST, requestCode, listener);
    }

    //场景选择节点列表
    public static void getGetSceneRightList(int pageNum, int requestCode, OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();
        request.put("pageNum", pageNum);
        request.put("pageSize", "10");
        HttpManager.getInstance().get(request, SERVICES_ADDRESS + SCENE_CHOOSE_JIEDIAN, requestCode, listener);
    }

    //根据节点id选择场景回路—>查询场景回路数据
    public static void getSceneById(int id, int requestCode, OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();
        HttpManager.getInstance().get(request, SERVICES_ADDRESS + QUERY_SCENE_BY_ID + id, requestCode, listener);
    }

    //根据场景名称查询强电场景控制结果数据
    public static void getControllerByIdSceneName(int partitionId, int siteId, int pageNum, SceneChooseNodeListData.Data.list sceneDetail, int requestCode, OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();
        request.put("pageNum", pageNum);
        request.put("pageSize", "10");
        request.put("name", sceneDetail.getName());
        request.put("buildingName", "");
        request.put("buildingTypeSn", "");
        request.put("nodeName", "");

        if (partitionId >= 0 && siteId >= 0) {
            request.put("partitionId", partitionId);
            request.put("siteId", siteId);
        }

        HttpManager.getInstance().post(request, SERVICES_ADDRESS + SCENE_CHOOSE_CONTROLLER_BY_NAME, requestCode, listener);
    }


    //根据节点id选择场景回路—>查询场景回路数据
    public static void sceneLijixiafa(int id, SceneDataListById.Data checkItemData, int requestCode, OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();

        HashMap<String, Object> map = new HashMap<>();
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        map.put("dsn", checkItemData.getDsn());
        map.put("nid", id);
        map.put("value", 1);
        map.put("tagId", checkItemData.getTagId());
        list.add(map);

        request.put("canChannelControls", list);

        HttpManager.getInstance().put(request, SERVICES_ADDRESS + SCENE_LI_JI_XIA_FA, requestCode, listener);
    }    //根据节点id选择场景回路—>查询场景回路数据

    public static void sceneTimingXiafa(int type, int id, String beginDate, String endDate, ArrayList<Integer> cycleTypes, SceneDataListById.Data checkItemData, int requestCode, OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();
        request.put("nid", id);


        HashMap<String, Object> map = new HashMap<>();
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        map.put("beginDate", beginDate);
        map.put("endDate", endDate);
        map.put("type", type);
        map.put("tagId", checkItemData.getTagId());
        map.put("cycleTypes", cycleTypes);
        list.add(map);
        request.put("timingParameters", list);


        HttpManager.getInstance().post(request, SERVICES_ADDRESS + SCENE_PRE_TIME_XIA_FA, requestCode, listener);
    }

    /**
     * partitionId
     *
     * @param partitionId
     * @param requestCode
     * @param listener
     */
    public static void queryRoleListByPartitionId(int partitionId, int requestCode, OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();
        request.put("partitionId", partitionId);
        HttpManager.getInstance().get(request, SERVICES_ADDRESS + ORDER_QUERY_ROLE_BY_PARTITION, requestCode, listener);
    }

    /**
     * 处理完成进入待审核
     *
     * @param orderId
     * @param picIdList
     * @param description
     * @param requestCode
     * @param listener
     */
    public static void orderDoOperator(int orderId, ArrayList<Integer> picIdList, String description, int requestCode, OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();
        request.put("orderId", orderId);
        request.put("imgIdList", picIdList);
        request.put("description", description);
        HttpManager.getInstance().post(request, SERVICES_ADDRESS + DO_OPERATOR, requestCode, listener);
    }

    /**
     * 根据处理人角色查询处理人员列表
     *
     * @param partitionId
     * @param requestCode
     * @param listener
     */
    public static void queryOperatorListByRole(int roleId, int partitionId, int requestCode, OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();
        request.put("roleId", roleId);
        request.put("partitionId", partitionId);
        HttpManager.getInstance().get(request, SERVICES_ADDRESS + ORDER_QUERY_OPERATOR_LIST_BY_ROLE, requestCode, listener);
    }

    //批量控制场景回路（立即下发）
    public static void scenePIliangXiafa(ArrayList<ControllerByIdSceneNameBean.Data.list> list, int requestCode, OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();
        request.put("canChannelControls", list);
        HttpManager.getInstance().put(request, SERVICES_ADDRESS + SCENE_XIAFA_PILIANG, requestCode, listener);
    }

    // 待初审（市级）—>获取用户列表 接口
    public static void orderCityInitAuditGetUserList(String gradeIds, int partitionId, int requestCode, OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();
        request.put("gradeIds", gradeIds);
        request.put("partitionId", partitionId);
        HttpManager.getInstance().get(request, SERVICES_ADDRESS + ORDER_CITY_GET_USER_LIST, requestCode, listener);
    }

    // 初审(市级)
    public static void orderInitAudit(int userLevel, int opinion, int operator, int handleTime, String remark,
                                      int orderId, int operatorRole,
                                      int requestCode, OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();
        if (opinion == 1) {
            request.put("operator", operator);
            request.put("handleTime", handleTime);
            request.put("operatorRole", operatorRole);
        }
        request.put("opinion", opinion);
        request.put("orderId", orderId);
        request.put("remark", remark);
        if (userLevel == 1) {//市级
            HttpManager.getInstance().post(request, SERVICES_ADDRESS + ORDER_INIT_AUDIT_CITY, requestCode, listener);
            return;
        }
        HttpManager.getInstance().post(request, SERVICES_ADDRESS + ORDER_INIT_AUDIT_PART, requestCode, listener);
    }

    //批量控制场景回路（定时下发）
    public static void scenePIliangXiafaTiming(int type, String beginDate, String endDate, ArrayList<Integer> cycleTypes, ArrayList<ControllerByIdSceneNameBean.Data.list> list, int requestCode, OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();
        ArrayList<SceneBatchXiaFaRequestBean.TimerVOS> requestList = new ArrayList<>();


        for (ControllerByIdSceneNameBean.Data.list item : list) {
            SceneBatchXiaFaRequestBean.TimerVOS timerVOS = new SceneBatchXiaFaRequestBean.TimerVOS();
            timerVOS.setNid(item.getNid());
            timerVOS.setType(type);

            ArrayList<SceneBatchXiaFaRequestBean.TimerVOS.TimingParameters> timingParametesList = new ArrayList<>();
            SceneBatchXiaFaRequestBean.TimerVOS.TimingParameters timingParametes = new SceneBatchXiaFaRequestBean.TimerVOS.TimingParameters();
            timingParametes.setBeginDate(beginDate);
            timingParametes.setCycleTypes(cycleTypes);
            timingParametes.setTagId(item.getTagId());
            timingParametes.setType(type);
            timingParametesList.add(timingParametes);

            timerVOS.setTimingParameters(timingParametesList);
            requestList.add(timerVOS);
        }


        request.put("timerVOS", requestList);
        HttpManager.getInstance().post(request, SERVICES_ADDRESS + SCENE_XIAFA_PILIANG_TIMING, requestCode, listener);
    }

    /**
     * 催单
     *
     * @param handleTime
     * @param orderId
     * @param requestCode
     * @param listener
     */
    public static void orderRushOrder(int handleTime, int orderId, int requestCode, OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();
        request.put("handleTime", handleTime);
        request.put("orderId", orderId);
        HttpManager.getInstance().get(request, SERVICES_ADDRESS + ORDER_RUSH_ORDER, requestCode, listener);
    }

    /**
     * 问题申报
     *
     * @param declareReason
     * @param orderId
     * @param fileIds
     * @param requestCode
     * @param listener
     */
    public static void orderErrorReport(String declareReason, int orderId, ArrayList<Integer> fileIds, int requestCode, OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();
        request.put("declareReason", declareReason);
        request.put("orderId", orderId);
        request.put("fileIds", fileIds);
        HttpManager.getInstance().post(request, SERVICES_ADDRESS + ORDER_ERROR_REPORT, requestCode, listener);
    }

    public static void orderDoAuditPartition(int opinion, int orderId, int operator, String remark, int requestCode, OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();
        request.put("opinion", opinion);
        request.put("orderId", orderId);
        request.put("operator", operator);
        request.put("remark", remark);
        HttpManager.getInstance().post(request, SERVICES_ADDRESS + ORDER_DO_AUDIT_PARTITION, requestCode, listener);
    }

    public static void orderDoAuditCity(int opinion, int handleTime, int orderId, String remark, int requestCode, OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();
        request.put("opinion", opinion);
        request.put("handleTime", handleTime);
        request.put("orderId", orderId);
        request.put("operator", "");
        request.put("operatorRole", "");
        request.put("remark", remark);
        HttpManager.getInstance().post(request, SERVICES_ADDRESS + ORDER_DO_AUDIT_CITY, requestCode, listener);
    }


    public static void getUserData(int id, int requestCode, OnHttpResponseListener listener) {
        Map<String, Object> request = new HashMap<>();
        request.put("id", id);
        HttpManager.getInstance().post(request, SERVICES_ADDRESS + GET_USR_DATA, requestCode, listener);
    }


    //account>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    /**
     * 获取用户
     *
     * @param userId
     * @param requestCode
     * @param listener
     */
    public static void getUser(long userId, final int requestCode, final OnHttpResponseListener listener) {
//		Map<String, Object> request = new HashMap<>();
//		request.put(CURRENT_USER_ID, DemoApplication.getInstance().getCurrentUserId());
//		request.put(USER_ID, userId);
//
//		HttpManager.getInstance().get(request, URL_BASE + "/user", requestCode, listener);
    }

    public static final int USER_LIST_RANGE_ALL = 0;
    public static final int USER_LIST_RANGE_RECOMMEND = 1;

    /**
     * 获取用户列表
     *
     * @param range
     * @param pageNum
     * @param requestCode
     * @param listener
     */
    public static void getUserList(int range, int pageNum, final int requestCode, final OnHttpResponseListener listener) {
//		Map<String, Object> request = new HashMap<>();
//		request.put(CURRENT_USER_ID, DemoApplication.getInstance().getCurrentUserId());
//		request.put(RANGE, range);
//		request.put(PAGE_NUM, pageNum);
//
//		HttpManager.getInstance().get(request, URL_BASE + "/user/list", requestCode, listener);
    }


    //user>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //示例代码>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    /**
     * 添加请求参数，value为空时不添加，最快在 19.0 删除
     *
     * @param list
     * @param key
     * @param value
     */
    @Deprecated
    public static void addExistParameter(List<Parameter> list, String key, Object value) {
        if (list == null) {
            list = new ArrayList<Parameter>();
        }
        if (StringUtil.isNotEmpty(key, true) && StringUtil.isNotEmpty(value, true)) {
            list.add(new Parameter(key, value));
        }
    }


    public static void getUserBackModule(Activity activity, int id, HttpListener httpListener) {
        Map<String, Object> request = new HashMap<>();
        request.put("id", id);
        HttpManager.getInstance().postBackModule(activity, request, SERVICES_ADDRESS + GET_USR_DATA, httpListener);
    }


}