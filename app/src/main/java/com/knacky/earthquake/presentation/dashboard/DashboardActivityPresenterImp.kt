package com.knacky.earthquake.presentation.dashboard

import android.util.Log
import com.google.gson.Gson
import com.knacky.earthquake.data.entity.HttpErrorBody
import com.knacky.earthquake.data.entity.dashboard.DataOfEarthquake
import com.knacky.earthquake.domain.GetEarthquakeDataUseCase
import com.knacky.earthquake.presentation.dashboard.DashboardActivityPresenter
import io.reactivex.Single
import retrofit2.HttpException

/**
 * Created by knacky on 27.10.2018.
 */
class DashboardActivityPresenterImp<T : DashboardActivityPresenter.DashboardActivityView>(val getEarthquakeDataUseCase: GetEarthquakeDataUseCase)
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
            Log.i("earthData", "successfully got data: $it")
        }, {
            val httpErrorBody = isHttpException(it)
            if (httpErrorBody != null) {
                Log.e("earthData", "FAILED while getting data: ${httpErrorBody.message}")
            } else {
                Log.e("earthData", "FAILED while getting data: $it")
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