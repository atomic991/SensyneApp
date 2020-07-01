package com.example.sensyneapp.data.model

import android.os.Parcel
import android.os.Parcelable

data class Hospital(
    val id: Long,
    val subType: String?,
    val sector: String?,
    val isPims: Boolean?,
    val name: String?,
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
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readLong(),
        source.readString(),
        source.readString(),
        source.readValue(Boolean::class.java.classLoader) as Boolean?,
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString(),
        source.readValue(Double::class.java.classLoader) as Double?,
        source.readValue(Double::class.java.classLoader) as Double?,
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeLong(id)
        writeString(subType)
        writeString(sector)
        writeValue(isPims)
        writeString(name)
        writeString(address)
        writeString(city)
        writeString(county)
        writeString(postCode)
        writeValue(latitude)
        writeValue(longitude)
        writeString(parentName)
        writeString(phone)
        writeString(email)
        writeString(website)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Hospital> = object : Parcelable.Creator<Hospital> {
            override fun createFromParcel(source: Parcel): Hospital = Hospital(source)
            override fun newArray(size: Int): Array<Hospital?> = arrayOfNulls(size)
        }
    }
}