package com.qy.sdk_rx.Interfaces;

import android.app.Activity;

import com.prize.interfaces.RDInterface;

/**
 * @Description
 * @Author huangchangguo
 * @Created 2018/10/19 10:59
 */

public interface Rd {
    void showBanner(Activity var1, RDInterface var2);

    void showInter(Activity var1, RDInterface var2);

    void showLog(boolean var1);

    void showOpen(Activity var1, RDInterface var2);
}
