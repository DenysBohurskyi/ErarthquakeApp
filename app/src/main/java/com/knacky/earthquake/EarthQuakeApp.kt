package com.knacky.earthquake

import android.app.Application
import android.util.Log
import com.knacky.earthquake.presentation.di.AppModule
import com.knacky.earthquake.presentation.di.AppComponent
import com.knacky.earthquake.presentation.di.DaggerAppComponent
import io.reactivex.plugins.RxJavaPlugins

/**
 * Created by knacky on 26.10.2018.
 */
class EarthQuakeApp: Application() {
    companion object {
        var earthQuakeApp: EarthQuakeApp? = null

        var appComponent: AppComponent? = null
    }

    override fun onCreate() {
        super.onCreate()

        earthQuakeApp = this
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        appComponent?.inject(this)

        RxJavaPlugins.setErrorHandler {
            Log.i("onxErrorHandler", "error: $it")
            it.printStackTrace()
        }
    }
}