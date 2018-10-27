package com.knacky.earthquake.presentation.di


import com.knacky.earthquake.EarthQuakeApp
import com.knacky.earthquake.data.di.DataModule
import com.knacky.earthquake.presentation.dashboard.di.DashboardComponent
import com.knacky.earthquake.presentation.dashboard.di.DashboardModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class, DataModule::class))
@Singleton
interface AppComponent {

    fun provideDashboardComponent(dashboardModule: DashboardModule): DashboardComponent

    fun inject(earthQuakeApp: EarthQuakeApp)

}