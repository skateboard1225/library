package com.skateboard.library.application.manager

import android.app.Application
import com.skateboard.library.application.Constants
import com.skateboard.library.application.manager.base.BaseManager

/**
 * Created by skateboard on 2017/8/31.
 */

class ApplicationManager :BaseManager()
{

    var isApplicationIntilized:Boolean=false

    private var intilizedListenerSet: HashSet<OnApplicationIntilizedListener> =HashSet<OnApplicationIntilizedListener>()

    companion object {

        val default:ApplicationManager
        get(){
            return BaseManager.MANAGERS.get(Constants.APPLICATION_MANAGER) as ApplicationManager
        }

    }

    interface OnApplicationIntilizedListener
    {
        fun onApplicationIntilized()
    }

    fun registerOnApplicationIntilizedListener(listener:OnApplicationIntilizedListener)
    {
        intilizedListenerSet.add(listener)
    }

    fun unRegisterOnApplicationintiliedListener(listener:OnApplicationIntilizedListener)
    {
        intilizedListenerSet.remove(listener)
    }


    override fun onCreate(application: Application) {

    }

    open fun notifyApplicationIntilized()
    {
        isApplicationIntilized=true

        for(listener in intilizedListenerSet)
        {
            listener.onApplicationIntilized()
        }
    }


}
