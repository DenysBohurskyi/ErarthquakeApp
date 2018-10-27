package com.knacky.earthquake.presentation.dashboard

import com.knacky.earthquake.data.entity.dashboard.DataOfEarthquake
import com.knacky.earthquake.presentation.Presenter
import io.reactivex.Single

/**
 * Created by knacky on 27.10.2018.
 */
interface DashboardActivityPresenter<T>: Presenter<T> {

    interface DashboardActivityView{
//        fun onEarthDataGot()
    }

    fun getEarthquakeData()
}