package com.crtf.citycardcustomermanager.util;

public class Log {
    public static final boolean START = true;


    private Log() {
    }


    public static int v(String tag, String msg) {
        return START ? android.util.Log.v(tag, msg) : -1;
    }


    public static int v(String tag, String msg, Throwable tr) {
        return START ? android.util.Log.v(tag, msg, tr) : -1;
    }


    public static int d(String tag, String msg) {
        return START ? android.util.Log.d(tag, msg) : -1;
    }


    public static int d(String tag, String msg, Throwable tr) {
        return START ? android.util.Log.d(tag, msg, tr) : -1;
    }


    public static int i(String tag, String msg) {
        return START ? android.util.Log.i(tag, msg) : -1;
    }


    public static int i(String tag, String msg, Throwable tr) {
        return START ? android.util.Log.i(tag, msg, tr) : -1;
    }


    public static int w(String tag, String msg) {
        return START ? android.util.Log.w(tag, msg) : -1;
    }


    public static int w(String tag, String msg, Throwable tr) {
        return START ? android.util.Log.w(tag, msg, tr) : -1;
    }

    public static int w(String tag, Throwable tr) {
        return START ? android.util.Log.w(tag, tr) : -1;
    }


    public static int e(String tag, String msg) {
        return START ? android.util.Log.e(tag, msg) : -1;
    }

    public static int e(String tag, String msg, Throwable tr) {
        return START ? android.util.Log.e(tag, msg, tr) : -1;
    }

}
