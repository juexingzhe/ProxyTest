package com.example.juexingzhe.proxytest;

import android.util.Log;

/**
 * Created by juexingzhe on 2017/5/23.
 */

public class Animal implements Fly, Run{

    public static final String TAG = "ProxyTest";

    @Override
    public void fly() {
        Log.i(TAG, "Animal fly");
    }

    @Override
    public void run() {
        Log.i(TAG, "Animal run");
    }
}
