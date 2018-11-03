package com.qy.sdk_rx.Utils;

import com.qy.sdk_rx.Interfaces.HttpInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.Iterator;

/**
 * @Description
 * @Author huangchangguo
 * @Created 2018/10/19 11:11
 */

public class HttpTools {
    private static final String TAG = HttpTools.class.getName();
    private static HttpTools network;

    private HttpTools() {
    }

    public static void download(final String var0, final String var1) {
        synchronized (HttpTools.class) {
        }

        try {
            Runnable var2 = new Runnable() {
                public void run() {
                    // $FF: Couldn't be decompiled
                }
            };
            Thread var3 = new Thread(var2);
            var3.start();
        } finally {
            ;
        }

    }

    public static HttpTools init() {
        if (network == null) {
            network = new HttpTools();
        }

        return network;
    }

    public void httpGet(String param1, JSONObject param2, HttpInterface param3) {
        // $FF: Couldn't be decompiled
    }

    public void httpPost(final String var1, final JSONObject var2, final HttpInterface var3) {
        (new Thread(new Runnable() {
            public void run() {
                // $FF: Couldn't be decompiled
            }
        })).start();
    }

    public String toGetSplice(JSONObject var1) {
        String var8;
        if (var1 != null && !var1.toString().equals("{}")) {
            String var2 = "";
            Iterator var4 = var1.keys();

            while (true) {
                String var9;
                while (true) {
                    if (!var4.hasNext()) {
                        var8 = var2.substring(1, var2.length()).replaceAll(" ", "");
                        return var8;
                    }

                    String var5 = (String) var4.next();

                    StringBuilder var3;
                    try {
                        var3 = new StringBuilder();
                        var9 = var3.append(var2).append("&").append(var5).append("=").append(var1.getString(var5)).toString();
                        break;
                    } catch (JSONException var7) {
                        var7.printStackTrace();

                        try {
                            var3 = new StringBuilder();
                            var9 = var3.append(var2).append("&").append(URLEncoder.encode(var5, "UTF-8")).append("=").toString();
                        } catch (Exception var6) {
                            var2 = var2 + "&" + var5 + "=";
                            continue;
                        }

                        var2 = var9;
                    }
                }

                var2 = var9;
            }
        } else {
            var8 = "";
            return var8;
        }
    }
}

