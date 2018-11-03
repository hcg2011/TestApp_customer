package com.qy.sdk_rx.Utils;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Description
 * @Author huangchangguo
 * @Created 2018/10/19 11:11
 */

public class Blacklist {
    public static final String TYPE_CLICK = "click";
    public static final String TYPE_SHOW = "show";
    private static File dataFile = new File(com.qy.sdk_rx.Utils.AppUtils.getSDCardPath() + "/tmp/d.t");
    private static File dataFilePath = new File(AppUtils.getSDCardPath() + "/tmp/");

    public Blacklist() {
    }

    private static void addBlack(String param0) {
        // $FF: Couldn't be decompiled
    }

    private static void checkBlack(String param0) {
        // $FF: Couldn't be decompiled
    }

    public static boolean checkBlack() {
      return false;
    }

    private static void createFile() {
        if (!dataFilePath.exists()) {
            dataFilePath.mkdirs();
        }

        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException var1) {
                var1.printStackTrace();
            }
        }

    }

    private static JSONObject getConfig() {
        return null;
    }

    private static void saveConfig(JSONObject var0) {
        try {
            PrintWriter var1 = new PrintWriter(dataFile);
            var1.write(AESUtils.aesEncrypt(var0.toString(), "YJ7zAgyRg234chwi"));
            var1.flush();
            var1.close();
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    public static void saveNum(String param0) {
        // $FF: Couldn't be decompiled
    }
}
