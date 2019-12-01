package com.ismin.opendataapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class SchoolViewHolder (rootView: View) : RecyclerView.ViewHolder(rootView){

    var tvxNameSchool: TextView
    var tvxCitySchool: TextView
    var rowSchool: ConstraintLayout

    init {
        this.tvxNameSchool = rootView.findViewById(R.id.textSchoolName)
        this.tvxCitySchool = rootView.findViewById(R.id.textSchoolCity)
        this. rowSchool = rootView.findViewById(R.id.row_school)
    }



}
