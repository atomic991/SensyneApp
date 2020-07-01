package com.example.sensyneapp.data.remote

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface API {

    @GET("/data/foi/Hospital.csv")
    fun downloadFile(): Call<ResponseBody>

}