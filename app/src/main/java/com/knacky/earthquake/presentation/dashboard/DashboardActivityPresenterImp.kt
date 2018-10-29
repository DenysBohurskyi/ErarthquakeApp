package com.knacky.earthquake.presentation.dashboard

import android.util.Log
import com.google.gson.Gson
import com.knacky.earthquake.data.entity.HttpErrorBody
import com.knacky.earthquake.domain.GetAllEarthquakesUseCase
import com.knacky.earthquake.domain.GetEarthquakeDataUseCase
import retrofit2.HttpException

/**
 * Created by knacky on 27.10.2018.
 */
class DashboardActivityPresenterImp<T : DashboardActivityPresenter.DashboardActivityView>(val getEarthquakeDataUseCase: GetEarthquakeDataUseCase,
                                                                                          val getAllEarthquakesUseCase: GetAllEarthquakesUseCase)
    : DashboardActivityPresenter <T>{

    private var view: T? = null

    override fun setView(view: T) {
        this.view = view
    }

    override fun destroy() {
        view = null
    }

    override fun getEarthquakeData(){
        getEarthquakeDataUseCase.executeSingle({
            Log.i("earthData", "successfully got data from api: $it")
            getAllEarthquakes() //get already added data from database (see DashboardRepositoryImpl)
        }, {
            val httpErrorBody = isHttpException(it)
            if (httpErrorBody != null) {
                Log.e("earthData", "FAILED while getting data from api: ${httpErrorBody.message}")
            } else {
                Log.e("earthData", "FAILED while getting data from api: $it")
            }

            getAllEarthquakes() //get old data from dabase
        })
    }

    private fun getAllEarthquakes(){
        getAllEarthquakesUseCase.executeSingle({
            Log.i("earthData", "successfully got data from database: $it")
            view?.onEarthDataGot(it)
        },{
            val httpErrorBody = isHttpException(it)
            if (httpErrorBody != null) {
                Log.e("earthData", "FAILED while getting data from database: ${httpErrorBody.message}")
            } else {
                Log.e("earthData", "FAILED while getting data from database: $it")
            }
        })
    }

    private fun isHttpException(it: Throwable): HttpErrorBody? {
        if (it is HttpException) {
            return if (it.response().errorBody() != null) Gson().fromJson(it.response().errorBody()?.string(), HttpErrorBody::class.java)
            else null
        }
        return null
    }

}