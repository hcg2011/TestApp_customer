package com.qy.sdk_rx.RDs;

import android.app.Activity;
import android.app.Application;
import android.graphics.Color;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;

import com.prize.interfaces.RDInterface;
import com.qy.sdk_rx.Datas.AppData;
import com.qy.sdk_rx.Interfaces.Rd;
import com.qy.sdk_rx.RDListener;
import com.qy.sdk_rx.Utils.Log;
import com.qy.sdk_rx.views.RDUi;

import org.json.JSONObject;

import java.lang.reflect.Proxy;

/**
 * @Description
 * @Author huangchangguo
 * @Created 2018/10/19 11:00
 */

public class BeDuiRd implements Rd {
    private Application application;
    private RDUi rdUi = null;

    public BeDuiRd(Application var1) {
        this.application = var1;
    }

    private boolean createActivity(Activity var1) {
        boolean var2;
        try {
            RDUi var3 = new RDUi(var1);
            this.rdUi = var3;
        } catch (Exception var4) {
            var4.printStackTrace();
            var2 = false;
            return var2;
        }

        var2 = true;
        return var2;
    }

    public void showBanner(Activity param1, RDInterface param2) {
        // $FF: Couldn't be decompiled
    }

    public void showInter(Activity var1, RDInterface var2) {
        try {
            Class var4 = this.application.getClassLoader().loadClass("com.baidu.ssp.mobile.interstitial.AdBaiduInterstitial");
            Class var3 = this.application.getClassLoader().loadClass("com.baidu.ssp.mobile.interstitial.AdBaiduInterstitialListener");
            Object var9 = var4.getConstructor(Activity.class, String.class).newInstance(var1, AppData.AD_APPKEY);
            JSONObject var5 = new JSONObject();
            var5.put("type", "inter");
            var5.put("src", "baidu");
            var5.put("obj", var9);
            var5.put("activity", var1);
            RDListener var7 = new RDListener(var5, var2);
            Object var8 = Proxy.newProxyInstance(this.application.getClassLoader(), new Class[]{var3}, var7);
            var9.getClass().getMethod("setAdListener", var3).invoke(var9, var8);
            var9.getClass().getMethod("loadAd", new Class[0]).invoke(var9, new Object[]{});
        } catch (Exception var6) {
            var6.printStackTrace();
            if (var2 != null) {
                var2.onClose();
                var2.showError(Log.getErrorLog(var6));
            }
        }

    }

    public void showLog(boolean var1) {
    }

    public void showOpen(final Activity var1, final RDInterface var2) {
        this.createActivity(var1);
        AppData.handler.postDelayed(new Runnable() {
            public void run() {
                try {
                    LinearLayout var3 = new LinearLayout(var1);
                    LayoutParams var4 = new LayoutParams(-1, -2);
                    var3.setLayoutParams(var4);
                    var3.setOrientation(LinearLayout.VERTICAL);
                    RelativeLayout var2x = new RelativeLayout(var1);
                    var2x.setBackgroundColor(-16711936);
                    final Button var1x = new Button(var1);
                    var1x.setText("关闭 5");
                    LayoutParams var5 = new LayoutParams(-2, -2);
                    var5.gravity = 5;
                    var5.topMargin = 20;
                    var5.rightMargin = 20;
                    var1x.setLayoutParams(var5);
                    var1x.setBackgroundColor(Color.parseColor("#109b9694"));
                    OnClickListener var14 = new OnClickListener() {
                        public void onClick(View var1x) {
                            RDUi.isShow = false;
                        }
                    };
                    var1x.setOnClickListener(var14);
                    var3.addView(var1x);
                    RelativeLayout.LayoutParams var15 = new RelativeLayout.LayoutParams(-1, 100);
                    var15.addRule(12);
                    var15 = new RelativeLayout.LayoutParams(-1, -1);
                    var15.addRule(2, 101);
                    com.qy.sdk_rx.RDs.BeDuiRd.this.rdUi.getActivity().addContentView(var2x, var15);
                    com.qy.sdk_rx.RDs.BeDuiRd.this.rdUi.getActivity().addContentView(var3, var4);
                    Class var11 = com.qy.sdk_rx.RDs.BeDuiRd.this.application.getClassLoader().loadClass("com.baidu.ssp.mobile.splash.AdBaiduSplash");
                    Class var9 = com.qy.sdk_rx.RDs.BeDuiRd.this.application.getClassLoader().loadClass("com.baidu.ssp.mobile.splash.AdBaiduSplashListener");
                    Object var7 = var11.getConstructor(Activity.class, String.class, ViewGroup.class).newInstance(var1, AppData.AD_APPKEY, var2x);
                    JSONObject var12 = new JSONObject();
                    var12.put("type", "open");
                    var12.put("src", "baidu");
                    var12.put("ui", com.qy.sdk_rx.RDs.BeDuiRd.this.rdUi);
                    RDListener var16 = new RDListener(var12, var2);
                    Object var13 = Proxy.newProxyInstance(com.qy.sdk_rx.RDs.BeDuiRd.this.application.getClassLoader(), new Class[]{var9}, var16);
                    var7.getClass().getMethod("setAdListener", var9).invoke(var7, var13);
                    Runnable var10 = new Runnable() {
                        public void run() {
                            int var1xx = 10;

                            while (true) {
                                final int var2x = var1xx - 1;
                                if (var1xx <= 0) {
                                    com.qy.sdk_rx.RDs.BeDuiRd.this.rdUi.finish();
                                    return;
                                }

                                try {
                                    Handler var3 = AppData.handler;
                                    Runnable var4 = new Runnable() {
                                        public void run() {
                                            var1x.setText("关闭 " + var2x);
                                        }
                                    };
                                    var3.post(var4);
                                    Thread.sleep(1000L);
                                } catch (InterruptedException var5) {
                                    var5.printStackTrace();
                                    var1xx = var2x;
                                    continue;
                                }

                                var1xx = var2x;
                            }
                        }
                    };
                    Thread var8 = new Thread(var10);
                    var8.start();
                } catch (Exception var6) {
                    var6.printStackTrace();
                    if (var2 != null) {
                        var2.onClose();
                        com.qy.sdk_rx.RDs.BeDuiRd.this.rdUi.finish();
                    }
                }

            }
        }, 100L);
    }
}

