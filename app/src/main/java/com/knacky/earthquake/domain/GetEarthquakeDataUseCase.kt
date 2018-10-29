package com.knacky.earthquake.domain

import com.knacky.earthquake.data.entity.apiResponce.DataOfEarthquake
import com.knacky.earthquake.data.repository.DashboardRepository
import com.knacky.earthquake.domain.base.UseCaseSingle
import com.knacky.earthquake.domain.base.schedulers.ObserveOn
import com.knacky.earthquake.domain.base.schedulers.SubscribeOn
import io.reactivex.Single

/**
 * Created by knacky on 27.10.2018.
 */
class GetEarthquakeDataUseCase(subscribeOn: SubscribeOn, observeOn: ObserveOn, val dashboardRepository: DashboardRepository)
    :UseCaseSingle<DataOfEarthquake>(subscribeOn, observeOn){

    override val useCaseSingle: Single<DataOfEarthquake>
        get() = dashboardRepository.getEarthquakeData()
}