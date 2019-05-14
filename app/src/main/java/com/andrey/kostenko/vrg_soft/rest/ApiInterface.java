package com.andrey.kostenko.vrg_soft.rest;

import com.andrey.kostenko.vrg_soft.model.pojo.NYTimesRequest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("emailed/{period}.json")
    Call<NYTimesRequest> getEmailed(@Path("period") int period, @Query("api-key") String apikey);

    @GET("shared/{period}.json")
    Call<NYTimesRequest> getShared(@Path("period") int period, @Query("api-key") String apikey);

    @GET("viewed/{period}.json")
    Call<NYTimesRequest> getViewed(@Path("period") int period, @Query("api-key") String apikey);
}
