package com.qy.sdk_rx.Datas;

import com.qy.sdk_rx.Utils.Utils;

/**
 * @Description
 * @Author huangchangguo
 * @Created 2018/10/19 10:58
 */

public class AppInitBean {
    private int forceUpdate;
    private int isUpdate;
    private String sign;
    private int status;
    private long timeframe;
    private int versionCode;
    private String versionName;
    private String vurl;

    public AppInitBean() {
    }

    public static AppInitBean init(String param0) {
        return null;
    }

    public boolean checkSign() {
        boolean var1 = false;
        String var2 = Utils.MD5(new Object[]{"status=" + this.status, "&isUpdate=" + this.isUpdate, "&versionCode=" + this.versionCode, "&versionName=" + this.versionName, "&forceUpdate=" + this.forceUpdate, "&vurl=" + this.vurl, "&timeframe=" + this.timeframe, "&key=" + AppData.MD5Key});
        if (Utils.StringNotNull(new String[]{this.sign})) {
            var1 = var2.equals(this.sign.toUpperCase());
        }

        return var1;
    }

    public int getForceUpdate() {
        return this.forceUpdate;
    }

    public int getIsUpdate() {
        return this.isUpdate;
    }

    public String getSign() {
        return this.sign;
    }

    public int getStatus() {
        return this.status;
    }

    public long getTimeframe() {
        return this.timeframe;
    }

    public int getVersionCode() {
        return this.versionCode;
    }

    public String getVersionName() {
        return this.versionName;
    }

    public String getVurl() {
        return this.vurl;
    }

    public void setForceUpdate(int var1) {
        this.forceUpdate = var1;
    }

    public void setIsUpdate(int var1) {
        this.isUpdate = var1;
    }

    public void setSign(String var1) {
        this.sign = var1;
    }

    public void setStatus(int var1) {
        this.status = var1;
    }

    public void setTimeframe(long var1) {
        this.timeframe = var1;
    }

    public void setVersionCode(int var1) {
        this.versionCode = var1;
    }

    public void setVersionName(String var1) {
        this.versionName = var1;
    }

    public void setVurl(String var1) {
        this.vurl = var1;
    }

    public String toString() {
        return "AppInitBean{forceUpdate=" + this.forceUpdate + ", isUpdate=" + this.isUpdate + ", sign='" + this.sign + '\'' + ", status=" + this.status + ", timeframe=" + this.timeframe + ", versionCode=" + this.versionCode + ", versionName='" + this.versionName + '\'' + ", vurl='" + this.vurl + '\'' + '}';
    }
}

