package com.prize;

import android.app.Activity;
import android.app.Application;
import android.os.Handler;

import com.prize.interfaces.RDInterface;
import com.prize.utils.Log_sdk;

import java.lang.reflect.Field;

/**
 * @Description
 * @Author huangchangguo
 * @Created 2018/10/18 12:48
 */

public class RDSDK {
    private Application application;
    private static RDSDK sdk;
    private Object rdkInter;
    private String packname;

    private RDSDK(Activity context, String channel, String appId, Handler handler) {
        this.packname = "com.qy.sdk_rx.Datas";
        this.application = RDCpplict.application;

        try {
            this.rdkInter = context.getApplication().getClassLoader().loadClass("com.qy.sdk_rx.RdInterImpl").getConstructor(Application.class).newInstance(context.getApplication());
            Log_sdk.e(this, "加载成功");
        } catch (Exception var7) {
            var7.printStackTrace();
            Log_sdk.e(this, "插件加载失败");
        }

        if (this.rdkInter != null) {
            try {
                this.rdkInter.getClass().getMethod(this.getName("a"), Activity.class, String.class, String.class, Handler.class).invoke(this.rdkInter, context, channel, appId, handler);
            } catch (Exception var6) {
                var6.printStackTrace();
            }
        }

    }

    private RDSDK(Activity activity, Handler handler) {
        this.application = RDCpplict.application;
        this.packname = "com.qy.selfrd.Datas";

        try {
            this.rdkInter = this.application.getClassLoader().loadClass("com.qy.selfrd.Qdk").getConstructor(Application.class).newInstance(this.application);
            Log_sdk.e(this, "加载成功");
        } catch (Exception var5) {
            var5.printStackTrace();
            Log_sdk.e(this, "插件加载失败");
        }

        if (this.rdkInter != null) {
            try {
                this.rdkInter.getClass().getMethod(this.getName("a"), Activity.class, String.class, String.class, Handler.class).invoke(this.rdkInter, activity, "", "", handler);
            } catch (Exception var4) {
                var4.printStackTrace();
            }
        } else {
            Log_sdk.i(this, "接口加载失败");
        }

    }

    public static RDSDK init(Activity context, String channel, String appId, Handler handler) {
        if (sdk == null) {
            sdk = new RDSDK(context, channel, appId, handler);
        }
        return sdk;
    }

    private static RDSDK init(Activity activity, Handler handler) {
        if (sdk == null) {
            sdk = new RDSDK(activity, handler);
        }

        return sdk;
    }

    public static RDSDK getSdk() {
        return sdk == null ? null : sdk;
    }

    public void showOpen(Activity activity, RDInterface rdInterface) {
        if (rdInterface == null) {
            rdInterface = new RDSDK.notInterface();
        }

        if (this.rdkInter == null) {
            ((RDInterface) rdInterface).onClose();
        } else {
            try {
                this.rdkInter.getClass().getMethod(this.getName("b"), Activity.class, RDInterface.class).invoke(this.rdkInter, activity, rdInterface);
            } catch (Exception var4) {
                var4.printStackTrace();
            }

        }
    }

    public void showBer(Activity activity, RDInterface rdInterface) {
        if (rdInterface == null) {
            rdInterface = new RDSDK.notInterface();
        }

        if (this.rdkInter == null) {
            ((RDInterface) rdInterface).onClose();
        } else {
            try {
                this.rdkInter.getClass().getMethod(this.getName("c"), Activity.class, RDInterface.class).invoke(this.rdkInter, activity, rdInterface);
            } catch (Exception var4) {
                var4.printStackTrace();
            }

        }
    }

    public void showInter(Activity activity, RDInterface rdInterface) {
        if (rdInterface == null) {
            rdInterface = new RDSDK.notInterface();
        }

        if (this.rdkInter == null) {
            ((RDInterface) rdInterface).onClose();
        } else {
            try {
                this.rdkInter.getClass().getMethod(this.getName("d"), Activity.class, RDInterface.class).invoke(this.rdkInter, activity, rdInterface);
            } catch (Exception var4) {
                var4.printStackTrace();
            }

        }
    }

    public void exit(Activity activity, RDInterface rdInterface) {
        if (rdInterface == null) {
            rdInterface = new RDSDK.notInterface();
        }

        if (this.rdkInter == null) {
            ((RDInterface) rdInterface).onClose();
        } else {
            try {
                this.rdkInter.getClass().getMethod(this.getName("e"), Activity.class, RDInterface.class).invoke(this.rdkInter, activity, rdInterface);
            } catch (Exception var4) {
                var4.printStackTrace();
            }

        }
    }

    public void onResume() {
        if (this.rdkInter != null) {
            try {
                this.rdkInter.getClass().getMethod(this.getName("f")).invoke(this.rdkInter);
            } catch (Exception var2) {
                var2.printStackTrace();
            }
        }

    }

    public void onPause() {
        if (this.rdkInter != null) {
            try {
                this.rdkInter.getClass().getMethod(this.getName("g")).invoke(this.rdkInter);
            } catch (Exception var2) {
                var2.printStackTrace();
            }
        }

    }

    public String getVersion() {
        String version = "null";
        if (this.rdkInter != null) {
            try {
                version = (String) this.rdkInter.getClass().getMethod(this.getName("v")).invoke(this.rdkInter);
            } catch (Exception var3) {
                var3.printStackTrace();
            }
        }

        return version;
    }

    public void setDeBug(boolean showLog) {
        try {
            System.out.println("-------->设置日志" + showLog);
            this.rdkInter.getClass().getMethod(this.getName("log"), Boolean.TYPE).invoke(this.rdkInter, showLog);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    private String getName(String s) {
        try {
            Log_sdk.i(this, "==========>" + this.packname + ".MethodNames>" + s);
            Field field = this.application.getClassLoader().loadClass(this.packname + ".MethodNames").getDeclaredField(s);
            String name = (String) field.get((Object) null);
            Log_sdk.i(this, "==========<" + name);
            return name;
        } catch (Exception var4) {
            var4.printStackTrace();
            return "";
        }
    }

    private class notInterface extends RDInterface {
        private notInterface() {
        }
    }
}


