package com.knacky.earthquake.presentation.dashboard.di

import com.knacky.earthquake.presentation.di.PerActivity
import com.knacky.earthquake.presentation.dashboard.DashboardActivity
import dagger.Subcomponent

/**
 * Created by knacky on 27.10.2018.
 */

@Subcomponent(modules = arrayOf(DashboardModule::class))
@PerActivity
interface DashboardComponent {

    fun inject(dashboardActivity: DashboardActivity)

}