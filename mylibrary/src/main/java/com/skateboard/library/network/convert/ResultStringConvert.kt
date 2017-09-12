package com.skateboard.library.network.convert

import okhttp3.ResponseBody
import retrofit2.Converter

/**
 * Created by skateboard on 2017/8/29.
 */
class ResultStringConvert : Converter<ResponseBody, String>
{
    override fun convert(response: ResponseBody?): String {
        return response?.string()?:""
    }

}