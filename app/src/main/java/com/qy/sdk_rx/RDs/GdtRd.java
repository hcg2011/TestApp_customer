package com.qy.sdk_rx.RDs;

import android.app.Activity;
import android.app.Application;
import android.view.ViewGroup;

import com.prize.interfaces.RDInterface;
import com.qy.sdk_rx.Datas.AppData;
import com.qy.sdk_rx.Interfaces.Rd;
import com.qy.sdk_rx.RDListener;
import com.qy.sdk_rx.Utils.Log;
import com.qy.sdk_rx.views.RDUi;

import org.json.JSONObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description
 * @Author huangchangguo
 * @Created 2018/10/19 11:00
 */

public class GdtRd implements Rd {
    private static boolean isBanner = false;
    private Application application;

    public GdtRd(Application var1) {
        this.application = var1;
    }

    private void hook() {
        try {
            Field var2 = this.application.getClassLoader().loadClass("com.qq.e.comm.constants.CustomPkgConstants").getDeclaredField("b");
            var2.setAccessible(true);
            String var1 = (String)var2.get((Object)null);
            StringBuilder var3 = new StringBuilder();
            Log.i(this, var3.append("原本 b = ").append(var1).toString());
            var1.replace("qq", "qr").replace("ADActivity", "RDActivity");
            var2.set((Object)null, "com.qy.sdk.views.ProxyActivity");
            String var6 = (String)var2.get((Object)null);
            StringBuilder var5 = new StringBuilder();
            Log.i(this, var5.append("现在 b = ").append(var6).toString());
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    public void showBanner(Activity param1, RDInterface param2) {
        // $FF: Couldn't be decompiled
    }

    public void showInter(Activity var1, RDInterface var2) {
        try {
            Class var4 = this.application.getClassLoader().loadClass("com.qq.e.ads.interstitial.InterstitialAD");
            Class var3 = this.application.getClassLoader().loadClass("com.qq.e.ads.interstitial.InterstitialADListener");
            Object var7 = var4.getConstructor(Activity.class, String.class, String.class).newInstance(var1, AppData.AD_APPID, AppData.AD_APPKEY);
            JSONObject var5 = new JSONObject();
            var5.put("listener", var7);
            var5.put("type", "inter");
            var5.put("src", "gdt");
            RDListener var9 = new RDListener(var5, var2);
            Object var10 = Proxy.newProxyInstance(this.application.getClassLoader(), new Class[]{var3}, var9);
            Method var8 = var7.getClass().getMethod("setADListener", var3);
            var8.setAccessible(true);
            var8.invoke(var7, var10);
            var8 = var7.getClass().getMethod("loadAD");
            var8.setAccessible(true);
            var8.invoke(var7);
        } catch (Exception var6) {
            var6.printStackTrace();
            var2.showError(Log.getErrorLog(var6));
            var2.onClose();
        }

    }

    public void showLog(boolean var1) {
    }

    public void showOpen(final Activity var1, final RDInterface var2) {
        isBanner = false;
        final RDUi var3 = new RDUi(var1);
        AppData.handler.postDelayed(new Runnable() {
            public void run() {
                try {
                    Log.i(this, "启动开屏");
                    Class var2x = com.qy.sdk_rx.RDs.GdtRd.this.application.getClassLoader().loadClass("com.qq.e.ads.splash.SplashAD");
                    Class var1x = com.qy.sdk_rx.RDs.GdtRd.this.application.getClassLoader().loadClass("com.qq.e.ads.splash.SplashADListener");
                    Constructor var7 = var2x.getConstructor(Activity.class, ViewGroup.class, String.class, String.class, var1x);
                    JSONObject var4 = new JSONObject();
                    var4.put("type", "open");
                    var4.put("src", "gdt");
                    var4.put("ui", var3);
                    RDListener var3x = new RDListener(var4, var2);
                    Object var6 = Proxy.newProxyInstance(com.qy.sdk_rx.RDs.GdtRd.this.application.getClassLoader(), new Class[]{var1x}, var3x);
                    var7.newInstance(var1, var3.getADView(), AppData.AD_APPID, AppData.AD_APPKEY, var6);
                } catch (Exception var5) {
                    var5.printStackTrace();
                    var2.showError(Log.getErrorLog(var5));
                    var2.onClose();
                    var3.finish();
                }

            }
        }, 1000L);
    }
}

