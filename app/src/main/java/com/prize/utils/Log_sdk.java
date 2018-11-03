package com.prize.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @Description
 * @Author huangchangguo
 * @Created 2018/10/18 12:55
 */

public class Log_sdk {
    public static boolean isDebug = false;
    private static boolean LogPrint = false;

    public Log_sdk() {
    }

    private static void printOrInfo(String string) {
        if (LogPrint || isDebug) {
            System.out.println(string);
        }

        LogW.i(string);
    }

    private static void printOrError(String string) {
        if (LogPrint || isDebug) {
            System.err.println(string);
        }

        LogW.i(string);
    }

    public static void i(Object title, String text) {
        printOrInfo("[" + title.getClass().getName() + "]" + text);
    }

    public static void e(Object title, String text) {
        printOrError("[" + title.getClass().getName() + "]" + text);
    }

    public static void e(Object title, Exception e) {
        StringWriter writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        e.printStackTrace(printWriter);
        printOrError("[" + title + "]" + writer.toString());
    }
}

