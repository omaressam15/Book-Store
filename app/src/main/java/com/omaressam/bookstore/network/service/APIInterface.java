package com.omaressam.bookstore.network.service;

import com.omaressam.bookstore.network.api.ApiConstant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET(ApiConstant.slider)
    Call<List<String>>slider();


}


