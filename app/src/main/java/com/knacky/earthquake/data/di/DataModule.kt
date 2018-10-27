package com.knacky.earthquake.data.di

import android.bluetooth.BluetoothManager
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = arrayOf(NetworkModule::class, RepositoryModule::class))
class DataModule {

}