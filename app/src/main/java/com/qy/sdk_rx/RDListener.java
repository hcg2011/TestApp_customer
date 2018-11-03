package com.qy.sdk_rx;

import com.prize.interfaces.RDInterface;

import org.json.JSONObject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Description
 * @Author huangchangguo
 * @Created 2018/10/19 11:07
 */

public class RDListener implements InvocationHandler {
    private static boolean isBaiduBanner = true;
    private RDInterface adInterface;
    private JSONObject json;
    private Object listener;

    public RDListener(JSONObject param1, RDInterface param2) {
        // $FF: Couldn't be decompiled
    }

    public Object invoke(Object param1, Method param2, Object[] param3) {
       return null;
    }

    private class RdInterfaceIsNull extends RDInterface {
        private RdInterfaceIsNull() {
        }
    }
}

