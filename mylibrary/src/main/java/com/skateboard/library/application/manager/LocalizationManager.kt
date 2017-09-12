package com.skateboard.library.application.manager

import android.app.Application
import com.skateboard.library.application.Constants
import com.skateboard.library.application.manager.base.BaseManager
import java.io.BufferedReader
import java.io.StringReader

/**
 * Created by skateboard on 2017/9/12.
 */
class LocalizationManager:BaseManager()
{

    val localizationMap:MutableMap<String,String> = mutableMapOf()

    override fun onCreate(application: Application) {

    }

    companion object {

        val default:LocalizationManager
        get()
        {
            return BaseManager.MANAGERS[Constants.LOCALIZATION_MANAGER] as LocalizationManager
        }


        fun getString(key:String):CharSequence
        {
            return LocalizationManager.default.localizationMap[key]?:""
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