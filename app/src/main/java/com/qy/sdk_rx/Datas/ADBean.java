package com.qy.sdk_rx.Datas;


import com.qy.sdk_rx.Utils.Log;
import com.qy.sdk_rx.Utils.Utils;

/**
 * @Description
 * @Author huangchangguo
 * @Created 2018/10/19 10:57
 */
public class ADBean {
    private String banner = ",,";
    private int isShow = -1;
    private String quit = ",,";
    private String screen = ",,";
    private String sign = "";
    private String start = ",,";
    private int status = -1;
    private String str;
    private Long timeframe = 0L;

    public ADBean() {
    }

    public static ADBean init(String param0) {
       return null;
    }

    public boolean checkSign() {
        boolean var1 = false;
        String var2 = Utils.MD5(new Object[]{"status=" + this.status, "&isShow=" + this.isShow + "&screen=" + this.screen + "&start=" + this.start + "&quit=" + this.quit + "&banner=" + this.banner + "&timeframe=" + this.timeframe + "&key=" + AppData.MD5Key});
        Log.i(this, "签名： " + this.sign + " " + var2);
        if (Utils.StringNotNull(new String[]{this.sign})) {
            var1 = this.sign.toUpperCase().equals(var2);
        }

        return var1;
    }

    public String getBanner() {
        return this.banner;
    }

    public int getIsShow() {
        return this.isShow;
    }

    public String getQuit() {
        return this.quit;
    }

    public String getScreen() {
        return this.screen;
    }

    public String getSign() {
        return this.sign;
    }

    public String getStart() {
        return this.start;
    }

    public int getStatus() {
        return this.status;
    }

    public String getStr() {
        return this.str;
    }

    public Long getTimeframe() {
        return this.timeframe;
    }

    public void setBanner(String var1) {
        this.banner = var1;
    }

    public void setIsShow(int var1) {
        this.isShow = var1;
    }

    public void setQuit(String var1) {
        this.quit = var1;
    }

    public void setScreen(String var1) {
        this.screen = var1;
    }

    public void setSign(String var1) {
        this.sign = var1;
    }

    public void setStart(String var1) {
        this.start = var1;
    }

    public void setStatus(int var1) {
        this.status = var1;
    }

    public void setTimeframe(Long var1) {
        this.timeframe = var1;
    }

    public String toString() {
        return "ADBean{banner='" + this.banner + '\'' + ", isShow=" + this.isShow + ", quit='" + this.quit + '\'' + ", screen='" + this.screen + '\'' + ", sign='" + this.sign + '\'' + ", start='" + this.start + '\'' + ", status=" + this.status + ", timeframe=" + this.timeframe + '}';
    }
}
