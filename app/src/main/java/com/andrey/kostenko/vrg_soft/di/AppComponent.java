package com.andrey.kostenko.vrg_soft.di;


import com.andrey.kostenko.vrg_soft.fragments.BaseFragment;
import com.andrey.kostenko.vrg_soft.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface AppComponent {

    //activity
    void inject(MainActivity mainActivity);

    //fragment
    void inject(BaseFragment baseFragment);
}