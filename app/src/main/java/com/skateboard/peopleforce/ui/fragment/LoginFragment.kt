package com.skateboard.peopleforce.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.skateboard.library.application.Constants
import com.skateboard.library.application.manager.LocalizationManager
import com.skateboard.library.util.AccountUtil
import com.skateboard.peopleforce.R
import com.skateboard.peopleforce.presenter.login.LoginPresenter
import com.skateboard.peopleforce.presenter.login.LoginView
import com.skateboard.peopleforce.ui.activity.MainActivity
import com.skateboard.peopleforce.ui.activity.RegisterActivity
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * Created by skateboard on 2017/9/22.
 */
class LoginFragment : PEBaseFragment<LoginView, LoginPresenter>(), LoginView, View.OnClickListener {


    override fun attachPresenter(): LoginPresenter {
        return LoginPresenter()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater?.inflate(R.layout.fragment_login, container, false)

        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPhoneNum()
        initPassword()
        initLoginBtn()
        initRegisterBtn()
    }

    private fun initPhoneNum() {
        phoneInp.hint = LocalizationManager.getString(Constants.APP_UI_USERNAME)
    }


    private fun initPassword() {
        passwordInp.hint = LocalizationManager.getString(Constants.APP_UI_PASSWORD)
    }

    private fun initLoginBtn() {
        loginBtn.setOnClickListener(this)
        loginBtn.text=LocalizationManager.getString(Constants.APP_UI_LOGIN)

    }

    private fun initRegisterBtn() {
        registerBtn.setOnClickListener(this)
        registerBtn.text=LocalizationManager.getString(Constants.APP_UI_REGISTER)
    }

    override fun onClick(view: View?) {

        when (view?.id) {

            R.id.loginBtn -> {
                presenter?.login(phoneInp.editText?.text.toString(), passwordInp.editText?.text.toString())
            }


            R.id.registerBtn -> {

                enterRegisterActivity()

            }

        }
    }

    override fun enterMainActivity() {

        val intent = Intent(activity, MainActivity::class.java)

        startActivity(intent)

        activity?.finish()

    }

    private fun enterRegisterActivity()
    {
        val intent=Intent(activity,RegisterActivity::class.java)

        startActivity(intent)
    }

    override fun showMessage(message: String) {
        AccountUtil.showToastByLoginCode(activity?.applicationContext,message)
    }
}