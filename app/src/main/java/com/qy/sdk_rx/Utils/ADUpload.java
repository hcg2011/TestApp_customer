package com.qy.sdk_rx.Utils;

import android.os.Build.VERSION;

import com.qy.sdk_rx.Datas.AppData;
import com.qy.sdk_rx.Datas.UserData;
import com.qy.sdk_rx.Interfaces.HttpInterface;

import org.json.JSONObject;

/**
 * @Description
 * @Author huangchangguo
 * @Created 2018/10/19 11:10
 */

public class ADUpload {
    public static final int BANNER = 4;
    public static final int END = 3;
    public static final int INTER = 2;
    public static final int OPEN = 1;
    public static final int RD_TYPE_BAIDU = 2;
    public static final int RD_TYPE_GDT = 1;
    public static final int RD_TYPE_HCM = 3;
    public static final int RD_TYPE_LT = 4;
    private static int agent = -1;
    private static int model = -1;

    public ADUpload() {
    }

    public static void upload(int var0, int var1, int var2, int var3, int var4) {
        model = var0;
        agent = var1;
        UserData var5 = UserData.getDataBean(AppData.context);

        try {
            JSONObject var6 = new JSONObject();
            var6.put("requestId", "1002");
            var6.put("sdkVersion", AppData.APP_VERSION_CODE);
            var6.put("agent", var1);
            var6.put("model", var0);
            var6.put("appId", AppData.APPID);
            var6.put("channelId", AppData.APP_CHANNELID);
            var6.put("showNum", var2);
            var6.put("clickNum", var3);
            var6.put("clickLiveNum", var4);
            var6.put("imsi", var5.getImsi());
            var6.put("imei", var5.getImei());
            var6.put("mac", UserData.getMachineHardwareAddress());
            var6.put("gatewayType", var5.getOperator());
            var6.put("product", var5.getPhoneType());
            var6.put("networkInfo", com.qy.sdk_rx.Utils.Utils.getNetworkState(AppData.context));
            var6.put("androidVersion", VERSION.SDK_INT);
            var6.put("timeframe", System.currentTimeMillis());
            var6.put("sign", Utils.MD5(var6));
            var6.remove("key");
            HttpTools.init().httpPost("http://ad.quwin.cn/interfaces.do", var6, (HttpInterface)null);
        } catch (Exception var7) {
            var7.printStackTrace();
        }

    }
}

