package com.example.orangetest.object;

import com.example.orangetest.object.Bored;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JSONPlaceholder {

    @GET("activity")
    Call<Bored> getBored();

}
