package com.skateboard.peopleforce.presenter.login

import com.skateboard.library.application.manager.AccountManager
import com.skateboard.library.bean.account.LoginResponse
import com.skateboard.library.presenter.base.IPresenter


/**
 * Created by skateboard on 2017/9/22.
 */
class LoginPresenter : IPresenter<LoginView>() {


    fun login(userName: String, password: String) {
        AccountManager.default.login(userName, password, object : AccountManager.LoginListener {

            override fun loginSuccess(response: LoginResponse) {
                view?.showMessage(response.code)
                view?.enterMainActivity()
            }

            override fun loginFailed(message: String?) {

                if (message != null) {
                    view?.showMessage(message)

                }
            }
        })
    }


    override fun cancelRequest() {
        AccountManager.default.cancelRunningRequest()
    }

}