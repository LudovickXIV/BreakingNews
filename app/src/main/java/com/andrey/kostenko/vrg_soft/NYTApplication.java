package com.andrey.kostenko.vrg_soft;

import android.app.Application;
import android.content.Context;

import com.andrey.kostenko.vrg_soft.di.AppComponent;
import com.andrey.kostenko.vrg_soft.di.ApplicationModule;
import com.andrey.kostenko.vrg_soft.di.DaggerAppComponent;

public class NYTApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public static AppComponent getAppComponent(Context context) {
        return ((NYTApplication)context.getApplicationContext()).appComponent;
    }
}
