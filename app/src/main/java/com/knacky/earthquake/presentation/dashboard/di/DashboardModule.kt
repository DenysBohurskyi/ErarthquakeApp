package com.knacky.earthquake.presentation.dashboard.di

import com.knacky.earthquake.presentation.di.PerActivity
import com.knacky.earthquake.data.repository.DashboardRepository
import com.knacky.earthquake.domain.GetEarthquakeDataUseCase
import com.knacky.earthquake.domain.base.schedulers.ObserveOn
import com.knacky.earthquake.domain.base.schedulers.SubscribeOn
import com.knacky.earthquake.presentation.dashboard.DashboardActivityPresenter
import com.knacky.earthquake.presentation.dashboard.DashboardActivityPresenterImp
import dagger.Module
import dagger.Provides

/**
 * Created by knacky on 27.10.2018.
 */
@Module
class DashboardModule {

    @Provides
    @PerActivity
    fun provideDashboardActivityPresenter(getEarthquakeDataUseCase: GetEarthquakeDataUseCase)
            : DashboardActivityPresenter<DashboardActivityPresenter.DashboardActivityView> {
        return DashboardActivityPresenterImp(getEarthquakeDataUseCase)
    }

    @Provides
    @PerActivity
    fun provideGetEarthquakeDataUseCase(subscribeOn: SubscribeOn, observeOn: ObserveOn, dashboardRepository: DashboardRepository)
            : GetEarthquakeDataUseCase = GetEarthquakeDataUseCase(subscribeOn, observeOn, dashboardRepository)
}