package com.example.sensyneapp.di

import com.example.sensyneapp.ui.main.MainActivity
import com.example.sensyneapp.ui.main.fragment.MainFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [
        MainFragmentProvider::class
    ])
    abstract fun provideMainActivity(): MainActivity

}