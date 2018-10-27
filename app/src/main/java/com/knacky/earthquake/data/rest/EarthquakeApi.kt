package com.knacky.earthquake.data.rest

import com.knacky.earthquake.data.entity.dashboard.DataOfEarthquake
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by knacky on 27.10.2018.
 */
interface EarthquakeApi {

    @GET("/fdsnws/event/1/query")
    fun getEarthquakeData(@Query("format") format: String,
                          @Query("eventtype") eventType: String,
                          @Query("starttime") timeFrom: String,
                          @Query("endtime") timeTo: String): Single<DataOfEarthquake>
}