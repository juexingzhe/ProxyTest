package com.example.juexingzhe.proxytest;

import android.util.Log;

/**
 * Created by juexingzhe on 2017/5/23.
 */

public class AnimalProxy implements Fly, Run{

    private Animal animal;

    public AnimalProxy(Animal animal) {
        this.animal = animal;
    }

    @Override
    public void fly() {
        Log.i(Animal.TAG, "In AnimalProxy fly");
        animal.fly();
    }

    @Override
    public void run() {
        Log.i(Animal.TAG, "In AnimalProxy run");
        animal.run();

    }
}
