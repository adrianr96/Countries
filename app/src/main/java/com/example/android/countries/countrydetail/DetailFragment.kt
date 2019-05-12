package com.example.android.countries.countrydetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.android.countries.databinding.FragmentCountryDetailBinding
import com.example.android.countries.R
import androidx.fragment.app.FragmentTransaction
import android.R.attr.fragment
import android.util.Log
import androidx.navigation.fragment.findNavController
import com.example.android.countries.network.CountryProperty
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class DetailFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        @Suppress("UNUSED_VARIABLE")
        val application = requireNotNull(activity).application
        val binding = FragmentCountryDetailBinding.inflate(inflater)
        val countryProperty = DetailFragmentArgs.fromBundle(arguments!!).selectedProperty
        val viewModelFactory = DetailViewModelFactory(countryProperty, application)
        binding.viewModel = ViewModelProviders.of(
            this, viewModelFactory
        ).get(DetailViewModel::class.java)
        binding.setLifecycleOwner(this)

        setupMap(countryProperty)
        return binding.root
    }

    private fun setupMap(countryProperty: CountryProperty) {

        val supportMapFragment = SupportMapFragment.newInstance()
        val latLng = LatLng(countryProperty.latlng[0], countryProperty.latlng[1])
        val name = countryProperty.name
        CameraPosition.builder().target(latLng).build()

        val onMapReadyCallback = OnMapReadyCallback { googleMap ->
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 3F))
            googleMap.addMarker(MarkerOptions().position(latLng).title(name))
        }

        supportMapFragment.getMapAsync(onMapReadyCallback)
        val manager = fragmentManager

        val transaction = manager?.beginTransaction()

        transaction?.replace(R.id.maps, supportMapFragment)

        transaction?.commit()
    }
}
