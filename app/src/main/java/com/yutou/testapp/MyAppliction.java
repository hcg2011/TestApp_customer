package com.yutou.testapp;

import android.app.Application;

import com.prize.RDCpplict;

public class MyAppliction extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("启动APP");
        RDCpplict.init(this);
    }
}
