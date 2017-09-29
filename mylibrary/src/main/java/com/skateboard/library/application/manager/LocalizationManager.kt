package com.skateboard.library.application.manager

import android.app.Application
import com.skateboard.library.application.Constants
import com.skateboard.library.application.manager.base.BaseManager
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.StringReader

/**
 * Created by skateboard on 2017/9/12.
 */
class LocalizationManager:BaseManager()
{

    val localizationMap:MutableMap<String,String> = mutableMapOf()

    override fun onCreate(application: Application) {

           dealLocalization(application.applicationContext.assets.open("localization/localiztion_default.string"))
    }

    companion object {

        val default:LocalizationManager
        get()
        {
            return BaseManager.MANAGERS[Constants.LOCALIZATION_MANAGER] as LocalizationManager
        }


        fun getString(key:String):String
        {
            return LocalizationManager.default.localizationMap[key]?:""
        }

    }


    fun dealLocalization(inputStream:InputStream)
    {
        var localizationReader= BufferedReader(InputStreamReader(inputStream))

        var line=localizationReader.readLine()

        while(line!=null)
        {

            if(!line.contains("#") && line.contains("="))
            {
                val item=line.split("=")

                val key=item[0].trim()

                val value=item[1].trim()

                localizationMap.put(key, value)
            }

            line=localizationReader.readLine()

        }
    }


    fun dealLocalization(data:String)
    {
        var localizationReader= BufferedReader(StringReader(data))

        var line=localizationReader.readLine()

        while(line!=null)
        {

            if(!line.contains("#") && line.contains("="))
            {
                val item=line.split("=")

                val key=item[0].trim()

                val value=item[1].trim()

                localizationMap.put(key, value)
            }

            line=localizationReader.readLine()

        }

    }


}