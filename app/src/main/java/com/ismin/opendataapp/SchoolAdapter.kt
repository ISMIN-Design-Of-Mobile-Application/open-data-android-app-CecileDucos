package com.ismin.opendataapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SchoolAdapter (private val listSchool: ArrayList<School>,
                     private val detailsElement: (Int) -> Unit) : RecyclerView.Adapter<SchoolViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.row_school, parent, false)
        return SchoolViewHolder(row)
    }

    override fun onBindViewHolder(holder: SchoolViewHolder, position: Int) {
        val (name, city) = this.listSchool[position]

        holder.tvxNameSchool.text = name
        holder.tvxCitySchool.text = city
        holder.rowSchool.setOnClickListener {
            detailsElement(position)

        }
    }

    override fun getItemCount(): Int {
        return this.listSchool.size
    }
}
