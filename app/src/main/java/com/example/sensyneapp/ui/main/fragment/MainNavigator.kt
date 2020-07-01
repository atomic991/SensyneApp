package com.example.sensyneapp.ui.main.fragment

import com.example.sensyneapp.data.model.Hospital
import com.example.sensyneapp.ui.base.BaseNavigator

interface MainNavigator : BaseNavigator {

    fun showHospitals(response: List<Hospital>)
}