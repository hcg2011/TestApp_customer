package com.qy.sdk_rx.views;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.qy.sdk_rx.Interfaces.RDADKActivity;
import com.qy.sdk_rx.Utils.Log;

/**
 * @Description
 * @Author huangchangguo
 * @Created 2018/10/19 11:06
 */

public class RDUi implements RDADKActivity {
    public static Activity activity;
    private static boolean isCloseAD = false;
    public static boolean isShow = true;
    private static LinearLayout ui;
    public Handler handler;
    private View view;

    public RDUi() {
    }

    public RDUi(Activity var1) {
        this.start(var1);
    }

    public RDUi(Activity var1, boolean var2) {
        this.start(var1);
        isCloseAD = var2;
    }

    private View createMainUI() {
        Log.i(this, "准备创建UI");
        LinearLayout var1;
        if (ui != null) {
            var1 = ui;
        } else {
            ui = new LinearLayout(activity);
            LayoutParams var2 = new LayoutParams(-2, -2);
            ui.setLayoutParams(var2);
            ui.setBackgroundColor(Color.parseColor("#00FFFFFF"));
            this.view = new View(activity);
            var2 = new LayoutParams(-1, -2);
            this.view.setLayoutParams(var2);
            ui.addView(this.view);
            this.view.setTag("ad");
            Log.i(this, "创建UI");
            var1 = ui;
        }

        return var1;
    }

    private void start(Activity param1) {
        // $FF: Couldn't be decompiled
    }

    public View createView(Activity var1) {
        activity = var1;
        activity.getWindow().setFlags(1024, 1024);
        activity.requestWindowFeature(1);
        isShow = true;
        return this.createMainUI();
    }

    public void finish() {
        if (activity != null) {
            activity.finish();
        }

    }

    public LinearLayout getADView() {
        return ui;
    }

    public Activity getActivity() {
        return activity;
    }

    public void onCreate(Activity var1, Bundle var2) {
        activity = var1;
        (new Thread(new Runnable() {
            public void run() {
                do {
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException var2) {
                        var2.printStackTrace();
                    }
                } while (com.qy.sdk_rx.views.RDUi.isShow);

                com.qy.sdk_rx.views.RDUi.this.finish();
            }
        })).start();
    }

    public void onPause() {
    }

    public void onRestart() {
    }

    public void onResume() {
    }
}

