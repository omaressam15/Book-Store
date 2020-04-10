package com.omaressam.bookstore.network.service;

import com.omaressam.bookstore.Model.Books;
import com.omaressam.bookstore.Model.SingForm;
import com.omaressam.bookstore.Model.Token;
import com.omaressam.bookstore.Model.User;
import com.omaressam.bookstore.network.api.ApiConstant;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

import static com.omaressam.bookstore.network.api.ApiConstant.SERVICE_USER_BUY_BOOK;
import static com.omaressam.bookstore.network.api.ApiConstant.SERVICE_USER_MY_BOOK;
import static com.omaressam.bookstore.network.api.ApiConstant.User;
import static com.omaressam.bookstore.network.api.ApiConstant.login;
import static com.omaressam.bookstore.network.api.ApiConstant.register;


public interface APIInterface {

    @GET(ApiConstant.slider)
    Call<List<String>>slider();

    @GET(ApiConstant.Book)
    Call<List<Books>>getBooks();

     @POST(login)
    Call<Token> login (@Body SingForm singForm );

      @POST(register)
    Call<Token> register (@Body SingForm signForm);

      @GET(User)
     Call<User>getProfile(@Header("x-auth-token")String accessToken);

    @POST(SERVICE_USER_BUY_BOOK + "/{id}")
    Call<ResponseBody> buyBook(@Header("x-auth-token") String accessToken,
                               @Path("id") String id);

    @GET(SERVICE_USER_MY_BOOK)
    Call<List<Books>> getMyBooks(@Header("x-auth-token")String accessToken) ;

}


