package com.neulion.library.ui.widget.base

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import com.skateboard.library.R
import com.skateboard.library.application.manager.LocalizationManager

/**
 * Created by skateboard on 2017/9/5.
 */
class LoadingDialog(context: Context?, themeResId: Int) : Dialog(context, themeResId) {

    private var progressBar: ProgressBar?=null

    private var message: TextView?=null

    constructor(context: Context?) : this(context, 0)

    fun setMessage(text:CharSequence)
    {
        show()

        message?.text=text

    }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.loading_dialog_layout)

        setCanceledOnTouchOutside(false)

        progressBar = findViewById(R.id.progress_bar)

        message=findViewById(R.id.message)
    }

    companion object
    {

        fun showLoading(context: Context?, message:CharSequence= LocalizationManager.getString("nl.ui.loading"), cancelable:Boolean=false, onCancelListener:DialogInterface.OnCancelListener?=null):LoadingDialog
        {
            var loadingDialog:LoadingDialog= LoadingDialog(context)

            var layoutParams=loadingDialog.window.attributes

            loadingDialog.show()

            layoutParams.width= (420*loadingDialog.context.resources.displayMetrics.density).toInt()

            layoutParams.height=(120*loadingDialog.context.resources.displayMetrics.density).toInt()

            loadingDialog.window.attributes=layoutParams

            loadingDialog.setMessage(message)

            loadingDialog.setCancelable(cancelable)

            loadingDialog.setOnCancelListener(onCancelListener)

            return loadingDialog

        }

    }



}