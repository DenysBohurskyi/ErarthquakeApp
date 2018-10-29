package com.knacky.earthquake.domain

import com.knacky.earthquake.data.entity.Earthquake
import com.knacky.earthquake.data.repository.DashboardRepository
import com.knacky.earthquake.domain.base.UseCaseSingle
import com.knacky.earthquake.domain.base.schedulers.ObserveOn
import com.knacky.earthquake.domain.base.schedulers.SubscribeOn
import io.reactivex.Single

/**
 * Created by knacky on 28.10.2018.
 */
class GetAllEarthquakesUseCase(subscribeOn: SubscribeOn, observeOn: ObserveOn, val dashboardRepository: DashboardRepository)
    : UseCaseSingle<List<Earthquake>>(subscribeOn, observeOn) {

    override val useCaseSingle: Single<List<Earthquake>>
        get() = dashboardRepository.getAllEarthquakes()
}