package com.skateboard.library.application.manager.base

import android.app.Application

/**
 * Created by skateboard on 2017/8/31.
 */
abstract class BaseManager {


    var application: Application?=null

    companion object {

        var MANAGERS:HashMap<String,BaseManager> =HashMap<String,BaseManager>()


        fun dispatchCreate(application: Application)
        {
            for(key in BaseManager.MANAGERS.keys)
            {
                var manager=BaseManager.MANAGERS[key]

                manager?.application=application

                manager?.onCreate(application)
            }
        }
    }

    abstract fun onCreate(application: Application)




}