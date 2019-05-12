package com.example.android.countries

import android.app.Activity
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahmadrosid.svgloader.SvgLoader
import com.example.android.countries.countrylist.CountryApiStatus
import com.example.android.countries.countrylist.PhotoListAdapter
import com.example.android.countries.network.CountryProperty


@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<CountryProperty>?) {
    val adapter = recyclerView.adapter as PhotoListAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        SvgLoader.pluck()
            .with(imgView.context as Activity?)
            .setPlaceHolder(R.drawable.loading_animation, R.drawable.broken_image)
            .load(imgUrl, imgView)
    }
}

@BindingAdapter("countryApiStatus")
fun bindStatus(statusImageView: ImageView, status: CountryApiStatus?) {
    when (status) {
        CountryApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        CountryApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.connection_error)
        }
        CountryApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}


