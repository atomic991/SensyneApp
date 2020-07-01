package com.example.sensyneapp.data.model

data class Hospital(
    val id: Long,
    val subType: String,
    val sector: String,
    val isPims: Boolean?,
    val name: String,
    val address: String?,
    val city: String?,
    val county: String?,
    val postCode: String?,
    val latitude: Double?,
    val longitude: Double?,
    val parentName: String?,
    val phone: String?,
    val email: String?,
    val website: String?
)