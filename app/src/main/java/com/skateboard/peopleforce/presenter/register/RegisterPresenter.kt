package com.skateboard.peopleforce.presenter.register

import android.text.TextUtils
import com.skateboard.library.application.Constants
import com.skateboard.library.application.manager.AccountManager
import com.skateboard.library.application.manager.LocalizationManager
import com.skateboard.library.bean.account.LoginResponse
import com.skateboard.library.bean.account.RegisterResponse
import com.skateboard.library.presenter.base.IPresenter
import com.skateboard.library.util.AccountUtil

/**
 * Created by skateboard on 2017/8/29.
 */
class RegisterPresenter:IPresenter<RegisterView>() {

    fun register(phoneNum:String,password:String,registerCode:String)
    {


        if(TextUtils.isEmpty(phoneNum))
        {

        }

        AccountManager.default.register(phoneNum, password, registerCode, object : AccountManager.RegisterListener {

            override fun registerSuccess(response: RegisterResponse) {

                login(phoneNum,password)
                view?.showMessage(AccountUtil.getRegisterMessage(response.message))

            }

            override fun registerFailed(message: String?) {

                view?.showMessage(AccountUtil.getRegisterMessage(message?:""))

            }
        })

    }

    fun getRegisterCode(phoneNum:String)
    {
        if(AccountUtil.VALID.equals(AccountUtil.checkUserNameValid(phoneNum)))
        {
             AccountManager.default.getRegisterCode(phoneNum)
        }
        else
        {
            view?.showMessage(LocalizationManager.getString(Constants.APP_MESSAGE_USERNAMENOTVALID))
        }
    }


    fun login(userName: String, password: String) {
        AccountManager.default.login(userName, password, object : AccountManager.LoginListener {

            override fun loginSuccess(response: LoginResponse) {
                view?.enterMainActivity()
            }

            override fun loginFailed(message: String?) {

            }
        })
    }


    override fun cancelRequest() {
        AccountManager.default.cancelRunningRequest()
    }


}