package com.skateboard.library.application.manager

import android.app.Application
import com.skateboard.library.application.Constants
import com.skateboard.library.application.manager.base.BaseManager
import com.skateboard.library.bean.mainconfig.MainConfig

/**
 * Created by skateboard on 2017/9/8.
 */
class ConfigurationManager : BaseManager() {

     var mainConfig: MainConfig?=null


    private val configurationLoadedSet:MutableSet<OnConfigurationLoadedListener> = mutableSetOf()

    interface OnConfigurationLoadedListener
    {
        fun onConfigurationLoaded()
    }

    companion object {
        val default:ConfigurationManager
        get() {

            return BaseManager.MANAGERS[Constants.CONFIGURATION_MANAGER] as ConfigurationManager
        }
    }

    fun registerOnConfigurationLoadedListener(listener: OnConfigurationLoadedListener)
    {
        configurationLoadedSet.add(listener)
    }

    fun unRegisterOnConfigurationLoadedListsner(listener: OnConfigurationLoadedListener)
    {
        configurationLoadedSet.remove(listener)
    }


    fun notifyOnConfigurationLoaded()
    {
        for(listener in configurationLoadedSet)
        {
            listener.onConfigurationLoaded()
        }
    }


    override fun onCreate(application: Application) {

    }


    fun getUrl(nlid: String, configParam: MutableMap<String, String>? = null): String {
        val mainConfigItem = mainConfig?.dataSource?.get(nlid)

        if (mainConfigItem == null) {
            return ""
        } else {

            if (configParam == null) {

                return mainConfigItem?.url ?: ""
            } else {
                val keySet = configParam.keys
                var url = mainConfigItem?.url ?: ""
                for (key in keySet) {
                    val param = configParam[key]

                    if(url!=null) {

                        url = url.replace("{${key}}", param!!)
                    }
                }

                return url
            }
        }
    }


    fun getParams(nlid: String, key: String): String {
        val mainConfigItem = mainConfig?.dataSource?.get(nlid)

        val params = mainConfigItem?.params

        return params?.get(key) ?: ""
    }



}