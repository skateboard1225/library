package com.skateboard.library

import android.content.Context
import android.content.DialogInterface.OnClickListener
import android.support.v7.app.AlertDialog
import com.neulion.library.ui.widget.base.LoadingDialog
import com.skateboard.library.application.manager.LocalizationManager


/**
 * Created by skateboard on 2017/8/2.
 */

object DialogUtil {


    private var loadingDialog: LoadingDialog?=null


    fun showLoadingDialog(context: Context, msg: String= LocalizationManager.getString("nl.ui.loading") as String):LoadingDialog?
    {
        loadingDialog= LoadingDialog.showLoading(context,msg)

        return loadingDialog
    }

    fun hideLoadingDialog()
    {
        loadingDialog?.dismiss()
    }



    fun showAlertDialog(context: Context, message: String, okListener: OnClickListener?=null, cancelListener: OnClickListener?=null) {
        val builder = AlertDialog.Builder(context)

        builder.setMessage(message)

        builder.setCancelable(false)

        if(okListener!=null) {

            builder.setPositiveButton("OK", okListener)

        }
        if (cancelListener != null) {
            builder.setNegativeButton("CANCEL", cancelListener)
        }

        builder.create().show()
    }





    fun showMessageDialog(context: Context?, message: String, okListener: OnClickListener?): AlertDialog? {
        if(context!=null)
        {
            val builder = AlertDialog.Builder(context)
            builder.setMessage(message)

                    .setCancelable(false)

                    .setPositiveButton("OK", okListener)

            return builder.show()
        }
        else
        {
            return null;
        }
    }
}