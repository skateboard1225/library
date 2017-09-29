package com.skateboard.peopleforce.presenter.register

import com.skateboard.library.presenter.base.IView

/**
 * Created by skateboard on 2017/8/29.
 */
interface RegisterView:IView {

    fun showMessage(message:String)

    fun enterMainActivity()

}