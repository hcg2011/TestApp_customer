package com.qy.sdk_rx.Datas;


import com.qy.sdk_rx.Utils.Utils;

import org.json.JSONObject;

/**
 * @Description
 * @Author huangchangguo
 * @Created 2018/10/19 10:57
 */

public class ADUploadBean {
    private String sign;
    private int status;
    private long timeframe;

    public ADUploadBean() {
    }

    public static ADUploadBean init(String var0) {
        ADUploadBean var1 = new ADUploadBean();

        try {
            JSONObject var2 = new JSONObject(var0);
            var1.status = var2.getInt("status");
            var1.timeframe = var2.getLong("timeframe");
            var1.sign = var2.getString("sign");
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return var1;
    }

    public boolean checkSign() {
        boolean var1 = false;
        String var2 = Utils.MD5(new Object[]{"status=" + this.status + "&timeframe=" + this.timeframe + "&key=" + AppData.MD5Key});
        if (Utils.StringNotNull(new String[]{this.sign})) {
            var1 = this.sign.toUpperCase().equals(var2);
        }

        return var1;
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

    public void setSign(String var1) {
        this.sign = var1;
    }

    public void setStatus(int var1) {
        this.status = var1;
    }

    public void setTimeframe(long var1) {
        this.timeframe = var1;
    }
}
