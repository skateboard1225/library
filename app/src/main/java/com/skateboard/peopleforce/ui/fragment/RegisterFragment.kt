package com.skateboard.peopleforce.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.skateboard.library.application.manager.LocalizationManager
import com.skateboard.library.util.showSnackBar
import com.skateboard.library.util.showToast
import com.skateboard.peopleforce.R
import com.skateboard.peopleforce.presenter.register.RegisterPresenter
import com.skateboard.peopleforce.presenter.register.RegisterView
import com.skateboard.peopleforce.ui.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_register.*

/**
 * Created by skateboard on 2017/8/29.
 */
class RegisterFragment:PEBaseFragment<RegisterView,RegisterPresenter>(),RegisterView,View.OnClickListener {


    override fun attachPresenter(): RegisterPresenter {
        return RegisterPresenter()
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val itemView=inflater?.inflate(R.layout.fragment_register,container,false)

        return itemView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPhoneNum()
        initPassword()
        initGetRegisterCodeBtn()
        initRegisterBtn()
    }

    private fun initPhoneNum()
    {
        phoneInp?.hint=LocalizationManager.getString("app.ui.username")
    }

    private fun initPassword()
    {
        passwordInp?.hint=LocalizationManager.getString("app.ui.password")
    }

    private fun initGetRegisterCodeBtn()
    {
        getCodeBtn?.setOnClickListener(this)
    }

    private fun initRegisterBtn()
    {

        registerBtn.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id)
        {
            R.id.registerBtn->presenter?.register(phoneInp.editText?.text.toString(),passwordInp.editText?.text.toString(),registerCode.text.toString())

            R.id.getCodeBtn->presenter?.getRegisterCode(phoneInp.editText?.text.toString())
        }

    }

    override fun showMessage(message: String) {
        showToast(message)
    }

    override fun enterMainActivity() {

        val intent=Intent(activity,MainActivity::class.java)

        activity?.startActivity(intent)

        activity?.finish()
    }
}