package com.example.sensyneapp.ui.main.fragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentProvider() {

    @ContributesAndroidInjector
    abstract fun provideMainFragment(): MainFragment
}