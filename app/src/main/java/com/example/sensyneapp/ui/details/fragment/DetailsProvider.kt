package com.example.sensyneapp.ui.details.fragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DetailsProvider {

    @ContributesAndroidInjector
    abstract fun provideDetailsFragment(): DetailsFragment

}