package com.example.android.countries.countrydetail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.countries.network.CountryProperty

class DetailViewModelFactory(
    private val countryProperty: CountryProperty,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(countryProperty, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
