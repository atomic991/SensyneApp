package com.example.sensyneapp.ui.base

import androidx.lifecycle.ViewModel
import com.example.sensyneapp.data.DataManager
import com.example.sensyneapp.data.scheduler.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

abstract class BaseViewModel<N: BaseNavigator>(
    private val dataManager: DataManager,
    private val schedulerProvider: SchedulerProvider
): ViewModel(){

    private var navigator: WeakReference<N>? = null

    private val compositeDisposable = CompositeDisposable()

    fun setNavigator(navigator: N){
        this.navigator = WeakReference(navigator)
    }

    fun getDataManager(): DataManager = dataManager

    fun getNavigator(): N = navigator!!.get()!!

    fun getSchedulerProvider(): SchedulerProvider = schedulerProvider

    fun getCompositeDisposable(): CompositeDisposable = compositeDisposable

    fun handleError(throwable: Throwable){
        throwable.printStackTrace()

        //TODO handle error (send to Crashlytics etc.)
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}