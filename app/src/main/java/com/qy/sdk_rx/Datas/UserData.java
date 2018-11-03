package com.qy.sdk_rx.Datas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * @Description
 * @Author huangchangguo
 * @Created 2018/10/19 10:58
 */

public class UserData {
    private String brand;
    private Integer bsc_cid;
    private Integer bsc_lac;
    private String iccid;
    private String imei = "-1";
    private String imsi = "-1";
    private String operator;
    private String osBuild;
    private String phoneType;

    public UserData() {
    }

    private static String bytesToString(byte[] var0) {
        String var4;
        if (var0 != null && var0.length != 0) {
            StringBuilder var3 = new StringBuilder();
            int var2 = var0.length;

            for (int var1 = 0; var1 < var2; ++var1) {
                var3.append(String.format("%02X:", var0[var1]));
            }

            if (var3.length() > 0) {
                var3.deleteCharAt(var3.length() - 1);
            }

            var4 = var3.toString();
        } else {
            var4 = null;
        }

        return var4;
    }

    private static int getCid(Context param0) {
        return 0;
    }

    public static UserData getDataBean(Context var0) {
        Context var1 = var0;
        if (var0 == null) {
            var1 = AppData.context;
        }

        TelephonyManager var3 = (TelephonyManager) var1.getSystemService(Context.TELEPHONY_SERVICE);
        com.qy.sdk_rx.Datas.UserData var2 = new com.qy.sdk_rx.Datas.UserData();
        var2.setOsBuild(VERSION.RELEASE);
        if (ActivityCompat.checkSelfPermission(var1, "android.permission.READ_PHONE_STATE") != 0) {
            var2.setImei("NOT_PERMISSION");
        } else {
            var2.setImei(var3.getDeviceId());
        }

        if (ActivityCompat.checkSelfPermission(var1, "android.permission.READ_PHONE_STATE") == 0) {
            var2.setImsi(var3.getSubscriberId());
        } else {
            var2.setImsi("NOT_PERMISSION");
        }

        var2.setBrand(Build.BRAND);
        var2.setPhoneType(Build.MODEL);
        var2.setBsc_cid(getCid(var1));
        var2.setBsc_lac(getLac(var1));
        var2.setOperator(getSimType(var1));
        var2.setIccid(getIccid(var1));
        return var2;
    }

    @SuppressLint("MissingPermission")
    private static String getIccid(Context var0) {
        TelephonyManager var2 = (TelephonyManager) var0.getSystemService(Context.TELEPHONY_SERVICE);
        String var3;
        try {
            var3 = var2.getSimSerialNumber();
        } catch (Exception var1) {
            var3 = "NOT　PERMISSION";
        }

        return var3;
    }

    private static int getLac(Context param0) {
        return 0;
    }

    public static String getMachineHardwareAddress() {
        Enumeration var1 = null;

        label41:
        {
            Enumeration var0;
            try {
                var0 = NetworkInterface.getNetworkInterfaces();
            } catch (SocketException var6) {
                var6.printStackTrace();
                break label41;
            }

            var1 = var0;
        }

        String var7 = "NOT_MAC";
        NetworkInterface var2 = null;

        while (var1.hasMoreElements()) {
            NetworkInterface var4 = (NetworkInterface) var1.nextElement();

            String var3;
            try {
                var3 = bytesToString(var4.getHardwareAddress());
            } catch (SocketException var5) {
                var5.printStackTrace();
                var2 = var4;
                continue;
            }

            var7 = var3;
            var2 = var4;
            if (var3 == null) {
                var7 = var3;
                var2 = var4;
            }
        }

        String var8 = var7;
        if (var2 != null) {
            var8 = var7;
            if (var2.getName().equals("wlan0")) {
                var8 = var7.replace(":", "");
            }
        }

        var7 = var8;
        if (var8 == null) {
            var7 = "02:00:00:00:00:01";
        }

        return var7;
    }

    private static String getSimType(Context var0) {
        String var1 = ((TelephonyManager) var0.getSystemService(Context.TELEPHONY_SERVICE)).getSimOperator();
        String var2;
        if (TextUtils.isEmpty(var1)) {
            var2 = "4";
        } else {
            var2 = "1";
            if (!var1.equals("46001") && !"46006".equals(var1)) {
                if (var1.equals("46003") || "46005".equals(var1) || "46011".equals(var1)) {
                    var2 = "3";
                }
            } else {
                var2 = "2";
            }
        }

        return var2;
    }

    public String getBrand() {
        return this.brand;
    }

    public Integer getBsc_cid() {
        return this.bsc_cid;
    }

    public Integer getBsc_lac() {
        return this.bsc_lac;
    }

    public int getDPI() {
        return com.qy.sdk_rx.Datas.AppData.context.getResources().getDisplayMetrics().densityDpi;
    }

    public String getIccid() {
        return this.iccid;
    }

    public String getImei() {
        return this.imei;
    }

    public String getImsi() {
        if (this.imsi == null) {
            this.imsi = "-1";
        }

        return this.imsi;
    }

    public String getOperator() {
        return this.operator;
    }

    public String getOsBuild() {
        return this.osBuild;
    }

    public String getPhoneType() {
        return this.phoneType;
    }

    public String getScreenSize() {
        DisplayMetrics var1 = AppData.context.getResources().getDisplayMetrics();
        return var1.widthPixels + "x" + var1.heightPixels;
    }

    public void setBrand(String var1) {
        this.brand = var1;
    }

    public void setBsc_cid(Integer var1) {
        this.bsc_cid = var1;
    }

    public void setBsc_lac(Integer var1) {
        this.bsc_lac = var1;
    }

    public void setIccid(String var1) {
        this.iccid = var1;
    }

    public void setImei(String var1) {
        this.imei = var1;
    }

    public void setImsi(String var1) {
        this.imsi = var1;
    }

    public void setOperator(String var1) {
        this.operator = var1;
    }

    public void setOsBuild(String var1) {
        this.osBuild = var1;
    }

    public void setPhoneType(String var1) {
        this.phoneType = var1;
    }
}
