package com.example.sensyneapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sensyneapp.data.DataManager
import com.example.sensyneapp.data.scheduler.SchedulerProvider
import com.example.sensyneapp.ui.details.fragment.DetailsViewModel
import com.example.sensyneapp.ui.main.fragment.MainViewModel
import java.lang.IllegalArgumentException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelProviderFactory @Inject constructor(private val dataManager: DataManager, private val schedulerProvider: SchedulerProvider): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(dataManager, schedulerProvider) as T
            modelClass.isAssignableFrom(DetailsViewModel::class.java) -> DetailsViewModel(dataManager, schedulerProvider) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

}