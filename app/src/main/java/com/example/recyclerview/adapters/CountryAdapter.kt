package com.example.recyclerview.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerview.R
import com.example.recyclerview.models.SimpleCountryData

class CountryAdapter(
    private var countryDataList: List<SimpleCountryData>,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<CountryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.country_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val countryData = countryDataList[position]
        holder.bind(countryData)
    }

    override fun getItemCount(): Int {
        return countryDataList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val countryNameTextView: TextView = itemView.findViewById(R.id.textCountryName)
        private val flagImageView: ImageView = itemView.findViewById(R.id.imageFlag)

        fun bind(countryData: SimpleCountryData) {
            countryNameTextView.text = countryData.name

            // Load flag image using Glide
            Glide.with(itemView.context)
                .load(countryData.pngFlagUrl)
                .into(flagImageView)

            itemView.setOnClickListener {
                onItemClick(countryData.name)
            }
        }
    }

    fun updateData(newData: List<SimpleCountryData>) {
        countryDataList = newData
        notifyDataSetChanged()
    }
}

