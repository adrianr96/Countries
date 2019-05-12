package com.example.android.countries.countrylist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.countries.databinding.CountryListItemBinding
import com.example.android.countries.network.CountryProperty

class PhotoListAdapter(private val onClickListner: OnClickListner) :
    ListAdapter<CountryProperty, PhotoListAdapter.CountryPropertyViewHolder>(DiffCallback) {
    class CountryPropertyViewHolder(private var binding: CountryListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(countryProperty: CountryProperty) {
            binding.property = countryProperty
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<CountryProperty>() {
        override fun areItemsTheSame(oldItem: CountryProperty, newItem: CountryProperty): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: CountryProperty, newItem: CountryProperty): Boolean {
            return oldItem.name == newItem.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryPropertyViewHolder {
        return CountryPropertyViewHolder(CountryListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: CountryPropertyViewHolder, position: Int) {
        val countryProperty = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListner.onClick(countryProperty)
        }
        holder.bind(countryProperty)
    }

    class OnClickListner(val clickListener: (countryProperty: CountryProperty) -> Unit) {
        fun onClick(countryProperty: CountryProperty) = clickListener(countryProperty)
    }
}
