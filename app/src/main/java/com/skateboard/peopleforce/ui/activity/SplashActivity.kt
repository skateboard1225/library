package com.skateboard.peopleforce.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import com.skateboard.library.application.manager.LaunchManager
import com.skateboard.library.ui.base.activity.BaseActivity
import com.skateboard.peopleforce.R

/**
 * Created by skateboard on 2017/8/31.
 */
class SplashActivity:BaseActivity(),LaunchManager.OnLaunchFinishedListener,LaunchManager.OnForceUpgradeCheckListener {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        LaunchManager.default.startLaunch(LaunchManager.LAUNCH_SPLASH_MODE)
        LaunchManager.default.registerOnLaunchFinishedListener(this)
        LaunchManager.default.registerOnForceUpgradeCheckListener(this)

    }


    override fun checkForceUpgrade(): Boolean {

        return false
    }


    override fun launchFinished(result: LaunchManager.LAUNCH_RESULT) {

        if(LaunchManager.LAUNCH_RESULT.LAUNCH_SUCCESS==result)
        {
            val intent=Intent(this,MainActivity::class.java)

            startActivity(intent)

            finish()
        }

        else if(LaunchManager.LAUNCH_RESULT.LAUNCH_FAILED==result)
        {



        }

    }

    override fun onDestroy() {
        super.onDestroy()
        LaunchManager.default.unRegisterOnForceUpgradeCheckListener(this)
        LaunchManager.default.unRegisterOnLaunchFinishedListener(this)
    }
}