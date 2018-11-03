package com.qy.sdk_rx.Interfaces;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * @Description
 * @Author huangchangguo
 * @Created 2018/10/19 10:59
 */

public interface RDADKActivity {
    View createView(Activity var1);

    void finish();

    Activity getActivity();

    void onCreate(Activity var1, Bundle var2);

    void onPause();

    void onRestart();

    void onResume();
}
