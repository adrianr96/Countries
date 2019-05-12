package com.example.android.countries.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CountryProperty(
    val name: String,
    val flag: String,
    val capital: String,
    val latlng: DoubleArray
) : Parcelable