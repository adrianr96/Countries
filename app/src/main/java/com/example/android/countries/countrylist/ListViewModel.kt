package com.example.android.countries.countrylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.countries.network.CountryApi
import com.example.android.countries.network.CountryProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.reflect.Array
import java.util.ArrayList

enum class CountryApiStatus { LOADING, ERROR, DONE }

class ListViewModel : ViewModel() {

    private val _status = MutableLiveData<CountryApiStatus>()

    val status: LiveData<CountryApiStatus>
        get() = _status

    private val _properties = MutableLiveData<List<CountryProperty>>()

    val properties: LiveData<List<CountryProperty>>
        get() = _properties

    private val _navigateToSelectedProperty = MutableLiveData<CountryProperty>()
    val navigateToSelectedProperty: LiveData<CountryProperty>
        get() = _navigateToSelectedProperty

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getCountryProperties()
    }


    private fun getCountryProperties() {
        coroutineScope.launch {
            var getPropertiesDeferred = CountryApi.retrofitService.getProperties()
            try {
                _status.value = CountryApiStatus.LOADING
                var listResult = getPropertiesDeferred.await()
                _status.value = CountryApiStatus.DONE
                _properties.value = listResult

            } catch (e: Exception) {
                _status.value = CountryApiStatus.ERROR
                _properties.value = ArrayList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun displayPropertyDetails(countryProperty: CountryProperty) {
        _navigateToSelectedProperty.value = countryProperty
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }
}