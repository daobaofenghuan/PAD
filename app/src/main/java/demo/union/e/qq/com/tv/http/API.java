package demo.union.e.qq.com.tv.http;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import demo.union.e.qq.com.tv.beans.Details;


/**
 * Created by 他的猫 on 2017/5/10.
 */

public class API {
    //    public static final String APIBase = "http://huanke.bcard.vip/api/app/api/";
    public static final String APIBase = "http://www.hcocloud.com/dash/app/api/";

    public static final String queryDeviceList = APIBase + "queryDeviceList";
    public static final String queryDetailByDeviceId = APIBase + "queryDetailByDeviceId";
    public static final String getHistoryData = APIBase + "getHistoryData";

    public static final String bind = APIBase + "bind?IMEI=";
    public static String currentDevice = "";
    public static String sendFuncS = APIBase + "sendFunc";
    public static String obtainApk = APIBase + "obtainApk";

    //    *****************************系统参数
    //图片地址
    public static String icon = "";
    //视频地址
    public static String videoUrl = "https://idcfota.oss-cn-hangzhou.aliyuncs.com/DASKLIMA/zuixin35.mp4";
    //视频缩略图
    public static String videoCover = "";

    //介紹
    public static String memo = "";
    //分組名
    public static String groupName = "";
    //分组列表
    public static List<Details.DataBean.GroupDataListBean> GroupDataListBean = new ArrayList<>();
    //分组列表
    public static List<Details.DataBean> DataBean = new ArrayList<>();
    //分組位置
    public static int Groupposition = 0;
    public static int JG_TIME = 500;

    //分組名
    public static String GroupID = "";
    public static boolean APIBoolen = true;


    /**
     * 获取分组 设备列表
     *
     * @para
     */
    public static void queryDeviceList(Okhttp.Objectcallback handler) {


        Okhttp.get(queryDeviceList, null, handler);

    }

    /**
     * 通过ID查设备
     *
     * @para
     */
    public static void queryDetailByDeviceId(String deviceId, Okhttp.Objectcallback handler) {
        Map<String, String> map = new HashMap<>();
        map.put("deviceId", deviceId);


        Okhttp.post(queryDetailByDeviceId, map, handler);

    }

    /**
     * 获取24小时记录
     *
     * @param deviceId
     * @param handler
     */
    public static void getHistoryData(String deviceId, Okhttp.Objectcallback handler) {
        Map<String, String> map = new HashMap<>();
        map.put("deviceId", deviceId);
        Okhttp.post(getHistoryData, map, handler);

    }


    /**
     * 编辑名称
     *
     * @param deviceId
     * @param deviceName
     * @param handler
     */
    public static void editDevice(String deviceId, String deviceName, Okhttp.Objectcallback handler) {

        Log.e("更改", APIBase + "editDevice?deviceId=" + deviceId + "&" + "deviceName=" + deviceName);
        Okhttp.get(APIBase + "editDevice?deviceId=" + deviceId + "&" + "deviceName=" + deviceName, null, handler);

    }

    /**
     * 设置挡位
     *
     * @param handler
     */
    public static void setgear(String deviceId, String funcId, Okhttp.Objectcallback handler) {
//        Map<String, String> map = new HashMap<>();
//        map.put("deviceId", deviceId);
//        map.put("funcId", funcId);
        Log.e("设置档位", "setgear：" + sendFuncS + "?deviceId=" + deviceId + "&funcId=" + funcId);
        Okhttp.get(sendFuncS + "?deviceId=" + deviceId + "&funcId=" + funcId, null, handler);

    }

    /**
     * 升级
     *
     * @param handler
     */
    public static void obtainApk(Okhttp.Objectcallback handler) {


        Okhttp.get(obtainApk, null, handler);

    }
}
