package com.skateboard.peopleforce.ui.activity

import android.os.Bundle
import com.skateboard.library.application.Constants
import com.skateboard.library.application.manager.LocalizationManager
import com.skateboard.library.ui.base.activity.BaseActivity
import com.skateboard.peopleforce.R
import com.skateboard.peopleforce.ui.fragment.LoginFragment
import kotlinx.android.synthetic.main.toolbar_layout.*

/**
 * Created by skateboard on 2017/9/22.
 */
class LoginActivity:PFBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        showLoginFragment()
    }

    private fun showLoginFragment()
    {
        supportFragmentManager.beginTransaction().replace(R.id.container,LoginFragment()).commit()
    }

    override fun getToolbarTitle(): String {
        return LocalizationManager.getString(Constants.APP_UI_LOGIN) as String
    }
}