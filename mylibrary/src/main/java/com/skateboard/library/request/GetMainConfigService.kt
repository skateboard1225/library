package com.skateboard.library.request

import com.skateboard.library.bean.mainconfig.MainConfig
import com.skateboard.library.network.type.TypeMainConfig
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Created by skateboard on 2017/9/9.
 */
interface GetMainConfigService {

    @GET
    @TypeMainConfig fun getMainConfig(@Url url:String="http://mainconfig.json"):Call<MainConfig>

}