package com.skateboard.library.util

import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.View
import android.widget.Toast
import com.skateboard.library.application.manager.AccountManager

/**
 * Created by skateboard on 2017/9/23.
 */

fun Fragment.showToast(message: String?, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this.activity?.applicationContext, message, duration).show()
}

fun Fragment.showSnackBar(view: View?, message: String, duration: Int = Snackbar.LENGTH_SHORT) {
    if(view!=null) {
        Snackbar.make(view, message, duration).show()
    }
}