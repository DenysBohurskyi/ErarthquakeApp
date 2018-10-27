package com.knacky.earthquake.data.repository

import com.knacky.earthquake.data.entity.dashboard.DataOfEarthquake
import io.reactivex.Single

/**
 * Created by knacky on 27.10.2018.
 */
interface DashboardRepository {
    fun getEarthquakeData(): Single<DataOfEarthquake>
}