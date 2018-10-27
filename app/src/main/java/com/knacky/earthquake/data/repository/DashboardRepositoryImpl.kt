package com.knacky.earthquake.data.repository

import android.content.Context
import com.knacky.earthquake.R
import com.knacky.earthquake.data.entity.dashboard.DataOfEarthquake
import com.knacky.earthquake.data.rest.EarthquakeApi
import com.knacky.earthquake.domain.GetEarthquakeDataUseCase
import com.knacky.earthquake.extensions.Utils
import io.reactivex.Single
import java.text.SimpleDateFormat

/**
 * Created by knacky on 27.10.2018.
 */
class DashboardRepositoryImpl(val context: Context, val earthquakeApi: EarthquakeApi) : DashboardRepository {

    override fun getEarthquakeData(): Single<DataOfEarthquake> {

        val timeFrom = Utils.getTimeForRequest(context).first
        val timeTo = Utils.getTimeForRequest(context).second

        val format = context.getString(R.string.earthquake_request_format)
        val eventType = context.getString(R.string.earthquake_request_event_type)

        return earthquakeApi.getEarthquakeData(format, eventType, timeFrom, timeTo)
    }


}