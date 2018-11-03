package com.qy.sdk_rx.Utils;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.widget.Toast;

import com.qy.sdk_rx.Datas.AppData;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @Description
 * @Author huangchangguo
 * @Created 2018/10/19 11:11
 */

public class Log {
    private static boolean LogPrint;
    public static boolean isDebug;

    static {
        isDebug = AppData.isDebug;
        LogPrint = false;
    }

    public Log() {
    }

    public static void e(Object var0, Exception var1) {
        LogPrint = com.qy.sdk_rx.Utils.Whitelist.isWhite(AppData.context);
        StringWriter var2 = new StringWriter();
        var1.printStackTrace(new PrintWriter(var2));
        printOrError("[" + var0 + "]" + var2.toString());
    }

    public static void e(Object var0, String var1) {
        LogPrint = com.qy.sdk_rx.Utils.Whitelist.isWhite(AppData.application);
        if (var0 instanceof String) {
            printOrError("[" + var0 + "]" + var1);
        } else {
            printOrError("[" + var0.getClass().getName() + "]" + var1);
        }

    }

    public static String getErrorLog(Exception var0) {
        StringWriter var3 = new StringWriter();
        var0.printStackTrace(new PrintWriter(var3));
        File var4 = new File(AppData.application.getFilesDir() + "/plugs");
        String var6 = "";
        File[] var7 = var4.listFiles();
        int var2 = var7.length;

        for (int var1 = 0; var1 < var2; ++var1) {
            File var5 = var7[var1];
            var6 = var6 + var5.getName() + "\n";
        }

        var6 = "phoneType = " + Build.MODEL + "\nSDK_Version = " + VERSION.SDK_INT + "\nfiles = " + var6 + "Error = \n";
        return var6 + var3.toString();
    }

    public static void i(Object var0, String var1) {
        LogPrint = com.qy.sdk_rx.Utils.Whitelist.isWhite(AppData.application);
        if (var0 instanceof String) {
            printOrInfo("[" + var0 + "]" + var1);
        } else {
            printOrInfo("[" + var0.getClass().getName() + "]" + var1);
        }

    }

    private static void printOrError(String var0) {
        if (LogPrint || isDebug) {
            System.err.println(var0);
        }

    }

    private static void printOrInfo(String var0) {
        if (LogPrint || isDebug) {
            System.out.println(var0);
        }

    }

    public static void toast(final Context var0, final String var1) {
        AppData.handler.post(new Runnable() {
            public void run() {
                if (Whitelist.isWhite(var0)) {
                    Toast.makeText(var0, var1, Toast.LENGTH_LONG).show();
                    Log.i("Toast", var1);
                }

            }
        });
    }
}
