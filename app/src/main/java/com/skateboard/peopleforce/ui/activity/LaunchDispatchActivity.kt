package com.skateboard.peopleforce.ui.activity

import android.content.Intent
import android.os.Bundle
import com.skateboard.library.application.manager.ApplicationManager
import com.skateboard.library.ui.base.activity.BaseActivity

/**
 * Created by skateboard on 2017/9/12.
 */
class LaunchDispatchActivity:BaseActivity()
{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(ApplicationManager.default.isApplicationIntilized)
        {
           val intent=Intent(this,MainActivity::class.java)

            startActivity(intent)

            finish()
        }
        else
        {
            val intent=Intent(this,SplashActivity::class.java)

            startActivity(intent)

            finish()
        }

    }
}