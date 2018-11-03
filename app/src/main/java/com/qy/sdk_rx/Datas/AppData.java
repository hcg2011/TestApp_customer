package com.qy.sdk_rx.Datas;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * @Description
 * @Author huangchangguo
 * @Created 2018/10/19 10:58
 */
public class AppData {
    public static String AD_APPID = "";
    public static String AD_APPKEY = "";
    public static String AES_KEY = "langtianaes20188";
    public static String APPID = "123";
    public static String APPKEY;
    public static String APP_CHANNELID = "NOT_ChannelId";
    public static String APP_VERSION_CODE = "2000";
    public static String APP_VERSION_NAME = "2.0";
    public static String MD5Key = "langtiansdk20188";
    public static Object activity;
    public static ADBean adBean = new ADBean();
    public static Activity appActivity;
    public static Application application;
    public static Context context;
    public static Handler handler;
    public static boolean isDebug = false;

    public AppData() {
    }
}
