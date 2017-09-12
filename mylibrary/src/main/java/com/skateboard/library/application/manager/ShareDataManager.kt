package com.neulion.library.application.manager

import android.app.Application
import android.content.Context
import android.preference.PreferenceManager

import com.skateboard.library.application.Constants
import com.skateboard.library.application.manager.base.BaseManager
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.Serializable

/**
 * Created by skateboard on 2017/7/31.
 */

open class ShareDataManager : BaseManager() {

    override fun onCreate(application: Application) {

    }


    open protected fun savePreferenceBoolean(key: String, value: Boolean) {
        PreferenceManager.getDefaultSharedPreferences(application).edit().putBoolean(key, value).commit()
    }


    open protected fun getPreferenceBoolean(key: String,defaultValue:Boolean=false): Boolean {
        return PreferenceManager.getDefaultSharedPreferences(application).getBoolean(key, defaultValue)
    }

    open protected fun savePreferenceInt(key: String, value: Int) {
        PreferenceManager.getDefaultSharedPreferences(application).edit().putInt(key, value).commit()
    }

    open protected fun getPreferenceInt(key: String): Int {
        return PreferenceManager.getDefaultSharedPreferences(application).getInt(key, -1)
    }

    open protected fun savePreferenceLong(key: String, value: Long) {
        PreferenceManager.getDefaultSharedPreferences(application).edit().putLong(key, value).commit()
    }

    open protected fun getPreferenceLong(key: String): Long {

        return PreferenceManager.getDefaultSharedPreferences(application).getLong(key, -1L)
    }

    open protected fun savePreferenceString(key: String, value: String) {
        PreferenceManager.getDefaultSharedPreferences(application).edit().putString(key, value).commit()
    }

    open protected fun getPreferenceString(key: String): String {
        return PreferenceManager.getDefaultSharedPreferences(application).getString(key, "")
    }


    open protected fun savePreferenceFloat(key: String, value: Float) {
        PreferenceManager.getDefaultSharedPreferences(application).edit().putFloat(key, value).commit()
    }

    open protected fun getPreferenceFloat(key: String): Float {
        return PreferenceManager.getDefaultSharedPreferences(application).getFloat(key, -1f)
    }

    open protected fun saveSerializable(fileName: String, obj: Serializable) {
        try {
            val objectOutputStream = ObjectOutputStream(application?.openFileOutput(fileName, Context.MODE_PRIVATE))
            objectOutputStream.writeObject(obj)
            objectOutputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    open protected fun getSerializable(fileName: String): Serializable? {
        var obj: Serializable? = null
        try {
            val objectInputStream = ObjectInputStream(application?.openFileInput(fileName))
            obj = objectInputStream.readObject() as Serializable
            objectInputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }

        return obj
    }



    object ShareData {

        fun savePreferenceBoolean(key: String, value: Boolean) {
            ShareDataManager.default.savePreferenceBoolean(key, value)
        }


        fun getPreferenceBoolean(key: String,defaultValue: Boolean=false): Boolean {
            return ShareDataManager.default.getPreferenceBoolean(key,defaultValue)
        }

        fun savePreferenceInt(key: String, value: Int) {
            ShareDataManager.default.savePreferenceInt(key, value)
        }

        fun getPreferenceInt(key: String): Int {
            return ShareDataManager.default.getPreferenceInt(key)
        }

        fun savePreferenceLong(key: String, value: Long) {
            ShareDataManager.default.savePreferenceLong(key, value)
        }

        fun getPreferenceLong(key: String): Long {
            return ShareDataManager.default.getPreferenceLong(key)
        }

        fun savePreferenceString(key: String, value: String) {
            ShareDataManager.default.savePreferenceString(key, value)
        }

        fun getPreferenceString(key: String): String {
            return ShareDataManager.default.getPreferenceString(key)
        }


        fun savePreferenceFloat(key: String, value: Float) {
            ShareDataManager.default.savePreferenceFloat(key, value)
        }

        fun getPreferenceFloat(key: String): Float {
            return ShareDataManager.default.getPreferenceFloat(key)
        }

        fun getSerializable(fileName: String): Serializable? {
            return ShareDataManager.default.getSerializable(fileName)
        }

        fun saveSerializable(fileName: String, obj: Serializable) {
            ShareDataManager.default.saveSerializable(fileName, obj)
        }

    }

    companion object {

        val default: ShareDataManager
            get() = BaseManager.MANAGERS.get(Constants.SHAREDATA_MANAGER) as ShareDataManager
    }

}
