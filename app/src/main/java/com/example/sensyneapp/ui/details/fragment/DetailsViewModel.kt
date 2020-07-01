package com.example.sensyneapp.ui.details.fragment

import androidx.databinding.ObservableField
import com.example.sensyneapp.data.DataManager
import com.example.sensyneapp.data.model.Hospital
import com.example.sensyneapp.data.scheduler.SchedulerProvider
import com.example.sensyneapp.ui.base.BaseViewModel

class DetailsViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider): BaseViewModel<DetailsNavigator>(dataManager, schedulerProvider){

    val hospitalId = ObservableField("")
    val address = ObservableField("")
    val phone = ObservableField<String>()
    val web = ObservableField<String>()
    val email = ObservableField<String>()

    fun setHospitalData(hospital: Hospital) {

        getNavigator().setTitle(hospital.name)

        hospitalId.set(hospital.id.toString())
        address.set(hospital.address)
        phone.set(if(hospital.phone?.isNotBlank() == true) hospital.phone else null)
        web.set(if(hospital.website?.isNotBlank() == true) hospital.website else null)
        email.set(if(hospital.email?.isNotBlank() == true) hospital.email else null)
    }

}