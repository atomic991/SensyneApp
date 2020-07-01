package com.example.sensyneapp.data

import com.example.sensyneapp.data.remote.ApiHelper
import javax.inject.Inject

class AppDataManager @Inject constructor(private val apiHelper: ApiHelper) : DataManager {

    override fun getApiHelper(): ApiHelper = apiHelper

}