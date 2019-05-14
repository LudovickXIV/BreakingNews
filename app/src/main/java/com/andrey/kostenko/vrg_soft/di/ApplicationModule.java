package com.andrey.kostenko.vrg_soft.di;

import android.app.Application;

import com.andrey.kostenko.vrg_soft.BuildConfig;
import com.andrey.kostenko.vrg_soft.model.sql.DBHelper;
import com.andrey.kostenko.vrg_soft.rest.ApiInterface;
import com.andrey.kostenko.vrg_soft.rest.RetrofitService;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                        .create()))
                .baseUrl(BuildConfig.BASE_URL)
                .build();
    }

    @Provides
    @Singleton
    public RetrofitService provideRetrofitService(Retrofit retrofit) {
        RetrofitService retrofitService = new RetrofitService();
        retrofitService.setRetrofitApiInterface(retrofit.create(ApiInterface.class));
        return retrofitService;
    }

    @Provides
    @Singleton
    public DBHelper provideDb() {
        return new DBHelper(application);
    }
}
