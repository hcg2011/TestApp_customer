package com.qy.sdk_rx.Utils;

import android.app.Activity;
import com.prize.interfaces.RDInterface;
import com.qy.sdk_rx.Datas.AppData;
import com.qy.sdk_rx.Interfaces.Rd;
import com.qy.sdk_rx.RdInterImpl;

/**
 * @Description
 * @Author huangchangguo
 * @Created 2018/10/19 11:11
 */

public class QyUtils implements Rd {
    private static QyUtils utils;
    private Object rdkInter;

    private QyUtils() {
    }

    private String getName(String var1) {
        try {
            StringBuilder var2 = new StringBuilder();
            Log.i(this, var2.append("==========>").append("com.qy.selfrd.Datas").append(".MethodNames>").append(var1).toString());
            ClassLoader var3 = AppData.application.getClassLoader();
            var2 = new StringBuilder();
            var1 = (String) var3.loadClass(var2.append("com.qy.selfrd.Datas").append(".MethodNames").toString()).getDeclaredField(var1).get((Object) null);
            var2 = new StringBuilder();
            Log.i(this, var2.append("==========<").append(var1).toString());
        } catch (Exception var4) {
            Log.e(this, var4);
            var1 = "";
        }

        return var1;
    }

    public static QyUtils init(RdInterImpl var0) {
        Log.i(QyUtils.class.getName(), "初始化Qy广告");
        if (utils == null) {
            utils = new QyUtils();
            utils.initQyRd((Activity) AppData.context, var0);
        }

        return utils;
    }

    private void initQyRd(Activity param1, RdInterImpl param2) {
        // $FF: Couldn't be decompiled
    }

    public void exit(Activity var1, RDInterface var2) {
        if (this.rdkInter == null) {
            var2.onClose();
        } else {
            try {
                this.rdkInter.getClass().getMethod(this.getName("e"), Activity.class, RDInterface.class).invoke(this.rdkInter, var1, var2);
            } catch (Exception var3) {
                Log.e(this, var3);
            }
        }

    }

    public String getVersion() {
        String var2 = "nul";
        String var1 = var2;
        if (this.rdkInter != null) {
            try {
                var1 = (String) this.rdkInter.getClass().getMethod(this.getName("v")).invoke(this.rdkInter);
            } catch (Exception var3) {
                Log.e(this, var3);
                var1 = var2;
            }
        }

        return var1;
    }

    public void onPause() {
        if (this.rdkInter != null) {
            try {
                this.rdkInter.getClass().getMethod(this.getName("g")).invoke(this.rdkInter);
            } catch (Exception var2) {
                Log.e(this, var2);
            }
        }

    }

    public void onResume() {
        if (this.rdkInter != null) {
            try {
                this.rdkInter.getClass().getMethod(this.getName("f")).invoke(this.rdkInter);
            } catch (Exception var2) {
                Log.e(this, var2);
            }
        }

    }

    public void showBanner(Activity var1, RDInterface var2) {
        if (this.rdkInter == null) {
            var2.onClose();
        } else {
            try {
                this.rdkInter.getClass().getMethod(this.getName("c"), Activity.class, RDInterface.class).invoke(this.rdkInter, var1, var2);
            } catch (Exception var3) {
                Log.e(this, var3);
            }
        }

    }

    public void showInter(Activity var1, RDInterface var2) {
        Log.i(this, "调用插屏");
        if (this.rdkInter == null) {
            var2.onClose();
        } else {
            try {
                this.rdkInter.getClass().getMethod(this.getName("d"), Activity.class, RDInterface.class).invoke(this.rdkInter, var1, var2);
            } catch (Exception var3) {
                Log.e(this, var3);
            }
        }

    }

    public void showLog(boolean var1) {
        try {
            this.rdkInter.getClass().getMethod(this.getName("log"), Boolean.TYPE).invoke(this.rdkInter, var1);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public void showOpen(Activity var1, RDInterface var2) {
        if (this.rdkInter == null) {
            var2.onClose();
        } else {
            try {
                this.rdkInter.getClass().getMethod(this.getName("b"), Activity.class, RDInterface.class).invoke(this.rdkInter, var1, var2);
            } catch (Exception var3) {
                Log.e(this, var3);
            }
        }

    }
}
