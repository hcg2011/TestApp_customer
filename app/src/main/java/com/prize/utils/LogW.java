package com.prize.utils;

import android.content.pm.ApplicationInfo;
import android.widget.TextView;

import com.prize.RDCpplict;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description
 * @Author huangchangguo
 * @Created 2018/10/18 12:55
 */
public class LogW {
    public static TextView view;

    public LogW() {
    }

    public static void i(String str) {
        if (isDebug()) {
            try {
                File logFile = new File(RDCpplict.application.getFilesDir() + "/logs/" + RDCpplict.logTime + ".log");
                if (!logFile.exists()) {
                    logFile.mkdirs();
                    logFile.delete();
                    logFile.createNewFile();
                }

                str = "[" + (new SimpleDateFormat("MM-dd hh:mm:ss SSSS")).format(new Date()) + "]" + str;
                PrintWriter writer = new PrintWriter(new FileWriter(logFile, true));
                writer.append(str + "\n");
                if (view != null) {
                    view.setText(str);
                }

                System.out.println(str);
                writer.flush();
                writer.close();
            } catch (Exception var3) {
                var3.printStackTrace();
            }

        }
    }

    private static boolean isDebug() {
        try {
            ApplicationInfo appInfo = RDCpplict.application.getPackageManager().getApplicationInfo(RDCpplict.application.getPackageName(), 128);
            boolean msg = appInfo.metaData.getBoolean("qydebug");
            return msg;
        } catch (Exception var2) {
            var2.printStackTrace();
            return false;
        }
    }
}
