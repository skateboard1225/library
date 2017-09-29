package com.skateboard.peopleforce.ui.fragment

import com.skateboard.library.presenter.base.IPresenter
import com.skateboard.library.presenter.base.IView
import com.skateboard.library.ui.base.fragment.MVPBaseFragment

/**
 * Created by skateboard on 2017/9/22.
 */
abstract class PEBaseFragment<V:IView,T:IPresenter<V>>:MVPBaseFragment<V,T>() {


}