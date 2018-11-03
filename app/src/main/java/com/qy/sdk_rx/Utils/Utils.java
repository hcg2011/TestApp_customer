package com.qy.sdk_rx.Utils;

import android.content.Context;
import android.os.Environment;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.security.MessageDigest;
import java.util.Iterator;

/**
 * @Description
 * @Author huangchangguo
 * @Created 2018/10/19 11:12
 */

public class Utils {
    private static final String TAG = com.qy.sdk_rx.Utils.Utils.class.getName();

    public Utils() {
    }

    public static String MD5(JSONObject var0) {
        String var6 = jsonToString(var0);
        com.qy.sdk_rx.Utils.Log.i(TAG, "json签名:" + var6);

        byte[] var4;
        try {
            var4 = MessageDigest.getInstance("MD5").digest(var6.getBytes("UTF-8"));
        } catch (Exception var5) {
            throw new RuntimeException("Huh, MD5 should be supported?", var5);
        }

        StringBuilder var7 = new StringBuilder(var4.length * 2);
        int var2 = var4.length;

        for (int var1 = 0; var1 < var2; ++var1) {
            byte var3 = var4[var1];
            if ((var3 & 255) < 16) {
                var7.append("0");
            }

            var7.append(Integer.toHexString(var3 & 255));
        }

        return var7.toString().toUpperCase();
    }

    public static String MD5(Object... var0) {
        byte var2 = 0;
        String var5 = "";
        int var3 = var0.length;

        int var1;
        String var4;
        for (var1 = 0; var1 < var3; var5 = var4) {
            Object var6 = var0[var1];
            var4 = var5;
            if (StringNotNull(var6.toString())) {
                var4 = var5 + var6;
            }

            ++var1;
        }

        com.qy.sdk_rx.Utils.Log.i(TAG, "字符串签名:" + var5);

        byte[] var8;
        try {
            var8 = MessageDigest.getInstance("MD5").digest(var5.getBytes("UTF-8"));
        } catch (Exception var7) {
            throw new RuntimeException("Huh, MD5 should be supported?", var7);
        }

        StringBuilder var10 = new StringBuilder(var8.length * 2);
        var3 = var8.length;

        for (var1 = var2; var1 < var3; ++var1) {
            byte var9 = var8[var1];
            if ((var9 & 255) < 16) {
                var10.append("0");
            }

            var10.append(Integer.toHexString(var9 & 255));
        }

        return var10.toString().toUpperCase();
    }

    public static boolean StringNotNull(String... var0) {
        boolean var4 = false;
        int var2 = var0.length;
        int var1 = 0;

        boolean var3;
        while (true) {
            if (var1 >= var2) {
                var3 = true;
                break;
            }

            String var5 = var0[var1];
            var3 = var4;
            if (var5 == null) {
                break;
            }

            if ("".equals(var5)) {
                var3 = var4;
                break;
            }

            ++var1;
        }

        return var3;
    }

    public static void delete(String var0) {
        File var3 = new File(var0);
        if (var3.exists()) {
            if (var3.isDirectory() && var3.list() != null) {
                File[] var4 = var3.listFiles();
                int var2 = var4.length;

                for (int var1 = 0; var1 < var2; ++var1) {
                    delete(var4[var1].getAbsolutePath());
                }
            }

            Log.i(TAG, "删除文件 = " + var3.getAbsolutePath());
            var3.delete();
        }

    }

    public static JSONObject getAssetsVer(Context param0) {
        return null;
    }

    public static String getNetworkState(Context param0) {
        return null;
    }

    public static String getSDKPath() {
        File var0 = new File(Environment.getExternalStorageDirectory().getPath() + "/ltsdk/");
        if (!var0.exists()) {
            var0.mkdirs();
        }

        return var0.getAbsolutePath();
    }

    public static boolean hasSimCard(Context var0) {
        int var1 = ((TelephonyManager) var0.getSystemService(Context.TELEPHONY_SERVICE)).getSimState();
        boolean var2 = true;
        switch (var1) {
            case 0:
                var2 = false;
                break;
            case 1:
                var2 = false;
        }

        return var2;
    }

    public static boolean isAirplaneModeOn(Context var0) {
        boolean var1 = true;
        if (System.getInt(var0.getContentResolver(), "airplane_mode_on", 0) != 1) {
            var1 = false;
        }

        return var1;
    }

    private static String jsonToString(JSONObject var0) {
        String var2;
        if (var0 != null && !var0.toString().equals("{}")) {
            String var1 = "";
            Iterator var3 = var0.keys();

            while (true) {
                var2 = var1;
                if (!var3.hasNext()) {
                    break;
                }

                String var4 = (String) var3.next();

                try {
                    StringBuilder var6 = new StringBuilder();
                    var2 = var6.append(var1).append(var0.getString(var4)).toString();
                } catch (JSONException var5) {
                    var5.printStackTrace();
                    var1 = var1 + "";
                    continue;
                }

                var1 = var2;
            }
        } else {
            var2 = "";
        }

        return var2;
    }

    public static void setAssetsVer(Context param0, String param1, int param2) {
        // $FF: Couldn't be decompiled
    }
}
