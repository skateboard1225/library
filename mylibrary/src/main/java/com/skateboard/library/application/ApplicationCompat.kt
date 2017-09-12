package com.skateboard.library.application

import android.app.Activity
import android.app.Application
import android.os.Bundle

/**
 * Created by skateboard on 2017/9/11.
 */
class ApplicationCompat {


  companion object {


      fun registerActivityLifecycleCallbacks(application: Application,lifecycleCallbacks: Application.ActivityLifecycleCallbacks)
      {
          application.registerActivityLifecycleCallbacks(lifecycleCallbacks)
      }

      open class ApplicationUILifecycleCallback:Application.ActivityLifecycleCallbacks
      {

          private var mActivityCreatedCounter: Int = 0

          private var mActivityStartedCounter: Int = 0

          private var mActivityResumedCounter: Int = 0

          override fun onActivityPaused(activity: Activity?) {

              mActivityResumedCounter--

              if (mActivityResumedCounter == 0) {
                  onApplicationUIPaused(activity)
              }

          }

          override fun onActivityResumed(activity: Activity?) {

              if (mActivityResumedCounter == 0) {
                  onApplicationUIResumed(activity)
              }

              mActivityResumedCounter++
          }

          override fun onActivityStarted(p0: Activity?) {
              if (mActivityStartedCounter == 0) {
                  onApplicationUIStarted(p0)
              }

              mActivityStartedCounter++
          }

          override fun onActivityDestroyed(activity: Activity?) {

              mActivityCreatedCounter--

              if (mActivityCreatedCounter == 0) {
                  onApplicationUIDestroyed(activity)
              }
          }

          override fun onActivitySaveInstanceState(p0: Activity?, p1: Bundle?) {
          }

          override fun onActivityStopped(activity: Activity?) {

              mActivityStartedCounter--

              if (mActivityStartedCounter == 0) {
                  onApplicationUIStopped(activity)
              }
          }

          override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {

              if (mActivityCreatedCounter == 0) {
                  onApplicationUICreated(activity, savedInstanceState)
              }

              mActivityCreatedCounter++
          }


          open fun onApplicationUICreated(activity: Activity?, savedInstanceState: Bundle?) {}

          open fun onApplicationUIDestroyed(activity: Activity?) {}

          open fun onApplicationUIStarted(activity: Activity?) {}

          open fun onApplicationUIStopped(activity: Activity?) {}

          open fun onApplicationUIPaused(activity: Activity?) {}

          open fun onApplicationUIResumed(activity: Activity?) {}

      }

  }

}