package com.example.sensyneapp.ui.base

import androidx.lifecycle.ViewModel
import com.example.sensyneapp.data.DataManager
import java.lang.ref.WeakReference

abstract class BaseViewModel<N: BaseNavigator>(private val dataManager: DataManager): ViewModel(){

    private var navigator: WeakReference<N>? = null

    fun getDataManager(): DataManager = dataManager

    fun getNavigator(): N = navigator!!.get()!!

    fun setNavigator(navigator: N){
        this.navigator = WeakReference(navigator)
    }

    fun handleError(throwable: Throwable){
        throwable.printStackTrace()

        //TODO handle error (send to Crashlytics etc.)
    }
}