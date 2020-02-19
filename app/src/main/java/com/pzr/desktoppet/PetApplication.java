package com.pzr.desktoppet;

import android.app.Application;
import android.content.Context;

public class PetApplication extends Application {

    private PetDelegate mPetDelegate;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        mPetDelegate = new PetDelegate(this);
        mPetDelegate.onCreate();
        context = this;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        mPetDelegate.onTerminate();
    }

    /**
     * 获取全局上下文*/
    public static Context getContext() {
        return context;
    }

}
