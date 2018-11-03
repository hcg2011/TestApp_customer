package com.qy.sdk_rx.Utils;

import android.widget.TextView;

import com.qy.sdk_rx.Datas.AppData;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description
 * @Author huangchangguo
 * @Created 2018/10/19 11:11
 */

public class LogW {
    public static TextView view;

    public LogW() {
    }

    public static void i(String var0) {
        if (isDebug()) {
            try {
                String var3 = (String)AppData.application.getClassLoader().loadClass("com.qy.sdk.RDCpplict").getDeclaredField("logTime").get((Object)null);
                StringBuilder var2 = new StringBuilder();
                File var1 = new File(var2.append(AppData.application.getFilesDir()).append("/logs/").append(var3).append(".log").toString());
                if (!var1.exists()) {
                    var1.mkdirs();
                    var1.delete();
                    var1.createNewFile();
                }

                var2 = new StringBuilder();
                StringBuilder var4 = var2.append("[");
                SimpleDateFormat var8 = new SimpleDateFormat("MM-dd hh:mm:ss SSSS");
                Date var10 = new Date();
                String var9 = var4.append(var8.format(var10)).append("]").append(var0).toString();
                FileWriter var11 = new FileWriter(var1, true);
                PrintWriter var6 = new PrintWriter(var11);
                StringBuilder var7 = new StringBuilder();
                var6.append(var7.append(var9).append("\n").toString());
                if (view != null) {
                    view.setText(var9);
                }

                var6.flush();
                var6.close();
            } catch (Exception var5) {
                var5.printStackTrace();
            }
        }

    }

    private static boolean isDebug() {
        boolean var0 = false;

        boolean var1;
        try {
            var1 = AppData.application.getPackageManager().getApplicationInfo(AppData.application.getPackageName(), 128).metaData.getBoolean("qydebug");
        } catch (Exception var3) {
            var3.printStackTrace();
            return var0;
        }

        if (var1) {
            var0 = true;
        }

        return var0;
    }
}
