package com.example.sensyneapp.ui.main.fragment

import com.example.sensyneapp.data.model.Hospital

class HospitalItemViewModel(val hospital: Hospital, private val listener: (Hospital) -> Unit){

    fun onHospitalClick() {
        listener.invoke(hospital)
    }

}