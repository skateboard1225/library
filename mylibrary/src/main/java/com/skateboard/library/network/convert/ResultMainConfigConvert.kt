package com.skateboard.library.network.convert

import com.skateboard.library.bean.mainconfig.MainConfig
import com.skateboard.library.util.MainConfigParser
import okhttp3.ResponseBody
import retrofit2.Converter

/**
 * Created by skateboard on 2017/9/9.
 */
class ResultMainConfigConvert : Converter<ResponseBody, MainConfig> {

    override fun convert(value: ResponseBody?): MainConfig {

        val data = value?.string()

        val mainConfig = MainConfig()

        if (data != null) {
            MainConfigParser.parse(data, mainConfig)
        }

        return mainConfig

    }
}