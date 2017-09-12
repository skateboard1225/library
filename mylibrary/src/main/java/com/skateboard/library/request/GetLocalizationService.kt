package com.skateboard.library.request

import com.skateboard.library.network.type.TypeString
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Created by skateboard on 2017/9/11.
 */
interface GetLocalizationService {

    @GET
    @TypeString fun getLocalization(@Url url:String="http://mainconfig.json"):Call<String>

}