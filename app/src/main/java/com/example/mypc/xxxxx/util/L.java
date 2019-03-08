package com.example.mypc.xxxxx.util;

import android.util.Log;

public class L {
    public static final boolean DEBUG=true;

    public static final String TAG="tonjies";

    public static void d(String text){
        if (DEBUG){
            Log.d(TAG,text+"");
        }
    }
    public static void i(String text) {
         if (DEBUG) {
           Log.i(TAG, text+"");
            }
    }
    public static void w(String text) {
        if (DEBUG) {
            Log.w(TAG, text+"");
        }
    }
    public static void e(String text) {
        if (DEBUG) {
            Log.e(TAG, text+"");
        }
    }

}
