package com.skateboard.library.request.account

import com.skateboard.library.bean.account.LoginResponse
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by skateboard on 2017/9/23.
 */
interface LoginRequest {
    @FormUrlEncoded
    @POST("/login")
    fun login(@Field("username")username:String,@Field("password")password:String):Observable<LoginResponse>

    @FormUrlEncoded
    @POST("/login")
    fun login(@Field("token")token:String):Observable<LoginResponse>

}