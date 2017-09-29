package com.skateboard.peopleforce.presenter.login

import com.skateboard.library.presenter.base.IView

/**
 * Created by skateboard on 2017/9/22.
 */
interface LoginView:IView {

    fun enterMainActivity()

    fun showMessage(message:String)

}