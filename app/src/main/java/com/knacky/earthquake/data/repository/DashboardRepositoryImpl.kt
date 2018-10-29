package com.knacky.earthquake.data.repository

import android.content.Context
import android.util.Log
import com.knacky.earthquake.R
import com.knacky.earthquake.data.database.EarthquakeDAO
import com.knacky.earthquake.data.entity.apiResponce.DataOfEarthquake
import com.knacky.earthquake.data.rest.EarthquakeApi
import com.knacky.earthquake.extensions.Utils
import io.reactivex.Single

/**
 * Created by knacky on 27.10.2018.
 */
class DashboardRepositoryImpl(val context: Context, val earthquakeApi: EarthquakeApi, val earthquakeDAO: EarthquakeDAO)
    : DashboardRepository {

    override fun getEarthquakeData(): Single<DataOfEarthquake> {

        val timeFrom = Utils.getTimeForRequest(context).first
        val timeTo = Utils.getTimeForRequest(context).second

        val format = context.getString(R.string.earthquake_request_format)
        val eventType = context.getString(R.string.earthquake_request_event_type)

        return earthquakeApi.getEarthquakeData(format, eventType, timeFrom, timeTo)
                .doOnEvent { t1, _ ->
                    Log.i("fillDb", "try to fill database with data: $t1")
                    pushEarthquakeDataToDB(t1)
                }
                .doOnError { Log.e("fillDbErr", it.toString())}
    }

    private fun pushEarthquakeDataToDB(dataOfEarthquake: DataOfEarthquake) {
//        val earthquakeDAO = EarthquakeDAO(context)
        Log.i("fillDb", "TIME = ${dataOfEarthquake.features[0].properties.time}, " +
                "after Utils time: ${Utils.getDateFormatString(dataOfEarthquake.features[0].properties.time, context)}")
        dataOfEarthquake.features.forEach {
            earthquakeDAO.createEarthquake(Utils.getDateFormatString(it.properties.time, context), it.properties.place, it.properties.mag.toString(),
                    it.geometry.coordinates[0].toString(), it.geometry.coordinates[1].toString())
        }
//        earthquakeDAO.close()
    }

    override fun getAllEarthquakes() = Single.fromCallable({earthquakeDAO.getAllEarthqquakes()})
}