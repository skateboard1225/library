package com.skateboard.peopleforce.ui.activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import com.neulion.library.application.manager.ShareDataManager
import com.skateboard.library.DialogUtil
import com.skateboard.library.application.Constants
import com.skateboard.library.application.manager.LaunchManager
import com.skateboard.library.application.manager.LocalizationManager
import com.skateboard.library.ui.base.activity.BaseActivity
import com.skateboard.peopleforce.R
import com.skateboard.peopleforce.application.PEConstants

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
            if(!ShareDataManager.ShareData.getPreferenceBoolean(PEConstants.HAS_TOKEN))
            {
                val intent = Intent(this,LoginActivity::class.java)

                startActivity(intent)

                finish()
            }
            else {
                val intent = Intent(this, MainActivity::class.java)

                startActivity(intent)

                finish()
            }
        }

        else if(LaunchManager.LAUNCH_RESULT.LAUNCH_FAILED==result)
        {

            DialogUtil.showMessageDialog(this,LocalizationManager.getString(Constants.APP_MESSAGE_NETWORKERROR), object : DialogInterface.OnClickListener {

                override fun onClick(p0: DialogInterface?, p1: Int) {
                    finish()
                }
            })
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        LaunchManager.default.unRegisterOnForceUpgradeCheckListener(this)
        LaunchManager.default.unRegisterOnLaunchFinishedListener(this)
    }
}