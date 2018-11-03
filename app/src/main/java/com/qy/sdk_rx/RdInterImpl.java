package com.qy.sdk_rx;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;

import com.prize.interfaces.RDInterface;
import com.qy.sdk_rx.Datas.AppData;
import com.qy.sdk_rx.Interfaces.Rd;
import com.qy.sdk_rx.Utils.AppUtils;
import com.qy.sdk_rx.Utils.Log;
import com.qy.sdk_rx.Utils.QyUtils;
import com.qy.sdk_rx.Utils.SDKPermissions;
import com.qy.sdk_rx.Utils.Whitelist;

import java.io.File;

/**
 * @Description
 * @Author huangchangguo
 * @Created 2018/10/19 11:07
 */

public class RdInterImpl {
    private static RdInterImpl rd = null;
    private static String rdData = null;
    private Application application;
    private Rd gdtRd;
    private QyUtils qyRd = null;

    public RdInterImpl(Application var1) {
        this.application = var1;
        AppData.application = var1;
        Log.i(this, "实例化成功");
        rd = this;
    }

    private boolean switchAds(String param1) {
        return false;
    }

    private void update(final Context var1) {
        AppData.handler.postDelayed(new Runnable() {
            public void run() {
                AppUtils.update();
                if (AppData.adBean != null) {
                    AppUtils.updateAssets(var1);
                }

                File[] var4 = (new File(var1.getFilesDir() + "/plugs/")).listFiles();
                int var2 = var4.length;

                for(int var1x = 0; var1x < var2; ++var1x) {
                    File var3 = var4[var1x];
                    if (var3.getName().contains(".prof")) {
                        var3.delete();
                    }
                }

            }
        }, 500L);
    }

    public void exit(Activity var1, RDInterface var2) {
        if (!this.switchAds(AppData.adBean.getQuit())) {
            var2.onClose();
        } else {
            this.gdtRd.showOpen(var1, var2);
        }

    }

    public String getVersion() {
        return AppData.APP_VERSION_CODE;
    }

    public Object init(Activity var1, String var2, String var3, Handler var4) {
        try {
            ActivityCompat.requestPermissions(var1, SDKPermissions.requestPermissions, 1);
        } catch (Exception var5) {
            var5.printStackTrace();
            throw new RuntimeException("动态权限申请失败，请开发者自行兼容6.0并实现动态权限申请!", var5);
        }
        AppData.handler = var4;
        AppData.application = this.application;
        AppData.context = var1;
        AppData.APPID = var3;
        AppData.APP_CHANNELID = var2;
        AppData.isDebug = Whitelist.isWhite(var1);
        AppUtils.getADConfig();
        this.update(var1);
        Log.toast(var1, "广告SDK加载完成：" + AppData.APP_VERSION_CODE);
        return this;
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public void setDebug(boolean var1) {
        Log.isDebug = var1;
    }

    public void setLog(boolean var1) {
        Log.isDebug = var1;
        System.out.println("日志模式:" + var1);
    }

    public void showBanner(Activity var1, RDInterface var2) {
        if (!this.switchAds(AppData.adBean.getBanner())) {
            var2.onClose();
        } else {
            this.gdtRd.showBanner(var1, var2);
        }

    }

    public void showInter(Activity var1, RDInterface var2) {
        if (!this.switchAds(AppData.adBean.getScreen())) {
            var2.onClose();
        } else {
            this.gdtRd.showInter(var1, var2);
        }

    }

    public void showOpen(Activity var1, RDInterface var2) {
        if (!this.switchAds(AppData.adBean.getStart())) {
            var2.onClose();
        } else {
            this.gdtRd.showOpen(var1, var2);
        }

    }
}

