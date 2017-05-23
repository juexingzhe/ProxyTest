package com.example.juexingzhe.proxytest;

import android.util.Log;

/**
 * Created by juexingzhe on 2017/5/23.
 */

public class Animal implements Fly, Run{



    @Override
    public void fly() {
        Log.i(Utils.TAG, "Animal fly");
    }

    @Override
    public void run() {
        Log.i(Utils.TAG, "Animal run");
    }
}
