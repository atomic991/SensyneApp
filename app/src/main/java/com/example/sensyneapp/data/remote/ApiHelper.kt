package com.example.sensyneapp.data.remote

import com.example.sensyneapp.data.model.Hospital
import io.reactivex.Single

interface ApiHelper {

    fun loadCSV(): Single<List<Hospital>>

}