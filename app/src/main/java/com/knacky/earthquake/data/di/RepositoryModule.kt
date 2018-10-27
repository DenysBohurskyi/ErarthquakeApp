package com.knacky.earthquake.data.di

import android.content.Context
import com.knacky.earthquake.data.repository.DashboardRepository
import com.knacky.earthquake.data.repository.DashboardRepositoryImpl
import com.knacky.earthquake.data.rest.EarthquakeApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideHomeRepository(cotext: Context, earthquakeApi: EarthquakeApi): DashboardRepository {
        return DashboardRepositoryImpl(cotext, earthquakeApi)
    }
}