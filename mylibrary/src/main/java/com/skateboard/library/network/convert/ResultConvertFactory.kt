package com.skateboard.library.network.convert

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.skateboard.library.network.type.TypeMainConfig
import com.skateboard.library.network.type.TypeString
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 * Created by skateboard on 2017/8/29.
 */
object ResultConvertFactory: Converter.Factory() {

    private val gson: Gson = Gson()

    override fun responseBodyConverter(type: Type?, annotations: Array<out Annotation>?, retrofit: Retrofit?): Converter<ResponseBody, *>? {

        if(annotations!=null)
        {
            for (ann in annotations) {

                println("ann is "+ann.toString())

                if(ann is TypeString)
                {
                    return ResultStringConvert()
                }
                else if(ann is TypeMainConfig)
                {
                    print("type mainconfig ")

                    return ResultMainConfigConvert()
                }
            }

        }
        val adapter = gson.getAdapter(TypeToken.get(type))
        return ResultGsonConverter(gson, adapter)

    }
}