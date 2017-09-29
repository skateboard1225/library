package com.skateboard.library.application.manager

import android.app.Application
import com.neulion.library.application.manager.ShareDataManager
import com.neulion.library.application.manager.ShareDataManager.*
import com.skateboard.library.application.Constants
import com.skateboard.library.application.manager.base.BaseManager
import com.skateboard.library.bean.BaseResponse
import com.skateboard.library.bean.account.GetRegisterCodeResponse
import com.skateboard.library.bean.account.LoginResponse
import com.skateboard.library.bean.account.RegisterResponse
import com.skateboard.library.network.RetrofitManager
import com.skateboard.library.request.account.LoginRequest
import com.skateboard.library.request.account.RegisterRequest
import com.skateboard.library.util.showToast
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * Created by skateboard on 2017/9/23.
 */
class AccountManager : BaseManager() {


    private var dispose:Disposable?=null

    val accountStateChangedListenerSet:Set<AccountStateChangedListener> = mutableSetOf()

    override fun onCreate(application: Application) {

    }


    interface LoginListener {
        fun loginSuccess(response: LoginResponse)

        fun loginFailed(message: String?)
    }

    interface RegisterListener
    {
        fun registerSuccess(response:RegisterResponse)

        fun registerFailed(message: String?)
    }

    interface AccountStateChangedListener
    {
        fun loginChanged(isLogin:Boolean)
    }

    fun getRegisterCode(userName: String):String
    {
        var request=RetrofitManager.getRequest(RegisterRequest::class.java)
        var getRegisterCodeObservable=RetrofitManager.observer(request.getRegisterCode(userName))
        var registerCode=""
        getRegisterCodeObservable.subscribe(object : Observer<GetRegisterCodeResponse> {
            override fun onNext(t: GetRegisterCodeResponse) {

                registerCode=t.registerCode

            }

            override fun onComplete() {

            }

            override fun onError(e: Throwable) {

            }

            override fun onSubscribe(d: Disposable) {

                dispose=d
            }


        })

        return registerCode
    }

    fun register(phoneNum:String,password: String,registerCode:String,listener:RegisterListener?=null)
    {
        var request=RetrofitManager.getRequest(RegisterRequest::class.java)
        var registerObservable=RetrofitManager.observer(request.register(phoneNum,password,registerCode))
        registerObservable.subscribe(object : Observer<RegisterResponse> {
            override fun onNext(t: RegisterResponse) {

                if("registered".equals(t.message))
                {
                    listener?.registerSuccess(t)
                }
                else
                {
                    listener?.registerFailed(t.message)
                }

            }

            override fun onComplete() {

            }

            override fun onSubscribe(d: Disposable) {
                 dispose=d
            }

            override fun onError(e: Throwable) {

                listener?.registerFailed(e.message)

            }


        })

    }
    

    fun login(userName: String, password: String, listener: LoginListener?=null): Observable<LoginResponse> {

        var request = RetrofitManager.getRequest(LoginRequest::class.java)
        var loginResponse = request.login(userName, password)
        val loginObservable = RetrofitManager.observer(loginResponse)
        loginObservable.subscribe(object : Observer<LoginResponse> {
            override fun onError(e: Throwable) {

                listener?.loginFailed(e.message)

            }

            override fun onSubscribe(d: Disposable) {

                dispose=d
            }

            override fun onComplete() {
            }

            override fun onNext(response: LoginResponse) {

                if (LOGIN_SUCCESS.equals(response.code)) {
                    saveAccountInfo(response)
                    listener?.loginSuccess(response)
                    notifyAccountStateChanged(true)
                } else {
                    listener?.loginFailed(response.code)
                }

            }

        })
        return loginObservable

    }

    fun cancelRunningRequest()
    {
        dispose?.dispose()
    }

    fun logOut()
    {
        cleanAccountInfo()
        notifyAccountStateChanged(false)
    }


    private fun notifyAccountStateChanged(isLogin: Boolean)
    {
        for(listener in accountStateChangedListenerSet)
        {
            listener.loginChanged(isLogin)
        }
    }

    private fun saveAccountInfo(response: LoginResponse) {
        ShareData.savePreferenceString(TOKEN_KEY, response.token)
        ShareData.savePreferenceString(PORTRAIT_KEY, response.portrait)
        ShareData.savePreferenceString(USERNAME_KEY, response.username)
//        ShareData.savePreferenceString(CREATEDTIME_KEY, response.created_at)
        ShareData.savePreferenceString(NICKNAME_KEY, response.nickname)

    }


    private fun cleanAccountInfo() {
        ShareData.savePreferenceString(TOKEN_KEY, "")
        ShareData.savePreferenceString(PORTRAIT_KEY, "")
        ShareData.savePreferenceString(USERNAME_KEY, "")
//        ShareData.savePreferenceString(CREATEDTIME_KEY, "")
        ShareData.savePreferenceString(NICKNAME_KEY, "")
    }

    companion object {

        val default:AccountManager
        get() {
            return BaseManager.MANAGERS[Constants.ACCOUNT_MANAGER] as AccountManager
        }

        val LOGIN_SUCCESS = "loginsuccess"
        val NOT_REGISTERED = "notregistered"
        val TOKEN_EXPIRED = "tokenexpired"

        val TOKEN_KEY = "token_key"
        val USERNAME_KEY = "username_key"
        val PORTRAIT_KEY = "portrait_key"
        val CREATEDTIME_KEY = "createdtime_key"
        val NICKNAME_KEY = "nickname_key"

    }




}