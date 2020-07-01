package com.example.sensyneapp.ui.main.fragment

import com.example.sensyneapp.data.DataManager
import com.example.sensyneapp.data.scheduler.SchedulerProvider
import com.example.sensyneapp.ui.base.BaseViewModel

class MainViewModel(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) : BaseViewModel<MainNavigator>(dataManager, schedulerProvider) {



}