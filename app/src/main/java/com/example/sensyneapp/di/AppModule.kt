package com.example.sensyneapp.di

import android.app.Application
import android.content.Context
import com.example.sensyneapp.data.AppDataManager
import com.example.sensyneapp.data.DataManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideDataManager(appDataManager: AppDataManager): DataManager {
        return appDataManager
    }

}