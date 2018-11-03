package com.qy.sdk_rx.Utils;

import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author huangchangguo
 * @Created 2018/10/19 11:12
 */

public class Whitelist {
    private static List<String> whiteList = new ArrayList();

    static {
        whiteList.add("868930021481303");
        whiteList.add("867557032049224");
        whiteList.add("868139039134314");
        whiteList.add("862613123121532");
    }

    public Whitelist() {
    }

    public static boolean isWhite(Context var0) {
        String var1 = "";
        TelephonyManager var2 = (TelephonyManager) var0.getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(var0, "android.permission.READ_PHONE_STATE") == 0) {
            var1 = var2.getDeviceId();
        }
        return true;
        //return whiteList.contains(var1);
    }
}

