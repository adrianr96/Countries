package com.example.android.countries.countrydetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.countries.network.CountryProperty

class DetailViewModel(@Suppress("UNUSED_PARAMETER") countryProperty: CountryProperty, app: Application) :
    AndroidViewModel(app) {
    private val _selectedProperty = MutableLiveData<CountryProperty>()
    val selectedProperty: LiveData<CountryProperty>
        get() = _selectedProperty

    init {
        _selectedProperty.value = countryProperty
    }
}
