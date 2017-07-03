package com.future.baselib.inteface;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by jniu on 2017/5/17.
 */

public interface HttpService {

    @POST("index.php")
    @FormUrlEncoded
    Call<String> getData(@Field("json") String json);
}
