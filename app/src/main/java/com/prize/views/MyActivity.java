package com.prize.views;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;

import java.lang.reflect.Method;

/**
 * @Description
 * @Author huangchangguo
 * @Created 2018/10/18 12:53
 */

public class MyActivity extends Activity {
    private Object activity;
    private static String classPath;

    public MyActivity() {
    }

    public MyActivity(String classPath) {
        classPath = classPath;
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            if (classPath == null) {
                this.finish();
            }

            this.activity = this.getApplication().getClassLoader().loadClass(classPath).newInstance();
            classPath = null;
            Method createView = this.activity.getClass().getMethod("createView", Activity.class);
            View view = (View) createView.invoke(this.activity, this);
            this.setContentView(view);
            Method onCreate = this.activity.getClass().getMethod("onCreate", Activity.class, Bundle.class);
            onCreate.invoke(this.activity, this, savedInstanceState);
        } catch (Exception var5) {
            var5.printStackTrace();
        }

    }

    protected void onResume() {
        super.onResume();

        try {
            Method method = this.activity.getClass().getMethod("onResume");
            method.invoke(this.activity);
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    protected void onPause() {
        super.onPause();

        try {
            Method method = this.activity.getClass().getMethod("onPause");
            method.invoke(this.activity);
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    protected void onRestart() {
        super.onRestart();

        try {
            Method method = this.activity.getClass().getMethod("onRestart");
            method.invoke(this.activity);
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        try {
            Method method = this.activity.getClass().getMethod("onKeyDown", Integer.TYPE);
            return (Boolean) method.invoke(this.activity, keyCode);
        } catch (Exception var4) {
            var4.printStackTrace();
            return keyCode != 4 && keyCode != 3 ? super.onKeyDown(keyCode, event) : true;
        }
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}

