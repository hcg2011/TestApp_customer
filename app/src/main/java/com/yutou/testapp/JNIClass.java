package com.yutou.testapp;

public class JNIClass {
   /* static {
          System.loadLibrary("SharedObject1");
    }*/
    public native void helloWorld(String string,int i);
}
