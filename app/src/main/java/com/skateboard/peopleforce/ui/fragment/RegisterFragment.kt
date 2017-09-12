package com.skateboard.peopleforce.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}