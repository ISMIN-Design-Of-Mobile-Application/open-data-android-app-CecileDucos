package com.ismin.opendataapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SchoolService {
    @GET("search/?dataset=fr-esr-principaux-etablissements-enseignement-superieur%40mesr")
    fun getRecords(@Query("rows") nbRows: Int) : Call<Server>
}