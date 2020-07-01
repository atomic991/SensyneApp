package com.example.sensyneapp.ui.main.fragment

import com.example.sensyneapp.data.DataManager
import com.example.sensyneapp.data.model.Hospital
import com.example.sensyneapp.data.scheduler.SchedulerProvider
import com.example.sensyneapp.ui.base.BaseViewModel

class MainViewModel(
    dataManager: DataManager,
    schedulerProvider: SchedulerProvider
) : BaseViewModel<MainNavigator>(dataManager, schedulerProvider) {

    private var hospitals: List<Hospital>? = null

    fun loadData() {
        getCompositeDisposable().add(
            getDataManager().getApiHelper().loadCSV()
                .observeOn(getSchedulerProvider().ui())
                .subscribeOn(getSchedulerProvider().io())
                .subscribe(
                    { response ->
                        this.hospitals = response
                        getNavigator().showHospitals(response)
                    },
                    { error -> handleError(error) }
                )
        )
    }



}