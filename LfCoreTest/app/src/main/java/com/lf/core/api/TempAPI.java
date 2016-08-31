package com.lf.core.api;


import com.lf.tempcore.tempResponse.TempResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by longf on 2016/8/26.
 */
public interface TempAPI {
    /**
     * 用户登录
     *
     * @param museUserName
     * @param musePwd
     * @return
     */
    @GET("app/public/mall/userLogin.do")
    Call<TempResponse> userLogin(@Query("museUserName") String museUserName, @Query("musePwd") String musePwd);

}
