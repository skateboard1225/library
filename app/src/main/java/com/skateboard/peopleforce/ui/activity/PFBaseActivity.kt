package com.skateboard.peopleforce.ui.activity

import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.skateboard.library.ui.base.activity.BaseActivity
import com.skateboard.peopleforce.R

/**
 * Created by skateboard on 2017/9/24.
 */
open class PFBaseActivity:BaseActivity() {

    protected var toolBar: Toolbar?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onContentChanged() {
        super.onContentChanged()
        toolBar=findViewById(R.id.toolbar) as? Toolbar
        setSupportActionBar(toolBar)
        toolBar?.title=getToolbarTitle()
    }

    open fun getToolbarTitle():String
    {
        return ""
    }
}