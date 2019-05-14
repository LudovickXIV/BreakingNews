package com.andrey.kostenko.vrg_soft.rest;

import com.andrey.kostenko.vrg_soft.BuildConfig;
import com.andrey.kostenko.vrg_soft.model.pojo.NYTimesRequest;

import retrofit2.Call;

public class RetrofitService {

    private static final int PERIOD = 30;
    private ApiInterface retrofitApiInterface;

    public void setRetrofitApiInterface(ApiInterface apiInterface) {
        this.retrofitApiInterface = apiInterface;
    }

    public Call<NYTimesRequest> getEmailed() {
        return retrofitApiInterface.getEmailed(PERIOD, BuildConfig.API_KEY);
    }

    public Call<NYTimesRequest> getShared() {
        return retrofitApiInterface.getShared(PERIOD, BuildConfig.API_KEY);
    }

    public Call<NYTimesRequest> getViewed() {
        return retrofitApiInterface.getViewed(PERIOD, BuildConfig.API_KEY);
    }

}
