package com.skateboard.library.presenter.base

/**
 * Created by skateboard on 2017/8/29.
 */
open class IPresenter<T:IView>
{
    protected var view:T?=null

    fun attachView(view:T)
    {
        this.view=view
    }

    fun detechView()
    {
        view=null
    }
}