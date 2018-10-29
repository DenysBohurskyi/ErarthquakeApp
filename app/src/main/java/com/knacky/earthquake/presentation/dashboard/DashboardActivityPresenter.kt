package com.knacky.earthquake.presentation.dashboard

import com.knacky.earthquake.data.entity.Earthquake
import com.knacky.earthquake.presentation.Presenter

/**
 * Created by knacky on 27.10.2018.
 */
interface DashboardActivityPresenter<T>: Presenter<T> {

    interface DashboardActivityView{
        fun onEarthDataGot(allEarthquakes: List<Earthquake>)
    }


    fun getEarthquakeData()
}