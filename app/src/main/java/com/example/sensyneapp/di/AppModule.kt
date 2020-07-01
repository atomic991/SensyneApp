package com.example.sensyneapp.di

import android.app.Application
import android.content.Context
import com.example.sensyneapp.data.AppDataManager
import com.example.sensyneapp.data.DataManager
import com.example.sensyneapp.data.remote.API
import com.example.sensyneapp.data.remote.ApiHelper
import com.example.sensyneapp.data.remote.AppApiHelper
import com.example.sensyneapp.data.scheduler.AppSchedulerProvider
import com.example.sensyneapp.data.scheduler.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import retrofit2.Retrofit
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
    fun provideDataManager(appDataManager: AppDataManager): DataManager = appDataManager

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: AppApiHelper): ApiHelper = apiHelper

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://media.nhschoices.nhs.uk")
            .build()
    }

    @Provides
    @Singleton
    fun provideAPIService(retrofit: Retrofit): API = retrofit.create(API::class.java)

    @Provides
    fun provideScheduledProvider(): SchedulerProvider = AppSchedulerProvider()

}