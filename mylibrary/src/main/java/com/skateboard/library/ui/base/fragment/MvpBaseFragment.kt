package com.skateboard.library.ui.base.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import com.skateboard.library.presenter.base.IPresenter
import com.skateboard.library.presenter.base.IView

/**
 * Created by skateboard on 2017/8/29.
 */
abstract class MVPBaseFragment<V: IView,T: IPresenter<V>> : Fragment(),IView {

    protected var presenter:T?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter=attachPresenter()
        presenter?.attachView(this as V)
    }

    protected abstract fun attachPresenter():T

    override fun onDestroy() {
        super.onDestroy()
        detechPresenter()
    }

    protected fun detechPresenter()
    {
        presenter?.cancelRequest()
        presenter?.detechView()
    }
}