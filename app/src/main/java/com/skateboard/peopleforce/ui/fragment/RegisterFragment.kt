package com.skateboard.peopleforce.ui.fragment

import com.skateboard.library.ui.base.fragment.MVPBaseFragment
import com.skateboard.peopleforce.presenter.register.RegisterPresenter
import com.skateboard.peopleforce.presenter.register.RegisterView

/**
 * Created by skateboard on 2017/8/29.
 */
class RegisterFragment:MVPBaseFragment<RegisterView,RegisterPresenter>(),RegisterView {


    override fun attachPresenter(): RegisterPresenter {
        return RegisterPresenter()
    }

    override fun register() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}