package com.skateboard.library.request.account

import com.skateboard.library.bean.BaseResponse
import com.skateboard.library.bean.account.GetRegisterCodeResponse
import com.skateboard.library.bean.account.RegisterResponse
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by skateboard on 2017/9/26.
 */
interface RegisterRequest {

    @FormUrlEncoded
    @POST("/get_register_code")
    fun getRegisterCode(@Field("phoneNum") phoneNum:String):Observable<GetRegisterCodeResponse>

    @FormUrlEncoded
    @POST("/register")
    fun register(@Field("phoneNum") phoneNum: String,@Field("password")passWord:String,@Field("registerCode") registerCode:String):Observable<RegisterResponse>


}