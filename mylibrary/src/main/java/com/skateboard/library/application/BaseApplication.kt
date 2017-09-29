package com.skateboard.library.application

import android.app.Application
import com.neulion.library.application.manager.ShareDataManager
import com.skateboard.library.application.manager.*
import com.skateboard.library.application.manager.base.BaseManager

/**
 * Created by skateboard on 2017/8/28.
 */
open class BaseApplication : Application()
{

    override fun onCreate() {
        super.onCreate()
        depoly()
    }

    private fun depoly()
    {
        onBindManagers()

        dispatchManagers()
    }

    open fun onBindManagers()
    {
        BaseManager.MANAGERS[Constants.APPLICATION_MANAGER]=ApplicationManager()

        BaseManager.MANAGERS[Constants.LAUNCH_MANAGER]=LaunchManager()

        BaseManager.MANAGERS[Constants.SHAREDATA_MANAGER]= ShareDataManager()

        BaseManager.MANAGERS[Constants.CONFIGURATION_MANAGER]=ConfigurationManager()

        BaseManager.MANAGERS[Constants.LOCALIZATION_MANAGER]=LocalizationManager()

        BaseManager.MANAGERS[Constants.ACCOUNT_MANAGER]=AccountManager()
    }

    private fun dispatchManagers()
    {
        BaseManager.dispatchCreate(this)
    }


}