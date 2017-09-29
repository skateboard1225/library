package com.skateboard.library.util

import android.content.Context
import android.widget.Toast
import com.skateboard.library.application.Constants
import com.skateboard.library.application.manager.AccountManager
import com.skateboard.library.application.manager.LocalizationManager
import java.util.regex.Pattern

/**
 * Created by skateboard on 2017/9/23.
 */
object AccountUtil {

    val VALID="vakud"
    val USERNAME_EMPTY="app.ui.username.empty"
    val USERNAME_NOTVALID="app.ui.username.notvalid"
    val PASSWORD_EMPTY="app.ui.password.empty"
    val PASSWORD_LESSSIX="app.ui.passowrd.lesssix"
    val PASSWORD_MORETWELVE="app.ui.password.moretwelve"
    val PASSWORD_NOTVALID="app.ui.password.notvalid"


    fun checkUserNameValid(userName:String?):CharSequence
    {
        if(userName==null || userName=="")
        {
            return USERNAME_EMPTY
        }
        else
        {
          val regex="[0-9]+"
          var valid=Pattern.matches(regex,userName)
          if(!valid) return LocalizationManager.getString(USERNAME_NOTVALID)
        }
        return VALID
    }


    fun checkPasswordValid(password:String?):CharSequence
    {
        if(password==null || password=="")
        {
            return LocalizationManager.getString(PASSWORD_EMPTY)
        }
        else
        {
            if(password.length<6)
            {
                return LocalizationManager.getString(PASSWORD_LESSSIX)
            }
            else if(password.length>12)
            {
                return LocalizationManager.getString(PASSWORD_MORETWELVE)
            }
            else
            {
                var regex="^[A-Za-z]*[0-9]*$"
                var valid=Pattern.matches(regex,password)
                if(!valid) return LocalizationManager.getString(PASSWORD_NOTVALID)
            }
        }
        return VALID
    }

    fun getRegisterMessage(message:String):String
    {

        if("alreadyexists".equals(message))
        {
            return LocalizationManager.getString(Constants.APP_MESSAGE_USERALREADYEXISTS)
        }
        else if("tokenfailed".equals(message))
        {
            return LocalizationManager.getString(Constants.APP_MESSAGE_REGISTERCODEFAILED)
        }
        else if("registered".equals(message))
        {
            return LocalizationManager.getString(Constants.APP_MESSAGE_REGISTERSUCCESS)
        }
        else
        {
            return LocalizationManager.getString(Constants.APP_MESSAGE_NETWORKERROR)
        }

    }

    fun showToastByLoginCode(context: Context?, code:String)
    {
        if(code.equals(AccountManager.LOGIN_SUCCESS))
        {
            Toast.makeText(context,LocalizationManager.getString(Constants.APP_MESSAGE_LOGINSUCCESS),Toast.LENGTH_SHORT).show()
        }
        else if(code.equals(AccountManager.NOT_REGISTERED))
        {
            Toast.makeText(context,LocalizationManager.getString(Constants.APP_MESSAGE_NOTREGISTERED),Toast.LENGTH_SHORT).show()
        }

        else
        {
            Toast.makeText(context,LocalizationManager.getString(Constants.APP_MESSAGE_NETWORKERROR),Toast.LENGTH_SHORT).show()
        }
    }


}