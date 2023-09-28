package com.example.recyclerview.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.R
import com.example.recyclerview.models.University

class UniversityAdapter(private var universityList: List<University>) : RecyclerView.Adapter<UniversityAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.university_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val university = universityList[position]
        holder.bind(university)
    }

    override fun getItemCount(): Int {
        return universityList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val universityNameTextView: TextView = itemView.findViewById(R.id.textUniversityName)
        private val countryTextView: TextView = itemView.findViewById(R.id.textCountry)
        private val alphaCodeTextView: TextView = itemView.findViewById(R.id.textAlphaCode)
        private val stateProvinceTextView: TextView = itemView.findViewById(R.id.textStateProvince)
        private val domainsTextView: TextView = itemView.findViewById(R.id.textDomains)
        private val webPagesTextView: TextView = itemView.findViewById(R.id.textWebPages)

        fun bind(university: University) {
            universityNameTextView.text = university.name
            countryTextView.text = itemView.context.getString(R.string.country_label, university.country)
            alphaCodeTextView.text = itemView.context.getString(R.string.alpha_code_label, university.alpha_two_code)

            if (!university.state_province.isNullOrBlank()) {
                stateProvinceTextView.visibility = View.VISIBLE
                stateProvinceTextView.text = itemView.context.getString(R.string.state_province_label, university.state_province)
            } else {
                stateProvinceTextView.visibility = View.GONE
            }

            domainsTextView.text = itemView.context.getString(R.string.domains_label, university.domains.joinToString(", "))
            webPagesTextView.text = itemView.context.getString(R.string.web_pages_label, university.web_pages.joinToString(", "))
        }
    }

    // Add this function to update the data
    fun updateData(newUniversityList: List<University>) {
        universityList = newUniversityList
        notifyDataSetChanged() // Notify RecyclerView of data change
    }

}
