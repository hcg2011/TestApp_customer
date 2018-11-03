package com.qy.sdk.Interfaces;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;

import com.prize.interfaces.RDInterface;

/**
 * @Description
 * @Author huangchangguo
 * @Created 2018/10/19 10:54
 */

public interface RdkInter {
    void exit(Activity var1, RDInterface var2);

    Object init(Context var1, String var2, String var3, Handler var4);

    void onPause();

    void onResume();

    void setDebug(boolean var1);

    void showBanner(Activity var1, RDInterface var2);

    void showInter(Activity var1, RDInterface var2);

    void showOpen(Activity var1, RDInterface var2);
}
