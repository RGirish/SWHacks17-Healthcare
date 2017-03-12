package com.girish.raman.healthcare;

import android.app.Application;

import net.gotev.speech.Speech;

public class Initializer extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Speech.init(this);
    }
}