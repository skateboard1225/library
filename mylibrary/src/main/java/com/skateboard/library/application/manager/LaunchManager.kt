package com.skateboard.library.application.manager

import android.app.Activity
import android.app.Application
import com.skateboard.library.application.ApplicationCompat
import com.skateboard.library.application.Constants
import com.skateboard.library.application.manager.base.BaseManager
import com.skateboard.library.network.RetrofitManager
import com.skateboard.library.request.GetLocalizationService
import com.skateboard.library.request.GetMainConfigService
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.io.BufferedReader
import java.io.StringReader


/**
 * Created by skateboard on 2017/9/9.
 */
class LaunchManager : BaseManager() {

    companion object {

        val default: LaunchManager
            get() {

                return BaseManager.MANAGERS[Constants.LAUNCH_MANAGER] as LaunchManager
            }


        val LAUNCH_SPLASH_MODE=99

        val LAUNCH_SOFT_MODE=100

    }

    enum class LAUNCH_RESULT
    {
        LAUNCH_CANCELED,LAUNCH_FAILED,LAUNCH_SUCCESS,LAUNCH_CONFIGURATION_LOADED
    }

    private var disposable:Disposable?=null


    override fun onCreate(application: Application) {


        ApplicationCompat.registerActivityLifecycleCallbacks(application, object : ApplicationCompat.Companion.ApplicationUILifecycleCallback() {

            override fun onApplicationUIStarted(activity: Activity?) {
                super.onApplicationUIStarted(activity)
                if(ApplicationManager.default.isApplicationIntilized)
                {
                    startLaunch(LAUNCH_SOFT_MODE)
                }

            }


            override fun onApplicationUIDestroyed(activity: Activity?) {
                super.onApplicationUIDestroyed(activity)
                stopLaunch()
            }
        })

    }

    fun stopLaunch()
    {
        disposable?.dispose()
    }


    fun startLaunch(launchMode: Int) {


        if(launchMode== LAUNCH_SPLASH_MODE)
        {
            stopLaunch()
        }

        var launchObservable = Observable.just(launchMode)
                .subscribeOn(Schedulers.newThread())
                .concatMap {

                    val getMainConfigService = RetrofitManager.getData(GetMainConfigService::class.java)

                    val mainConfig = getMainConfigService.getMainConfig("http://peopleforce.app/mainconfig.json").execute().body()

                    Observable.just(mainConfig)
                }
                .subscribeOn(AndroidSchedulers.mainThread())
                .concatMap {
                    if (it != null) {
                        ConfigurationManager.default.mainConfig = it
                        ConfigurationManager.default.notifyOnConfigurationLoaded()
                        if(checkForceUpgrade())
                        {
                            Observable.just(LAUNCH_RESULT.LAUNCH_CANCELED)
                        }
                        else {
                            Observable.just(LAUNCH_RESULT.LAUNCH_CONFIGURATION_LOADED)
                        }
                    } else {
                        Observable.just(LAUNCH_RESULT.LAUNCH_FAILED)
                    }
                }
                .subscribeOn(Schedulers.newThread())
                .concatMap {
                    if (it !=LAUNCH_RESULT.LAUNCH_CONFIGURATION_LOADED)
                    {
                        Observable.just(it)

                    } else {
                        val getLocalizationService = RetrofitManager.getData(GetLocalizationService::class.java)

                        val url = ConfigurationManager.default.getUrl(Constants.NLID_FEED_LOCALIZATION)

                        val localization = getLocalizationService.getLocalization(url).execute().body()

                        if (localization != null) {

                            LocalizationManager.default.dealLocalization(localization)

                            Observable.just(LAUNCH_RESULT.LAUNCH_SUCCESS)
                        } else {
                            Observable.just(LAUNCH_RESULT.LAUNCH_FAILED)
                        }

                    }

                }

        subscribe(launchObservable)
    }


    fun subscribe(launchObservable: Observable<LAUNCH_RESULT>) {
        launchObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<LAUNCH_RESULT> {
                    override fun onError(e: Throwable) {

                        println("launch error "+e.message)

                    }

                    override fun onNext(t: LAUNCH_RESULT) {

                        if(t==LAUNCH_RESULT.LAUNCH_SUCCESS) {
                            ApplicationManager.default.isApplicationIntilized = true
                        }
                        notifyLaunchFinished(t)

                    }

                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {

                        disposable=d

                    }

                })
    }


    var onForceUpgradeCheckListenerSet:MutableSet<OnForceUpgradeCheckListener> = mutableSetOf()

    interface OnForceUpgradeCheckListener
    {
        fun checkForceUpgrade():Boolean
    }

    fun registerOnForceUpgradeCheckListener(listener:OnForceUpgradeCheckListener)
    {
        onForceUpgradeCheckListenerSet.add(listener)

    }

    fun unRegisterOnForceUpgradeCheckListener(listener:OnForceUpgradeCheckListener)
    {
        onForceUpgradeCheckListenerSet.remove(listener)
    }

    fun checkForceUpgrade():Boolean
    {
        for(listener in onForceUpgradeCheckListenerSet)
        {

            if (listener.checkForceUpgrade())
            {
                return true
            }
        }

        return false
    }


    interface OnLaunchFinishedListener {
        fun launchFinished(result: LAUNCH_RESULT)
    }


    var onLaunchFinishedListenerSet: MutableSet<OnLaunchFinishedListener> = mutableSetOf()

    fun registerOnLaunchFinishedListener(listener: OnLaunchFinishedListener) {
        onLaunchFinishedListenerSet.add(listener)
    }

    fun unRegisterOnLaunchFinishedListener(listener: OnLaunchFinishedListener) {
        onLaunchFinishedListenerSet.remove(listener)
    }

    fun notifyLaunchFinished(result: LAUNCH_RESULT) {
        for (listener in onLaunchFinishedListenerSet) {
            listener.launchFinished(result)
        }
    }
}