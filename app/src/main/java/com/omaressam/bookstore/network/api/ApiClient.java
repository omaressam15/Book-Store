package com.omaressam.bookstore.network.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.omaressam.bookstore.network.api.ApiConstant.BASE_URL;

public class ApiClient {

    private static Retrofit retrofit =null;
    public static Retrofit  getClient () {
        if (retrofit == null){
            retrofit=new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build();
        }
        return retrofit;
    }


}
